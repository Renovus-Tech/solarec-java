package tech.renovus.solarec.certificate.greenhub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.certificate.common.CertificateService;
import tech.renovus.solarec.certificate.greenhub.api.Client;
import tech.renovus.solarec.certificate.greenhub.api.Location;
import tech.renovus.solarec.certificate.greenhub.api.Mode;
import tech.renovus.solarec.certificate.greenhub.api.Record;
import tech.renovus.solarec.certificate.greenhub.api.Redemption;
import tech.renovus.solarec.certificate.greenhub.api.Response;
import tech.renovus.solarec.certificate.greenhub.api.Sdg;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

@Service
@ConditionalOnProperty(name = "solarec.service.certificate.provider", havingValue = "greenhub")
public class GreenhubService implements CertificateService {
	
	//--- Private constants ---------------------
	private static final String END_POINT_LOCATION				= "/location";
	private static final String END_POINT_LOCATION_GENERATIONR	= "/location/generationr";
	private static final String END_POINT_CLIENT				= "/client";
//	private static final String END_POINT_BUYER					= "/buyer";
//	private static final String END_POINT_BUY_RECS				= "/buyer/recs";
	private static final String END_POINT_REDEMPTION_EXISTS		= "/redemption/${redemption}/exists";

	//--- Private properties --------------------
	private @Autowired GreenhubConfiguration config; 
	private @Resource GenDataDao genDataDao;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'sss'Z'");
	
	//--- Private methods -----------------------
	private <T extends Object> Response<T> doPostCall(String endPoint, T payload) {
		return JsonCaller.post(this.config.getUrl() + endPoint, payload, new Response<T>().getClass());
	}
	
	private <T extends Object> Response<T> doGetCall(String endPoint, Class<T> response) { 
		return JsonCaller.get(this.config.getUrl() + endPoint, new Response<T>().getClass());
	}
	
	private Double convertGMT(String gmt) {
		boolean isNegative	= gmt.startsWith("-");
        String[] parts		= (isNegative ? gmt.substring(1) : gmt).split(":");
        int hours			= Integer.parseInt(parts[0]);
        int minutes			= parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        double result		= hours + minutes / 60.0;
        return isNegative ? -result : result;
	}
	
	private void setMode(Mode mode) {
		mode.setTestMode(Boolean.valueOf(this.config.getTestMode())); 
	}
	
	private <T extends Object> void checkSuccesResponse(Response<T> response) throws CertificateServiceException {
		if (response.getStatusCode() == null) {
			throw new CertificateServiceException("No response return from server.");
		}
		if (response.getStatusCode().intValue() < 200 || response.getStatusCode().intValue() >= 300) {
			throw new CertificateServiceException("Response code no success: " + response.getStatusCode().toString());
		}
	}
	
	private Client createClient(ClientVo cliVo) throws CertificateServiceException {
		Client client = new Client()
				.withExternalId(cliVo.getCliId())
				.withName(cliVo.getCliName())
				.withNameLegal(cliVo.getCliNameLegal())
				.withNameAddress(cliVo.getCliNameAddress())
			;
		
		Response<Client> response = this.doPostCall(END_POINT_CLIENT, client);
		
		this.checkSuccesResponse(response);
		return response.getData();
	}
	
	private Location createLocation(LocationVo locVo) throws CertificateServiceException {
		Location location = new Location()
				.withClientId(locVo.getCliId())
				.withExternalId(locVo.getLocId())
				.withName(locVo.getName())
				.withCode(locVo.getLocCode())
				.withAddress(locVo.getLocAddress())
				.withState(locVo.getLocState())
				.withCountry(locVo.getCountryVo().getCtrName())
				.withCountryCode2(locVo.getCountryVo().getCtrCode2())
				.withCountryCode3(locVo.getCountryVo().getCtrCode3())
				.withCoordLat(locVo.getLocCoordLat())
				.withCoordLng(locVo.getLocCoordLng())
				.withTimezoneGmt(this.convertGMT(locVo.getLocGmt()))
				.withOutputCapacity(locVo.getLocOutputCapacity())
				.withTypeOfInstallation(locVo.getLocType())
				.withConnectionToGrid(Boolean.valueOf(FlagUtil.getFlagValue(locVo, LocationVo.FLAG_CONNECTED_TO_GRID)))
				.withSdgs(new ArrayList<>())
			;

		if (CollectionUtil.notEmpty(locVo.getSdgs())) {
			locVo.getSdgs().stream().forEach(sdg -> 				new Sdg()
				.withName(sdg.getSdgVo().getSdgName())
				.withCode(sdg.getSdgVo().getSdgCode())
				.withDescription(sdg.getLocSdgDescription())
			);
		}
		
		this.setMode(location);
		
		Response<Location> response = this.doPostCall(END_POINT_LOCATION, location);
		
		this.checkSuccesResponse(response);
		
		return response.getData();
	}
	
	private Record recordGeneration(LocationVo locVo, Date dateStart, Date dateEnd, Double power) throws CertificateServiceException {
		Record recordToSend = new Record()
				.withLocationId(locVo.getLocId())
				.withDataProId(Integer.valueOf(Long.valueOf(System.currentTimeMillis()).intValue()))
				.withPower(power)
				.withDataStartDate(this.dateFormat.format(dateStart))
				.withDataEndDate(this.dateFormat.format(dateEnd))
			;
		
		Response<Record> response = this.doPostCall(END_POINT_LOCATION_GENERATIONR, recordToSend);
		
		this.checkSuccesResponse(response);
		return response.getData();

	}
	
	private boolean checkRedemptionExists(String redemption) throws CertificateServiceException {
		String url = StringUtil.replace(END_POINT_REDEMPTION_EXISTS, "${redemption}", redemption);
		
		Response<Redemption> response = this.doGetCall(url, Redemption.class);

		this.checkSuccesResponse(response);
		
		return BooleanUtils.isTrue(response.getData().getExists());
	}
	
	//--- Implemented methods -------------------
	@Override public void register(ClientVo cliVo) throws CertificateServiceException {
		Client client = this.createClient(cliVo);
		try {
			cliVo.setCliCertProvData(JsonUtil.toString(client));
			cliVo.setSyncType(ClientVo.SYNC_UPDATE);
			
			if (CollectionUtil.notEmpty(cliVo.getLocations())) {
				for (LocationVo locVo : cliVo.getLocations()) {
					Location location = this.createLocation(locVo);
					locVo.setLocCertProvData(JsonUtil.toString(location));
					locVo.setSyncType(LocationVo.SYNC_UPDATE);
				}
			}
		} catch (JsonProcessingException e) {
			throw new CertificateServiceException(e);
		}

	}
	
	@Override public void updateRegistration(ClientVo cliVo) throws CertificateServiceException {
		try {
			if (CollectionUtil.notEmpty(cliVo.getLocations())) {
				for (LocationVo locVo : cliVo.getLocations()) {
					if (StringUtil.notEmpty(locVo.getLocCertProvData())) {
						continue;
					}
					Location location = this.createLocation(locVo);
					locVo.setLocCertProvData(JsonUtil.toString(location));
					locVo.setSyncType(LocationVo.SYNC_UPDATE);
	
				}
			}
		} catch (JsonProcessingException e) {
			throw new CertificateServiceException(e);
		}
	}
	
	@Override public void registerGeneration(ClientVo cliVo) throws CertificateServiceException {
		if (CollectionUtil.notEmpty(cliVo.getLocations())) {
			for (LocationVo locVo : cliVo.getLocations()) {
				Collection<GenDataVo> dataToSend = this.genDataDao.getAllWithoutCertProvData(locVo.getCliId(), locVo.getLocId(), DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				
				if (CollectionUtil.notEmpty(dataToSend)) {
					long dateStart	= -1;
					long dateEnd	= -1;
					double power	= 0;
					
					for (GenDataVo genDataVo : dataToSend) {
						if (genDataVo.getDataValue() == null) {
							continue;
						}
						
						if (dateStart == -1) {
							dateStart	= genDataVo.getDataDate().getTime();
							dateEnd		= genDataVo.getDataDate().getTime();
						} else {
							dateStart	= Math.min(dateStart, genDataVo.getDataDate().getTime());
							dateEnd		= Math.min(dateEnd, genDataVo.getDataDate().getTime());
						}
						
						power += genDataVo.getDataValue().doubleValue();
					}
					
					try {
						Record dataRecord	= this.recordGeneration(locVo, new Date(dateStart), new Date(dateEnd), Double.valueOf(power));
						String jsonRecord	= JsonUtil.toString(dataRecord);
						
						dataToSend.stream().forEach(vo -> {
							vo.setGenDataCertProvData(jsonRecord);
							vo.setSyncType(GenDataVo.SYNC_UPDATE);
						});
						
						locVo.setGeneratorData(dataToSend);
						
					} catch (JsonProcessingException e) {
						throw new CertificateServiceException(e);
					}
				}
				
			}
		}
	}
	
	@Override public void generateCertificate(ClientVo client) {}
}

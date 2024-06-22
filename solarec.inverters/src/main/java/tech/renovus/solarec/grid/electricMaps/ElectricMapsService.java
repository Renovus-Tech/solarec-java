package tech.renovus.solarec.grid.electricMaps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.grid.electricMaps.api.CarbonIntensity;
import tech.renovus.solarec.grid.electricMaps.api.CarbonIntensityHistory;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;

/**
 * Request access to: https://api-portal.electricitymaps.com/
 * API Documentation: https://static.electricitymaps.com/api/docs/index.html
 * Prices: https://www.electricitymaps.com/pricing
 */
@Service
@ConditionalOnProperty(name = "solarec.service.grid.provider", havingValue = "electricmaps")
public class ElectricMapsService implements DataGridService {

	//--- Private constants ---------------------
	private static final String URL = "https://api.electricitymap.org";
	
	private static final String ENDPOINT_CARBON_INTENSITY_HISTORY = "/v3/carbon-intensity/history";
	
	private static final String EMISSION_FACTOR_TYPE_DIRECT		= "direct";
	private static final String EMISSION_FACTOR_TYPE_LIFECYCLE	= "lifecycle";
	
	//--- Resources -----------------------------
	@Autowired ElectricMapsConfiguration config;
	
	private SimpleDateFormat dateTimeFormat	= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	
	//--- Constructors --------------------------
	public ElectricMapsService() {}
	
	public ElectricMapsService(ElectricMapsConfiguration config) {
		this.config = config;
	}
	
	//--- Private methods -----------------------
	private Map<String, String> getAuthenticationHeader() {
		Map<String, String> headers = new HashMap<>();
		headers.put("auth-token", this.config.getKey());
		
		return headers;
	}
	
	private Double adjustValue(Integer carbonIntensity) {
		return carbonIntensity == null ? null : Double.valueOf(carbonIntensity.intValue() / (double) 1000);
	}

	//--- Implemented methods -------------------
	@Override
	public Collection<CtrDataVo> retrieveGridData(CountryVo ctrVo) throws DataGridServiceException {
		Map<String, String> params = new HashMap<>();
		params.put("disableEstimations", Boolean.TRUE.toString());
		params.put("zone", ctrVo.getCtrCode2());
		params.put("emissionFactorType", EMISSION_FACTOR_TYPE_DIRECT);
		
		CarbonIntensityHistory response = JsonCaller.get(
				URL + ENDPOINT_CARBON_INTENSITY_HISTORY, 
				this.getAuthenticationHeader(), 
				params, 
				CarbonIntensityHistory.class
			);
		
		Collection<CtrDataVo> result = new ArrayList<>();
		if (CollectionUtil.notEmpty(response.getHistory())) {
			try {
				Calendar cal = GregorianCalendar.getInstance();
				
				for (CarbonIntensity intensity : response.getHistory()) {
					cal.setTime(this.dateTimeFormat.parse(intensity.getDatetime()));
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					
					CtrDataVo data = new CtrDataVo();
					data.setDataTypeId(DataTypeVo.TYPE_COUNTRY_EMISSIONS_INTENSITY_GCO2_PER_KWH);
					data.setDataValue(adjustValue(intensity.getCarbonIntensity()));
					data.setDataDate(cal.getTime());
					
					result.add(data);
				}
			} catch (ParseException e ) {
				throw new DataGridServiceException(e);
			}
		}
		
		return result;
	}
}

package tech.renovus.solarec.inverters.brand.sofar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.sofar.api.AuthorizationRequest;
import tech.renovus.solarec.inverters.brand.sofar.api.AuthorizationResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.Device;
import tech.renovus.solarec.inverters.brand.sofar.api.DevideListResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.ListRequest;
import tech.renovus.solarec.inverters.brand.sofar.api.PermissionResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationDataItem;
import tech.renovus.solarec.inverters.brand.sofar.api.StationDeviceRequest;
import tech.renovus.solarec.inverters.brand.sofar.api.StationHistoryDataRequest;
import tech.renovus.solarec.inverters.brand.sofar.api.StationHistoryDataResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationListResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;

/**
 * API Documentation: https://forum.gce-electronics.com/uploads/short-url/3v9kqnZYHoiX53IZEl0XX1mKs1.pdf
 */
public class SofarInverterService implements InverterService {

	
	//--- Protected properties ------------------
	private static final String LOG_PREFIX						= "[Sofar] ";
	private static final String URL_PRD							= "https://api.solarmanpv.com";
	private static final String URL_BETA						= "http://120.195.219.223:30032";
	
	private static final String ENDPOINT_AUTHORIZATION			= "/account/v1.0/token";
	private static final String ENDPOINT_PERMISSION				= "/account/v1.0/role?language=en";
	private static final String ENDPOINT_DEVICE_LIST			= "/device/v1.0/list?language=en";
	private static final String ENDPOINT_STATION_LIST			= "/station/v1.0/list?language=en";
	private static final String ENDPOINT_STATION_DEVICE_LIST	= "/station/v1.0/device?language=en";
	private static final String ENDPOINT_STATION_HISTORY_DATA	= "/station/v1.0/history?language=en";
	
	protected static final String PARAM_BETA_MODE				= "sofar.beta";
	protected static final String PARAM_ACCESS_APP_ID			= "sofar.client.app_id";
	protected static final String PARAM_ACCESS_APP_SECRET		= "sofar.client.app_secret";
	protected static final String PARAM_ACCESS_APP_ORG_ID		= "sofar.client.app_org_id";
	protected static final String PARAM_ACCESS_APP_USER			= "sofar.client.app_user";
	protected static final String PARAM_ACCESS_APP_PASSWORD		= "sofar.client.app_password";
	protected static final String PARAM_GEN_STATION_ID			= "sofar.generator.station_id";
	protected static final String PARAM_CLI_LAST_DATE_RETRIEVE	= "sofar.client.last_retrieve";
	protected static final String PARAM_LOC_LAST_DATE_RETRIEVE	= "sofar.location.last_retrieve";
	protected static final String PARAM_GEN_LAST_DATE_RETRIEVE	= "sofar.generator.last_retrieve";
	
	//--- Private properties --------------------
	@Autowired WeatherService weatherService;
	private final SimpleDateFormat formatDate			= new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat formatDateTime		= new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private ClientVo cliVo;
	
	//--- Private methods -----------------------
	private List<GenDataVo> process(GeneratorVo generator, StationHistoryDataResponse data, Date fromDate) throws ParseException {
		//	501 no disponible
		//	502 generationValue
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getStationDataItems())) {
			InvertersUtil.logInfo(LOG_PREFIX + "Amount of data: {0}", Integer.toString(CollectionUtil.size(data.getStationDataItems())));
			for (StationDataItem aData : data.getStationDataItems()) {
				String aDate = new StringBuilder()
						.append(aData.getYear())
						.append("-")
						.append(aData.getMonth())
						.append("-")
						.append(aData.getDay())
						.append(" ")
						.append(aData.getDateTime())
						.toString();
				Date dataDate = this.formatDateTime.parse(aDate);
				
				if (dataDate.before(fromDate)) continue;
				
				GenDataVo genData = new GenDataVo();
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(dataDate);
				genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				genData.setDataValue((aData.getGenerationValue() == null ? Double.valueOf(0) : aData.getGenerationValue()));
				
				result.add(genData);
			}
		} else {
			InvertersUtil.logInfo(LOG_PREFIX + "No data to process");
		}
		
		return result;
	}
	
	private void retrieveData(InverterData inverterData, LocationVo location, StationVo station, GeneratorVo generator, Date dateFrom, Date to) throws WeatherServiceException {
		String appId		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_ID);
		String appSecret	= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_SECRET);
		Integer appOrgId	= Integer.valueOf(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_ORG_ID));
		String appUser		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_USER);
		String appPassword	= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_PASSWORD);
		boolean betaMode 	= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_BETA_MODE));
		
		String url			= this.getUrl(betaMode);
		
		InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE_TIME), DateUtil.formatDateTime(to, DateUtil.FMT_PARAMETER_DATE_TIME));
		
		AuthorizationResponse authorization = this.getAuthorize(url, appId, appSecret, appOrgId, appUser, appPassword);
		
		Integer stationId				= Integer.valueOf(InvertersUtil.getParameter(generator, PARAM_GEN_STATION_ID));
		StationHistoryDataResponse data = this.getStationHistoryData(url, authorization.getAccessToken(), stationId, dateFrom, dateFrom);
		
		try {
			List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
			
			if (CollectionUtil.notEmpty(generatorData)) {
				CollectionUtil.addAll(inverterData.getGeneratorData(), generatorData);
				
				Date lastDate = generatorData.stream().max(Comparator.comparing(GenDataVo::getDataDate)).get().getDataDate();
				
				InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(lastDate.getTime()));
				
				CollectionUtil.addAll(inverterData.getStationData(), this.weatherService.retrieveWeatherData(location, station, dateFrom, lastDate));
				
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(lastDate);
				cal.add(Calendar.MINUTE, 15); //we need next 15 min due to aggregation
				
				if (! cal.getTime().equals(dateFrom) && cal.getTime().before(to)) this.retrieveData(inverterData, location, station, generator, cal.getTime(), to);
				
			}
			
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
		} catch (ParseException e) {
			LoggerService.inverterLogger().error(LOG_PREFIX + "Error parsing data: " + e.getLocalizedMessage(), e);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
		}
	}
	
	//--- Overridden methods --------------------
	@Override
	public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}

	@Override public boolean canRetrieve() { return true; }
	@Override public boolean continueWithStats() { return true; }
	@Override public String getReasonWhyCantRetrieve() {return null; }

	@Override public InverterData retrieveData() throws InveterServiceException {
		Calendar cal = Calendar.getInstance();
		
		InverterData inverterData = new InverterData(new ArrayList<>(), new ArrayList<>());
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.isEmpty(location.getStations())) {
					LoggerService.inverterLogger().error(LOG_PREFIX + "Can't fina station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
					continue;
				}
				
				StationVo station = location.getStations().iterator().next();
				
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					for (GeneratorVo generator : location.getGenerators()) {
						boolean isDemoData 		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_BETA_MODE));
						String genLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE);
						Date dateFrom 			= InvertersUtil.calculateDateFrom(genLastRetrieve);

						cal.setTime(dateFrom);
						
						if (isDemoData) {
							cal.add(Calendar.YEAR, -1);
							dateFrom = cal.getTime();
						}
						
						cal.add(Calendar.DAY_OF_YEAR, 1);
						cal.add(Calendar.MILLISECOND, -1);
						
						Date to = cal.getTime();
						
						try {
							this.retrieveData(inverterData, location, station, generator, dateFrom, to);
						} catch (WeatherServiceException e) {
							throw new InveterServiceException(e);
						}
					}
				}
			}
		}
		
		
		return inverterData;
	}

	//--- Public methods ------------------------
	public String getUrl(boolean betaMode) { return betaMode ? URL_BETA : URL_PRD; }
	
	public AuthorizationResponse getAuthorize(String url, String appId, String appSecret, Integer orgId, String userName, String password) {
		Map<String, String> params = new HashMap<>();
		params.put("appId", appId);
		params.put("language", "en");
		
		AuthorizationRequest request = new AuthorizationRequest()
				.withAppSecret(appSecret)
				.withUsername(userName)
				.withOrgId(orgId)
				.withPassword(password);
		
		return JsonCaller.post(
				JsonCaller.generateCompleteURL(url + ENDPOINT_AUTHORIZATION, params), 
				request, 
				AuthorizationResponse.class
			);
	}
	
	public PermissionResponse getPermission(String url, String authCode) {
		return JsonCaller.bearerPost(
				url + ENDPOINT_PERMISSION,
				null, 
				authCode, 
				PermissionResponse.class
			);
	}
	
	public DevideListResponse getDeviveList(String url, String authCode) {
		return JsonCaller.bearerPost(
				url + ENDPOINT_DEVICE_LIST, 
				ListRequest.DEFAULT_VALUES, 
				authCode, 
				DevideListResponse.class
			);
	}
	
	public StationListResponse getStationList(String url, String authCode) {
		return JsonCaller.bearerPost(
				url + ENDPOINT_STATION_LIST, 
				ListRequest.DEFAULT_VALUES, 
				authCode, 
				StationListResponse.class
			);
	}
	
	public DevideListResponse getStationDeviceList(String url, String authCode, Integer stationId) {
		StationDeviceRequest request = new StationDeviceRequest()
				.withPage(Integer.valueOf(1))
				.withSize(Integer.valueOf(50))
				.withStationId(stationId)
				.withDeviceType(Device.TYPE_INVERTER);
		
		return JsonCaller.bearerPost(
				url + ENDPOINT_STATION_DEVICE_LIST, 
				request, 
				authCode, 
				DevideListResponse.class
			);
	}
	
	public StationHistoryDataResponse getStationHistoryData(String url, String authCode, Integer stationId, Date dateFrom, Date dateTo) {
		StationHistoryDataRequest request = new StationHistoryDataRequest()
				.withStationId(stationId)
				.withTimeType(StationHistoryDataRequest.TIME_TYPE_DAY)
				.withStartTime(this.formatDate.format(dateFrom))
				.withEndTime(this.formatDate.format(dateTo));
		
		return JsonCaller.bearerPost(
				url + ENDPOINT_STATION_HISTORY_DATA, 
				request, 
				authCode, 
				StationHistoryDataResponse.class
			);
	}
}

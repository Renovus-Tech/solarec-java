package tech.renovus.solarec.inverters.brand.fronius;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.Channel;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.Datum;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.DeviceListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.custom.chart.alerts.AlertTrigger;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;

/**
 * Documentation: https://api.solarweb.com/swqapi/index.html?utm_source=solar-web-system-mail&utm_medium=email&utm_campaign=SE__Solar-web-query-api-mails&utm_content=
 * 
 * URL: https://www.fronius.com/en/solarweb-query-api
 * URL API Beta: https://swqapi-beta.solarweb.com/index.html
 * 
 * Authentication format (2 header parameters):
 * 
 * GET https://api.solarweb.com/swqapi/pvsystems HTTP/1.1
 * AccessKeyId: FKIAFEF58CFEFA94486F9C804CF6077A01AB
 * AccessKeyValue: 47c076bc-23e5-4949-37a6-4bcfcf8d21d6
 * 
 * Data required from client: key_id and key_value
 * Data required from inverter: pv_systems_id
 */
public class FroniusInverterService implements InverterService {

	//-- Private constants ----------------------
	private static final String LOG_PREFIX	= "[Fronius] ";
	private static final double ADJUSTMENT_MULTIPLIER = 4;
	
	//--- Protected constants -------------------
	public static final String URL_PRD												= "https://api.solarweb.com/swqapi";
	public static final String URL_BETA												= "https://swqapi-beta.solarweb.com";

	public static final String ENDPOINT_INFO_RELEASE								= "/info/release";
	public static final String ENDPOINT_INFO_USER									= "/info/user";
	public static final String ENDPOINT_PV_SYSTEMS_LIST								= "/pvsystems-list";
	public static final String ENDPOINT_PV_SYSTEM_DEVICE_LIST						= "/pvsystems/{pvSystemId}/devices-list";
	
	public static final String ENDPOINT_PV_SYSTEMS_HISTORY_DATA						= "/pvsystems/{pvSystemId}/histdata";
	public static final String ENDPOINT_PV_SYSTEMS_DEVICE_HISTORY_DATA				= "/pvsystems/{pvSystemId}/devices/{deviceId}/histdata";
	public static final String ENDPOINT_PV_SYSTEM_AGGREGATED_DATA_SPECIFIC_DATE		= "/pvsystems/{pvSystemId}/aggdata/years/{year}/months/{month}/days/{day}";
	
	public static final String PV_SYSTEMS_HIST_DATA_LIMIT							= "288";
	
	public static final String PARAM_BETA_MODE										= "fronius.beta";
	public static final String PARAM_ACCESS_KEY_ID									= "fronius.client.key_id";
	public static final String PARAM_ACCESS_KEY_VALUE								= "fronius.client.key_value";
	public static final String PARAM_CLI_LAST_DATE_RETRIEVE							= "fronius.client.last_retrieve";
	public static final String PARAM_LOC_LAST_DATE_RETRIEVE							= "fronius.location.last_retrieve";
	public static final String PARAM_GEN_PV_SYSTEM_ID								= "fronius.generator.pv_systems_id";
	public static final String PARAM_GEN_LAST_DATE_RETRIEVE							= "fronius.generator.last_retrieve";
	
	protected static final String PARAM_DATA_DEMO									= "fronius.data_demo";

	//--- Resources -----------------------------
	private @Autowired RenovusSolarecConfiguration configuration;
	private @Autowired WeatherService weatherService;
	private @Autowired JsonCaller jsonCaller;

	//--- Private properties --------------------
	private final SimpleDateFormat formatDate							= new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'");	
	private ClientVo cliVo;
	private boolean continueWithStats = true;
	
	//--- Private methods -----------------------
	private String getUrl(boolean betaMode) { return betaMode ? URL_BETA : URL_PRD; }
	
	private Map<String, String> getAuthenticationHeaders(String accessKeyId, String accessKeyValue) {
		Map<String, String> result = new HashMap<>(2);
		
		result.put("AccessKeyId", accessKeyId);
		result.put("AccessKeyValue", accessKeyValue);
		
		return result;
	}
	
	private Date adjustGmt(Date aDate, String gmt) {
		if (StringUtil.isEmpty(gmt)) {
			return aDate;
		}
		String[] parts = StringUtil.split(gmt, ":");
		if (parts == null || parts.length != 2) {
			return aDate;
		}
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		
		if (hours < 0) {
			minutes *= -1;
		}
		return DateUtil.addUnit(aDate, Calendar.MINUTE, (hours * 60) + minutes);
	}
	
	private List<GenDataVo> process(GeneratorVo generator, HistoryDataResponse data, Date fromDate, String gmt) throws ParseException {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getData())) {
			InvertersUtil.logInfo("Amount of data: {0}", Integer.toString(CollectionUtil.size(data.getData())));
			for (Datum aData : data.getData()) {
				if (CollectionUtil.notEmpty(aData.getChannels())) {
					for (Channel aChannel : aData.getChannels()) {
						if (! "EnergyProductionTotal".equals(aChannel.getChannelName())) {
							continue;
						}
						
						Date dataDate = this.formatDate.parse(aData.getLogDateTime());
						dataDate = this.adjustGmt(dataDate, gmt);
						if (dataDate.before(fromDate)) {
							continue;
						}
						
						GenDataVo genData = new GenDataVo();
						genData.setCliId(generator.getCliId());
						genData.setGenId(generator.getGenId());
						genData.setDataDate(dataDate);
						genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
						genData.setDataValue(aChannel.getValue() == null ? null : Double.valueOf((aChannel.getValue().intValue() * ADJUSTMENT_MULTIPLIER )/ 1000 )); //Values in WH
						
						result.add(genData);
					}
				}
			}
		} else {
			InvertersUtil.logInfo("No data to process");
		}
		
		return result;
	}
	
	private List<GenDataVo> aggregate(List<GenDataVo> dataValues) {
		Calendar cal = Calendar.getInstance();
		
		Map<Date, GenDataVo> result = new HashMap<>(CollectionUtil.size(dataValues) / 3);
		dataValues.forEach(
                data -> {
                	cal.setTime(data.getDataDate());
            		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) / 15 * 15);
            		Date simpleDate = cal.getTime();
                	
                	result.computeIfAbsent(simpleDate, x -> new GenDataVo(data.getCliId(), data.getGenId(), x, data.getDataTypeId()));
                	result.get(simpleDate).add(data.getDataValue());
                }
        );
		
		result.values().forEach(x -> x.sum());
		
		return new ArrayList<>(result.values());
	}
	
	private String generateUrl(boolean betaMode, String endPoint, String... variables) {
		String url = this.getUrl(betaMode) + endPoint;
		
		for (int pos = 0; pos < variables.length; pos += 2) {
			url = url.replaceFirst(variables[pos], variables[pos +1]);
		}
		
		return url;
	}
	
	private void retrieveData(InverterData inverterData, LocationVo location, StationVo station, GeneratorVo generator, Date dateFrom, Date to) throws IOException, WeatherServiceException {
		String accessKeyId		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_KEY_ID);
		String accessKeyValue	= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_KEY_VALUE);
		String pvSystemsId		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_GEN_PV_SYSTEM_ID);
		boolean betaMode 		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_BETA_MODE));
		
		InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE_TIME), DateUtil.formatDateTime(to, DateUtil.FMT_PARAMETER_DATE_TIME));
		
		HistoryDataResponse data = this.getPvSystemsHistData(betaMode, accessKeyId, accessKeyValue, pvSystemsId, dateFrom, to);
		
		if (data == null || data.hasError()) {
			this.continueWithStats = false;
			String errorMessage = data == null ? "No data response from server." : "Error parsing data: " + data.getResponseError() + " - " + data.getResponseMessage();
			LoggerService.inverterLogger().error(LOG_PREFIX + errorMessage);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
			
			Date dateError = new Date();
			dateError = DateUtil.clearTime(dateError);
			dateError = DateUtil.addUnit(dateError, Calendar.MINUTE, -1);
			
			AlertTrigger trigger = new AlertTrigger();
			trigger.setDate(formatDate.format(dateError));
			trigger.setType(AlertTrigger.TYPE_CUSTOM);
			trigger.setDescription(
					"Error during data retrieve: " + errorMessage + 
					" - Date to retrieve: " + this.formatDate.format(dateFrom) + 
					" - Date of error: " + this.formatDate.format(new Date()) + 
					" - Location: " + location.getLocName() + 
					" - Inverter: " + generator.getGenName()
				);
			
			try {
				inverterData.add(InvertersUtil.generateAlert(generator, dateError, trigger));
			} catch (InveterServiceException e) {
				LoggerService.inverterLogger().error(LOG_PREFIX + "Error generating alert: " + e.getLocalizedMessage(), e);
			}
			
			return;
		}
		
		try {
			String gmtToUse = location.getLocGmt();
			if (StringUtil.isEmpty(gmtToUse)) {
				gmtToUse = this.cliVo.getCliGmt();
			}
			
			List<GenDataVo> generatorData = this.process(generator, data, dateFrom, gmtToUse);
			generatorData = this.aggregate(generatorData);
			
			if (CollectionUtil.notEmpty(generatorData)) {
				CollectionUtil.addAll(inverterData.getGeneratorData(), generatorData);
				
				Date lastDate = generatorData.stream().max(Comparator.comparing(GenDataVo::getDataDate)).get().getDataDate();
				
				InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(lastDate.getTime()));
				
				CollectionUtil.addAll(inverterData.getStationData(), this.retrieveWeatherData(location, station, dateFrom, lastDate, gmtToUse));
				
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(lastDate);
				cal.add(Calendar.MINUTE, 15); //we need next 15 min due to aggregation
				
				if (! cal.getTime().equals(dateFrom) && cal.getTime().before(to)) {
					this.retrieveData(inverterData, location, station, generator, cal.getTime(), to);
				}
				
			}
			
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
		} catch (ParseException e) {
			LoggerService.inverterLogger().error(LOG_PREFIX + "Error parsing data: " + e.getLocalizedMessage(), e);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
		}
	}

	private Collection<StaDataVo> retrieveWeatherData(LocationVo location, StationVo station, Date dateFrom, Date lastDate, String gmt) throws WeatherServiceException {
		Collection<StaDataVo> result = this.weatherService.retrieveWeatherData(location, station, dateFrom, lastDate);
		
		if (CollectionUtil.notEmpty(result)) {
			result.forEach(data -> data.setDataDate(this.adjustGmt(data.getDataDate(), gmt)));
		}
		
		return result;
	}
	
	//--- Implemented methods -------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	@Override public boolean continueWithStats() { return this.continueWithStats; }
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
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
						boolean isDemoData 		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_DATA_DEMO));
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
						} catch (IOException | WeatherServiceException e) {
							throw new InveterServiceException(e);
						}
					}
				}
			}
		}
		
		return inverterData;
	}

	//--- Public methods ------------------------
	public InfoReleaseResponse getInfoRelease(boolean betaMode, String accessKeyId, String accessKeyValue) {
		return this.jsonCaller.get(
				this.getUrl(betaMode) + ENDPOINT_INFO_RELEASE,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				InfoReleaseResponse.class);
	}
	
	public InfoUserResponse getInfoUser(boolean betaMode, String accessKeyId, String accessKeyValue) {
		return this.jsonCaller.get(
				this.getUrl(betaMode) + ENDPOINT_INFO_USER,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				InfoUserResponse.class);
	}
	
	public PvSystemsListResponse getPvSystemsList(boolean betaMode, String accessKeyId, String accessKeyValue) {
		return this.jsonCaller.get(
				this.getUrl(betaMode) + ENDPOINT_PV_SYSTEMS_LIST,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				PvSystemsListResponse.class
			);
	}

	public DeviceListResponse getPvSystemDevicesList(boolean betaMode, String accessKeyId, String accessKeyValue, String pvSystemsId) {
		return this.jsonCaller.get(
				this.generateUrl(betaMode, ENDPOINT_PV_SYSTEM_DEVICE_LIST, "\\{pvSystemId\\}", pvSystemsId),
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				DeviceListResponse.class
			);
	}
	
//	public AggregatedSpecificDate getPvSystemsAggredatedDataSpecificDate(boolean betaMode, String accessKeyId, String accessKeyValue, String pvSystemsId, int year, int month, int day) {
//		return this.jsonCaller.get(
//			this.generateUrl(betaMode, ENDPOINT_PV_SYSTEM_AGGREGATED_DATA_SPECIFIC_DATE, 
//					"\\{pvSystemId\\}", pvSystemsId ,
//					"\\{year\\}", Integer.toString(year), 
//					"\\{month\\}", Integer.toString(month), 
//					"\\{day\\}", Integer.toString(day) 
//				),
//			this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
//			null,
//			AggregatedSpecificDate.class
//		);
//	}
	
	public HistoryDataResponse getPvSystemsHistData(boolean betaMode, String accessKeyId, String accessKeyValue, String pvSystemsId, Date from, Date to) {
		Map<String, String> params = new HashMap<>(2);
		params.put("from", this.formatDate.format(from));
		params.put("to", this.formatDate.format(to));
		params.put("limit", PV_SYSTEMS_HIST_DATA_LIMIT);
		
		return this.jsonCaller.get(
				this.generateUrl(betaMode, ENDPOINT_PV_SYSTEMS_HISTORY_DATA, "\\{pvSystemId\\}", pvSystemsId ),
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				params,
				HistoryDataResponse.class
			);
	}
	
//	public DeviceHistoryDataResponse getPvSystemsDeviceHistData(boolean betaMode, String accessKeyId, String accessKeyValue, String pvSystemsId, String deviceId, Date from, Date to) {
//		Map<String, String> params = new HashMap<>(2);
//		params.put("from", this.formatDate.format(from));
//		params.put("to", this.formatDate.format(to));
//		params.put("limit", PV_SYSTEMS_HIST_DATA_LIMIT);
//		
//		return this.jsonCaller.get(
//				this.generateUrl(betaMode, ENDPOINT_PV_SYSTEMS_DEVICE_HISTORY_DATA, "\\{pvSystemId\\}", pvSystemsId, "\\{deviceId\\}", deviceId ),
//				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
//				params,
//				DeviceHistoryDataResponse.class
//			);
//	}

	public void setConfiguration(RenovusSolarecConfiguration configuration) {
		this.configuration = configuration;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public void setJsonCaller(JsonCaller jsonCaller) {
		this.jsonCaller = jsonCaller;
	}
}

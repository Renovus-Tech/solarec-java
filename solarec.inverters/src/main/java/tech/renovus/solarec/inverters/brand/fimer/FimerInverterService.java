package tech.renovus.solarec.inverters.brand.fimer;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.datalogger.IpRangeDataloggerResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.web.IpRangeWebResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.organization.OrganizationResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.Result;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.TelemetryDataEnergyTimeseriesResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
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
 * URL: https://solis-service.solisinverters.com/en/support/solutions/articles/44002212561-api-access-soliscloud
 * URL: https://documentation.auroravision.net/index.html%3Fp=1267.html <--
 * URL pasos: https://documentation.auroravision.net/es/index.html%3Fp=6884.html
 * swagger
 */
public class FimerInverterService implements InverterService {

	// --- Private constants ---------------------
	private static final String LOG_PREFIX										= "FIMER";
	private static final String URL												= "https://api.auroravision.net/api/rest";
	private static final String ENDPOINT_STATUS									= "/status";
	private static final String ENDPOINT_AUTHENTICATE							= "/authenticate";
	private static final String ENDPOINT_ORGANIZATION							= "/v1/portfolioGroup";

	private static final String ENDPOINT_TELEMETRY_DATA_POWER_TIMESERIES		= "/v1/stats/power/timeseries/{entityID}/{dataType}/{valueType}";
	private static final String ENDPOINT_TELEMETRY_DATA_ENERGY_TIMESERIES		= "/v1/stats/energy/timeseries/{entityID}/{dataType}/{valueType}";
	
	private static final String ENDPOINT_IP_RANGE_DATALOGGER					= "/v1/ip-ranges/datalogger";
	private static final String ENDPOINT_IP_RANGE_WEB							= "/v1/ip-ranges/web";
	
	
	public static final String DATA_TYPE_GENERATION_ENERGY						= "GenerationEnergy";
	public static final String DATA_TYPE_GENERATION_POWER						= "GenerationPower";
	
	public static final String VALUE_TYPE_DELTA									= "delta";
	public static final String VALUE_TYPE_AVERAGE								= "average";
	
	public static final String SAMPLE_SIZE_MIN_15								= "Min15";
	
	//--- Public constants ----------------------
	public static final String PARAM_USER										= "fimer.client.user";
	public static final String PARAM_PASSWORD									= "fimer.client.password";
	public static final String PARAM_KEY										= "fimer.client.key";
	public static final String PARAM_TIME_ZONE									= "fimer.client.timezone";
	public static final String PARAM_PORTAFOLIO_ID								= "fimer.client.portafolioId";
	public static final String PARAM_PLANT_ID									= "fimer.location.plantId";
	public static final String PARAM_DEVICE_ID									= "fimer.generator.deviceId";
	public static final String PARAM_CLIENT_LAST_RETRIEVE						= "fimer.client.last_retrieve";
	public static final String PARAM_LOCATION_LAST_RETRIEVE						= "fimer.location.last_retrieve";
	public static final String PARAM_GENERATOR_LAST_RETRIEVE					= "fimer.generator.last_retrieve";
	
	//--- Resources -----------------------------
	@Autowired RenovusSolarecConfiguration configuration;
	@Autowired WeatherService weatherService;

	//--- Private properties ---------------------
	
	private final SimpleDateFormat formatDate									= new SimpleDateFormat("yyyyMMdd");
	private ClientVo cliVo;
	private AuthenticateResponse authentication;
	
	// --- Private methods -----------------------
	private Map<String, String> generateHeaders(String auroraVisionApiKey) {
		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-Token", auroraVisionApiKey);
		return headers;
	}

	private List<GenDataVo> process(GeneratorVo generator, TelemetryDataEnergyTimeseriesResponse data, Date dateFrom, ZoneId zoneId) {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getResult())) {
			for (Result aData : data.getResult()) {
				Instant instant = Instant.ofEpochSecond(aData.getStart().intValue());
				ZonedDateTime zonedDateTime = instant.atZone(zoneId);
				Date dataDate = Date.from(zonedDateTime.toInstant());

				if (dataDate.before(dateFrom)) {
					continue;
				}
				
				Double value = aData.getValue();
				if (value == null) value = Double.valueOf(0);
				
				GenDataVo genData = new GenDataVo();
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(dataDate);
				genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				genData.setDataValue(value);
				
				result.add(genData);
			}
		}
		
		return result;
	}

	private Map<String, String> generateParams(String sampleSize, Date startDate, Date endDate, String timeZone) {
		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); 
		params.put("startDate", this.formatDate.format(startDate));
		params.put("endDate", this.formatDate.format(endDate)); 
		params.put("timeZone", timeZone);
		return params;
	}
	
	private String generateUrl(String url, int entityID, String dataType, String valueType) {
		url = url.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); 
		url = url.replaceFirst("\\{valueType\\}", valueType);
		return url;
	}
	
	private boolean isAuthenticated() {
		String authenticationKey = authentication == null ? null : authentication.getResult();
		return StringUtil.notEmpty(authenticationKey);
	}
	
	private  TelemetryDataEnergyTimeseriesResponse getTelemetryDataPowerTimeseries(
		String auroraVisionApiKey, 
		int entityID, 
		String dataType, 	// REQUIRED - Available values : GenerationEnergy, DCGenerationEnergy, Insolation, StorageInEnergy, StorageOutEnergy, GridEnergyExport, GridEnergyImport, SelfConsumedEnergy, ActiveEnergyEV, SessionEnergyEV
		String valueType, 	// REQUIRED - Available values : maximum, minimum, average, delta
		String sampleSize,	// REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		Date startDate, 	// REQUIRED - Pattern: yyyyMMdd
		Date endDate, 		// REQUIRED - Pattern: yyyyMMdd
		String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
	) {
		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);
		Map<String, String> params	= generateParams(sampleSize, startDate, endDate, timeZone); 
		String url					= this.generateUrl(URL + ENDPOINT_TELEMETRY_DATA_POWER_TIMESERIES, entityID, dataType, valueType); 
		TelemetryDataEnergyTimeseriesResponse response = JsonCaller.get(url, headers, params, TelemetryDataEnergyTimeseriesResponse.class);
		
		return response == null ? null : response;
	}

	private Collection<StaDataVo> retrieveWeatherData(LocationVo location, StationVo station, Date dateFrom, Date lastDate, String gmt) throws WeatherServiceException {
		Collection<StaDataVo> result = this.weatherService.retrieveWeatherData(location, station, dateFrom, lastDate);
		
		if (CollectionUtil.notEmpty(result)) result.forEach(data -> data.setDataDate(this.adjustGmt(data.getDataDate(), gmt)));
		
		return result;
	}

	private Date adjustGmt(Date aDate, String gmt) {
		if (StringUtil.isEmpty(gmt)) return aDate;
		String[] parts = StringUtil.split(gmt, ":");
		if (parts == null || parts.length != 2) return aDate;
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		
		if (hours < 0) minutes *= -1;
		return DateUtil.addUnit(aDate, Calendar.MINUTE, (hours * 60) + minutes);
	}
	
	// --- Implemented methods -------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	
	@Override 	public boolean continueWithStats() { return this.isAuthenticated(); }
	
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
	@Override public InverterData retrieveData() throws InveterServiceException {
		LoggerService.inverterLogger().info("[{}] [{}] Start retrieve for: {} ({})", LOG_PREFIX, this.cliVo.getCliName(), this.cliVo.getCliId());
		InverterData inverterData = new InverterData(new ArrayList<>(), new ArrayList<>());

		Date today		= new Date();
		Calendar cal	= GregorianCalendar.getInstance();
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					if (CollectionUtil.isEmpty(location.getStations())) {
						LoggerService.inverterLogger().error("[{}] Can't find station for client: {} - location: {}", LOG_PREFIX, this.cliVo.getCliName(), location.getLocName());
						continue;
					}
					
					StationVo station = location.getStations().iterator().next();
					
					for (GeneratorVo generator : location.getGenerators()) {
						this.authentication = this.authenticate(
								InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_USER),
								InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_PASSWORD),
								InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_KEY)
							);
						
						boolean authenticated = this.isAuthenticated();

						LoggerService.inverterLogger().info("[{}] [{}] Authentication ok: {} ", LOG_PREFIX, authenticated);
						if (authenticated) {
							String timeZone				= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_TIME_ZONE);
							int deviceId				= Integer.parseInt(InvertersUtil.getParameter(generator, PARAM_DEVICE_ID));
							String getLastRetrieve		= InvertersUtil.getParameter(generator, PARAM_GENERATOR_LAST_RETRIEVE);
							Date dateFrom 				= InvertersUtil.calculateDateFrom(getLastRetrieve);
							ZoneId zoneId				= ZoneId.of(timeZone);
							ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
							ZoneOffset zoneOffset 		= zonedDateTime.getOffset();
							String gmtToUse				= zoneOffset.toString();
							
							cal.setTime(dateFrom);
							cal.add(Calendar.DAY_OF_YEAR, 1);
							
							Date dateTo = cal.getTime();
							
							if (dateTo.after(today)) continue;
	
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_DATE), DateUtil.formatDateTime(dateTo, DateUtil.FMT_DATE));
							
							TelemetryDataEnergyTimeseriesResponse data = this.getTelemetryDataEnergyTimeseries(
									this.authentication.getResult(), 
									deviceId, 
									dateFrom, 
									dateTo, 
									timeZone
								);
							List<GenDataVo> generatorData = this.process(generator, data, dateFrom, zoneId);
							
							Date lastDate = null;
							if (CollectionUtil.notEmpty(generatorData)) {
								CollectionUtil.addAll(inverterData.getGeneratorData(), generatorData);
								
								Collections.reverse(generatorData);
								lastDate = generatorData.stream().max(Comparator.comparing(GenDataVo::getDataDate)).get().getDataDate();
								
								try {
									CollectionUtil.addAll(inverterData.getStationData(), this.retrieveWeatherData(location, station, dateFrom, lastDate, gmtToUse));
								} catch (WeatherServiceException e) {
									throw new InveterServiceException(e);
								}
							}
							
							lastDate = dateTo;
							
							InvertersUtil.setParameter(generator, PARAM_GENERATOR_LAST_RETRIEVE, Long.toString(lastDate.getTime()));
							
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
						} else {
							String error = authentication == null ? "n/a" : authentication.getError();
							String message = authentication == null ? "n/a" : authentication.getMessage();
							String exception = authentication == null ? "n/a" : authentication.getException();
							LoggerService.inverterLogger().error("[{}] Error: {} | {} | {}", LOG_PREFIX, error, message, exception);
						}
					}
				}
			}
		}

		LoggerService.inverterLogger().info("[{}] End retrieve for: {} ({})", LOG_PREFIX, this.cliVo.getCliName(), this.cliVo.getCliId());

		return inverterData;
	}
	
	// --- Public methods ------------------------
	public AuthenticateResponse authenticate(String user, String password, String key) {
		String credentials = user + ":" + password;
		String base64Credentials = new String(Base64.getEncoder().encode(credentials.getBytes(StandardCharsets.UTF_8)));

		Map<String, String> headers = new HashMap<>(2);
		headers.put(HttpHeaders.AUTHORIZATION, "Basic " + base64Credentials);
		headers.put("X-AuroraVision-ApiKey", key);

		AuthenticateResponse response = JsonCaller.get(URL + ENDPOINT_AUTHENTICATE, headers, null,
				AuthenticateResponse.class);

		return response;
	}
	
	public TelemetryDataEnergyTimeseriesResponse getTelemetryDataEnergyTimeseries(
		String auroraVisionApiKey, 
		int entityID, 
		Date startDate, 	// REQUIRED - Pattern: yyyyMMdd
		Date endDate, 		// REQUIRED - Pattern: yyyyMMdd
		String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
	) {
		return this.getTelemetryDataEnergyTimeseries(
				auroraVisionApiKey, 
				entityID, 
				FimerInverterService.DATA_TYPE_GENERATION_ENERGY, 
				FimerInverterService.VALUE_TYPE_DELTA, 
				FimerInverterService.SAMPLE_SIZE_MIN_15, 
				startDate, 
				endDate, 
				timeZone
			);
	}
	
	private TelemetryDataEnergyTimeseriesResponse getTelemetryDataEnergyTimeseries(
		String auroraVisionApiKey, 
		int entityID, 
		String dataType, 	// REQUIRED - Available values : GenerationEnergy, DCGenerationEnergy, Insolation, StorageInEnergy, StorageOutEnergy, GridEnergyExport, GridEnergyImport, SelfConsumedEnergy, ActiveEnergyEV, SessionEnergyEV
		String valueType, 	// REQUIRED - Available values : maximum, minimum, average, delta
		String sampleSize,	// REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		Date startDate, 	// REQUIRED - Pattern: yyyyMMdd
		Date endDate, 		// REQUIRED - Pattern: yyyyMMdd
		String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
	) {
		//Need to add one day due to test asking 2024-09-22 an result starts with 2024-09-21
		startDate	= DateUtil.addUnit(startDate, Calendar.DAY_OF_MONTH, 1);
		endDate		= DateUtil.addUnit(endDate, Calendar.DAY_OF_MONTH, 1);
		
		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);
		Map<String, String> params	= this.generateParams(sampleSize, startDate, endDate, timeZone); 
		String url					= this.generateUrl(URL + ENDPOINT_TELEMETRY_DATA_ENERGY_TIMESERIES, entityID, dataType, valueType);
		
		TelemetryDataEnergyTimeseriesResponse response = JsonCaller.get(url, headers, params, TelemetryDataEnergyTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataEnergyTimeseriesResponse getTelemetryDataPowerTimeseries(
		String auroraVisionApiKey, 
		int entityID, 
		Date startDate, 	// REQUIRED - Pattern: yyyyMMdd
		Date endDate, 		// REQUIRED - Pattern: yyyyMMdd
		String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
	) {
		return this.getTelemetryDataPowerTimeseries(
				auroraVisionApiKey, 
				entityID, 
				FimerInverterService.DATA_TYPE_GENERATION_POWER, 
				FimerInverterService.VALUE_TYPE_AVERAGE, 
				FimerInverterService.SAMPLE_SIZE_MIN_15, 
				startDate, 
				endDate, 
				timeZone
			);
	}
	
	public IpRangeDataloggerResponse getIpRangeDatalogger(String auroraVisionApiKey) {
		// Package api.ipRanges.datalogger
		// Payload -
		// Response IpRangeDataloggerResponse
		// Description
		// Allows to retrieve IP/CIDR couples used by devices to contact and connect to Aurora vision.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_IP_RANGE_DATALOGGER;
		IpRangeDataloggerResponse response = JsonCaller.get(url, headers, null, IpRangeDataloggerResponse.class);

		return response == null ? null : response;
	}
	
	
	public IpRangeWebResponse getIpRangeWeb(String auroraVisionApiKey) {
		// Package api.ipRanges.datalogger
		// Payload -
		// Response IpRangeWebResponse
		// Description
		// Allows to retrieve IP/CIDR couples of the devices that are contatcted by Aurora vision. 

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_IP_RANGE_WEB;
		IpRangeWebResponse response = JsonCaller.get(url, headers, null, IpRangeWebResponse.class);

		return response == null ? null : response;
	}
	
	public OrganizationResponse getPortafolioGroup(String auroraVisionApiKey) {
		// Package api.organization
		// Payload -
		// Response OrganizationResponse
		// Description
		// Allows to retrieve info on the Organization (PortfolioGroup) the user is
		// associated with together with the list of active portfolios contained in the
		// Organization (portfolioGroupPortfolios).

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		OrganizationResponse response = JsonCaller.get(URL + ENDPOINT_ORGANIZATION, headers, null,
				OrganizationResponse.class);

		return response == null ? null : response;
	}

	
	public StatusResponse status() {
		return JsonCaller.get(URL + ENDPOINT_STATUS, StatusResponse.class);
	}
}

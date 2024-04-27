package tech.renovus.solarec.inverters.brand.fimer;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

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
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;
import tech.renovus.solarec.weather.meteoblue.MeteoblueWeatherServiceImpl;

/**
 * URL: https://solis-service.solisinverters.com/en/support/solutions/articles/44002212561-api-access-soliscloud
 * URL: https://documentation.auroravision.net/index.html%3Fp=1267.html <--
 * URL pasos: https://documentation.auroravision.net/es/index.html%3Fp=6884.html
 * swagger
 */
public class FimerInverterService implements InverterService {

	// --- Private constants ---------------------
	private static final SimpleDateFormat DATE_FORMAT							= new SimpleDateFormat("yyyyMMdd");
	
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
	public static final String PARAM_USER						= "fimer.client.user";
	public static final String PARAM_PASSWORD					= "fimer.client.password";
	public static final String PARAM_KEY						= "fimer.client.key";
	public static final String PARAM_TIME_ZONE					= "fimer.client.timezone";
	public static final String PARAM_PORTAFOLIO_ID				= "fimer.client.portafolioId";
	public static final String PARAM_PLANT_ID					= "fimer.location.plantId";
	public static final String PARAM_DEVICE_ID					= "fimer.generator.deviceId";
	public static final String PARAM_CLIENT_LAST_RETRIEVE		= "fimer.client.last_retrieve";
	public static final String PARAM_LOCATION_LAST_RETRIEVE		= "fimer.location.last_retrieve";
	public static final String PARAM_GENERATOR_LAST_RETRIEVE	= "fimer.generator.last_retrieve";
	
	
	//--- Private properties ---------------------
	private ClientVo cliVo;
	private AuthenticateResponse authentication;
	
	// --- Private methods -----------------------
	private Map<String, String> generateHeaders(String auroraVisionApiKey) {
		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-Token", auroraVisionApiKey);
		return headers;
	}

	private List<GenDataVo> process(GeneratorVo generator, TelemetryDataEnergyTimeseriesResponse data, Date dateFrom) {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getResult())) {
			for (Result aData : data.getResult()) {
				Date dataDate = new Date(aData.getStart().intValue() * 1000);
				if (dataDate.before(dateFrom)) continue;
				
				GenDataVo genData = new GenDataVo();
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(dataDate);
				genData.setDataTypeId(DataTypeVo.TYPE_GENERATOR_POWER_KWH);
				genData.setDataValue(aData.getValue());
				
				result.add(genData);
			}
		}
		
		return result;
	}

	private Map<String, String> generateParams(String sampleSize, Date startDate, Date endDate, String timeZone) {
		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); 
		params.put("startDate", DATE_FORMAT.format(startDate));
		params.put("endDate", DATE_FORMAT.format(endDate)); 
		params.put("timeZone", timeZone);
		return params;
	}
	
	private String generateUrl(String url, int entityID, String dataType, String valueType) {
		url = url.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); 
		url = url.replaceFirst("\\{valueType\\}", valueType);
		return url;
	}
	
	private Date calculateFrom(String genLastRetrieve) {
		Calendar cal = Calendar.getInstance();
		
		if (StringUtil.isEmpty(genLastRetrieve)) {
			cal.add(Calendar.DAY_OF_YEAR, -1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);
		} else {
			cal.setTimeInMillis(Long.parseLong(genLastRetrieve));
		}
		
		return cal.getTime();
	}
	
	private boolean isAuthenticated() {
		String authenticationKey = authentication == null ? null : authentication.getResult();
		return StringUtil.notEmpty(authenticationKey);
	}
	

	// --- Implemented methods -------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	
	@Override 	public boolean continueWithStats() { return this.isAuthenticated(); }
	
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
	@Override public InverterData retrieveData() throws InveterServiceException {
		long t = System.currentTimeMillis();
		LoggerService.inverterLogger().info("[{t}] Start retrieve for: {client} ({cliId})", t, this.cliVo.getCliName(), this.cliVo.getCliId());
		InverterData result = new InverterData(new ArrayList<>(), new ArrayList<>());

		Calendar cal = GregorianCalendar.getInstance();
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					if (CollectionUtil.isEmpty(location.getStations())) {
						LoggerService.inverterLogger().error("Can't fina station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
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

						LoggerService.inverterLogger().info("[{t}] Authentication ok: {authenticated} ", t, authenticated);
						if (authenticated) {
							String timeZone = InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_TIME_ZONE);

							int deviceId			= Integer.parseInt(InvertersUtil.getParameter(generator, PARAM_DEVICE_ID));
							String getLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GENERATOR_LAST_RETRIEVE);
							Date dateFrom 			= this.calculateFrom(getLastRetrieve);
							
							cal.setTime(dateFrom);
							cal.add(Calendar.DAY_OF_YEAR, 1);
							cal.add(Calendar.MILLISECOND, -1);
							
							Date dateTo = cal.getTime();
	
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_DATE), DateUtil.formatDateTime(dateTo, DateUtil.FMT_DATE));
							
							TelemetryDataEnergyTimeseriesResponse data = this.getTelemetryDataPowerTimeseries(
									this.authentication.getResult(), 
									deviceId, 
									DATA_TYPE_GENERATION_POWER, 
									VALUE_TYPE_DELTA,
									SAMPLE_SIZE_MIN_15, 
									dateFrom, 
									dateTo, 
									timeZone
								);
							
							List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
							
							if (CollectionUtil.notEmpty(generatorData)) {
								CollectionUtil.addAll(result.getGeneratorData(), generatorData);
								
								Collections.reverse(generatorData);
								GenDataVo lastData = generatorData.iterator().next();
								
								try {
									CollectionUtil.addAll(result.getStationData(), new MeteoblueWeatherServiceImpl().retrieveWeatherData(location, station, dateFrom, dateTo));
								} catch (WeatherServiceException e) {
									throw new InveterServiceException(e);
								}
								
								InvertersUtil.setParameter(generator, PARAM_GENERATOR_LAST_RETRIEVE, Long.toString(lastData.getDataDate().getTime()));
							}
							
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
						} else {
							LoggerService.inverterLogger().error("[{t}] Error: {s} | {s} | {s}", t, authentication.getError(), authentication.getMessage(), authentication.getException());
						}
					}
				}
			}
		}

		LoggerService.inverterLogger().info("[{t}] End retrieve for: {client} ({cliId})", t, this.cliVo.getCliName(), this.cliVo.getCliId());

		return result;
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
		String dataType, 	// REQUIRED - Available values : GenerationEnergy, DCGenerationEnergy, Insolation, StorageInEnergy, StorageOutEnergy, GridEnergyExport, GridEnergyImport, SelfConsumedEnergy, ActiveEnergyEV, SessionEnergyEV
		String valueType, 	// REQUIRED - Available values : maximum, minimum, average, delta
		String sampleSize,	// REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		Date startDate, 	// REQUIRED - Pattern: yyyyMMdd
		Date endDate, 	// REQUIRED - Pattern: yyyyMMdd
		String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
	) {
		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);
		Map<String, String> params	= this.generateParams(sampleSize, startDate, endDate, timeZone); 
		String url					= this.generateUrl(URL + ENDPOINT_TELEMETRY_DATA_ENERGY_TIMESERIES, entityID, dataType, valueType);
		
		TelemetryDataEnergyTimeseriesResponse response = JsonCaller.get(url, headers, params, TelemetryDataEnergyTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataEnergyTimeseriesResponse getTelemetryDataPowerTimeseries(
			String auroraVisionApiKey, 
			int entityID, 
			String dataType, 	// REQUIRED - Available values : GenerationEnergy, DCGenerationEnergy, Insolation, StorageInEnergy, StorageOutEnergy, GridEnergyExport, GridEnergyImport, SelfConsumedEnergy, ActiveEnergyEV, SessionEnergyEV
			String valueType, 	// REQUIRED - Available values : maximum, minimum, average, delta
			String sampleSize,	// REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
			Date startDate, 	// REQUIRED - Pattern: yyyyMMdd
			Date endDate, 	// REQUIRED - Pattern: yyyyMMdd
			String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
		) {
		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);
		Map<String, String> params	= generateParams(sampleSize, startDate, endDate, timeZone); 
		String url					= this.generateUrl(URL + ENDPOINT_TELEMETRY_DATA_POWER_TIMESERIES, entityID, dataType, valueType); 
		TelemetryDataEnergyTimeseriesResponse response = JsonCaller.get(url, headers, params, TelemetryDataEnergyTimeseriesResponse.class);
		
		return response == null ? null : response;
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

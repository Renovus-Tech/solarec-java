package tech.renovus.solarec.inverters.brand.fimer;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fimer.api.asset.AssetInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.devices.device.DevicesDeviceResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.devices.events.DevicesEventsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.devices.info.DevicesInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.devices.status.DevicesStatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.datalogger.IpRangeDataloggerResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.web.IpRangeWebResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.kpi.aggregated.KpiAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.kpi.timeseries.KpiTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.logger.devices.LoggerDevicesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.logger.events.LoggerEventsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.logger.info.LoggerInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.logger.status.LoggerStatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.organization.OrganizationResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plants.billingData.BillingDataResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plants.events.PlantEventsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plants.info.PlantInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plants.loggers.LoggersResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plants.status.PlantStatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plants.weather.PlantWeatherResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plantsGroups.info.InfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plantsGroups.plants.PlantsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.info.PortfolioInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plantGroup.PortfolioPlantGroupResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plants.PortfolioPlantsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.region.RegionResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.current.aggregated.TelemetryDataCurrentAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.current.timeseries.TelemetryDataCurrentTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.aggregated.TelemetryDataEnergyAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.Result;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.TelemetryDataEnergyTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.frequency.aggregated.TelemetryDataFrequencyAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.frequency.timeseries.TelemetryDataFrequencyTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.power.aggregated.TelemetryDataPowerAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.power.timeseries.TelemetryDataPowerTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.temperature.aggregated.TelemetryDataTemperatureAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.temperature.timeseries.TelemetryDataTemperatureTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.voltage.aggregated.TelemetryDataVoltageAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.voltage.timeseries.TelemetryDataVoltageTimeseriesResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.wind.aggregated.TelemetryDataWindAggregatedResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.wind.timeseries.TelemetryDataWindTimeseriesResponse;
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
import tech.renovus.solarec.weather.WeatherService;
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
	private static final String URL												= "https://api.auroravision.net/api/rest";
	private static final String ENDPOINT_STATUS									= "/status";
	private static final String ENDPOINT_AUTHENTICATE							= "/authenticate";
//	private static final String ENDPOINT_ASSET_INFO								= "/v1/asset/{entityID}/info";
	private static final String ENDPOINT_ORGANIZATION							= "/v1/portfolioGroup";

//	private static final String ENDPOINT_PORTFOLIO_PLANTS						= "/v1/portfolio/{entityID}/plants";
//	private static final String ENDPOINT_PORTFOLIO_PLANT_GROUP					= "/v1/portfolio/{entityID}/plantGroups";
//	private static final String ENDPOINT_PORTFOLIO_INFO							= "/v1/portfolio/{entityID}/info";

//	private static final String ENDPOINT_PLANT_GROUPS_PLANTS					= "/v1/plantGroup/{entityID}/plants";
//	private static final String ENDPOINT_PLANT_GROUPS_INFO						= "/v1/plantGroup/{entityID}/info";

//	private static final String ENDPOINT_PLANT_LOGGERS 							= "/v1/plant/{entityID}/loggers";
//	private static final String ENDPOINT_PLANT_BILLING_DATA						= "/v1/plant/{entityID}/billingData";
//	private static final String ENDPOINT_PLANT_DIALY_PROD						= "/v1/plant/{entityID}/dailyProduction";
//	private static final String ENDPOINT_PLANT_INFO								= "/v1/plant/{entityID}/info";
//	private static final String ENDPOINT_PLANT_STATUS							= "/v1/plant/{entityID}/status";
//	private static final String ENDPOINT_PLANT_EVENTS							= "/v1/plant/{entityID}/events";
//	private static final String ENDPOINT_PLANT_WEATHER							= "/v1/plant/{entityID}/weather";

//	private static final String ENDPOINT_LOGGER_DEVICES							= "/v1/logger/{entityID}/devices";
//	private static final String ENDPOINT_LOGGER_INFO							= "/v1/logger/{entityID}/info";
//	private static final String ENDPOINT_LOGGER_STATUS							= "/v1/logger/{entityID}/status";
//	private static final String ENDPOINT_LOGGER_EVENTS							= "/v1/logger/{entityID}/events";

//	private static final String ENDPOINT_DEVICE									= "/v1/device/";
//	private static final String ENDPOINT_DEVICE_INFO							= "/v1/device/{entityID}/info";
//	private static final String ENDPOINT_DEVICE_STATUS							= "/v1/device/{entityID}/status";
//	private static final String ENDPOINT_DEVICE_EVENTS							= "/v1/device/{entityID}/events";

//	private static final String ENDPOINT_TELEMETRY_DATA_POWER_AGGREGATED		= "/v1/stats/power/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_FREQUENCY_AGGREGATED	= "/v1/stats/frequency/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_WIND_AGGREGATED			= "/v1/stats/wind/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_TEMPERATURE_AGGREGATED	= "/v1/stats/temperature/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_VOLTAGE_AGGREGATED		= "/v1/stats/voltage/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_CURRENT_AGGREGATED 		= "/v1/stats/current/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_ENERGY_AGGREGATED		= "/v1/stats/energy/aggregated/{entityID}/{dataType}/{valueType}";

//	private static final String ENDPOINT_TELEMETRY_DATA_POWER_TIMESERIES		= "/v1/stats/power/timeseries/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_FREQUENCY_TIMESERIES	= "/v1/stats/frequency/timeseries/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_WIND_TIMESERIES			= "/v1/stats/wind/timeseries/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_TEMPERATURE_TIMESERIES	= "/v1/stats/temperature/timeseries/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_VOLTAGE_TIMESERIES		= "/v1/stats/voltage/timeseries/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_TELEMETRY_DATA_CURRENT_TIMESERIES		= "/v1/stats/current/timeseries/{entityID}/{dataType}/{valueType}";
	private static final String ENDPOINT_TELEMETRY_DATA_ENERGY_TIMESERIES		= "/v1/stats/energy/timeseries/{entityID}/{dataType}/{valueType}";
	
//	private static final String ENDPOINT_KPI_AGGREGATED							= "/v1/kpis/aggregated/{entityID}/{dataType}/{valueType}";
//	private static final String ENDPOINT_KPI_TIMESERIES							= "/v1/kpis/timeseries/{entityID}/{dataType}/{valueType}";
	
//	private static final String ENDPOINT_REGION									= "/v1/region";
	
	private static final String ENDPOINT_IP_RANGE_DATALOGGER					= "/v1/ip-ranges/datalogger";
	private static final String ENDPOINT_IP_RANGE_WEB							= "/v1/ip-ranges/web";
	
	
	private static final String DATA_TYPE_GENERATION_ENERGY			= "GenerationEnergy";
//	private static final String DATA_TYPE_DC_GENERATION_ENERGY		= "DCGenerationEnergy";
//	private static final String DATA_TYPE_INSOLATION				= "Insolation";
//	private static final String DATA_TYPE_STORAGE_IN_ENERGY			= "StorageInEnergy";
//	private static final String DATA_TYPE_STORAGE_OUT_ENERGY		= "StorageOutEnergy";
//	private static final String DATA_TYPE_GRID_ENERGY_EXPORT		= "GridEnergyExport";
//	private static final String DATA_TYPE_GRID_ENERGY_IMPORT		= "GridEnergyImport";
//	private static final String DATA_TYPE_SELF_CONSUME_ENERGY		= "SelfConsumedEnergy";
//	private static final String DATA_TYPE_ACTIVE_ENERGY_EV			= "ActiveEnergyEV";
//	private static final String DATA_TYPE_SESSION_ENERGY_EV			= "SessionEnergyEV";
	
	
//	private static final String VALUE_TYPE_MAXIMUM		= "maximum";
//	private static final String VALUE_TYPE_MINIMUM		= "minimum";
//	private static final String VALUE_TYPE_AVERAGE		= "average";
	private static final String VALUE_TYPE_DELTA		= "delta";
	
//	private static final String SAMPLE_SIZE_MIN_5		= "Min5";
	private static final String SAMPLE_SIZE_MIN_15		= "Min15";
//	private static final String SAMPLE_SIZE_HOUR		= "Hour";
//	private static final String SAMPLE_SIZE_DAY			= "Day";
//	private static final String SAMPLE_SIZE_MONTH		= "Month";
//	private static final String SAMPLE_SIZE_YEAR		= "Year";
	
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
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		
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
							
							TelemetryDataEnergyTimeseriesResponse data = this.telemetryDataEnergyTimeseries(
									this.authentication.getResult(), 
									deviceId, 
									DATA_TYPE_GENERATION_ENERGY, 
									VALUE_TYPE_DELTA,
									SAMPLE_SIZE_MIN_15, 
									formater.format(dateFrom), 
									formater.format(dateTo), 
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
	
	public TelemetryDataEnergyTimeseriesResponse telemetryDataEnergyTimeseries(
		String auroraVisionApiKey, 
		int entityID, 
		String dataType, 	// REQUIRED - Available values : GenerationEnergy, DCGenerationEnergy, Insolation, StorageInEnergy, StorageOutEnergy, GridEnergyExport, GridEnergyImport, SelfConsumedEnergy, ActiveEnergyEV, SessionEnergyEV
		String valueType, 	// REQUIRED - Available values : maximum, minimum, average, delta
		String sampleSize,	// REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		String startDate, 	// REQUIRED - Pattern: yyyyMMdd
		String endDate, 	// REQUIRED - Pattern: yyyyMMdd
		String timeZone		// REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City) - Example: Europe/Rome
	) {
		// Package api.telemetryData.energy.timeseries
		// Payload -
		// Response TelemetryDataEnergyTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Energy or Insolation values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); 
		params.put("startDate", startDate);
		params.put("endDate", endDate); 
		params.put("timeZone", timeZone); 

		String url = URL + ENDPOINT_TELEMETRY_DATA_ENERGY_TIMESERIES;
		url = url.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); 
		url = url.replaceFirst("\\{valueType\\}", valueType); 
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

/*
	public AssetInfoResponse assetInfo(String auroraVisionApiKey, int entityID) {
		// Package api.asset
		// Payload -
		// Response AssetInfoResponse
		// Description
		// Allows to retrieve info on an Aurora Vision Asset.
		// An Asset can be a Portfolio, Plant, Plant Group, Logger or Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_ASSET_INFO.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		AssetInfoResponse response = JsonCaller.get(url, headers, null, AssetInfoResponse.class);

		return response == null ? null : response;
	}

	public PortfolioPlantsResponse portfolioPlants(String auroraVisionApiKey, int entityID) {
		// Package api.portfolio.plants
		// Payload -
		// Response PortfolioPlantsResponse
		// Description
		// Allows to retrieve the list of Plants in a Portfolio.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(1);
		params.put("page", "0"); // If not entered, the API always returns the first page.
									// Default value : 0 ( = First Page )

		String url = URL + ENDPOINT_PORTFOLIO_PLANTS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PortfolioPlantsResponse response = JsonCaller.get(url, headers, params, PortfolioPlantsResponse.class);

		return response == null ? null : response;
	}

	public PortfolioPlantGroupResponse portfolioPlantGroup(String auroraVisionApiKey, int entityID) {
		// Package api.portfolio.plantGroup
		// Payload -
		// Response PortfolioPlantGroupResponse
		// Description
		// Allows to retrieve the list of Plant Groups in a Portfolio.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(1);
		params.put("page", "0"); // If not entered, the API always returns the first page.
									// Default value : 0 ( = First Page )

		String url = URL + ENDPOINT_PORTFOLIO_PLANT_GROUP.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PortfolioPlantGroupResponse response = JsonCaller.get(url, headers, params, PortfolioPlantGroupResponse.class);

		return response == null ? null : response;
	}

	public PortfolioInfoResponse portfolioInfo(String auroraVisionApiKey, int entityID) {
		// Package api.portfolio
		// Payload -
		// Response PortfolioInfoResponse
		// Description
		// Allows to retrieve info of a Portfolio.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PORTFOLIO_INFO.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PortfolioInfoResponse response = JsonCaller.get(url, headers, null, PortfolioInfoResponse.class);

		return response == null ? null : response;
	}

	public PlantsResponse plantsGroups(String auroraVisionApiKey, int entityID, String plantStatus) {
		// Package api.plantsGroups.plants
		// Payload -
		// Response PlantsResponse
		// Description
		// Allows to retrieve the list of Plant(s) in a Plant Group.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(1);
		params.put("plantStatus", plantStatus); // Available values : HIGH, MEDIUM, LOW, NORM, INFO

		String url = URL + ENDPOINT_PLANT_GROUPS_PLANTS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PlantsResponse response = JsonCaller.get(url, headers, params, PlantsResponse.class);

		return response == null ? null : response;
	}

	public InfoResponse plantsGroupsInfo(String auroraVisionApiKey, int entityID) {
		// Package api.portfolio
		// Payload -
		// Response PortfolioInfoResponse
		// Description
		// Allows to retrieve info on a Plant Group.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PLANT_GROUPS_INFO.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		InfoResponse response = JsonCaller.get(url, headers, null, InfoResponse.class);

		return response == null ? null : response;
	}

	public LoggersResponse plantLoggers(String auroraVisionApiKey, int entityID) {
		// Package api.plants.loggers
		// Payload -
		// Response LoggersResponse
		// Description
		// Allows to retrieve the list of Loggers in a Plant.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PLANT_LOGGERS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		LoggersResponse response = JsonCaller.get(url, headers, null, LoggersResponse.class);

		return response == null ? null : response;
	}

	public BillingDataResponse plantBillingData(String auroraVisionApiKey, int entityID) {
		// Package api.plants.billingData
		// Payload -
		// Response BillingDataResponse
		// Description
		// Allows to retrieve Plant’s lifetime and current day produced energy (kWh).

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PLANT_BILLING_DATA.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		BillingDataResponse response = JsonCaller.get(url, headers, null, BillingDataResponse.class);

		return response == null ? null : response;
	}
*/
	/*
	 * public asdf plantDialyProd(String auroraVisionApiKey, int entityID, String
	 * startDate, String endDate) { // Package api.plants // Payload - // Response
	 * // Description // Allows to retrieve Plant’s daily produced energy.
	 * 
	 * Map<String, String> headers = new HashMap<>(1);
	 * headers.put("X-AuroraVision-ApiKey", auroraVisionApiKey);
	 * 
	 * Map<String, String> params = new HashMap<>(2); params.put("startDate",
	 * startDate); // Pattern: yyyyMMdd params.put("endDate", endDate); // Pattern:
	 * yyyyMMdd
	 * 
	 * String url = URL + ENDPOINT_PLANT_DIALY_PROD.replaceFirst("\\{entityID\\}"
	 * String.valueOf(entityID)); asdf response = JsonCaller.get(url, headers, null,
	 * asdf.class);
	 * 
	 * return response == null ? null : response; }
	 */
/*
	public PlantInfoResponse plantInfo(String auroraVisionApiKey, int entityID) {
		// Package api.plants.info
		// Payload -
		// Response PlantInfoResponse
		// Description
		// Allows to retrieve detailed info of a Plant.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PLANT_INFO.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PlantInfoResponse response = JsonCaller.get(url, headers, null, PlantInfoResponse.class);

		return response == null ? null : response;
	}

	public PlantStatusResponse plantStatus(String auroraVisionApiKey, int entityID) {
		// Package api.plants.status
		// Payload -
		// Response PlantStatusResponse
		// Description
		// Allows to retrieve Plant status. If the Plant Status is different from
		// NORMAL, returns the hierarchical explosion of loggers/devices affected by a
		// status different from NORMAL.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PLANT_STATUS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PlantStatusResponse response = JsonCaller.get(url, headers, null, PlantStatusResponse.class);

		return response == null ? null : response;
	}

	public PlantEventsResponse plantEvents(String auroraVisionApiKey, int entityID, String eventsKind,
			String eventsType, String eventsState, String eventsOccurrence, int page) {
		// Package api.plants.events
		// Payload -
		// Response PlantEventsResponse
		// Description
		// Allows to retrieve the list of Plant's Events.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(5);
		params.put("eventsKind", eventsKind); // REQUIRED - Available values : PROFILE, SOURCE
		params.put("eventsType", eventsType); // Available values : NOTCOM, DEVCOM, LOGCOM, PWROFF, GENFLT, GENALR,
												// TMPFLT, TMPALR, ACGFLT, ACGALR, ACVFLT, ACVALR, ACIFLT, ACFFLT,
												// ACFALR, DCGFLT, DCVFLT, DCIFLT, STROFF, DCVALR, ACIALR, VDCLOW,
												// SUBOFF, PR-ING, DCGALR, DCIALR, GENSTS, TMPSTS, ACGSTS, ACVSTS,
												// ACISTS, ACFSTS, DCGSTS, DCVSTS, DCISTS, MNTFLT, MNTALR, MNTSTS,
												// EXPLMT
		params.put("eventsState", eventsState); // Available values : ALL, ACTIVE, CLOSED
		params.put("eventsOccurrence", eventsOccurrence); // Available values : H24, D7, D30
		params.put("page", String.valueOf(page)); // Default value : 0 ( = First Page )

		String url = URL + ENDPOINT_PLANT_EVENTS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PlantEventsResponse response = JsonCaller.get(url, headers, params, PlantEventsResponse.class);

		return response == null ? null : response;
	}

	public PlantWeatherResponse plantWeather(String auroraVisionApiKey, int entityID) {
		// Package api.plants.weather
		// Payload -
		// Response PlantWeatherResponse
		// Description
		// Allows to retrieve weather data of a Plant.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_PLANT_WEATHER.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		PlantWeatherResponse response = JsonCaller.get(url, headers, null, PlantWeatherResponse.class);

		return response == null ? null : response;
	}

	public LoggerDevicesResponse loggerDevices(String auroraVisionApiKey, int entityID) {
		// Package api.logger.devices
		// Payload -
		// Response LoggerDevicesResponse
		// Description
		// Allows to retrieve the list of Devices monitored by a Logger.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_LOGGER_DEVICES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		LoggerDevicesResponse response = JsonCaller.get(url, headers, null, LoggerDevicesResponse.class);

		return response == null ? null : response;
	}

	public LoggerInfoResponse loggerInfo(String auroraVisionApiKey, int entityID) {
		// Package api.logger.info
		// Payload -
		// Response LoggerInfoResponse
		// Description
		// Allows to retrieve info of a Logger.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_LOGGER_INFO.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		LoggerInfoResponse response = JsonCaller.get(url, headers, null, LoggerInfoResponse.class);

		return response == null ? null : response;
	}

	public LoggerStatusResponse loggerStatus(String auroraVisionApiKey, int entityID) {
		// Package api.logger.status
		// Payload -
		// Response LoggerStatusResponse
		// Description
		// Allows to retrieve Logger status. If the Logger Status is different from
		// NORMAL, returns the hierarchical explosion of devices affected by a status
		// different from NORMAL.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_LOGGER_STATUS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		LoggerStatusResponse response = JsonCaller.get(url, headers, null, LoggerStatusResponse.class);

		return response == null ? null : response;
	}

	public LoggerEventsResponse loggerEvents(String auroraVisionApiKey, int entityID, String eventsKind,
			String eventsType, String eventsState, String eventsOccurrence, int page) {
		// Package api.logger.events
		// Payload -
		// Response LoggerEventsResponse
		// Description
		// Allows to retrieve the list of Logger's Events.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(5);
		params.put("eventsKind", eventsKind); // REQUIRED - Available values : PROFILE, SOURCE
		params.put("eventsType", eventsType); // Available values : DEVCOM, LOGCOM
		params.put("eventsState", eventsState); // Available values : ALL, ACTIVE, CLOSED
		params.put("eventsOccurrence", eventsOccurrence); // Available values : H24, D7, D30
		params.put("page", String.valueOf(page)); // Default value : 0 ( = First Page )

		String url = URL + ENDPOINT_LOGGER_EVENTS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		LoggerEventsResponse response = JsonCaller.get(url, headers, params, LoggerEventsResponse.class);

		return response == null ? null : response;
	}

	public DevicesDeviceResponse devicesDevice(String auroraVisionApiKey, String serialNumber) {
		// Package api.devices.device
		// Payload -
		// Response DevicesDeviceResponse
		// Description
		// Allows to dynamically retrieve the compaxt hierarchy starting from the device
		// Serial Number.
		// The compact hierarchy dynamically adapts according to the type of device
		// inserted in the request.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(1);
		params.put("serialNumber", serialNumber); // REQUIRED

		String url = URL + ENDPOINT_DEVICE;
		DevicesDeviceResponse response = JsonCaller.get(url, headers, params, DevicesDeviceResponse.class);

		return response == null ? null : response;
	}

	public DevicesInfoResponse devicesInfo(String auroraVisionApiKey, int entityID) {
		// Package api.devices.info
		// Payload -
		// Response DevicesInfoResponse
		// Description
		// Allows to retrieve info of a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_DEVICE_INFO.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		DevicesInfoResponse response = JsonCaller.get(url, headers, null, DevicesInfoResponse.class);

		return response == null ? null : response;
	}

	public DevicesStatusResponse devicesStatus(String auroraVisionApiKey, int entityID) {
		// Package api.devices
		// Payload -
		// Response DevicesStatusResponse
		// Description
		// Allows to retrieve Device staus.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		String url = URL + ENDPOINT_DEVICE_STATUS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		DevicesStatusResponse response = JsonCaller.get(url, headers, null, DevicesStatusResponse.class);

		return response == null ? null : response;
	}

	public DevicesEventsResponse devicesEvents(String auroraVisionApiKey, int entityID, String eventsKind,
			String eventsType, String eventsState, String eventsOccurrence, int page) {
		// Package api.devices.events
		// Payload -
		// Response DevicesEventsResponse
		// Description
		// Allows to retrieve the list of Devices's Events.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(5);
		params.put("eventsKind", eventsKind); // REQUIRED - Available values : PROFILE, SOURCE
		params.put("eventsType", eventsType); // Available values : DEVCOM, LOGCOM, PWROFF, GENFLT, GENALR, TMPFLT,
												// TMPALR, ACGFLT, ACGALR, ACVFLT, ACVALR, ACIFLT, ACFFLT, ACFALR,
												// DCGFLT, DCVFLT, DCIFLT, STROFF, DCVALR, ACIALR, VDCLOW, SUBOFF,
												// PR-ING, DCGALR, DCIALR, GENSTS, TMPSTS, ACGSTS, ACVSTS, ACISTS,
												// ACFSTS, DCGSTS, DCVSTS, DCISTS, MNTFLT, MNTALR, MNTSTS, EXPLMT
		params.put("eventsState", eventsState); // Available values : ALL, ACTIVE, CLOSED
		params.put("eventsOccurrence", eventsOccurrence); // Available values : H24, D7, D30
		params.put("page", String.valueOf(page)); // Default value : 0 ( = First Page )

		String url = URL + ENDPOINT_DEVICE_EVENTS.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		DevicesEventsResponse response = JsonCaller.get(url, headers, params, DevicesEventsResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataPowerAggregatedResponse telemetryDataPowerAggregated(String auroraVisionApiKey, int entityID,
			String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.power.aggregated
		// Payload -
		// Response TelemetryDataPowerAggregatedResponse
		// Description
		// Allows to retrieve aggregated Power or Irradiance values of a Plant or a
		// Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City
											// )
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_POWER_AGGREGATED.replaceFirst("\\{entityID\\}", String.valueOf(entityID))
				.replaceFirst("\\{dataType\\}", dataType) // REQUIRED - Available values : GenerationPower,
														// DCGenerationPower,
														// Irradiance, GridPowerExport, StoredPower, ActivePowerEV
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataPowerAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataPowerAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataFrequencyAggregatedResponse telemetryDataFrequencyAggregated(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.frequency.aggregated
		// Payload -
		// Response TelemetryDataFrequencyAggregatedResponse
		// Description
		// Allows to retrieve aggregated Frequency values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City
											// )
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_FREQUENCY_AGGREGATED
				.replaceFirst("\\{entityID\\}", String.valueOf(entityID)).replaceFirst("\\{dataType\\}", dataType) // REQUIRED -
																											// Available
																											// values :
																											// LineFrequency
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataFrequencyAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataFrequencyAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataWindAggregatedResponse telemetryDataWindAggregated(String auroraVisionApiKey, int entityID,
			String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.wind.aggregated
		// Payload -
		// Response TelemetryDataWindAggregatedResponse
		// Description
		// Allows to retrieve aggregated Wind values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City
											// )
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_WIND_AGGREGATED.replaceFirst("\\{entityID\\}", String.valueOf(entityID))
				.replaceFirst("\\{dataType\\}", dataType) // REQUIRED - Available values : WindDirection, WindSpeed
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataWindAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataWindAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataTemperatureAggregatedResponse telemetryDataTemperatureAggregated(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.temperature.aggregated
		// Payload -
		// Response TelemetryDataTemperatureAggregatedResponse
		// Description
		// Allows to retrieve aggregated Temperature values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City
											// )
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_TEMPERATURE_AGGREGATED
				.replaceFirst("\\{entityID\\}", String.valueOf(entityID)).replaceFirst("\\{dataType\\}", dataType) // REQUIRED -
																											// Available
																											// values :
																											// CellTemp,
																											// AmbientTemp
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataTemperatureAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataTemperatureAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataVoltageAggregatedResponse telemetryDataVoltageAggregated(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.voltage.aggregated
		// Payload -
		// Response TelemetryDataVoltageAggregatedResponse
		// Description
		// Allows to retrieve aggregated Voltage values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City
											// )
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_VOLTAGE_AGGREGATED
				.replaceFirst("\\{entityID\\}", String.valueOf(entityID)).replaceFirst("\\{dataType\\}", dataType) // REQUIRED -
																											// Available
																											// values :
																											// Voltage,
																											// DCVoltage
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataVoltageAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataVoltageAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataCurrentAggregatedResponse telemetryDataCurrentAggregated(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.current.aggregated
		// Payload -
		// Response TelemetryDataCurrentAggregatedResponse
		// Description
		// Allows to retrieve aggregated Current values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City
											// )
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_CURRENT_AGGREGATED
				.replaceFirst("\\{entityID\\}", String.valueOf(entityID)).replaceFirst("\\{dataType\\}", dataType) // REQUIRED -
																											// Available
																											// values :
																											// Current,
																											// DCCurrent
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataCurrentAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataCurrentAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataEnergyAggregatedResponse telemetryDataEnergyAggregated(String auroraVisionApiKey, int entityID, String dataType, String valueType, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.energy.aggregated
		// Payload -
		// Response TelemetryDataEnergyAggregatedResponse
		// Description
		// Allows to retrieve aggregated Energy or Insolation values of a Plant or a
		// Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(3);
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone ( Format: Civilian abbreviation or Country/City ) - Example: Europe/Rome


		// REQUIRED - Available values : GenerationEnergy, DCGenerationEnergy, Insolation, StorageInEnergy, StorageOutEnergy, GridEnergyExport, GridEnergyImport, SelfConsumedEnergy, ActiveEnergyEV, SessionEnergyEV
		
		String url = URL + ENDPOINT_TELEMETRY_DATA_ENERGY_AGGREGATED
				.replaceFirst("\\{entityID\\}", String.valueOf(entityID)).replaceFirst("\\{dataType\\}", dataType) 
				.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : cumulative, delta
		TelemetryDataEnergyAggregatedResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataEnergyAggregatedResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataPowerTimeseriesResponse telemetryDataPowerTimeseries(String auroraVisionApiKey, int entityID,
			String dataType, String valueType, String sampleSize, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.power.timeseries
		// Payload -
		// Response TelemetryDataPowerTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Power or Irradiance values of a Plant or a
		// Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL
				+ ENDPOINT_TELEMETRY_DATA_POWER_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - Available values : GenerationPower,
														// DCGenerationPower, Irradiance, GridPowerExport, StoredPower,
														// ActivePowerEV
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataPowerTimeseriesResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataPowerTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataVoltageTimeseriesResponse telemetryDataVoltageTimeseries(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String sampleSize, String startDate, String endDate,
			String timeZone) {
		// Package api.telemetryData.voltage.timeseries
		// Payload -
		// Response TelemetryDataVoltageTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Voltage values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL
				+ ENDPOINT_TELEMETRY_DATA_VOLTAGE_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - Available values : Voltage, DCVoltage
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataVoltageTimeseriesResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataVoltageTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataFrequencyTimeseriesResponse telemetryDataFrequencyTimeseries(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String sampleSize, String startDate, String endDate,
			String timeZone) {
		// Package api.telemetryData.frequency.timeseries
		// Payload -
		// Response TelemetryDataFrequencyTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Frequency values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL
				+ ENDPOINT_TELEMETRY_DATA_FREQUENCY_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - Available values : LineFrequency
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataFrequencyTimeseriesResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataFrequencyTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataWindTimeseriesResponse telemetryDataWindTimeseries(String auroraVisionApiKey, int entityID,
			String dataType, String valueType, String sampleSize, String startDate, String endDate, String timeZone) {
		// Package api.telemetryData.wind.timeseries
		// Payload -
		// Response TelemetryDataWindTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Wind values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL + ENDPOINT_TELEMETRY_DATA_WIND_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - Available values : WindDirection, WindSpeed
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataWindTimeseriesResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataWindTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataTemperatureTimeseriesResponse telemetryDataTemperatureTimeseries(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String sampleSize, String startDate, String endDate,
			String timeZone) {
		// Package api.telemetryData.temperature.timeseries
		// Payload -
		// Response TelemetryDataTemperatureTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Temperature values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL
				+ ENDPOINT_TELEMETRY_DATA_TEMPERATURE_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - Available values : CellTemp, AmbientTemp
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataTemperatureTimeseriesResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataTemperatureTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public TelemetryDataCurrentTimeseriesResponse telemetryDataCurrentTimeseries(String auroraVisionApiKey,
			int entityID, String dataType, String valueType, String sampleSize, String startDate, String endDate,
			String timeZone) {
		// Package api.telemetryData.current.timeseries
		// Payload -
		// Response TelemetryDataCurrentTimeseriesResponse
		// Description
		// Allows to retrieve a timeseried Current values of a Plant or a Device.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL
				+ ENDPOINT_TELEMETRY_DATA_CURRENT_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - Available values : Current, DCCurrent
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - Available values : maximum, minimum, average
		TelemetryDataCurrentTimeseriesResponse response = JsonCaller.get(url, headers, params,
				TelemetryDataCurrentTimeseriesResponse.class);

		return response == null ? null : response;
	}

	public KpiTimeseriesResponse kpiTimeseries(String auroraVisionApiKey, int entityID, String dataType, String valueType,
			String sampleSize, String startDate, String endDate, String timeZone) {
		// Package api.kpi.timeseries
		// Payload -
		// Response KpiTimeseriesResponse
		// Description
		// Allows to retrieve timeseried Power-Based KPIs or Energy-Based KPIs values of a Plant.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL + ENDPOINT_KPI_TIMESERIES.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - based on type
								// Power-Based KPIs: StateofCharge, StateofHealth, LoadsPowerConsumed, LoadsPowerConsumedPV
								// Energy-Based KPIs: PerformanceRatio, SelfConsumption, SelfSufficiency, LoadsEnergyConsumed, LoadsEnergyConsumedPV
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - based on type
								// Power-Based KPIs: maximum, minimum, average
								// Energy-Based KPIs: cumulative, delta
		KpiTimeseriesResponse response = JsonCaller.get(url, headers, params, KpiTimeseriesResponse.class);

		return response == null ? null : response;
	}
	
	public KpiAggregatedResponse kpiAggregated(String auroraVisionApiKey, int entityID, String dataType, String valueType,
			String sampleSize, String startDate, String endDate, String timeZone) {
		// Package api.kpi.aggregated
		// Payload -
		// Response KpiAggregatedResponse
		// Description
		// Allows to retrieve aggregated Power-Data KPIs or Energy-Data KPIs values of a Plant.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);

		Map<String, String> params = new HashMap<>(4);
		params.put("sampleSize", sampleSize); // REQUIRED - Available values : Min5, Min15, Hour, Day, Month, Year
		params.put("startDate", startDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("endDate", endDate); // REQUIRED - Pattern: yyyyMMdd
		params.put("timeZone", timeZone); // REQUIRED - Plant Time Zone (Format: Civilian abbreviation or Country/City)
											// Example: Europe/Rome

		String url = URL + ENDPOINT_KPI_AGGREGATED.replaceFirst("\\{entityID\\}", String.valueOf(entityID));
		url = url.replaceFirst("\\{dataType\\}", dataType); // REQUIRED - based on type
								// Power-Based KPIs: StateofCharge, StateofHealth, LoadsPowerConsumed, LoadsPowerConsumedPV
								// Energy-Based KPIs: PerformanceRatio, SelfConsumption, SelfSufficiency, LoadsEnergyConsumed, LoadsEnergyConsumedPV
		url = url.replaceFirst("\\{valueType\\}", valueType); // REQUIRED - based on type
								// Power-Based KPIs: maximum, minimum, average
								// Energy-Based KPIs: cumulative, delta
		KpiAggregatedResponse response = JsonCaller.get(url, headers, params, KpiAggregatedResponse.class);

		return response == null ? null : response;
	}
	
	public RegionResponse region(String auroraVisionApiKey, String countryCode) {
		// Package api.region
		// Payload -
		// Response RegionResponse
		// Description
		// Allows to retrieve available regions/states in a Country as stored in Aurora Vision.

		Map<String, String> headers = this.generateHeaders(auroraVisionApiKey);
		
		Map<String, String> params = new HashMap<>(1);
		params.put("countryCode", countryCode); // REQUIRED - ISO 3166 Alpha2 Country Code Format Standard

		String url = URL + ENDPOINT_REGION;
		RegionResponse response = JsonCaller.get(url, headers, params, RegionResponse.class);

		return response == null ? null : response;
	}
*/
}

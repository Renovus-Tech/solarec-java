package tech.renovus.solarec.inverters.brand.sma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.MeasurementsResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.Set;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantDevicesResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantsResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.ClassUtil;
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
 * URL: https://sandbox.smaapis.de/monitoring/index.html
 * URL: https://sandbox.smaapis.de/live/index.html
 * Authentication flows: https://developer.sma.de/api-access-control#c59102
 * API Swagger: https://sandbox.smaapis.de/monitoring/index.html
 * Postman template: https://cdn.sma.de/fileadmin/content/www.developer.sma.de/docs/SMA_CustomFlowSandbox_v6.postman_collection.json?v=1698749440
 */
public class SmaInverterService implements InverterService {

	//--- Private constants ---------------------
	public static final String LOG_PREFIX							= "[SMA] ";
	public static final String PROD_URL_AUTH						= "https://auth.smaapis.de";
	public static final String SANDBOX_URL_AUTH						= "https://sandbox-auth.smaapis.de";

	public static final String PROD_URL_DATA						= "https://smaapis.de";
	public static final String SANDBOX_URL_DATA						= "https://sandbox.smaapis.de";

	public static final String ENDPOINT_TOKEN						= "/oauth2/token";
	public static final String ENDPOINT_BC_AUTHORIZE				= "/oauth2/v2/bc-authorize";
	public static final String ENDPOINT_BC_AUTHROIZE_STATUS			= "/oauth2/v2/bc-authorize/{emailAddressResourceOwner}/status";
	public static final String ENDPOINT_BC_AUTHROIZE_TOKEN			= "/oauth2/v2/bc-authorize/{emailAddressResourceOwner}";

	public static final String ENDPOINT_MONITORING_PLANTS			= "/monitoring/v1/plants";
	public static final String ENDPOINT_MONITORING_PLAT_DEVICES		= "/monitoring/v1/plants/{plantId}/devices";

	public static final String ENDPOINT_MEASUREMENTS_DEVICE			= "/monitoring/v1/devices/{deviceId}/measurements/sets/{setType}/{period}";

	//--- Public constants ----------------------
	public static final String PARAM_SANBOX							= "sma.sandbox";
	public static final String PARAM_CLIENT_CLIENT_ID				= "sma.client.client_id";
	public static final String PARAM_CLIENT_CLIENT_SECRET			= "sma.client.client_secret";
	public static final String PARAM_CLIENT_RESOURCE_OWNER			= "sma.client.resource_owner";
	public static final String PARAM_LOCATION_PLANT_ID				= "sma.location.plant_id";
	public static final String PARAM_GENERATOR_DEVICE_ID			= "sma.generator.device_id";
	public static final String PARAM_GEN_LAST_DATE_RETRIEVE			= "sma.generator.last_retrieve";
	
	public static final String AUTHORIZE_ACCEPTED = "Accepted";
		
	public static final String SET_TYPE_ENERGY_AND_POWER_BATTERY		= "EnergyAndPowerBattery";
	public static final String SET_TYPE_ENERGY_AND_POWER_CONSUMPTION	= "EnergyAndPowerConsumption";
	public static final String SET_TYPE_ENERGY_AND_POWER_IN_OUT			= "EnergyAndPowerInOut";
	public static final String SET_TYPE_ENERGY_AND_POWER_PV				= "EnergyAndPowerPv";
	public static final String SET_TYPE_ENERGY_MIX						= "EnergyMix";
	public static final String SET_TYPE_POWER_AC						= "PowerAc";
	public static final String SET_TYPE_POWER_DC						= "PowerDc";
	public static final String SET_TYPE_SENSOR							= "Sensor";
	
	public static final String PERIOD_RECENT	= "Recent";
	public static final String PERIOD_DAY		= "Day";
	public static final String PERIOD_WEEK		= "Week";
	public static final String PERIOD_MONTH		= "Month";
	public static final String PERIOD_YEAR		= "Year";
	public static final String PERIOD_TOTAL		= "Total";
	
	public static final String DEVICE_TYPE_SOLAR_INVETER	= "Solar Inverters";
	public static final String DATA_TIME_FORMAT				= "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS";

	//--- Autowired properties ------------------
	@Autowired WeatherService weatherService;
	@Autowired JsonCaller jsonCaller;
	
	//--- Private properties --------------------
	private ClientVo cliVo;
	private AuthResponse auth;
	private BcAuthorizeResponse authorize;
	
	//--- Private methods -----------------------
	private String getUrlAuth(boolean sandboxMode) { return sandboxMode ? SANDBOX_URL_AUTH : PROD_URL_AUTH; }
	private String getUrlData(boolean sandboxMode) { return sandboxMode ? SANDBOX_URL_DATA : PROD_URL_DATA; }

	private List<GenDataVo> process(GeneratorVo generator, MeasurementsResponse data, Date fromDate) throws InveterServiceException {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getSet())) {
			SimpleDateFormat formatter = new SimpleDateFormat(DATA_TIME_FORMAT);
			try {
				for (Set aData : data.getSet()) {
					Date dataDate = formatter.parse(aData.getTime());
					Double dataValue = aData.getPvGeneration();
					
					GenDataVo genData = new GenDataVo();
					genData.setCliId(generator.getCliId());
					genData.setGenId(generator.getGenId());
					genData.setDataDate(dataDate);
					genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_DC_POWER);
					genData.setDataValue(dataValue);
					
					result.add(genData);
				}
			} catch (ParseException e) {
                throw new InveterServiceException(e);
			}
		}
		
		return result;
	}
	
	private boolean isAuthorized() {
		return this.authorize != null && AUTHORIZE_ACCEPTED.equals(this.authorize.getState());
	}
	
	//--- Implemented methods -------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	@Override public boolean continueWithStats() { return this.isAuthorized(); }
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
	@Override public InverterData retrieveData() throws InveterServiceException {
		InverterData result = new InverterData(new ArrayList<>(), new ArrayList<>());

		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("yyyy'-'MM'-'dd");
			
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					if (CollectionUtil.isEmpty(location.getStations())) {
						LoggerService.inverterLogger().error(LOG_PREFIX + "Can't fina station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
						continue;
					}
					
					StationVo station = location.getStations().iterator().next();
					
					for (GeneratorVo generator : location.getGenerators()) {
						String clientId			= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_CLIENT_CLIENT_ID);
						String clientSecret		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_CLIENT_CLIENT_SECRET);
						String resourceOwner	= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_CLIENT_RESOURCE_OWNER);
						boolean sandboxMode		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_SANBOX));
						
						this.auth		= this.retrieveToken(sandboxMode, clientId, clientSecret);
						this.authorize	= this.retrieveBcAuthorizeToken(sandboxMode, this.auth, resourceOwner);

						if (this.isAuthorized()) {
							String deviceId			= InvertersUtil.getParameter(generator, PARAM_GENERATOR_DEVICE_ID);
							String genLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE);
							Date dateFrom			= InvertersUtil.calculateDateFrom(genLastRetrieve);
	
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_DATE), "");
							
							MeasurementsResponse data = this.retrieveDeviceData(
									sandboxMode, 
									this.auth, 
									deviceId,
									SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV, 
									SmaInverterService.PERIOD_DAY, 
									formater.format(dateFrom)
								);
							
							List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
							
							if (CollectionUtil.notEmpty(generatorData)) {
								CollectionUtil.addAll(result.getGeneratorData(), generatorData);
								
								Collections.reverse(generatorData);
								GenDataVo lastData = generatorData.iterator().next();
								
								try {
									CollectionUtil.addAll(result.getStationData(), this.weatherService.retrieveWeatherData(location, station, dateFrom, lastData.getDataDate()));
								} catch (WeatherServiceException e) {
									throw new InveterServiceException(e);
								}
								
								InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(lastData.getDataDate().getTime()));
							}
							
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
						}
					}
				}
			}
		}
		
		return result;
	}
	
	//--- Public methods ------------------------
	public AuthResponse retrieveToken(boolean sandboxMode, String clientId, String clientSecret) {
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		
		return this.jsonCaller.post(
				this.getUrlAuth(sandboxMode) + ENDPOINT_TOKEN, 
				params, 
				AuthResponse.class
			);
	}
//	
//	public BcAuthorizeResponse retrieveBcAuthorize(AuthResponse auth, String clientEmail) {
//		return this.jsonCaller.bearerPost(
//				this.getUrlAuth(sandboxMode) + ENDPOINT_BC_AUTHORIZE, 
//				new BcAuthorizeRequest().withLoginHint(clientEmail), 
//				auth.getAccessToken(),
//				BcAuthorizeResponse.class
//			);
//	}
//	
//	public BcAuthorizeResponse retrieveBcAuthorizeStatus(AuthResponse auth, String clientEmail) {
//		return this.jsonCaller.bearerGet(
//				(this.getUrlAuth(sandboxMode) + ENDPOINT_BC_AUTHROIZE_STATUS).replaceFirst("\\{emailAddressResourceOwner\\}", clientEmail), 
//				null, 
//				auth.getAccessToken(),
//				BcAuthorizeResponse.class
//			);
//	}
	
	public BcAuthorizeResponse retrieveBcAuthorizeToken(boolean sandboxMode, AuthResponse auth, String clientEmail) {
		return this.jsonCaller.bearerGet(
				(this.getUrlData(sandboxMode) + ENDPOINT_BC_AUTHROIZE_TOKEN).replaceFirst("\\{emailAddressResourceOwner\\}", clientEmail), 
				null, 
				auth.getAccessToken(),
				BcAuthorizeResponse.class
			);
	}
	
	public PlantsResponse retrievePlants(boolean sandboxMode, AuthResponse auth) {
		return this.jsonCaller.bearerGet(
				this.getUrlData(sandboxMode) + ENDPOINT_MONITORING_PLANTS, 
				null, 
				auth.getAccessToken(),
				PlantsResponse.class
			);
	}

	public PlantDevicesResponse retrievePlantDevices(boolean sandboxMode, AuthResponse auth, String plantId) {
		return this.jsonCaller.bearerGet(
				(this.getUrlData(sandboxMode) + ENDPOINT_MONITORING_PLAT_DEVICES).replaceFirst("\\{plantId\\}", plantId), 
				null, 
				auth.getAccessToken(),
				PlantDevicesResponse.class
			);
	}
	
	
	public MeasurementsResponse retrieveDeviceData(boolean sandboxMode, AuthResponse auth, String deviceId, String setType, String period, String date) {
		Map<String, String> params = new HashMap<>();
		
		params.put("Date", date);
		
		return this.jsonCaller.bearerGet(
				(this.getUrlData(sandboxMode) + ENDPOINT_MEASUREMENTS_DEVICE)
					.replaceFirst("\\{deviceId\\}", deviceId) 
					.replaceFirst("\\{setType\\}", setType) 
					.replaceFirst("\\{period\\}", period), 
				params, 
				auth.getAccessToken(),
				MeasurementsResponse.class
			);
	}
	
	public boolean isInveter(Device device) {
		return device != null && ClassUtil.equals(device.getType(), DEVICE_TYPE_SOLAR_INVETER);
	}
	
	//--- Getters and Setters -------------------
	public void setJsonCaller(JsonCaller jsonCaller) {
		this.jsonCaller = jsonCaller;
	}
}

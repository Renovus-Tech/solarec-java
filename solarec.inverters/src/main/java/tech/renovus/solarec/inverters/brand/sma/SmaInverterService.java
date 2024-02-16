package tech.renovus.solarec.inverters.brand.sma;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.MeasurementsResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantDevicesResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantsResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL: https://sandbox.smaapis.de/monitoring/index.html
 * URL: https://sandbox.smaapis.de/live/index.html
 * Documentation: https://developer.sma.de/api-access-control#c59102
 * Postman template: https://cdn.sma.de/fileadmin/content/www.developer.sma.de/docs/SMA_CustomFlowSandbox_v6.postman_collection.json?v=1698749440
 */
public class SmaInverterService implements InverterService {

	//--- Private constants ---------------------
	private String PROD_URL_AUTH					= "https://auth.smaapis.de";
	private String SANDBOX_URL_AUTH					= "https://sandbox-auth.smaapis.de";
	
	private String PROD_URL_DATA					= "https://smaapis.de";
	private String SANDBOX_URL_DATA					= "https://sandbox.smaapis.de";
	
	private String URL_AUTH							= SANDBOX_URL_AUTH;
	private String URL_DATA							= SANDBOX_URL_DATA;
	
	private String ENDPOINT_TOKEN					= "/oauth2/token";
	private String ENDPOINT_BC_AUTHORIZE			= "/oauth2/v2/bc-authorize";
	private String ENDPOINT_BC_AUTHROIZE_STATUS		= "/oauth2/v2/bc-authorize/{emailAddressResourceOwner}/status";
	private String ENDPOINT_BC_AUTHROIZE_TOKEN		= "/oauth2/v2/bc-authorize/{emailAddressResourceOwner}";
	
	private String ENDPOINT_MONITORING_PLANTS		= "/monitoring/v1/plants";
	private String ENDPOINT_MONITORING_PLAT_DEVICES	= "/monitoring/v1/plants/{plantId}/devices";
	
	private String ENDPOINT_MEASUREMENTS_DEVICE		= "/monitoring/v1/devices/{deviceId}/measurements/sets/{setType}/{period}";
	
	//--- Public constants ----------------------
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
	
	//--- Private methods -----------------------
	
	//--- Implemented methods -------------------
	@Override public Collection<GenDataVo> retrieveData(ClientVo client) {
		String clientId			= "";
		String clientSecret		= "";
		String resourceOwner	= "";
		
		AuthResponse auth				= this.retrieveToken(clientId, clientSecret);
		BcAuthorizeResponse authorize	= this.retrieveBcAutorizeToken(auth, resourceOwner);
		
		if (AUTHORIZE_ACCEPTED.equals(authorize.getState())) {
//			BcAuthorizeResponse status		= this.retrieveBcAuthorizeStatus(auth, resourceOwner);
//			BcAuthroizeTokenResponse token	= this.retrieveBcAutorizeToken(auth, resourceOwner);
		}
		
		return null;
	}
	
	//--- Public methods ------------------------
	public AuthResponse retrieveToken(String clientId, String clientSecret) {
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		
		return JsonCaller.post(
				URL_AUTH + ENDPOINT_TOKEN, 
				params, 
				AuthResponse.class
			);
	}
//	
//	public BcAuthorizeResponse retrieveBcAuthorize(AuthResponse auth, String clientEmail) {
//		return JsonCaller.bearerPost(
//				URL_DATA + ENDPOINT_BC_AUTHORIZE, 
//				new BcAuthorizeRequest().withLoginHint(clientEmail), 
//				auth.getAccessToken(),
//				BcAuthorizeResponse.class
//			);
//	}
//	
//	public BcAuthorizeResponse retrieveBcAuthorizeStatus(AuthResponse auth, String clientEmail) {
//		return JsonCaller.bearerGet(
//				(URL_DATA + ENDPOINT_BC_AUTHROIZE_STATUS).replaceFirst("\\{emailAddressResourceOwner\\}", clientEmail), 
//				null, 
//				auth.getAccessToken(),
//				BcAuthorizeResponse.class
//			);
//	}
	
	public BcAuthorizeResponse retrieveBcAutorizeToken(AuthResponse auth, String clientEmail) {
		return JsonCaller.bearerGet(
				(URL_DATA + ENDPOINT_BC_AUTHROIZE_TOKEN).replaceFirst("\\{emailAddressResourceOwner\\}", clientEmail), 
				null, 
				auth.getAccessToken(),
				BcAuthorizeResponse.class
			);
	}
	
	public PlantsResponse retrievePlants(AuthResponse auth) {
		return JsonCaller.bearerGet(
				URL_DATA + ENDPOINT_MONITORING_PLANTS, 
				null, 
				auth.getAccessToken(),
				PlantsResponse.class
			);
	}

	public PlantDevicesResponse retrievePlantDevices(AuthResponse auth, String plantId) {
		return JsonCaller.bearerGet(
				(URL_DATA + ENDPOINT_MONITORING_PLAT_DEVICES).replaceFirst("\\{plantId\\}", plantId), 
				null, 
				auth.getAccessToken(),
				PlantDevicesResponse.class
			);
	}
	
	
	public MeasurementsResponse retrieveDeviceData(AuthResponse auth, String deviceId, String setType, String period, String date) {
		Map<String, String> params = new HashMap<>();
		
		params.put("Date", date);
		
		return JsonCaller.bearerGet(
				(URL_DATA + ENDPOINT_MEASUREMENTS_DEVICE)
					.replaceFirst("\\{deviceId\\}", deviceId) 
					.replaceFirst("\\{setType\\}", setType) 
					.replaceFirst("\\{period\\}", period), 
				params, 
				auth.getAccessToken(),
				MeasurementsResponse.class
			);
	}
	
}

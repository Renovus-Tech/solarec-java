package tech.renovus.solarec.inverters.brand.fimer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fimer.api.asset.AssetInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.organization.OrganizationResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plantsGroups.info.InfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.plantsGroups.plants.Plants;
import tech.renovus.solarec.inverters.brand.fimer.api.plantsGroups.plants.PlantsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.info.PortfolioInfoResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plantGroup.PortfolioPlantGroup;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plantGroup.PortfolioPlantGroupResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plants.PortfolioPlants;
import tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plants.PortfolioPlantsResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL:
 * https://solis-service.solisinverters.com/en/support/solutions/articles/44002212561-api-access-soliscloud
 * URL: https://documentation.auroravision.net/index.html%3Fp=1267.html <--
 * swagger
 */
public class FimerInverterService implements InverterService {

	// --- Private constants ---------------------
	private static final String URL 							= "https://api.auroravision.net/api/rest";
	private static final String ENDPOINT_STATUS 				= "/status";
	private static final String ENDPOINT_AUTHENTICATE 			= "/authenticate";
	private static final String ENDPOINT_ASSET_INFO 			= "/v1/asset/{entityID}/info";
	private static final String ENDPOINT_ORGANIZATION 			= "/v1/portfolioGroup";
	
	private static final String ENDPOINT_PORTFOLIO_PLANTS 		= "/v1/portfolio/{entityID}/plants";
	private static final String ENDPOINT_PORTFOLIO_PLANT_GROUP 	= "/v1/portfolio/{entityID}/plantGroups";
	private static final String ENDPOINT_PORTFOLIO_INFO 		= "/v1/portfolio/{entityID}/info";
	
	private static final String ENDPOINT_PLANT_GROUPS_PLANTS 	= "/v1/plantGroup/{entityID}/plants";
	private static final String ENDPOINT_PLANT_GROUPS_INFO 		= "/v1/plantGroup/{entityID}/info";

	// --- Private properties --------------------
	private Logger logger = LoggerService.schedulesLogger();

	// --- Private methods -----------------------
	private String authenticate(InverterCofigurationVo configuration) {
		String credentials = configuration.getUser() + ":" + configuration.getPassword();
		String base64Credentials = new String(Base64.getEncoder().encode(credentials.getBytes(StandardCharsets.UTF_8)));

		//TODO si agregas dos, no deberías iniciar en 2 para que no redimencione?
		Map<String, String> headers = new HashMap<>(1);
		headers.put(HttpHeaders.AUTHORIZATION, "Basic " + base64Credentials);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		AuthenticateResponse response = JsonCaller.get(URL + ENDPOINT_AUTHENTICATE, headers, null,
				AuthenticateResponse.class);

		return response == null ? null : response.getResult();
	}

	// --- Implemented methods -------------------
	@Override
	public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		long t = System.currentTimeMillis();
		this.logger.info("[{t}] Start retrieve for: {client} ({cliId})", t, client.getCliName(), client.getCliId());
		String authenticationKey = this.authenticate(configuration);

		this.logger.info("[{t}] Authentication ok: ", t, StringUtil.notEmpty(authenticationKey));

		// do something here

		this.logger.info("[{t}] End retrieve for: {client} ({cliId})", t, client.getCliName(), client.getCliId());

		return null;
	}

	// --- Public methods ------------------------
	public StatusResponse status() {
		return JsonCaller.get(URL + ENDPOINT_STATUS, StatusResponse.class);
	}

	public boolean validateConfiguration(InverterCofigurationVo configuration) {
		return StringUtil.notEmpty(this.authenticate(configuration));
	}

	public AssetInfoResponse assetInfo(InverterCofigurationVo configuration) {
		// Package api.asset
		// Payload -
		// Response AssetInfoResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		String url = URL + String.valueOf(ENDPOINT_ASSET_INFO).replaceFirst("{entityID}", ""); // como obtener el
																								// entityID
		AssetInfoResponse response = JsonCaller.get(url, headers, null, AssetInfoResponse.class);

		return response == null ? null : response;
	}

	public OrganizationResponse organization(InverterCofigurationVo configuration) {
		// Package api.organization
		// Payload -
		// Response OrganizationResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		OrganizationResponse response = JsonCaller.get(URL + ENDPOINT_ORGANIZATION, headers, null,
				OrganizationResponse.class);

		return response == null ? null : response;
	}

	public PortfolioPlantsResponse portfolioPlants(InverterCofigurationVo configuration) {
		// Package api.portfolio.plants
		// Payload PortfolioPlants
		// Response PortfolioPlantsResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		String url = URL + String.valueOf(ENDPOINT_PORTFOLIO_PLANTS).replaceFirst("{entityID}", ""); // como obtener el entityID
		PortfolioPlants payload = new PortfolioPlants();// Es posible indicar la pagina para los resultados
		PortfolioPlantsResponse response = JsonCaller.get(url, headers, payload, PortfolioPlantsResponse.class);

		return response == null ? null : response;
	}

	public PortfolioPlantGroupResponse portfolioPlantGroup(InverterCofigurationVo configuration) {
		// Package api.portfolio.plantGroup
		// Payload PortfolioPlantGroup
		// Response PortfolioPlantGroupResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		String url = URL + String.valueOf(ENDPOINT_PORTFOLIO_PLANT_GROUP).replaceFirst("{entityID}", ""); // como obtener el entityID
		PortfolioPlantGroup payload = new PortfolioPlantGroup();// Es posible indicar la pagina para los resultados
		PortfolioPlantGroupResponse response = JsonCaller.get(url, headers, payload, PortfolioPlantGroupResponse.class);

		return response == null ? null : response;
	}

	public PortfolioInfoResponse portfolioInfo(InverterCofigurationVo configuration) {
		// Package api.portfolio
		// Payload -
		// Response PortfolioInfoResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		String url = URL + String.valueOf(ENDPOINT_PORTFOLIO_INFO).replaceFirst("{entityID}", ""); // como obtener el entityID
		PortfolioInfoResponse response = JsonCaller.get(url, headers, null, PortfolioInfoResponse.class);

		return response == null ? null : response;
	}
	
	public PlantsResponse plantsGroups(InverterCofigurationVo configuration) {
		// Package api.plantsGroups.plants
		// Payload  Plants
		// Response PlantsResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		String url = URL + String.valueOf(ENDPOINT_PLANT_GROUPS_PLANTS).replaceFirst("{entityID}", ""); // como obtener el entityID
		Plants payload = new Plants().withPlantStatus(); //Available values : HIGH, MEDIUM, LOW, NORM, INFO //Lo da el inverter?
		PlantsResponse response = JsonCaller.get(url, headers, payload, PlantsResponse.class);

		return response == null ? null : response;
	}
	
	public InfoResponse plantsGroupsInfo(InverterCofigurationVo configuration) {
		// Package api.portfolio
		// Payload -
		// Response PortfolioInfoResponse

		Map<String, String> headers = new HashMap<>(1);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());

		String url = URL + String.valueOf(ENDPOINT_PLANT_GROUPS_INFO).replaceFirst("{entityID}", ""); // como obtener el entityID
		InfoResponse response = JsonCaller.get(url, headers, null, InfoResponse.class);

		return response == null ? null : response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

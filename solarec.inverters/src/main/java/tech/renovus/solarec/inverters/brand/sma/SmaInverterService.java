package tech.renovus.solarec.inverters.brand.sma;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeRequest;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthroizeTokenResponse;
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

	//--- Production URLs -----------------------
	private String PROD_URL						= "https://auth.smaapis.de";
	private String SANDBOX_URL					= "https://auth.smaapis.de";
	
	private String URL							= SANDBOX_URL;
	
	private String ENDPOINT_TOKEN				= "/oauth2/token";
	private String ENDPOINT_BC_AUTHORIZE		= "/oauth2/v2/bc-authorize";
	private String ENDPOINT_BC_AUTHROIZE_STATUS	= "/oauth2/v2/bc-authorize/{emailAddressResourceOwner}/status";
	private String ENDPOINT_BC_AUTHROIZE_TOKEN	= "/oauth2/v2/bc-authorize/{emailAddressResourceOwner}";
	
	//--- Private methods -----------------------
	private AuthResponse retrieveToken(String clientId, String clientSecret) {
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		
		return JsonCaller.get(
				URL + ENDPOINT_TOKEN, 
				params, 
				AuthResponse.class
			);
	}
	
	private BcAuthorizeResponse retrieveBcAuthorize(AuthResponse auth, String clientEmail) {
		return JsonCaller.bearerPost(
				URL + ENDPOINT_BC_AUTHORIZE, 
				new BcAuthorizeRequest().withLoginHint(clientEmail), 
				auth.getBearer(),
				BcAuthorizeResponse.class
			);
	}
	
	private BcAuthorizeResponse retrieveBcAuthorizeStatus(AuthResponse auth, String clientEmail) {
		return JsonCaller.bearerGet(
				(URL + ENDPOINT_BC_AUTHROIZE_STATUS).replaceFirst("\\{emailAddressResourceOwner\\}", clientEmail), 
				null, 
				auth.getBearer(),
				BcAuthorizeResponse.class
			);
	}
	
	private BcAuthroizeTokenResponse retrieveBcAutorizeToken(AuthResponse auth, String clientEmail) {
		return JsonCaller.bearerGet(
				(URL + ENDPOINT_BC_AUTHROIZE_TOKEN).replaceFirst("\\{emailAddressResourceOwner\\}", clientEmail), 
				null, 
				auth.getBearer(),
				BcAuthroizeTokenResponse.class
			);
	}
	
	//--- Implemented methods -------------------
	@Override public Collection<GenDataVo> retrieveData(ClientVo client) {
		String clientId		= "";
		String clientSecret	= "";
		String clientEmail	= "";
		
		AuthResponse auth				= this.retrieveToken(clientId, clientSecret);
		BcAuthorizeResponse authorize	= this.retrieveBcAuthorize(auth, clientEmail);
		BcAuthorizeResponse status		= this.retrieveBcAuthorizeStatus(auth, clientEmail);
		BcAuthroizeTokenResponse token	= this.retrieveBcAutorizeToken(auth, clientEmail);
		
		return null;
	}

}

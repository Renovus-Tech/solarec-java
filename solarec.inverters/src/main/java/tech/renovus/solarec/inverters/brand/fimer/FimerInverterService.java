package tech.renovus.solarec.inverters.brand.fimer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL: https://solis-service.solisinverters.com/en/support/solutions/articles/44002212561-api-access-soliscloud
 * URL: https://documentation.auroravision.net/index.html%3Fp=1267.html <-- swagger
 */
public class FimerInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL						= "https://api.auroravision.net/api/rest";
	private static final String ENDPOINT_STATUS			= "/status";
	private static final String ENDPOINT_AUTHENTICATE	= "/authenticate";
	
	//--- Private properties --------------------
	private Logger logger = LoggerService.schedulesLogger();
		
	//--- Private methods -----------------------
	private String authenticate(InverterCofigurationVo configuration) {
		String credentials = configuration.getUser() + ":" + configuration.getPassword();
		String base64Credentials = new String(Base64.getEncoder().encode(credentials.getBytes(StandardCharsets.UTF_8)));
		
		Map<String, String> headers = new HashMap<>(1);
		headers.put(HttpHeaders.AUTHORIZATION, "Basic " + base64Credentials);
		headers.put("X-AuroraVision-ApiKey", configuration.getKey());
		
		AuthenticateResponse response = JsonCaller.get(URL + ENDPOINT_AUTHENTICATE, headers, null, AuthenticateResponse.class);
		
		return response == null ? null : response.getResult();
	}
	
	//--- Implemented methods -------------------
	@Override
	public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		long t = System.currentTimeMillis();
		this.logger.info("[{t}] Start retrieve for: {client} ({cliId})", t, client.getCliName(), client.getCliId());
		String authenticationKey = this.authenticate(configuration);
		
		this.logger.info("[{t}] Authentication ok: ", t, StringUtil.notEmpty(authenticationKey));
		
		//do something here
		
		this.logger.info("[{t}] End retrieve for: {client} ({cliId})", t, client.getCliName(), client.getCliId());
		
		return null;
	}

	//--- Public methods ------------------------
	public StatusResponse status() {
		return JsonCaller.get(URL + ENDPOINT_STATUS, StatusResponse.class);
	}
	
	public boolean validateConfiguration(InverterCofigurationVo configuration) {
		return StringUtil.notEmpty(this.authenticate(configuration));
	}
	
}

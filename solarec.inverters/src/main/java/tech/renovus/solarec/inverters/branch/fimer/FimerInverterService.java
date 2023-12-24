package tech.renovus.solarec.inverters.branch.fimer;

import java.util.Collection;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.branch.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL: https://solis-service.solisinverters.com/en/support/solutions/articles/44002212561-api-access-soliscloud
 * URL: https://documentation.auroravision.net/index.html%3Fp=1267.html <-- swagger
 */
public class FimerInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL				= "https://api.auroravision.net/api/rest";
	private static final String ENDPOINT_STATUS	= "/status";
	
	//--- Private methods -----------------------
	
	//--- Implemented methods -------------------
	@Override
	public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		
		return null;
	}

	//--- Public methods ------------------------
	public StatusResponse status() {
		return JsonCaller.get(URL + ENDPOINT_STATUS, StatusResponse.class);
	}
	
}

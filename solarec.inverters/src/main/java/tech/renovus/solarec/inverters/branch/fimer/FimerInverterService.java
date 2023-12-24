package tech.renovus.solarec.inverters.branch.fimer;

import java.util.Collection;

import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL: https://solis-service.solisinverters.com/en/support/solutions/articles/44002212561-api-access-soliscloud
 * URL: https://documentation.auroravision.net/index.html%3Fp=1267.html <-- swagger
 */
public class FimerInverterService implements InverterService {

	@Override
	public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		// TODO Auto-generated method stub
		return null;
	}

}

package tech.renovus.solarec.inverters.branch.sma;

import java.util.Collection;

import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL: https://sandbox.smaapis.de/monitoring/index.html
 */
public class SmaInverterService implements InverterService {

	@Override
	public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		// TODO Auto-generated method stub
		return null;
	}

}

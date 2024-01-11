package tech.renovus.solarec.inverters.brand.fronius;

import java.util.Collection;

import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

/**
 * URL: https://www.fronius.com/en/solarweb-query-api
 * URL API BEta: https://swqapi-beta.solarweb.com/index.html
 * 
 * Authentication format (2 header parameters):
 * 
 * GET https://api.solarweb.com/swqapi/pvsystems HTTP/1.1
 * AccessKeyId: FKIAFEF58CFEFA94486F9C804CF6077A01AB
 * AccessKeyValue: 47c076bc-23e5-4949-37a6-4bcfcf8d21d6
 */
public class FroniusInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_BETA	= "https://swqapi-beta.solarweb.com";
	
	
	
	//--- Implemented methods -------------------
	@Override
	public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		// TODO Auto-generated method stub
		return null;
	}

}

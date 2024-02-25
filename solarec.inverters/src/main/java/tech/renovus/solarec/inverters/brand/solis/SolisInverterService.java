package tech.renovus.solarec.inverters.brand.solis;

import java.util.Collection;

import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

public class SolisInverterService implements InverterService {

	@Override public void prepareFor(ClientVo client) {}
	@Override public boolean canRetrieve() { return false; }
	@Override public boolean continueWithStats() { return false; }
	@Override public String getReasonWhyCantRetrieve() {return null;}
	
	@Override
	public Collection<GenDataVo> retrieveData() {
		// TODO Auto-generated method stub
		return null;
	}

}

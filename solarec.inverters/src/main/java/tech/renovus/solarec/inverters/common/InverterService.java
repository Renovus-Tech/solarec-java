package tech.renovus.solarec.inverters.common;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

public interface InverterService {

	void prepareFor(ClientVo client);
	boolean canRetrieve();
	boolean continueWithStats();
	String getReasonWhyCantRetrieve();
	Collection<GenDataVo> retrieveData();
}

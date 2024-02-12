package tech.renovus.solarec.inverters.common;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

public interface InverterService {

	Collection<GenDataVo> retrieveData(ClientVo client);
}

package tech.renovus.solarec.business;

import java.util.Collection;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

public interface DataDefinitionService {

	Collection<DataDefinitionVo> findAll(UserData userData);
	Collection<CliDataDefTriggerVo> findAllTriggers(UserData userData);
}

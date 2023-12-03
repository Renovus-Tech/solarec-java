package tech.renovus.solarec.business.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.DataDefinitionService;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefTriggerDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;

@Service
public class DataDefinitionServiceImpl implements DataDefinitionService {

	//--- Resources -----------------------------
	@Resource DataDefinitionDao dao;
	@Resource CliDataDefTriggerDao triggerDao;
	
	//--- Overridden methods --------------------
	@Override public Collection<DataDefinitionVo> findAll(UserData userData) {
		return this.dao.findAll();
	}

	@Override
	public Collection<CliDataDefTriggerVo> findAllTriggers(UserData userData) {
		return this.triggerDao.findAllFor(userData.getCliId());
	}

}

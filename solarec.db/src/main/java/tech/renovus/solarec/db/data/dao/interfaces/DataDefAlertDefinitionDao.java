package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataDefAlertDefinitionVo;

public interface DataDefAlertDefinitionDao {

	Collection<DataDefAlertDefinitionVo> findAll();
	DataDefAlertDefinitionVo findVo(Integer dataDefId, Integer alertDefId);
	void insert(DataDefAlertDefinitionVo vo);
	void update(DataDefAlertDefinitionVo vo);
	void delete(DataDefAlertDefinitionVo vo);
	void synchronize(DataDefAlertDefinitionVo vo);
	void synchronize(Collection<DataDefAlertDefinitionVo> vos);
	Collection<DataDefAlertDefinitionVo> getAllForDataDefinition(Integer dataDefId);

}


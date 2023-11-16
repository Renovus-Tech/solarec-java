package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.AlertDefinitionVo;

public interface AlertDefinitionDao {

	Collection<AlertDefinitionVo> findAll();
	AlertDefinitionVo findVo(Integer alertDefId);
	void insert(AlertDefinitionVo vo);
	void update(AlertDefinitionVo vo);
	void delete(AlertDefinitionVo vo);
	void synchronize(AlertDefinitionVo vo);
	void synchronize(Collection<AlertDefinitionVo> vos);

}


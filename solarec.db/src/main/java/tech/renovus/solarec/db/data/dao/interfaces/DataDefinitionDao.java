package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataDefinitionVo;

public interface DataDefinitionDao {

	Collection<DataDefinitionVo> findAll();
	DataDefinitionVo findVo(Integer dataDefId);
	void insert(DataDefinitionVo vo);
	void update(DataDefinitionVo vo);
	void delete(DataDefinitionVo vo);
	void synchronize(DataDefinitionVo vo);
	void synchronize(Collection<DataDefinitionVo> vos);

}


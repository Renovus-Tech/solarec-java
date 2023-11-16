package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;

public interface DataDefStatDefinitionDao {

	Collection<DataDefStatDefinitionVo> findAll();
	DataDefStatDefinitionVo findVo(Integer dataDefId, Integer statDefId);
	void insert(DataDefStatDefinitionVo vo);
	void update(DataDefStatDefinitionVo vo);
	void delete(DataDefStatDefinitionVo vo);
	void synchronize(DataDefStatDefinitionVo vo);
	void synchronize(Collection<DataDefStatDefinitionVo> vos);
	
	Collection<DataDefStatDefinitionVo> getAllForDataDefinition(Integer dataDefId);

}


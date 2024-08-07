package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.StatDefinitionVo;

public interface StatDefinitionDao {

	Collection<StatDefinitionVo> findAll();
	StatDefinitionVo findVo(Integer statDefId);
	void insert(StatDefinitionVo vo);
	void update(StatDefinitionVo vo);
	void delete(StatDefinitionVo vo);
	void synchronize(StatDefinitionVo vo);
	void synchronize(Collection<StatDefinitionVo> vos);

	StatDefinitionVo findVo(String statDefName);
}


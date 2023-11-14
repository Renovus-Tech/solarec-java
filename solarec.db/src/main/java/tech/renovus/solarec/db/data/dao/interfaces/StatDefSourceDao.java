package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.StatDefSourceVo;

public interface StatDefSourceDao {

	Collection<StatDefSourceVo> findAll();
	StatDefSourceVo findVo(Integer statDefId, Integer dataTypeId);
	void insert(StatDefSourceVo vo);
	void update(StatDefSourceVo vo);
	void delete(StatDefSourceVo vo);
	void synchronize(StatDefSourceVo vo);
	void synchronize(Collection<StatDefSourceVo> vos);

}


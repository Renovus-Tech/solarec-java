package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.StatDefResultVo;

public interface StatDefResultDao {

	Collection<StatDefResultVo> findAll();
	StatDefResultVo findVo(Integer statDefId, Integer statTypeId);
	void insert(StatDefResultVo vo);
	void update(StatDefResultVo vo);
	void delete(StatDefResultVo vo);
	void synchronize(StatDefResultVo vo);
	void synchronize(Collection<StatDefResultVo> vos);

}


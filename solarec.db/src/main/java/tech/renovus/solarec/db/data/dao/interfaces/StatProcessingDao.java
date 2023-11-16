package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.StatProcessingVo;

public interface StatProcessingDao {

	Collection<StatProcessingVo> findAll();
	StatProcessingVo findVo(Integer statProId);
	void insert(StatProcessingVo vo);
	void update(StatProcessingVo vo);
	void delete(StatProcessingVo vo);
	void synchronize(StatProcessingVo vo);
	void synchronize(Collection<StatProcessingVo> vos);

}


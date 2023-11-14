package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.StatTypeVo;

public interface StatTypeDao {

	Collection<StatTypeVo> findAll();
	StatTypeVo findVo(Integer statTypeId);
	void insert(StatTypeVo vo);
	void update(StatTypeVo vo);
	void delete(StatTypeVo vo);
	void synchronize(StatTypeVo vo);
	void synchronize(Collection<StatTypeVo> vos);

}


package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.LocStatisticVo;

public interface LocStatisticDao {

	Collection<LocStatisticVo> findAll();
	LocStatisticVo findVo(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId);
	void insert(LocStatisticVo vo);
	void update(LocStatisticVo vo);
	void delete(LocStatisticVo vo);
	void synchronize(LocStatisticVo vo);
	void synchronize(Collection<LocStatisticVo> vos);

}


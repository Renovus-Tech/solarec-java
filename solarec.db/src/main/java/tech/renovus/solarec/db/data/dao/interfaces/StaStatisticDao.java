package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.StaStatisticVo;

public interface StaStatisticDao {

	Collection<StaStatisticVo> findAll();
	StaStatisticVo findVo(Integer cliId, Integer staId, java.util.Date statDate, Integer statTypeId);
	void insert(StaStatisticVo vo);
	void update(StaStatisticVo vo);
	void delete(StaStatisticVo vo);
	void synchronize(StaStatisticVo vo);
	void synchronize(Collection<StaStatisticVo> vos);
	
	void deleteAllForStation(Integer cliId, Integer staId);

}


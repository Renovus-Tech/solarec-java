package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.StationVo;

public interface StationDao {

	Collection<StationVo> findAll();
	StationVo findVo(Integer cliId, Integer staId);
	void insert(StationVo vo);
	void update(StationVo vo);
	void delete(StationVo vo);
	void synchronize(StationVo vo);
	void synchronize(Collection<StationVo> vos);
	Collection<StationVo> findAll(Integer cliId);
	Collection<StationVo> findAll(Integer cliId, Integer locId);
	void updateDataDateMaxMin();
	
	Date getMaxDataDate(Integer cliId, Date beforeDate);

}


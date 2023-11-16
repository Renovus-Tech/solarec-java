package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.LocDataWeatherVo;

public interface LocDataWeatherDao {

	Collection<LocDataWeatherVo> findAll();
	LocDataWeatherVo findVo(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId);
	void insert(LocDataWeatherVo vo);
	void update(LocDataWeatherVo vo);
	void delete(LocDataWeatherVo vo);
	void synchronize(LocDataWeatherVo vo);
	void synchronize(Collection<LocDataWeatherVo> vos);
	Collection<LocDataWeatherVo> getForPeriod(Integer cliId, Integer locId, Date from, Date to);
	Collection<LocDataWeatherVo> getForPeriod(Integer cliId, Integer locId, Integer dataTypeId, Date from, Date to);
}


package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocWeatherDataVo;

public interface LocWeatherDataDao {

	Collection<LocWeatherDataVo> findAll();
	LocWeatherDataVo findVo(Integer cliId, Integer locId, Integer locWeaDataId);
	void insert(LocWeatherDataVo vo);
	void update(LocWeatherDataVo vo);
	void delete(LocWeatherDataVo vo);
	void synchronize(LocWeatherDataVo vo);
	void synchronize(Collection<LocWeatherDataVo> vos);
	
	LocWeatherDataVo getLastFor(Integer cliId, Integer locId);
}


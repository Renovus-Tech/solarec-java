package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.GenPowerVo;

public interface GenPowerDao {

	Collection<GenPowerVo> findAll();
	GenPowerVo findVo(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity);
	void insert(GenPowerVo vo);
	void update(GenPowerVo vo);
	void delete(GenPowerVo vo);
	void synchronize(GenPowerVo vo);
	void synchronize(Collection<GenPowerVo> vos);
	
	Collection<GenPowerVo> getAllFor(Integer cliId, Integer genId);

}


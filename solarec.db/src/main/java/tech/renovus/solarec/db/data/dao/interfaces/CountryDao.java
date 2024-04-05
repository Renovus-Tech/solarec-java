package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CountryVo;

public interface CountryDao {

	Collection<CountryVo> findAll();
	CountryVo findVo(Integer ctrId);
	void insert(CountryVo vo);
	void update(CountryVo vo);
	void delete(CountryVo vo);
	void synchronize(CountryVo vo);
	void synchronize(Collection<CountryVo> vos);
	
	Collection<CountryVo> getCountriesInUse();
}

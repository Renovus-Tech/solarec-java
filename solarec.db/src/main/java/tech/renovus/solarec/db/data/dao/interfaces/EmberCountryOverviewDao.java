package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.EmberCountryOverviewVo;

public interface EmberCountryOverviewDao {

	Collection<EmberCountryOverviewVo> findAll();
	EmberCountryOverviewVo findVo(String countryOrRegion, Integer year);
	void insert(EmberCountryOverviewVo vo);
	void update(EmberCountryOverviewVo vo);
	void delete(EmberCountryOverviewVo vo);
	void synchronize(EmberCountryOverviewVo vo);
	void synchronize(Collection<EmberCountryOverviewVo> vos);
	EmberCountryOverviewVo findFirstFrom(String string, Integer valueOf);
	EmberCountryOverviewVo findLastFrom(String string, Integer valueOf);

}


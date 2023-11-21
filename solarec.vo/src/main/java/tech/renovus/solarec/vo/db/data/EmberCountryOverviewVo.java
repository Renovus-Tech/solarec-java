package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbEmberCountryOverviewVo;

public class EmberCountryOverviewVo extends DbEmberCountryOverviewVo {

	//--- Constructors --------------------------
	public EmberCountryOverviewVo() {
	}

	public EmberCountryOverviewVo(Integer year, String countryOrRegion) {
		this.setPk(year, countryOrRegion);
	}

}
package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbEmberCountryOverviewVo;

public class EmberCountryOverviewVo extends DbEmberCountryOverviewVo {

	//--- Constructors --------------------------
	public EmberCountryOverviewVo() {
	}

	public EmberCountryOverviewVo(String countryOrRegion, Integer year) {
		this.setPk(countryOrRegion, year);
	}

}
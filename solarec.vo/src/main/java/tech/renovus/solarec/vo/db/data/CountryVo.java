package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCountryVo;

public class CountryVo extends DbCountryVo {

	//--- Constructors --------------------------
	public CountryVo() {
	}

	public CountryVo(Integer ctrId) {
		this.setPk(ctrId);
	}

}
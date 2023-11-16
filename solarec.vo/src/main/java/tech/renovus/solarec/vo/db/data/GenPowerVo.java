package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbGenPowerVo;

public class GenPowerVo extends DbGenPowerVo {

	//--- Constructors --------------------------
	public GenPowerVo() {
	}

	public GenPowerVo(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) {
		this.setPk(cliId, genId, pwrWindSpeed, pwrAirDensity);
	}

}
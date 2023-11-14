package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbGenPowerVo;

public class GenPowerVo extends DbGenPowerVo {

	//--- Constructors --------------------------
	public GenPowerVo() {
	}

	public GenPowerVo(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) {
		this.setPk(cliId, genId, pwrWindSpeed, pwrAirDensity);
	}

}
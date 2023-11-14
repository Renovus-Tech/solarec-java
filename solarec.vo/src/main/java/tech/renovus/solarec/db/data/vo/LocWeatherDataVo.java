package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbLocWeatherDataVo;

public class LocWeatherDataVo extends DbLocWeatherDataVo {

	//--- Constructors --------------------------
	public LocWeatherDataVo() {
	}

	public LocWeatherDataVo(Integer cliId, Integer locId, Integer locWeaDataId) {
		this.setPk(cliId, locId, locWeaDataId);
	}

}
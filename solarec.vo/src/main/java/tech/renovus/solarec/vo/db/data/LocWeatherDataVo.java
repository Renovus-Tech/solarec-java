package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocWeatherDataVo;

public class LocWeatherDataVo extends DbLocWeatherDataVo {

	//--- Constructors --------------------------
	public LocWeatherDataVo() {
	}

	public LocWeatherDataVo(Integer cliId, Integer locId, Integer locWeaDataId) {
		this.setPk(cliId, locId, locWeaDataId);
	}

}
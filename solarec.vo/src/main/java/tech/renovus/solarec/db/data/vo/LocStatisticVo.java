package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbLocStatisticVo;

public class LocStatisticVo extends DbLocStatisticVo {

	//--- Constructors --------------------------
	public LocStatisticVo() {
	}

	public LocStatisticVo(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId) {
		this.setPk(cliId, locId, statDate, statTypeId);
	}

}
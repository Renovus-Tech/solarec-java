package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocStatisticVo;

public class LocStatisticVo extends DbLocStatisticVo {

	//--- Constructors --------------------------
	public LocStatisticVo() {
	}

	public LocStatisticVo(Integer cliId, Integer locId, java.util.Date statDate, Integer statTypeId) {
		this.setPk(cliId, locId, statDate, statTypeId);
	}

}
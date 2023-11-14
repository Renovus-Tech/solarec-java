package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStaStatisticVo;

public class StaStatisticVo extends DbStaStatisticVo {

	//--- Constructors --------------------------
	public StaStatisticVo() {
	}

	public StaStatisticVo(Integer cliId, Integer staId, java.util.Date statDate, Integer statTypeId) {
		this.setPk(cliId, staId, statDate, statTypeId);
	}

}
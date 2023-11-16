package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbStaStatisticVo;

public class StaStatisticVo extends DbStaStatisticVo {

	//--- Constructors --------------------------
	public StaStatisticVo() {
	}

	public StaStatisticVo(Integer cliId, Integer staId, java.util.Date statDate, Integer statTypeId) {
		this.setPk(cliId, staId, statDate, statTypeId);
	}

}
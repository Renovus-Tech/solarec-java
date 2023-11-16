package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbGenStatisticVo;

public class GenStatisticVo extends DbGenStatisticVo {

	//--- Constructors --------------------------
	public GenStatisticVo() {
	}

	public GenStatisticVo(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId) {
		this.setPk(cliId, genId, statDate, statTypeId);
	}

}
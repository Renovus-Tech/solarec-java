package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbGenStatisticVo;

public class GenStatisticVo extends DbGenStatisticVo {

	//--- Constructors --------------------------
	public GenStatisticVo() {
	}

	public GenStatisticVo(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId) {
		this.setPk(cliId, genId, statDate, statTypeId);
	}

}
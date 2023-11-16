package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliUserVo;

public class CliUserVo extends DbCliUserVo {

	//--- Constructors --------------------------
	public CliUserVo() {
	}

	public CliUserVo(Integer cliId, Integer usrId) {
		this.setPk(cliId, usrId);
	}

}
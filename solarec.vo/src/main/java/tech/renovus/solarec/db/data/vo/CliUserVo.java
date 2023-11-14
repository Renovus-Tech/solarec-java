package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbCliUserVo;

public class CliUserVo extends DbCliUserVo {

	//--- Constructors --------------------------
	public CliUserVo() {
	}

	public CliUserVo(Integer cliId, Integer usrId) {
		this.setPk(cliId, usrId);
	}

}
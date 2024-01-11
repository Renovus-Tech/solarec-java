package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliGenUsrAlertVo;

public class CliGenUsrAlertVo extends DbCliGenUsrAlertVo {

	//--- Constructors --------------------------
	public CliGenUsrAlertVo() {
	}

	public CliGenUsrAlertVo(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) {
		this.setPk(cliId, genId, usrId, cliGenAlertId);
	}

}
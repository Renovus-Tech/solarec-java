package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliLocUsrAlertVo;

public class CliLocUsrAlertVo extends DbCliLocUsrAlertVo {

	//--- Constructors --------------------------
	public CliLocUsrAlertVo() {
	}

	public CliLocUsrAlertVo(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId) {
		this.setPk(cliId, locId, usrId, cliLocAlertId);
	}

}
package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliLocAlertVo;

public class CliLocAlertVo extends DbCliLocAlertVo {

	//--- Constructors --------------------------
	public CliLocAlertVo() {
	}

	public CliLocAlertVo(Integer cliId, Integer locId, Integer cliLocAlertId) {
		this.setPk(cliId, locId, cliLocAlertId);
	}

}
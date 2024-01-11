package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliGenAlertVo;

public class CliGenAlertVo extends DbCliGenAlertVo {

	//--- Constructors --------------------------
	public CliGenAlertVo() {
	}

	public CliGenAlertVo(Integer cliId, Integer genId, Integer cliGenAlertId) {
		this.setPk(cliId, genId, cliGenAlertId);
	}

}
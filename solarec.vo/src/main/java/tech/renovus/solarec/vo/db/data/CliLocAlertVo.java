package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbAlertDefinitionVo;

public class CliLocAlertVo extends DbAlertDefinitionVo {

	//--- Constructors --------------------------
	public CliLocAlertVo() {
	}

	public CliLocAlertVo(Integer alertDefId) {
		this.setPk(alertDefId);
	}

}
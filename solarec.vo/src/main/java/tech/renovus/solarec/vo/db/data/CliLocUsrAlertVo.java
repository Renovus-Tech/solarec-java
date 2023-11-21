package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbAlertDefinitionVo;

public class CliLocUsrAlertVo extends DbAlertDefinitionVo {

	//--- Constructors --------------------------
	public CliLocUsrAlertVo() {
	}

	public CliLocUsrAlertVo(Integer alertDefId) {
		this.setPk(alertDefId);
	}

}
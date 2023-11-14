package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbAlertDefinitionVo;

public class AlertDefinitionVo extends DbAlertDefinitionVo {

	//--- Constructors --------------------------
	public AlertDefinitionVo() {
	}

	public AlertDefinitionVo(Integer alertDefId) {
		this.setPk(alertDefId);
	}

}
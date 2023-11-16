package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbAlertDefinitionVo;

public class AlertDefinitionVo extends DbAlertDefinitionVo {

	//--- Constructors --------------------------
	public AlertDefinitionVo() {
	}

	public AlertDefinitionVo(Integer alertDefId) {
		this.setPk(alertDefId);
	}

}
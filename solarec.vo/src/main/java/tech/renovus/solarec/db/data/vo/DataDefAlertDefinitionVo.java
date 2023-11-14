package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDataDefAlertDefinitionVo;

public class DataDefAlertDefinitionVo extends DbDataDefAlertDefinitionVo {

	//--- Constructors --------------------------
	public DataDefAlertDefinitionVo() {
	}

	public DataDefAlertDefinitionVo(Integer dataDefId, Integer alertDefId) {
		this.setPk(dataDefId, alertDefId);
	}

}
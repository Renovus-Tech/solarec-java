package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataDefAlertDefinitionVo;

public class DataDefAlertDefinitionVo extends DbDataDefAlertDefinitionVo {

	//--- Constructors --------------------------
	public DataDefAlertDefinitionVo() {
	}

	public DataDefAlertDefinitionVo(Integer dataDefId, Integer alertDefId) {
		this.setPk(dataDefId, alertDefId);
	}

}
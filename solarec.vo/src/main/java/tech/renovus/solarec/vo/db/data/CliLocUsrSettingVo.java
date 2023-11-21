package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbAlertDefinitionVo;

public class CliLocUsrSettingVo extends DbAlertDefinitionVo {

	//--- Constructors --------------------------
	public CliLocUsrSettingVo() {
	}

	public CliLocUsrSettingVo(Integer alertDefId) {
		this.setPk(alertDefId);
	}

}
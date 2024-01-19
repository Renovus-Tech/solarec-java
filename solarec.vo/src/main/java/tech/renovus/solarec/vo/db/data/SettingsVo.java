package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbSettingsVo;

public class SettingsVo extends DbSettingsVo {

	//--- Constructors --------------------------
	public SettingsVo() {
	}

	public SettingsVo(String setName) {
		this.setPk(setName);
	}

}
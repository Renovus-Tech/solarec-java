package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.interfaces.ISetting;
import tech.renovus.solarec.vo.db.relation.DbUsrSettingVo;

public class UsrSettingVo extends DbUsrSettingVo implements ISetting {

	//--- Private properties --------------------
	private SettingsVo settingVo;
	
	//--- Constructors --------------------------
	public UsrSettingVo() {
	}

	public UsrSettingVo(Integer usrId, String usrSetName) {
		this.setPk(usrId, usrSetName);
	}

	public UsrSettingVo(Integer usrId, String usrSetName, String usrSetValue) {
		this.setPk(usrId, usrSetName);
		this.setUsrSetValue(usrSetValue);
	}
	
	//--- Static methods ------------------------
	
	//--- Implemented methods -------------------
	@Override public String getName() { return this.getUsrSetName(); }
	@Override public String getValue() { return this.getUsrSetValue(); }
	@Override public SettingsVo getSettingVo() { return settingVo; }

	//--- Getters and Setters -------------------
	public void setSettingVo(SettingsVo settingVo) {
		this.settingVo = settingVo;
	}

}
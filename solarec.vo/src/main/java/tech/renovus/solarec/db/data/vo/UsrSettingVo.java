package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbUsrSettingVo;
import tech.renovus.solarec.util.interfaces.ISetting;
import tech.renvous.solarec.util.StringUtil;

public class UsrSettingVo extends DbUsrSettingVo implements ISetting {

	//--- Public constants ----------------------
	
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
	public static boolean validName(String name) {
		if (StringUtil.isEmpty(name)) return false;
		
		switch (name) {
			default:
				return false;
		}
	}
	
	//--- Implemented methods -------------------
	@Override public String getName() { return this.getUsrSetName(); }
	@Override public String getValue() { return this.getUsrSetValue(); }

}
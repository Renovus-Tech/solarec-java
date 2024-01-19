package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.util.interfaces.ISetting;
import tech.renovus.solarec.vo.db.relation.DbCliSettingVo;

public class CliSettingVo extends DbCliSettingVo implements ISetting {

	//--- Private properties --------------------
	private SettingsVo settingVo;
	
	//--- Constructors --------------------------
	public CliSettingVo() {
	}

	public CliSettingVo(Integer cliId, String cliSetName) {
		this.setPk(cliId, cliSetName);
	}

	public CliSettingVo(Integer cliId, String cliSetName, String cliSetValue) {
		this.setPk(cliId, cliSetName);
		this.setCliSetValue(cliSetValue);
	}

	//--- Static methods ------------------------
	public double doubleValue() {
		return StringUtil.isEmpty(this.getValue()) ? this.settingVo.doubleValue() : Double.valueOf(this.getValue()).doubleValue();
	}
	
	//--- Implemented methods -------------------
	@Override public String getName() { return this.getCliSetName(); }
	@Override public String getValue() { return this.getCliSetValue(); }

	//--- Getters and Setters -------------------
	public SettingsVo getSettingVo() {
		return settingVo;
	}

	public void setSettingVo(SettingsVo settingVo) {
		this.settingVo = settingVo;
	}
	
}
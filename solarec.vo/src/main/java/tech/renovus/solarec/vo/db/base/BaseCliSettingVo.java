package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseCliSettingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_CLI_SET_NAME = "cli_set_name";
	public static final String COLUMN_CLI_SET_VALUE = "cli_set_value";

	public static final int LENGTH_COLUMN_CLI_SET_NAME =  200;
	public static final int LENGTH_COLUMN_CLI_SET_VALUE =  200;

	//--- Private properties --------------------
	private Integer cliId;
	private String cliSetName;
	private String cliSetValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.cliSetName == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliSettingVo)) return false;
		
		BaseCliSettingVo aObj = (BaseCliSettingVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliSetName,aObj.cliSetName)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.cliSetName != null) hashCode += this.cliSetName.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliSettingVo aObj = (BaseCliSettingVo) obj;
		if (!ClassUtil.equals(this.cliSetValue,aObj.cliSetValue)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, String cliSetName) {
		this.cliId = cliId;
		this.cliSetName = cliSetName;
	}

	public void setPk(BaseCliSettingVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.cliId, aVo.cliSetName);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCliSetName() {
		return this.cliSetName;
	}
	public void setCliSetName(String cliSetName) {
		this.cliSetName = cliSetName;
	}

	public String getCliSetValue() {
		return this.cliSetValue;
	}
	public void setCliSetValue(String cliSetValue) {
		this.cliSetValue = cliSetValue;
	}

}
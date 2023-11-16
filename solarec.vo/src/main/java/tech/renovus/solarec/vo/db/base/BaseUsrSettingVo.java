package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseUsrSettingVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_USR_ID = "usr_id";
	 public static final String COLUMN_USR_SET_NAME = "usr_set_name";
	 public static final String COLUMN_USR_SET_VALUE = "usr_set_value";

	//--- Private properties --------------------
	 private Integer usrId;
	 private String usrSetName;
	 private String usrSetValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.usrId == null) {
			return false;
		}
		if (this.usrSetName == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseUsrSettingVo)) return false;
		
		BaseUsrSettingVo aObj = (BaseUsrSettingVo) obj;
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrSetName,aObj.usrSetName)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		if (this.usrSetName != null) hashCode += this.usrSetName.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseUsrSettingVo)) return false;
		
		BaseUsrSettingVo aObj = (BaseUsrSettingVo) obj;
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrSetName,aObj.usrSetName)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrSetValue,aObj.usrSetValue)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer usrId, String usrSetName) {
		this.usrId = usrId;
		this.usrSetName = usrSetName;
	}

	public void setPk(BaseUsrSettingVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getUsrId(), aVo.getUsrSetName());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getUsrId() {
		return this.usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getUsrSetName() {
		return this.usrSetName;
	}
	public void setUsrSetName(String usrSetName) {
		this.usrSetName = usrSetName;
	}

	public String getUsrSetValue() {
		return this.usrSetValue;
	}
	public void setUsrSetValue(String usrSetValue) {
		this.usrSetValue = usrSetValue;
	}

}
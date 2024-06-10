package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseAlertDefinitionVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_ALERT_DEF_ID = "alert_def_id_auto";
	public static final String COLUMN_ALERT_DEF_NAME = "alert_def_name";
	public static final String COLUMN_ALERT_DEF_DESCRIPTION = "alert_def_description";
	public static final String COLUMN_ALERT_DEF_EXECUTABLE = "alert_def_executable";
	public static final String COLUMN_ALERT_DEF_FLAGS = "alert_def_flags";

	public static final int LENGTH_COLUMN_ALERT_DEF_NAME =  100;
	public static final int LENGTH_COLUMN_ALERT_DEF_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_ALERT_DEF_EXECUTABLE =  500;
	public static final int LENGTH_COLUMN_ALERT_DEF_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.alertDefFlags; }
	@Override public void setFlags(String alertDefFlags) { this.alertDefFlags = alertDefFlags; }

	//--- Private properties --------------------
	private Integer alertDefId;
	private String alertDefName;
	private String alertDefDescription;
	private String alertDefExecutable;
	private String alertDefFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.alertDefId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseAlertDefinitionVo)) return false;
		
		BaseAlertDefinitionVo aObj = (BaseAlertDefinitionVo) obj;
		if (!ClassUtil.equals(this.alertDefId,aObj.alertDefId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.alertDefId != null) hashCode += this.alertDefId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseAlertDefinitionVo aObj = (BaseAlertDefinitionVo) obj;
		if (!ClassUtil.equals(this.alertDefName,aObj.alertDefName)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefDescription,aObj.alertDefDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefExecutable,aObj.alertDefExecutable)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefFlags,aObj.alertDefFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer alertDefId) {
		this.alertDefId = alertDefId;
	}

	public void setPk(BaseAlertDefinitionVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.alertDefId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getAlertDefId() {
		return this.alertDefId;
	}
	public void setAlertDefId(Integer alertDefId) {
		this.alertDefId = alertDefId;
	}

	public String getAlertDefName() {
		return this.alertDefName;
	}
	public void setAlertDefName(String alertDefName) {
		this.alertDefName = alertDefName;
	}

	public String getAlertDefDescription() {
		return this.alertDefDescription;
	}
	public void setAlertDefDescription(String alertDefDescription) {
		this.alertDefDescription = alertDefDescription;
	}

	public String getAlertDefExecutable() {
		return this.alertDefExecutable;
	}
	public void setAlertDefExecutable(String alertDefExecutable) {
		this.alertDefExecutable = alertDefExecutable;
	}

	public String getAlertDefFlags() {
		return this.alertDefFlags;
	}
	public void setAlertDefFlags(String alertDefFlags) {
		this.alertDefFlags = alertDefFlags;
	}

}
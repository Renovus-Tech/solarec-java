package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseAlertProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_ALERT_PRO_RESULT = "alert_pro_result";
	public static final String COLUMN_ALERT_PRO_ID = "alert_pro_id_auto";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_ALERT_DEF_ID = "alert_def_id";
	public static final String COLUMN_ALERT_PRO_DATE_START = "alert_pro_date_start";
	public static final String COLUMN_ALERT_PRO_DATE_END = "alert_pro_date_end";
	public static final String COLUMN_ALERT_PRO_FILE_LOG = "alert_pro_file_log";
	public static final String COLUMN_ALERT_PRO_FILE_NAME = "alert_pro_file_name";

	public static final int LENGTH_COLUMN_ALERT_PRO_FILE_LOG =  100;
	public static final int LENGTH_COLUMN_ALERT_PRO_FILE_NAME =  100;

	//--- Private properties --------------------
	private Integer alertProResult;
	private Integer alertProId;
	private Integer cliId;
	private Integer locId;
	private Integer alertDefId;
	private java.util.Date alertProDateStart;
	private java.util.Date alertProDateEnd;
	private String alertProFileLog;
	private String alertProFileName;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.alertProId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		if (this.alertDefId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseAlertProcessingVo)) return false;
		
		BaseAlertProcessingVo aObj = (BaseAlertProcessingVo) obj;
		return ClassUtil.equals(this.alertProId,aObj.alertProId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.alertProId != null) hashCode += this.alertProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseAlertProcessingVo aObj = (BaseAlertProcessingVo) obj;
		if (!ClassUtil.equals(this.alertProResult,aObj.alertProResult)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefId,aObj.alertDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProDateStart,aObj.alertProDateStart)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProDateEnd,aObj.alertProDateEnd)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProFileLog,aObj.alertProFileLog)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProFileName,aObj.alertProFileName)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer alertProId) {
		this.alertProId = alertProId;
	}

	public void setPk(BaseAlertProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.alertProId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getAlertProResult() {
		return this.alertProResult;
	}
	public void setAlertProResult(Integer alertProResult) {
		this.alertProResult = alertProResult;
	}

	public Integer getAlertProId() {
		return this.alertProId;
	}
	public void setAlertProId(Integer alertProId) {
		this.alertProId = alertProId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getAlertDefId() {
		return this.alertDefId;
	}
	public void setAlertDefId(Integer alertDefId) {
		this.alertDefId = alertDefId;
	}

	public java.util.Date getAlertProDateStart() {
		return this.alertProDateStart;
	}
	public void setAlertProDateStart(java.util.Date alertProDateStart) {
		this.alertProDateStart = alertProDateStart;
	}

	public java.util.Date getAlertProDateEnd() {
		return this.alertProDateEnd;
	}
	public void setAlertProDateEnd(java.util.Date alertProDateEnd) {
		this.alertProDateEnd = alertProDateEnd;
	}

	public String getAlertProFileLog() {
		return this.alertProFileLog;
	}
	public void setAlertProFileLog(String alertProFileLog) {
		this.alertProFileLog = alertProFileLog;
	}

	public String getAlertProFileName() {
		return this.alertProFileName;
	}
	public void setAlertProFileName(String alertProFileName) {
		this.alertProFileName = alertProFileName;
	}

}
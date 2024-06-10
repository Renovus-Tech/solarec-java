package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseStatProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_STAT_PRO_TYPE = "stat_pro_type";
	public static final String COLUMN_STAT_DEF_ID = "stat_def_id";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_STAT_PRO_DATE_START = "stat_pro_date_start";
	public static final String COLUMN_STAT_PRO_DATE_END = "stat_pro_date_end";
	public static final String COLUMN_STAT_PRO_RESULT = "stat_pro_result";
	public static final String COLUMN_STAT_PRO_ID = "stat_pro_id_auto";
	public static final String COLUMN_STAT_PRO_FILE_LOG = "stat_pro_file_log";

	public static final int LENGTH_COLUMN_STAT_PRO_FILE_LOG =  100;

	//--- Private properties --------------------
	private Integer statProType;
	private Integer statDefId;
	private Integer cliId;
	private java.util.Date statProDateStart;
	private java.util.Date statProDateEnd;
	private Integer statProResult;
	private Integer statProId;
	private String statProFileLog;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statDefId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		if (this.statProId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatProcessingVo)) return false;
		
		BaseStatProcessingVo aObj = (BaseStatProcessingVo) obj;
		if (!ClassUtil.equals(this.statProId,aObj.statProId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statProId != null) hashCode += this.statProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStatProcessingVo aObj = (BaseStatProcessingVo) obj;
		if (!ClassUtil.equals(this.statProType,aObj.statProType)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProDateStart,aObj.statProDateStart)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProDateEnd,aObj.statProDateEnd)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProResult,aObj.statProResult)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProFileLog,aObj.statProFileLog)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer statProId) {
		this.statProId = statProId;
	}

	public void setPk(BaseStatProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.statProId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getStatProType() {
		return this.statProType;
	}
	public void setStatProType(Integer statProType) {
		this.statProType = statProType;
	}

	public Integer getStatDefId() {
		return this.statDefId;
	}
	public void setStatDefId(Integer statDefId) {
		this.statDefId = statDefId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public java.util.Date getStatProDateStart() {
		return this.statProDateStart;
	}
	public void setStatProDateStart(java.util.Date statProDateStart) {
		this.statProDateStart = statProDateStart;
	}

	public java.util.Date getStatProDateEnd() {
		return this.statProDateEnd;
	}
	public void setStatProDateEnd(java.util.Date statProDateEnd) {
		this.statProDateEnd = statProDateEnd;
	}

	public Integer getStatProResult() {
		return this.statProResult;
	}
	public void setStatProResult(Integer statProResult) {
		this.statProResult = statProResult;
	}

	public Integer getStatProId() {
		return this.statProId;
	}
	public void setStatProId(Integer statProId) {
		this.statProId = statProId;
	}

	public String getStatProFileLog() {
		return this.statProFileLog;
	}
	public void setStatProFileLog(String statProFileLog) {
		this.statProFileLog = statProFileLog;
	}

}
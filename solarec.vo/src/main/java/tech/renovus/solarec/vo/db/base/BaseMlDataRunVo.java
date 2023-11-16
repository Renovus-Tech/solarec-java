package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseMlDataRunVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_RUN_ID = "run_id_auto";
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_DATA_DATE = "data_date";
	 public static final String COLUMN_TOTAL_TIME_MS = "total_time_ms";
	 public static final String COLUMN_MODEL_VERSION = "model_version";
	 public static final String COLUMN_TOTAL_EVALUATED_ROWS = "total_evaluated_rows";

	//--- Private properties --------------------
	 private Integer runId;
	 private Integer cliId;
	 private java.util.Date dataDate;
	 private java.util.Date totalTimeMs;
	 private String modelVersion;
	 private Integer totalEvaluatedRows;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.runId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseMlDataRunVo)) return false;
		
		BaseMlDataRunVo aObj = (BaseMlDataRunVo) obj;
		if (!ClassUtil.equals(this.runId,aObj.runId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.runId != null) hashCode += this.runId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseMlDataRunVo)) return false;
		
		BaseMlDataRunVo aObj = (BaseMlDataRunVo) obj;
		if (!ClassUtil.equals(this.runId,aObj.runId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDate,aObj.dataDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.totalTimeMs,aObj.totalTimeMs)) {
			return false;
		}
		if (!ClassUtil.equals(this.modelVersion,aObj.modelVersion)) {
			return false;
		}
		if (!ClassUtil.equals(this.totalEvaluatedRows,aObj.totalEvaluatedRows)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer runId) {
		this.runId = runId;
	}

	public void setPk(BaseMlDataRunVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.getRunId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getRunId() {
		return this.runId;
	}
	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public java.util.Date getDataDate() {
		return this.dataDate;
	}
	public void setDataDate(java.util.Date dataDate) {
		this.dataDate = dataDate;
	}

	public java.util.Date getTotalTimeMs() {
		return this.totalTimeMs;
	}
	public void setTotalTimeMs(java.util.Date totalTimeMs) {
		this.totalTimeMs = totalTimeMs;
	}

	public String getModelVersion() {
		return this.modelVersion;
	}
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	public Integer getTotalEvaluatedRows() {
		return this.totalEvaluatedRows;
	}
	public void setTotalEvaluatedRows(Integer totalEvaluatedRows) {
		this.totalEvaluatedRows = totalEvaluatedRows;
	}

}
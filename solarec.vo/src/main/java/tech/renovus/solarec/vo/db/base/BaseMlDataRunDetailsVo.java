package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseMlDataRunDetailsVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_RUN_DET_ID = "run_det_id_auto";
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_RUN_ID = "run_id";
	 public static final String COLUMN_DATA_GEN_ID = "data_gen_id";
	 public static final String COLUMN_DATA_DATE = "data_date";
	 public static final String COLUMN_PREDICTION = "prediction";

	//--- Private properties --------------------
	 private Integer runDetId;
	 private Integer cliId;
	 private Integer runId;
	 private Integer dataGenId;
	 private java.util.Date dataDate;
	 private java.util.Date prediction;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.runDetId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseMlDataRunDetailsVo)) return false;
		
		BaseMlDataRunDetailsVo aObj = (BaseMlDataRunDetailsVo) obj;
		if (!ClassUtil.equals(this.runDetId,aObj.runDetId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.runDetId != null) hashCode += this.runDetId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseMlDataRunDetailsVo)) return false;
		
		BaseMlDataRunDetailsVo aObj = (BaseMlDataRunDetailsVo) obj;
		if (!ClassUtil.equals(this.runDetId,aObj.runDetId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.runId,aObj.runId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataGenId,aObj.dataGenId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDate,aObj.dataDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.prediction,aObj.prediction)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer runDetId) {
		this.runDetId = runDetId;
	}

	public void setPk(BaseMlDataRunDetailsVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.getRunDetId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getRunDetId() {
		return this.runDetId;
	}
	public void setRunDetId(Integer runDetId) {
		this.runDetId = runDetId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getRunId() {
		return this.runId;
	}
	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	public Integer getDataGenId() {
		return this.dataGenId;
	}
	public void setDataGenId(Integer dataGenId) {
		this.dataGenId = dataGenId;
	}

	public java.util.Date getDataDate() {
		return this.dataDate;
	}
	public void setDataDate(java.util.Date dataDate) {
		this.dataDate = dataDate;
	}

	public java.util.Date getPrediction() {
		return this.prediction;
	}
	public void setPrediction(java.util.Date prediction) {
		this.prediction = prediction;
	}

}
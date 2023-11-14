package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseLocGenAlarmVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_ALARM_CODE = "alarm_code";
	 public static final String COLUMN_ALARM_DESCRIPTION = "alarm_description";
	 public static final String COLUMN_DATA_CAT_ID = "data_cat_id";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private Double alarmCode;
	 private String alarmDescription;
	 private Integer dataCatId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.alarmCode == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocGenAlarmVo)) return false;
		
		BaseLocGenAlarmVo aObj = (BaseLocGenAlarmVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alarmCode,aObj.alarmCode)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.alarmCode != null) hashCode += this.alarmCode.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocGenAlarmVo)) return false;
		
		BaseLocGenAlarmVo aObj = (BaseLocGenAlarmVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alarmCode,aObj.alarmCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.alarmDescription,aObj.alarmDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataCatId,aObj.dataCatId)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Double alarmCode) {
		this.cliId = cliId;
		this.locId = locId;
		this.alarmCode = alarmCode;
	}

	public void setPk(BaseLocGenAlarmVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getAlarmCode());
		}
	}

	//--- Getters and Setters -------------------
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

	public Double getAlarmCode() {
		return this.alarmCode;
	}
	public void setAlarmCode(Double alarmCode) {
		this.alarmCode = alarmCode;
	}

	public String getAlarmDescription() {
		return this.alarmDescription;
	}
	public void setAlarmDescription(String alarmDescription) {
		this.alarmDescription = alarmDescription;
	}

	public Integer getDataCatId() {
		return this.dataCatId;
	}
	public void setDataCatId(Integer dataCatId) {
		this.dataCatId = dataCatId;
	}

}
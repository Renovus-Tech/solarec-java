package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseLocDataWeatherVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_DATA_DATE_ADDED = "data_date_added";
	 public static final String COLUMN_DATA_DATE = "data_date";
	 public static final String COLUMN_DATA_TYPE_ID = "data_type_id";
	 public static final String COLUMN_DATA_VALUE = "data_value";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private java.util.Date dataDateAdded;
	 private java.util.Date dataDate;
	 private Integer dataTypeId;
	 private Double dataValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.dataDateAdded == null) {
			return false;
		}
		if (this.dataDate == null) {
			return false;
		}
		if (this.dataTypeId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocDataWeatherVo)) return false;
		
		BaseLocDataWeatherVo aObj = (BaseLocDataWeatherVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDateAdded,aObj.dataDateAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDate,aObj.dataDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.dataDateAdded != null) hashCode += this.dataDateAdded.hashCode();
		if (this.dataDate != null) hashCode += this.dataDate.hashCode();
		if (this.dataTypeId != null) hashCode += this.dataTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocDataWeatherVo)) return false;
		
		BaseLocDataWeatherVo aObj = (BaseLocDataWeatherVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDateAdded,aObj.dataDateAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDate,aObj.dataDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue,aObj.dataValue)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, java.util.Date dataDateAdded, java.util.Date dataDate, Integer dataTypeId) {
		this.cliId = cliId;
		this.locId = locId;
		this.dataDateAdded = dataDateAdded;
		this.dataDate = dataDate;
		this.dataTypeId = dataTypeId;
	}

	public void setPk(BaseLocDataWeatherVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getDataDateAdded(), aVo.getDataDate(), aVo.getDataTypeId());
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

	public java.util.Date getDataDateAdded() {
		return this.dataDateAdded;
	}
	public void setDataDateAdded(java.util.Date dataDateAdded) {
		this.dataDateAdded = dataDateAdded;
	}

	public java.util.Date getDataDate() {
		return this.dataDate;
	}
	public void setDataDate(java.util.Date dataDate) {
		this.dataDate = dataDate;
	}

	public Integer getDataTypeId() {
		return this.dataTypeId;
	}
	public void setDataTypeId(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public Double getDataValue() {
		return this.dataValue;
	}
	public void setDataValue(Double dataValue) {
		this.dataValue = dataValue;
	}

}
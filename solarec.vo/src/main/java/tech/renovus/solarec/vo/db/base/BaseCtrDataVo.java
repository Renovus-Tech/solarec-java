package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseCtrDataVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CTR_ID = "ctr_id";
	public static final String COLUMN_DATA_DATE = "data_date";
	public static final String COLUMN_DATA_TYPE_ID = "data_type_id";
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	public static final String COLUMN_DATA_VALUE = "data_value";
	public static final String COLUMN_DATA_DATE_ADDED = "data_date_added";


	//--- Private properties --------------------
	private Integer ctrId;
	private java.util.Date dataDate;
	private Integer dataTypeId;
	private Integer dataProId;
	private Double dataValue;
	private java.util.Date dataDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.ctrId == null) {
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

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCtrDataVo)) return false;
		
		BaseCtrDataVo aObj = (BaseCtrDataVo) obj;
		if (!ClassUtil.equals(this.ctrId,aObj.ctrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDate.getTime(),aObj.dataDate.getTime())) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.ctrId != null) hashCode += this.ctrId.hashCode();
		if (this.dataDate != null) hashCode += this.dataDate.hashCode();
		if (this.dataTypeId != null) hashCode += this.dataTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCtrDataVo aObj = (BaseCtrDataVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue,aObj.dataValue)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDateAdded,aObj.dataDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer ctrId, java.util.Date dataDate, Integer dataTypeId) {
		this.ctrId = ctrId;
		this.dataDate = dataDate;
		this.dataTypeId = dataTypeId;
	}

	public void setPk(BaseCtrDataVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.ctrId, aVo.dataDate, aVo.dataTypeId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCtrId() {
		return this.ctrId;
	}
	public void setCtrId(Integer ctrId) {
		this.ctrId = ctrId;
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

	public Integer getDataProId() {
		return this.dataProId;
	}
	public void setDataProId(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public Double getDataValue() {
		return this.dataValue;
	}
	public void setDataValue(Double dataValue) {
		this.dataValue = dataValue;
	}

	public java.util.Date getDataDateAdded() {
		return this.dataDateAdded;
	}
	public void setDataDateAdded(java.util.Date dataDateAdded) {
		this.dataDateAdded = dataDateAdded;
	}

}
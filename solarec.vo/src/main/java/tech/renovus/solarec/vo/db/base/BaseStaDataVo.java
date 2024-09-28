package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseStaDataVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_STA_ID = "sta_id";
	public static final String COLUMN_DATA_DATE = "data_date";
	public static final String COLUMN_DATA_TYPE_ID = "data_type_id";
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	public static final String COLUMN_DATA_VALUE = "data_value";
	public static final String COLUMN_DATA_DATE_ADDED = "data_date_added";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer staId;
	private java.util.Date dataDate;
	private Integer dataTypeId;
	private Integer dataProId;
	private Double dataValue;
	private java.util.Date dataDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.staId == null) {
			return false;
		}
		if (this.dataDate == null) {
			return false;
		}
		if (this.dataTypeId == null) {
			return false;
		}
		if (this.dataProId == null) {
			return false;
		}
		if (this.dataValue == null) {
			return false;
		}
		if (this.dataDateAdded == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStaDataVo)) return false;
		
		BaseStaDataVo aObj = (BaseStaDataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
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
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.staId != null) hashCode += this.staId.hashCode();
		if (this.dataDate != null) hashCode += this.dataDate.hashCode();
		if (this.dataTypeId != null) hashCode += this.dataTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStaDataVo aObj = (BaseStaDataVo) obj;
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

	public void setPk(Integer cliId, Integer staId, java.util.Date dataDate, Integer dataTypeId) {
		this.cliId = cliId;
		this.staId = staId;
		this.dataDate = dataDate;
		this.dataTypeId = dataTypeId;
	}

	public void setPk(BaseStaDataVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.staId, aVo.dataDate, aVo.dataTypeId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getStaId() {
		return this.staId;
	}
	public void setStaId(Integer staId) {
		this.staId = staId;
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
package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseDataTypeVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_DATA_TYPE_ID = "data_type_id";
	 public static final String COLUMN_DATA_TYPE_NAME = "data_type_name";
	 public static final String COLUMN_DATA_TYPE_UNITS = "data_type_units";
	 public static final String COLUMN_DATA_TYPE_DESCRIPTION = "data_type_description";

	//--- Private properties --------------------
	 private Integer dataTypeId;
	 private String dataTypeName;
	 private String dataTypeUnits;
	 private String dataTypeDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataTypeId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataTypeVo)) return false;
		
		BaseDataTypeVo aObj = (BaseDataTypeVo) obj;
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataTypeId != null) hashCode += this.dataTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataTypeVo)) return false;
		
		BaseDataTypeVo aObj = (BaseDataTypeVo) obj;
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeName,aObj.dataTypeName)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeUnits,aObj.dataTypeUnits)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeDescription,aObj.dataTypeDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public void setPk(BaseDataTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.getDataTypeId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataTypeId() {
		return this.dataTypeId;
	}
	public void setDataTypeId(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getDataTypeName() {
		return this.dataTypeName;
	}
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public String getDataTypeUnits() {
		return this.dataTypeUnits;
	}
	public void setDataTypeUnits(String dataTypeUnits) {
		this.dataTypeUnits = dataTypeUnits;
	}

	public String getDataTypeDescription() {
		return this.dataTypeDescription;
	}
	public void setDataTypeDescription(String dataTypeDescription) {
		this.dataTypeDescription = dataTypeDescription;
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseStatTypeVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_STAT_TYPE_ID = "stat_type_id";
	 public static final String COLUMN_STAT_TYPE_NAME = "stat_type_name";
	 public static final String COLUMN_STAT_TYPE_UNIT = "stat_type_unit";
	 public static final String COLUMN_STAT_TYPE_DESCRIPTION = "stat_type_description";

	//--- Private properties --------------------
	 private Integer statTypeId;
	 private String statTypeName;
	 private String statTypeUnit;
	 private String statTypeDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statTypeId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatTypeVo)) return false;
		
		BaseStatTypeVo aObj = (BaseStatTypeVo) obj;
		if (!ClassUtil.equals(this.statTypeId,aObj.statTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statTypeId != null) hashCode += this.statTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatTypeVo)) return false;
		
		BaseStatTypeVo aObj = (BaseStatTypeVo) obj;
		if (!ClassUtil.equals(this.statTypeId,aObj.statTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeName,aObj.statTypeName)) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeUnit,aObj.statTypeUnit)) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeDescription,aObj.statTypeDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer statTypeId) {
		this.statTypeId = statTypeId;
	}

	public void setPk(BaseStatTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.getStatTypeId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getStatTypeId() {
		return this.statTypeId;
	}
	public void setStatTypeId(Integer statTypeId) {
		this.statTypeId = statTypeId;
	}

	public String getStatTypeName() {
		return this.statTypeName;
	}
	public void setStatTypeName(String statTypeName) {
		this.statTypeName = statTypeName;
	}

	public String getStatTypeUnit() {
		return this.statTypeUnit;
	}
	public void setStatTypeUnit(String statTypeUnit) {
		this.statTypeUnit = statTypeUnit;
	}

	public String getStatTypeDescription() {
		return this.statTypeDescription;
	}
	public void setStatTypeDescription(String statTypeDescription) {
		this.statTypeDescription = statTypeDescription;
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseSettingsVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_SET_NAME = "set_name";
	public static final String COLUMN_SET_CAT_NAME = "set_cat_name";
	public static final String COLUMN_SET_TYPE = "set_type";
	public static final String COLUMN_SET_UNIT = "set_unit";
	public static final String COLUMN_SET_VALUE_DEFAULT = "set_value_default";
	public static final String COLUMN_SET_VALUE_MIN = "set_value_min";
	public static final String COLUMN_SET_VALUE_MAX = "set_value_max";
	public static final String COLUMN_SET_FLAGS = "set_flags";

	public static final int LENGTH_COLUMN_SET_NAME =  200;
	public static final int LENGTH_COLUMN_SET_CAT_NAME =  200;
	public static final int LENGTH_COLUMN_SET_TYPE =  200;
	public static final int LENGTH_COLUMN_SET_UNIT =  100;
	public static final int LENGTH_COLUMN_SET_VALUE_DEFAULT =  200;
	public static final int LENGTH_COLUMN_SET_VALUE_MIN =  100;
	public static final int LENGTH_COLUMN_SET_VALUE_MAX =  100;
	public static final int LENGTH_COLUMN_SET_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.setFlags; }
	@Override public void setFlags(String setFlags) { this.setFlags = setFlags; }

	//--- Private properties --------------------
	private String setName;
	private String setCatName;
	private String setType;
	private String setUnit;
	private String setValueDefault;
	private String setValueMin;
	private String setValueMax;
	private String setFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.setName == null) {
			return false;
		}
		if (this.setCatName == null) {
			return false;
		}
		if (this.setType == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseSettingsVo)) return false;
		
		BaseSettingsVo aObj = (BaseSettingsVo) obj;
		if (!ClassUtil.equals(this.setName,aObj.setName)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.setName != null) hashCode += this.setName.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseSettingsVo aObj = (BaseSettingsVo) obj;
		if (!ClassUtil.equals(this.setCatName,aObj.setCatName)) {
			return false;
		}
		if (!ClassUtil.equals(this.setType,aObj.setType)) {
			return false;
		}
		if (!ClassUtil.equals(this.setUnit,aObj.setUnit)) {
			return false;
		}
		if (!ClassUtil.equals(this.setValueDefault,aObj.setValueDefault)) {
			return false;
		}
		if (!ClassUtil.equals(this.setValueMin,aObj.setValueMin)) {
			return false;
		}
		if (!ClassUtil.equals(this.setValueMax,aObj.setValueMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.setFlags,aObj.setFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(String setName) {
		this.setName = setName;
	}

	public void setPk(BaseSettingsVo aVo) {
		if(aVo == null) { 
			this.setPk((String)null);
		} else {
			this.setPk((String)aVo.setName);
		}
	}

	//--- Getters and Setters -------------------
	public String getSetName() {
		return this.setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getSetCatName() {
		return this.setCatName;
	}
	public void setSetCatName(String setCatName) {
		this.setCatName = setCatName;
	}

	public String getSetType() {
		return this.setType;
	}
	public void setSetType(String setType) {
		this.setType = setType;
	}

	public String getSetUnit() {
		return this.setUnit;
	}
	public void setSetUnit(String setUnit) {
		this.setUnit = setUnit;
	}

	public String getSetValueDefault() {
		return this.setValueDefault;
	}
	public void setSetValueDefault(String setValueDefault) {
		this.setValueDefault = setValueDefault;
	}

	public String getSetValueMin() {
		return this.setValueMin;
	}
	public void setSetValueMin(String setValueMin) {
		this.setValueMin = setValueMin;
	}

	public String getSetValueMax() {
		return this.setValueMax;
	}
	public void setSetValueMax(String setValueMax) {
		this.setValueMax = setValueMax;
	}

	public String getSetFlags() {
		return this.setFlags;
	}
	public void setSetFlags(String setFlags) {
		this.setFlags = setFlags;
	}

}
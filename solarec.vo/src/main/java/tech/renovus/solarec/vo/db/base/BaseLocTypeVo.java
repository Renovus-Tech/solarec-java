package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseLocTypeVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_LOC_TYPE_ID = "loc_type_id_auto";
	public static final String COLUMN_LOC_TYPE_CODE = "loc_type_code";
	public static final String COLUMN_LOC_TYPE_TEXT = "loc_type_text";

	public static final int LENGTH_COLUMN_LOC_TYPE_CODE =  255;
	public static final int LENGTH_COLUMN_LOC_TYPE_TEXT =  255;

	//--- Private properties --------------------
	private Integer locTypeId;
	private String locTypeCode;
	private String locTypeText;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.locTypeId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocTypeVo)) return false;
		
		BaseLocTypeVo aObj = (BaseLocTypeVo) obj;
		return ClassUtil.equals(this.locTypeId,aObj.locTypeId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.locTypeId != null) hashCode += this.locTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocTypeVo aObj = (BaseLocTypeVo) obj;
		if (!ClassUtil.equals(this.locTypeCode,aObj.locTypeCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.locTypeText,aObj.locTypeText)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer locTypeId) {
		this.locTypeId = locTypeId;
	}

	public void setPk(BaseLocTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.locTypeId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getLocTypeId() {
		return this.locTypeId;
	}
	public void setLocTypeId(Integer locTypeId) {
		this.locTypeId = locTypeId;
	}

	public String getLocTypeCode() {
		return this.locTypeCode;
	}
	public void setLocTypeCode(String locTypeCode) {
		this.locTypeCode = locTypeCode;
	}

	public String getLocTypeText() {
		return this.locTypeText;
	}
	public void setLocTypeText(String locTypeText) {
		this.locTypeText = locTypeText;
	}

}
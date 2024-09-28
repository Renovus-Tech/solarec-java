package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseDataCategoryVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_CAT_ID = "data_cat_id_auto";
	public static final String COLUMN_DATA_CAT_NAME = "data_cat_name";
	public static final String COLUMN_DATA_CAT_DESCRIPTION = "data_cat_description";

	public static final int LENGTH_COLUMN_DATA_CAT_NAME =  255;
	public static final int LENGTH_COLUMN_DATA_CAT_DESCRIPTION =  255;

	//--- Private properties --------------------
	private Integer dataCatId;
	private String dataCatName;
	private String dataCatDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataCatId == null) {
			return false;
		}
		if (this.dataCatName == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataCategoryVo)) return false;
		
		BaseDataCategoryVo aObj = (BaseDataCategoryVo) obj;
		return ClassUtil.equals(this.dataCatId,aObj.dataCatId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataCatId != null) hashCode += this.dataCatId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataCategoryVo aObj = (BaseDataCategoryVo) obj;
		if (!ClassUtil.equals(this.dataCatName,aObj.dataCatName)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataCatDescription,aObj.dataCatDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataCatId) {
		this.dataCatId = dataCatId;
	}

	public void setPk(BaseDataCategoryVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.dataCatId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataCatId() {
		return this.dataCatId;
	}
	public void setDataCatId(Integer dataCatId) {
		this.dataCatId = dataCatId;
	}

	public String getDataCatName() {
		return this.dataCatName;
	}
	public void setDataCatName(String dataCatName) {
		this.dataCatName = dataCatName;
	}

	public String getDataCatDescription() {
		return this.dataCatDescription;
	}
	public void setDataCatDescription(String dataCatDescription) {
		this.dataCatDescription = dataCatDescription;
	}

}
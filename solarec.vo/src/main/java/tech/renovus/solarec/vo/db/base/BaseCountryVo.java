package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseCountryVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CTR_ID = "ctr_id_auto";
	public static final String COLUMN_CTR_DATA_DATE_MAX = "ctr_data_date_max";
	public static final String COLUMN_CTR_DATA_DATE_MIN = "ctr_data_date_min";
	public static final String COLUMN_CTR_CODE_3 = "ctr_code_3";
	public static final String COLUMN_CTR_CODE_2 = "ctr_code_2";
	public static final String COLUMN_CTR_NAME = "ctr_name";
	public static final String COLUMN_CTR_NAME_SHOW = "ctr_name_show";

	public static final int LENGTH_COLUMN_CTR_CODE_3 =  3;
	public static final int LENGTH_COLUMN_CTR_CODE_2 =  2;
	public static final int LENGTH_COLUMN_CTR_NAME =  120;
	public static final int LENGTH_COLUMN_CTR_NAME_SHOW =  120;

	//--- Private properties --------------------
	private Integer ctrId;
	private java.util.Date ctrDataDateMax;
	private java.util.Date ctrDataDateMin;
	private String ctrCode3;
	private String ctrCode2;
	private String ctrName;
	private String ctrNameShow;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.ctrId == null) {
			return false;
		}
		if (this.ctrName == null) {
			return false;
		}
		if (this.ctrNameShow == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCountryVo)) return false;
		
		BaseCountryVo aObj = (BaseCountryVo) obj;
		if (!ClassUtil.equals(this.ctrId,aObj.ctrId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.ctrId != null) hashCode += this.ctrId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCountryVo aObj = (BaseCountryVo) obj;
		if (!ClassUtil.equals(this.ctrDataDateMax,aObj.ctrDataDateMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.ctrDataDateMin,aObj.ctrDataDateMin)) {
			return false;
		}
		if (!ClassUtil.equals(this.ctrCode3,aObj.ctrCode3)) {
			return false;
		}
		if (!ClassUtil.equals(this.ctrCode2,aObj.ctrCode2)) {
			return false;
		}
		if (!ClassUtil.equals(this.ctrName,aObj.ctrName)) {
			return false;
		}
		if (!ClassUtil.equals(this.ctrNameShow,aObj.ctrNameShow)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer ctrId) {
		this.ctrId = ctrId;
	}

	public void setPk(BaseCountryVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.ctrId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCtrId() {
		return this.ctrId;
	}
	public void setCtrId(Integer ctrId) {
		this.ctrId = ctrId;
	}

	public java.util.Date getCtrDataDateMax() {
		return this.ctrDataDateMax;
	}
	public void setCtrDataDateMax(java.util.Date ctrDataDateMax) {
		this.ctrDataDateMax = ctrDataDateMax;
	}

	public java.util.Date getCtrDataDateMin() {
		return this.ctrDataDateMin;
	}
	public void setCtrDataDateMin(java.util.Date ctrDataDateMin) {
		this.ctrDataDateMin = ctrDataDateMin;
	}

	public String getCtrCode3() {
		return this.ctrCode3;
	}
	public void setCtrCode3(String ctrCode3) {
		this.ctrCode3 = ctrCode3;
	}

	public String getCtrCode2() {
		return this.ctrCode2;
	}
	public void setCtrCode2(String ctrCode2) {
		this.ctrCode2 = ctrCode2;
	}

	public String getCtrName() {
		return this.ctrName;
	}
	public void setCtrName(String ctrName) {
		this.ctrName = ctrName;
	}

	public String getCtrNameShow() {
		return this.ctrNameShow;
	}
	public void setCtrNameShow(String ctrNameShow) {
		this.ctrNameShow = ctrNameShow;
	}

}
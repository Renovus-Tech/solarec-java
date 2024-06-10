package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseSdgVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_SDG_ID = "sdg_id_auto";
	public static final String COLUMN_SDG_CODE = "sdg_code";
	public static final String COLUMN_SDG_NAME = "sdg_name";

	public static final int LENGTH_COLUMN_SDG_CODE =  10;
	public static final int LENGTH_COLUMN_SDG_NAME =  255;

	//--- Private properties --------------------
	private Integer sdgId;
	private String sdgCode;
	private String sdgName;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.sdgId == null) {
			return false;
		}
		if (this.sdgCode == null) {
			return false;
		}
		if (this.sdgName == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseSdgVo)) return false;
		
		BaseSdgVo aObj = (BaseSdgVo) obj;
		if (!ClassUtil.equals(this.sdgId,aObj.sdgId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.sdgId != null) hashCode += this.sdgId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseSdgVo aObj = (BaseSdgVo) obj;
		if (!ClassUtil.equals(this.sdgCode,aObj.sdgCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.sdgName,aObj.sdgName)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer sdgId) {
		this.sdgId = sdgId;
	}

	public void setPk(BaseSdgVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.sdgId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getSdgId() {
		return this.sdgId;
	}
	public void setSdgId(Integer sdgId) {
		this.sdgId = sdgId;
	}

	public String getSdgCode() {
		return this.sdgCode;
	}
	public void setSdgCode(String sdgCode) {
		this.sdgCode = sdgCode;
	}

	public String getSdgName() {
		return this.sdgName;
	}
	public void setSdgName(String sdgName) {
		this.sdgName = sdgName;
	}

}
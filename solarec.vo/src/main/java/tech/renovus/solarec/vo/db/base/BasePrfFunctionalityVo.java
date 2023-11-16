package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BasePrfFunctionalityVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_PRF_ID = "prf_id";
	 public static final String COLUMN_FNC_ID = "fnc_id";

	//--- Private properties --------------------
	 private Integer prfId;
	 private Integer fncId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.prfId == null) {
			return false;
		}
		if (this.fncId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BasePrfFunctionalityVo)) return false;
		
		BasePrfFunctionalityVo aObj = (BasePrfFunctionalityVo) obj;
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncId,aObj.fncId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.prfId != null) hashCode += this.prfId.hashCode();
		if (this.fncId != null) hashCode += this.fncId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BasePrfFunctionalityVo)) return false;
		
		BasePrfFunctionalityVo aObj = (BasePrfFunctionalityVo) obj;
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncId,aObj.fncId)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer prfId, Integer fncId) {
		this.prfId = prfId;
		this.fncId = fncId;
	}

	public void setPk(BasePrfFunctionalityVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getPrfId(), aVo.getFncId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getPrfId() {
		return this.prfId;
	}
	public void setPrfId(Integer prfId) {
		this.prfId = prfId;
	}

	public Integer getFncId() {
		return this.fncId;
	}
	public void setFncId(Integer fncId) {
		this.fncId = fncId;
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseStatDefResultVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_STAT_DEF_ID = "stat_def_id";
	public static final String COLUMN_STAT_TYPE_ID = "stat_type_id";


	//--- Private properties --------------------
	private Integer statDefId;
	private Integer statTypeId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statDefId == null) {
			return false;
		}
		if (this.statTypeId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatDefResultVo)) return false;
		
		BaseStatDefResultVo aObj = (BaseStatDefResultVo) obj;
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeId,aObj.statTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statDefId != null) hashCode += this.statDefId.hashCode();
		if (this.statTypeId != null) hashCode += this.statTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStatDefResultVo aObj = (BaseStatDefResultVo) obj;
		return true;
	}

	public void setPk(Integer statDefId, Integer statTypeId) {
		this.statDefId = statDefId;
		this.statTypeId = statTypeId;
	}

	public void setPk(BaseStatDefResultVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.statDefId, aVo.statTypeId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getStatDefId() {
		return this.statDefId;
	}
	public void setStatDefId(Integer statDefId) {
		this.statDefId = statDefId;
	}

	public Integer getStatTypeId() {
		return this.statTypeId;
	}
	public void setStatTypeId(Integer statTypeId) {
		this.statTypeId = statTypeId;
	}

}
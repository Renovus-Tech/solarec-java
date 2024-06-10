package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseDataDefStatDefinitionVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_STAT_DEF_ID = "stat_def_id";
	public static final String COLUMN_STAT_DEF_CALL_ORDER = "stat_def_call_order";


	//--- Private properties --------------------
	private Integer dataDefId;
	private Integer statDefId;
	private Integer statDefCallOrder;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataDefId == null) {
			return false;
		}
		if (this.statDefId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataDefStatDefinitionVo)) return false;
		
		BaseDataDefStatDefinitionVo aObj = (BaseDataDefStatDefinitionVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataDefId != null) hashCode += this.dataDefId.hashCode();
		if (this.statDefId != null) hashCode += this.statDefId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataDefStatDefinitionVo aObj = (BaseDataDefStatDefinitionVo) obj;
		if (!ClassUtil.equals(this.statDefCallOrder,aObj.statDefCallOrder)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataDefId, Integer statDefId) {
		this.dataDefId = dataDefId;
		this.statDefId = statDefId;
	}

	public void setPk(BaseDataDefStatDefinitionVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.dataDefId, aVo.statDefId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Integer getStatDefId() {
		return this.statDefId;
	}
	public void setStatDefId(Integer statDefId) {
		this.statDefId = statDefId;
	}

	public Integer getStatDefCallOrder() {
		return this.statDefCallOrder;
	}
	public void setStatDefCallOrder(Integer statDefCallOrder) {
		this.statDefCallOrder = statDefCallOrder;
	}

}
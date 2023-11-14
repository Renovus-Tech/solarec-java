package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseDataDefAlertDefinitionVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	 public static final String COLUMN_ALERT_DEF_ID = "alert_def_id";
	 public static final String COLUMN_ALERT_DEF_CALL_ORDER = "alert_def_call_order";

	//--- Private properties --------------------
	 private Integer dataDefId;
	 private Integer alertDefId;
	 private Integer alertDefCallOrder;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataDefId == null) {
			return false;
		}
		if (this.alertDefId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataDefAlertDefinitionVo)) return false;
		
		BaseDataDefAlertDefinitionVo aObj = (BaseDataDefAlertDefinitionVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefId,aObj.alertDefId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataDefId != null) hashCode += this.dataDefId.hashCode();
		if (this.alertDefId != null) hashCode += this.alertDefId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataDefAlertDefinitionVo)) return false;
		
		BaseDataDefAlertDefinitionVo aObj = (BaseDataDefAlertDefinitionVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefId,aObj.alertDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefCallOrder,aObj.alertDefCallOrder)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataDefId, Integer alertDefId) {
		this.dataDefId = dataDefId;
		this.alertDefId = alertDefId;
	}

	public void setPk(BaseDataDefAlertDefinitionVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getDataDefId(), aVo.getAlertDefId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Integer getAlertDefId() {
		return this.alertDefId;
	}
	public void setAlertDefId(Integer alertDefId) {
		this.alertDefId = alertDefId;
	}

	public Integer getAlertDefCallOrder() {
		return this.alertDefCallOrder;
	}
	public void setAlertDefCallOrder(Integer alertDefCallOrder) {
		this.alertDefCallOrder = alertDefCallOrder;
	}

}
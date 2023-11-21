package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseDataDefAlertDefinitionVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_ALERT_DEF_ID = "alert_def_id";
	public static final String COLUMN_ALERT_DEF_CALL_ORDER = "alert_def_call_order";


	//--- Private properties --------------------
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter Integer alertDefId;
	private @Getter @Setter Integer alertDefCallOrder;

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

	//--- Overriden methods ---------------------
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
		if (! this.equals(obj)) return false;
		
		BaseDataDefAlertDefinitionVo aObj = (BaseDataDefAlertDefinitionVo) obj;
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
			this.setPk(aVo.dataDefId, aVo.alertDefId);
		}
	}

}
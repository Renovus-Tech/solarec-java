package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseDataProAlertProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	public static final String COLUMN_ALERT_PRO_ID = "alert_pro_id";


	//--- Private properties --------------------
	private Integer dataProId;
	private Integer alertProId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataProId == null) {
			return false;
		}
		if (this.alertProId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataProAlertProcessingVo)) return false;
		
		BaseDataProAlertProcessingVo aObj = (BaseDataProAlertProcessingVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProId,aObj.alertProId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataProId != null) hashCode += this.dataProId.hashCode();
		if (this.alertProId != null) hashCode += this.alertProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataProAlertProcessingVo aObj = (BaseDataProAlertProcessingVo) obj;
		return true;
	}

	public void setPk(Integer dataProId, Integer alertProId) {
		this.dataProId = dataProId;
		this.alertProId = alertProId;
	}

	public void setPk(BaseDataProAlertProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.dataProId, aVo.alertProId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataProId() {
		return this.dataProId;
	}
	public void setDataProId(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public Integer getAlertProId() {
		return this.alertProId;
	}
	public void setAlertProId(Integer alertProId) {
		this.alertProId = alertProId;
	}

}
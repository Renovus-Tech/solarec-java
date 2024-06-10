package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseLocAlertVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_ALERT_DEF_ID = "alert_def_id";
	public static final String COLUMN_ALERT_PRO_ID = "alert_pro_id";
	public static final String COLUMN_ALERT_DATE_ADDED = "alert_date_added";
	public static final String COLUMN_ALERT_DATE_SEND = "alert_date_send";
	public static final String COLUMN_ALERT_MESSAGE = "alert_message";

	public static final int LENGTH_COLUMN_ALERT_MESSAGE =  2000;

	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private Integer alertDefId;
	private Integer alertProId;
	private java.util.Date alertDateAdded;
	private java.util.Date alertDateSend;
	private String alertMessage;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.alertDefId == null) {
			return false;
		}
		if (this.alertProId == null) {
			return false;
		}
		if (this.alertDateAdded == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocAlertVo)) return false;
		
		BaseLocAlertVo aObj = (BaseLocAlertVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefId,aObj.alertDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDateAdded.getTime(),aObj.alertDateAdded.getTime())) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.alertDefId != null) hashCode += this.alertDefId.hashCode();
		if (this.alertDateAdded != null) hashCode += this.alertDateAdded.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocAlertVo aObj = (BaseLocAlertVo) obj;
		if (!ClassUtil.equals(this.alertProId,aObj.alertProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDateSend,aObj.alertDateSend)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertMessage,aObj.alertMessage)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer alertDefId, java.util.Date alertDateAdded) {
		this.cliId = cliId;
		this.locId = locId;
		this.alertDefId = alertDefId;
		this.alertDateAdded = alertDateAdded;
	}

	public void setPk(BaseLocAlertVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.alertDefId, aVo.alertDateAdded);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getAlertDefId() {
		return this.alertDefId;
	}
	public void setAlertDefId(Integer alertDefId) {
		this.alertDefId = alertDefId;
	}

	public Integer getAlertProId() {
		return this.alertProId;
	}
	public void setAlertProId(Integer alertProId) {
		this.alertProId = alertProId;
	}

	public java.util.Date getAlertDateAdded() {
		return this.alertDateAdded;
	}
	public void setAlertDateAdded(java.util.Date alertDateAdded) {
		this.alertDateAdded = alertDateAdded;
	}

	public java.util.Date getAlertDateSend() {
		return this.alertDateSend;
	}
	public void setAlertDateSend(java.util.Date alertDateSend) {
		this.alertDateSend = alertDateSend;
	}

	public String getAlertMessage() {
		return this.alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}

}
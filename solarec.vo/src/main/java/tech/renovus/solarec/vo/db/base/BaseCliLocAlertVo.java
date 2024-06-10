package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseCliLocAlertVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_LOC_ALERT_TRIGGER = "cli_loc_alert_trigger";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_CLI_LOC_ALERT_ID = "cli_loc_alert_id_auto";
	public static final String COLUMN_CLI_LOC_ALERT_ADDED = "cli_loc_alert_added";
	public static final String COLUMN_CLI_LOC_ALERT_TYPE = "cli_loc_alert_type";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_CLI_LOC_ALERT_FLAGS = "cli_loc_alert_flags";
	public static final String COLUMN_CLI_LOC_ALERT_DATA = "cli_loc_alert_data";

	public static final int LENGTH_COLUMN_CLI_LOC_ALERT_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.cliLocAlertFlags; }
	@Override public void setFlags(String cliLocAlertFlags) { this.cliLocAlertFlags = cliLocAlertFlags; }

	//--- Private properties --------------------
	private java.util.Date cliLocAlertTrigger;
	private Integer locId;
	private Integer cliLocAlertId;
	private java.util.Date cliLocAlertAdded;
	private Integer cliLocAlertType;
	private Integer cliId;
	private String cliLocAlertFlags;
	private String cliLocAlertData;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.locId == null) {
			return false;
		}
		if (this.cliLocAlertId == null) {
			return false;
		}
		if (this.cliLocAlertAdded == null) {
			return false;
		}
		if (this.cliLocAlertType == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliLocAlertVo)) return false;
		
		BaseCliLocAlertVo aObj = (BaseCliLocAlertVo) obj;
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertId,aObj.cliLocAlertId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.cliLocAlertId != null) hashCode += this.cliLocAlertId.hashCode();
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliLocAlertVo aObj = (BaseCliLocAlertVo) obj;
		if (!ClassUtil.equals(this.cliLocAlertTrigger,aObj.cliLocAlertTrigger)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertAdded,aObj.cliLocAlertAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertType,aObj.cliLocAlertType)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertFlags,aObj.cliLocAlertFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertData,aObj.cliLocAlertData)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer locId, Integer cliLocAlertId, Integer cliId) {
		this.locId = locId;
		this.cliLocAlertId = cliLocAlertId;
		this.cliId = cliId;
	}

	public void setPk(BaseCliLocAlertVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.locId, aVo.cliLocAlertId, aVo.cliId);
		}
	}

	//--- Getters and Setters -------------------
	public java.util.Date getCliLocAlertTrigger() {
		return this.cliLocAlertTrigger;
	}
	public void setCliLocAlertTrigger(java.util.Date cliLocAlertTrigger) {
		this.cliLocAlertTrigger = cliLocAlertTrigger;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getCliLocAlertId() {
		return this.cliLocAlertId;
	}
	public void setCliLocAlertId(Integer cliLocAlertId) {
		this.cliLocAlertId = cliLocAlertId;
	}

	public java.util.Date getCliLocAlertAdded() {
		return this.cliLocAlertAdded;
	}
	public void setCliLocAlertAdded(java.util.Date cliLocAlertAdded) {
		this.cliLocAlertAdded = cliLocAlertAdded;
	}

	public Integer getCliLocAlertType() {
		return this.cliLocAlertType;
	}
	public void setCliLocAlertType(Integer cliLocAlertType) {
		this.cliLocAlertType = cliLocAlertType;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCliLocAlertFlags() {
		return this.cliLocAlertFlags;
	}
	public void setCliLocAlertFlags(String cliLocAlertFlags) {
		this.cliLocAlertFlags = cliLocAlertFlags;
	}

	public String getCliLocAlertData() {
		return this.cliLocAlertData;
	}
	public void setCliLocAlertData(String cliLocAlertData) {
		this.cliLocAlertData = cliLocAlertData;
	}

}
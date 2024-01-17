package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseCliGenAlertVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_GEN_ALERT_TRIGGER = "cli_gen_alert_trigger";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_CLI_GEN_ALERT_ID = "cli_gen_alert_id_auto";
	public static final String COLUMN_CLI_GEN_ALERT_ADDED = "cli_gen_alert_added";
	public static final String COLUMN_CLI_GEN_ALERT_TYPE = "cli_gen_alert_type";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_CLI_GEN_ALERT_FLAGS = "cli_gen_alert_flags";
	public static final String COLUMN_CLI_GEN_ALERT_DATA = "cli_gen_alert_data";

	public static final int LENGTH_COLUMN_CLI_GEN_ALERT_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.cliGenAlertFlags; }
	@Override public void setFlags(String cliGenAlertFlags) { this.cliGenAlertFlags = cliGenAlertFlags; }

	//--- Private properties --------------------
	private java.util.Date cliGenAlertTrigger;
	private Integer genId;
	private Integer cliGenAlertId;
	private java.util.Date cliGenAlertAdded;
	private Integer cliGenAlertType;
	private Integer cliId;
	private String cliGenAlertFlags;
	private String cliGenAlertData;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.genId == null) {
			return false;
		}
		if (this.cliGenAlertId == null) {
			return false;
		}
		if (this.cliGenAlertAdded == null) {
			return false;
		}
		if (this.cliGenAlertType == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliGenAlertVo)) return false;
		
		BaseCliGenAlertVo aObj = (BaseCliGenAlertVo) obj;
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenAlertId,aObj.cliGenAlertId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.cliGenAlertId != null) hashCode += this.cliGenAlertId.hashCode();
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliGenAlertVo aObj = (BaseCliGenAlertVo) obj;
		if (!ClassUtil.equals(this.cliGenAlertTrigger,aObj.cliGenAlertTrigger)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenAlertAdded,aObj.cliGenAlertAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenAlertType,aObj.cliGenAlertType)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenAlertFlags,aObj.cliGenAlertFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenAlertData,aObj.cliGenAlertData)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer genId, Integer cliGenAlertId, Integer cliId) {
		this.genId = genId;
		this.cliGenAlertId = cliGenAlertId;
		this.cliId = cliId;
	}

	public void setPk(BaseCliGenAlertVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.genId, aVo.cliGenAlertId, aVo.cliId);
		}
	}

	//--- Getters and Setters -------------------
	public java.util.Date getCliGenAlertTrigger() {
		return this.cliGenAlertTrigger;
	}
	public void setCliGenAlertTrigger(java.util.Date cliGenAlertTrigger) {
		this.cliGenAlertTrigger = cliGenAlertTrigger;
	}

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public Integer getCliGenAlertId() {
		return this.cliGenAlertId;
	}
	public void setCliGenAlertId(Integer cliGenAlertId) {
		this.cliGenAlertId = cliGenAlertId;
	}

	public java.util.Date getCliGenAlertAdded() {
		return this.cliGenAlertAdded;
	}
	public void setCliGenAlertAdded(java.util.Date cliGenAlertAdded) {
		this.cliGenAlertAdded = cliGenAlertAdded;
	}

	public Integer getCliGenAlertType() {
		return this.cliGenAlertType;
	}
	public void setCliGenAlertType(Integer cliGenAlertType) {
		this.cliGenAlertType = cliGenAlertType;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCliGenAlertFlags() {
		return this.cliGenAlertFlags;
	}
	public void setCliGenAlertFlags(String cliGenAlertFlags) {
		this.cliGenAlertFlags = cliGenAlertFlags;
	}

	public String getCliGenAlertData() {
		return this.cliGenAlertData;
	}
	public void setCliGenAlertData(String cliGenAlertData) {
		this.cliGenAlertData = cliGenAlertData;
	}

}
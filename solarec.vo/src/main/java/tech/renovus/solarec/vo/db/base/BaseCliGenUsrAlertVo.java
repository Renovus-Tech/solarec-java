package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseCliGenUsrAlertVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_USR_ID = "usr_id";
	public static final String COLUMN_CLI_GEN_ALERT_ID = "cli_gen_alert_id";
	public static final String COLUMN_CLI_GEN_USR_ALERT_SEND_DATE = "cli_gen_usr_alert_send_date";
	public static final String COLUMN_CLI_GEN_USR_ALERT_SEND_RESULT = "cli_gen_usr_alert_send_result";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer genId;
	private Integer usrId;
	private Integer cliGenAlertId;
	private java.util.Date cliGenUsrAlertSendDate;
	private String cliGenUsrAlertSendResult;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		if (this.usrId == null) {
			return false;
		}
		if (this.cliGenAlertId == null) {
			return false;
		}
		if (this.cliGenUsrAlertSendDate == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliGenUsrAlertVo)) return false;
		
		BaseCliGenUsrAlertVo aObj = (BaseCliGenUsrAlertVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenAlertId,aObj.cliGenAlertId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		if (this.cliGenAlertId != null) hashCode += this.cliGenAlertId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliGenUsrAlertVo aObj = (BaseCliGenUsrAlertVo) obj;
		if (!ClassUtil.equals(this.cliGenUsrAlertSendDate,aObj.cliGenUsrAlertSendDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGenUsrAlertSendResult,aObj.cliGenUsrAlertSendResult)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId) {
		this.cliId = cliId;
		this.genId = genId;
		this.usrId = usrId;
		this.cliGenAlertId = cliGenAlertId;
	}

	public void setPk(BaseCliGenUsrAlertVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.genId, aVo.usrId, aVo.cliGenAlertId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public Integer getUsrId() {
		return this.usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public Integer getCliGenAlertId() {
		return this.cliGenAlertId;
	}
	public void setCliGenAlertId(Integer cliGenAlertId) {
		this.cliGenAlertId = cliGenAlertId;
	}

	public java.util.Date getCliGenUsrAlertSendDate() {
		return this.cliGenUsrAlertSendDate;
	}
	public void setCliGenUsrAlertSendDate(java.util.Date cliGenUsrAlertSendDate) {
		this.cliGenUsrAlertSendDate = cliGenUsrAlertSendDate;
	}

	public String getCliGenUsrAlertSendResult() {
		return this.cliGenUsrAlertSendResult;
	}
	public void setCliGenUsrAlertSendResult(String cliGenUsrAlertSendResult) {
		this.cliGenUsrAlertSendResult = cliGenUsrAlertSendResult;
	}

}
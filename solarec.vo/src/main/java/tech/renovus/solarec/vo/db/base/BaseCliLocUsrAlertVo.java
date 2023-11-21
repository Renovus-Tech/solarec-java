package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseCliLocUsrAlertVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_USR_ID = "usr_id";
	public static final String COLUMN_CLI_LOC_ALERT_ID = "cli_loc_alert_id";
	public static final String COLUMN_CLI_LOC_USR_ALERT_SEND_DATE = "cli_loc_usr_alert_send_date";
	public static final String COLUMN_CLI_LOC_USR_ALERT_SEND_RESULT = "cli_loc_usr_alert_send_result";


	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer usrId;
	private @Getter @Setter Integer cliLocAlertId;
	private @Getter @Setter java.util.Date cliLocUsrAlertSendDate;
	private @Getter @Setter String cliLocUsrAlertSendResult;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.usrId == null) {
			return false;
		}
		if (this.cliLocAlertId == null) {
			return false;
		}
		if (this.cliLocUsrAlertSendDate == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliLocUsrAlertVo)) return false;
		
		BaseCliLocUsrAlertVo aObj = (BaseCliLocUsrAlertVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertId,aObj.cliLocAlertId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		if (this.cliLocAlertId != null) hashCode += this.cliLocAlertId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliLocUsrAlertVo aObj = (BaseCliLocUsrAlertVo) obj;
		if (!ClassUtil.equals(this.cliLocUsrAlertSendDate,aObj.cliLocUsrAlertSendDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocUsrAlertSendResult,aObj.cliLocUsrAlertSendResult)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId) {
		this.cliId = cliId;
		this.locId = locId;
		this.usrId = usrId;
		this.cliLocAlertId = cliLocAlertId;
	}

	public void setPk(BaseCliLocUsrAlertVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.usrId, aVo.cliLocAlertId);
		}
	}

}
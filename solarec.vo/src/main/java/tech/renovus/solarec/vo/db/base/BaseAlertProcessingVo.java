package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseAlertProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_ALERT_PRO_RESULT = "alert_pro_result";
	public static final String COLUMN_ALERT_PRO_ID = "alert_pro_id_auto";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_ALERT_DEF_ID = "alert_def_id";
	public static final String COLUMN_ALERT_PRO_DATE_START = "alert_pro_date_start";
	public static final String COLUMN_ALERT_PRO_DATE_END = "alert_pro_date_end";
	public static final String COLUMN_ALERT_PRO_FILE_LOG = "alert_pro_file_log";
	public static final String COLUMN_ALERT_PRO_FILE_NAME = "alert_pro_file_name";

	public static final int LENGTH_COLUMN_ALERT_PRO_FILE_LOG =  100;
	public static final int LENGTH_COLUMN_ALERT_PRO_FILE_NAME =  100;

	//--- Private properties --------------------
	private @Getter @Setter Integer alertProResult;
	private @Getter @Setter Integer alertProId;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer alertDefId;
	private @Getter @Setter java.util.Date alertProDateStart;
	private @Getter @Setter java.util.Date alertProDateEnd;
	private @Getter @Setter String alertProFileLog;
	private @Getter @Setter String alertProFileName;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.alertProId == null) {
			return false;
		}
		if (this.cliId == null) {
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
		if (!(obj instanceof BaseAlertProcessingVo)) return false;
		
		BaseAlertProcessingVo aObj = (BaseAlertProcessingVo) obj;
		if (!ClassUtil.equals(this.alertProId,aObj.alertProId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.alertProId != null) hashCode += this.alertProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseAlertProcessingVo aObj = (BaseAlertProcessingVo) obj;
		if (!ClassUtil.equals(this.alertProResult,aObj.alertProResult)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertDefId,aObj.alertDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProDateStart,aObj.alertProDateStart)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProDateEnd,aObj.alertProDateEnd)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProFileLog,aObj.alertProFileLog)) {
			return false;
		}
		if (!ClassUtil.equals(this.alertProFileName,aObj.alertProFileName)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer alertProId) {
		this.alertProId = alertProId;
	}

	public void setPk(BaseAlertProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.alertProId);
		}
	}

}
package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseCliLocAlertVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_CLI_LOC_ALERT_ID = "cli_loc_alert_id_auto";
	public static final String COLUMN_CLI_LOC_ALERT_ADDED = "cli_loc_alert_added";
	public static final String COLUMN_CLI_LOC_ALERT_TYPE = "cli_loc_alert_type";
	public static final String COLUMN_CLI_LOC_ALERT_DATA = "cli_loc_alert_data";
	public static final String COLUMN_CLI_LOC_ALERT_FLAGS = "cli_loc_alert_flags";

	public static final int LENGTH_COLUMN_CLI_LOC_ALERT_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.cliLocAlertFlags; }
	@Override public void setFlags(String cliLocAlertFlags) { this.cliLocAlertFlags = cliLocAlertFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer cliLocAlertId;
	private @Getter @Setter java.util.Date cliLocAlertAdded;
	private @Getter @Setter Integer cliLocAlertType;
	private @Getter @Setter String cliLocAlertData;
	private @Getter @Setter String cliLocAlertFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
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
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliLocAlertVo)) return false;
		
		BaseCliLocAlertVo aObj = (BaseCliLocAlertVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
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
		if (this.cliLocAlertId != null) hashCode += this.cliLocAlertId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliLocAlertVo aObj = (BaseCliLocAlertVo) obj;
		if (!ClassUtil.equals(this.cliLocAlertAdded,aObj.cliLocAlertAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertType,aObj.cliLocAlertType)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertData,aObj.cliLocAlertData)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliLocAlertFlags,aObj.cliLocAlertFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer cliLocAlertId) {
		this.cliId = cliId;
		this.locId = locId;
		this.cliLocAlertId = cliLocAlertId;
	}

	public void setPk(BaseCliLocAlertVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.cliLocAlertId);
		}
	}

}
package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseCliLocUsrSettingVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_USR_ID = "usr_id";
	public static final String COLUMN_CLI_LOC_ALERT_FLAGS = "cli_loc_alert_flags";

	public static final int LENGTH_COLUMN_CLI_LOC_ALERT_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.cliLocAlertFlags; }
	@Override public void setFlags(String cliLocAlertFlags) { this.cliLocAlertFlags = cliLocAlertFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer usrId;
	private @Getter @Setter String cliLocAlertFlags;

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
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliLocUsrSettingVo)) return false;
		
		BaseCliLocUsrSettingVo aObj = (BaseCliLocUsrSettingVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliLocUsrSettingVo aObj = (BaseCliLocUsrSettingVo) obj;
		if (!ClassUtil.equals(this.cliLocAlertFlags,aObj.cliLocAlertFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer usrId) {
		this.cliId = cliId;
		this.locId = locId;
		this.usrId = usrId;
	}

	public void setPk(BaseCliLocUsrSettingVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.usrId);
		}
	}

}
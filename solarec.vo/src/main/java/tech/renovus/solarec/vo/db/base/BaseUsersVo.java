package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseUsersVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_USR_ID = "usr_id_auto";
	public static final String COLUMN_USR_DATE_LOGIN = "usr_date_login";
	public static final String COLUMN_USR_DATE_ADDED = "usr_date_added";
	public static final String COLUMN_USR_DATE_LOCKED = "usr_date_locked";
	public static final String COLUMN_USR_PWD_RESET_REQUESTED = "usr_pwd_reset_requested";
	public static final String COLUMN_USR_PWD_RESET_UUID = "usr_pwd_reset_uuid";
	public static final String COLUMN_USR_COMMENTS = "usr_comments";
	public static final String COLUMN_USR_EMAIL = "usr_email";
	public static final String COLUMN_USR_NAME = "usr_name";
	public static final String COLUMN_USR_FLAGS = "usr_flags";
	public static final String COLUMN_USR_PASSWORD = "usr_password";

	public static final int LENGTH_COLUMN_USR_PWD_RESET_UUID =  500;
	public static final int LENGTH_COLUMN_USR_COMMENTS =  500;
	public static final int LENGTH_COLUMN_USR_EMAIL =  500;
	public static final int LENGTH_COLUMN_USR_NAME =  100;
	public static final int LENGTH_COLUMN_USR_FLAGS =  20;
	public static final int LENGTH_COLUMN_USR_PASSWORD =  500;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.usrFlags; }
	@Override public void setFlags(String usrFlags) { this.usrFlags = usrFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer usrId;
	private @Getter @Setter java.util.Date usrDateLogin;
	private @Getter @Setter java.util.Date usrDateAdded;
	private @Getter @Setter java.util.Date usrDateLocked;
	private @Getter @Setter java.util.Date usrPwdResetRequested;
	private @Getter @Setter String usrPwdResetUuid;
	private @Getter @Setter String usrComments;
	private @Getter @Setter String usrEmail;
	private @Getter @Setter String usrName;
	private @Getter @Setter String usrFlags;
	private @Getter @Setter String usrPassword;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.usrId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseUsersVo)) return false;
		
		BaseUsersVo aObj = (BaseUsersVo) obj;
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseUsersVo aObj = (BaseUsersVo) obj;
		if (!ClassUtil.equals(this.usrDateLogin,aObj.usrDateLogin)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrDateAdded,aObj.usrDateAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrDateLocked,aObj.usrDateLocked)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrPwdResetRequested,aObj.usrPwdResetRequested)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrPwdResetUuid,aObj.usrPwdResetUuid)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrComments,aObj.usrComments)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrEmail,aObj.usrEmail)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrName,aObj.usrName)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrFlags,aObj.usrFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrPassword,aObj.usrPassword)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer usrId) {
		this.usrId = usrId;
	}

	public void setPk(BaseUsersVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.usrId);
		}
	}

}
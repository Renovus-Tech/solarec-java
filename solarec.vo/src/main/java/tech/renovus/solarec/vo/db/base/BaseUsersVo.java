package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@javax.annotation.Generated(value = "Renovus") public class BaseUsersVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_USR_ID = "usr_id_auto";
	public static final String COLUMN_USR_DATE_LOGIN = "usr_date_login";
	public static final String COLUMN_USR_DATE_ADDED = "usr_date_added";
	public static final String COLUMN_USR_DATE_LOCKED = "usr_date_locked";
	public static final String COLUMN_USR_PWD_RESET_REQUESTED = "usr_pwd_reset_requested";
	public static final String COLUMN_USR_COMMENTS = "usr_comments";
	public static final String COLUMN_USR_CERT_PROV_DATA = "usr_cert_prov_data";
	public static final String COLUMN_USR_PWD_RESET_UUID = "usr_pwd_reset_uuid";
	public static final String COLUMN_USR_EMAIL = "usr_email";
	public static final String COLUMN_USR_NAME = "usr_name";
	public static final String COLUMN_USR_FLAGS = "usr_flags";
	public static final String COLUMN_USR_PASSWORD = "usr_password";

	public static final int LENGTH_COLUMN_USR_COMMENTS =  500;
	public static final int LENGTH_COLUMN_USR_CERT_PROV_DATA =  2000;
	public static final int LENGTH_COLUMN_USR_PWD_RESET_UUID =  500;
	public static final int LENGTH_COLUMN_USR_EMAIL =  500;
	public static final int LENGTH_COLUMN_USR_NAME =  100;
	public static final int LENGTH_COLUMN_USR_FLAGS =  20;
	public static final int LENGTH_COLUMN_USR_PASSWORD =  500;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.usrFlags; }
	@Override public void setFlags(String usrFlags) { this.usrFlags = usrFlags; }

	//--- Private properties --------------------
	private Integer usrId;
	private java.util.Date usrDateLogin;
	private java.util.Date usrDateAdded;
	private java.util.Date usrDateLocked;
	private java.util.Date usrPwdResetRequested;
	private String usrComments;
	private String usrCertProvData;
	private String usrPwdResetUuid;
	private String usrEmail;
	private String usrName;
	private String usrFlags;
	private String usrPassword;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.usrId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseUsersVo)) return false;
		
		BaseUsersVo aObj = (BaseUsersVo) obj;
		return ClassUtil.equals(this.usrId,aObj.usrId);
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
		if (!ClassUtil.equals(this.usrComments,aObj.usrComments)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrCertProvData,aObj.usrCertProvData)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrPwdResetUuid,aObj.usrPwdResetUuid)) {
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
			this.setPk(aVo.usrId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getUsrId() {
		return this.usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public java.util.Date getUsrDateLogin() {
		return this.usrDateLogin;
	}
	public void setUsrDateLogin(java.util.Date usrDateLogin) {
		this.usrDateLogin = usrDateLogin;
	}

	public java.util.Date getUsrDateAdded() {
		return this.usrDateAdded;
	}
	public void setUsrDateAdded(java.util.Date usrDateAdded) {
		this.usrDateAdded = usrDateAdded;
	}

	public java.util.Date getUsrDateLocked() {
		return this.usrDateLocked;
	}
	public void setUsrDateLocked(java.util.Date usrDateLocked) {
		this.usrDateLocked = usrDateLocked;
	}

	public java.util.Date getUsrPwdResetRequested() {
		return this.usrPwdResetRequested;
	}
	public void setUsrPwdResetRequested(java.util.Date usrPwdResetRequested) {
		this.usrPwdResetRequested = usrPwdResetRequested;
	}

	public String getUsrComments() {
		return this.usrComments;
	}
	public void setUsrComments(String usrComments) {
		this.usrComments = usrComments;
	}

	public String getUsrCertProvData() {
		return this.usrCertProvData;
	}
	public void setUsrCertProvData(String usrCertProvData) {
		this.usrCertProvData = usrCertProvData;
	}

	public String getUsrPwdResetUuid() {
		return this.usrPwdResetUuid;
	}
	public void setUsrPwdResetUuid(String usrPwdResetUuid) {
		this.usrPwdResetUuid = usrPwdResetUuid;
	}

	public String getUsrEmail() {
		return this.usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getUsrName() {
		return this.usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrFlags() {
		return this.usrFlags;
	}
	public void setUsrFlags(String usrFlags) {
		this.usrFlags = usrFlags;
	}

	public String getUsrPassword() {
		return this.usrPassword;
	}
	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

}
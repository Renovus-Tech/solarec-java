package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseCliUserVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_USR_ID = "usr_id";
	 public static final String COLUMN_CLI_USER_DATE_ADDED = "cli_user_date_added";
	 public static final String COLUMN_CLI_USER_DATE_ACCESS = "cli_user_date_access";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer usrId;
	 private java.util.Date cliUserDateAdded;
	 private java.util.Date cliUserDateAccess;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.usrId == null) {
			return false;
		}
		if (this.cliUserDateAdded == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliUserVo)) return false;
		
		BaseCliUserVo aObj = (BaseCliUserVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
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
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliUserVo)) return false;
		
		BaseCliUserVo aObj = (BaseCliUserVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliUserDateAdded,aObj.cliUserDateAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliUserDateAccess,aObj.cliUserDateAccess)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer usrId) {
		this.cliId = cliId;
		this.usrId = usrId;
	}

	public void setPk(BaseCliUserVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getUsrId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getUsrId() {
		return this.usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public java.util.Date getCliUserDateAdded() {
		return this.cliUserDateAdded;
	}
	public void setCliUserDateAdded(java.util.Date cliUserDateAdded) {
		this.cliUserDateAdded = cliUserDateAdded;
	}

	public java.util.Date getCliUserDateAccess() {
		return this.cliUserDateAccess;
	}
	public void setCliUserDateAccess(java.util.Date cliUserDateAccess) {
		this.cliUserDateAccess = cliUserDateAccess;
	}

}
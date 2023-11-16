package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseLocUsrRepTypeVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_USR_ID = "usr_id";
	 public static final String COLUMN_REP_TYPE_ID = "rep_type_id";
	 public static final String COLUMN_LOC_USR_REP_TYPE_FLAGS = "loc_usr_rep_type_flags";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.locUsrRepTypeFlags; }
	@Override public void setFlags(String locUsrRepTypeFlags) { this.locUsrRepTypeFlags = locUsrRepTypeFlags; }

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private Integer usrId;
	 private Integer repTypeId;
	 private String locUsrRepTypeFlags;

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
		if (this.repTypeId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocUsrRepTypeVo)) return false;
		
		BaseLocUsrRepTypeVo aObj = (BaseLocUsrRepTypeVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.repTypeId,aObj.repTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		if (this.repTypeId != null) hashCode += this.repTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocUsrRepTypeVo)) return false;
		
		BaseLocUsrRepTypeVo aObj = (BaseLocUsrRepTypeVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.repTypeId,aObj.repTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locUsrRepTypeFlags,aObj.locUsrRepTypeFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer usrId, Integer repTypeId) {
		this.cliId = cliId;
		this.locId = locId;
		this.usrId = usrId;
		this.repTypeId = repTypeId;
	}

	public void setPk(BaseLocUsrRepTypeVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getUsrId(), aVo.getRepTypeId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getUsrId() {
		return this.usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public Integer getRepTypeId() {
		return this.repTypeId;
	}
	public void setRepTypeId(Integer repTypeId) {
		this.repTypeId = repTypeId;
	}

	public String getLocUsrRepTypeFlags() {
		return this.locUsrRepTypeFlags;
	}
	public void setLocUsrRepTypeFlags(String locUsrRepTypeFlags) {
		this.locUsrRepTypeFlags = locUsrRepTypeFlags;
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseProfileVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_PRF_ID = "prf_id_auto";
	 public static final String COLUMN_PRF_NAME = "prf_name";
	 public static final String COLUMN_PRF_DESCRIPTION = "prf_description";
	 public static final String COLUMN_PRF_FLAGS = "prf_flags";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.prfFlags; }
	@Override public void setFlags(String prfFlags) { this.prfFlags = prfFlags; }

	//--- Private properties --------------------
	 private Integer prfId;
	 private String prfName;
	 private String prfDescription;
	 private String prfFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.prfId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseProfileVo)) return false;
		
		BaseProfileVo aObj = (BaseProfileVo) obj;
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.prfId != null) hashCode += this.prfId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseProfileVo)) return false;
		
		BaseProfileVo aObj = (BaseProfileVo) obj;
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		if (!ClassUtil.equals(this.prfName,aObj.prfName)) {
			return false;
		}
		if (!ClassUtil.equals(this.prfDescription,aObj.prfDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.prfFlags,aObj.prfFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer prfId) {
		this.prfId = prfId;
	}

	public void setPk(BaseProfileVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.getPrfId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getPrfId() {
		return this.prfId;
	}
	public void setPrfId(Integer prfId) {
		this.prfId = prfId;
	}

	public String getPrfName() {
		return this.prfName;
	}
	public void setPrfName(String prfName) {
		this.prfName = prfName;
	}

	public String getPrfDescription() {
		return this.prfDescription;
	}
	public void setPrfDescription(String prfDescription) {
		this.prfDescription = prfDescription;
	}

	public String getPrfFlags() {
		return this.prfFlags;
	}
	public void setPrfFlags(String prfFlags) {
		this.prfFlags = prfFlags;
	}

}
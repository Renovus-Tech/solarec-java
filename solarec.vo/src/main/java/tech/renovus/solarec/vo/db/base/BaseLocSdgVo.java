package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseLocSdgVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_SDG_ID = "sdg_id";
	public static final String COLUMN_LOC_SDG_DESCRIPTION = "loc_sdg_description";

	public static final int LENGTH_COLUMN_LOC_SDG_DESCRIPTION =  1000;

	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private Integer sdgId;
	private String locSdgDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.sdgId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocSdgVo)) return false;
		
		BaseLocSdgVo aObj = (BaseLocSdgVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.sdgId,aObj.sdgId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.sdgId != null) hashCode += this.sdgId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocSdgVo aObj = (BaseLocSdgVo) obj;
		if (!ClassUtil.equals(this.locSdgDescription,aObj.locSdgDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer sdgId) {
		this.cliId = cliId;
		this.locId = locId;
		this.sdgId = sdgId;
	}

	public void setPk(BaseLocSdgVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.sdgId);
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

	public Integer getSdgId() {
		return this.sdgId;
	}
	public void setSdgId(Integer sdgId) {
		this.sdgId = sdgId;
	}

	public String getLocSdgDescription() {
		return this.locSdgDescription;
	}
	public void setLocSdgDescription(String locSdgDescription) {
		this.locSdgDescription = locSdgDescription;
	}

}
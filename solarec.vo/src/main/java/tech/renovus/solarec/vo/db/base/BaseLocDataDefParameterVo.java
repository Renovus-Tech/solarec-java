package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseLocDataDefParameterVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_DATA_DEF_PAR_ID = "data_def_par_id";
	public static final String COLUMN_LOC_DATA_DEF_PAR_VALUE = "loc_data_def_par_value";

	public static final int LENGTH_COLUMN_LOC_DATA_DEF_PAR_VALUE =  255;

	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private Integer dataDefId;
	private Integer dataDefParId;
	private String locDataDefParValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.dataDefId == null) {
			return false;
		}
		if (this.dataDefParId == null) {
			return false;
		}
		if (this.locDataDefParValue == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocDataDefParameterVo)) return false;
		
		BaseLocDataDefParameterVo aObj = (BaseLocDataDefParameterVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefParId,aObj.dataDefParId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.dataDefId != null) hashCode += this.dataDefId.hashCode();
		if (this.dataDefParId != null) hashCode += this.dataDefParId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocDataDefParameterVo aObj = (BaseLocDataDefParameterVo) obj;
		if (!ClassUtil.equals(this.locDataDefParValue,aObj.locDataDefParValue)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer dataDefId, Integer dataDefParId) {
		this.cliId = cliId;
		this.locId = locId;
		this.dataDefId = dataDefId;
		this.dataDefParId = dataDefParId;
	}

	public void setPk(BaseLocDataDefParameterVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.dataDefId, aVo.dataDefParId);
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

	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Integer getDataDefParId() {
		return this.dataDefParId;
	}
	public void setDataDefParId(Integer dataDefParId) {
		this.dataDefParId = dataDefParId;
	}

	public String getLocDataDefParValue() {
		return this.locDataDefParValue;
	}
	public void setLocDataDefParValue(String locDataDefParValue) {
		this.locDataDefParValue = locDataDefParValue;
	}

}
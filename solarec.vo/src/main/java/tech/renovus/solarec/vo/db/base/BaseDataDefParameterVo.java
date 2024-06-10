package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseDataDefParameterVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_DATA_DEF_PAR_ID = "data_def_par_id_auto";
	public static final String COLUMN_DATA_DEF_PAR_NAME = "data_def_par_name";
	public static final String COLUMN_DATA_DEF_DESCRIPTION = "data_def_description";

	public static final int LENGTH_COLUMN_DATA_DEF_PAR_NAME =  100;
	public static final int LENGTH_COLUMN_DATA_DEF_DESCRIPTION =  500;

	//--- Private properties --------------------
	private Integer dataDefId;
	private Integer dataDefParId;
	private String dataDefParName;
	private String dataDefDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataDefId == null) {
			return false;
		}
		if (this.dataDefParId == null) {
			return false;
		}
		if (this.dataDefParName == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataDefParameterVo)) return false;
		
		BaseDataDefParameterVo aObj = (BaseDataDefParameterVo) obj;
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
		if (this.dataDefId != null) hashCode += this.dataDefId.hashCode();
		if (this.dataDefParId != null) hashCode += this.dataDefParId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataDefParameterVo aObj = (BaseDataDefParameterVo) obj;
		if (!ClassUtil.equals(this.dataDefParName,aObj.dataDefParName)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefDescription,aObj.dataDefDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataDefId, Integer dataDefParId) {
		this.dataDefId = dataDefId;
		this.dataDefParId = dataDefParId;
	}

	public void setPk(BaseDataDefParameterVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.dataDefId, aVo.dataDefParId);
		}
	}

	//--- Getters and Setters -------------------
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

	public String getDataDefParName() {
		return this.dataDefParName;
	}
	public void setDataDefParName(String dataDefParName) {
		this.dataDefParName = dataDefParName;
	}

	public String getDataDefDescription() {
		return this.dataDefDescription;
	}
	public void setDataDefDescription(String dataDefDescription) {
		this.dataDefDescription = dataDefDescription;
	}

}
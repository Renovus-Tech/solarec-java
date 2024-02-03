package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseGenDataDefParameterVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_DATA_DEF_PAR_ID = "data_def_par_id";
	public static final String COLUMN_CLI_DATA_DEF_PAR_VALUE = "cli_data_def_par_value";

	public static final int LENGTH_COLUMN_CLI_DATA_DEF_PAR_VALUE =  255;

	//--- Private properties --------------------
	private Integer cliId;
	private Integer genId;
	private Integer dataDefId;
	private Integer dataDefParId;
	private String cliDataDefParValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		if (this.dataDefId == null) {
			return false;
		}
		if (this.dataDefParId == null) {
			return false;
		}
		if (this.cliDataDefParValue == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGenDataDefParameterVo)) return false;
		
		BaseGenDataDefParameterVo aObj = (BaseGenDataDefParameterVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
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
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.dataDefId != null) hashCode += this.dataDefId.hashCode();
		if (this.dataDefParId != null) hashCode += this.dataDefParId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseGenDataDefParameterVo aObj = (BaseGenDataDefParameterVo) obj;
		if (!ClassUtil.equals(this.cliDataDefParValue,aObj.cliDataDefParValue)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId, Integer dataDefId, Integer dataDefParId) {
		this.cliId = cliId;
		this.genId = genId;
		this.dataDefId = dataDefId;
		this.dataDefParId = dataDefParId;
	}

	public void setPk(BaseGenDataDefParameterVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.genId, aVo.dataDefId, aVo.dataDefParId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
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

	public String getCliDataDefParValue() {
		return this.cliDataDefParValue;
	}
	public void setCliDataDefParValue(String cliDataDefParValue) {
		this.cliDataDefParValue = cliDataDefParValue;
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseCliDataDefTriggerVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_TRI_ID = "tri_id_auto";
	public static final String COLUMN_STA_ID = "sta_id";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_TRI_SOURCE = "tri_source";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_TRI_NAME = "tri_name";
	public static final String COLUMN_TRI_VALUE = "tri_value";
	public static final String COLUMN_TRI_FLAGS = "tri_flags";

	public static final int LENGTH_COLUMN_TRI_NAME =  255;
	public static final int LENGTH_COLUMN_TRI_VALUE =  200;
	public static final int LENGTH_COLUMN_TRI_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.triFlags; }
	@Override public void setFlags(String triFlags) { this.triFlags = triFlags; }

	//--- Private properties --------------------
	private Integer triId;
	private Integer staId;
	private Integer dataDefId;
	private Integer triSource;
	private Integer cliId;
	private Integer locId;
	private Integer genId;
	private String triName;
	private String triValue;
	private String triFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.triId == null) {
			return false;
		}
		if (this.dataDefId == null) {
			return false;
		}
		if (this.triSource == null) {
			return false;
		}
		if (this.triName == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseCliDataDefTriggerVo)) return false;
		
		BaseCliDataDefTriggerVo aObj = (BaseCliDataDefTriggerVo) obj;
		if (!ClassUtil.equals(this.triId,aObj.triId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.triId != null) hashCode += this.triId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseCliDataDefTriggerVo aObj = (BaseCliDataDefTriggerVo) obj;
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.triSource,aObj.triSource)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.triName,aObj.triName)) {
			return false;
		}
		if (!ClassUtil.equals(this.triValue,aObj.triValue)) {
			return false;
		}
		if (!ClassUtil.equals(this.triFlags,aObj.triFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer triId) {
		this.triId = triId;
	}

	public void setPk(BaseCliDataDefTriggerVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.triId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getTriId() {
		return this.triId;
	}
	public void setTriId(Integer triId) {
		this.triId = triId;
	}

	public Integer getStaId() {
		return this.staId;
	}
	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Integer getTriSource() {
		return this.triSource;
	}
	public void setTriSource(Integer triSource) {
		this.triSource = triSource;
	}

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

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public String getTriName() {
		return this.triName;
	}
	public void setTriName(String triName) {
		this.triName = triName;
	}

	public String getTriValue() {
		return this.triValue;
	}
	public void setTriValue(String triValue) {
		this.triValue = triValue;
	}

	public String getTriFlags() {
		return this.triFlags;
	}
	public void setTriFlags(String triFlags) {
		this.triFlags = triFlags;
	}

}
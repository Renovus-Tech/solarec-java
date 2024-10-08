package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@javax.annotation.Generated(value = "Renovus") public class BaseDataDefinitionVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_DEF_ID = "data_def_id_auto";
	public static final String COLUMN_DATA_DEF_NAME = "data_def_name";
	public static final String COLUMN_DATA_DEF_DESCRIPTION = "data_def_description";
	public static final String COLUMN_DATA_DEF_EXECUTABLE = "data_def_executable";
	public static final String COLUMN_DATA_DEF_FLAGS = "data_def_flags";

	public static final int LENGTH_COLUMN_DATA_DEF_NAME =  100;
	public static final int LENGTH_COLUMN_DATA_DEF_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_DATA_DEF_EXECUTABLE =  500;
	public static final int LENGTH_COLUMN_DATA_DEF_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.dataDefFlags; }
	@Override public void setFlags(String dataDefFlags) { this.dataDefFlags = dataDefFlags; }

	//--- Private properties --------------------
	private Integer dataDefId;
	private String dataDefName;
	private String dataDefDescription;
	private String dataDefExecutable;
	private String dataDefFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.dataDefId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataDefinitionVo)) return false;
		
		BaseDataDefinitionVo aObj = (BaseDataDefinitionVo) obj;
		return ClassUtil.equals(this.dataDefId,aObj.dataDefId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataDefId != null) hashCode += this.dataDefId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataDefinitionVo aObj = (BaseDataDefinitionVo) obj;
		if (!ClassUtil.equals(this.dataDefName,aObj.dataDefName)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefDescription,aObj.dataDefDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefExecutable,aObj.dataDefExecutable)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefFlags,aObj.dataDefFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public void setPk(BaseDataDefinitionVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.dataDefId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public String getDataDefName() {
		return this.dataDefName;
	}
	public void setDataDefName(String dataDefName) {
		this.dataDefName = dataDefName;
	}

	public String getDataDefDescription() {
		return this.dataDefDescription;
	}
	public void setDataDefDescription(String dataDefDescription) {
		this.dataDefDescription = dataDefDescription;
	}

	public String getDataDefExecutable() {
		return this.dataDefExecutable;
	}
	public void setDataDefExecutable(String dataDefExecutable) {
		this.dataDefExecutable = dataDefExecutable;
	}

	public String getDataDefFlags() {
		return this.dataDefFlags;
	}
	public void setDataDefFlags(String dataDefFlags) {
		this.dataDefFlags = dataDefFlags;
	}

}
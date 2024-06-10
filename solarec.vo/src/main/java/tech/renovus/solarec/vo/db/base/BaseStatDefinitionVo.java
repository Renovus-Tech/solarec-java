package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseStatDefinitionVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_STAT_DEF_ID = "stat_def_id_auto";
	public static final String COLUMN_STAT_DEF_TYPE = "stat_def_type";
	public static final String COLUMN_STAT_DEF_NAME = "stat_def_name";
	public static final String COLUMN_STAT_DEF_DESCRIPTION = "stat_def_description";
	public static final String COLUMN_STAT_DEF_EXECUTABLE = "stat_def_executable";
	public static final String COLUMN_STAT_DEF_FLAGS = "stat_def_flags";

	public static final int LENGTH_COLUMN_STAT_DEF_NAME =  100;
	public static final int LENGTH_COLUMN_STAT_DEF_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_STAT_DEF_EXECUTABLE =  500;
	public static final int LENGTH_COLUMN_STAT_DEF_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.statDefFlags; }
	@Override public void setFlags(String statDefFlags) { this.statDefFlags = statDefFlags; }

	//--- Private properties --------------------
	private Integer statDefId;
	private Integer statDefType;
	private String statDefName;
	private String statDefDescription;
	private String statDefExecutable;
	private String statDefFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.statDefId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatDefinitionVo)) return false;
		
		BaseStatDefinitionVo aObj = (BaseStatDefinitionVo) obj;
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statDefId != null) hashCode += this.statDefId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStatDefinitionVo aObj = (BaseStatDefinitionVo) obj;
		if (!ClassUtil.equals(this.statDefType,aObj.statDefType)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefName,aObj.statDefName)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefDescription,aObj.statDefDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefExecutable,aObj.statDefExecutable)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefFlags,aObj.statDefFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer statDefId) {
		this.statDefId = statDefId;
	}

	public void setPk(BaseStatDefinitionVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.statDefId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getStatDefId() {
		return this.statDefId;
	}
	public void setStatDefId(Integer statDefId) {
		this.statDefId = statDefId;
	}

	public Integer getStatDefType() {
		return this.statDefType;
	}
	public void setStatDefType(Integer statDefType) {
		this.statDefType = statDefType;
	}

	public String getStatDefName() {
		return this.statDefName;
	}
	public void setStatDefName(String statDefName) {
		this.statDefName = statDefName;
	}

	public String getStatDefDescription() {
		return this.statDefDescription;
	}
	public void setStatDefDescription(String statDefDescription) {
		this.statDefDescription = statDefDescription;
	}

	public String getStatDefExecutable() {
		return this.statDefExecutable;
	}
	public void setStatDefExecutable(String statDefExecutable) {
		this.statDefExecutable = statDefExecutable;
	}

	public String getStatDefFlags() {
		return this.statDefFlags;
	}
	public void setStatDefFlags(String statDefFlags) {
		this.statDefFlags = statDefFlags;
	}

}
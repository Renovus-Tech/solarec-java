package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseStatDefinitionVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_STAT_DEF_ID = "stat_def_id_auto";
	 public static final String COLUMN_STAT_DEF_NAME = "stat_def_name";
	 public static final String COLUMN_STAT_DEF_DESCRIPTION = "stat_def_description";
	 public static final String COLUMN_STAT_DEF_EXECUTABLE = "stat_def_executable";
	 public static final String COLUMN_STAT_DEF_FLAGS = "stat_def_flags";
	 public static final String COLUMN_STAT_DEF_TYPE = "stat_def_type";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.statDefFlags; }
	@Override public void setFlags(String statDefFlags) { this.statDefFlags = statDefFlags; }

	//--- Private properties --------------------
	 private Integer statDefId;
	 private String statDefName;
	 private String statDefDescription;
	 private String statDefExecutable;
	 private String statDefFlags;
	 private Integer statDefType;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statDefId == null) {
			return false;
		}
		return true;
	}

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
		if (obj == null) return false;
		if (!(obj instanceof BaseStatDefinitionVo)) return false;
		
		BaseStatDefinitionVo aObj = (BaseStatDefinitionVo) obj;
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
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
		if (!ClassUtil.equals(this.statDefType,aObj.statDefType)) {
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
			this.setPk((Integer)aVo.getStatDefId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getStatDefId() {
		return this.statDefId;
	}
	public void setStatDefId(Integer statDefId) {
		this.statDefId = statDefId;
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

	public Integer getStatDefType() {
		return this.statDefType;
	}
	public void setStatDefType(Integer statDefType) {
		this.statDefType = statDefType;
	}

}
package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
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
	private @Getter @Setter Integer statDefId;
	private @Getter @Setter Integer statDefType;
	private @Getter @Setter String statDefName;
	private @Getter @Setter String statDefDescription;
	private @Getter @Setter String statDefExecutable;
	private @Getter @Setter String statDefFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statDefId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
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

}
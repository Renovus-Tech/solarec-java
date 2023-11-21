package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseDataDefinitionVo extends BaseDbVo implements IFlags {

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
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter String dataDefName;
	private @Getter @Setter String dataDefDescription;
	private @Getter @Setter String dataDefExecutable;
	private @Getter @Setter String dataDefFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataDefId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataDefinitionVo)) return false;
		
		BaseDataDefinitionVo aObj = (BaseDataDefinitionVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		return true;
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
			this.setPk((Integer)aVo.dataDefId);
		}
	}

}
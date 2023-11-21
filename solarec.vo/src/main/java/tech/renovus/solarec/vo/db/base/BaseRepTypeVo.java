package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseRepTypeVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_REP_TYPE_ID = "rep_type_id_auto";
	public static final String COLUMN_REP_ORDER = "rep_order";
	public static final String COLUMN_REP_TYPE_NAME = "rep_type_name";
	public static final String COLUMN_REP_TYPE_TITLE = "rep_type_title";
	public static final String COLUMN_REP_FLAGS = "rep_flags";
	public static final String COLUMN_REP_EXECUTABLE = "rep_executable";

	public static final int LENGTH_COLUMN_REP_TYPE_NAME =  100;
	public static final int LENGTH_COLUMN_REP_TYPE_TITLE =  200;
	public static final int LENGTH_COLUMN_REP_FLAGS =  20;
	public static final int LENGTH_COLUMN_REP_EXECUTABLE =  500;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.repFlags; }
	@Override public void setFlags(String repFlags) { this.repFlags = repFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer repTypeId;
	private @Getter @Setter Integer repOrder;
	private @Getter @Setter String repTypeName;
	private @Getter @Setter String repTypeTitle;
	private @Getter @Setter String repFlags;
	private @Getter @Setter String repExecutable;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.repTypeId == null) {
			return false;
		}
		if (this.repOrder == null) {
			return false;
		}
		if (this.repExecutable == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseRepTypeVo)) return false;
		
		BaseRepTypeVo aObj = (BaseRepTypeVo) obj;
		if (!ClassUtil.equals(this.repTypeId,aObj.repTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.repTypeId != null) hashCode += this.repTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseRepTypeVo aObj = (BaseRepTypeVo) obj;
		if (!ClassUtil.equals(this.repOrder,aObj.repOrder)) {
			return false;
		}
		if (!ClassUtil.equals(this.repTypeName,aObj.repTypeName)) {
			return false;
		}
		if (!ClassUtil.equals(this.repTypeTitle,aObj.repTypeTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.repFlags,aObj.repFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.repExecutable,aObj.repExecutable)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer repTypeId) {
		this.repTypeId = repTypeId;
	}

	public void setPk(BaseRepTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.repTypeId);
		}
	}

}
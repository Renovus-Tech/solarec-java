package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseProfileVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_PRF_ID = "prf_id_auto";
	public static final String COLUMN_PRF_NAME = "prf_name";
	public static final String COLUMN_PRF_DESCRIPTION = "prf_description";
	public static final String COLUMN_PRF_FLAGS = "prf_flags";

	public static final int LENGTH_COLUMN_PRF_NAME =  100;
	public static final int LENGTH_COLUMN_PRF_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_PRF_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.prfFlags; }
	@Override public void setFlags(String prfFlags) { this.prfFlags = prfFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer prfId;
	private @Getter @Setter String prfName;
	private @Getter @Setter String prfDescription;
	private @Getter @Setter String prfFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.prfId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseProfileVo)) return false;
		
		BaseProfileVo aObj = (BaseProfileVo) obj;
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.prfId != null) hashCode += this.prfId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseProfileVo aObj = (BaseProfileVo) obj;
		if (!ClassUtil.equals(this.prfName,aObj.prfName)) {
			return false;
		}
		if (!ClassUtil.equals(this.prfDescription,aObj.prfDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.prfFlags,aObj.prfFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer prfId) {
		this.prfId = prfId;
	}

	public void setPk(BaseProfileVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.prfId);
		}
	}

}
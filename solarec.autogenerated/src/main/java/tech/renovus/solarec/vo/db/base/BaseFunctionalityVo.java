package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseFunctionalityVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_FNC_ID = "fnc_id_auto";
	public static final String COLUMN_FNC_ORDER = "fnc_order";
	public static final String COLUMN_FNC_TITLE = "fnc_title";
	public static final String COLUMN_FNC_FLAGS = "fnc_flags";
	public static final String COLUMN_FNC_URL = "fnc_url";
	public static final String COLUMN_FNC_DESCRIPTION = "fnc_description";
	public static final String COLUMN_FNC_NAME = "fnc_name";

	public static final int LENGTH_COLUMN_FNC_TITLE =  200;
	public static final int LENGTH_COLUMN_FNC_FLAGS =  20;
	public static final int LENGTH_COLUMN_FNC_URL =  255;
	public static final int LENGTH_COLUMN_FNC_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_FNC_NAME =  100;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.fncFlags; }
	@Override public void setFlags(String fncFlags) { this.fncFlags = fncFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer fncId;
	private @Getter @Setter Integer fncOrder;
	private @Getter @Setter String fncTitle;
	private @Getter @Setter String fncFlags;
	private @Getter @Setter String fncUrl;
	private @Getter @Setter String fncDescription;
	private @Getter @Setter String fncName;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.fncId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseFunctionalityVo)) return false;
		
		BaseFunctionalityVo aObj = (BaseFunctionalityVo) obj;
		if (!ClassUtil.equals(this.fncId,aObj.fncId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.fncId != null) hashCode += this.fncId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseFunctionalityVo aObj = (BaseFunctionalityVo) obj;
		if (!ClassUtil.equals(this.fncOrder,aObj.fncOrder)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncTitle,aObj.fncTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncFlags,aObj.fncFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncUrl,aObj.fncUrl)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncDescription,aObj.fncDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncName,aObj.fncName)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer fncId) {
		this.fncId = fncId;
	}

	public void setPk(BaseFunctionalityVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.fncId);
		}
	}

}
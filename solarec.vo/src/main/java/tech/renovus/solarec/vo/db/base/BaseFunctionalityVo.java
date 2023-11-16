package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseFunctionalityVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_FNC_ID = "fnc_id_auto";
	 public static final String COLUMN_FNC_NAME = "fnc_name";
	 public static final String COLUMN_FNC_TITLE = "fnc_title";
	 public static final String COLUMN_FNC_DESCRIPTION = "fnc_description";
	 public static final String COLUMN_FNC_FLAGS = "fnc_flags";
	 public static final String COLUMN_FNC_URL = "fnc_url";
	 public static final String COLUMN_FNC_ORDER = "fnc_order";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.fncFlags; }
	@Override public void setFlags(String fncFlags) { this.fncFlags = fncFlags; }

	//--- Private properties --------------------
	 private Integer fncId;
	 private String fncName;
	 private String fncTitle;
	 private String fncDescription;
	 private String fncFlags;
	 private String fncUrl;
	 private Integer fncOrder;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.fncId == null) {
			return false;
		}
		return true;
	}

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
		if (obj == null) return false;
		if (!(obj instanceof BaseFunctionalityVo)) return false;
		
		BaseFunctionalityVo aObj = (BaseFunctionalityVo) obj;
		if (!ClassUtil.equals(this.fncId,aObj.fncId)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncName,aObj.fncName)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncTitle,aObj.fncTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncDescription,aObj.fncDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncFlags,aObj.fncFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncUrl,aObj.fncUrl)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncOrder,aObj.fncOrder)) {
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
			this.setPk((Integer)aVo.getFncId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getFncId() {
		return this.fncId;
	}
	public void setFncId(Integer fncId) {
		this.fncId = fncId;
	}

	public String getFncName() {
		return this.fncName;
	}
	public void setFncName(String fncName) {
		this.fncName = fncName;
	}

	public String getFncTitle() {
		return this.fncTitle;
	}
	public void setFncTitle(String fncTitle) {
		this.fncTitle = fncTitle;
	}

	public String getFncDescription() {
		return this.fncDescription;
	}
	public void setFncDescription(String fncDescription) {
		this.fncDescription = fncDescription;
	}

	public String getFncFlags() {
		return this.fncFlags;
	}
	public void setFncFlags(String fncFlags) {
		this.fncFlags = fncFlags;
	}

	public String getFncUrl() {
		return this.fncUrl;
	}
	public void setFncUrl(String fncUrl) {
		this.fncUrl = fncUrl;
	}

	public Integer getFncOrder() {
		return this.fncOrder;
	}
	public void setFncOrder(Integer fncOrder) {
		this.fncOrder = fncOrder;
	}

}
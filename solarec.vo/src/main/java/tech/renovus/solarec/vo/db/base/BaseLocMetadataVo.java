package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseLocMetadataVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_METADATA_NAME = "metadata_name";
	 public static final String COLUMN_METADATA_TITLE = "metadata_title";
	 public static final String COLUMN_METADATA_VALUE = "metadata_value";
	 public static final String COLUMN_METADATA_DATE_ADDED = "metadata_date_added";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private String metadataName;
	 private String metadataTitle;
	 private String metadataValue;
	 private java.util.Date metadataDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.metadataName == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocMetadataVo)) return false;
		
		BaseLocMetadataVo aObj = (BaseLocMetadataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataName,aObj.metadataName)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.metadataName != null) hashCode += this.metadataName.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocMetadataVo)) return false;
		
		BaseLocMetadataVo aObj = (BaseLocMetadataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataName,aObj.metadataName)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataTitle,aObj.metadataTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataValue,aObj.metadataValue)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataDateAdded,aObj.metadataDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, String metadataName) {
		this.cliId = cliId;
		this.locId = locId;
		this.metadataName = metadataName;
	}

	public void setPk(BaseLocMetadataVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getMetadataName());
		}
	}

	//--- Getters and Setters -------------------
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

	public String getMetadataName() {
		return this.metadataName;
	}
	public void setMetadataName(String metadataName) {
		this.metadataName = metadataName;
	}

	public String getMetadataTitle() {
		return this.metadataTitle;
	}
	public void setMetadataTitle(String metadataTitle) {
		this.metadataTitle = metadataTitle;
	}

	public String getMetadataValue() {
		return this.metadataValue;
	}
	public void setMetadataValue(String metadataValue) {
		this.metadataValue = metadataValue;
	}

	public java.util.Date getMetadataDateAdded() {
		return this.metadataDateAdded;
	}
	public void setMetadataDateAdded(java.util.Date metadataDateAdded) {
		this.metadataDateAdded = metadataDateAdded;
	}

}
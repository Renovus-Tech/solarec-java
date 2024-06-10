package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseGenMetadataVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_METADATA_DATE_ADDED = "metadata_date_added";
	public static final String COLUMN_METADATA_CODE = "metadata_code";
	public static final String COLUMN_METADATA_TITLE = "metadata_title";
	public static final String COLUMN_METADATA_VALUE = "metadata_value";

	public static final int LENGTH_COLUMN_METADATA_CODE =  100;
	public static final int LENGTH_COLUMN_METADATA_TITLE =  200;
	public static final int LENGTH_COLUMN_METADATA_VALUE =  200;

	//--- Private properties --------------------
	private Integer cliId;
	private Integer genId;
	private java.util.Date metadataDateAdded;
	private String metadataCode;
	private String metadataTitle;
	private String metadataValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		if (this.metadataCode == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGenMetadataVo)) return false;
		
		BaseGenMetadataVo aObj = (BaseGenMetadataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataCode,aObj.metadataCode)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.metadataCode != null) hashCode += this.metadataCode.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseGenMetadataVo aObj = (BaseGenMetadataVo) obj;
		if (!ClassUtil.equals(this.metadataDateAdded,aObj.metadataDateAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataTitle,aObj.metadataTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.metadataValue,aObj.metadataValue)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId, String metadataCode) {
		this.cliId = cliId;
		this.genId = genId;
		this.metadataCode = metadataCode;
	}

	public void setPk(BaseGenMetadataVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.genId, aVo.metadataCode);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public java.util.Date getMetadataDateAdded() {
		return this.metadataDateAdded;
	}
	public void setMetadataDateAdded(java.util.Date metadataDateAdded) {
		this.metadataDateAdded = metadataDateAdded;
	}

	public String getMetadataCode() {
		return this.metadataCode;
	}
	public void setMetadataCode(String metadataCode) {
		this.metadataCode = metadataCode;
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

}
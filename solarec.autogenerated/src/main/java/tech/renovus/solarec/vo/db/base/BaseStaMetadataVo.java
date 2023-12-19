package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseStaMetadataVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_STA_ID = "sta_id";
	public static final String COLUMN_METADATA_DATE_ADDED = "metadata_date_added";
	public static final String COLUMN_METADATA_NAME = "metadata_name";
	public static final String COLUMN_METADATA_TITLE = "metadata_title";
	public static final String COLUMN_METADATA_VALUE = "metadata_value";

	public static final int LENGTH_COLUMN_METADATA_NAME =  100;
	public static final int LENGTH_COLUMN_METADATA_TITLE =  200;
	public static final int LENGTH_COLUMN_METADATA_VALUE =  200;

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer staId;
	private @Getter @Setter java.util.Date metadataDateAdded;
	private @Getter @Setter String metadataName;
	private @Getter @Setter String metadataTitle;
	private @Getter @Setter String metadataValue;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.staId == null) {
			return false;
		}
		if (this.metadataName == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStaMetadataVo)) return false;
		
		BaseStaMetadataVo aObj = (BaseStaMetadataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
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
		if (this.staId != null) hashCode += this.staId.hashCode();
		if (this.metadataName != null) hashCode += this.metadataName.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStaMetadataVo aObj = (BaseStaMetadataVo) obj;
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

	public void setPk(Integer cliId, Integer staId, String metadataName) {
		this.cliId = cliId;
		this.staId = staId;
		this.metadataName = metadataName;
	}

	public void setPk(BaseStaMetadataVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.staId, aVo.metadataName);
		}
	}

}
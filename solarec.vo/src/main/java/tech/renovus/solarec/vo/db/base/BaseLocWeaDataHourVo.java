package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseLocWeaDataHourVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_LOC_WEA_DATA_ID = "loc_wea_data_id";
	 public static final String COLUMN_LOC_WEA_DATA_HOUR = "loc_wea_data_hour";
	 public static final String COLUMN_LOC_WEA_DATA_RESPONSE = "loc_wea_data_response";
	 public static final String COLUMN_LOC_WEA_DATA_RETRIEVE = "loc_wea_data_retrieve";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private Integer locWeaDataId;
	 private java.util.Date locWeaDataHour;
	 private String locWeaDataResponse;
	 private java.util.Date locWeaDataRetrieve;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.locWeaDataId == null) {
			return false;
		}
		if (this.locWeaDataHour == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocWeaDataHourVo)) return false;
		
		BaseLocWeaDataHourVo aObj = (BaseLocWeaDataHourVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataId,aObj.locWeaDataId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataHour,aObj.locWeaDataHour)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.locWeaDataId != null) hashCode += this.locWeaDataId.hashCode();
		if (this.locWeaDataHour != null) hashCode += this.locWeaDataHour.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocWeaDataHourVo)) return false;
		
		BaseLocWeaDataHourVo aObj = (BaseLocWeaDataHourVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataId,aObj.locWeaDataId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataHour,aObj.locWeaDataHour)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataResponse,aObj.locWeaDataResponse)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataRetrieve,aObj.locWeaDataRetrieve)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour) {
		this.cliId = cliId;
		this.locId = locId;
		this.locWeaDataId = locWeaDataId;
		this.locWeaDataHour = locWeaDataHour;
	}

	public void setPk(BaseLocWeaDataHourVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getLocWeaDataId(), aVo.getLocWeaDataHour());
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

	public Integer getLocWeaDataId() {
		return this.locWeaDataId;
	}
	public void setLocWeaDataId(Integer locWeaDataId) {
		this.locWeaDataId = locWeaDataId;
	}

	public java.util.Date getLocWeaDataHour() {
		return this.locWeaDataHour;
	}
	public void setLocWeaDataHour(java.util.Date locWeaDataHour) {
		this.locWeaDataHour = locWeaDataHour;
	}

	public String getLocWeaDataResponse() {
		return this.locWeaDataResponse;
	}
	public void setLocWeaDataResponse(String locWeaDataResponse) {
		this.locWeaDataResponse = locWeaDataResponse;
	}

	public java.util.Date getLocWeaDataRetrieve() {
		return this.locWeaDataRetrieve;
	}
	public void setLocWeaDataRetrieve(java.util.Date locWeaDataRetrieve) {
		this.locWeaDataRetrieve = locWeaDataRetrieve;
	}

}
package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseLocWeatherDataVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_LOC_WEA_DATA_ID = "loc_wea_data_id_auto";
	 public static final String COLUMN_LOC_WEA_DATA_DATE = "loc_wea_data_date";
	 public static final String COLUMN_LOC_WEA_DATA_RESONSE_STATUS = "loc_wea_data_resonse_status";
	 public static final String COLUMN_LOC_WEA_DATA_RESPONSE = "loc_wea_data_response";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private Integer locWeaDataId;
	 private java.util.Date locWeaDataDate;
	 private Integer locWeaDataResonseStatus;
	 private String locWeaDataResponse;

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
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocWeatherDataVo)) return false;
		
		BaseLocWeatherDataVo aObj = (BaseLocWeatherDataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataId,aObj.locWeaDataId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.locWeaDataId != null) hashCode += this.locWeaDataId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocWeatherDataVo)) return false;
		
		BaseLocWeatherDataVo aObj = (BaseLocWeatherDataVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataId,aObj.locWeaDataId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataDate,aObj.locWeaDataDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataResonseStatus,aObj.locWeaDataResonseStatus)) {
			return false;
		}
		if (!ClassUtil.equals(this.locWeaDataResponse,aObj.locWeaDataResponse)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer locWeaDataId) {
		this.cliId = cliId;
		this.locId = locId;
		this.locWeaDataId = locWeaDataId;
	}

	public void setPk(BaseLocWeatherDataVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getLocWeaDataId());
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

	public java.util.Date getLocWeaDataDate() {
		return this.locWeaDataDate;
	}
	public void setLocWeaDataDate(java.util.Date locWeaDataDate) {
		this.locWeaDataDate = locWeaDataDate;
	}

	public Integer getLocWeaDataResonseStatus() {
		return this.locWeaDataResonseStatus;
	}
	public void setLocWeaDataResonseStatus(Integer locWeaDataResonseStatus) {
		this.locWeaDataResonseStatus = locWeaDataResonseStatus;
	}

	public String getLocWeaDataResponse() {
		return this.locWeaDataResponse;
	}
	public void setLocWeaDataResponse(String locWeaDataResponse) {
		this.locWeaDataResponse = locWeaDataResponse;
	}

}
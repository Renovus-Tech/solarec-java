package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseStaDataMdlMlSanitizedVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_STA_ID = "sta_id";
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	public static final String COLUMN_DATA_STAT_ID = "data_stat_id";
	public static final String COLUMN_DATA_DATE = "data_date";
	public static final String COLUMN_DATA_VALUE_303 = "data_value_303";
	public static final String COLUMN_DATA_VALUE_304 = "data_value_304";
	public static final String COLUMN_DATA_VALUE_305 = "data_value_305";
	public static final String COLUMN_DATA_DATE_ADDED = "data_date_added";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private Integer staId;
	private Integer dataProId;
	private Integer dataStatId;
	private java.util.Date dataDate;
	private Double dataValue303;
	private Double dataValue304;
	private Double dataValue305;
	private java.util.Date dataDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.staId == null) {
			return false;
		}
		if (this.dataProId == null) {
			return false;
		}
		if (this.dataStatId == null) {
			return false;
		}
		if (this.dataDate == null) {
			return false;
		}
		if (this.dataDateAdded == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStaDataMdlMlSanitizedVo)) return false;
		
		BaseStaDataMdlMlSanitizedVo aObj = (BaseStaDataMdlMlSanitizedVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDate.getTime(),aObj.dataDate.getTime())) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.staId != null) hashCode += this.staId.hashCode();
		if (this.dataDate != null) hashCode += this.dataDate.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStaDataMdlMlSanitizedVo aObj = (BaseStaDataMdlMlSanitizedVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataStatId,aObj.dataStatId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue303,aObj.dataValue303)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue304,aObj.dataValue304)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue305,aObj.dataValue305)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDateAdded,aObj.dataDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer staId, java.util.Date dataDate) {
		this.cliId = cliId;
		this.locId = locId;
		this.staId = staId;
		this.dataDate = dataDate;
	}

	public void setPk(BaseStaDataMdlMlSanitizedVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.staId, aVo.dataDate);
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

	public Integer getStaId() {
		return this.staId;
	}
	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public Integer getDataProId() {
		return this.dataProId;
	}
	public void setDataProId(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public Integer getDataStatId() {
		return this.dataStatId;
	}
	public void setDataStatId(Integer dataStatId) {
		this.dataStatId = dataStatId;
	}

	public java.util.Date getDataDate() {
		return this.dataDate;
	}
	public void setDataDate(java.util.Date dataDate) {
		this.dataDate = dataDate;
	}

	public Double getDataValue303() {
		return this.dataValue303;
	}
	public void setDataValue303(Double dataValue303) {
		this.dataValue303 = dataValue303;
	}

	public Double getDataValue304() {
		return this.dataValue304;
	}
	public void setDataValue304(Double dataValue304) {
		this.dataValue304 = dataValue304;
	}

	public Double getDataValue305() {
		return this.dataValue305;
	}
	public void setDataValue305(Double dataValue305) {
		this.dataValue305 = dataValue305;
	}

	public java.util.Date getDataDateAdded() {
		return this.dataDateAdded;
	}
	public void setDataDateAdded(java.util.Date dataDateAdded) {
		this.dataDateAdded = dataDateAdded;
	}

}
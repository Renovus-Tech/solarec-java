package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@javax.annotation.Generated(value = "Renovus") public class BaseLocDataMdlMlSanitizedVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	public static final String COLUMN_DATA_STAT_ID = "data_stat_id";
	public static final String COLUMN_DATA_DATE = "data_date";
	public static final String COLUMN_DATA_VALUE_401 = "data_value_401";
	public static final String COLUMN_DATA_VALUE_402 = "data_value_402";
	public static final String COLUMN_DATA_DATE_ADDED = "data_date_added";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private Integer dataProId;
	private Integer dataStatId;
	private java.util.Date dataDate;
	private Double dataValue401;
	private Double dataValue402;
	private java.util.Date dataDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
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
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocDataMdlMlSanitizedVo)) return false;
		
		BaseLocDataMdlMlSanitizedVo aObj = (BaseLocDataMdlMlSanitizedVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
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
		if (this.dataDate != null) hashCode += this.dataDate.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocDataMdlMlSanitizedVo aObj = (BaseLocDataMdlMlSanitizedVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataStatId,aObj.dataStatId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue401,aObj.dataValue401)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue402,aObj.dataValue402)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDateAdded,aObj.dataDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, java.util.Date dataDate) {
		this.cliId = cliId;
		this.locId = locId;
		this.dataDate = dataDate;
	}

	public void setPk(BaseLocDataMdlMlSanitizedVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.dataDate);
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

	public Double getDataValue401() {
		return this.dataValue401;
	}
	public void setDataValue401(Double dataValue401) {
		this.dataValue401 = dataValue401;
	}

	public Double getDataValue402() {
		return this.dataValue402;
	}
	public void setDataValue402(Double dataValue402) {
		this.dataValue402 = dataValue402;
	}

	public java.util.Date getDataDateAdded() {
		return this.dataDateAdded;
	}
	public void setDataDateAdded(java.util.Date dataDateAdded) {
		this.dataDateAdded = dataDateAdded;
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseGenDataMdlMlSanitizedVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	public static final String COLUMN_DATA_STAT_ID = "data_stat_id";
	public static final String COLUMN_DATA_DATE = "data_date";
	public static final String COLUMN_DATA_VALUE_201 = "data_value_201";
	public static final String COLUMN_DATA_VALUE_202 = "data_value_202";
	public static final String COLUMN_DATA_VALUE_204 = "data_value_204";
	public static final String COLUMN_DATA_VALUE_205 = "data_value_205";
	public static final String COLUMN_DATA_VALUE_257 = "data_value_257";
	public static final String COLUMN_DATA_DATE_ADDED = "data_date_added";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private Integer genId;
	private Integer dataProId;
	private Integer dataStatId;
	private java.util.Date dataDate;
	private Double dataValue201;
	private Double dataValue202;
	private Double dataValue204;
	private Double dataValue205;
	private Double dataValue257;
	private java.util.Date dataDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.genId == null) {
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
		if (!(obj instanceof BaseGenDataMdlMlSanitizedVo)) return false;
		
		BaseGenDataMdlMlSanitizedVo aObj = (BaseGenDataMdlMlSanitizedVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
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
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.dataDate != null) hashCode += this.dataDate.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseGenDataMdlMlSanitizedVo aObj = (BaseGenDataMdlMlSanitizedVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataStatId,aObj.dataStatId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue201,aObj.dataValue201)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue202,aObj.dataValue202)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue204,aObj.dataValue204)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue205,aObj.dataValue205)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataValue257,aObj.dataValue257)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDateAdded,aObj.dataDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer genId, java.util.Date dataDate) {
		this.cliId = cliId;
		this.locId = locId;
		this.genId = genId;
		this.dataDate = dataDate;
	}

	public void setPk(BaseGenDataMdlMlSanitizedVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId, aVo.genId, aVo.dataDate);
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

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
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

	public Double getDataValue201() {
		return this.dataValue201;
	}
	public void setDataValue201(Double dataValue201) {
		this.dataValue201 = dataValue201;
	}

	public Double getDataValue202() {
		return this.dataValue202;
	}
	public void setDataValue202(Double dataValue202) {
		this.dataValue202 = dataValue202;
	}

	public Double getDataValue204() {
		return this.dataValue204;
	}
	public void setDataValue204(Double dataValue204) {
		this.dataValue204 = dataValue204;
	}

	public Double getDataValue205() {
		return this.dataValue205;
	}
	public void setDataValue205(Double dataValue205) {
		this.dataValue205 = dataValue205;
	}

	public Double getDataValue257() {
		return this.dataValue257;
	}
	public void setDataValue257(Double dataValue257) {
		this.dataValue257 = dataValue257;
	}

	public java.util.Date getDataDateAdded() {
		return this.dataDateAdded;
	}
	public void setDataDateAdded(java.util.Date dataDateAdded) {
		this.dataDateAdded = dataDateAdded;
	}

}
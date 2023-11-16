package tech.renovus.solarec.vo.db.base;

import java.util.Date;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseStationVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_STA_ID = "sta_id_auto";
	 public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_STA_NAME = "sta_name";
	 public static final String COLUMN_STA_DESCRIPTION = "sta_description";
	 public static final String COLUMN_STA_COORD_LAT = "sta_coord_lat";
	 public static final String COLUMN_STA_COORD_LNG = "sta_coord_lng";
	 public static final String COLUMN_STA_FLAGS = "sta_flags";
	 public static final String COLUMN_STA_CODE = "sta_code";
	 public static final String COLUMN_STA_DATA_DATE_MAX = "sta_data_date_max";
	 public static final String COLUMN_STA_DATA_DATE_MIN = "sta_data_date_min";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.staFlags; }
	@Override public void setFlags(String staFlags) { this.staFlags = staFlags; }

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer staId;
	 private Integer dataDefId;
	 private Integer locId;
	 private String staName;
	 private String staDescription;
	 private Double staCoordLat;
	 private Double staCoordLng;
	 private String staFlags;
	 private String staCode;
	 private Date staDataDateMax;
	 private Date staDataDateMin;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.staId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStationVo)) return false;
		
		BaseStationVo aObj = (BaseStationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.staId != null) hashCode += this.staId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStationVo)) return false;
		
		BaseStationVo aObj = (BaseStationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staName,aObj.staName)) {
			return false;
		}
		if (!ClassUtil.equals(this.staDescription,aObj.staDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.staCoordLat,aObj.staCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.staCoordLng,aObj.staCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.staFlags,aObj.staFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.staCode,aObj.staCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.staDataDateMax,aObj.staDataDateMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.staDataDateMin,aObj.staDataDateMin)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer staId) {
		this.cliId = cliId;
		this.staId = staId;
	}

	public void setPk(BaseStationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getStaId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getStaId() {
		return this.staId;
	}
	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public String getStaName() {
		return this.staName;
	}
	public void setStaName(String staName) {
		this.staName = staName;
	}

	public String getStaDescription() {
		return this.staDescription;
	}
	public void setStaDescription(String staDescription) {
		this.staDescription = staDescription;
	}

	public Double getStaCoordLat() {
		return this.staCoordLat;
	}
	public void setStaCoordLat(Double staCoordLat) {
		this.staCoordLat = staCoordLat;
	}

	public Double getStaCoordLng() {
		return this.staCoordLng;
	}
	public void setStaCoordLng(Double staCoordLng) {
		this.staCoordLng = staCoordLng;
	}

	public String getStaFlags() {
		return this.staFlags;
	}
	public void setStaFlags(String staFlags) {
		this.staFlags = staFlags;
	}

	public String getStaCode() {
		return this.staCode;
	}
	public void setStaCode(String staCode) {
		this.staCode = staCode;
	}

	public Date getStaDataDateMax() {
		return this.staDataDateMax;
	}
	public void setStaDataDateMax(Date staDataDateMax) {
		this.staDataDateMax = staDataDateMax;
	}

	public Date getStaDataDateMin() {
		return this.staDataDateMin;
	}
	public void setStaDataDateMin(Date staDataDateMin) {
		this.staDataDateMin = staDataDateMin;
	}

}
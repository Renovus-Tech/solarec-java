package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseLocationVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_LOC_TYPE_ID = "loc_type_id";
	public static final String COLUMN_LOC_ID = "loc_id_auto";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_LOC_OUTPUT_TOTAL_CAPACITY = "loc_output_total_capacity";
	public static final String COLUMN_LOC_REFERENCE_DENSITY = "loc_reference_density";
	public static final String COLUMN_LOC_DATA_DATE_MAX = "loc_data_date_max";
	public static final String COLUMN_LOC_DATA_DATE_MIN = "loc_data_date_min";
	public static final String COLUMN_LOC_DEMO_DATE = "loc_demo_date";
	public static final String COLUMN_CTR_ID = "ctr_id";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_COORD_LAT = "loc_coord_lat";
	public static final String COLUMN_LOC_COORD_LNG = "loc_coord_lng";
	public static final String COLUMN_LOC_OUTPUT_CAPACITY = "loc_output_capacity";
	public static final String COLUMN_LOC_NAME = "loc_name";
	public static final String COLUMN_LOC_ADDRESS = "loc_address";
	public static final String COLUMN_LOC_STATE = "loc_state";
	public static final String COLUMN_LOC_TYPE = "loc_type";
	public static final String COLUMN_LOC_FLAGS = "loc_flags";
	public static final String COLUMN_LOC_CODE = "loc_code";
	public static final String COLUMN_LOC_GMT = "loc_gmt";

	public static final int LENGTH_COLUMN_LOC_NAME =  100;
	public static final int LENGTH_COLUMN_LOC_ADDRESS =  500;
	public static final int LENGTH_COLUMN_LOC_STATE =  100;
	public static final int LENGTH_COLUMN_LOC_TYPE =  50;
	public static final int LENGTH_COLUMN_LOC_FLAGS =  20;
	public static final int LENGTH_COLUMN_LOC_CODE =  100;
	public static final int LENGTH_COLUMN_LOC_GMT =  8;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.locFlags; }
	@Override public void setFlags(String locFlags) { this.locFlags = locFlags; }

	//--- Private properties --------------------
	private Integer locTypeId;
	private Integer locId;
	private Integer dataDefId;
	private Double locOutputTotalCapacity;
	private Double locReferenceDensity;
	private java.util.Date locDataDateMax;
	private java.util.Date locDataDateMin;
	private java.util.Date locDemoDate;
	private Integer ctrId;
	private Integer cliId;
	private Double locCoordLat;
	private Double locCoordLng;
	private Double locOutputCapacity;
	private String locName;
	private String locAddress;
	private String locState;
	private String locType;
	private String locFlags;
	private String locCode;
	private String locGmt;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.locId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocationVo)) return false;
		
		BaseLocationVo aObj = (BaseLocationVo) obj;
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocationVo aObj = (BaseLocationVo) obj;
		if (!ClassUtil.equals(this.locTypeId,aObj.locTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locOutputTotalCapacity,aObj.locOutputTotalCapacity)) {
			return false;
		}
		if (!ClassUtil.equals(this.locReferenceDensity,aObj.locReferenceDensity)) {
			return false;
		}
		if (!ClassUtil.equals(this.locDataDateMax,aObj.locDataDateMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.locDataDateMin,aObj.locDataDateMin)) {
			return false;
		}
		if (!ClassUtil.equals(this.locDemoDate,aObj.locDemoDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.ctrId,aObj.ctrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locCoordLat,aObj.locCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.locCoordLng,aObj.locCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.locOutputCapacity,aObj.locOutputCapacity)) {
			return false;
		}
		if (!ClassUtil.equals(this.locName,aObj.locName)) {
			return false;
		}
		if (!ClassUtil.equals(this.locAddress,aObj.locAddress)) {
			return false;
		}
		if (!ClassUtil.equals(this.locState,aObj.locState)) {
			return false;
		}
		if (!ClassUtil.equals(this.locType,aObj.locType)) {
			return false;
		}
		if (!ClassUtil.equals(this.locFlags,aObj.locFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.locCode,aObj.locCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.locGmt,aObj.locGmt)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer locId, Integer cliId) {
		this.locId = locId;
		this.cliId = cliId;
	}

	public void setPk(BaseLocationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.locId, aVo.cliId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getLocTypeId() {
		return this.locTypeId;
	}
	public void setLocTypeId(Integer locTypeId) {
		this.locTypeId = locTypeId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Double getLocOutputTotalCapacity() {
		return this.locOutputTotalCapacity;
	}
	public void setLocOutputTotalCapacity(Double locOutputTotalCapacity) {
		this.locOutputTotalCapacity = locOutputTotalCapacity;
	}

	public Double getLocReferenceDensity() {
		return this.locReferenceDensity;
	}
	public void setLocReferenceDensity(Double locReferenceDensity) {
		this.locReferenceDensity = locReferenceDensity;
	}

	public java.util.Date getLocDataDateMax() {
		return this.locDataDateMax;
	}
	public void setLocDataDateMax(java.util.Date locDataDateMax) {
		this.locDataDateMax = locDataDateMax;
	}

	public java.util.Date getLocDataDateMin() {
		return this.locDataDateMin;
	}
	public void setLocDataDateMin(java.util.Date locDataDateMin) {
		this.locDataDateMin = locDataDateMin;
	}

	public java.util.Date getLocDemoDate() {
		return this.locDemoDate;
	}
	public void setLocDemoDate(java.util.Date locDemoDate) {
		this.locDemoDate = locDemoDate;
	}

	public Integer getCtrId() {
		return this.ctrId;
	}
	public void setCtrId(Integer ctrId) {
		this.ctrId = ctrId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Double getLocCoordLat() {
		return this.locCoordLat;
	}
	public void setLocCoordLat(Double locCoordLat) {
		this.locCoordLat = locCoordLat;
	}

	public Double getLocCoordLng() {
		return this.locCoordLng;
	}
	public void setLocCoordLng(Double locCoordLng) {
		this.locCoordLng = locCoordLng;
	}

	public Double getLocOutputCapacity() {
		return this.locOutputCapacity;
	}
	public void setLocOutputCapacity(Double locOutputCapacity) {
		this.locOutputCapacity = locOutputCapacity;
	}

	public String getLocName() {
		return this.locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocAddress() {
		return this.locAddress;
	}
	public void setLocAddress(String locAddress) {
		this.locAddress = locAddress;
	}

	public String getLocState() {
		return this.locState;
	}
	public void setLocState(String locState) {
		this.locState = locState;
	}

	public String getLocType() {
		return this.locType;
	}
	public void setLocType(String locType) {
		this.locType = locType;
	}

	public String getLocFlags() {
		return this.locFlags;
	}
	public void setLocFlags(String locFlags) {
		this.locFlags = locFlags;
	}

	public String getLocCode() {
		return this.locCode;
	}
	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocGmt() {
		return this.locGmt;
	}
	public void setLocGmt(String locGmt) {
		this.locGmt = locGmt;
	}

}
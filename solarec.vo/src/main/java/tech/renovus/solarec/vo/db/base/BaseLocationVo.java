package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseLocationVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id_auto";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_LOC_COORD_LAT = "loc_coord_lat";
	public static final String COLUMN_LOC_COORD_LNG = "loc_coord_lng";
	public static final String COLUMN_LOC_OUTPUT_CAPACITY = "loc_output_capacity";
	public static final String COLUMN_LOC_OUTPUT_TOTAL_CAPACITY = "loc_output_total_capacity";
	public static final String COLUMN_LOC_REFERENCE_DENSITY = "loc_reference_density";
	public static final String COLUMN_LOC_DATA_DATE_MAX = "loc_data_date_max";
	public static final String COLUMN_LOC_DATA_DATE_MIN = "loc_data_date_min";
	public static final String COLUMN_LOC_DEMO_DATE = "loc_demo_date";
	public static final String COLUMN_LOC_COUNTRY_ALPHA_2 = "loc_country_alpha_2";
	public static final String COLUMN_LOC_NAME = "loc_name";
	public static final String COLUMN_LOC_ADDRESS = "loc_address";
	public static final String COLUMN_LOC_STATE = "loc_state";
	public static final String COLUMN_LOC_COUNTRY = "loc_country";
	public static final String COLUMN_LOC_FLAGS = "loc_flags";
	public static final String COLUMN_LOC_CODE = "loc_code";
	public static final String COLUMN_LOC_TYPE = "loc_type";
	public static final String COLUMN_LOC_GMT = "loc_gmt";

	public static final int LENGTH_COLUMN_LOC_COUNTRY_ALPHA_2 =  2;
	public static final int LENGTH_COLUMN_LOC_NAME =  100;
	public static final int LENGTH_COLUMN_LOC_ADDRESS =  500;
	public static final int LENGTH_COLUMN_LOC_STATE =  100;
	public static final int LENGTH_COLUMN_LOC_COUNTRY =  100;
	public static final int LENGTH_COLUMN_LOC_FLAGS =  20;
	public static final int LENGTH_COLUMN_LOC_CODE =  100;
	public static final int LENGTH_COLUMN_LOC_TYPE =  50;
	public static final int LENGTH_COLUMN_LOC_GMT =  8;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.locFlags; }
	@Override public void setFlags(String locFlags) { this.locFlags = locFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter Double locCoordLat;
	private @Getter @Setter Double locCoordLng;
	private @Getter @Setter Double locOutputCapacity;
	private @Getter @Setter Double locOutputTotalCapacity;
	private @Getter @Setter Double locReferenceDensity;
	private @Getter @Setter java.util.Date locDataDateMax;
	private @Getter @Setter java.util.Date locDataDateMin;
	private @Getter @Setter java.util.Date locDemoDate;
	private @Getter @Setter String locCountryAlpha2;
	private @Getter @Setter String locName;
	private @Getter @Setter String locAddress;
	private @Getter @Setter String locState;
	private @Getter @Setter String locCountry;
	private @Getter @Setter String locFlags;
	private @Getter @Setter String locCode;
	private @Getter @Setter String locType;
	private @Getter @Setter String locGmt;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.dataDefId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocationVo)) return false;
		
		BaseLocationVo aObj = (BaseLocationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocationVo aObj = (BaseLocationVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
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
		if (!ClassUtil.equals(this.locCountryAlpha2,aObj.locCountryAlpha2)) {
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
		if (!ClassUtil.equals(this.locCountry,aObj.locCountry)) {
			return false;
		}
		if (!ClassUtil.equals(this.locFlags,aObj.locFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.locCode,aObj.locCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.locType,aObj.locType)) {
			return false;
		}
		if (!ClassUtil.equals(this.locGmt,aObj.locGmt)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId) {
		this.cliId = cliId;
		this.locId = locId;
	}

	public void setPk(BaseLocationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.cliId, aVo.locId);
		}
	}

}
package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@javax.annotation.Generated(value = "Renovus") public class BaseGeneratorVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_GEN_ID = "gen_id_auto";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_GEN_RATE_POWER = "gen_rate_power";
	public static final String COLUMN_GEN_COORD_LAT = "gen_coord_lat";
	public static final String COLUMN_GEN_COORD_LNG = "gen_coord_lng";
	public static final String COLUMN_GEN_DATA_DATE_MAX = "gen_data_date_max";
	public static final String COLUMN_GEN_DATA_DATE_MIN = "gen_data_date_min";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_GEN_CERT_PROV_DATA = "gen_cert_prov_data";
	public static final String COLUMN_GEN_NAME = "gen_name";
	public static final String COLUMN_GEN_DESCRIPTION = "gen_description";
	public static final String COLUMN_GEN_BRAND = "gen_brand";
	public static final String COLUMN_GEN_MODEL = "gen_model";
	public static final String COLUMN_GEN_SERIAL_NUM = "gen_serial_num";
	public static final String COLUMN_GEN_FLAGS = "gen_flags";
	public static final String COLUMN_GEN_CODE = "gen_code";

	public static final int LENGTH_COLUMN_GEN_CERT_PROV_DATA =  2000;
	public static final int LENGTH_COLUMN_GEN_NAME =  100;
	public static final int LENGTH_COLUMN_GEN_DESCRIPTION =  200;
	public static final int LENGTH_COLUMN_GEN_BRAND =  100;
	public static final int LENGTH_COLUMN_GEN_MODEL =  100;
	public static final int LENGTH_COLUMN_GEN_SERIAL_NUM =  100;
	public static final int LENGTH_COLUMN_GEN_FLAGS =  20;
	public static final int LENGTH_COLUMN_GEN_CODE =  100;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.genFlags; }
	@Override public void setFlags(String genFlags) { this.genFlags = genFlags; }

	//--- Private properties --------------------
	private Integer genId;
	private Integer dataDefId;
	private Double genRatePower;
	private Double genCoordLat;
	private Double genCoordLng;
	private java.util.Date genDataDateMax;
	private java.util.Date genDataDateMin;
	private Integer cliId;
	private Integer locId;
	private String genCertProvData;
	private String genName;
	private String genDescription;
	private String genBrand;
	private String genModel;
	private String genSerialNum;
	private String genFlags;
	private String genCode;

	//--- Public methods ------------------------
	@Override
	public boolean validData() {
		if (this.genId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseGeneratorVo)) {
			return false;
		}
		
		BaseGeneratorVo aObj = (BaseGeneratorVo) obj;
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.genId != null) {
			hashCode += this.genId.hashCode();
		}
		if (this.cliId != null) {
			hashCode += this.cliId.hashCode();
		}
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) {
			return false;
		}
		
		BaseGeneratorVo aObj = (BaseGeneratorVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genRatePower,aObj.genRatePower)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCoordLat,aObj.genCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCoordLng,aObj.genCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.genDataDateMax,aObj.genDataDateMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.genDataDateMin,aObj.genDataDateMin)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCertProvData,aObj.genCertProvData)) {
			return false;
		}
		if (!ClassUtil.equals(this.genName,aObj.genName)) {
			return false;
		}
		if (!ClassUtil.equals(this.genDescription,aObj.genDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.genBrand,aObj.genBrand)) {
			return false;
		}
		if (!ClassUtil.equals(this.genModel,aObj.genModel)) {
			return false;
		}
		if (!ClassUtil.equals(this.genSerialNum,aObj.genSerialNum)) {
			return false;
		}
		if (!ClassUtil.equals(this.genFlags,aObj.genFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCode,aObj.genCode)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId) {
		this.cliId = cliId;
		this.genId = genId;
	}

	public void setPk(BaseGeneratorVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.cliId, aVo.genId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Double getGenRatePower() {
		return this.genRatePower;
	}
	public void setGenRatePower(Double genRatePower) {
		this.genRatePower = genRatePower;
	}

	public Double getGenCoordLat() {
		return this.genCoordLat;
	}
	public void setGenCoordLat(Double genCoordLat) {
		this.genCoordLat = genCoordLat;
	}

	public Double getGenCoordLng() {
		return this.genCoordLng;
	}
	public void setGenCoordLng(Double genCoordLng) {
		this.genCoordLng = genCoordLng;
	}

	public java.util.Date getGenDataDateMax() {
		return this.genDataDateMax;
	}
	public void setGenDataDateMax(java.util.Date genDataDateMax) {
		this.genDataDateMax = genDataDateMax;
	}

	public java.util.Date getGenDataDateMin() {
		return this.genDataDateMin;
	}
	public void setGenDataDateMin(java.util.Date genDataDateMin) {
		this.genDataDateMin = genDataDateMin;
	}

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

	public String getGenCertProvData() {
		return this.genCertProvData;
	}
	public void setGenCertProvData(String genCertProvData) {
		this.genCertProvData = genCertProvData;
	}

	public String getGenName() {
		return this.genName;
	}
	public void setGenName(String genName) {
		this.genName = genName;
	}

	public String getGenDescription() {
		return this.genDescription;
	}
	public void setGenDescription(String genDescription) {
		this.genDescription = genDescription;
	}

	public String getGenBrand() {
		return this.genBrand;
	}
	public void setGenBrand(String genBrand) {
		this.genBrand = genBrand;
	}

	public String getGenModel() {
		return this.genModel;
	}
	public void setGenModel(String genModel) {
		this.genModel = genModel;
	}

	public String getGenSerialNum() {
		return this.genSerialNum;
	}
	public void setGenSerialNum(String genSerialNum) {
		this.genSerialNum = genSerialNum;
	}

	public String getGenFlags() {
		return this.genFlags;
	}
	public void setGenFlags(String genFlags) {
		this.genFlags = genFlags;
	}

	public String getGenCode() {
		return this.genCode;
	}
	public void setGenCode(String genCode) {
		this.genCode = genCode;
	}

}
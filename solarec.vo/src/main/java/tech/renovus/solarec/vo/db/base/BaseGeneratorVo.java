package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseGeneratorVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id_auto";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_GEN_COORD_LAT = "gen_coord_lat";
	public static final String COLUMN_GEN_COORD_LNG = "gen_coord_lng";
	public static final String COLUMN_GEN_RATE_POWER = "gen_rate_power";
	public static final String COLUMN_GEN_DATA_DATE_MAX = "gen_data_date_max";
	public static final String COLUMN_GEN_DATA_DATE_MIN = "gen_data_date_min";
	public static final String COLUMN_GEN_MODEL = "gen_model";
	public static final String COLUMN_GEN_SERIAL_NUM = "gen_serial_num";
	public static final String COLUMN_GEN_NAME = "gen_name";
	public static final String COLUMN_GEN_DESCRIPTION = "gen_description";
	public static final String COLUMN_GEN_CODE = "gen_code";
	public static final String COLUMN_GEN_FLAGS = "gen_flags";
	public static final String COLUMN_GEN_BRAND = "gen_brand";

	public static final int LENGTH_COLUMN_GEN_MODEL =  100;
	public static final int LENGTH_COLUMN_GEN_SERIAL_NUM =  100;
	public static final int LENGTH_COLUMN_GEN_NAME =  100;
	public static final int LENGTH_COLUMN_GEN_DESCRIPTION =  200;
	public static final int LENGTH_COLUMN_GEN_CODE =  100;
	public static final int LENGTH_COLUMN_GEN_FLAGS =  20;
	public static final int LENGTH_COLUMN_GEN_BRAND =  100;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.genFlags; }
	@Override public void setFlags(String genFlags) { this.genFlags = genFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer genId;
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Double genCoordLat;
	private @Getter @Setter Double genCoordLng;
	private @Getter @Setter Double genRatePower;
	private @Getter @Setter java.util.Date genDataDateMax;
	private @Getter @Setter java.util.Date genDataDateMin;
	private @Getter @Setter String genModel;
	private @Getter @Setter String genSerialNum;
	private @Getter @Setter String genName;
	private @Getter @Setter String genDescription;
	private @Getter @Setter String genCode;
	private @Getter @Setter String genFlags;
	private @Getter @Setter String genBrand;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGeneratorVo)) return false;
		
		BaseGeneratorVo aObj = (BaseGeneratorVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseGeneratorVo aObj = (BaseGeneratorVo) obj;
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCoordLat,aObj.genCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCoordLng,aObj.genCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.genRatePower,aObj.genRatePower)) {
			return false;
		}
		if (!ClassUtil.equals(this.genDataDateMax,aObj.genDataDateMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.genDataDateMin,aObj.genDataDateMin)) {
			return false;
		}
		if (!ClassUtil.equals(this.genModel,aObj.genModel)) {
			return false;
		}
		if (!ClassUtil.equals(this.genSerialNum,aObj.genSerialNum)) {
			return false;
		}
		if (!ClassUtil.equals(this.genName,aObj.genName)) {
			return false;
		}
		if (!ClassUtil.equals(this.genDescription,aObj.genDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.genCode,aObj.genCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.genFlags,aObj.genFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.genBrand,aObj.genBrand)) {
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

}
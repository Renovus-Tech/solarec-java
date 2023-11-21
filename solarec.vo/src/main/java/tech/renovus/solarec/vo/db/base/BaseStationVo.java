package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseStationVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_STA_DATA_DATE_MIN = "sta_data_date_min";
	public static final String COLUMN_STA_ID = "sta_id_auto";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_STA_COORD_LAT = "sta_coord_lat";
	public static final String COLUMN_STA_COORD_LNG = "sta_coord_lng";
	public static final String COLUMN_STA_DATA_DATE_MAX = "sta_data_date_max";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_STA_NAME = "sta_name";
	public static final String COLUMN_STA_DESCRIPTION = "sta_description";
	public static final String COLUMN_STA_FLAGS = "sta_flags";
	public static final String COLUMN_STA_CODE = "sta_code";

	public static final int LENGTH_COLUMN_STA_NAME =  100;
	public static final int LENGTH_COLUMN_STA_DESCRIPTION =  200;
	public static final int LENGTH_COLUMN_STA_FLAGS =  20;
	public static final int LENGTH_COLUMN_STA_CODE =  100;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.staFlags; }
	@Override public void setFlags(String staFlags) { this.staFlags = staFlags; }

	//--- Private properties --------------------
	private @Getter @Setter java.util.Date staDataDateMin;
	private @Getter @Setter Integer staId;
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Double staCoordLat;
	private @Getter @Setter Double staCoordLng;
	private @Getter @Setter java.util.Date staDataDateMax;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter String staName;
	private @Getter @Setter String staDescription;
	private @Getter @Setter String staFlags;
	private @Getter @Setter String staCode;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.staId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStationVo)) return false;
		
		BaseStationVo aObj = (BaseStationVo) obj;
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.staId != null) hashCode += this.staId.hashCode();
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStationVo aObj = (BaseStationVo) obj;
		if (!ClassUtil.equals(this.staDataDateMin,aObj.staDataDateMin)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staCoordLat,aObj.staCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.staCoordLng,aObj.staCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.staDataDateMax,aObj.staDataDateMax)) {
			return false;
		}
		if (!ClassUtil.equals(this.staName,aObj.staName)) {
			return false;
		}
		if (!ClassUtil.equals(this.staDescription,aObj.staDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.staFlags,aObj.staFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.staCode,aObj.staCode)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer staId, Integer cliId) {
		this.staId = staId;
		this.cliId = cliId;
	}

	public void setPk(BaseStationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.staId, aVo.cliId);
		}
	}

}
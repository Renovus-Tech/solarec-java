package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseWeaDefinitionVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_WEA_ID = "wea_id_auto";
	public static final String COLUMN_WEA_COORD_LNG = "wea_coord_lng";
	public static final String COLUMN_WEA_CHECK_TYPE = "wea_check_type";
	public static final String COLUMN_WEA_CHECK_FREQUENCY = "wea_check_frequency";
	public static final String COLUMN_WEA_COORD_LAT = "wea_coord_lat";
	public static final String COLUMN_WEA_NAME = "wea_name";
	public static final String COLUMN_WEA_DESCRIPTION = "wea_description";
	public static final String COLUMN_WEA_FLAGS = "wea_flags";

	public static final int LENGTH_COLUMN_WEA_NAME =  200;
	public static final int LENGTH_COLUMN_WEA_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_WEA_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.weaFlags; }
	@Override public void setFlags(String weaFlags) { this.weaFlags = weaFlags; }

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer weaId;
	private @Getter @Setter Double weaCoordLng;
	private @Getter @Setter Integer weaCheckType;
	private @Getter @Setter Integer weaCheckFrequency;
	private @Getter @Setter Double weaCoordLat;
	private @Getter @Setter String weaName;
	private @Getter @Setter String weaDescription;
	private @Getter @Setter String weaFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.weaId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseWeaDefinitionVo)) return false;
		
		BaseWeaDefinitionVo aObj = (BaseWeaDefinitionVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaId,aObj.weaId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.weaId != null) hashCode += this.weaId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseWeaDefinitionVo aObj = (BaseWeaDefinitionVo) obj;
		if (!ClassUtil.equals(this.weaCoordLng,aObj.weaCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCheckType,aObj.weaCheckType)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCheckFrequency,aObj.weaCheckFrequency)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCoordLat,aObj.weaCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaName,aObj.weaName)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaDescription,aObj.weaDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaFlags,aObj.weaFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer weaId) {
		this.cliId = cliId;
		this.weaId = weaId;
	}

	public void setPk(BaseWeaDefinitionVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.cliId, aVo.weaId);
		}
	}

}
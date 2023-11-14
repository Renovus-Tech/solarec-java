package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseWeaDefinitionVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_WEA_ID = "wea_id_auto";
	 public static final String COLUMN_WEA_NAME = "wea_name";
	 public static final String COLUMN_WEA_DESCRIPTION = "wea_description";
	 public static final String COLUMN_WEA_COORD_LAT = "wea_coord_lat";
	 public static final String COLUMN_WEA_COORD_LNG = "wea_coord_lng";
	 public static final String COLUMN_WEA_CHECK_TYPE = "wea_check_type";
	 public static final String COLUMN_WEA_CHECK_FREQUENCY = "wea_check_frequency";
	 public static final String COLUMN_WEA_FLAGS = "wea_flags";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.weaFlags; }
	@Override public void setFlags(String weaFlags) { this.weaFlags = weaFlags; }

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer weaId;
	 private String weaName;
	 private String weaDescription;
	 private Double weaCoordLat;
	 private Double weaCoordLng;
	 private Integer weaCheckType;
	 private Integer weaCheckFrequency;
	 private String weaFlags;

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
		if (obj == null) return false;
		if (!(obj instanceof BaseWeaDefinitionVo)) return false;
		
		BaseWeaDefinitionVo aObj = (BaseWeaDefinitionVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaId,aObj.weaId)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaName,aObj.weaName)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaDescription,aObj.weaDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCoordLat,aObj.weaCoordLat)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCoordLng,aObj.weaCoordLng)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCheckType,aObj.weaCheckType)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCheckFrequency,aObj.weaCheckFrequency)) {
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
			this.setPk(aVo.getCliId(), aVo.getWeaId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getWeaId() {
		return this.weaId;
	}
	public void setWeaId(Integer weaId) {
		this.weaId = weaId;
	}

	public String getWeaName() {
		return this.weaName;
	}
	public void setWeaName(String weaName) {
		this.weaName = weaName;
	}

	public String getWeaDescription() {
		return this.weaDescription;
	}
	public void setWeaDescription(String weaDescription) {
		this.weaDescription = weaDescription;
	}

	public Double getWeaCoordLat() {
		return this.weaCoordLat;
	}
	public void setWeaCoordLat(Double weaCoordLat) {
		this.weaCoordLat = weaCoordLat;
	}

	public Double getWeaCoordLng() {
		return this.weaCoordLng;
	}
	public void setWeaCoordLng(Double weaCoordLng) {
		this.weaCoordLng = weaCoordLng;
	}

	public Integer getWeaCheckType() {
		return this.weaCheckType;
	}
	public void setWeaCheckType(Integer weaCheckType) {
		this.weaCheckType = weaCheckType;
	}

	public Integer getWeaCheckFrequency() {
		return this.weaCheckFrequency;
	}
	public void setWeaCheckFrequency(Integer weaCheckFrequency) {
		this.weaCheckFrequency = weaCheckFrequency;
	}

	public String getWeaFlags() {
		return this.weaFlags;
	}
	public void setWeaFlags(String weaFlags) {
		this.weaFlags = weaFlags;
	}

}
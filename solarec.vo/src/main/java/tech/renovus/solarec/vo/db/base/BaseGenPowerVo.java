package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseGenPowerVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_PWR_WIND_SPEED = "pwr_wind_speed";
	public static final String COLUMN_PWR_AIR_DENSITY = "pwr_air_density";
	public static final String COLUMN_GEN_POWER = "gen_power";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer genId;
	private Double pwrWindSpeed;
	private Double pwrAirDensity;
	private Double genPower;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		if (this.pwrWindSpeed == null) {
			return false;
		}
		if (this.pwrAirDensity == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGenPowerVo)) return false;
		
		BaseGenPowerVo aObj = (BaseGenPowerVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.pwrWindSpeed,aObj.pwrWindSpeed)) {
			return false;
		}
		if (!ClassUtil.equals(this.pwrAirDensity,aObj.pwrAirDensity)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.pwrWindSpeed != null) hashCode += this.pwrWindSpeed.hashCode();
		if (this.pwrAirDensity != null) hashCode += this.pwrAirDensity.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseGenPowerVo aObj = (BaseGenPowerVo) obj;
		if (!ClassUtil.equals(this.genPower,aObj.genPower)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId, Double pwrWindSpeed, Double pwrAirDensity) {
		this.cliId = cliId;
		this.genId = genId;
		this.pwrWindSpeed = pwrWindSpeed;
		this.pwrAirDensity = pwrAirDensity;
	}

	public void setPk(BaseGenPowerVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.genId, aVo.pwrWindSpeed, aVo.pwrAirDensity);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

	public Double getPwrWindSpeed() {
		return this.pwrWindSpeed;
	}
	public void setPwrWindSpeed(Double pwrWindSpeed) {
		this.pwrWindSpeed = pwrWindSpeed;
	}

	public Double getPwrAirDensity() {
		return this.pwrAirDensity;
	}
	public void setPwrAirDensity(Double pwrAirDensity) {
		this.pwrAirDensity = pwrAirDensity;
	}

	public Double getGenPower() {
		return this.genPower;
	}
	public void setGenPower(Double genPower) {
		this.genPower = genPower;
	}

}
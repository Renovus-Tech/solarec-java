package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseEmberCountryOverviewVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_COUNTRY_OR_REGION = "country_or_region";
	 public static final String COLUMN_COUNTRY_CODE = "country_code";
	 public static final String COLUMN_YEAR = "year";
	 public static final String COLUMN_DEMAND_TWH = "demand_twh";
	 public static final String COLUMN_DEMAND_MWH_PER_CAPITA = "demand_mwh_per_capita";
	 public static final String COLUMN_EMISSIONS_INTENSITY_GCO2_PER_KWH = "emissions_intensity_gco2_per_kwh";
	 public static final String COLUMN_CONTINENT = "continent";
	 public static final String COLUMN_EMBER_REGION = "ember_region";
	 public static final String COLUMN_EU_FLAG = "eu_flag";
	 public static final String COLUMN_G20_FLAG = "g20_flag";
	 public static final String COLUMN_G7_FLAG = "g7_flag";
	 public static final String COLUMN_OECD_FLAG = "oecd_flag";
	 public static final String COLUMN_WORLD_DEMAND_RANK = "world_demand_rank";
	 public static final String COLUMN_REGION_DEMAND_RANK = "region_demand_rank";
	 public static final String COLUMN_OECD_DEMAND_RANK = "oecd_demand_rank";
	 public static final String COLUMN_EU_DEMAND_RANK = "eu_demand_rank";
	 public static final String COLUMN_LATEST_YEAR = "latest_year";
	 public static final String COLUMN_COAL_DEADLINE = "coal_deadline";
	 public static final String COLUMN_CLEAN_DEADLINE = "clean_deadline";

	//--- Private properties --------------------
	 private String countryOrRegion;
	 private String countryCode;
	 private Integer year;
	 private Double demandTwh;
	 private Double demandMwhPerCapita;
	 private Double emissionsIntensityGco2PerKwh;
	 private String continent;
	 private String emberRegion;
	 private Integer euFlag;
	 private Integer g20Flag;
	 private Integer g7Flag;
	 private Integer oecdFlag;
	 private Integer worldDemandRank;
	 private Integer regionDemandRank;
	 private Integer oecdDemandRank;
	 private Integer euDemandRank;
	 private Integer latestYear;
	 private Integer coalDeadline;
	 private Integer cleanDeadline;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.countryOrRegion == null) {
			return false;
		}
		if (this.year == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseEmberCountryOverviewVo)) return false;
		
		BaseEmberCountryOverviewVo aObj = (BaseEmberCountryOverviewVo) obj;
		if (!ClassUtil.equals(this.countryOrRegion,aObj.countryOrRegion)) {
			return false;
		}
		if (!ClassUtil.equals(this.year,aObj.year)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.countryOrRegion != null) hashCode += this.countryOrRegion.hashCode();
		if (this.year != null) hashCode += this.year.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseEmberCountryOverviewVo)) return false;
		
		BaseEmberCountryOverviewVo aObj = (BaseEmberCountryOverviewVo) obj;
		if (!ClassUtil.equals(this.countryOrRegion,aObj.countryOrRegion)) {
			return false;
		}
		if (!ClassUtil.equals(this.countryCode,aObj.countryCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.year,aObj.year)) {
			return false;
		}
		if (!ClassUtil.equals(this.demandTwh,aObj.demandTwh)) {
			return false;
		}
		if (!ClassUtil.equals(this.demandMwhPerCapita,aObj.demandMwhPerCapita)) {
			return false;
		}
		if (!ClassUtil.equals(this.emissionsIntensityGco2PerKwh,aObj.emissionsIntensityGco2PerKwh)) {
			return false;
		}
		if (!ClassUtil.equals(this.continent,aObj.continent)) {
			return false;
		}
		if (!ClassUtil.equals(this.emberRegion,aObj.emberRegion)) {
			return false;
		}
		if (!ClassUtil.equals(this.euFlag,aObj.euFlag)) {
			return false;
		}
		if (!ClassUtil.equals(this.g20Flag,aObj.g20Flag)) {
			return false;
		}
		if (!ClassUtil.equals(this.g7Flag,aObj.g7Flag)) {
			return false;
		}
		if (!ClassUtil.equals(this.oecdFlag,aObj.oecdFlag)) {
			return false;
		}
		if (!ClassUtil.equals(this.worldDemandRank,aObj.worldDemandRank)) {
			return false;
		}
		if (!ClassUtil.equals(this.regionDemandRank,aObj.regionDemandRank)) {
			return false;
		}
		if (!ClassUtil.equals(this.oecdDemandRank,aObj.oecdDemandRank)) {
			return false;
		}
		if (!ClassUtil.equals(this.euDemandRank,aObj.euDemandRank)) {
			return false;
		}
		if (!ClassUtil.equals(this.latestYear,aObj.latestYear)) {
			return false;
		}
		if (!ClassUtil.equals(this.coalDeadline,aObj.coalDeadline)) {
			return false;
		}
		if (!ClassUtil.equals(this.cleanDeadline,aObj.cleanDeadline)) {
			return false;
		}
		return true;
	}

	public void setPk(String countryOrRegion, Integer year) {
		this.countryOrRegion = countryOrRegion;
		this.year = year;
	}

	public void setPk(BaseEmberCountryOverviewVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getCountryOrRegion(), aVo.getYear());
		}
	}

	//--- Getters and Setters -------------------
	public String getCountryOrRegion() {
		return this.countryOrRegion;
	}
	public void setCountryOrRegion(String countryOrRegion) {
		this.countryOrRegion = countryOrRegion;
	}

	public String getCountryCode() {
		return this.countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getYear() {
		return this.year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getDemandTwh() {
		return this.demandTwh;
	}
	public void setDemandTwh(Double demandTwh) {
		this.demandTwh = demandTwh;
	}

	public Double getDemandMwhPerCapita() {
		return this.demandMwhPerCapita;
	}
	public void setDemandMwhPerCapita(Double demandMwhPerCapita) {
		this.demandMwhPerCapita = demandMwhPerCapita;
	}

	public Double getEmissionsIntensityGco2PerKwh() {
		return this.emissionsIntensityGco2PerKwh;
	}
	public void setEmissionsIntensityGco2PerKwh(Double emissionsIntensityGco2PerKwh) {
		this.emissionsIntensityGco2PerKwh = emissionsIntensityGco2PerKwh;
	}

	public String getContinent() {
		return this.continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getEmberRegion() {
		return this.emberRegion;
	}
	public void setEmberRegion(String emberRegion) {
		this.emberRegion = emberRegion;
	}

	public Integer getEuFlag() {
		return this.euFlag;
	}
	public void setEuFlag(Integer euFlag) {
		this.euFlag = euFlag;
	}

	public Integer getG20Flag() {
		return this.g20Flag;
	}
	public void setG20Flag(Integer g20Flag) {
		this.g20Flag = g20Flag;
	}

	public Integer getG7Flag() {
		return this.g7Flag;
	}
	public void setG7Flag(Integer g7Flag) {
		this.g7Flag = g7Flag;
	}

	public Integer getOecdFlag() {
		return this.oecdFlag;
	}
	public void setOecdFlag(Integer oecdFlag) {
		this.oecdFlag = oecdFlag;
	}

	public Integer getWorldDemandRank() {
		return this.worldDemandRank;
	}
	public void setWorldDemandRank(Integer worldDemandRank) {
		this.worldDemandRank = worldDemandRank;
	}

	public Integer getRegionDemandRank() {
		return this.regionDemandRank;
	}
	public void setRegionDemandRank(Integer regionDemandRank) {
		this.regionDemandRank = regionDemandRank;
	}

	public Integer getOecdDemandRank() {
		return this.oecdDemandRank;
	}
	public void setOecdDemandRank(Integer oecdDemandRank) {
		this.oecdDemandRank = oecdDemandRank;
	}

	public Integer getEuDemandRank() {
		return this.euDemandRank;
	}
	public void setEuDemandRank(Integer euDemandRank) {
		this.euDemandRank = euDemandRank;
	}

	public Integer getLatestYear() {
		return this.latestYear;
	}
	public void setLatestYear(Integer latestYear) {
		this.latestYear = latestYear;
	}

	public Integer getCoalDeadline() {
		return this.coalDeadline;
	}
	public void setCoalDeadline(Integer coalDeadline) {
		this.coalDeadline = coalDeadline;
	}

	public Integer getCleanDeadline() {
		return this.cleanDeadline;
	}
	public void setCleanDeadline(Integer cleanDeadline) {
		this.cleanDeadline = cleanDeadline;
	}

}
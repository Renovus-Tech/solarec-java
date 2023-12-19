package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseEmberCountryOverviewVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLEAN_DEADLINE = "clean_deadline";
	public static final String COLUMN_OECD_DEMAND_RANK = "oecd_demand_rank";
	public static final String COLUMN_EU_DEMAND_RANK = "eu_demand_rank";
	public static final String COLUMN_LATEST_YEAR = "latest_year";
	public static final String COLUMN_COAL_DEADLINE = "coal_deadline";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_DEMAND_TWH = "demand_twh";
	public static final String COLUMN_DEMAND_MWH_PER_CAPITA = "demand_mwh_per_capita";
	public static final String COLUMN_EMISSIONS_INTENSITY_GCO2_PER_KWH = "emissions_intensity_gco2_per_kwh";
	public static final String COLUMN_EU_FLAG = "eu_flag";
	public static final String COLUMN_G20_FLAG = "g20_flag";
	public static final String COLUMN_G7_FLAG = "g7_flag";
	public static final String COLUMN_OECD_FLAG = "oecd_flag";
	public static final String COLUMN_WORLD_DEMAND_RANK = "world_demand_rank";
	public static final String COLUMN_REGION_DEMAND_RANK = "region_demand_rank";
	public static final String COLUMN_COUNTRY_CODE = "country_code";
	public static final String COLUMN_COUNTRY_OR_REGION = "country_or_region";
	public static final String COLUMN_CONTINENT = "continent";
	public static final String COLUMN_EMBER_REGION = "ember_region";

	public static final int LENGTH_COLUMN_COUNTRY_CODE =  100;
	public static final int LENGTH_COLUMN_COUNTRY_OR_REGION =  100;
	public static final int LENGTH_COLUMN_CONTINENT =  100;
	public static final int LENGTH_COLUMN_EMBER_REGION =  100;

	//--- Private properties --------------------
	private @Getter @Setter Integer cleanDeadline;
	private @Getter @Setter Integer oecdDemandRank;
	private @Getter @Setter Integer euDemandRank;
	private @Getter @Setter Integer latestYear;
	private @Getter @Setter Integer coalDeadline;
	private @Getter @Setter Integer year;
	private @Getter @Setter Double demandTwh;
	private @Getter @Setter Double demandMwhPerCapita;
	private @Getter @Setter Double emissionsIntensityGco2PerKwh;
	private @Getter @Setter Integer euFlag;
	private @Getter @Setter Integer g20Flag;
	private @Getter @Setter Integer g7Flag;
	private @Getter @Setter Integer oecdFlag;
	private @Getter @Setter Integer worldDemandRank;
	private @Getter @Setter Integer regionDemandRank;
	private @Getter @Setter String countryCode;
	private @Getter @Setter String countryOrRegion;
	private @Getter @Setter String continent;
	private @Getter @Setter String emberRegion;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.year == null) {
			return false;
		}
		if (this.countryOrRegion == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseEmberCountryOverviewVo)) return false;
		
		BaseEmberCountryOverviewVo aObj = (BaseEmberCountryOverviewVo) obj;
		if (!ClassUtil.equals(this.year,aObj.year)) {
			return false;
		}
		if (!ClassUtil.equals(this.countryOrRegion,aObj.countryOrRegion)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.year != null) hashCode += this.year.hashCode();
		if (this.countryOrRegion != null) hashCode += this.countryOrRegion.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseEmberCountryOverviewVo aObj = (BaseEmberCountryOverviewVo) obj;
		if (!ClassUtil.equals(this.cleanDeadline,aObj.cleanDeadline)) {
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
		if (!ClassUtil.equals(this.demandTwh,aObj.demandTwh)) {
			return false;
		}
		if (!ClassUtil.equals(this.demandMwhPerCapita,aObj.demandMwhPerCapita)) {
			return false;
		}
		if (!ClassUtil.equals(this.emissionsIntensityGco2PerKwh,aObj.emissionsIntensityGco2PerKwh)) {
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
		if (!ClassUtil.equals(this.countryCode,aObj.countryCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.continent,aObj.continent)) {
			return false;
		}
		if (!ClassUtil.equals(this.emberRegion,aObj.emberRegion)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer year, String countryOrRegion) {
		this.year = year;
		this.countryOrRegion = countryOrRegion;
	}

	public void setPk(BaseEmberCountryOverviewVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.year, aVo.countryOrRegion);
		}
	}

}
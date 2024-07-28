package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbStatDefinitionVo;

public class StatDefinitionVo extends DbStatDefinitionVo {

	//--- Public constants ----------------------
	public static final Integer TYPE_CHART				= Integer.valueOf(1);
	public static final Integer TYPE_CALCULATION		= Integer.valueOf(2);
	public static final Integer TYPE_ALERT				= Integer.valueOf(3);

	//--- Public wind definitions ---------------
	public static final Integer ID_WIND_OVERVIEW		= Integer.valueOf(1);
	
	//--- Public solar definitions ---------------
	public static final String ID_SOLAR_OVERVIEW			= "Overview solar";
	public static final String ID_SOLAR_PERFORMANCE_INDEX	= "Performance Index solar";
	public static final String ID_SOLAR_CLIMATE				= "Climate solar";
	public static final String ID_SOLAR_POWER_CURVE			= "Power curve (Solar)";
	public static final String ID_SOLAR_OVERVIEW_CO2		= "Emissions CO2";
	public static final String ID_SOLAR_SALES				= "Certificates sales";
	public static final String ID_SOLAR_REVENUE				= "Certificates";
	
	//--- Constructors --------------------------
	public StatDefinitionVo() {
	}

	public StatDefinitionVo(Integer statDefId) {
		this.setPk(statDefId);
	}

}
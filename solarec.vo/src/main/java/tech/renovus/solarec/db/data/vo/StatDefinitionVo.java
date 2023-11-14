package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStatDefinitionVo;

public class StatDefinitionVo extends DbStatDefinitionVo {

	//--- Public constants ----------------------
	public static final Integer ID_POWER_CURVE			= Integer.valueOf(2);
	public static final Integer ID_PERFORMANCE_INDEX	= Integer.valueOf(3);
	public static final Integer ID_DATA_AVAILABILITY	= Integer.valueOf(4);
	public static final Integer ID_CLIMATE				= Integer.valueOf(5);
	public static final Integer ID_ALARMS				= Integer.valueOf(6);
	public static final Integer ID_CURTAILMENTS			= Integer.valueOf(7);
	public static final Integer ID_STOPPED_GEN			= Integer.valueOf(11);
	public static final Integer ID_POWER_RPM			= Integer.valueOf(12);
	public static final Integer ID_PITCH_WINDSPEED		= Integer.valueOf(13);
	public static final Integer ID_ALARMS_GENERATOR		= Integer.valueOf(14);
	public static final Integer ID_WIND_MEASUREMENTS	= Integer.valueOf(15);
	public static final Integer ID_NACELLE_DEVIATION	= Integer.valueOf(16);
	public static final Integer ID_BUDGET				= Integer.valueOf(17);
	public static final Integer ID_BUDGET_CUMULATIVE	= Integer.valueOf(18);
	public static final Integer ID_ALARMS_V2			= Integer.valueOf(19);
	public static final Integer ID_AVAILABILITY			= Integer.valueOf(20);
	public static final Integer ID_WIND_ROSE			= Integer.valueOf(24);
	
	public static final Integer TYPE_CHART				= Integer.valueOf(1);
	public static final Integer TYPE_CALCULATION		= Integer.valueOf(2);
	public static final Integer TYPE_ALERT				= Integer.valueOf(3);

	//--- Public wind definitions ---------------
	public static final Integer ID_WIND_OVERVIEW		= Integer.valueOf(1);
	
	//--- Public solar definitions ---------------
	public static final Integer ID_SOLAR_OVERVIEW			= Integer.valueOf(21);
	public static final Integer ID_SOLAR_PERFORMANCE_INDEX	= Integer.valueOf(22);
	public static final Integer ID_SOLAR_CLIMATE			= Integer.valueOf(23);
	public static final Integer ID_SOLAR_POWER_CURVE		= Integer.valueOf(27);
	
	//--- Constructors --------------------------
	public StatDefinitionVo() {
	}

	public StatDefinitionVo(Integer statDefId) {
		this.setPk(statDefId);
	}

}
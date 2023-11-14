package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStatTypeVo;

public class StatTypeVo extends DbStatTypeVo {

	//--- Public constants ----------------------
	public static final int COMMON_TIME_BASED_AVAILABILITY		= 101;
	public static final int COMMON_CAPACITY_FACTOR				= 102;
	public static final int COMMON_MWH_GENERATED_FOR_PERIOD		= 103;
	public static final int COMMON_AVERAGE_WIND_SPEED			= 104;
	public static final int COMMON_TEMPERATURE					= 105;

	public static final int LOCATION_MWH_GENERATED_PERIOD		= 201;
	public static final int LOCATION_AVEAGE_WIND_SPEED			= 202;
	public static final int LOCATOIN_MET_MAST_WIND_SPEED		= 203;
	public static final int LOCATION_MWH_FROM_WIND_TURBINES		= 204;

	public static final int MANUFACTURER_POWER_CURVE			= 301;
	
	public static final int GENERATOR_IEC_CURVE					= 401;
	public static final int GENERATOR_OPERATION_DATA			= 402;
	public static final int GENERATOR_PERFORMANCE_INDEX			= 403;
	public static final int GENERATOR_PERFORMANCE_MWH			= 404;
	
	public static final int WIND_SPEED							= 501;
	public static final int WIND_DIRECTIOn						= 502;
	public static final int WIN_POWER_AVERAGE					= 503;
	

	
	//--- Constructors --------------------------
	public StatTypeVo() {
	}

	public StatTypeVo(Integer statTypeId) {
		this.setPk(statTypeId);
	}

}
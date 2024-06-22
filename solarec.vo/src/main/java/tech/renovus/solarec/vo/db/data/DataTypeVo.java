package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataTypeVo;

public class DataTypeVo extends DbDataTypeVo {

	//--- Public constants ----------------------
	public static final int TYPE_INVALID												= -1;
	
	public static final int TYPE_WEATHER_WIND_SPEED_100									= 101;
	public static final int TYPE_WEATHER_WIND_DIRECTION_100								= 102;
	public static final int TYPE_WEATHER_ATMOSPHERE_PRESURE								= 103;
	public static final int TYPE_WEATHER_HUMIDITY										= 104;
	public static final int TYPE_WEATHER_AIR_TEMPERATURE_100							= 105;
	public static final int TYPE_WEATHER_RADIATION_FLUX									= 106;
	
	public static final int TYPE_LOCATION_OUTPUT_CAPACITY								= 401;
	public static final int TYPE_LOCATION_POWER_KWH										= 402;
	public static final int TYPE_LOCATION_CONTROL_COMMAND_SENT							= 403;
	
	public static final int TYPE_LOCATION_SOLAR_OUTPUT_CAPACITY							= 500; //MW
	
	public static final int TYPE_SOLAR_INVERTER_DC_POWER								= 501; //Production DC KW //totalDCProductionMwh
	public static final int TYPE_SOLAR_INVERTER_AC_POWER								= 502; //Production KW //totalACProductionMwh

	public static final int TYPE_SOLAR_STATION_AMBIENT_TEMPERATURE						= 503; //Temperature
	public static final int TYPE_SOLAR_STATION_MODULE_TEMPERATURE						= 504; //Cell temperature
	public static final int TYPE_SOLAR_STATION_IRRADIATION								= 505; //Irradiation
	public static final int TYPE_SOLAR_STATION_TOTAL_CLOUD_COVER						= 506; //Cloud cover
	public static final int TYPE_SOLAR_STATION_PRECIPITATION							= 507; //Precipitation
	
	public static final int TYPE_COUNTRY_EMISSIONS_INTENSITY_GCO2_PER_KWH				= 901; //emissions_intensity_gco2_per_kwh
	
	//--- Constructors --------------------------
	public DataTypeVo() {
	}

	public DataTypeVo(Integer dataTypeId) {
		this.setPk(dataTypeId);
	}

	public static String toString(Integer dataTypeId) {
		if (dataTypeId == null) {
			return null;
		}
		
		switch (dataTypeId.intValue()) {
			case TYPE_WEATHER_WIND_SPEED_100: 			return "TYPE_WEATHER_WIND_SPEED_100";
			case TYPE_WEATHER_WIND_DIRECTION_100: 		return "TYPE_WEATHER_WIND_DIRECTION_100";
			case TYPE_WEATHER_ATMOSPHERE_PRESURE: 		return "TYPE_WEATHER_ATMOSPHERE_PRESURE";
			case TYPE_WEATHER_HUMIDITY: 				return "TYPE_WEATHER_HUMIDITY";
			case TYPE_WEATHER_AIR_TEMPERATURE_100: 		return "TYPE_WEATHER_AIR_TEMPERATURE_100";
			case TYPE_WEATHER_RADIATION_FLUX: 			return "TYPE_WEATHER_RADIATION_FLUX";

			default: return "n/a";
		}
	}

}
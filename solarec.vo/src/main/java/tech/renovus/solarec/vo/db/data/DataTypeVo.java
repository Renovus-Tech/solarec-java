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
	
	public static final int TYPE_GENERATOR_WIND_SPEED_METERS_PER_SECOND					= 201;
	public static final int TYPE_GENERATOR_WIND_DIRECTION_DEGREE						= 202; 
	public static final int TYPE_GENERATOR_OK_SECONDS									= 203;
	public static final int TYPE_GENERATOR_ERROR_CODE									= 204;
	public static final int TYPE_GENERATOR_POWER_KWH									= 205;
	public static final int TYPE_GENERATOR_DIRECTION_DEGREE								= 206;
	public static final int TYPE_GENERATOR_PITCH										= 207; //ángulo de las palas
	public static final int TYPE_GENERATOR_EXPECTED_PRODUCTION_WTG_DATA_NO_AIRDENSITY	= 208;
	public static final int TYPE_GENERATOR_PRICE_INDEX									= 209;
	public static final int TYPE_GENERATOR_GENERATOR_RPM_AVG							= 210;
	public static final int TYPE_GENERATOR_GENERATOR_BEARING_TEMP_AVG					= 211;
	public static final int TYPE_GENERATOR_GENERATOR_PHASE1_TEMP_AVG					= 212;
	public static final int TYPE_GENERATOR_GENERATOR_PHASE2_TEMP_AVG					= 213;
	public static final int TYPE_GENERATOR_GENERATOR_PHASE3_TEMP_AVG					= 214;
	public static final int TYPE_GENERATOR_GENERATOR_BEARING2_TEMP_AVG					= 215;
	public static final int TYPE_GENERATOR_GENERATOR_COOLINGWATER_TEMP_AVG				= 216;
	public static final int TYPE_GENERATOR_HYDRAULIC_OIL_TEMP_AVG						= 217;
	public static final int TYPE_GENERATOR_HYDRAULIC_OIL_PRESSURE_AVG					= 218;
	public static final int TYPE_GENERATOR_GEAR_BEARING_TEMP_AVG						= 219;
	public static final int TYPE_GENERATOR_GEAR_BEARING_TEMPB_AVG						= 220;
	public static final int TYPE_GENERATOR_GEAR_BEARING_TEMPC_AVG						= 221;
	public static final int TYPE_GENERATOR_GEAR_OIL_TEMPINLET_AVG						= 222;
	public static final int TYPE_GENERATOR_GEAR_MAINTANK_OILTEMP_AVG					= 223;
	public static final int TYPE_GENERATOR_NACELLE_TEMP_AVG								= 224;
	public static final int TYPE_GENERATOR_ROTOR_RPM_AVG								= 225;
	public static final int TYPE_GENERATOR_ROTOR_RPM2_AVG								= 226;
	public static final int TYPE_GENERATOR_AMBIENT_TEMP_AVG								= 227;
	public static final int TYPE_GENERATOR_AMBIENT_WINDDIR_RELATIVEWINDSENSOR2_AVG		= 228;
	public static final int TYPE_GENERATOR_AMBIENT_WINDDIR_RELATIVEWINDSENSOR1_AVG		= 229;
	public static final int TYPE_GENERATOR_AMBIENT_WINDSPEED_WINDSENSOR2_AVG			= 230;
	public static final int TYPE_GENERATOR_AMBIENT_WINDSPEED_WINDSENSOR1_AVG			= 231;
	public static final int TYPE_GENERATOR_AMBIENT_SELECTEDWINDSENSOR_ACTIVEWINDSENSOR	= 232;
	public static final int TYPE_GENERATOR_GRID_PRODUCTION_REACTIVEPOWER_AVG			= 233;
	public static final int TYPE_GENERATOR_GRID_PRODUCTION_POSSIBLEPOWER_AVG			= 234;
	public static final int TYPE_GENERATOR_ACTIVE_POWER_LIMIT							= 235;
	public static final int TYPE_GENERATOR_GRID_PRODUCTION_POWER_INTERNALDERATESTATE	= 236;
	public static final int TYPE_GENERATOR_CONTROLLER_GROUND_TEMP_AVG					= 237;
	public static final int TYPE_GENERATOR_CONTROLLER_TOP_TEMP_AVG						= 238;
	public static final int TYPE_GENERATOR_CONTROLLER_HUB_TEMP_AVG						= 239;
	public static final int TYPE_GENERATOR_BLADES_BLADEA_LOAD							= 240;
	public static final int TYPE_GENERATOR_BLADES_BLADEB_LOAD							= 241;
	public static final int TYPE_GENERATOR_BLADES_BLADEC_LOAD							= 242;
	public static final int TYPE_GENERATOR_BLADES_BLADEA_BLPITCHANGLE_AVG				= 243;
	public static final int TYPE_GENERATOR_BLADES_BLADEB_BLPITCHANGLE_AVG				= 244;
	public static final int TYPE_GENERATOR_BLADES_BLADEC_BLPITCHANGLE_AVG				= 245;
	public static final int TYPE_GENERATOR_BLADES_TILTYAWCONTROL_AMPLTILT_AVG			= 246;
	public static final int TYPE_GENERATOR_BLADES_TILTYAWCONTROL_AMPLYAW_AVG			= 247;
	public static final int TYPE_GENERATOR_HVTRAFO_PHASE1_TEMP_AVG						= 248;
	public static final int TYPE_GENERATOR_HVTRAFO_PHASE2_TEMP_AVG						= 249;
	public static final int TYPE_GENERATOR_HVTRAFO_PHASE3_TEMP_AVG						= 250;
	public static final int TYPE_GENERATOR_YAWYAWCONTROLYAWCONTROLAUTOYAWACTIVE			= 251;
	public static final int TYPE_GENERATOR_AMB_LAPM_ACTSECTORINPERIOD					= 252;
	public static final int TYPE_GENERATOR_AMB_LAPM_SECTORTIMEINPERIOD					= 253;
	public static final int TYPE_GENERATOR_AMB_LAPM_WINDDIRECTIONINPERIOD				= 254;
	public static final int TYPE_GENERATOR_AMB_LAPM_PARTIALLOADMODEINPERIOD				= 255;
	public static final int TYPE_GENERATOR_AMBIENTE_WINDSPEED_ESTIMATED_AVG				= 256;
	public static final int TYPE_GENERATOR_AMBIENTE_WINDSPEED_STDDEV					= 257;
	
	public static final int TYPE_STATION_WIND_SPEED_METERS_PER_SECOND					= 301;
	public static final int TYPE_STATION_WIND_DIRECTION_DEGREE							= 302;
	public static final int TYPE_STATION_TEMPERATURE									= 303;
	public static final int TYPE_STATION_PRESSURE										= 304;
	public static final int TYPE_STATION_HUMIDITY										= 305;
	
	public static final int TYPE_STATION_WEATHER_TEMPERATURE							= 393;
	public static final int TYPE_STATION_WEATHER_PRESSURE								= 394;
	public static final int TYPE_STATION_WEATHER_HUMIDITY								= 395;
	
	public static final int TYPE_LOCATION_OUTPUT_CAPACITY								= 401;
	public static final int TYPE_LOCATION_POWER_KWH										= 402;
	public static final int TYPE_LOCATION_CONTROL_COMMAND_SENT							= 403;
	
	public static final int TYPE_LOCATION_SOLAR_OUTPUT_CAPACITY							= 500; //MW
	
	public static final int TYPE_SOLAR_INVERTER_DC_POWER								= 501; //Production DC KW //totalDCProductionMwh
	public static final int TYPE_SOLAR_INVERTER_AC_POWER								= 502; //Production KW //totalACProductionMwh

	public static final int TYPE_SOLAR_STATION_AMBIENT_TEMPERATURE						= 503; //Temperature
	public static final int TYPE_SOLAR_STATION_MODULE_TEMPERATURE						= 504; //Cell temperature
	public static final int TYPE_SOLAR_STATION_IRRADIATION								= 505; //Irradiation
	public static final int TYPE_SOLAR_STATION_TOTAL_CLOUD_COVER						= 506; //Irradiation
	public static final int TYPE_SOLAR_STATION_PRECIPITATION							= 507; //Irradiation
	
	public static final int TYPE_COUNTRY_EMISSIONS_INTENSITY_GCO2_PER_KWH				= 901; //emissions_intensity_gco2_per_kwh
	
	//--- Constructors --------------------------
	public DataTypeVo() {
	}

	public DataTypeVo(Integer dataTypeId) {
		this.setPk(dataTypeId);
	}

	public static String toString(Integer dataTypeId) {
		if (dataTypeId == null) return null;
		
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
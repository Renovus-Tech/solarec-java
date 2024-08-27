package tech.renovus.solarec.api.rest.controller;

public class EndPointFactory {

	//--- Public constants ----------------------
	public static final String REST_API												= "/api/rest";
	public static final String REST_API_SECURITY									= REST_API + "/security";
	public static final String RESP_API_ADMIN										= REST_API + "/admin";
	public static final String REST_API_BACKGROUND									= REST_API + "/background";
	public static final String REST_API_CHART										= REST_API + "/chart";
	public static final String REST_API_WEATHER										= REST_API + "/weather";
	public static final String REST_API_REPORT										= REST_API + "/report";

	public static final String REST_SECURITY_AUTHENTICATE							= REST_API_SECURITY + "/authenticate";
	public static final String REST_SECURITY_AUTHENTICATE_RESET						= REST_API_SECURITY + "/authenticate/reset";
	public static final String REST_SECURITY_AUTHENTICATE_LOCATION					= REST_API_SECURITY + "/authenticate/location";
	
	public static final String REST_ADMINISTRATION_CLIENTS	 						= RESP_API_ADMIN + "/clients";
	public static final String REST_ADMINISTRATION_LOCATIONS 						= RESP_API_ADMIN + "/locations";
	public static final String REST_ADMINISTRATION_GENERATORS						= RESP_API_ADMIN + "/generators";
	public static final String REST_ADMINISTRATION_STATIONS							= RESP_API_ADMIN + "/stations";
	public static final String REST_ADMINISTRATION_DOCUMENTS						= RESP_API_ADMIN + "/documents";
	public static final String REST_ADMINISTRATION_DOCUMENTS_CATEGORIES				= RESP_API_ADMIN + "/documents/categories";
	public static final String REST_ADMINISTRATION_DATA_DEFINITIONS					= RESP_API_ADMIN + "/dataDefinitions";
	public static final String REST_ADMINISTRATION_DATA_DEFINITIONS_TRIGGERS		= RESP_API_ADMIN + "/dataDefinitions/triggers";
	
	public static final String REST_ADMINISTRATION_DOCUMENTS_DOWNLOAD				= REST_ADMINISTRATION_DOCUMENTS + "/download";

	public static final String REST_BACKGROUND_PROCESSING							= REST_API_BACKGROUND + "/processing";
	public static final String REST_BACKGROUND_TRIGGER								= REST_API_BACKGROUND + "/trigger";

	public static final String REST_CHART_DASHBOARD									= REST_API_CHART + "/dashboard";
	public static final String REST_CHART_DASHBOARD_NOW								= REST_API_CHART + "/dashboard/now";
	public static final String REST_CHART_OVERVIEW									= REST_API_CHART + "/overview";
	public static final String REST_CHART_POWER_CURVE								= REST_API_CHART + "/powerCurve";
	public static final String REST_CHART_POWER_RPM									= REST_API_CHART + "/powerRpm";
	public static final String REST_CHART_PITCH_WINDSPEED							= REST_API_CHART + "/pitchWindspeed";
	public static final String REST_CHART_PERFORMANCE_INDEX							= REST_API_CHART + "/performanceIndex";
	public static final String REST_CHART_DATA_AVAILABILITY							= REST_API_CHART + "/dataAvailability";
	public static final String REST_CHART_AVAILABILITY								= REST_API_CHART + "/availability";
	public static final String REST_CHART_CLIMATE									= REST_API_CHART + "/climate";
	public static final String REST_CHART_REVENUE									= REST_API_CHART + "/revenue";
	public static final String REST_CHART_REVENUE_SALES								= REST_API_CHART + "/revenue/sales";
	
	public static final String REST_API_WEATHER_HISTORY								= REST_API_WEATHER + "/history";
	
	public static final String REST_API_REPORT_CONFIGURE							= REST_API_REPORT + "/configure";
	public static final String REST_API_REPORT_GENERATE								= REST_API_REPORT + "/generate";
	
	//--- Solar public constants ----------------
	public static final String REST_API_SOLAR										= REST_API + "/solar";
	public static final String REST_API_SOLOAR_POWER_CURVE							= REST_API_SOLAR + "/powerCurve";
	public static final String REST_API_SOLAR_OVERVIEW								= REST_API_SOLAR + "/overview";
	public static final String REST_API_SOLAR_DASHBOARD								= REST_API_SOLAR + "/dashboard";
	public static final String REST_API_SOLAR_DASHBOARD_NOW							= REST_API_SOLAR + "/dashboard/now";
	public static final String REST_API_SOLAR_WEATHER								= REST_API_SOLAR + "/weather";
	public static final String REST_API_SOLAR_CLIMATE								= REST_API_SOLAR + "/climate";
	public static final String REST_API_SOLAR_PERFORMANCE_INDEX						= REST_API_SOLAR + "/performanceIndex";
	
	public static final String REST_API_SOLAR_OVERVIEW_ALERTS						= REST_API_SOLAR_OVERVIEW + "/alerts";
	public static final String REST_API_SOLAR_OVERVIEW_CO2							= REST_API_SOLAR_OVERVIEW + "/co2";
}

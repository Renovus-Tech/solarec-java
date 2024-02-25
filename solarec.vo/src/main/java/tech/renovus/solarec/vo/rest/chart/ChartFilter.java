package tech.renovus.solarec.vo.rest.chart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.renovus.solarec.util.CollectionUtil;

public class ChartFilter implements IFilter {
	
	//--- Public constants ----------------------
	public static final String PERIOD_YESTERDAY				= "y";
	public static final String PERIOD_BEFORE_YESTERDAY		= "by";
	public static final String PERIOD_30_DAYS				= "30d";
	public static final String PERIOD_4_WEEKS				= "4w";
	public static final String PERIOD_12_WEEKS				= "12w";
	public static final String PERIOD_6_MONTH				= "6m";
	public static final String PERIOD_12_MONTH				= "12m";
	public static final String PERIOD_CURRENT_YEAR			= "cy";
	public static final String PERIOD_CURRENT_YEAR_DELTA	= "cy-";
	public static final String PERIOD_CURRENT_MONTH			= "cm";
	public static final String PERIOD_CURRENT_WEEK			= "cw";
	
	public static final String PERIOD_FILTER_WEEK		= "fw";
	public static final String PERIOD_FILTER_WEEK_4		= "fw4";
	public static final String PERIOD_FILTER_MONTH		= "fm";
	public static final String PERIOD_FILTER_MONTH_6	= "fm6";
	public static final String PERIOD_FILTER_YEAR		= "fy";

	public static final String GROUP_BY_HOUR	= "hour";
	public static final String GROUP_BY_DAY		= "day";
	public static final String GROUP_BY_WEEK	= "week";
	public static final String GROUP_BY_MONTH	= "month";
	public static final String GROUP_BY_YEAR	= "year";
	
	public static final String TYPE_JSON		= "json";
	public static final String TYPE_CHART		= "chart";
	
	//--- Private properties --------------------
	private String title;
	private Integer client;
	private Integer location;
	private List<Integer> generators;
	private List<Integer> stations;
	private Date from;
	private Date to;
	private String period;
	private String groupBy;
	private String type;
	private List<Angle> angles;
	private Boolean sendAverage;
	
	private boolean forReport = false;
	
	//--- Constructors --------------------------
	public ChartFilter() {}
	
	public ChartFilter(String period) {
		this.period = period;
	}
	
	//--- Public methods ------------------------
	public ChartFilter createCopy() {
		ChartFilter result = new ChartFilter();

		result.title		= this.title;
		result.client		= this.client;
		result.location		= this.location;
		result.from			= this.from;
		result.to			= this.to;
		result.period		= this.period;
		result.groupBy		= this.groupBy;
		result.type			= this.type;

		result.generators	= new ArrayList<>();
		result.stations		= new ArrayList<>();
		result.angles		= new ArrayList<>();

		CollectionUtil.addAll(result.generators, this.generators);
		CollectionUtil.addAll(result.stations, this.stations);
		CollectionUtil.addAll(result.angles, this.angles);
		
		return result;
	}
	
	//--- Getters and Setters -------------------
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer locationId) {
		this.location = locationId;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public List<Integer> getGenerators() {
		return generators;
	}
	public void setGenerators(List<Integer> generatorsIds) {
		this.generators = generatorsIds;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getClient() {
		return client;
	}
	public void setClient(Integer clientId) {
		this.client = clientId;
	}
	public List<Integer> getStations() {
		return stations;
	}
	public void setStations(List<Integer> stationsIds) {
		this.stations = stationsIds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Angle> getAngles() {
		return angles;
	}
	public void setAngles(List<Angle> angles) {
		this.angles = angles;
	}

	public Boolean getSendAverage() {
		return sendAverage;
	}

	public void setSendAverage(Boolean sendAverage) {
		this.sendAverage = sendAverage;
	}

	public boolean isForReport() {
		return forReport;
	}

	public void setForReport(boolean forReport) {
		this.forReport = forReport;
	}
}

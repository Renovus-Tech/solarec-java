package tech.renovus.solarec.business.impl.chart.base;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chart {

	//--- Public constants ----------------------
	public static final String GROUP_BY_HOUR	= "hour";
	public static final String GROUP_BY_DAY		= "day";
	public static final String GROUP_BY_WEEK	= "week";
	public static final String GROUP_BY_MONTH	= "month";
	
	//--- Getters and Setters -------------------
	private Integer client;
	private Integer location;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") private Date from;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") private Date to;
	private String groupBy;
	private Boolean sendAverage;
	
	private List<Angle> angles;
	private List<Integer> generators;
	private List<Integer> stations;
	
	//--- Getters and Setters -------------------
	public Integer getClient() {
		return client;
	}
	public void setClient(Integer client) {
		this.client = client;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
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
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public List<Integer> getGenerators() {
		return generators;
	}
	public void setGenerators(List<Integer> generators) {
		this.generators = generators;
	}
	public List<Integer> getStations() {
		return stations;
	}
	public void setStations(List<Integer> stations) {
		this.stations = stations;
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
}

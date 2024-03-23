package tech.renovus.solarec.inverters.brand.sofar.api;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "stationId", "timeType", "startTime", "endTime" })
@Generated("jsonschema2pojo")
public class StationHistoryDataRequest {

	@JsonProperty("stationId")
	private Integer stationId;
	@JsonProperty("timeType")
	private Integer timeType;
	@JsonProperty("startTime")
	private String startTime;
	@JsonProperty("endTime")
	private String endTime;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("stationId")
	public Integer getStationId() {
		return stationId;
	}

	@JsonProperty("stationId")
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public StationHistoryDataRequest withStationId(Integer stationId) {
		this.stationId = stationId;
		return this;
	}

	@JsonProperty("timeType")
	public Integer getTimeType() {
		return timeType;
	}

	@JsonProperty("timeType")
	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public StationHistoryDataRequest withTimeType(Integer timeType) {
		this.timeType = timeType;
		return this;
	}

	@JsonProperty("startTime")
	public String getStartTime() {
		return startTime;
	}

	@JsonProperty("startTime")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public StationHistoryDataRequest withStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	@JsonProperty("endTime")
	public String getEndTime() {
		return endTime;
	}

	@JsonProperty("endTime")
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public StationHistoryDataRequest withEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public StationHistoryDataRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	
	//--- Added code ----------------------------
	public static final Integer TIME_TYPE_FRAME		= Integer.valueOf(1);
	public static final Integer TIME_TYPE_DAY		= Integer.valueOf(2);
	public static final Integer TIME_TYPE_MONTH		= Integer.valueOf(3);
	public static final Integer TIME_TYPE_YEAR		= Integer.valueOf(4);
}
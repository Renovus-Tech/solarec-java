package tech.renovus.solarec.inverters.brand.huawei.api;

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
@JsonPropertyOrder({ "stationCodes", "collectTime" })
@Generated("jsonschema2pojo")
public class PowerStationMonthRequest {

	@JsonProperty("stationCodes")
	private String stationCodes;
	@JsonProperty("collectTime")
	private Long collectTime;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("stationCodes")
	public String getStationCodes() {
		return stationCodes;
	}

	@JsonProperty("stationCodes")
	public void setStationCodes(String stationCodes) {
		this.stationCodes = stationCodes;
	}

	public PowerStationMonthRequest withStationCodes(String stationCodes) {
		this.stationCodes = stationCodes;
		return this;
	}

	@JsonProperty("collectTime")
	public Long getCollectTime() {
		return collectTime;
	}

	@JsonProperty("collectTime")
	public void setCollectTime(Long collectTime) {
		this.collectTime = collectTime;
	}

	public PowerStationMonthRequest withCollectTime(Long collectTime) {
		this.collectTime = collectTime;
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

	public PowerStationMonthRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
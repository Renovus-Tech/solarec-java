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
@JsonPropertyOrder({ "deviceType", "stationId" })
@Generated("jsonschema2pojo")
public class StationDeviceRequest extends ListRequest {
	
	@JsonProperty("deviceType")
	private String deviceType;
	@JsonProperty("stationId")
	private Integer stationId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("deviceType")
	public String getDeviceType() {
		return deviceType;
	}

	@JsonProperty("deviceType")
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public StationDeviceRequest withDeviceType(String deviceType) {
		this.deviceType = deviceType;
		return this;
	}

	@JsonProperty("stationId")
	public Integer getStationId() {
		return stationId;
	}

	@JsonProperty("stationId")
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public StationDeviceRequest withStationId(Integer stationId) {
		this.stationId = stationId;
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

	public StationDeviceRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}
	
	//--- Added code ----------------------------
	@Override public StationDeviceRequest withPage(Integer page) {
		this.setPage(page);
		return this;
	}

	@Override public StationDeviceRequest withSize(Integer size) {
		this.setSize(size);
		return this;
	}
	
}
package tech.renovus.solarec.inverters.brand.fronius.api.history.device;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.inverters.brand.fronius.api.ErrorResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.Links;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "pvSystemId", "deviceId", "data", "links", "totalDataCount" })
@Generated("jsonschema2pojo")
public class DeviceHistoryDataResponse extends ErrorResponse {

	@JsonProperty("pvSystemId")
	private String pvSystemId;
	@JsonProperty("deviceId")
	private String deviceId;
	@JsonProperty("data")
	private List<Datum> data;
	@JsonProperty("links")
	private Links links;
	@JsonProperty("totalDataCount")
	private Integer totalDataCount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("pvSystemId")
	public String getPvSystemId() {
		return pvSystemId;
	}

	@JsonProperty("pvSystemId")
	public void setPvSystemId(String pvSystemId) {
		this.pvSystemId = pvSystemId;
	}

	public DeviceHistoryDataResponse withPvSystemId(String pvSystemId) {
		this.pvSystemId = pvSystemId;
		return this;
	}

	@JsonProperty("deviceId")
	public String getDeviceId() {
		return deviceId;
	}

	@JsonProperty("deviceId")
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceHistoryDataResponse withDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
	}

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	public DeviceHistoryDataResponse withData(List<Datum> data) {
		this.data = data;
		return this;
	}

	@JsonProperty("links")
	public Links getLinks() {
		return links;
	}

	@JsonProperty("links")
	public void setLinks(Links links) {
		this.links = links;
	}

	public DeviceHistoryDataResponse withLinks(Links links) {
		this.links = links;
		return this;
	}

	@JsonProperty("totalDataCount")
	public Integer getTotalDataCount() {
		return totalDataCount;
	}

	@JsonProperty("totalDataCount")
	public void setTotalDataCount(Integer totalDataCount) {
		this.totalDataCount = totalDataCount;
	}

	public DeviceHistoryDataResponse withTotalDataCount(Integer totalDataCount) {
		this.totalDataCount = totalDataCount;
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

	public DeviceHistoryDataResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
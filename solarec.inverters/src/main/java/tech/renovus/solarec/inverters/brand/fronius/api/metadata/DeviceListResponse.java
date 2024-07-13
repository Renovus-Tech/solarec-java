package tech.renovus.solarec.inverters.brand.fronius.api.metadata;

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
@JsonPropertyOrder({ "deviceIds", "links" })
@Generated("jsonschema2pojo")
public class DeviceListResponse extends ErrorResponse {

	@JsonProperty("deviceIds")
	private List<String> deviceIds;
	@JsonProperty("links")
	private Links links;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("deviceIds")
	public List<String> getDeviceIds() {
		return deviceIds;
	}

	@JsonProperty("deviceIds")
	public void setDeviceIds(List<String> deviceIds) {
		this.deviceIds = deviceIds;
	}

	public DeviceListResponse withDeviceIds(List<String> deviceIds) {
		this.deviceIds = deviceIds;
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

	public DeviceListResponse withLinks(Links links) {
		this.links = links;
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

	public DeviceListResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
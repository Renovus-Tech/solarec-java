package tech.renovus.solarec.inverters.brand.sofar.api;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "deviceListItems" })
@Generated("jsonschema2pojo")
public class StationDeviceListResponse extends BasicResponse {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("deviceListItems")
	private List<Device> deviceListItems;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	public StationDeviceListResponse withTotal(Integer total) {
		this.total = total;
		return this;
	}

	@JsonProperty("deviceListItems")
	public List<Device> getDeviceListItems() {
		return deviceListItems;
	}

	@JsonProperty("deviceListItems")
	public void setDeviceListItems(List<Device> Devices) {
		this.deviceListItems = Devices;
	}

	public StationDeviceListResponse withDeviceListItems(List<Device> deviceListItems) {
		this.deviceListItems = deviceListItems;
		return this;
	}

	@Override
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public StationDeviceListResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
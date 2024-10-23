
package tech.renovus.solarec.inverters.brand.enphase.api.devices;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.inverters.brand.enphase.api.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "system_id", "total_devices", "items", "devices" })
@Generated("jsonschema2pojo")
public class DevicesResponse extends ErrorResponse {

	@JsonProperty("system_id")
	private Integer systemId;
	@JsonProperty("total_devices")
	private Integer totalDevices;
	@JsonProperty("items")
	private String items;
	@JsonProperty("devices")
	private Devices devices;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("system_id")
	public Integer getSystemId() {
		return systemId;
	}

	@JsonProperty("system_id")
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public DevicesResponse withSystemId(Integer systemId) {
		this.systemId = systemId;
		return this;
	}

	@JsonProperty("total_devices")
	public Integer getTotalDevices() {
		return totalDevices;
	}

	@JsonProperty("total_devices")
	public void setTotalDevices(Integer totalDevices) {
		this.totalDevices = totalDevices;
	}

	public DevicesResponse withTotalDevices(Integer totalDevices) {
		this.totalDevices = totalDevices;
		return this;
	}

	@JsonProperty("items")
	public String getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems(String items) {
		this.items = items;
	}

	public DevicesResponse withItems(String items) {
		this.items = items;
		return this;
	}

	@JsonProperty("devices")
	public Devices getDevices() {
		return devices;
	}

	@JsonProperty("devices")
	public void setDevices(Devices devices) {
		this.devices = devices;
	}

	public DevicesResponse withDevices(Devices devices) {
		this.devices = devices;
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
	public DevicesResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

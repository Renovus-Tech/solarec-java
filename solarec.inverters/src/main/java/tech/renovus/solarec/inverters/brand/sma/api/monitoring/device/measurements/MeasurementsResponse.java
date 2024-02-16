
package tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements;

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

import tech.renovus.solarec.inverters.brand.sma.api.ErrorResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.Plant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "plant", "device", "setType", "resolution", "set", "total" })
@Generated("jsonschema2pojo")
public class MeasurementsResponse extends ErrorResponse {

	@JsonProperty("plant")
	private Plant plant;
	@JsonProperty("device")
	private Device device;
	@JsonProperty("setType")
	private String setType;
	@JsonProperty("resolution")
	private String resolution;
	@JsonProperty("set")
	private List<Set> set;
	@JsonProperty("total")
	private Total total;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("plant")
	public Plant getPlant() {
		return plant;
	}

	@JsonProperty("plant")
	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public MeasurementsResponse withPlant(Plant plant) {
		this.plant = plant;
		return this;
	}

	@JsonProperty("device")
	public Device getDevice() {
		return device;
	}

	@JsonProperty("device")
	public void setDevice(Device device) {
		this.device = device;
	}

	public MeasurementsResponse withDevice(Device device) {
		this.device = device;
		return this;
	}

	@JsonProperty("setType")
	public String getSetType() {
		return setType;
	}

	@JsonProperty("setType")
	public void setSetType(String setType) {
		this.setType = setType;
	}

	public MeasurementsResponse withSetType(String setType) {
		this.setType = setType;
		return this;
	}

	@JsonProperty("resolution")
	public String getResolution() {
		return resolution;
	}

	@JsonProperty("resolution")
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public MeasurementsResponse withResolution(String resolution) {
		this.resolution = resolution;
		return this;
	}

	@JsonProperty("set")
	public List<Set> getSet() {
		return set;
	}

	@JsonProperty("set")
	public void setSet(List<Set> set) {
		this.set = set;
	}

	public MeasurementsResponse withSet(List<Set> set) {
		this.set = set;
		return this;
	}

	@JsonProperty("total")
	public Total getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Total total) {
		this.total = total;
	}

	public MeasurementsResponse withTotal(Total total) {
		this.total = total;
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

	public MeasurementsResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

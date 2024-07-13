
package tech.renovus.solarec.inverters.brand.growatt.api;

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
@JsonPropertyOrder({ "data" })
@Generated("jsonschema2pojo")
public class PlantEnergyResponse extends ErrorResponse {

	@JsonProperty("data")
	private PlantEnergyData data;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("data")
	public PlantEnergyData getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(PlantEnergyData data) {
		this.data = data;
	}

	public PlantEnergyResponse withData(PlantEnergyData data) {
		this.data = data;
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
	public PlantEnergyResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

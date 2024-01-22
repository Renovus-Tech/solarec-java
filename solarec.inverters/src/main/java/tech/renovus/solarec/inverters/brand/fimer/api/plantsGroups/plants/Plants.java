package tech.renovus.solarec.inverters.brand.fimer.api.plantsGroups.plants;

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
@JsonPropertyOrder({ "plantStatus" })
@Generated("jsonschema2pojo")
public class Plants {

	@JsonProperty("plantStatus")
	private String plantStatus;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("plantStatus")
	public String getPlantStatus() {
		return plantStatus;
	}

	@JsonProperty("plantStatus")
	public void setPlantStatus(String plantStatus) {
		this.plantStatus = plantStatus;
	}

	public Plants withPlantStatus(String plantStatus) {
		this.plantStatus = plantStatus;
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

	public Plants withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

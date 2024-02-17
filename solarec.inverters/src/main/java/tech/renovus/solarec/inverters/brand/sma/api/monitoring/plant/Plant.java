package tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.util.ClassUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "plantId", "name", "description", "timezone" })
@Generated("jsonschema2pojo")
public class Plant {

	@JsonProperty("plantId")
	private String plantId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("timezone")
	private String timezone;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("plantId")
	public String getPlantId() {
		return plantId;
	}

	@JsonProperty("plantId")
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public Plant withPlantId(String plantId) {
		this.plantId = plantId;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Plant withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	public Plant withDescription(String description) {
		this.description = description;
		return this;
	}

	@JsonProperty("timezone")
	public String getTimezone() {
		return timezone;
	}

	@JsonProperty("timezone")
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Plant withTimezone(String timezone) {
		this.timezone = timezone;
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

	public Plant withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override public boolean equals(Object obj) {
		if (! (obj instanceof Plant)) return false;
		return ClassUtil.equals(this.plantId, ((Plant) obj).plantId);
	}
	
	@Override public int hashCode() {
		return this.plantId.hashCode();
	}
}

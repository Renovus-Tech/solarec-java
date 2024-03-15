package tech.renovus.solarec.vo.custom.chart;

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
@JsonPropertyOrder({ "co2Emissons", "co2Avoided" })
@Generated("jsonschema2pojo")
public class Co2Overview {

	@JsonProperty("co2Emissons")
	private Double co2Emissons;
	@JsonProperty("co2Avoided")
	private Double co2Avoided;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("co2Emissons")
	public Double getCo2Emissons() {
		return co2Emissons;
	}

	@JsonProperty("co2Emissons")
	public void setCo2Emissons(Double co2Emissons) {
		this.co2Emissons = co2Emissons;
	}

	public Co2Overview withCo2Emissons(Double co2Emissons) {
		this.co2Emissons = co2Emissons;
		return this;
	}

	@JsonProperty("co2Avoided")
	public Double getCo2Avoided() {
		return co2Avoided;
	}

	@JsonProperty("co2Avoided")
	public void setCo2Avoided(Double co2Avoided) {
		this.co2Avoided = co2Avoided;
	}

	public Co2Overview withCo2Avoided(Double co2Avoided) {
		this.co2Avoided = co2Avoided;
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

	public Co2Overview withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
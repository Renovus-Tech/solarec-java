package tech.renovus.solarec.certificate.irec.br.api.source;

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
@JsonPropertyOrder({ "name", "variation", "efficency" })
@Generated("jsonschema2pojo")
public class SourceRequest {

	@JsonProperty("name")
	private String name;
	@JsonProperty("variation")
	private Integer variation;
	@JsonProperty("efficency")
	private Integer efficency;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public SourceRequest withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("variation")
	public Integer getVariation() {
		return variation;
	}

	@JsonProperty("variation")
	public void setVariation(Integer variation) {
		this.variation = variation;
	}

	public SourceRequest withVariation(Integer variation) {
		this.variation = variation;
		return this;
	}

	@JsonProperty("efficency")
	public Integer getEfficency() {
		return efficency;
	}

	@JsonProperty("efficency")
	public void setEfficency(Integer efficency) {
		this.efficency = efficency;
	}

	public SourceRequest withEfficency(Integer efficency) {
		this.efficency = efficency;
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

	public SourceRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
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
@JsonPropertyOrder({ "endpoint", "params", "error" })
@Generated("jsonschema2pojo")
public class BaseError {

	@JsonProperty("endpoint")
	private String endpoint;
	@JsonProperty("params")
	private String params;
	@JsonProperty("error")
	private String error;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("endpoint")
	public String getEndpoint() {
		return endpoint;
	}

	@JsonProperty("endpoint")
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public BaseError withEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	@JsonProperty("params")
	public String getParams() {
		return params;
	}

	@JsonProperty("params")
	public void setParams(String params) {
		this.params = params;
	}

	public BaseError withParams(String params) {
		this.params = params;
		return this;
	}

	@JsonProperty("error")
	public String getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}

	public BaseError withError(String error) {
		this.error = error;
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

	public BaseError withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
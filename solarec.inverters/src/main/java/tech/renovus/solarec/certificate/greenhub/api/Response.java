package tech.renovus.solarec.certificate.greenhub.api;

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
@JsonPropertyOrder({ "data", "status_code" })
@Generated("jsonschema2pojo")
public class Response<T extends Object> {

	@JsonProperty("data")
	private T data;
	@JsonProperty("status_code")
	private Integer statusCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("data")
	public T getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(T data) {
		this.data = data;
	}

	public Response<T> withData(T data) {
		this.data = data;
		return this;
	}

	@JsonProperty("status_code")
	public Integer getStatusCode() {
		return statusCode;
	}

	@JsonProperty("status_code")
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Response<T> withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
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

	public Response<T> withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
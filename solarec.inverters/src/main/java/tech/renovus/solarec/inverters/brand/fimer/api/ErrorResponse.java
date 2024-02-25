package tech.renovus.solarec.inverters.brand.fimer.api;

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
@JsonPropertyOrder({ "timestamp", "status", "error", "exception", "message", "path" })
@Generated("jsonschema2pojo")
public class ErrorResponse {

	@JsonProperty("timestamp")
	private Long timestamp;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("error")
	private String error;
	@JsonProperty("exception")
	private String exception;
	@JsonProperty("message")
	private String message;
	@JsonProperty("path")
	private String path;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("timestamp")
	public Long getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public ErrorResponse withTimestamp(Long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status) {
		this.status = status;
	}

	public ErrorResponse withStatus(Integer status) {
		this.status = status;
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

	public ErrorResponse withError(String error) {
		this.error = error;
		return this;
	}

	@JsonProperty("exception")
	public String getException() {
		return exception;
	}

	@JsonProperty("exception")
	public void setException(String exception) {
		this.exception = exception;
	}

	public ErrorResponse withException(String exception) {
		this.exception = exception;
		return this;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse withMessage(String message) {
		this.message = message;
		return this;
	}

	@JsonProperty("path")
	public String getPath() {
		return path;
	}

	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}

	public ErrorResponse withPath(String path) {
		this.path = path;
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

	public ErrorResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
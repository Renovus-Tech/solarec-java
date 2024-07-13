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
@JsonPropertyOrder({ "error_msg", "error_code" })
@Generated("jsonschema2pojo")
public class ErrorResponse {

	@JsonProperty("error_msg")
	private String errorMsg;
	@JsonProperty("error_code")
	private Integer errorCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("error_msg")
	public String getErrorMsg() {
		return errorMsg;
	}

	@JsonProperty("error_msg")
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorResponse withErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
		return this;
	}

	@JsonProperty("error_code")
	public Integer getErrorCode() {
		return errorCode;
	}

	@JsonProperty("error_code")
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorResponse withErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
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

	
	//---- Added code ---------------------------
	public boolean hasError() { return this.errorCode != null && this.errorCode.intValue() != 0; }
}
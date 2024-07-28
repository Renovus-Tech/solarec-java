package tech.renovus.solarec.inverters.brand.huawei.api;

import java.util.Arrays;
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

import tech.renovus.solarec.connection.IWithHeaders;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "success", "data", "failCode", "params", "message" })
@Generated("jsonschema2pojo")
public class LoginResponse implements IWithHeaders {

	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("data")
	private Object data;
	@JsonProperty("failCode")
	private Integer failCode;
	@JsonProperty("params")
	private BasePowerStationResponseParams params;
	@JsonProperty("message")
	private String message;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public LoginResponse withSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	@JsonProperty("data")
	public Object getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Object data) {
		this.data = data;
	}

	public LoginResponse withData(Object data) {
		this.data = data;
		return this;
	}

	@JsonProperty("failCode")
	public Integer getFailCode() {
		return failCode;
	}

	@JsonProperty("failCode")
	public void setFailCode(Integer failCode) {
		this.failCode = failCode;
	}

	public LoginResponse withFailCode(Integer failCode) {
		this.failCode = failCode;
		return this;
	}

	@JsonProperty("params")
	public BasePowerStationResponseParams getParams() {
		return params;
	}

	@JsonProperty("params")
	public void setParams(BasePowerStationResponseParams params) {
		this.params = params;
	}

	public LoginResponse withParams(BasePowerStationResponseParams params) {
		this.params = params;
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

	public LoginResponse withMessage(String message) {
		this.message = message;
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

	public LoginResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	//--- Implemented methods -------------------
	private static final String HEADER_XSRF_TOKEN	= "xsrf-token";
	private String xsrfToken;
	
	@Override public List<String> getHeadersToSet() {
		return Arrays.asList(HEADER_XSRF_TOKEN);
	}

	@Override
	public void setHeader(String header, String value) {
		if (HEADER_XSRF_TOKEN.equals(header)) {
			this.xsrfToken = value;
		}
	}

	public String getXsrfToken() {
		return xsrfToken;
	}
}
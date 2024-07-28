package tech.renovus.solarec.inverters.brand.huawei.api;

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
@JsonPropertyOrder({ "success", "failCode", "params", "message" })
@Generated("jsonschema2pojo")
public class BasePowerStationResponse {

	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("failCode")
	private Integer failCode;
	@JsonProperty("params")
	private BasePowerStationResponseParams params;
	@JsonProperty("message")
	private Object message;
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

	public BasePowerStationResponse withSuccess(Boolean success) {
		this.success = success;
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

	public BasePowerStationResponse withFailCode(Integer failCode) {
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

	public BasePowerStationResponse withParams(BasePowerStationResponseParams params) {
		this.params = params;
		return this;
	}

	@JsonProperty("message")
	public Object getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(Object message) {
		this.message = message;
	}

	public BasePowerStationResponse withMessage(Object message) {
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

	public BasePowerStationResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
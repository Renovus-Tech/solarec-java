package tech.renovus.solarec.inverters.brand.sofar.api;

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
@JsonPropertyOrder({ "code", "msg", "success", "requestId" })
@Generated("jsonschema2pojo")
public class BasicResponse {

	@JsonProperty("code")
	private Object code;
	@JsonProperty("msg")
	private Object msg;
	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("requestId")
	private String requestId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("code")
	public Object getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(Object code) {
		this.code = code;
	}

	public BasicResponse withCode(Object code) {
		this.code = code;
		return this;
	}

	@JsonProperty("msg")
	public Object getMsg() {
		return msg;
	}

	@JsonProperty("msg")
	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public BasicResponse withMsg(Object msg) {
		this.msg = msg;
		return this;
	}

	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	@JsonProperty("success")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public BasicResponse withSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	@JsonProperty("requestId")
	public String getRequestId() {
		return requestId;
	}

	@JsonProperty("requestId")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public BasicResponse withRequestId(String requestId) {
		this.requestId = requestId;
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

	public BasicResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
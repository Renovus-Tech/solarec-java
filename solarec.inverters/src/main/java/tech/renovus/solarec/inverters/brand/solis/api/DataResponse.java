package tech.renovus.solarec.inverters.brand.solis.api;

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
@JsonPropertyOrder({ "success", "code", "msg", "data" })
@Generated("jsonschema2pojo")
public abstract class DataResponse<T extends Object> {

	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("code")
	private String code;
	@JsonProperty("msg")
	private String msg;
	@JsonProperty("data")
	private T data;
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

	public DataResponse<T> withSuccess(Boolean success) {
		this.success = success;
		return this.getSelf();
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	public DataResponse<T> withCode(String code) {
		this.code = code;
		return this.getSelf();
	}

	@JsonProperty("msg")
	public String getMsg() {
		return msg;
	}

	@JsonProperty("msg")
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DataResponse<T> withMsg(String msg) {
		this.msg = msg;
		return this.getSelf();
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public DataResponse<T> withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this.getSelf();
	}

	@JsonProperty("msg")
	public T getData() {
		return data;
	}

	@JsonProperty("msg")
	public void setData(T data) {
		this.data = data;
	}

	public DataResponse<T> withMsg(T data) {
		this.data = data;
		return this.getSelf();
	}
	
	public abstract DataResponse<T> getSelf();
}
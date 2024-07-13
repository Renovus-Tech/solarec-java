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
@JsonPropertyOrder({ "userName", "systemCode" })
@Generated("jsonschema2pojo")
public class LoginRequest {

	@JsonProperty("userName")
	private String userName;
	@JsonProperty("systemCode")
	private String systemCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LoginRequest withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	@JsonProperty("systemCode")
	public String getSystemCode() {
		return systemCode;
	}

	@JsonProperty("systemCode")
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public LoginRequest withSystemCode(String systemCode) {
		this.systemCode = systemCode;
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

	public LoginRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}
}
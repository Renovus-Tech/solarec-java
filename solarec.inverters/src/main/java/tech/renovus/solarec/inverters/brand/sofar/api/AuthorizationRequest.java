
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
@JsonPropertyOrder({ "appSecret", "username", "orgId", "password" })
@Generated("jsonschema2pojo")
public class AuthorizationRequest {

	@JsonProperty("appSecret")
	private String appSecret;
	@JsonProperty("username")
	private String username;
	@JsonProperty("orgId")
	private Integer orgId;
	@JsonProperty("password")
	private String password;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("appSecret")
	public String getAppSecret() {
		return appSecret;
	}

	@JsonProperty("appSecret")
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public AuthorizationRequest withAppSecret(String appSecret) {
		this.appSecret = appSecret;
		return this;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	public AuthorizationRequest withUsername(String username) {
		this.username = username;
		return this;
	}

	@JsonProperty("orgId")
	public Integer getOrgId() {
		return orgId;
	}

	@JsonProperty("orgId")
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public AuthorizationRequest withOrgId(Integer orgId) {
		this.orgId = orgId;
		return this;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	public AuthorizationRequest withPassword(String password) {
		this.password = password;
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

	public AuthorizationRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

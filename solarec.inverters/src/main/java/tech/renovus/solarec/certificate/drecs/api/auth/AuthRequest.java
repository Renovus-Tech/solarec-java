package tech.renovus.solarec.certificate.drecs.api.auth;

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
@JsonPropertyOrder({ "username", "password" })
@Generated("jsonschema2pojo")
public class AuthRequest {

	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}

	public AuthRequest withUsername(String username) {
		this.username = username;
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

	public AuthRequest withPassword(String password) {
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

	public AuthRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

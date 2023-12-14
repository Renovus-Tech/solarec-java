
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
@JsonPropertyOrder({ "statusCode", "accessToken", "message" })
@Generated("jsonschema2pojo")
public class AuthResponse {

	@JsonProperty("statusCode")
	private Integer statusCode;
	@JsonProperty("accessToken")
	private String accessToken;
	@JsonProperty("message")
	private String message;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("statusCode")
	public Integer getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public AuthResponse withStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
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

	public AuthResponse withMessage(String message) {
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

	public AuthResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@JsonProperty("accessToken")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("accessToken")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public AuthResponse withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
}

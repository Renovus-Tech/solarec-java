package tech.renovus.solarec.inverters.brand.sofar.api;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "access_token", "token_type", "refresh_token", "expires_in", "scope", "uid" })
@Generated("jsonschema2pojo")
public class AuthorizationResponse extends BasicResponse {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("expires_in")
	private String expiresIn;
	@JsonProperty("scope")
	private Object scope;
	@JsonProperty("uid")
	private Integer uid;

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public AuthorizationResponse withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public AuthorizationResponse withTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	@JsonProperty("refresh_token")
	public String getRefreshToken() {
		return refreshToken;
	}

	@JsonProperty("refresh_token")
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public AuthorizationResponse withRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	@JsonProperty("expires_in")
	public String getExpiresIn() {
		return expiresIn;
	}

	@JsonProperty("expires_in")
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public AuthorizationResponse withExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	@JsonProperty("scope")
	public Object getScope() {
		return scope;
	}

	@JsonProperty("scope")
	public void setScope(Object scope) {
		this.scope = scope;
	}

	public AuthorizationResponse withScope(Object scope) {
		this.scope = scope;
		return this;
	}

	@JsonProperty("uid")
	public Integer getUid() {
		return uid;
	}

	@JsonProperty("uid")
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public AuthorizationResponse withUid(Integer uid) {
		this.uid = uid;
		return this;
	}
}
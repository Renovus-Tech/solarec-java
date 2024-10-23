package tech.renovus.solarec.inverters.brand.enphase.api;

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
@JsonPropertyOrder({ "access_token", "token_type", "refresh_token", "expires_in", "scope", "enl_uid", "enl_cid",
		"enl_password_last_changed", "is_internal_app", "app_type", "jti" })
@Generated("jsonschema2pojo")
public class TokenResponse extends ErrorResponse {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("enl_uid")
	private String enlUid;
	@JsonProperty("enl_cid")
	private String enlCid;
	@JsonProperty("enl_password_last_changed")
	private String enlPasswordLastChanged;
	@JsonProperty("is_internal_app")
	private Boolean isInternalApp;
	@JsonProperty("app_type")
	private String appType;
	@JsonProperty("jti")
	private String jti;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public TokenResponse withAccessToken(String accessToken) {
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

	public TokenResponse withTokenType(String tokenType) {
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

	public TokenResponse withRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}

	@JsonProperty("expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	@JsonProperty("expires_in")
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public TokenResponse withExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	@JsonProperty("scope")
	public String getScope() {
		return scope;
	}

	@JsonProperty("scope")
	public void setScope(String scope) {
		this.scope = scope;
	}

	public TokenResponse withScope(String scope) {
		this.scope = scope;
		return this;
	}

	@JsonProperty("enl_uid")
	public String getEnlUid() {
		return enlUid;
	}

	@JsonProperty("enl_uid")
	public void setEnlUid(String enlUid) {
		this.enlUid = enlUid;
	}

	public TokenResponse withEnlUid(String enlUid) {
		this.enlUid = enlUid;
		return this;
	}

	@JsonProperty("enl_cid")
	public String getEnlCid() {
		return enlCid;
	}

	@JsonProperty("enl_cid")
	public void setEnlCid(String enlCid) {
		this.enlCid = enlCid;
	}

	public TokenResponse withEnlCid(String enlCid) {
		this.enlCid = enlCid;
		return this;
	}

	@JsonProperty("enl_password_last_changed")
	public String getEnlPasswordLastChanged() {
		return enlPasswordLastChanged;
	}

	@JsonProperty("enl_password_last_changed")
	public void setEnlPasswordLastChanged(String enlPasswordLastChanged) {
		this.enlPasswordLastChanged = enlPasswordLastChanged;
	}

	public TokenResponse withEnlPasswordLastChanged(String enlPasswordLastChanged) {
		this.enlPasswordLastChanged = enlPasswordLastChanged;
		return this;
	}

	@JsonProperty("is_internal_app")
	public Boolean getIsInternalApp() {
		return isInternalApp;
	}

	@JsonProperty("is_internal_app")
	public void setIsInternalApp(Boolean isInternalApp) {
		this.isInternalApp = isInternalApp;
	}

	public TokenResponse withIsInternalApp(Boolean isInternalApp) {
		this.isInternalApp = isInternalApp;
		return this;
	}

	@JsonProperty("app_type")
	public String getAppType() {
		return appType;
	}

	@JsonProperty("app_type")
	public void setAppType(String appType) {
		this.appType = appType;
	}

	public TokenResponse withAppType(String appType) {
		this.appType = appType;
		return this;
	}

	@JsonProperty("jti")
	public String getJti() {
		return jti;
	}

	@JsonProperty("jti")
	public void setJti(String jti) {
		this.jti = jti;
	}

	public TokenResponse withJti(String jti) {
		this.jti = jti;
		return this;
	}

	@Override
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public TokenResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
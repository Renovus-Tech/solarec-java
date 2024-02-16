package tech.renovus.solarec.inverters.brand.sma.api.authorization;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.inverters.brand.sma.api.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"access_token",
"expires_in",
"refresh_expires_in",
"refresh_token",
"token_type",
"not-before-policy",
"session_state",
"scope"
})
@Generated("jsonschema2pojo")
public class AuthResponse extends ErrorResponse {

@JsonProperty("access_token")
private String accessToken;
@JsonProperty("expires_in")
private Integer expiresIn;
@JsonProperty("refresh_expires_in")
private Integer refreshExpiresIn;
@JsonProperty("refresh_token")
private String refreshToken;
@JsonProperty("token_type")
private String tokenType;
@JsonProperty("not-before-policy")
private Integer notBeforePolicy;
@JsonProperty("session_state")
private String sessionState;
@JsonProperty("scope")
private String scope;
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

public AuthResponse withAccessToken(String accessToken) {
this.accessToken = accessToken;
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

public AuthResponse withExpiresIn(Integer expiresIn) {
this.expiresIn = expiresIn;
return this;
}

@JsonProperty("refresh_expires_in")
public Integer getRefreshExpiresIn() {
return refreshExpiresIn;
}

@JsonProperty("refresh_expires_in")
public void setRefreshExpiresIn(Integer refreshExpiresIn) {
this.refreshExpiresIn = refreshExpiresIn;
}

public AuthResponse withRefreshExpiresIn(Integer refreshExpiresIn) {
this.refreshExpiresIn = refreshExpiresIn;
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

public AuthResponse withRefreshToken(String refreshToken) {
this.refreshToken = refreshToken;
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

public AuthResponse withTokenType(String tokenType) {
this.tokenType = tokenType;
return this;
}

@JsonProperty("not-before-policy")
public Integer getNotBeforePolicy() {
return notBeforePolicy;
}

@JsonProperty("not-before-policy")
public void setNotBeforePolicy(Integer notBeforePolicy) {
this.notBeforePolicy = notBeforePolicy;
}

public AuthResponse withNotBeforePolicy(Integer notBeforePolicy) {
this.notBeforePolicy = notBeforePolicy;
return this;
}

@JsonProperty("session_state")
public String getSessionState() {
return sessionState;
}

@JsonProperty("session_state")
public void setSessionState(String sessionState) {
this.sessionState = sessionState;
}

public AuthResponse withSessionState(String sessionState) {
this.sessionState = sessionState;
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

public AuthResponse withScope(String scope) {
this.scope = scope;
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

}
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"access_token",
"token_type",
"expires_in",
"refresh_token",
"refresh_expires_in"
})
@Generated("jsonschema2pojo")
public class BcAuthroizeTokenResponse {

@JsonProperty("access_token")
private String accessToken;
@JsonProperty("token_type")
private String tokenType;
@JsonProperty("expires_in")
private String expiresIn;
@JsonProperty("refresh_token")
private String refreshToken;
@JsonProperty("refresh_expires_in")
private String refreshExpiresIn;
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

public BcAuthroizeTokenResponse withAccessToken(String accessToken) {
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

public BcAuthroizeTokenResponse withTokenType(String tokenType) {
this.tokenType = tokenType;
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

public BcAuthroizeTokenResponse withExpiresIn(String expiresIn) {
this.expiresIn = expiresIn;
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

public BcAuthroizeTokenResponse withRefreshToken(String refreshToken) {
this.refreshToken = refreshToken;
return this;
}

@JsonProperty("refresh_expires_in")
public String getRefreshExpiresIn() {
return refreshExpiresIn;
}

@JsonProperty("refresh_expires_in")
public void setRefreshExpiresIn(String refreshExpiresIn) {
this.refreshExpiresIn = refreshExpiresIn;
}

public BcAuthroizeTokenResponse withRefreshExpiresIn(String refreshExpiresIn) {
this.refreshExpiresIn = refreshExpiresIn;
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

public BcAuthroizeTokenResponse withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
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
"loginHint",
"state",
"expirationDate",
"interval"
})
@Generated("jsonschema2pojo")
public class BcAuthorizeResponse extends ErrorResponse {

@JsonProperty("loginHint")
private String loginHint;
@JsonProperty("state")
private String state;
@JsonProperty("expirationDate")
private String expirationDate;
@JsonProperty("interval")
private Integer interval;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("loginHint")
public String getLoginHint() {
return loginHint;
}

@JsonProperty("loginHint")
public void setLoginHint(String loginHint) {
this.loginHint = loginHint;
}

public BcAuthorizeResponse withLoginHint(String loginHint) {
this.loginHint = loginHint;
return this;
}

@JsonProperty("state")
public String getState() {
return state;
}

@JsonProperty("state")
public void setState(String state) {
this.state = state;
}

public BcAuthorizeResponse withState(String state) {
this.state = state;
return this;
}

@JsonProperty("expirationDate")
public String getExpirationDate() {
return expirationDate;
}

@JsonProperty("expirationDate")
public void setExpirationDate(String expirationDate) {
this.expirationDate = expirationDate;
}

public BcAuthorizeResponse withExpirationDate(String expirationDate) {
this.expirationDate = expirationDate;
return this;
}

@JsonProperty("interval")
public Integer getInterval() {
return interval;
}

@JsonProperty("interval")
public void setInterval(Integer interval) {
this.interval = interval;
}

public BcAuthorizeResponse withInterval(Integer interval) {
this.interval = interval;
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

public BcAuthorizeResponse withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
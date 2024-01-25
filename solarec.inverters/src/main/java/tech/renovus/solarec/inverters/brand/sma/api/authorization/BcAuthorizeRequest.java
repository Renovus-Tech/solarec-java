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
"loginHint"
})
@Generated("jsonschema2pojo")
public class BcAuthorizeRequest {

@JsonProperty("loginHint")
private String loginHint;
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

public BcAuthorizeRequest withLoginHint(String loginHint) {
this.loginHint = loginHint;
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

public BcAuthorizeRequest withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
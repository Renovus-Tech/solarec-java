package tech.renovus.solarec.inverters.brand.sma.api;

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
"message",
"code"
})
@Generated("jsonschema2pojo")
public class ErrorResponse {

@JsonProperty("message")
private String message;
@JsonProperty("code")
private String code;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String message) {
this.message = message;
}

public ErrorResponse withMessage(String message) {
this.message = message;
return this;
}

@JsonProperty("code")
public String getCode() {
return code;
}

@JsonProperty("code")
public void setCode(String code) {
this.code = code;
}

public ErrorResponse withCode(String code) {
this.code = code;
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

public ErrorResponse withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
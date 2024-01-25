
package tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.datalogger;

import java.util.LinkedHashMap;
import java.util.List;
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
    "service",
    "prefixes"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("service")
    private String service;
    @JsonProperty("prefixes")
    private List<String> prefixes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("service")
    public String getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    public Result withService(String service) {
        this.service = service;
        return this;
    }

    @JsonProperty("prefixes")
    public List<String> getPrefixes() {
        return prefixes;
    }

    @JsonProperty("prefixes")
    public void setPrefixes(List<String> prefixes) {
        this.prefixes = prefixes;
    }

    public Result withPrefixes(List<String> prefixes) {
        this.prefixes = prefixes;
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

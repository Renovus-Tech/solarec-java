
package tech.renovus.solarec.grid.ember.api;

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
    "min",
    "max"
})
@Generated("jsonschema2pojo")
public class Date {

    @JsonProperty("min")
    private String min;
    @JsonProperty("max")
    private String max;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("min")
    public String getMin() {
        return min;
    }

    @JsonProperty("min")
    public void setMin(String min) {
        this.min = min;
    }

    public Date withMin(String min) {
        this.min = min;
        return this;
    }

    @JsonProperty("max")
    public String getMax() {
        return max;
    }

    @JsonProperty("max")
    public void setMax(String max) {
        this.max = max;
    }

    public Date withMax(String max) {
        this.max = max;
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

    public Date withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

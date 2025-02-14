
package tech.renovus.solarec.grid.ember.api;

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
    "stats",
    "data"
})
@Generated("jsonschema2pojo")
public class CarbonIntensityMonthlyResponse {

    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("data")
    private List<CountryData> data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("stats")
    public Stats getStats() {
        return stats;
    }

    @JsonProperty("stats")
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public CarbonIntensityMonthlyResponse withStats(Stats stats) {
        this.stats = stats;
        return this;
    }

    @JsonProperty("data")
    public List<CountryData> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<CountryData> data) {
        this.data = data;
    }

    public CarbonIntensityMonthlyResponse withData(List<CountryData> data) {
        this.data = data;
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

    public CarbonIntensityMonthlyResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

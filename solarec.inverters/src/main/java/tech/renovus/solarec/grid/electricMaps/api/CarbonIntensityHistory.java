
package tech.renovus.solarec.grid.electricMaps.api;

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
    "zone",
    "history"
})
@Generated("jsonschema2pojo")
public class CarbonIntensityHistory {

    @JsonProperty("zone")
    private String zone;
    @JsonProperty("history")
    private List<CarbonIntensity> history;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("zone")
    public String getZone() {
        return zone;
    }

    @JsonProperty("zone")
    public void setZone(String zone) {
        this.zone = zone;
    }

    public CarbonIntensityHistory withZone(String zone) {
        this.zone = zone;
        return this;
    }

    @JsonProperty("history")
    public List<CarbonIntensity> getHistory() {
        return history;
    }

    @JsonProperty("history")
    public void setHistory(List<CarbonIntensity> history) {
        this.history = history;
    }

    public CarbonIntensityHistory withHistory(List<CarbonIntensity> history) {
        this.history = history;
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

    public CarbonIntensityHistory withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

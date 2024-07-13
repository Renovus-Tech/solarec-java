
package tech.renovus.solarec.inverters.brand.growatt.api;

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
    "energys",
    "count",
    "time_unit"
})
@Generated("jsonschema2pojo")
public class PlantEnergyData {

    @JsonProperty("energys")
    private List<Energy> energys;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("time_unit")
    private String timeUnit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("energys")
    public List<Energy> getEnergys() {
        return energys;
    }

    @JsonProperty("energys")
    public void setEnergys(List<Energy> energys) {
        this.energys = energys;
    }

    public PlantEnergyData withEnergys(List<Energy> energys) {
        this.energys = energys;
        return this;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    public PlantEnergyData withCount(Integer count) {
        this.count = count;
        return this;
    }

    @JsonProperty("time_unit")
    public String getTimeUnit() {
        return timeUnit;
    }

    @JsonProperty("time_unit")
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public PlantEnergyData withTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
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

    public PlantEnergyData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

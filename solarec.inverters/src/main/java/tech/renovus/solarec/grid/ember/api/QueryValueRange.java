
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
    "date",
    "emissions_intensity_gco2_per_kwh"
})
@Generated("jsonschema2pojo")
public class QueryValueRange {

    @JsonProperty("date")
    private Date date;
    @JsonProperty("emissions_intensity_gco2_per_kwh")
    private EmissionsIntensityGco2PerKwh emissionsIntensityGco2PerKwh;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Date date) {
        this.date = date;
    }

    public QueryValueRange withDate(Date date) {
        this.date = date;
        return this;
    }

    @JsonProperty("emissions_intensity_gco2_per_kwh")
    public EmissionsIntensityGco2PerKwh getEmissionsIntensityGco2PerKwh() {
        return emissionsIntensityGco2PerKwh;
    }

    @JsonProperty("emissions_intensity_gco2_per_kwh")
    public void setEmissionsIntensityGco2PerKwh(EmissionsIntensityGco2PerKwh emissionsIntensityGco2PerKwh) {
        this.emissionsIntensityGco2PerKwh = emissionsIntensityGco2PerKwh;
    }

    public QueryValueRange withEmissionsIntensityGco2PerKwh(EmissionsIntensityGco2PerKwh emissionsIntensityGco2PerKwh) {
        this.emissionsIntensityGco2PerKwh = emissionsIntensityGco2PerKwh;
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

    public QueryValueRange withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

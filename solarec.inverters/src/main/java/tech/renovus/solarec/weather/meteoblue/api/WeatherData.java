
package tech.renovus.solarec.weather.meteoblue.api;

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
    "metadata",
    "units",
    "data_1h"
})
@Generated("jsonschema2pojo")
public class WeatherData {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("units")
    private Units units;
    @JsonProperty("data_1h")
    private Data1h data1h;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public WeatherData withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("units")
    public Units getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(Units units) {
        this.units = units;
    }

    public WeatherData withUnits(Units units) {
        this.units = units;
        return this;
    }

    @JsonProperty("data_1h")
    public Data1h getData1h() {
        return data1h;
    }

    @JsonProperty("data_1h")
    public void setData1h(Data1h data1h) {
        this.data1h = data1h;
    }

    public WeatherData withData1h(Data1h data1h) {
        this.data1h = data1h;
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

    public WeatherData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

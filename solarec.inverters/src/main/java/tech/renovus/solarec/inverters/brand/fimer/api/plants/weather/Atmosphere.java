
package tech.renovus.solarec.inverters.brand.fimer.api.plants.weather;

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
    "humidity",
    "pressure",
    "visibility"
})
@Generated("jsonschema2pojo")
public class Atmosphere {

    @JsonProperty("humidity")
    private Humidity humidity;
    @JsonProperty("pressure")
    private Pressure pressure;
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("humidity")
    public Humidity getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Atmosphere withHumidity(Humidity humidity) {
        this.humidity = humidity;
        return this;
    }

    @JsonProperty("pressure")
    public Pressure getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Atmosphere withPressure(Pressure pressure) {
        this.pressure = pressure;
        return this;
    }

    @JsonProperty("visibility")
    public Visibility getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Atmosphere withVisibility(Visibility visibility) {
        this.visibility = visibility;
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

    public Atmosphere withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}


package tech.renovus.solarec.weather.stormglass.solar;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "downwardShortWaveRadiationFlux",
    "time"
})
@Generated("jsonschema2pojo")
public class Hour {

    @JsonProperty("downwardShortWaveRadiationFlux")
    private DownwardShortWaveRadiationFlux downwardShortWaveRadiationFlux;
    @JsonProperty("time")
    private String time;

    @JsonProperty("downwardShortWaveRadiationFlux")
    public DownwardShortWaveRadiationFlux getDownwardShortWaveRadiationFlux() {
        return downwardShortWaveRadiationFlux;
    }

    @JsonProperty("downwardShortWaveRadiationFlux")
    public void setDownwardShortWaveRadiationFlux(DownwardShortWaveRadiationFlux downwardShortWaveRadiationFlux) {
        this.downwardShortWaveRadiationFlux = downwardShortWaveRadiationFlux;
    }

    public Hour withDownwardShortWaveRadiationFlux(DownwardShortWaveRadiationFlux downwardShortWaveRadiationFlux) {
        this.downwardShortWaveRadiationFlux = downwardShortWaveRadiationFlux;
        return this;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    public Hour withTime(String time) {
        this.time = time;
        return this;
    }

}


package tech.renovus.solarec.weather.meteoblue.solar;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "radiation"
})
@Generated("jsonschema2pojo")
public class Units {

    @JsonProperty("time")
    private String time;
    @JsonProperty("radiation")
    private String radiation;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    public Units withTime(String time) {
        this.time = time;
        return this;
    }

    @JsonProperty("radiation")
    public String getRadiation() {
        return radiation;
    }

    @JsonProperty("radiation")
    public void setRadiation(String radiation) {
        this.radiation = radiation;
    }

    public Units withRadiation(String radiation) {
        this.radiation = radiation;
        return this;
    }

}

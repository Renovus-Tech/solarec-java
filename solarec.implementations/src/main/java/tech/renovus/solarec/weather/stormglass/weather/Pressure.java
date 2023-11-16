
package tech.renovus.solarec.weather.stormglass.weather;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "noaa",
    "sg"
})
@Generated("jsonschema2pojo")
public class Pressure {

    @JsonProperty("noaa")
    private Double noaa;
    @JsonProperty("sg")
    private Double sg;

    @JsonProperty("noaa")
    public Double getNoaa() {
        return noaa;
    }

    @JsonProperty("noaa")
    public void setNoaa(Double noaa) {
        this.noaa = noaa;
    }

    public Pressure withNoaa(Double noaa) {
        this.noaa = noaa;
        return this;
    }

    @JsonProperty("sg")
    public Double getSg() {
        return sg;
    }

    @JsonProperty("sg")
    public void setSg(Double sg) {
        this.sg = sg;
    }

    public Pressure withSg(Double sg) {
        this.sg = sg;
        return this;
    }

}

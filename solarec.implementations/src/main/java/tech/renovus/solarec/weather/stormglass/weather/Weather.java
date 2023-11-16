
package tech.renovus.solarec.weather.stormglass.weather;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hours",
    "meta"
})
@Generated("jsonschema2pojo")
public class Weather {

    @JsonProperty("hours")
    private List<Hour> hours = null;
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("hours")
    public List<Hour> getHours() {
        return hours;
    }

    @JsonProperty("hours")
    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    public Weather withHours(List<Hour> hours) {
        this.hours = hours;
        return this;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Weather withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

}

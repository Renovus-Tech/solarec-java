
package tech.renovus.solarec.weather.stormglass.solar;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.weather.stormglass.common.Meta;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hours",
    "meta"
})
@Generated("jsonschema2pojo")
public class Solar {

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

    public Solar withHours(List<Hour> hours) {
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

    public Solar withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

}

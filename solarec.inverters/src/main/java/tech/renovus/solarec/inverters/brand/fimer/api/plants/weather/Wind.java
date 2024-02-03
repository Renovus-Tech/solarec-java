
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
    "speed",
    "direction"
})
@Generated("jsonschema2pojo")
public class Wind {

    @JsonProperty("speed")
    private Speed speed;
    @JsonProperty("direction")
    private Direction direction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("speed")
    public Speed getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Wind withSpeed(Speed speed) {
        this.speed = speed;
        return this;
    }

    @JsonProperty("direction")
    public Direction getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Wind withDirection(Direction direction) {
        this.direction = direction;
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

    public Wind withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

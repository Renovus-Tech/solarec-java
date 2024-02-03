
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
    "entityID",
    "date",
    "location",
    "contidion",
    "atmosphere",
    "wind"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("entityID")
    private Integer entityID;
    @JsonProperty("date")
    private Integer date;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("contidion")
    private Contidion contidion;
    @JsonProperty("atmosphere")
    private Atmosphere atmosphere;
    @JsonProperty("wind")
    private Wind wind;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("entityID")
    public Integer getEntityID() {
        return entityID;
    }

    @JsonProperty("entityID")
    public void setEntityID(Integer entityID) {
        this.entityID = entityID;
    }

    public Result withEntityID(Integer entityID) {
        this.entityID = entityID;
        return this;
    }

    @JsonProperty("date")
    public Integer getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Integer date) {
        this.date = date;
    }

    public Result withDate(Integer date) {
        this.date = date;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Result withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("contidion")
    public Contidion getContidion() {
        return contidion;
    }

    @JsonProperty("contidion")
    public void setContidion(Contidion contidion) {
        this.contidion = contidion;
    }

    public Result withContidion(Contidion contidion) {
        this.contidion = contidion;
        return this;
    }

    @JsonProperty("atmosphere")
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @JsonProperty("atmosphere")
    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Result withAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
        return this;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Result withWind(Wind wind) {
        this.wind = wind;
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

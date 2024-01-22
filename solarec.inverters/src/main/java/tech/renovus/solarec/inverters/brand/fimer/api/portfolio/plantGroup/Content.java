
package tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plantGroup;

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
    "plantGroupEntityID",
    "plantGroupName",
    "plantGroupState"
})
@Generated("jsonschema2pojo")
public class Content {

    @JsonProperty("plantGroupEntityID")
    private Integer plantGroupEntityID;
    @JsonProperty("plantGroupName")
    private String plantGroupName;
    @JsonProperty("plantGroupState")
    private String plantGroupState;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("plantGroupEntityID")
    public Integer getPlantGroupEntityID() {
        return plantGroupEntityID;
    }

    @JsonProperty("plantGroupEntityID")
    public void setPlantGroupEntityID(Integer plantGroupEntityID) {
        this.plantGroupEntityID = plantGroupEntityID;
    }

    public Content withPlantGroupEntityID(Integer plantGroupEntityID) {
        this.plantGroupEntityID = plantGroupEntityID;
        return this;
    }

    @JsonProperty("plantGroupName")
    public String getPlantGroupName() {
        return plantGroupName;
    }

    @JsonProperty("plantGroupName")
    public void setPlantGroupName(String plantGroupName) {
        this.plantGroupName = plantGroupName;
    }

    public Content withPlantGroupName(String plantGroupName) {
        this.plantGroupName = plantGroupName;
        return this;
    }

    @JsonProperty("plantGroupState")
    public String getPlantGroupState() {
        return plantGroupState;
    }

    @JsonProperty("plantGroupState")
    public void setPlantGroupState(String plantGroupState) {
        this.plantGroupState = plantGroupState;
    }

    public Content withPlantGroupState(String plantGroupState) {
        this.plantGroupState = plantGroupState;
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

    public Content withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

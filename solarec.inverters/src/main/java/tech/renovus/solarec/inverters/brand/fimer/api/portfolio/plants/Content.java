package tech.renovus.solarec.inverters.brand.fimer.api.portfolio.plants;

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
    "plantEntityID",
    "plantName",
    "plantDescription",
    "plantState",
    "plantStatus"
})
@Generated("jsonschema2pojo")
public class Content {

    @JsonProperty("plantEntityID")
    private Integer plantEntityID;
    @JsonProperty("plantName")
    private String plantName;
    @JsonProperty("plantDescription")
    private String plantDescription;
    @JsonProperty("plantState")
    private String plantState;
    @JsonProperty("plantStatus")
    private String plantStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("plantEntityID")
    public Integer getPlantEntityID() {
        return plantEntityID;
    }

    @JsonProperty("plantEntityID")
    public void setPlantEntityID(Integer plantEntityID) {
        this.plantEntityID = plantEntityID;
    }

    public Content withPlantEntityID(Integer plantEntityID) {
        this.plantEntityID = plantEntityID;
        return this;
    }

    @JsonProperty("plantName")
    public String getPlantName() {
        return plantName;
    }

    @JsonProperty("plantName")
    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public Content withPlantName(String plantName) {
        this.plantName = plantName;
        return this;
    }

    @JsonProperty("plantDescription")
    public String getPlantDescription() {
        return plantDescription;
    }

    @JsonProperty("plantDescription")
    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public Content withPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
        return this;
    }

    @JsonProperty("plantState")
    public String getPlantState() {
        return plantState;
    }

    @JsonProperty("plantState")
    public void setPlantState(String plantState) {
        this.plantState = plantState;
    }

    public Content withPlantState(String plantState) {
        this.plantState = plantState;
        return this;
    }

    @JsonProperty("plantStatus")
    public String getPlantStatus() {
        return plantStatus;
    }

    @JsonProperty("plantStatus")
    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }

    public Content withPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
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

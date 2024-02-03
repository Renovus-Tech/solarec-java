package tech.renovus.solarec.inverters.brand.fimer.api.plants.info;

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
    "plantLocation",
    "plantConfiguration",
    "portfolioEID"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("plantEntityID")
    private Integer plantEntityID;
    @JsonProperty("plantName")
    private String plantName;
    @JsonProperty("plantDescription")
    private String plantDescription;
    @JsonProperty("plantState")
    private String plantState;
    @JsonProperty("plantLocation")
    private PlantLocation plantLocation;
    @JsonProperty("plantConfiguration")
    private PlantConfiguration plantConfiguration;
    @JsonProperty("portfolioEID")
    private Integer portfolioEID;
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

    public Result withPlantEntityID(Integer plantEntityID) {
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

    public Result withPlantName(String plantName) {
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

    public Result withPlantDescription(String plantDescription) {
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

    public Result withPlantState(String plantState) {
        this.plantState = plantState;
        return this;
    }

    @JsonProperty("plantLocation")
    public PlantLocation getPlantLocation() {
        return plantLocation;
    }

    @JsonProperty("plantLocation")
    public void setPlantLocation(PlantLocation plantLocation) {
        this.plantLocation = plantLocation;
    }

    public Result withPlantLocation(PlantLocation plantLocation) {
        this.plantLocation = plantLocation;
        return this;
    }

    @JsonProperty("plantConfiguration")
    public PlantConfiguration getPlantConfiguration() {
        return plantConfiguration;
    }

    @JsonProperty("plantConfiguration")
    public void setPlantConfiguration(PlantConfiguration plantConfiguration) {
        this.plantConfiguration = plantConfiguration;
    }

    public Result withPlantConfiguration(PlantConfiguration plantConfiguration) {
        this.plantConfiguration = plantConfiguration;
        return this;
    }

    @JsonProperty("portfolioEID")
    public Integer getPortfolioEID() {
        return portfolioEID;
    }

    @JsonProperty("portfolioEID")
    public void setPortfolioEID(Integer portfolioEID) {
        this.portfolioEID = portfolioEID;
    }

    public Result withPortfolioEID(Integer portfolioEID) {
        this.portfolioEID = portfolioEID;
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

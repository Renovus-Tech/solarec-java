package tech.renovus.solarec.inverters.brand.fimer.api.plants.status;

import java.util.LinkedHashMap;
import java.util.List;
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
    "plantState",
    "plantStatus",
    "loggerStatuses"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("plantEntityID")
    private Integer plantEntityID;
    @JsonProperty("plantName")
    private String plantName;
    @JsonProperty("plantState")
    private String plantState;
    @JsonProperty("plantStatus")
    private String plantStatus;
    @JsonProperty("loggerStatuses")
    private List<LoggerStatus> loggerStatuses;
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

    @JsonProperty("plantStatus")
    public String getPlantStatus() {
        return plantStatus;
    }

    @JsonProperty("plantStatus")
    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }

    public Result withPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
        return this;
    }

    @JsonProperty("loggerStatuses")
    public List<LoggerStatus> getLoggerStatuses() {
        return loggerStatuses;
    }

    @JsonProperty("loggerStatuses")
    public void setLoggerStatuses(List<LoggerStatus> loggerStatuses) {
        this.loggerStatuses = loggerStatuses;
    }

    public Result withLoggerStatuses(List<LoggerStatus> loggerStatuses) {
        this.loggerStatuses = loggerStatuses;
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

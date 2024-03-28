
package tech.renovus.solarec.grid.electricMaps.api;

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
    "zone",
    "carbonIntensity",
    "datetime",
    "updatedAt",
    "createdAt",
    "emissionFactorType",
    "isEstimated",
    "estimationMethod"
})
@Generated("jsonschema2pojo")
public class CarbonIntensity {

    @JsonProperty("zone")
    private String zone;
    @JsonProperty("carbonIntensity")
    private Integer carbonIntensity;
    @JsonProperty("datetime")
    private String datetime;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("emissionFactorType")
    private String emissionFactorType;
    @JsonProperty("isEstimated")
    private Boolean isEstimated;
    @JsonProperty("estimationMethod")
    private Object estimationMethod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("zone")
    public String getZone() {
        return zone;
    }

    @JsonProperty("zone")
    public void setZone(String zone) {
        this.zone = zone;
    }

    public CarbonIntensity withZone(String zone) {
        this.zone = zone;
        return this;
    }

    @JsonProperty("carbonIntensity")
    public Integer getCarbonIntensity() {
        return carbonIntensity;
    }

    @JsonProperty("carbonIntensity")
    public void setCarbonIntensity(Integer carbonIntensity) {
        this.carbonIntensity = carbonIntensity;
    }

    public CarbonIntensity withCarbonIntensity(Integer carbonIntensity) {
        this.carbonIntensity = carbonIntensity;
        return this;
    }

    @JsonProperty("datetime")
    public String getDatetime() {
        return datetime;
    }

    @JsonProperty("datetime")
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public CarbonIntensity withDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CarbonIntensity withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public CarbonIntensity withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("emissionFactorType")
    public String getEmissionFactorType() {
        return emissionFactorType;
    }

    @JsonProperty("emissionFactorType")
    public void setEmissionFactorType(String emissionFactorType) {
        this.emissionFactorType = emissionFactorType;
    }

    public CarbonIntensity withEmissionFactorType(String emissionFactorType) {
        this.emissionFactorType = emissionFactorType;
        return this;
    }

    @JsonProperty("isEstimated")
    public Boolean getIsEstimated() {
        return isEstimated;
    }

    @JsonProperty("isEstimated")
    public void setIsEstimated(Boolean isEstimated) {
        this.isEstimated = isEstimated;
    }

    public CarbonIntensity withIsEstimated(Boolean isEstimated) {
        this.isEstimated = isEstimated;
        return this;
    }

    @JsonProperty("estimationMethod")
    public Object getEstimationMethod() {
        return estimationMethod;
    }

    @JsonProperty("estimationMethod")
    public void setEstimationMethod(Object estimationMethod) {
        this.estimationMethod = estimationMethod;
    }

    public CarbonIntensity withEstimationMethod(Object estimationMethod) {
        this.estimationMethod = estimationMethod;
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

    public CarbonIntensity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

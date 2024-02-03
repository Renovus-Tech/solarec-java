package tech.renovus.solarec.inverters.brand.fimer.api.plants.billingData;

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
    "plantLifetimeEnergykWh",
    "plantCurrentDayEnergykWh",
    "plantLastReportedTimeUTC",
    "plantTimeZone",
    "plantStatus"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("plantEntityID")
    private Integer plantEntityID;
    @JsonProperty("plantLifetimeEnergykWh")
    private String plantLifetimeEnergykWh;
    @JsonProperty("plantCurrentDayEnergykWh")
    private String plantCurrentDayEnergykWh;
    @JsonProperty("plantLastReportedTimeUTC")
    private String plantLastReportedTimeUTC;
    @JsonProperty("plantTimeZone")
    private String plantTimeZone;
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

    public Result withPlantEntityID(Integer plantEntityID) {
        this.plantEntityID = plantEntityID;
        return this;
    }

    @JsonProperty("plantLifetimeEnergykWh")
    public String getPlantLifetimeEnergykWh() {
        return plantLifetimeEnergykWh;
    }

    @JsonProperty("plantLifetimeEnergykWh")
    public void setPlantLifetimeEnergykWh(String plantLifetimeEnergykWh) {
        this.plantLifetimeEnergykWh = plantLifetimeEnergykWh;
    }

    public Result withPlantLifetimeEnergykWh(String plantLifetimeEnergykWh) {
        this.plantLifetimeEnergykWh = plantLifetimeEnergykWh;
        return this;
    }

    @JsonProperty("plantCurrentDayEnergykWh")
    public String getPlantCurrentDayEnergykWh() {
        return plantCurrentDayEnergykWh;
    }

    @JsonProperty("plantCurrentDayEnergykWh")
    public void setPlantCurrentDayEnergykWh(String plantCurrentDayEnergykWh) {
        this.plantCurrentDayEnergykWh = plantCurrentDayEnergykWh;
    }

    public Result withPlantCurrentDayEnergykWh(String plantCurrentDayEnergykWh) {
        this.plantCurrentDayEnergykWh = plantCurrentDayEnergykWh;
        return this;
    }

    @JsonProperty("plantLastReportedTimeUTC")
    public String getPlantLastReportedTimeUTC() {
        return plantLastReportedTimeUTC;
    }

    @JsonProperty("plantLastReportedTimeUTC")
    public void setPlantLastReportedTimeUTC(String plantLastReportedTimeUTC) {
        this.plantLastReportedTimeUTC = plantLastReportedTimeUTC;
    }

    public Result withPlantLastReportedTimeUTC(String plantLastReportedTimeUTC) {
        this.plantLastReportedTimeUTC = plantLastReportedTimeUTC;
        return this;
    }

    @JsonProperty("plantTimeZone")
    public String getPlantTimeZone() {
        return plantTimeZone;
    }

    @JsonProperty("plantTimeZone")
    public void setPlantTimeZone(String plantTimeZone) {
        this.plantTimeZone = plantTimeZone;
    }

    public Result withPlantTimeZone(String plantTimeZone) {
        this.plantTimeZone = plantTimeZone;
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

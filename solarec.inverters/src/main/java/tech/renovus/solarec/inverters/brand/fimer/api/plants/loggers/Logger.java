package tech.renovus.solarec.inverters.brand.fimer.api.plants.loggers;

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
    "loggerEntityID",
    "loggerName",
    "loggerDescription",
    "loggerState",
    "loggerMACAddress",
    "loggerManufacturer",
    "loggerModel"
})
@Generated("jsonschema2pojo")
public class Logger {

    @JsonProperty("loggerEntityID")
    private Integer loggerEntityID;
    @JsonProperty("loggerName")
    private String loggerName;
    @JsonProperty("loggerDescription")
    private String loggerDescription;
    @JsonProperty("loggerState")
    private String loggerState;
    @JsonProperty("loggerMACAddress")
    private String loggerMACAddress;
    @JsonProperty("loggerManufacturer")
    private String loggerManufacturer;
    @JsonProperty("loggerModel")
    private String loggerModel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("loggerEntityID")
    public Integer getLoggerEntityID() {
        return loggerEntityID;
    }

    @JsonProperty("loggerEntityID")
    public void setLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
    }

    public Logger withLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
        return this;
    }

    @JsonProperty("loggerName")
    public String getLoggerName() {
        return loggerName;
    }

    @JsonProperty("loggerName")
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public Logger withLoggerName(String loggerName) {
        this.loggerName = loggerName;
        return this;
    }

    @JsonProperty("loggerDescription")
    public String getLoggerDescription() {
        return loggerDescription;
    }

    @JsonProperty("loggerDescription")
    public void setLoggerDescription(String loggerDescription) {
        this.loggerDescription = loggerDescription;
    }

    public Logger withLoggerDescription(String loggerDescription) {
        this.loggerDescription = loggerDescription;
        return this;
    }

    @JsonProperty("loggerState")
    public String getLoggerState() {
        return loggerState;
    }

    @JsonProperty("loggerState")
    public void setLoggerState(String loggerState) {
        this.loggerState = loggerState;
    }

    public Logger withLoggerState(String loggerState) {
        this.loggerState = loggerState;
        return this;
    }

    @JsonProperty("loggerMACAddress")
    public String getLoggerMACAddress() {
        return loggerMACAddress;
    }

    @JsonProperty("loggerMACAddress")
    public void setLoggerMACAddress(String loggerMACAddress) {
        this.loggerMACAddress = loggerMACAddress;
    }

    public Logger withLoggerMACAddress(String loggerMACAddress) {
        this.loggerMACAddress = loggerMACAddress;
        return this;
    }

    @JsonProperty("loggerManufacturer")
    public String getLoggerManufacturer() {
        return loggerManufacturer;
    }

    @JsonProperty("loggerManufacturer")
    public void setLoggerManufacturer(String loggerManufacturer) {
        this.loggerManufacturer = loggerManufacturer;
    }

    public Logger withLoggerManufacturer(String loggerManufacturer) {
        this.loggerManufacturer = loggerManufacturer;
        return this;
    }

    @JsonProperty("loggerModel")
    public String getLoggerModel() {
        return loggerModel;
    }

    @JsonProperty("loggerModel")
    public void setLoggerModel(String loggerModel) {
        this.loggerModel = loggerModel;
    }

    public Logger withLoggerModel(String loggerModel) {
        this.loggerModel = loggerModel;
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

    public Logger withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

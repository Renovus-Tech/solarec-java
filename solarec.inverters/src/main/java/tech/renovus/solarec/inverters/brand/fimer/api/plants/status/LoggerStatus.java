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
    "loggerEntityID",
    "loggerName",
    "loggerState",
    "loggerStatus",
    "deviceStatuses"
})
@Generated("jsonschema2pojo")
public class LoggerStatus {

    @JsonProperty("loggerEntityID")
    private Integer loggerEntityID;
    @JsonProperty("loggerName")
    private String loggerName;
    @JsonProperty("loggerState")
    private String loggerState;
    @JsonProperty("loggerStatus")
    private String loggerStatus;
    @JsonProperty("deviceStatuses")
    private List<DeviceStatus> deviceStatuses;
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

    public LoggerStatus withLoggerEntityID(Integer loggerEntityID) {
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

    public LoggerStatus withLoggerName(String loggerName) {
        this.loggerName = loggerName;
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

    public LoggerStatus withLoggerState(String loggerState) {
        this.loggerState = loggerState;
        return this;
    }

    @JsonProperty("loggerStatus")
    public String getLoggerStatus() {
        return loggerStatus;
    }

    @JsonProperty("loggerStatus")
    public void setLoggerStatus(String loggerStatus) {
        this.loggerStatus = loggerStatus;
    }

    public LoggerStatus withLoggerStatus(String loggerStatus) {
        this.loggerStatus = loggerStatus;
        return this;
    }

    @JsonProperty("deviceStatuses")
    public List<DeviceStatus> getDeviceStatuses() {
        return deviceStatuses;
    }

    @JsonProperty("deviceStatuses")
    public void setDeviceStatuses(List<DeviceStatus> deviceStatuses) {
        this.deviceStatuses = deviceStatuses;
    }

    public LoggerStatus withDeviceStatuses(List<DeviceStatus> deviceStatuses) {
        this.deviceStatuses = deviceStatuses;
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

    public LoggerStatus withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

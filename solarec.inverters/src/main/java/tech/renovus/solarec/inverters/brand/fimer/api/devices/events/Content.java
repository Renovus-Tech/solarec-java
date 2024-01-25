
package tech.renovus.solarec.inverters.brand.fimer.api.devices.events;

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
    "loggerName",
    "loggerEntityID",
    "loggerMACAddress",
    "deviceEntityID",
    "deviceSerialNumber",
    "eventSeverity",
    "eventType",
    "eventStart",
    "eventEnd"
})
@Generated("jsonschema2pojo")
public class Content {

    @JsonProperty("plantEntityID")
    private Integer plantEntityID;
    @JsonProperty("loggerName")
    private String loggerName;
    @JsonProperty("loggerEntityID")
    private Integer loggerEntityID;
    @JsonProperty("loggerMACAddress")
    private String loggerMACAddress;
    @JsonProperty("deviceEntityID")
    private Integer deviceEntityID;
    @JsonProperty("deviceSerialNumber")
    private String deviceSerialNumber;
    @JsonProperty("eventSeverity")
    private String eventSeverity;
    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("eventStart")
    private String eventStart;
    @JsonProperty("eventEnd")
    private String eventEnd;
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

    @JsonProperty("loggerName")
    public String getLoggerName() {
        return loggerName;
    }

    @JsonProperty("loggerName")
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public Content withLoggerName(String loggerName) {
        this.loggerName = loggerName;
        return this;
    }

    @JsonProperty("loggerEntityID")
    public Integer getLoggerEntityID() {
        return loggerEntityID;
    }

    @JsonProperty("loggerEntityID")
    public void setLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
    }

    public Content withLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
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

    public Content withLoggerMACAddress(String loggerMACAddress) {
        this.loggerMACAddress = loggerMACAddress;
        return this;
    }

    @JsonProperty("deviceEntityID")
    public Integer getDeviceEntityID() {
        return deviceEntityID;
    }

    @JsonProperty("deviceEntityID")
    public void setDeviceEntityID(Integer deviceEntityID) {
        this.deviceEntityID = deviceEntityID;
    }

    public Content withDeviceEntityID(Integer deviceEntityID) {
        this.deviceEntityID = deviceEntityID;
        return this;
    }

    @JsonProperty("deviceSerialNumber")
    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    @JsonProperty("deviceSerialNumber")
    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public Content withDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
        return this;
    }

    @JsonProperty("eventSeverity")
    public String getEventSeverity() {
        return eventSeverity;
    }

    @JsonProperty("eventSeverity")
    public void setEventSeverity(String eventSeverity) {
        this.eventSeverity = eventSeverity;
    }

    public Content withEventSeverity(String eventSeverity) {
        this.eventSeverity = eventSeverity;
        return this;
    }

    @JsonProperty("eventType")
    public String getEventType() {
        return eventType;
    }

    @JsonProperty("eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Content withEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    @JsonProperty("eventStart")
    public String getEventStart() {
        return eventStart;
    }

    @JsonProperty("eventStart")
    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public Content withEventStart(String eventStart) {
        this.eventStart = eventStart;
        return this;
    }

    @JsonProperty("eventEnd")
    public String getEventEnd() {
        return eventEnd;
    }

    @JsonProperty("eventEnd")
    public void setEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Content withEventEnd(String eventEnd) {
        this.eventEnd = eventEnd;
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


package tech.renovus.solarec.inverters.brand.fimer.api.devices.status;

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
    "deviceEntityID",
    "deviceName",
    "devicetate",
    "deviceSerialNumber",
    "deviceManufacturer",
    "loggerStatus"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("deviceEntityID")
    private Integer deviceEntityID;
    @JsonProperty("deviceName")
    private String deviceName;
    @JsonProperty("devicetate")
    private String devicetate;
    @JsonProperty("deviceSerialNumber")
    private String deviceSerialNumber;
    @JsonProperty("deviceManufacturer")
    private String deviceManufacturer;
    @JsonProperty("loggerStatus")
    private String loggerStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("deviceEntityID")
    public Integer getDeviceEntityID() {
        return deviceEntityID;
    }

    @JsonProperty("deviceEntityID")
    public void setDeviceEntityID(Integer deviceEntityID) {
        this.deviceEntityID = deviceEntityID;
    }

    public Result withDeviceEntityID(Integer deviceEntityID) {
        this.deviceEntityID = deviceEntityID;
        return this;
    }

    @JsonProperty("deviceName")
    public String getDeviceName() {
        return deviceName;
    }

    @JsonProperty("deviceName")
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Result withDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    @JsonProperty("devicetate")
    public String getDevicetate() {
        return devicetate;
    }

    @JsonProperty("devicetate")
    public void setDevicetate(String devicetate) {
        this.devicetate = devicetate;
    }

    public Result withDevicetate(String devicetate) {
        this.devicetate = devicetate;
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

    public Result withDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
        return this;
    }

    @JsonProperty("deviceManufacturer")
    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    @JsonProperty("deviceManufacturer")
    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public Result withDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
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

    public Result withLoggerStatus(String loggerStatus) {
        this.loggerStatus = loggerStatus;
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

package tech.renovus.solarec.inverters.brand.fimer.api.plants.status;

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
    "deviceState",
    "deviceSerialNumber",
    "deviceManufacturer",
    "deviceStatus"
})
@Generated("jsonschema2pojo")
public class DeviceStatus {

    @JsonProperty("deviceEntityID")
    private Integer deviceEntityID;
    @JsonProperty("deviceName")
    private String deviceName;
    @JsonProperty("deviceState")
    private String deviceState;
    @JsonProperty("deviceSerialNumber")
    private String deviceSerialNumber;
    @JsonProperty("deviceManufacturer")
    private String deviceManufacturer;
    @JsonProperty("deviceStatus")
    private String deviceStatus;
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

    public DeviceStatus withDeviceEntityID(Integer deviceEntityID) {
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

    public DeviceStatus withDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    @JsonProperty("deviceState")
    public String getDeviceState() {
        return deviceState;
    }

    @JsonProperty("deviceState")
    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    public DeviceStatus withDeviceState(String deviceState) {
        this.deviceState = deviceState;
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

    public DeviceStatus withDeviceSerialNumber(String deviceSerialNumber) {
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

    public DeviceStatus withDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
        return this;
    }

    @JsonProperty("deviceStatus")
    public String getDeviceStatus() {
        return deviceStatus;
    }

    @JsonProperty("deviceStatus")
    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public DeviceStatus withDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
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

    public DeviceStatus withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

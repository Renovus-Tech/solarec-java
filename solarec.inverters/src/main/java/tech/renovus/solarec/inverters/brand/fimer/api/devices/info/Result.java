
package tech.renovus.solarec.inverters.brand.fimer.api.devices.info;

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
    "deviceEntityID",
    "deviceCategory",
    "deviceName",
    "deviceDescription",
    "deviceState",
    "deviceSerialNumber",
    "deviceManufacturer",
    "deviceModel",
    "deviceFWVersion",
    "deviceCommunicationInterface",
    "deviceRS485Address",
    "deviceFirstReportedDate",
    "loggerEID"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("deviceEntityID")
    private Integer deviceEntityID;
    @JsonProperty("deviceCategory")
    private List<String> deviceCategory;
    @JsonProperty("deviceName")
    private String deviceName;
    @JsonProperty("deviceDescription")
    private String deviceDescription;
    @JsonProperty("deviceState")
    private String deviceState;
    @JsonProperty("deviceSerialNumber")
    private String deviceSerialNumber;
    @JsonProperty("deviceManufacturer")
    private String deviceManufacturer;
    @JsonProperty("deviceModel")
    private String deviceModel;
    @JsonProperty("deviceFWVersion")
    private String deviceFWVersion;
    @JsonProperty("deviceCommunicationInterface")
    private String deviceCommunicationInterface;
    @JsonProperty("deviceRS485Address")
    private String deviceRS485Address;
    @JsonProperty("deviceFirstReportedDate")
    private String deviceFirstReportedDate;
    @JsonProperty("loggerEID")
    private Integer loggerEID;
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

    @JsonProperty("deviceCategory")
    public List<String> getDeviceCategory() {
        return deviceCategory;
    }

    @JsonProperty("deviceCategory")
    public void setDeviceCategory(List<String> deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public Result withDeviceCategory(List<String> deviceCategory) {
        this.deviceCategory = deviceCategory;
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

    @JsonProperty("deviceDescription")
    public String getDeviceDescription() {
        return deviceDescription;
    }

    @JsonProperty("deviceDescription")
    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public Result withDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
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

    public Result withDeviceState(String deviceState) {
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

    @JsonProperty("deviceModel")
    public String getDeviceModel() {
        return deviceModel;
    }

    @JsonProperty("deviceModel")
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Result withDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    @JsonProperty("deviceFWVersion")
    public String getDeviceFWVersion() {
        return deviceFWVersion;
    }

    @JsonProperty("deviceFWVersion")
    public void setDeviceFWVersion(String deviceFWVersion) {
        this.deviceFWVersion = deviceFWVersion;
    }

    public Result withDeviceFWVersion(String deviceFWVersion) {
        this.deviceFWVersion = deviceFWVersion;
        return this;
    }

    @JsonProperty("deviceCommunicationInterface")
    public String getDeviceCommunicationInterface() {
        return deviceCommunicationInterface;
    }

    @JsonProperty("deviceCommunicationInterface")
    public void setDeviceCommunicationInterface(String deviceCommunicationInterface) {
        this.deviceCommunicationInterface = deviceCommunicationInterface;
    }

    public Result withDeviceCommunicationInterface(String deviceCommunicationInterface) {
        this.deviceCommunicationInterface = deviceCommunicationInterface;
        return this;
    }

    @JsonProperty("deviceRS485Address")
    public String getDeviceRS485Address() {
        return deviceRS485Address;
    }

    @JsonProperty("deviceRS485Address")
    public void setDeviceRS485Address(String deviceRS485Address) {
        this.deviceRS485Address = deviceRS485Address;
    }

    public Result withDeviceRS485Address(String deviceRS485Address) {
        this.deviceRS485Address = deviceRS485Address;
        return this;
    }

    @JsonProperty("deviceFirstReportedDate")
    public String getDeviceFirstReportedDate() {
        return deviceFirstReportedDate;
    }

    @JsonProperty("deviceFirstReportedDate")
    public void setDeviceFirstReportedDate(String deviceFirstReportedDate) {
        this.deviceFirstReportedDate = deviceFirstReportedDate;
    }

    public Result withDeviceFirstReportedDate(String deviceFirstReportedDate) {
        this.deviceFirstReportedDate = deviceFirstReportedDate;
        return this;
    }

    @JsonProperty("loggerEID")
    public Integer getLoggerEID() {
        return loggerEID;
    }

    @JsonProperty("loggerEID")
    public void setLoggerEID(Integer loggerEID) {
        this.loggerEID = loggerEID;
    }

    public Result withLoggerEID(Integer loggerEID) {
        this.loggerEID = loggerEID;
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

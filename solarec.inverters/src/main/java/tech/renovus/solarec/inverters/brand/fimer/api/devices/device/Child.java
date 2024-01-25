
package tech.renovus.solarec.inverters.brand.fimer.api.devices.device;

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
    "entityID",
    "type",
    "state",
    "serialNumber",
    "model",
    "deviceFWVersion",
    "macAddress",
    "communicationMaster"
})
@Generated("jsonschema2pojo")
public class Child {

    @JsonProperty("entityID")
    private Integer entityID;
    @JsonProperty("type")
    private String type;
    @JsonProperty("state")
    private String state;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("model")
    private String model;
    @JsonProperty("deviceFWVersion")
    private String deviceFWVersion;
    @JsonProperty("macAddress")
    private String macAddress;
    @JsonProperty("communicationMaster")
    private String communicationMaster;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("entityID")
    public Integer getEntityID() {
        return entityID;
    }

    @JsonProperty("entityID")
    public void setEntityID(Integer entityID) {
        this.entityID = entityID;
    }

    public Child withEntityID(Integer entityID) {
        this.entityID = entityID;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Child withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    public Child withState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Child withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    public Child withModel(String model) {
        this.model = model;
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

    public Child withDeviceFWVersion(String deviceFWVersion) {
        this.deviceFWVersion = deviceFWVersion;
        return this;
    }

    @JsonProperty("macAddress")
    public String getMacAddress() {
        return macAddress;
    }

    @JsonProperty("macAddress")
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Child withMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    @JsonProperty("communicationMaster")
    public String getCommunicationMaster() {
        return communicationMaster;
    }

    @JsonProperty("communicationMaster")
    public void setCommunicationMaster(String communicationMaster) {
        this.communicationMaster = communicationMaster;
    }

    public Child withCommunicationMaster(String communicationMaster) {
        this.communicationMaster = communicationMaster;
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

    public Child withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

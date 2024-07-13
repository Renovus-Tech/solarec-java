
package tech.renovus.solarec.inverters.brand.growatt.api;

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
    "last_update_time",
    "device_id",
    "device_sn",
    "lost",
    "model",
    "type",
    "datalogger_sn",
    "manufacturer",
    "status"
})
@Generated("jsonschema2pojo")
public class Device {

    @JsonProperty("last_update_time")
    private String lastUpdateTime;
    @JsonProperty("device_id")
    private Integer deviceId;
    @JsonProperty("device_sn")
    private String deviceSn;
    @JsonProperty("lost")
    private Boolean lost;
    @JsonProperty("model")
    private String model;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("datalogger_sn")
    private String dataloggerSn;
    @JsonProperty("manufacturer")
    private String manufacturer;
    @JsonProperty("status")
    private Integer status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("last_update_time")
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    @JsonProperty("last_update_time")
    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Device withLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    @JsonProperty("device_id")
    public Integer getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device_id")
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Device withDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @JsonProperty("device_sn")
    public String getDeviceSn() {
        return deviceSn;
    }

    @JsonProperty("device_sn")
    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Device withDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
        return this;
    }

    @JsonProperty("lost")
    public Boolean getLost() {
        return lost;
    }

    @JsonProperty("lost")
    public void setLost(Boolean lost) {
        this.lost = lost;
    }

    public Device withLost(Boolean lost) {
        this.lost = lost;
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

    public Device withModel(String model) {
        this.model = model;
        return this;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    public Device withType(Integer type) {
        this.type = type;
        return this;
    }

    @JsonProperty("datalogger_sn")
    public String getDataloggerSn() {
        return dataloggerSn;
    }

    @JsonProperty("datalogger_sn")
    public void setDataloggerSn(String dataloggerSn) {
        this.dataloggerSn = dataloggerSn;
    }

    public Device withDataloggerSn(String dataloggerSn) {
        this.dataloggerSn = dataloggerSn;
        return this;
    }

    @JsonProperty("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    @JsonProperty("manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Device withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Device withStatus(Integer status) {
        this.status = status;
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

    public Device withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

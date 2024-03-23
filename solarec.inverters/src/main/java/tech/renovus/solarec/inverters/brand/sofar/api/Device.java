
package tech.renovus.solarec.inverters.brand.sofar.api;

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
    "deviceSn",
    "deviceId",
    "deviceType",
    "deviceState",
    "updateTime"
})
@Generated("jsonschema2pojo")
public class Device {

    @JsonProperty("deviceSn")
    private String deviceSn;
    @JsonProperty("deviceId")
    private Integer deviceId;
    @JsonProperty("deviceType")
    private String deviceType;
    @JsonProperty("deviceState")
    private Integer deviceState;
    @JsonProperty("updateTime")
    private Integer updateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("deviceSn")
    public String getDeviceSn() {
        return deviceSn;
    }

    @JsonProperty("deviceSn")
    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Device withDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
        return this;
    }

    @JsonProperty("deviceId")
    public Integer getDeviceId() {
        return deviceId;
    }

    @JsonProperty("deviceId")
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Device withDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @JsonProperty("deviceType")
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty("deviceType")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Device withDeviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    @JsonProperty("deviceState")
    public Integer getDeviceState() {
        return deviceState;
    }

    @JsonProperty("deviceState")
    public void setDeviceState(Integer deviceState) {
        this.deviceState = deviceState;
    }

    public Device withDeviceState(Integer deviceState) {
        this.deviceState = deviceState;
        return this;
    }

    @JsonProperty("updateTime")
    public Integer getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("updateTime")
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Device withUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
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

    //--- Added code ----------------------------
	public static final String TYPE_INVERTER	= "INVERTER";
	public static final String TYPE_COLLECTOR	= "COLLECTOR";
	
	public static final Integer STATE_ONLINE	= Integer.valueOf(1);
	public static final Integer STATE_ALERTING	= Integer.valueOf(2);
	public static final Integer STATE_OFFLINE	= Integer.valueOf(3);
}

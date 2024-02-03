
package tech.renovus.solarec.inverters.brand.fimer.api.logger.devices;

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
    "devices"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("loggerEntityID")
    private Integer loggerEntityID;
    @JsonProperty("devices")
    private List<Device> devices;
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

    public Result withLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
        return this;
    }

    @JsonProperty("devices")
    public List<Device> getDevices() {
        return devices;
    }

    @JsonProperty("devices")
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Result withDevices(List<Device> devices) {
        this.devices = devices;
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


package tech.renovus.solarec.inverters.brand.fronius.api.history.data;

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
    "logDateTime",
    "logDuration",
    "channels"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("logDateTime")
    private String logDateTime;
    @JsonProperty("logDuration")
    private Integer logDuration;
    @JsonProperty("channels")
    private List<Channel> channels;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("logDateTime")
    public String getLogDateTime() {
        return logDateTime;
    }

    @JsonProperty("logDateTime")
    public void setLogDateTime(String logDateTime) {
        this.logDateTime = logDateTime;
    }

    public Datum withLogDateTime(String logDateTime) {
        this.logDateTime = logDateTime;
        return this;
    }

    @JsonProperty("logDuration")
    public Integer getLogDuration() {
        return logDuration;
    }

    @JsonProperty("logDuration")
    public void setLogDuration(Integer logDuration) {
        this.logDuration = logDuration;
    }

    public Datum withLogDuration(Integer logDuration) {
        this.logDuration = logDuration;
        return this;
    }

    @JsonProperty("channels")
    public List<Channel> getChannels() {
        return channels;
    }

    @JsonProperty("channels")
    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public Datum withChannels(List<Channel> channels) {
        this.channels = channels;
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

    public Datum withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

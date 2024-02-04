
package tech.renovus.solarec.inverters.brand.fronius.api.aggregated.specific;

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
    "logPeriod",
    "channels"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("logPeriod")
    private LogPeriod logPeriod;
    @JsonProperty("channels")
    private List<Channel> channels;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("logPeriod")
    public LogPeriod getLogPeriod() {
        return logPeriod;
    }

    @JsonProperty("logPeriod")
    public void setLogPeriod(LogPeriod logPeriod) {
        this.logPeriod = logPeriod;
    }

    public Data withLogPeriod(LogPeriod logPeriod) {
        this.logPeriod = logPeriod;
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

    public Data withChannels(List<Channel> channels) {
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

    public Data withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

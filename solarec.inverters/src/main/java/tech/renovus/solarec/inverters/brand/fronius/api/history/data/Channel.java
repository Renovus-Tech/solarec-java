
package tech.renovus.solarec.inverters.brand.fronius.api.history.data;

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
    "channelName",
    "channelType",
    "unit",
    "value"
})
@Generated("jsonschema2pojo")
public class Channel {

    @JsonProperty("channelName")
    private String channelName;
    @JsonProperty("channelType")
    private String channelType;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("value")
    private Integer value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("channelName")
    public String getChannelName() {
        return channelName;
    }

    @JsonProperty("channelName")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Channel withChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    @JsonProperty("channelType")
    public String getChannelType() {
        return channelType;
    }

    @JsonProperty("channelType")
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Channel withChannelType(String channelType) {
        this.channelType = channelType;
        return this;
    }

    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Channel withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
    }

    public Channel withValue(Integer value) {
        this.value = value;
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

    public Channel withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

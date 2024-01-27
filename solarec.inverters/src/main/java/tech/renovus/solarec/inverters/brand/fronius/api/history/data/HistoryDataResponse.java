
package tech.renovus.solarec.inverters.brand.fronius.api.history.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import org.apache.catalina.valves.ErrorReportValve;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.inverters.brand.fronius.api.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pvSystemId",
    "deviceId",
    "data",
    "links"
})
@Generated("jsonschema2pojo")
public class HistoryDataResponse extends ErrorResponse {

    @JsonProperty("pvSystemId")
    private String pvSystemId;
    @JsonProperty("deviceId")
    private Object deviceId;
    @JsonProperty("data")
    private List<Datum> data;
    @JsonProperty("links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("pvSystemId")
    public String getPvSystemId() {
        return pvSystemId;
    }

    @JsonProperty("pvSystemId")
    public void setPvSystemId(String pvSystemId) {
        this.pvSystemId = pvSystemId;
    }

    public HistoryDataResponse withPvSystemId(String pvSystemId) {
        this.pvSystemId = pvSystemId;
        return this;
    }

    @JsonProperty("deviceId")
    public Object getDeviceId() {
        return deviceId;
    }

    @JsonProperty("deviceId")
    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public HistoryDataResponse withDeviceId(Object deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

    public HistoryDataResponse withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    public HistoryDataResponse withLinks(Links links) {
        this.links = links;
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

    public HistoryDataResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

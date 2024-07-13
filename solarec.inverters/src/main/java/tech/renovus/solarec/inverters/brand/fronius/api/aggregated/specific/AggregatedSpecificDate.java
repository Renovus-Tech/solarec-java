
package tech.renovus.solarec.inverters.brand.fronius.api.aggregated.specific;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.inverters.brand.fronius.api.ErrorResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.Links;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pvSystemId",
    "data",
    "links"
})
@Generated("jsonschema2pojo")
public class AggregatedSpecificDate extends ErrorResponse {

    @JsonProperty("pvSystemId")
    private String pvSystemId;
    @JsonProperty("data")
    private Data data;
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

    public AggregatedSpecificDate withPvSystemId(String pvSystemId) {
        this.pvSystemId = pvSystemId;
        return this;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    public AggregatedSpecificDate withData(Data data) {
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

    public AggregatedSpecificDate withLinks(Links links) {
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

    public AggregatedSpecificDate withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

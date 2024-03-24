
package tech.renovus.solarec.inverters.brand.aiswei.api;

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
    "sid",
    "dataunit",
    "data"
})
@Generated("jsonschema2pojo")
public class PlantOutputResponse {

    @JsonProperty("sid")
    private Integer sid;
    @JsonProperty("dataunit")
    private String dataunit;
    @JsonProperty("data")
    private List<PlantOutputData> data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sid")
    public Integer getSid() {
        return sid;
    }

    @JsonProperty("sid")
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public PlantOutputResponse withSid(Integer sid) {
        this.sid = sid;
        return this;
    }

    @JsonProperty("dataunit")
    public String getDataunit() {
        return dataunit;
    }

    @JsonProperty("dataunit")
    public void setDataunit(String dataunit) {
        this.dataunit = dataunit;
    }

    public PlantOutputResponse withDataunit(String dataunit) {
        this.dataunit = dataunit;
        return this;
    }

    @JsonProperty("data")
    public List<PlantOutputData> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<PlantOutputData> data) {
        this.data = data;
    }

    public PlantOutputResponse withData(List<PlantOutputData> data) {
        this.data = data;
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

    public PlantOutputResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

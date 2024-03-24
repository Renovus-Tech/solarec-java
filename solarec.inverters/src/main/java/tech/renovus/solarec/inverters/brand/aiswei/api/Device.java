
package tech.renovus.solarec.inverters.brand.aiswei.api;

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
    "pstate",
    "inverters",
    "psn"
})
@Generated("jsonschema2pojo")
public class Device {

    @JsonProperty("pstate")
    private Integer pstate;
    @JsonProperty("inverters")
    private java.util.List<Inverter> inverters;
    @JsonProperty("psn")
    private String psn;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("pstate")
    public Integer getPstate() {
        return pstate;
    }

    @JsonProperty("pstate")
    public void setPstate(Integer pstate) {
        this.pstate = pstate;
    }

    public Device withPstate(Integer pstate) {
        this.pstate = pstate;
        return this;
    }

    @JsonProperty("inverters")
    public java.util.List<Inverter> getInverters() {
        return inverters;
    }

    @JsonProperty("inverters")
    public void setInverters(java.util.List<Inverter> inverters) {
        this.inverters = inverters;
    }

    public Device withInverters(java.util.List<Inverter> inverters) {
        this.inverters = inverters;
        return this;
    }

    @JsonProperty("psn")
    public String getPsn() {
        return psn;
    }

    @JsonProperty("psn")
    public void setPsn(String psn) {
        this.psn = psn;
    }

    public Device withPsn(String psn) {
        this.psn = psn;
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

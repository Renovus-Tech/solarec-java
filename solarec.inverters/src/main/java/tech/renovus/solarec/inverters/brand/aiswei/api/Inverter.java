
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
    "isn",
    "ludt",
    "istate"
})
@Generated("jsonschema2pojo")
public class Inverter {

    @JsonProperty("isn")
    private String isn;
    @JsonProperty("ludt")
    private String ludt;
    @JsonProperty("istate")
    private Integer istate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("isn")
    public String getIsn() {
        return isn;
    }

    @JsonProperty("isn")
    public void setIsn(String isn) {
        this.isn = isn;
    }

    public Inverter withIsn(String isn) {
        this.isn = isn;
        return this;
    }

    @JsonProperty("ludt")
    public String getLudt() {
        return ludt;
    }

    @JsonProperty("ludt")
    public void setLudt(String ludt) {
        this.ludt = ludt;
    }

    public Inverter withLudt(String ludt) {
        this.ludt = ludt;
        return this;
    }

    @JsonProperty("istate")
    public Integer getIstate() {
        return istate;
    }

    @JsonProperty("istate")
    public void setIstate(Integer istate) {
        this.istate = istate;
    }

    public Inverter withIstate(Integer istate) {
        this.istate = istate;
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

    public Inverter withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

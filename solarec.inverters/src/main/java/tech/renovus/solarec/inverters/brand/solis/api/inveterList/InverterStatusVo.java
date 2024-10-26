
package tech.renovus.solarec.inverters.brand.solis.api.inveterList;

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
    "all",
    "normal",
    "fault",
    "offline",
    "mppt"
})
@Generated("jsonschema2pojo")
public class InverterStatusVo {

    @JsonProperty("all")
    private Integer all;
    @JsonProperty("normal")
    private Integer normal;
    @JsonProperty("fault")
    private Integer fault;
    @JsonProperty("offline")
    private Integer offline;
    @JsonProperty("mppt")
    private Integer mppt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("all")
    public Integer getAll() {
        return all;
    }

    @JsonProperty("all")
    public void setAll(Integer all) {
        this.all = all;
    }

    public InverterStatusVo withAll(Integer all) {
        this.all = all;
        return this;
    }

    @JsonProperty("normal")
    public Integer getNormal() {
        return normal;
    }

    @JsonProperty("normal")
    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public InverterStatusVo withNormal(Integer normal) {
        this.normal = normal;
        return this;
    }

    @JsonProperty("fault")
    public Integer getFault() {
        return fault;
    }

    @JsonProperty("fault")
    public void setFault(Integer fault) {
        this.fault = fault;
    }

    public InverterStatusVo withFault(Integer fault) {
        this.fault = fault;
        return this;
    }

    @JsonProperty("offline")
    public Integer getOffline() {
        return offline;
    }

    @JsonProperty("offline")
    public void setOffline(Integer offline) {
        this.offline = offline;
    }

    public InverterStatusVo withOffline(Integer offline) {
        this.offline = offline;
        return this;
    }

    @JsonProperty("mppt")
    public Integer getMppt() {
        return mppt;
    }

    @JsonProperty("mppt")
    public void setMppt(Integer mppt) {
        this.mppt = mppt;
    }

    public InverterStatusVo withMppt(Integer mppt) {
        this.mppt = mppt;
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

    public InverterStatusVo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

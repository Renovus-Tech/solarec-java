
package tech.renovus.solarec.inverters.brand.enphase.api.data;

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
    "end_at",
    "powr",
    "enwh",
    "charge",
    "discharge",
    "soc"
})
@Generated("jsonschema2pojo")
public class Interval {

    @JsonProperty("end_at")
    private Integer endAt;
    @JsonProperty("powr")
    private Integer powr;
    @JsonProperty("enwh")
    private Integer enwh;
    @JsonProperty("charge")
    private Charge charge;
    @JsonProperty("discharge")
    private Discharge discharge;
    @JsonProperty("soc")
    private Soc soc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("end_at")
    public Integer getEndAt() {
        return endAt;
    }

    @JsonProperty("end_at")
    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public Interval withEndAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    @JsonProperty("powr")
    public Integer getPowr() {
        return powr;
    }

    @JsonProperty("powr")
    public void setPowr(Integer powr) {
        this.powr = powr;
    }

    public Interval withPowr(Integer powr) {
        this.powr = powr;
        return this;
    }

    @JsonProperty("enwh")
    public Integer getEnwh() {
        return enwh;
    }

    @JsonProperty("enwh")
    public void setEnwh(Integer enwh) {
        this.enwh = enwh;
    }

    public Interval withEnwh(Integer enwh) {
        this.enwh = enwh;
        return this;
    }

    @JsonProperty("charge")
    public Charge getCharge() {
        return charge;
    }

    @JsonProperty("charge")
    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Interval withCharge(Charge charge) {
        this.charge = charge;
        return this;
    }

    @JsonProperty("discharge")
    public Discharge getDischarge() {
        return discharge;
    }

    @JsonProperty("discharge")
    public void setDischarge(Discharge discharge) {
        this.discharge = discharge;
    }

    public Interval withDischarge(Discharge discharge) {
        this.discharge = discharge;
        return this;
    }

    @JsonProperty("soc")
    public Soc getSoc() {
        return soc;
    }

    @JsonProperty("soc")
    public void setSoc(Soc soc) {
        this.soc = soc;
    }

    public Interval withSoc(Soc soc) {
        this.soc = soc;
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

    public Interval withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

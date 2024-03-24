
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
    "isno",
    "e_today",
    "e_month",
    "e_total",
    "co2",
    "yield"
})
@Generated("jsonschema2pojo")
public class InverterOverviewData {

    @JsonProperty("isno")
    private String isno;
    @JsonProperty("e_today")
    private String eToday;
    @JsonProperty("e_month")
    private String eMonth;
    @JsonProperty("e_total")
    private String eTotal;
    @JsonProperty("co2")
    private String co2;
    @JsonProperty("yield")
    private String yield;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("isno")
    public String getIsno() {
        return isno;
    }

    @JsonProperty("isno")
    public void setIsno(String isno) {
        this.isno = isno;
    }

    public InverterOverviewData withIsno(String isno) {
        this.isno = isno;
        return this;
    }

    @JsonProperty("e_today")
    public String geteToday() {
        return eToday;
    }

    @JsonProperty("e_today")
    public void seteToday(String eToday) {
        this.eToday = eToday;
    }

    public InverterOverviewData witheToday(String eToday) {
        this.eToday = eToday;
        return this;
    }

    @JsonProperty("e_month")
    public String geteMonth() {
        return eMonth;
    }

    @JsonProperty("e_month")
    public void seteMonth(String eMonth) {
        this.eMonth = eMonth;
    }

    public InverterOverviewData witheMonth(String eMonth) {
        this.eMonth = eMonth;
        return this;
    }

    @JsonProperty("e_total")
    public String geteTotal() {
        return eTotal;
    }

    @JsonProperty("e_total")
    public void seteTotal(String eTotal) {
        this.eTotal = eTotal;
    }

    public InverterOverviewData witheTotal(String eTotal) {
        this.eTotal = eTotal;
        return this;
    }

    @JsonProperty("co2")
    public String getCo2() {
        return co2;
    }

    @JsonProperty("co2")
    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public InverterOverviewData withCo2(String co2) {
        this.co2 = co2;
        return this;
    }

    @JsonProperty("yield")
    public String getYield() {
        return yield;
    }

    @JsonProperty("yield")
    public void setYield(String yield) {
        this.yield = yield;
    }

    public InverterOverviewData withYield(String yield) {
        this.yield = yield;
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

    public InverterOverviewData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

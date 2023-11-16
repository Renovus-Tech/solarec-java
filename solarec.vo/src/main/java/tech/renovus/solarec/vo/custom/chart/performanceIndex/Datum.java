package tech.renovus.solarec.vo.custom.chart.performanceIndex;

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
    "from",
    "to",
    "genData",
    "totalProductionMwh",
    "totalACProductionMwh",
    "totalIrradiationKwhM2",
    "performanceRatio",
    "timeBasedAvailability"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("genData")
    private List<GenDatum> genData;
    @JsonProperty("totalProductionMwh")
    private Double totalProductionMwh;
    @JsonProperty("totalACProductionMwh")
    private Double totalACProductionMwh;
    @JsonProperty("totalIrradiationKwhM2")
    private Double totalIrradiationKwhM2;
    @JsonProperty("performanceRatio")
    private Double performanceRatio;
    @JsonProperty("timeBasedAvailability")
    private Double timeBasedAvailability;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    public Datum withFrom(String from) {
        this.from = from;
        return this;
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }

    public Datum withTo(String to) {
        this.to = to;
        return this;
    }

    @JsonProperty("genData")
    public List<GenDatum> getGenData() {
        return genData;
    }

    @JsonProperty("genData")
    public void setGenData(List<GenDatum> genData) {
        this.genData = genData;
    }

    public Datum withGenData(List<GenDatum> genData) {
        this.genData = genData;
        return this;
    }

    @JsonProperty("totalProductionMwh")
    public Double getTotalProductionMwh() {
        return totalProductionMwh;
    }

    @JsonProperty("totalProductionMwh")
    public void setTotalProductionMwh(Double totalProductionMwh) {
        this.totalProductionMwh = totalProductionMwh;
    }

    public Datum withTotalProductionMwh(Double totalProductionMwh) {
        this.totalProductionMwh = totalProductionMwh;
        return this;
    }

    @JsonProperty("totalACProductionMwh")
    public Double getTotalACProductionMwh() {
        return totalACProductionMwh;
    }

    @JsonProperty("totalACProductionMwh")
    public void setTotalACProductionMwh(Double totalACProductionMwh) {
        this.totalACProductionMwh = totalACProductionMwh;
    }

    public Datum withTotalACProductionMwh(Double totalACProductionMwh) {
        this.totalACProductionMwh = totalACProductionMwh;
        return this;
    }

    @JsonProperty("totalIrradiationKwhM2")
    public Double getTotalIrradiationKwhM2() {
        return totalIrradiationKwhM2;
    }

    @JsonProperty("totalIrradiationKwhM2")
    public void setTotalIrradiationKwhM2(Double totalIrradiationKwhM2) {
        this.totalIrradiationKwhM2 = totalIrradiationKwhM2;
    }

    public Datum withTotalIrradiationKwhM2(Double totalIrradiationKwhM2) {
        this.totalIrradiationKwhM2 = totalIrradiationKwhM2;
        return this;
    }

    @JsonProperty("performanceRatio")
    public Double getPerformanceRatio() {
        return performanceRatio;
    }

    @JsonProperty("performanceRatio")
    public void setPerformanceRatio(Double performanceRatio) {
        this.performanceRatio = performanceRatio;
    }

    public Datum withPerformanceRatio(Double performanceRatio) {
        this.performanceRatio = performanceRatio;
        return this;
    }

    @JsonProperty("timeBasedAvailability")
    public Double getTimeBasedAvailability() {
        return timeBasedAvailability;
    }

    @JsonProperty("timeBasedAvailability")
    public void setTimeBasedAvailability(Double timeBasedAvailability) {
        this.timeBasedAvailability = timeBasedAvailability;
    }

    public Datum withTimeBasedAvailability(Double timeBasedAvailability) {
        this.timeBasedAvailability = timeBasedAvailability;
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

    public Datum withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

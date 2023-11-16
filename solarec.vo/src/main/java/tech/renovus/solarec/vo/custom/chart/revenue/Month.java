package tech.renovus.solarec.vo.custom.chart.revenue;

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
    "label",
    "coAvoided",
    "dRecGenerated",
    "dRecPrice",
    "dRecSold",
    "dRecIncome"
})
@Generated("jsonschema2pojo")
public class Month {

    @JsonProperty("label")
    private String label;
    @JsonProperty("coAvoided")
    private Double coAvoided;
    @JsonProperty("dRecGenerated")
    private Double dRecGenerated;
    @JsonProperty("dRecPrice")
    private Double dRecPrice;
    @JsonProperty("dRecSold")
    private Double dRecSold;
    @JsonProperty("dRecIncome")
    private Double dRecIncome;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public Month withLabel(String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("coAvoided")
    public Double getCoAvoided() {
        return coAvoided;
    }

    @JsonProperty("coAvoided")
    public void setCoAvoided(Double coAvoided) {
        this.coAvoided = coAvoided;
    }

    public Month withCoAvoided(Double coAvoided) {
        this.coAvoided = coAvoided;
        return this;
    }

    @JsonProperty("dRecGenerated")
    public Double getdRecGenerated() {
        return dRecGenerated;
    }

    @JsonProperty("dRecPrice")
    public Double getdRecPrice() {
    	return dRecPrice;
    }
    
    @JsonProperty("dRecGenerated")
    public void setdRecGenerated(Double dRecGenerated) {
        this.dRecGenerated = dRecGenerated;
    }

    @JsonProperty("dRecPrice")
    public void setdRecPrice(Double dRecPrice) {
    	this.dRecPrice = dRecPrice;
    }
    
    public Month withdRecGenerated(Double dRecGenerated) {
        this.dRecGenerated = dRecGenerated;
        return this;
    }

    public Month withdRecPrice(Double dRecPrice) {
    	this.dRecPrice = dRecPrice;
    	return this;
    }
    
    @JsonProperty("dRecSold")
    public Double getdRecSold() {
        return dRecSold;
    }

    @JsonProperty("dRecSold")
    public void setdRecSold(Double dRecSold) {
        this.dRecSold = dRecSold;
    }

    public Month withdRecSold(Double dRecSold) {
        this.dRecSold = dRecSold;
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

    public Month withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @JsonProperty("dRecIncome")
    public Double getdRecIncome() {
        return dRecIncome;
    }

    @JsonProperty("dRecIncome")
    public void setdRecIncome(Double dRecIncome) {
        this.dRecIncome = dRecIncome;
    }

    public Month withdRecIncome(Double dRecIncome) {
        this.dRecIncome = dRecIncome;
        return this;
    }

}

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
    "certGenerated",
    "certPrice",
    "certSold",
    "certIncome"
})
@Generated("jsonschema2pojo")
public class Month {

    @JsonProperty("label")
    private String label;
    @JsonProperty("coAvoided")
    private Double coAvoided;
    @JsonProperty("certGenerated")
    private Double certGenerated;
    @JsonProperty("certPrice")
    private Double certPrice;
    @JsonProperty("certSold")
    private Double certSold;
    @JsonProperty("certIncome")
    private Double certIncome;
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

    @JsonProperty("certGenerated")
    public Double getCertGenerated() {
        return certGenerated;
    }

    @JsonProperty("certPrice")
    public Double getCertPrice() {
    	return certPrice;
    }
    
    @JsonProperty("certGenerated")
    public void setCertGenerated(Double certGenerated) {
        this.certGenerated = certGenerated;
    }

    @JsonProperty("certPrice")
    public void setCertPrice(Double certPrice) {
    	this.certPrice = certPrice;
    }
    
    public Month withCertGenerated(Double certGenerated) {
        this.certGenerated = certGenerated;
        return this;
    }

    public Month withCertPrice(Double certPrice) {
    	this.certPrice = certPrice;
    	return this;
    }
    
    @JsonProperty("certSold")
    public Double getCertSold() {
        return certSold;
    }

    @JsonProperty("certSold")
    public void setCertSold(Double certSold) {
        this.certSold = certSold;
    }

    public Month withCertSold(Double certSold) {
        this.certSold = certSold;
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

    @JsonProperty("certIncome")
    public Double getCertIncome() {
        return certIncome;
    }

    @JsonProperty("certIncome")
    public void setCertIncome(Double certIncome) {
        this.certIncome = certIncome;
    }

    public Month withCertIncome(Double certIncome) {
        this.certIncome = certIncome;
        return this;
    }

}


package tech.renovus.solarec.inverters.brand.fimer.api.organization;

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
    "portfolioEntityID",
    "portfolioName"
})
@Generated("jsonschema2pojo")
public class PortfolioGroupPortfolio {

    @JsonProperty("portfolioEntityID")
    private Integer portfolioEntityID;
    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("portfolioEntityID")
    public Integer getPortfolioEntityID() {
        return portfolioEntityID;
    }

    @JsonProperty("portfolioEntityID")
    public void setPortfolioEntityID(Integer portfolioEntityID) {
        this.portfolioEntityID = portfolioEntityID;
    }

    public PortfolioGroupPortfolio withPortfolioEntityID(Integer portfolioEntityID) {
        this.portfolioEntityID = portfolioEntityID;
        return this;
    }

    @JsonProperty("portfolioName")
    public String getPortfolioName() {
        return portfolioName;
    }

    @JsonProperty("portfolioName")
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public PortfolioGroupPortfolio withPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
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

    public PortfolioGroupPortfolio withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}


package tech.renovus.solarec.inverters.brand.fimer.api.organization;

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
    "portfolioGroupEntityID",
    "portfolioGroupName",
    "portfolioGroupPortfolios"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("portfolioGroupEntityID")
    private Integer portfolioGroupEntityID;
    @JsonProperty("portfolioGroupName")
    private String portfolioGroupName;
    @JsonProperty("portfolioGroupPortfolios")
    private List<PortfolioGroupPortfolio> portfolioGroupPortfolios;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("portfolioGroupEntityID")
    public Integer getPortfolioGroupEntityID() {
        return portfolioGroupEntityID;
    }

    @JsonProperty("portfolioGroupEntityID")
    public void setPortfolioGroupEntityID(Integer portfolioGroupEntityID) {
        this.portfolioGroupEntityID = portfolioGroupEntityID;
    }

    public Result withPortfolioGroupEntityID(Integer portfolioGroupEntityID) {
        this.portfolioGroupEntityID = portfolioGroupEntityID;
        return this;
    }

    @JsonProperty("portfolioGroupName")
    public String getPortfolioGroupName() {
        return portfolioGroupName;
    }

    @JsonProperty("portfolioGroupName")
    public void setPortfolioGroupName(String portfolioGroupName) {
        this.portfolioGroupName = portfolioGroupName;
    }

    public Result withPortfolioGroupName(String portfolioGroupName) {
        this.portfolioGroupName = portfolioGroupName;
        return this;
    }

    @JsonProperty("portfolioGroupPortfolios")
    public List<PortfolioGroupPortfolio> getPortfolioGroupPortfolios() {
        return portfolioGroupPortfolios;
    }

    @JsonProperty("portfolioGroupPortfolios")
    public void setPortfolioGroupPortfolios(List<PortfolioGroupPortfolio> portfolioGroupPortfolios) {
        this.portfolioGroupPortfolios = portfolioGroupPortfolios;
    }

    public Result withPortfolioGroupPortfolios(List<PortfolioGroupPortfolio> portfolioGroupPortfolios) {
        this.portfolioGroupPortfolios = portfolioGroupPortfolios;
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

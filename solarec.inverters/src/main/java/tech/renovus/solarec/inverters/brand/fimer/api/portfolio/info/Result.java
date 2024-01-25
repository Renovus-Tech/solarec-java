
package tech.renovus.solarec.inverters.brand.fimer.api.portfolio.info;

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
    "portfolioName",
    "portfolioState"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("portfolioEntityID")
    private Integer portfolioEntityID;
    @JsonProperty("portfolioName")
    private String portfolioName;
    @JsonProperty("portfolioState")
    private String portfolioState;
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

    public Result withPortfolioEntityID(Integer portfolioEntityID) {
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

    public Result withPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
        return this;
    }

    @JsonProperty("portfolioState")
    public String getPortfolioState() {
        return portfolioState;
    }

    @JsonProperty("portfolioState")
    public void setPortfolioState(String portfolioState) {
        this.portfolioState = portfolioState;
    }

    public Result withPortfolioState(String portfolioState) {
        this.portfolioState = portfolioState;
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

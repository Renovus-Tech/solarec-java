
package tech.renovus.solarec.grid.ember.api;

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
    "timestamp",
    "response_time_in_seconds",
    "rate_limit",
    "number_of_records",
    "query_parameters_used",
    "available_metrics",
    "query_value_range",
    "query_all_dates_value_range"
})
@Generated("jsonschema2pojo")
public class Stats {

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("response_time_in_seconds")
    private Double responseTimeInSeconds;
    @JsonProperty("rate_limit")
    private String rateLimit;
    @JsonProperty("number_of_records")
    private Integer numberOfRecords;
    @JsonProperty("query_parameters_used")
    private QueryParametersUsed queryParametersUsed;
    @JsonProperty("available_metrics")
    private List<String> availableMetrics;
    @JsonProperty("query_value_range")
    private QueryValueRange queryValueRange;
    @JsonProperty("query_all_dates_value_range")
    private QueryAllDatesValueRange queryAllDatesValueRange;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Stats withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("response_time_in_seconds")
    public Double getResponseTimeInSeconds() {
        return responseTimeInSeconds;
    }

    @JsonProperty("response_time_in_seconds")
    public void setResponseTimeInSeconds(Double responseTimeInSeconds) {
        this.responseTimeInSeconds = responseTimeInSeconds;
    }

    public Stats withResponseTimeInSeconds(Double responseTimeInSeconds) {
        this.responseTimeInSeconds = responseTimeInSeconds;
        return this;
    }

    @JsonProperty("rate_limit")
    public String getRateLimit() {
        return rateLimit;
    }

    @JsonProperty("rate_limit")
    public void setRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
    }

    public Stats withRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
        return this;
    }

    @JsonProperty("number_of_records")
    public Integer getNumberOfRecords() {
        return numberOfRecords;
    }

    @JsonProperty("number_of_records")
    public void setNumberOfRecords(Integer numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public Stats withNumberOfRecords(Integer numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
        return this;
    }

    @JsonProperty("query_parameters_used")
    public QueryParametersUsed getQueryParametersUsed() {
        return queryParametersUsed;
    }

    @JsonProperty("query_parameters_used")
    public void setQueryParametersUsed(QueryParametersUsed queryParametersUsed) {
        this.queryParametersUsed = queryParametersUsed;
    }

    public Stats withQueryParametersUsed(QueryParametersUsed queryParametersUsed) {
        this.queryParametersUsed = queryParametersUsed;
        return this;
    }

    @JsonProperty("available_metrics")
    public List<String> getAvailableMetrics() {
        return availableMetrics;
    }

    @JsonProperty("available_metrics")
    public void setAvailableMetrics(List<String> availableMetrics) {
        this.availableMetrics = availableMetrics;
    }

    public Stats withAvailableMetrics(List<String> availableMetrics) {
        this.availableMetrics = availableMetrics;
        return this;
    }

    @JsonProperty("query_value_range")
    public QueryValueRange getQueryValueRange() {
        return queryValueRange;
    }

    @JsonProperty("query_value_range")
    public void setQueryValueRange(QueryValueRange queryValueRange) {
        this.queryValueRange = queryValueRange;
    }

    public Stats withQueryValueRange(QueryValueRange queryValueRange) {
        this.queryValueRange = queryValueRange;
        return this;
    }

    @JsonProperty("query_all_dates_value_range")
    public QueryAllDatesValueRange getQueryAllDatesValueRange() {
        return queryAllDatesValueRange;
    }

    @JsonProperty("query_all_dates_value_range")
    public void setQueryAllDatesValueRange(QueryAllDatesValueRange queryAllDatesValueRange) {
        this.queryAllDatesValueRange = queryAllDatesValueRange;
    }

    public Stats withQueryAllDatesValueRange(QueryAllDatesValueRange queryAllDatesValueRange) {
        this.queryAllDatesValueRange = queryAllDatesValueRange;
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

    public Stats withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

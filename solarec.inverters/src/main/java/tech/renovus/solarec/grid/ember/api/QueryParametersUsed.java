
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
    "entity",
    "start_date",
    "include_all_dates_value_range"
})
@Generated("jsonschema2pojo")
public class QueryParametersUsed {

    @JsonProperty("entity")
    private List<String> entity;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("include_all_dates_value_range")
    private String includeAllDatesValueRange;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("entity")
    public List<String> getEntity() {
        return entity;
    }

    @JsonProperty("entity")
    public void setEntity(List<String> entity) {
        this.entity = entity;
    }

    public QueryParametersUsed withEntity(List<String> entity) {
        this.entity = entity;
        return this;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public QueryParametersUsed withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    @JsonProperty("include_all_dates_value_range")
    public String getIncludeAllDatesValueRange() {
        return includeAllDatesValueRange;
    }

    @JsonProperty("include_all_dates_value_range")
    public void setIncludeAllDatesValueRange(String includeAllDatesValueRange) {
        this.includeAllDatesValueRange = includeAllDatesValueRange;
    }

    public QueryParametersUsed withIncludeAllDatesValueRange(String includeAllDatesValueRange) {
        this.includeAllDatesValueRange = includeAllDatesValueRange;
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

    public QueryParametersUsed withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

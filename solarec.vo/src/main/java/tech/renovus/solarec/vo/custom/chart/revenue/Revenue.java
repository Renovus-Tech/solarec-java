package tech.renovus.solarec.vo.custom.chart.revenue;

import java.util.ArrayList;
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
    "months"
})
@Generated("jsonschema2pojo")
public class Revenue {

    @JsonProperty("months")
    private List<Month> months;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("months")
    public List<Month> getMonths() {
        return months;
    }

    @JsonProperty("months")
    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public Revenue withMonths(List<Month> months) {
        this.months = months;
        return this;
    }
    
    public Revenue add(Month month) {
    	if (this.months == null) this.months = new ArrayList<>();
    	this.months.add(month);
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

    public Revenue withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

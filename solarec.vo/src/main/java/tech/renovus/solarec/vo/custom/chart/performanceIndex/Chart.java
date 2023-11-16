package tech.renovus.solarec.vo.custom.chart.performanceIndex;

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
    "from",
    "to",
    "resultCode",
    "resultText",
    "groupBy"
})
@Generated("jsonschema2pojo")
public class Chart {

    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
    @JsonProperty("resultCode")
    private Integer resultCode;
    @JsonProperty("resultText")
    private String resultText;
    @JsonProperty("groupBy")
    private String groupBy;
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

    public Chart withFrom(String from) {
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

    public Chart withTo(String to) {
        this.to = to;
        return this;
    }

    @JsonProperty("resultCode")
    public Integer getResultCode() {
        return resultCode;
    }

    @JsonProperty("resultCode")
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public Chart withResultCode(Integer resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    @JsonProperty("resultText")
    public String getResultText() {
        return resultText;
    }

    @JsonProperty("resultText")
    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public Chart withResultText(String resultText) {
        this.resultText = resultText;
        return this;
    }

    @JsonProperty("groupBy")
    public String getGroupBy() {
        return groupBy;
    }

    @JsonProperty("groupBy")
    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public Chart withGroupBy(String groupBy) {
        this.groupBy = groupBy;
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

    public Chart withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

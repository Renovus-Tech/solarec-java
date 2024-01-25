package tech.renovus.solarec.inverters.brand.fimer.api.plants.info;

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
    "systemSize",
    "installDate",
    "firstReportedDate"
})
@Generated("jsonschema2pojo")
public class PlantConfiguration {

    @JsonProperty("systemSize")
    private Integer systemSize;
    @JsonProperty("installDate")
    private String installDate;
    @JsonProperty("firstReportedDate")
    private String firstReportedDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("systemSize")
    public Integer getSystemSize() {
        return systemSize;
    }

    @JsonProperty("systemSize")
    public void setSystemSize(Integer systemSize) {
        this.systemSize = systemSize;
    }

    public PlantConfiguration withSystemSize(Integer systemSize) {
        this.systemSize = systemSize;
        return this;
    }

    @JsonProperty("installDate")
    public String getInstallDate() {
        return installDate;
    }

    @JsonProperty("installDate")
    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public PlantConfiguration withInstallDate(String installDate) {
        this.installDate = installDate;
        return this;
    }

    @JsonProperty("firstReportedDate")
    public String getFirstReportedDate() {
        return firstReportedDate;
    }

    @JsonProperty("firstReportedDate")
    public void setFirstReportedDate(String firstReportedDate) {
        this.firstReportedDate = firstReportedDate;
    }

    public PlantConfiguration withFirstReportedDate(String firstReportedDate) {
        this.firstReportedDate = firstReportedDate;
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

    public PlantConfiguration withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

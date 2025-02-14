
package tech.renovus.solarec.grid.ember.api;

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
    "entity",
    "entity_code",
    "is_aggregate_entity",
    "date",
    "emissions_intensity_gco2_per_kwh"
})
@Generated("jsonschema2pojo")
public class CountryData {

    @JsonProperty("entity")
    private String entity;
    @JsonProperty("entity_code")
    private String entityCode;
    @JsonProperty("is_aggregate_entity")
    private Boolean isAggregateEntity;
    @JsonProperty("date")
    private String date;
    @JsonProperty("emissions_intensity_gco2_per_kwh")
    private Double emissionsIntensityGco2PerKwh;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("entity")
    public String getEntity() {
        return entity;
    }

    @JsonProperty("entity")
    public void setEntity(String entity) {
        this.entity = entity;
    }

    public CountryData withEntity(String entity) {
        this.entity = entity;
        return this;
    }

    @JsonProperty("entity_code")
    public String getEntityCode() {
        return entityCode;
    }

    @JsonProperty("entity_code")
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public CountryData withEntityCode(String entityCode) {
        this.entityCode = entityCode;
        return this;
    }

    @JsonProperty("is_aggregate_entity")
    public Boolean getIsAggregateEntity() {
        return isAggregateEntity;
    }

    @JsonProperty("is_aggregate_entity")
    public void setIsAggregateEntity(Boolean isAggregateEntity) {
        this.isAggregateEntity = isAggregateEntity;
    }

    public CountryData withIsAggregateEntity(Boolean isAggregateEntity) {
        this.isAggregateEntity = isAggregateEntity;
        return this;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    public CountryData withDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("emissions_intensity_gco2_per_kwh")
    public Double getEmissionsIntensityGco2PerKwh() {
        return emissionsIntensityGco2PerKwh;
    }

    @JsonProperty("emissions_intensity_gco2_per_kwh")
    public void setEmissionsIntensityGco2PerKwh(Double emissionsIntensityGco2PerKwh) {
        this.emissionsIntensityGco2PerKwh = emissionsIntensityGco2PerKwh;
    }

    public CountryData withEmissionsIntensityGco2PerKwh(Double emissionsIntensityGco2PerKwh) {
        this.emissionsIntensityGco2PerKwh = emissionsIntensityGco2PerKwh;
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

    public CountryData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

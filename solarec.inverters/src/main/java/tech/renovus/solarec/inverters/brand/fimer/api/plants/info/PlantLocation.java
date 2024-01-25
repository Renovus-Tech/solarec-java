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
    "country",
    "region",
    "city",
    "street1",
    "postalCode",
    "timeZone",
    "latitude",
    "longitude",
    "elevation"
})
@Generated("jsonschema2pojo")
public class PlantLocation {

    @JsonProperty("country")
    private String country;
    @JsonProperty("region")
    private String region;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street1")
    private String street1;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("elevation")
    private Double elevation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public PlantLocation withCountry(String country) {
        this.country = country;
        return this;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    public PlantLocation withRegion(String region) {
        this.region = region;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public PlantLocation withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("street1")
    public String getStreet1() {
        return street1;
    }

    @JsonProperty("street1")
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public PlantLocation withStreet1(String street1) {
        this.street1 = street1;
        return this;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public PlantLocation withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public PlantLocation withTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public PlantLocation withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public PlantLocation withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    @JsonProperty("elevation")
    public Double getElevation() {
        return elevation;
    }

    @JsonProperty("elevation")
    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public PlantLocation withElevation(Double elevation) {
        this.elevation = elevation;
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

    public PlantLocation withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

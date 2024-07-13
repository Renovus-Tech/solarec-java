
package tech.renovus.solarec.inverters.brand.growatt.api;

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
    "total_energy",
    "plant_id",
    "country",
    "latitude_f",
    "latitude_d",
    "city",
    "image_url",
    "latitude",
    "current_power",
    "locale",
    "peak_power",
    "operator",
    "installer",
    "user_id",
    "name",
    "create_date",
    "status",
    "longitude"
})
@Generated("jsonschema2pojo")
public class Plant {

    @JsonProperty("total_energy")
    private String totalEnergy;
    @JsonProperty("plant_id")
    private Integer plantId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("latitude_f")
    private Object latitudeF;
    @JsonProperty("latitude_d")
    private Object latitudeD;
    @JsonProperty("city")
    private String city;
    @JsonProperty("image_url")
    private Object imageUrl;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("current_power")
    private String currentPower;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("peak_power")
    private Double peakPower;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("installer")
    private String installer;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("create_date")
    private String createDate;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("longitude")
    private String longitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("total_energy")
    public String getTotalEnergy() {
        return totalEnergy;
    }

    @JsonProperty("total_energy")
    public void setTotalEnergy(String totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public Plant withTotalEnergy(String totalEnergy) {
        this.totalEnergy = totalEnergy;
        return this;
    }

    @JsonProperty("plant_id")
    public Integer getPlantId() {
        return plantId;
    }

    @JsonProperty("plant_id")
    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Plant withPlantId(Integer plantId) {
        this.plantId = plantId;
        return this;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public Plant withCountry(String country) {
        this.country = country;
        return this;
    }

    @JsonProperty("latitude_f")
    public Object getLatitudeF() {
        return latitudeF;
    }

    @JsonProperty("latitude_f")
    public void setLatitudeF(Object latitudeF) {
        this.latitudeF = latitudeF;
    }

    public Plant withLatitudeF(Object latitudeF) {
        this.latitudeF = latitudeF;
        return this;
    }

    @JsonProperty("latitude_d")
    public Object getLatitudeD() {
        return latitudeD;
    }

    @JsonProperty("latitude_d")
    public void setLatitudeD(Object latitudeD) {
        this.latitudeD = latitudeD;
    }

    public Plant withLatitudeD(Object latitudeD) {
        this.latitudeD = latitudeD;
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

    public Plant withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("image_url")
    public Object getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Plant withImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Plant withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    @JsonProperty("current_power")
    public String getCurrentPower() {
        return currentPower;
    }

    @JsonProperty("current_power")
    public void setCurrentPower(String currentPower) {
        this.currentPower = currentPower;
    }

    public Plant withCurrentPower(String currentPower) {
        this.currentPower = currentPower;
        return this;
    }

    @JsonProperty("locale")
    public String getLocale() {
        return locale;
    }

    @JsonProperty("locale")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Plant withLocale(String locale) {
        this.locale = locale;
        return this;
    }

    @JsonProperty("peak_power")
    public Double getPeakPower() {
        return peakPower;
    }

    @JsonProperty("peak_power")
    public void setPeakPower(Double peakPower) {
        this.peakPower = peakPower;
    }

    public Plant withPeakPower(Double peakPower) {
        this.peakPower = peakPower;
        return this;
    }

    @JsonProperty("operator")
    public String getOperator() {
        return operator;
    }

    @JsonProperty("operator")
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Plant withOperator(String operator) {
        this.operator = operator;
        return this;
    }

    @JsonProperty("installer")
    public String getInstaller() {
        return installer;
    }

    @JsonProperty("installer")
    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public Plant withInstaller(String installer) {
        this.installer = installer;
        return this;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Plant withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Plant withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("create_date")
    public String getCreateDate() {
        return createDate;
    }

    @JsonProperty("create_date")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Plant withCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Plant withStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Plant withLongitude(String longitude) {
        this.longitude = longitude;
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

    public Plant withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

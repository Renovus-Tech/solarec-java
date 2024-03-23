
package tech.renovus.solarec.inverters.brand.sofar.api;

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
    "id",
    "name",
    "locationLat",
    "locationLng",
    "locationAddress",
    "regionNationId",
    "regionLevel1",
    "regionLevel2",
    "regionLevel3",
    "regionLevel4",
    "regionLevel5",
    "regionTimezone",
    "type",
    "gridInterconnectionType",
    "installedCapacity",
    "startOperatingTime",
    "stationImage",
    "createdDate",
    "batterySoc",
    "networkStatus",
    "generationPower",
    "lastUpdateTime"
})
@Generated("jsonschema2pojo")
public class Station {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("locationLat")
    private Double locationLat;
    @JsonProperty("locationLng")
    private Double locationLng;
    @JsonProperty("locationAddress")
    private String locationAddress;
    @JsonProperty("regionNationId")
    private Integer regionNationId;
    @JsonProperty("regionLevel1")
    private Integer regionLevel1;
    @JsonProperty("regionLevel2")
    private Integer regionLevel2;
    @JsonProperty("regionLevel3")
    private Integer regionLevel3;
    @JsonProperty("regionLevel4")
    private Object regionLevel4;
    @JsonProperty("regionLevel5")
    private Object regionLevel5;
    @JsonProperty("regionTimezone")
    private String regionTimezone;
    @JsonProperty("type")
    private String type;
    @JsonProperty("gridInterconnectionType")
    private String gridInterconnectionType;
    @JsonProperty("installedCapacity")
    private Double installedCapacity;
    @JsonProperty("startOperatingTime")
    private Double startOperatingTime;
    @JsonProperty("stationImage")
    private Object stationImage;
    @JsonProperty("createdDate")
    private Double createdDate;
    @JsonProperty("batterySoc")
    private Double batterySoc;
    @JsonProperty("networkStatus")
    private String networkStatus;
    @JsonProperty("generationPower")
    private Double generationPower;
    @JsonProperty("lastUpdateTime")
    private Double lastUpdateTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Station withId(Integer id) {
        this.id = id;
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

    public Station withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("locationLat")
    public Double getLocationLat() {
        return locationLat;
    }

    @JsonProperty("locationLat")
    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Station withLocationLat(Double locationLat) {
        this.locationLat = locationLat;
        return this;
    }

    @JsonProperty("locationLng")
    public Double getLocationLng() {
        return locationLng;
    }

    @JsonProperty("locationLng")
    public void setLocationLng(Double locationLng) {
        this.locationLng = locationLng;
    }

    public Station withLocationLng(Double locationLng) {
        this.locationLng = locationLng;
        return this;
    }

    @JsonProperty("locationAddress")
    public String getLocationAddress() {
        return locationAddress;
    }

    @JsonProperty("locationAddress")
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public Station withLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    @JsonProperty("regionNationId")
    public Integer getRegionNationId() {
        return regionNationId;
    }

    @JsonProperty("regionNationId")
    public void setRegionNationId(Integer regionNationId) {
        this.regionNationId = regionNationId;
    }

    public Station withRegionNationId(Integer regionNationId) {
        this.regionNationId = regionNationId;
        return this;
    }

    @JsonProperty("regionLevel1")
    public Integer getRegionLevel1() {
        return regionLevel1;
    }

    @JsonProperty("regionLevel1")
    public void setRegionLevel1(Integer regionLevel1) {
        this.regionLevel1 = regionLevel1;
    }

    public Station withRegionLevel1(Integer regionLevel1) {
        this.regionLevel1 = regionLevel1;
        return this;
    }

    @JsonProperty("regionLevel2")
    public Integer getRegionLevel2() {
        return regionLevel2;
    }

    @JsonProperty("regionLevel2")
    public void setRegionLevel2(Integer regionLevel2) {
        this.regionLevel2 = regionLevel2;
    }

    public Station withRegionLevel2(Integer regionLevel2) {
        this.regionLevel2 = regionLevel2;
        return this;
    }

    @JsonProperty("regionLevel3")
    public Integer getRegionLevel3() {
        return regionLevel3;
    }

    @JsonProperty("regionLevel3")
    public void setRegionLevel3(Integer regionLevel3) {
        this.regionLevel3 = regionLevel3;
    }

    public Station withRegionLevel3(Integer regionLevel3) {
        this.regionLevel3 = regionLevel3;
        return this;
    }

    @JsonProperty("regionLevel4")
    public Object getRegionLevel4() {
        return regionLevel4;
    }

    @JsonProperty("regionLevel4")
    public void setRegionLevel4(Object regionLevel4) {
        this.regionLevel4 = regionLevel4;
    }

    public Station withRegionLevel4(Object regionLevel4) {
        this.regionLevel4 = regionLevel4;
        return this;
    }

    @JsonProperty("regionLevel5")
    public Object getRegionLevel5() {
        return regionLevel5;
    }

    @JsonProperty("regionLevel5")
    public void setRegionLevel5(Object regionLevel5) {
        this.regionLevel5 = regionLevel5;
    }

    public Station withRegionLevel5(Object regionLevel5) {
        this.regionLevel5 = regionLevel5;
        return this;
    }

    @JsonProperty("regionTimezone")
    public String getRegionTimezone() {
        return regionTimezone;
    }

    @JsonProperty("regionTimezone")
    public void setRegionTimezone(String regionTimezone) {
        this.regionTimezone = regionTimezone;
    }

    public Station withRegionTimezone(String regionTimezone) {
        this.regionTimezone = regionTimezone;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Station withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("gridInterconnectionType")
    public String getGridInterconnectionType() {
        return gridInterconnectionType;
    }

    @JsonProperty("gridInterconnectionType")
    public void setGridInterconnectionType(String gridInterconnectionType) {
        this.gridInterconnectionType = gridInterconnectionType;
    }

    public Station withGridInterconnectionType(String gridInterconnectionType) {
        this.gridInterconnectionType = gridInterconnectionType;
        return this;
    }

    @JsonProperty("installedCapacity")
    public Double getInstalledCapacity() {
        return installedCapacity;
    }

    @JsonProperty("installedCapacity")
    public void setInstalledCapacity(Double installedCapacity) {
        this.installedCapacity = installedCapacity;
    }

    public Station withInstalledCapacity(Double installedCapacity) {
        this.installedCapacity = installedCapacity;
        return this;
    }

    @JsonProperty("startOperatingTime")
    public Double getStartOperatingTime() {
        return startOperatingTime;
    }

    @JsonProperty("startOperatingTime")
    public void setStartOperatingTime(Double startOperatingTime) {
        this.startOperatingTime = startOperatingTime;
    }

    public Station withStartOperatingTime(Double startOperatingTime) {
        this.startOperatingTime = startOperatingTime;
        return this;
    }

    @JsonProperty("stationImage")
    public Object getStationImage() {
        return stationImage;
    }

    @JsonProperty("stationImage")
    public void setStationImage(Object stationImage) {
        this.stationImage = stationImage;
    }

    public Station withStationImage(Object stationImage) {
        this.stationImage = stationImage;
        return this;
    }

    @JsonProperty("createdDate")
    public Double getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(Double createdDate) {
        this.createdDate = createdDate;
    }

    public Station withCreatedDate(Double createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @JsonProperty("batterySoc")
    public Double getBatterySoc() {
        return batterySoc;
    }

    @JsonProperty("batterySoc")
    public void setBatterySoc(Double batterySoc) {
        this.batterySoc = batterySoc;
    }

    public Station withBatterySoc(Double batterySoc) {
        this.batterySoc = batterySoc;
        return this;
    }

    @JsonProperty("networkStatus")
    public String getNetworkStatus() {
        return networkStatus;
    }

    @JsonProperty("networkStatus")
    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public Station withNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
        return this;
    }

    @JsonProperty("generationPower")
    public Double getGenerationPower() {
        return generationPower;
    }

    @JsonProperty("generationPower")
    public void setGenerationPower(Double generationPower) {
        this.generationPower = generationPower;
    }

    public Station withGenerationPower(Double generationPower) {
        this.generationPower = generationPower;
        return this;
    }

    @JsonProperty("lastUpdateTime")
    public Double getLastUpdateTime() {
        return lastUpdateTime;
    }

    @JsonProperty("lastUpdateTime")
    public void setLastUpdateTime(Double lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Station withLastUpdateTime(Double lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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

    public Station withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

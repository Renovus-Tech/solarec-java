package tech.renovus.solarec.certificate.drecs.api.device;

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
@JsonPropertyOrder({ "externalId", "projectName", "address", "latitude", "longitude", "countryCode", "fuelCode",
		"deviceTypeCode", "capacity", "commissioningDate", "gridInterconnection", "offTaker", "impactStory", "images",
		"deviceDescription", "energyStorage", "energyStorageCapacity", "qualityLabels", "SDGBenefits", "version" })
@Generated("jsonschema2pojo")
public class DeviceResponse {

	@JsonProperty("externalId")
	private String externalId;
	@JsonProperty("projectName")
	private String projectName;
	@JsonProperty("address")
	private String address;
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("longitude")
	private String longitude;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("fuelCode")
	private String fuelCode;
	@JsonProperty("deviceTypeCode")
	private String deviceTypeCode;
	@JsonProperty("capacity")
	private Integer capacity;
	@JsonProperty("commissioningDate")
	private String commissioningDate;
	@JsonProperty("gridInterconnection")
	private Boolean gridInterconnection;
	@JsonProperty("offTaker")
	private String offTaker;
	@JsonProperty("impactStory")
	private String impactStory;
	@JsonProperty("images")
	private List<String> images;
	@JsonProperty("deviceDescription")
	private String deviceDescription;
	@JsonProperty("energyStorage")
	private Boolean energyStorage;
	@JsonProperty("energyStorageCapacity")
	private Integer energyStorageCapacity;
	@JsonProperty("qualityLabels")
	private String qualityLabels;
	@JsonProperty("SDGBenefits")
	private List<String> sDGBenefits;
	@JsonProperty("version")
	private String version;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("externalId")
	public String getExternalId() {
		return externalId;
	}

	@JsonProperty("externalId")
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public DeviceResponse withExternalId(String externalId) {
		this.externalId = externalId;
		return this;
	}

	@JsonProperty("projectName")
	public String getProjectName() {
		return projectName;
	}

	@JsonProperty("projectName")
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public DeviceResponse withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	public DeviceResponse withAddress(String address) {
		this.address = address;
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

	public DeviceResponse withLatitude(String latitude) {
		this.latitude = latitude;
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

	public DeviceResponse withLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public DeviceResponse withCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	@JsonProperty("fuelCode")
	public String getFuelCode() {
		return fuelCode;
	}

	@JsonProperty("fuelCode")
	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}

	public DeviceResponse withFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
		return this;
	}

	@JsonProperty("deviceTypeCode")
	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}

	@JsonProperty("deviceTypeCode")
	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}

	public DeviceResponse withDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
		return this;
	}

	@JsonProperty("capacity")
	public Integer getCapacity() {
		return capacity;
	}

	@JsonProperty("capacity")
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public DeviceResponse withCapacity(Integer capacity) {
		this.capacity = capacity;
		return this;
	}

	@JsonProperty("commissioningDate")
	public String getCommissioningDate() {
		return commissioningDate;
	}

	@JsonProperty("commissioningDate")
	public void setCommissioningDate(String commissioningDate) {
		this.commissioningDate = commissioningDate;
	}

	public DeviceResponse withCommissioningDate(String commissioningDate) {
		this.commissioningDate = commissioningDate;
		return this;
	}

	@JsonProperty("gridInterconnection")
	public Boolean getGridInterconnection() {
		return gridInterconnection;
	}

	@JsonProperty("gridInterconnection")
	public void setGridInterconnection(Boolean gridInterconnection) {
		this.gridInterconnection = gridInterconnection;
	}

	public DeviceResponse withGridInterconnection(Boolean gridInterconnection) {
		this.gridInterconnection = gridInterconnection;
		return this;
	}

	@JsonProperty("offTaker")
	public String getOffTaker() {
		return offTaker;
	}

	@JsonProperty("offTaker")
	public void setOffTaker(String offTaker) {
		this.offTaker = offTaker;
	}

	public DeviceResponse withOffTaker(String offTaker) {
		this.offTaker = offTaker;
		return this;
	}

	@JsonProperty("impactStory")
	public String getImpactStory() {
		return impactStory;
	}

	@JsonProperty("impactStory")
	public void setImpactStory(String impactStory) {
		this.impactStory = impactStory;
	}

	public DeviceResponse withImpactStory(String impactStory) {
		this.impactStory = impactStory;
		return this;
	}

	@JsonProperty("images")
	public List<String> getImages() {
		return images;
	}

	@JsonProperty("images")
	public void setImages(List<String> images) {
		this.images = images;
	}

	public DeviceResponse withImages(List<String> images) {
		this.images = images;
		return this;
	}

	@JsonProperty("deviceDescription")
	public String getDeviceDescription() {
		return deviceDescription;
	}

	@JsonProperty("deviceDescription")
	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	public DeviceResponse withDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
		return this;
	}

	@JsonProperty("energyStorage")
	public Boolean getEnergyStorage() {
		return energyStorage;
	}

	@JsonProperty("energyStorage")
	public void setEnergyStorage(Boolean energyStorage) {
		this.energyStorage = energyStorage;
	}

	public DeviceResponse withEnergyStorage(Boolean energyStorage) {
		this.energyStorage = energyStorage;
		return this;
	}

	@JsonProperty("energyStorageCapacity")
	public Integer getEnergyStorageCapacity() {
		return energyStorageCapacity;
	}

	@JsonProperty("energyStorageCapacity")
	public void setEnergyStorageCapacity(Integer energyStorageCapacity) {
		this.energyStorageCapacity = energyStorageCapacity;
	}

	public DeviceResponse withEnergyStorageCapacity(Integer energyStorageCapacity) {
		this.energyStorageCapacity = energyStorageCapacity;
		return this;
	}

	@JsonProperty("qualityLabels")
	public String getQualityLabels() {
		return qualityLabels;
	}

	@JsonProperty("qualityLabels")
	public void setQualityLabels(String qualityLabels) {
		this.qualityLabels = qualityLabels;
	}

	public DeviceResponse withQualityLabels(String qualityLabels) {
		this.qualityLabels = qualityLabels;
		return this;
	}

	@JsonProperty("SDGBenefits")
	public List<String> getSDGBenefits() {
		return sDGBenefits;
	}

	@JsonProperty("SDGBenefits")
	public void setSDGBenefits(List<String> sDGBenefits) {
		this.sDGBenefits = sDGBenefits;
	}

	public DeviceResponse withSDGBenefits(List<String> sDGBenefits) {
		this.sDGBenefits = sDGBenefits;
		return this;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	public DeviceResponse withVersion(String version) {
		this.version = version;
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

	public DeviceResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

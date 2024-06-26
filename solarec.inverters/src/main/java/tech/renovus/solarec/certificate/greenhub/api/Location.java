
package tech.renovus.solarec.certificate.greenhub.api;

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
@JsonPropertyOrder({ "external_id", "client_id", "name", "address", "state", "country", "country_code_2",
		"country_code_3", "coord_lat", "coord_lng", "code", "timezone_gmt", "output_capacity", "type_of_installation",
		"connection_to_grid", "sdgs" })
@Generated("jsonschema2pojo")
public class Location extends Mode {

	@JsonProperty("external_id")
	private Integer externalId;
	@JsonProperty("client_id")
	private Integer clientId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country")
	private String country;
	@JsonProperty("country_code_2")
	private String countryCode2;
	@JsonProperty("country_code_3")
	private String countryCode3;
	@JsonProperty("coord_lat")
	private Double coordLat;
	@JsonProperty("coord_lng")
	private Double coordLng;
	@JsonProperty("code")
	private String code;
	@JsonProperty("timezone_gmt")
	private Double timezoneGmt;
	@JsonProperty("output_capacity")
	private Double outputCapacity;
	@JsonProperty("type_of_installation")
	private String typeOfInstallation;
	@JsonProperty("connection_to_grid")
	private Boolean connectionToGrid;
	@JsonProperty("sdgs")
	private List<Sdg> sdgs;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("blockchain_network")
	private String blockchainNetwork;
	@JsonProperty("blockchain_address")
	private String blockchainAddress;
	@JsonProperty("ipfs_hash")
	private String ipfsHash;
	@JsonProperty("file_link")
	private String fileLink;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("external_id")
	public Integer getExternalId() {
		return externalId;
	}

	@JsonProperty("external_id")
	public void setExternalId(Integer externalId) {
		this.externalId = externalId;
	}

	public Location withExternalId(Integer externalId) {
		this.externalId = externalId;
		return this;
	}

	@JsonProperty("client_id")
	public Integer getClientId() {
		return clientId;
	}

	@JsonProperty("client_id")
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Location withClientId(Integer clientId) {
		this.clientId = clientId;
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

	public Location withName(String name) {
		this.name = name;
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

	public Location withAddress(String address) {
		this.address = address;
		return this;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	public Location withState(String state) {
		this.state = state;
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

	public Location withCountry(String country) {
		this.country = country;
		return this;
	}

	@JsonProperty("country_code_2")
	public String getCountryCode2() {
		return countryCode2;
	}

	@JsonProperty("country_code_2")
	public void setCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
	}

	public Location withCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
		return this;
	}

	@JsonProperty("country_code_3")
	public String getCountryCode3() {
		return countryCode3;
	}

	@JsonProperty("country_code_3")
	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}

	public Location withCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
		return this;
	}

	@JsonProperty("coord_lat")
	public Double getCoordLat() {
		return coordLat;
	}

	@JsonProperty("coord_lat")
	public void setCoordLat(Double coordLat) {
		this.coordLat = coordLat;
	}

	public Location withCoordLat(Double coordLat) {
		this.coordLat = coordLat;
		return this;
	}

	@JsonProperty("coord_lng")
	public Double getCoordLng() {
		return coordLng;
	}

	@JsonProperty("coord_lng")
	public void setCoordLng(Double coordLng) {
		this.coordLng = coordLng;
	}

	public Location withCoordLng(Double coordLng) {
		this.coordLng = coordLng;
		return this;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	public Location withCode(String code) {
		this.code = code;
		return this;
	}

	@JsonProperty("timezone_gmt")
	public Double getTimezoneGmt() {
		return timezoneGmt;
	}

	@JsonProperty("timezone_gmt")
	public void setTimezoneGmt(Double timezoneGmt) {
		this.timezoneGmt = timezoneGmt;
	}

	public Location withTimezoneGmt(Double timezoneGmt) {
		this.timezoneGmt = timezoneGmt;
		return this;
	}

	@JsonProperty("output_capacity")
	public Double getOutputCapacity() {
		return outputCapacity;
	}

	@JsonProperty("output_capacity")
	public void setOutputCapacity(Double outputCapacity) {
		this.outputCapacity = outputCapacity;
	}

	public Location withOutputCapacity(Double outputCapacity) {
		this.outputCapacity = outputCapacity;
		return this;
	}

	@JsonProperty("type_of_installation")
	public String getTypeOfInstallation() {
		return typeOfInstallation;
	}

	@JsonProperty("type_of_installation")
	public void setTypeOfInstallation(String typeOfInstallation) {
		this.typeOfInstallation = typeOfInstallation;
	}

	public Location withTypeOfInstallation(String typeOfInstallation) {
		this.typeOfInstallation = typeOfInstallation;
		return this;
	}

	@JsonProperty("connection_to_grid")
	public Boolean getConnectionToGrid() {
		return connectionToGrid;
	}

	@JsonProperty("connection_to_grid")
	public void setConnectionToGrid(Boolean connectionToGrid) {
		this.connectionToGrid = connectionToGrid;
	}

	public Location withConnectionToGrid(Boolean connectionToGrid) {
		this.connectionToGrid = connectionToGrid;
		return this;
	}

	@JsonProperty("sdgs")
	public List<Sdg> getSdgs() {
		return sdgs;
	}

	@JsonProperty("sdgs")
	public void setSdgs(List<Sdg> sdgs) {
		this.sdgs = sdgs;
	}

	public Location withSdgs(List<Sdg> sdgs) {
		this.sdgs = sdgs;
		return this;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	public Location withId(Integer id) {
		this.id = id;
		return this;
	}

	@JsonProperty("blockchain_network")
	public String getBlockchainNetwork() {
		return blockchainNetwork;
	}

	@JsonProperty("blockchain_network")
	public void setBlockchainNetwork(String blockchainNetwork) {
		this.blockchainNetwork = blockchainNetwork;
	}

	public Location withBlockchainNetwork(String blockchainNetwork) {
		this.blockchainNetwork = blockchainNetwork;
		return this;
	}

	@JsonProperty("blockchain_address")
	public String getBlockchainAddress() {
		return blockchainAddress;
	}

	@JsonProperty("blockchain_address")
	public void setBlockchainAddress(String blockchainAddress) {
		this.blockchainAddress = blockchainAddress;
	}

	public Location withBlockchainAddress(String blockchainAddress) {
		this.blockchainAddress = blockchainAddress;
		return this;
	}

	@JsonProperty("ipfs_hash")
	public String getIpfsHash() {
		return ipfsHash;
	}

	@JsonProperty("ipfs_hash")
	public void setIpfsHash(String ipfsHash) {
		this.ipfsHash = ipfsHash;
	}

	public Location withIpfsHash(String ipfsHash) {
		this.ipfsHash = ipfsHash;
		return this;
	}

	@JsonProperty("file_link")
	public String getFileLink() {
		return fileLink;
	}

	@JsonProperty("file_link")
	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public Location withFileLink(String fileLink) {
		this.fileLink = fileLink;
		return this;
	}

	@Override
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@Override
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public Location withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

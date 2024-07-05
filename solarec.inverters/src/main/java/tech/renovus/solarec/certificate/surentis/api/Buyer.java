package tech.renovus.solarec.certificate.surentis.api;

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
@JsonPropertyOrder({ "name", "id", "blockchain_network", "blockchain_address" })
@Generated("jsonschema2pojo")
public class Buyer extends Mode {

	@JsonProperty("name")
	private String name;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("blockchain_network")
	private String blockchainNetwork;
	@JsonProperty("blockchain_address")
	private String blockchainAddress;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Buyer withName(String name) {
		this.name = name;
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

	public Buyer withId(Integer id) {
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

	public Buyer withBlockchainNetwork(String blockchainNetwork) {
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

	public Buyer withBlockchainAddress(String blockchainAddress) {
		this.blockchainAddress = blockchainAddress;
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
	public Buyer withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
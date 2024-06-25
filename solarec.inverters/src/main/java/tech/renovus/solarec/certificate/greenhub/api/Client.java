package tech.renovus.solarec.certificate.greenhub.api;

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
@JsonPropertyOrder({ "external_id", "name", "name_legal", "name_address", "id" })
@Generated("jsonschema2pojo")
public class Client {

	@JsonProperty("external_id")
	private Integer externalId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("name_legal")
	private String nameLegal;
	@JsonProperty("name_address")
	private String nameAddress;
	@JsonProperty("id")
	private Integer id;
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

	public Client withExternalId(Integer externalId) {
		this.externalId = externalId;
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

	public Client withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("name_legal")
	public String getNameLegal() {
		return nameLegal;
	}

	@JsonProperty("name_legal")
	public void setNameLegal(String nameLegal) {
		this.nameLegal = nameLegal;
	}

	public Client withNameLegal(String nameLegal) {
		this.nameLegal = nameLegal;
		return this;
	}

	@JsonProperty("name_address")
	public String getNameAddress() {
		return nameAddress;
	}

	@JsonProperty("name_address")
	public void setNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
	}

	public Client withNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
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

	public Client withId(Integer id) {
		this.id = id;
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

	public Client withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
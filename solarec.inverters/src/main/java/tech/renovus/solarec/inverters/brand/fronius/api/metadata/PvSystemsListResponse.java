package tech.renovus.solarec.inverters.brand.fronius.api.metadata;

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

import tech.renovus.solarec.inverters.brand.fronius.api.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "pvSystemIds", "links" })
@Generated("jsonschema2pojo")
public class PvSystemsListResponse extends ErrorResponse {

	@JsonProperty("pvSystemIds")
	private List<String> pvSystemIds;
	@JsonProperty("links")
	private Links links;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("pvSystemIds")
	public List<String> getPvSystemIds() {
		return pvSystemIds;
	}

	@JsonProperty("pvSystemIds")
	public void setPvSystemIds(List<String> pvSystemIds) {
		this.pvSystemIds = pvSystemIds;
	}

	public PvSystemsListResponse withPvSystemIds(List<String> pvSystemIds) {
		this.pvSystemIds = pvSystemIds;
		return this;
	}

	@JsonProperty("links")
	public Links getLinks() {
		return links;
	}

	@JsonProperty("links")
	public void setLinks(Links links) {
		this.links = links;
	}

	public PvSystemsListResponse withLinks(Links links) {
		this.links = links;
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

	public PvSystemsListResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
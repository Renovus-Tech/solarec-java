package tech.renovus.solarec.inverters.brand.fimer.api.asset;

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
@JsonPropertyOrder({ "assetEntityID", "assetCategory", "assetName", "assetDescription", "assetState", "parentsEIDs" })
@Generated("jsonschema2pojo")
public class Result {

	@JsonProperty("assetEntityID")
	private Integer assetEntityID;
	@JsonProperty("assetCategory")
	private List<String> assetCategory;
	@JsonProperty("assetName")
	private String assetName;
	@JsonProperty("assetDescription")
	private String assetDescription;
	@JsonProperty("assetState")
	private String assetState;
	@JsonProperty("parentsEIDs")
	private List<Integer> parentsEIDs;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("assetEntityID")
	public Integer getAssetEntityID() {
		return assetEntityID;
	}

	@JsonProperty("assetEntityID")
	public void setAssetEntityID(Integer assetEntityID) {
		this.assetEntityID = assetEntityID;
	}

	public Result withAssetEntityID(Integer assetEntityID) {
		this.assetEntityID = assetEntityID;
		return this;
	}

	@JsonProperty("assetCategory")
	public List<String> getAssetCategory() {
		return assetCategory;
	}

	@JsonProperty("assetCategory")
	public void setAssetCategory(List<String> assetCategory) {
		this.assetCategory = assetCategory;
	}

	public Result withAssetCategory(List<String> assetCategory) {
		this.assetCategory = assetCategory;
		return this;
	}

	@JsonProperty("assetName")
	public String getAssetName() {
		return assetName;
	}

	@JsonProperty("assetName")
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Result withAssetName(String assetName) {
		this.assetName = assetName;
		return this;
	}

	@JsonProperty("assetDescription")
	public String getAssetDescription() {
		return assetDescription;
	}

	@JsonProperty("assetDescription")
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public Result withAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
		return this;
	}

	@JsonProperty("assetState")
	public String getAssetState() {
		return assetState;
	}

	@JsonProperty("assetState")
	public void setAssetState(String assetState) {
		this.assetState = assetState;
	}

	public Result withAssetState(String assetState) {
		this.assetState = assetState;
		return this;
	}

	@JsonProperty("parentsEIDs")
	public List<Integer> getParentsEIDs() {
		return parentsEIDs;
	}

	@JsonProperty("parentsEIDs")
	public void setParentsEIDs(List<Integer> parentsEIDs) {
		this.parentsEIDs = parentsEIDs;
	}

	public Result withParentsEIDs(List<Integer> parentsEIDs) {
		this.parentsEIDs = parentsEIDs;
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

	public Result withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
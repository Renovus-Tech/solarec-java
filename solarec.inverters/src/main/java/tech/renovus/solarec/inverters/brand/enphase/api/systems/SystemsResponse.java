
package tech.renovus.solarec.inverters.brand.enphase.api.systems;

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

import tech.renovus.solarec.inverters.brand.enphase.api.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "current_page", "size", "count", "items", "systems" })
@Generated("jsonschema2pojo")
public class SystemsResponse extends ErrorResponse {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("current_page")
	private Integer currentPage;
	@JsonProperty("size")
	private Integer size;
	@JsonProperty("count")
	private Integer count;
	@JsonProperty("items")
	private String items;
	@JsonProperty("systems")
	private List<System> systems;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	public SystemsResponse withTotal(Integer total) {
		this.total = total;
		return this;
	}

	@JsonProperty("current_page")
	public Integer getCurrentPage() {
		return currentPage;
	}

	@JsonProperty("current_page")
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public SystemsResponse withCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		return this;
	}

	@JsonProperty("size")
	public Integer getSize() {
		return size;
	}

	@JsonProperty("size")
	public void setSize(Integer size) {
		this.size = size;
	}

	public SystemsResponse withSize(Integer size) {
		this.size = size;
		return this;
	}

	@JsonProperty("count")
	public Integer getCount() {
		return count;
	}

	@JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
	}

	public SystemsResponse withCount(Integer count) {
		this.count = count;
		return this;
	}

	@JsonProperty("items")
	public String getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems(String items) {
		this.items = items;
	}

	public SystemsResponse withItems(String items) {
		this.items = items;
		return this;
	}

	@JsonProperty("systems")
	public List<System> getSystems() {
		return systems;
	}

	@JsonProperty("systems")
	public void setSystems(List<System> systems) {
		this.systems = systems;
	}

	public SystemsResponse withSystems(List<System> systems) {
		this.systems = systems;
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
	public SystemsResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

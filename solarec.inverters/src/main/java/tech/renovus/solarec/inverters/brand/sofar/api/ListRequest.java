
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
@JsonPropertyOrder({ "page", "size" })
@Generated("jsonschema2pojo")
public class ListRequest {

	@JsonProperty("page")
	private Integer page;
	@JsonProperty("size")
	private Integer size;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("page")
	public Integer getPage() {
		return page;
	}

	@JsonProperty("page")
	public void setPage(Integer page) {
		this.page = page;
	}

	public ListRequest withPage(Integer page) {
		this.page = page;
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

	public ListRequest withSize(Integer size) {
		this.size = size;
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

	public ListRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}
	
	
	///--- Addded code --------------------------
	public static final ListRequest DEFAULT_VALUES = new ListRequest().withPage(Integer.valueOf(1)).withSize(Integer.valueOf(50));

}

package tech.renovus.solarec.inverters.brand.solis.api.inveterList;

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
@JsonPropertyOrder({ "inverterStatusVo", "page", "mpptSwitch" })
@Generated("jsonschema2pojo")
public class InverterList {

	@JsonProperty("inverterStatusVo")
	private InverterStatusVo inverterStatusVo;
	@JsonProperty("page")
	private Page page;
	@JsonProperty("mpptSwitch")
	private Integer mpptSwitch;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("inverterStatusVo")
	public InverterStatusVo getInverterStatusVo() {
		return inverterStatusVo;
	}

	@JsonProperty("inverterStatusVo")
	public void setInverterStatusVo(InverterStatusVo inverterStatusVo) {
		this.inverterStatusVo = inverterStatusVo;
	}

	public InverterList withInverterStatusVo(InverterStatusVo inverterStatusVo) {
		this.inverterStatusVo = inverterStatusVo;
		return this;
	}

	@JsonProperty("page")
	public Page getPage() {
		return page;
	}

	@JsonProperty("page")
	public void setPage(Page page) {
		this.page = page;
	}

	public InverterList withPage(Page page) {
		this.page = page;
		return this;
	}

	@JsonProperty("mpptSwitch")
	public Integer getMpptSwitch() {
		return mpptSwitch;
	}

	@JsonProperty("mpptSwitch")
	public void setMpptSwitch(Integer mpptSwitch) {
		this.mpptSwitch = mpptSwitch;
	}

	public InverterList withMpptSwitch(Integer mpptSwitch) {
		this.mpptSwitch = mpptSwitch;
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

	public InverterList withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

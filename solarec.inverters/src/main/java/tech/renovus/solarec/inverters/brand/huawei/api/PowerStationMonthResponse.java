package tech.renovus.solarec.inverters.brand.huawei.api;

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
@JsonPropertyOrder({ "success", "data" })
@Generated("jsonschema2pojo")
public class PowerStationMonthResponse extends BasePowerStationResponse {

	@JsonProperty("success")
	private Boolean success;
	@JsonProperty("data")
	private List<PowerStationMonthData> data;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@Override
	@JsonProperty("success")
	public Boolean getSuccess() {
		return success;
	}

	@Override
	@JsonProperty("success")
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public PowerStationMonthResponse withSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	@JsonProperty("data")
	public List<PowerStationMonthData> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<PowerStationMonthData> data) {
		this.data = data;
	}

	public PowerStationMonthResponse withData(List<PowerStationMonthData> data) {
		this.data = data;
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
	public PowerStationMonthResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
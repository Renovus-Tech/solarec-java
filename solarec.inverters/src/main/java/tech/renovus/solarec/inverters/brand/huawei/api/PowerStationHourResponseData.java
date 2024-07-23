package tech.renovus.solarec.inverters.brand.huawei.api;

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
@JsonPropertyOrder({ "dataItemMap", "stationCode", "collectTime" })
@Generated("jsonschema2pojo")
public class PowerStationHourResponseData {

	@JsonProperty("dataItemMap")
	private PowerStationHourResponseDataMap dataItemMap;
	@JsonProperty("stationCode")
	private String stationCode;
	@JsonProperty("collectTime")
	private Long collectTime;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("dataItemMap")
	public PowerStationHourResponseDataMap getDataItemMap() {
		return dataItemMap;
	}

	@JsonProperty("dataItemMap")
	public void setDataItemMap(PowerStationHourResponseDataMap dataItemMap) {
		this.dataItemMap = dataItemMap;
	}

	public PowerStationHourResponseData withDataItemMap(PowerStationHourResponseDataMap dataItemMap) {
		this.dataItemMap = dataItemMap;
		return this;
	}

	@JsonProperty("stationCode")
	public String getStationCode() {
		return stationCode;
	}

	@JsonProperty("stationCode")
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public PowerStationHourResponseData withStationCode(String stationCode) {
		this.stationCode = stationCode;
		return this;
	}

	@JsonProperty("collectTime")
	public Long getCollectTime() {
		return collectTime;
	}

	@JsonProperty("collectTime")
	public void setCollectTime(Long collectTime) {
		this.collectTime = collectTime;
	}

	public PowerStationHourResponseData withCollectTime(Long collectTime) {
		this.collectTime = collectTime;
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

	public PowerStationHourResponseData withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
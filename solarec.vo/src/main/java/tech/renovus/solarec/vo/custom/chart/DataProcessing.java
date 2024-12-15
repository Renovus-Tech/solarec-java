package tech.renovus.solarec.vo.custom.chart;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.vo.db.data.DataProcessingVo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "data_pro_id" })
@Generated("jsonschema2pojo")
public class DataProcessing {

	@JsonProperty("data_pro_id")
	private Integer dataProId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("data_pro_id")
	public Integer getDataProId() {
		return dataProId;
	}

	@JsonProperty("data_pro_id")
	public void setDataProId(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public DataProcessing withDataProId(Integer dataProId) {
		this.dataProId = dataProId;
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

	public DataProcessing withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}
	
	public DataProcessing() {}
	public DataProcessing(DataProcessingVo dataProVo) {
		this.dataProId = dataProVo.getDataProId();
	}
}
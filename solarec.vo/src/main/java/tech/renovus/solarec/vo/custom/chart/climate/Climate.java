package tech.renovus.solarec.vo.custom.chart.climate;

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

import tech.renovus.solarec.vo.custom.chart.BaseError;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "chart", "data" })
@Generated("jsonschema2pojo")
public class Climate extends BaseError {

	@JsonProperty("chart")
	private Chart chart;
	@JsonProperty("data")
	private List<Datum> data;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("chart")
	public Chart getChart() {
		return chart;
	}

	@JsonProperty("chart")
	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public Climate withChart(Chart chart) {
		this.chart = chart;
		return this;
	}

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	public Climate withData(List<Datum> data) {
		this.data = data;
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

	public Climate withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
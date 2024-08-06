package tech.renovus.solarec.vo.custom.chart.overview;

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
@JsonPropertyOrder({ "id", "name", "code", "productionMwh", "irradiationKwhM2", "avgAmbientTemp", "avgModuleTemp",
		"timeBasedAvailability", "specificYield", "performanceRatio" })
@Generated("jsonschema2pojo")
public class Datum {

	@JsonProperty("id")
	private List<Integer> id;
	@JsonProperty("name")
	private List<String> name;
	@JsonProperty("code")
	private List<String> code;
	@JsonProperty("productionMwh")
	private Double productionMwh;
	@JsonProperty("irradiationKwhM2")
	private Double irradiationKwhM2;
	@JsonProperty("avgAmbientTemp")
	private Double avgAmbientTemp;
	@JsonProperty("avgModuleTemp")
	private Double avgModuleTemp;
	@JsonProperty("timeBasedAvailability")
	private Double timeBasedAvailability;
	@JsonProperty("specificYield")
	private Double specificYield;
	@JsonProperty("performanceRatio")
	private Double performanceRatio;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("id")
	public List<Integer> getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(List<Integer> id) {
		this.id = id;
	}

	public Datum withId(List<Integer> id) {
		this.id = id;
		return this;
	}

	@JsonProperty("name")
	public List<String> getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(List<String> name) {
		this.name = name;
	}

	public Datum withName(List<String> name) {
		this.name = name;
		return this;
	}

	@JsonProperty("code")
	public List<String> getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(List<String> code) {
		this.code = code;
	}

	public Datum withCode(List<String> code) {
		this.code = code;
		return this;
	}

	@JsonProperty("productionMwh")
	public Double getProductionMwh() {
		return productionMwh;
	}

	@JsonProperty("productionMwh")
	public void setProductionMwh(Double productionMwh) {
		this.productionMwh = productionMwh;
	}

	public Datum withProductionMwh(Double productionMwh) {
		this.productionMwh = productionMwh;
		return this;
	}

	@JsonProperty("irradiationKwhM2")
	public Double getIrradiationKwhM2() {
		return irradiationKwhM2;
	}

	@JsonProperty("irradiationKwhM2")
	public void setIrradiationKwhM2(Double irradiationKwhM2) {
		this.irradiationKwhM2 = irradiationKwhM2;
	}

	public Datum withIrradiationKwhM2(Double irradiationKwhM2) {
		this.irradiationKwhM2 = irradiationKwhM2;
		return this;
	}

	@JsonProperty("avgAmbientTemp")
	public Double getAvgAmbientTemp() {
		return avgAmbientTemp;
	}

	@JsonProperty("avgAmbientTemp")
	public void setAvgAmbientTemp(Double avgAmbientTemp) {
		this.avgAmbientTemp = avgAmbientTemp;
	}

	public Datum withAvgAmbientTemp(Double avgAmbientTemp) {
		this.avgAmbientTemp = avgAmbientTemp;
		return this;
	}

	@JsonProperty("avgModuleTemp")
	public Double getAvgModuleTemp() {
		return avgModuleTemp;
	}

	@JsonProperty("avgModuleTemp")
	public void setAvgModuleTemp(Double avgModuleTemp) {
		this.avgModuleTemp = avgModuleTemp;
	}

	public Datum withAvgModuleTemp(Double avgModuleTemp) {
		this.avgModuleTemp = avgModuleTemp;
		return this;
	}

	@JsonProperty("timeBasedAvailability")
	public Double getTimeBasedAvailability() {
		return timeBasedAvailability;
	}

	@JsonProperty("timeBasedAvailability")
	public void setTimeBasedAvailability(Double timeBasedAvailability) {
		this.timeBasedAvailability = timeBasedAvailability;
	}

	public Datum withTimeBasedAvailability(Double timeBasedAvailability) {
		this.timeBasedAvailability = timeBasedAvailability;
		return this;
	}

	@JsonProperty("specificYield")
	public Double getSpecificYield() {
		return specificYield;
	}

	@JsonProperty("specificYield")
	public void setSpecificYield(Double specificYield) {
		this.specificYield = specificYield;
	}

	public Datum withSpecificYield(Double specificYield) {
		this.specificYield = specificYield;
		return this;
	}

	@JsonProperty("performanceRatio")
	public Double getPerformanceRatio() {
		return performanceRatio;
	}

	@JsonProperty("performanceRatio")
	public void setPerformanceRatio(Double performanceRatio) {
		this.performanceRatio = performanceRatio;
	}

	public Datum withPerformanceRatio(Double performanceRatio) {
		this.performanceRatio = performanceRatio;
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

	public Datum withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
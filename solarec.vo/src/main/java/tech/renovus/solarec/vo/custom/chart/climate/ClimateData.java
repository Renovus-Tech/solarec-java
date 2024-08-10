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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "from", "to", "genData", "totalProductionMwh", "totalACProductionMwh", "totalIrradiationKwhM2",
		"avgAmbientTemp", "avgModuleTemp" })
@Generated("jsonschema2pojo")
public class ClimateData {

	@JsonProperty("from")
	private String from;
	@JsonProperty("to")
	private String to;
	@JsonProperty("genData")
	private List<ClimateGenData> genData;
	@JsonProperty("totalProductionMwh")
	private Double totalProductionMwh;
	@JsonProperty("totalACProductionMwh")
	private Double totalACProductionMwh;
	@JsonProperty("totalIrradiationKwhM2")
	private Double totalIrradiationKwhM2;
	@JsonProperty("avgAmbientTemp")
	private Double avgAmbientTemp;
	@JsonProperty("avgModuleTemp")
	private Double avgModuleTemp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("from")
	public String getFrom() {
		return from;
	}

	@JsonProperty("from")
	public void setFrom(String from) {
		this.from = from;
	}

	public ClimateData withFrom(String from) {
		this.from = from;
		return this;
	}

	@JsonProperty("to")
	public String getTo() {
		return to;
	}

	@JsonProperty("to")
	public void setTo(String to) {
		this.to = to;
	}

	public ClimateData withTo(String to) {
		this.to = to;
		return this;
	}

	@JsonProperty("genData")
	public List<ClimateGenData> getGenData() {
		return genData;
	}

	@JsonProperty("genData")
	public void setGenData(List<ClimateGenData> genData) {
		this.genData = genData;
	}

	public ClimateData withGenData(List<ClimateGenData> genData) {
		this.genData = genData;
		return this;
	}

	@JsonProperty("totalProductionMwh")
	public Double getTotalProductionMwh() {
		return totalProductionMwh;
	}

	@JsonProperty("totalProductionMwh")
	public void setTotalProductionMwh(Double totalProductionMwh) {
		this.totalProductionMwh = totalProductionMwh;
	}

	public ClimateData withTotalProductionMwh(Double totalProductionMwh) {
		this.totalProductionMwh = totalProductionMwh;
		return this;
	}

	@JsonProperty("totalACProductionMwh")
	public Double getTotalACProductionMwh() {
		return totalACProductionMwh;
	}

	@JsonProperty("totalACProductionMwh")
	public void setTotalACProductionMwh(Double totalACProductionMwh) {
		this.totalACProductionMwh = totalACProductionMwh;
	}

	public ClimateData withTotalACProductionMwh(Double totalACProductionMwh) {
		this.totalACProductionMwh = totalACProductionMwh;
		return this;
	}

	@JsonProperty("totalIrradiationKwhM2")
	public Double getTotalIrradiationKwhM2() {
		return totalIrradiationKwhM2;
	}

	@JsonProperty("totalIrradiationKwhM2")
	public void setTotalIrradiationKwhM2(Double totalIrradiationKwhM2) {
		this.totalIrradiationKwhM2 = totalIrradiationKwhM2;
	}

	public ClimateData withTotalIrradiationKwhM2(Double totalIrradiationKwhM2) {
		this.totalIrradiationKwhM2 = totalIrradiationKwhM2;
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

	public ClimateData withAvgAmbientTemp(Double avgAmbientTemp) {
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

	public ClimateData withAvgModuleTemp(Double avgModuleTemp) {
		this.avgModuleTemp = avgModuleTemp;
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

	public ClimateData withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
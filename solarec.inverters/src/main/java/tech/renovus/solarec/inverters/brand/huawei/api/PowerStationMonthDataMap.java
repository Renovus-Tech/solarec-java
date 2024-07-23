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
@JsonPropertyOrder({ "use_power", "radiation_intensity", "reduction_total_co2", "reduction_total_coal",
		"inverter_power", "theory_power", "ongrid_power", "power_profit", "installed_capacity", "perpower_ratio",
		"reduction_total_tree", "performance_ratio" })
@Generated("jsonschema2pojo")
public class PowerStationMonthDataMap {

	@JsonProperty("use_power")
	private Object usePower;
	@JsonProperty("radiation_intensity")
	private Double radiationIntensity;
	@JsonProperty("reduction_total_co2")
	private Double reductionTotalCo2;
	@JsonProperty("reduction_total_coal")
	private Double reductionTotalCoal;
	@JsonProperty("inverter_power")
	private Object inverterPower;
	@JsonProperty("theory_power")
	private Double theoryPower;
	@JsonProperty("ongrid_power")
	private Object ongridPower;
	@JsonProperty("power_profit")
	private Integer powerProfit;
	@JsonProperty("installed_capacity")
	private Double installedCapacity;
	@JsonProperty("perpower_ratio")
	private Double perpowerRatio;
	@JsonProperty("reduction_total_tree")
	private Integer reductionTotalTree;
	@JsonProperty("performance_ratio")
	private Integer performanceRatio;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("use_power")
	public Object getUsePower() {
		return usePower;
	}

	@JsonProperty("use_power")
	public void setUsePower(Object usePower) {
		this.usePower = usePower;
	}

	public PowerStationMonthDataMap withUsePower(Object usePower) {
		this.usePower = usePower;
		return this;
	}

	@JsonProperty("radiation_intensity")
	public Double getRadiationIntensity() {
		return radiationIntensity;
	}

	@JsonProperty("radiation_intensity")
	public void setRadiationIntensity(Double radiationIntensity) {
		this.radiationIntensity = radiationIntensity;
	}

	public PowerStationMonthDataMap withRadiationIntensity(Double radiationIntensity) {
		this.radiationIntensity = radiationIntensity;
		return this;
	}

	@JsonProperty("reduction_total_co2")
	public Double getReductionTotalCo2() {
		return reductionTotalCo2;
	}

	@JsonProperty("reduction_total_co2")
	public void setReductionTotalCo2(Double reductionTotalCo2) {
		this.reductionTotalCo2 = reductionTotalCo2;
	}

	public PowerStationMonthDataMap withReductionTotalCo2(Double reductionTotalCo2) {
		this.reductionTotalCo2 = reductionTotalCo2;
		return this;
	}

	@JsonProperty("reduction_total_coal")
	public Double getReductionTotalCoal() {
		return reductionTotalCoal;
	}

	@JsonProperty("reduction_total_coal")
	public void setReductionTotalCoal(Double reductionTotalCoal) {
		this.reductionTotalCoal = reductionTotalCoal;
	}

	public PowerStationMonthDataMap withReductionTotalCoal(Double reductionTotalCoal) {
		this.reductionTotalCoal = reductionTotalCoal;
		return this;
	}

	@JsonProperty("inverter_power")
	public Object getInverterPower() {
		return inverterPower;
	}

	@JsonProperty("inverter_power")
	public void setInverterPower(Object inverterPower) {
		this.inverterPower = inverterPower;
	}

	public PowerStationMonthDataMap withInverterPower(Object inverterPower) {
		this.inverterPower = inverterPower;
		return this;
	}

	@JsonProperty("theory_power")
	public Double getTheoryPower() {
		return theoryPower;
	}

	@JsonProperty("theory_power")
	public void setTheoryPower(Double theoryPower) {
		this.theoryPower = theoryPower;
	}

	public PowerStationMonthDataMap withTheoryPower(Double theoryPower) {
		this.theoryPower = theoryPower;
		return this;
	}

	@JsonProperty("ongrid_power")
	public Object getOngridPower() {
		return ongridPower;
	}

	@JsonProperty("ongrid_power")
	public void setOngridPower(Object ongridPower) {
		this.ongridPower = ongridPower;
	}

	public PowerStationMonthDataMap withOngridPower(Object ongridPower) {
		this.ongridPower = ongridPower;
		return this;
	}

	@JsonProperty("power_profit")
	public Integer getPowerProfit() {
		return powerProfit;
	}

	@JsonProperty("power_profit")
	public void setPowerProfit(Integer powerProfit) {
		this.powerProfit = powerProfit;
	}

	public PowerStationMonthDataMap withPowerProfit(Integer powerProfit) {
		this.powerProfit = powerProfit;
		return this;
	}

	@JsonProperty("installed_capacity")
	public Double getInstalledCapacity() {
		return installedCapacity;
	}

	@JsonProperty("installed_capacity")
	public void setInstalledCapacity(Double installedCapacity) {
		this.installedCapacity = installedCapacity;
	}

	public PowerStationMonthDataMap withInstalledCapacity(Double installedCapacity) {
		this.installedCapacity = installedCapacity;
		return this;
	}

	@JsonProperty("perpower_ratio")
	public Double getPerpowerRatio() {
		return perpowerRatio;
	}

	@JsonProperty("perpower_ratio")
	public void setPerpowerRatio(Double perpowerRatio) {
		this.perpowerRatio = perpowerRatio;
	}

	public PowerStationMonthDataMap withPerpowerRatio(Double perpowerRatio) {
		this.perpowerRatio = perpowerRatio;
		return this;
	}

	@JsonProperty("reduction_total_tree")
	public Integer getReductionTotalTree() {
		return reductionTotalTree;
	}

	@JsonProperty("reduction_total_tree")
	public void setReductionTotalTree(Integer reductionTotalTree) {
		this.reductionTotalTree = reductionTotalTree;
	}

	public PowerStationMonthDataMap withReductionTotalTree(Integer reductionTotalTree) {
		this.reductionTotalTree = reductionTotalTree;
		return this;
	}

	@JsonProperty("performance_ratio")
	public Integer getPerformanceRatio() {
		return performanceRatio;
	}

	@JsonProperty("performance_ratio")
	public void setPerformanceRatio(Integer performanceRatio) {
		this.performanceRatio = performanceRatio;
	}

	public PowerStationMonthDataMap withPerformanceRatio(Integer performanceRatio) {
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

	public PowerStationMonthDataMap withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
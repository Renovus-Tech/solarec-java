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
@JsonPropertyOrder({ "radiation_intensity", "theory_power", "inverter_power", "ongrid_power", "power_profit" })
@Generated("jsonschema2pojo")
public class PowerStationHourResponseDataMap {

	@JsonProperty("radiation_intensity")
	private Object radiationIntensity;
	@JsonProperty("theory_power")
	private Object theoryPower;
	@JsonProperty("inverter_power")
	private Integer inverterPower;
	@JsonProperty("ongrid_power")
	private Object ongridPower;
	@JsonProperty("power_profit")
	private Integer powerProfit;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("radiation_intensity")
	public Object getRadiationIntensity() {
		return radiationIntensity;
	}

	@JsonProperty("radiation_intensity")
	public void setRadiationIntensity(Object radiationIntensity) {
		this.radiationIntensity = radiationIntensity;
	}

	public PowerStationHourResponseDataMap withRadiationIntensity(Object radiationIntensity) {
		this.radiationIntensity = radiationIntensity;
		return this;
	}

	@JsonProperty("theory_power")
	public Object getTheoryPower() {
		return theoryPower;
	}

	@JsonProperty("theory_power")
	public void setTheoryPower(Object theoryPower) {
		this.theoryPower = theoryPower;
	}

	public PowerStationHourResponseDataMap withTheoryPower(Object theoryPower) {
		this.theoryPower = theoryPower;
		return this;
	}

	@JsonProperty("inverter_power")
	public Integer getInverterPower() {
		return inverterPower;
	}

	@JsonProperty("inverter_power")
	public void setInverterPower(Integer inverterPower) {
		this.inverterPower = inverterPower;
	}

	public PowerStationHourResponseDataMap withInverterPower(Integer inverterPower) {
		this.inverterPower = inverterPower;
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

	public PowerStationHourResponseDataMap withOngridPower(Object ongridPower) {
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

	public PowerStationHourResponseDataMap withPowerProfit(Integer powerProfit) {
		this.powerProfit = powerProfit;
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

	public PowerStationHourResponseDataMap withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
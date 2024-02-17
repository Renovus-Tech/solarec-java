
package tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements;

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
@JsonPropertyOrder({ "time", "batteryCharging", "batteryDischarging", "batteryStateOfCharge", "batteryStateOfHealth",
		"batteryStateOfChargeArray", "batteryStateOfHealthArray", "batteryVoltage", "batteryCurrent",
		"batteryTemperature", "currentBatteryChargingSetVoltage", "consumption", "gridFeedIn", "gridConsumption",
		"pvGeneration", "pvConsumptionRate", "batteryConsumptionRate", "gridConsumptionRate", "totalConsumption",
		"voltagePhaseA2B", "voltagePhaseB2C", "voltagePhaseC2A", "voltagePhaseA", "voltagePhaseB", "voltagePhaseC",
		"currentPhaseA", "currentPhaseB", "currentPhaseC", "activePowerPhaseA", "activePowerPhaseB",
		"activePowerPhaseC", "activePower", "reactivePowerPhaseA", "reactivePowerPhaseB", "reactivePowerPhaseC",
		"reactivePower", "apparentPowerPhaseA", "apparentPowerPhaseB", "apparentPowerPhaseC", "apparentPower",
		"gridFrequency", "displacementPowerFactor", "dcPowerInput", "dcVoltageInput", "dcCurrentInput",
		"isolationResistance", "externalInsolation", "ambientTemperature", "moduleTemperature", "windSpeed" })
@Generated("jsonschema2pojo")
public class Set {

	@JsonProperty("time")
	private String time;
	@JsonProperty("batteryCharging")
	private Double batteryCharging;
	@JsonProperty("batteryDischarging")
	private Double batteryDischarging;
	@JsonProperty("batteryStateOfCharge")
	private Double batteryStateOfCharge;
	@JsonProperty("batteryStateOfHealth")
	private Double batteryStateOfHealth;
	@JsonProperty("batteryStateOfChargeArray")
	private List<BatteryStateOfChargeArray> batteryStateOfChargeArray;
	@JsonProperty("batteryStateOfHealthArray")
	private List<BatteryStateOfHealthArray> batteryStateOfHealthArray;
	@JsonProperty("batteryVoltage")
	private List<BatteryVoltage> batteryVoltage;
	@JsonProperty("batteryCurrent")
	private List<BatteryCurrent> batteryCurrent;
	@JsonProperty("batteryTemperature")
	private List<BatteryTemperature> batteryTemperature;
	@JsonProperty("currentBatteryChargingSetVoltage")
	private List<CurrentBatteryChargingSetVoltage> currentBatteryChargingSetVoltage;
	@JsonProperty("consumption")
	private Double consumption;
	@JsonProperty("gridFeedIn")
	private Double gridFeedIn;
	@JsonProperty("gridConsumption")
	private Double gridConsumption;
	@JsonProperty("pvGeneration")
	private Double pvGeneration;
	@JsonProperty("pvConsumptionRate")
	private Double pvConsumptionRate;
	@JsonProperty("batteryConsumptionRate")
	private Double batteryConsumptionRate;
	@JsonProperty("gridConsumptionRate")
	private Double gridConsumptionRate;
	@JsonProperty("totalConsumption")
	private Double totalConsumption;
	@JsonProperty("voltagePhaseA2B")
	private Double voltagePhaseA2B;
	@JsonProperty("voltagePhaseB2C")
	private Double voltagePhaseB2C;
	@JsonProperty("voltagePhaseC2A")
	private Double voltagePhaseC2A;
	@JsonProperty("voltagePhaseA")
	private Double voltagePhaseA;
	@JsonProperty("voltagePhaseB")
	private Double voltagePhaseB;
	@JsonProperty("voltagePhaseC")
	private Double voltagePhaseC;
	@JsonProperty("currentPhaseA")
	private Double currentPhaseA;
	@JsonProperty("currentPhaseB")
	private Double currentPhaseB;
	@JsonProperty("currentPhaseC")
	private Double currentPhaseC;
	@JsonProperty("activePowerPhaseA")
	private Double activePowerPhaseA;
	@JsonProperty("activePowerPhaseB")
	private Double activePowerPhaseB;
	@JsonProperty("activePowerPhaseC")
	private Double activePowerPhaseC;
	@JsonProperty("activePower")
	private Double activePower;
	@JsonProperty("reactivePowerPhaseA")
	private Integer reactivePowerPhaseA;
	@JsonProperty("reactivePowerPhaseB")
	private Double reactivePowerPhaseB;
	@JsonProperty("reactivePowerPhaseC")
	private Double reactivePowerPhaseC;
	@JsonProperty("reactivePower")
	private Integer reactivePower;
	@JsonProperty("apparentPowerPhaseA")
	private Double apparentPowerPhaseA;
	@JsonProperty("apparentPowerPhaseB")
	private Double apparentPowerPhaseB;
	@JsonProperty("apparentPowerPhaseC")
	private Double apparentPowerPhaseC;
	@JsonProperty("apparentPower")
	private Integer apparentPower;
	@JsonProperty("gridFrequency")
	private Double gridFrequency;
	@JsonProperty("displacementPowerFactor")
	private Double displacementPowerFactor;
	@JsonProperty("dcPowerInput")
	private List<DcPowerInput> dcPowerInput;
	@JsonProperty("dcVoltageInput")
	private List<DcVoltageInput> dcVoltageInput;
	@JsonProperty("dcCurrentInput")
	private List<DcCurrentInput> dcCurrentInput;
	@JsonProperty("isolationResistance")
	private Integer isolationResistance;
	@JsonProperty("externalInsolation")
	private Double externalInsolation;
	@JsonProperty("ambientTemperature")
	private Double ambientTemperature;
	@JsonProperty("moduleTemperature")
	private Double moduleTemperature;
	@JsonProperty("windSpeed")
	private Double windSpeed;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("time")
	public String getTime() {
		return time;
	}

	@JsonProperty("time")
	public void setTime(String time) {
		this.time = time;
	}

	public Set withTime(String time) {
		this.time = time;
		return this;
	}

	@JsonProperty("batteryCharging")
	public Double getBatteryCharging() {
		return batteryCharging;
	}

	@JsonProperty("batteryCharging")
	public void setBatteryCharging(Double batteryCharging) {
		this.batteryCharging = batteryCharging;
	}

	public Set withBatteryCharging(Double batteryCharging) {
		this.batteryCharging = batteryCharging;
		return this;
	}

	@JsonProperty("batteryDischarging")
	public Double getBatteryDischarging() {
		return batteryDischarging;
	}

	@JsonProperty("batteryDischarging")
	public void setBatteryDischarging(Double batteryDischarging) {
		this.batteryDischarging = batteryDischarging;
	}

	public Set withBatteryDischarging(Double batteryDischarging) {
		this.batteryDischarging = batteryDischarging;
		return this;
	}

	@JsonProperty("batteryStateOfCharge")
	public Double getBatteryStateOfCharge() {
		return batteryStateOfCharge;
	}

	@JsonProperty("batteryStateOfCharge")
	public void setBatteryStateOfCharge(Double batteryStateOfCharge) {
		this.batteryStateOfCharge = batteryStateOfCharge;
	}

	public Set withBatteryStateOfCharge(Double batteryStateOfCharge) {
		this.batteryStateOfCharge = batteryStateOfCharge;
		return this;
	}

	@JsonProperty("batteryStateOfHealth")
	public Double getBatteryStateOfHealth() {
		return batteryStateOfHealth;
	}

	@JsonProperty("batteryStateOfHealth")
	public void setBatteryStateOfHealth(Double batteryStateOfHealth) {
		this.batteryStateOfHealth = batteryStateOfHealth;
	}

	public Set withBatteryStateOfHealth(Double batteryStateOfHealth) {
		this.batteryStateOfHealth = batteryStateOfHealth;
		return this;
	}

	@JsonProperty("batteryStateOfChargeArray")
	public List<BatteryStateOfChargeArray> getBatteryStateOfChargeArray() {
		return batteryStateOfChargeArray;
	}

	@JsonProperty("batteryStateOfChargeArray")
	public void setBatteryStateOfChargeArray(List<BatteryStateOfChargeArray> batteryStateOfChargeArray) {
		this.batteryStateOfChargeArray = batteryStateOfChargeArray;
	}

	public Set withBatteryStateOfChargeArray(List<BatteryStateOfChargeArray> batteryStateOfChargeArray) {
		this.batteryStateOfChargeArray = batteryStateOfChargeArray;
		return this;
	}

	@JsonProperty("batteryStateOfHealthArray")
	public List<BatteryStateOfHealthArray> getBatteryStateOfHealthArray() {
		return batteryStateOfHealthArray;
	}

	@JsonProperty("batteryStateOfHealthArray")
	public void setBatteryStateOfHealthArray(List<BatteryStateOfHealthArray> batteryStateOfHealthArray) {
		this.batteryStateOfHealthArray = batteryStateOfHealthArray;
	}

	public Set withBatteryStateOfHealthArray(List<BatteryStateOfHealthArray> batteryStateOfHealthArray) {
		this.batteryStateOfHealthArray = batteryStateOfHealthArray;
		return this;
	}

	@JsonProperty("batteryVoltage")
	public List<BatteryVoltage> getBatteryVoltage() {
		return batteryVoltage;
	}

	@JsonProperty("batteryVoltage")
	public void setBatteryVoltage(List<BatteryVoltage> batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public Set withBatteryVoltage(List<BatteryVoltage> batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
		return this;
	}

	@JsonProperty("batteryCurrent")
	public List<BatteryCurrent> getBatteryCurrent() {
		return batteryCurrent;
	}

	@JsonProperty("batteryCurrent")
	public void setBatteryCurrent(List<BatteryCurrent> batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
	}

	public Set withBatteryCurrent(List<BatteryCurrent> batteryCurrent) {
		this.batteryCurrent = batteryCurrent;
		return this;
	}

	@JsonProperty("batteryTemperature")
	public List<BatteryTemperature> getBatteryTemperature() {
		return batteryTemperature;
	}

	@JsonProperty("batteryTemperature")
	public void setBatteryTemperature(List<BatteryTemperature> batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
	}

	public Set withBatteryTemperature(List<BatteryTemperature> batteryTemperature) {
		this.batteryTemperature = batteryTemperature;
		return this;
	}

	@JsonProperty("currentBatteryChargingSetVoltage")
	public List<CurrentBatteryChargingSetVoltage> getCurrentBatteryChargingSetVoltage() {
		return currentBatteryChargingSetVoltage;
	}

	@JsonProperty("currentBatteryChargingSetVoltage")
	public void setCurrentBatteryChargingSetVoltage(
			List<CurrentBatteryChargingSetVoltage> currentBatteryChargingSetVoltage) {
		this.currentBatteryChargingSetVoltage = currentBatteryChargingSetVoltage;
	}

	public Set withCurrentBatteryChargingSetVoltage(
			List<CurrentBatteryChargingSetVoltage> currentBatteryChargingSetVoltage) {
		this.currentBatteryChargingSetVoltage = currentBatteryChargingSetVoltage;
		return this;
	}

	@JsonProperty("consumption")
	public Double getConsumption() {
		return consumption;
	}

	@JsonProperty("consumption")
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public Set withConsumption(Double consumption) {
		this.consumption = consumption;
		return this;
	}

	@JsonProperty("gridFeedIn")
	public Double getGridFeedIn() {
		return gridFeedIn;
	}

	@JsonProperty("gridFeedIn")
	public void setGridFeedIn(Double gridFeedIn) {
		this.gridFeedIn = gridFeedIn;
	}

	public Set withGridFeedIn(Double gridFeedIn) {
		this.gridFeedIn = gridFeedIn;
		return this;
	}

	@JsonProperty("gridConsumption")
	public Double getGridConsumption() {
		return gridConsumption;
	}

	@JsonProperty("gridConsumption")
	public void setGridConsumption(Double gridConsumption) {
		this.gridConsumption = gridConsumption;
	}

	public Set withGridConsumption(Double gridConsumption) {
		this.gridConsumption = gridConsumption;
		return this;
	}

	@JsonProperty("pvGeneration")
	public Double getPvGeneration() {
		return pvGeneration;
	}

	@JsonProperty("pvGeneration")
	public void setPvGeneration(Double pvGeneration) {
		this.pvGeneration = pvGeneration;
	}

	public Set withPvGeneration(Double pvGeneration) {
		this.pvGeneration = pvGeneration;
		return this;
	}

	@JsonProperty("pvConsumptionRate")
	public Double getPvConsumptionRate() {
		return pvConsumptionRate;
	}

	@JsonProperty("pvConsumptionRate")
	public void setPvConsumptionRate(Double pvConsumptionRate) {
		this.pvConsumptionRate = pvConsumptionRate;
	}

	public Set withPvConsumptionRate(Double pvConsumptionRate) {
		this.pvConsumptionRate = pvConsumptionRate;
		return this;
	}

	@JsonProperty("batteryConsumptionRate")
	public Double getBatteryConsumptionRate() {
		return batteryConsumptionRate;
	}

	@JsonProperty("batteryConsumptionRate")
	public void setBatteryConsumptionRate(Double batteryConsumptionRate) {
		this.batteryConsumptionRate = batteryConsumptionRate;
	}

	public Set withBatteryConsumptionRate(Double batteryConsumptionRate) {
		this.batteryConsumptionRate = batteryConsumptionRate;
		return this;
	}

	@JsonProperty("gridConsumptionRate")
	public Double getGridConsumptionRate() {
		return gridConsumptionRate;
	}

	@JsonProperty("gridConsumptionRate")
	public void setGridConsumptionRate(Double gridConsumptionRate) {
		this.gridConsumptionRate = gridConsumptionRate;
	}

	public Set withGridConsumptionRate(Double gridConsumptionRate) {
		this.gridConsumptionRate = gridConsumptionRate;
		return this;
	}

	@JsonProperty("totalConsumption")
	public Double getTotalConsumption() {
		return totalConsumption;
	}

	@JsonProperty("totalConsumption")
	public void setTotalConsumption(Double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	public Set withTotalConsumption(Double totalConsumption) {
		this.totalConsumption = totalConsumption;
		return this;
	}

	@JsonProperty("voltagePhaseA2B")
	public Double getVoltagePhaseA2B() {
		return voltagePhaseA2B;
	}

	@JsonProperty("voltagePhaseA2B")
	public void setVoltagePhaseA2B(Double voltagePhaseA2B) {
		this.voltagePhaseA2B = voltagePhaseA2B;
	}

	public Set withVoltagePhaseA2B(Double voltagePhaseA2B) {
		this.voltagePhaseA2B = voltagePhaseA2B;
		return this;
	}

	@JsonProperty("voltagePhaseB2C")
	public Double getVoltagePhaseB2C() {
		return voltagePhaseB2C;
	}

	@JsonProperty("voltagePhaseB2C")
	public void setVoltagePhaseB2C(Double voltagePhaseB2C) {
		this.voltagePhaseB2C = voltagePhaseB2C;
	}

	public Set withVoltagePhaseB2C(Double voltagePhaseB2C) {
		this.voltagePhaseB2C = voltagePhaseB2C;
		return this;
	}

	@JsonProperty("voltagePhaseC2A")
	public Double getVoltagePhaseC2A() {
		return voltagePhaseC2A;
	}

	@JsonProperty("voltagePhaseC2A")
	public void setVoltagePhaseC2A(Double voltagePhaseC2A) {
		this.voltagePhaseC2A = voltagePhaseC2A;
	}

	public Set withVoltagePhaseC2A(Double voltagePhaseC2A) {
		this.voltagePhaseC2A = voltagePhaseC2A;
		return this;
	}

	@JsonProperty("voltagePhaseA")
	public Double getVoltagePhaseA() {
		return voltagePhaseA;
	}

	@JsonProperty("voltagePhaseA")
	public void setVoltagePhaseA(Double voltagePhaseA) {
		this.voltagePhaseA = voltagePhaseA;
	}

	public Set withVoltagePhaseA(Double voltagePhaseA) {
		this.voltagePhaseA = voltagePhaseA;
		return this;
	}

	@JsonProperty("voltagePhaseB")
	public Double getVoltagePhaseB() {
		return voltagePhaseB;
	}

	@JsonProperty("voltagePhaseB")
	public void setVoltagePhaseB(Double voltagePhaseB) {
		this.voltagePhaseB = voltagePhaseB;
	}

	public Set withVoltagePhaseB(Double voltagePhaseB) {
		this.voltagePhaseB = voltagePhaseB;
		return this;
	}

	@JsonProperty("voltagePhaseC")
	public Double getVoltagePhaseC() {
		return voltagePhaseC;
	}

	@JsonProperty("voltagePhaseC")
	public void setVoltagePhaseC(Double voltagePhaseC) {
		this.voltagePhaseC = voltagePhaseC;
	}

	public Set withVoltagePhaseC(Double voltagePhaseC) {
		this.voltagePhaseC = voltagePhaseC;
		return this;
	}

	@JsonProperty("currentPhaseA")
	public Double getCurrentPhaseA() {
		return currentPhaseA;
	}

	@JsonProperty("currentPhaseA")
	public void setCurrentPhaseA(Double currentPhaseA) {
		this.currentPhaseA = currentPhaseA;
	}

	public Set withCurrentPhaseA(Double currentPhaseA) {
		this.currentPhaseA = currentPhaseA;
		return this;
	}

	@JsonProperty("currentPhaseB")
	public Double getCurrentPhaseB() {
		return currentPhaseB;
	}

	@JsonProperty("currentPhaseB")
	public void setCurrentPhaseB(Double currentPhaseB) {
		this.currentPhaseB = currentPhaseB;
	}

	public Set withCurrentPhaseB(Double currentPhaseB) {
		this.currentPhaseB = currentPhaseB;
		return this;
	}

	@JsonProperty("currentPhaseC")
	public Double getCurrentPhaseC() {
		return currentPhaseC;
	}

	@JsonProperty("currentPhaseC")
	public void setCurrentPhaseC(Double currentPhaseC) {
		this.currentPhaseC = currentPhaseC;
	}

	public Set withCurrentPhaseC(Double currentPhaseC) {
		this.currentPhaseC = currentPhaseC;
		return this;
	}

	@JsonProperty("activePowerPhaseA")
	public Double getActivePowerPhaseA() {
		return activePowerPhaseA;
	}

	@JsonProperty("activePowerPhaseA")
	public void setActivePowerPhaseA(Double activePowerPhaseA) {
		this.activePowerPhaseA = activePowerPhaseA;
	}

	public Set withActivePowerPhaseA(Double activePowerPhaseA) {
		this.activePowerPhaseA = activePowerPhaseA;
		return this;
	}

	@JsonProperty("activePowerPhaseB")
	public Double getActivePowerPhaseB() {
		return activePowerPhaseB;
	}

	@JsonProperty("activePowerPhaseB")
	public void setActivePowerPhaseB(Double activePowerPhaseB) {
		this.activePowerPhaseB = activePowerPhaseB;
	}

	public Set withActivePowerPhaseB(Double activePowerPhaseB) {
		this.activePowerPhaseB = activePowerPhaseB;
		return this;
	}

	@JsonProperty("activePowerPhaseC")
	public Double getActivePowerPhaseC() {
		return activePowerPhaseC;
	}

	@JsonProperty("activePowerPhaseC")
	public void setActivePowerPhaseC(Double activePowerPhaseC) {
		this.activePowerPhaseC = activePowerPhaseC;
	}

	public Set withActivePowerPhaseC(Double activePowerPhaseC) {
		this.activePowerPhaseC = activePowerPhaseC;
		return this;
	}

	@JsonProperty("activePower")
	public Double getActivePower() {
		return activePower;
	}

	@JsonProperty("activePower")
	public void setActivePower(Double activePower) {
		this.activePower = activePower;
	}

	public Set withActivePower(Double activePower) {
		this.activePower = activePower;
		return this;
	}

	@JsonProperty("reactivePowerPhaseA")
	public Integer getReactivePowerPhaseA() {
		return reactivePowerPhaseA;
	}

	@JsonProperty("reactivePowerPhaseA")
	public void setReactivePowerPhaseA(Integer reactivePowerPhaseA) {
		this.reactivePowerPhaseA = reactivePowerPhaseA;
	}

	public Set withReactivePowerPhaseA(Integer reactivePowerPhaseA) {
		this.reactivePowerPhaseA = reactivePowerPhaseA;
		return this;
	}

	@JsonProperty("reactivePowerPhaseB")
	public Double getReactivePowerPhaseB() {
		return reactivePowerPhaseB;
	}

	@JsonProperty("reactivePowerPhaseB")
	public void setReactivePowerPhaseB(Double reactivePowerPhaseB) {
		this.reactivePowerPhaseB = reactivePowerPhaseB;
	}

	public Set withReactivePowerPhaseB(Double reactivePowerPhaseB) {
		this.reactivePowerPhaseB = reactivePowerPhaseB;
		return this;
	}

	@JsonProperty("reactivePowerPhaseC")
	public Double getReactivePowerPhaseC() {
		return reactivePowerPhaseC;
	}

	@JsonProperty("reactivePowerPhaseC")
	public void setReactivePowerPhaseC(Double reactivePowerPhaseC) {
		this.reactivePowerPhaseC = reactivePowerPhaseC;
	}

	public Set withReactivePowerPhaseC(Double reactivePowerPhaseC) {
		this.reactivePowerPhaseC = reactivePowerPhaseC;
		return this;
	}

	@JsonProperty("reactivePower")
	public Integer getReactivePower() {
		return reactivePower;
	}

	@JsonProperty("reactivePower")
	public void setReactivePower(Integer reactivePower) {
		this.reactivePower = reactivePower;
	}

	public Set withReactivePower(Integer reactivePower) {
		this.reactivePower = reactivePower;
		return this;
	}

	@JsonProperty("apparentPowerPhaseA")
	public Double getApparentPowerPhaseA() {
		return apparentPowerPhaseA;
	}

	@JsonProperty("apparentPowerPhaseA")
	public void setApparentPowerPhaseA(Double apparentPowerPhaseA) {
		this.apparentPowerPhaseA = apparentPowerPhaseA;
	}

	public Set withApparentPowerPhaseA(Double apparentPowerPhaseA) {
		this.apparentPowerPhaseA = apparentPowerPhaseA;
		return this;
	}

	@JsonProperty("apparentPowerPhaseB")
	public Double getApparentPowerPhaseB() {
		return apparentPowerPhaseB;
	}

	@JsonProperty("apparentPowerPhaseB")
	public void setApparentPowerPhaseB(Double apparentPowerPhaseB) {
		this.apparentPowerPhaseB = apparentPowerPhaseB;
	}

	public Set withApparentPowerPhaseB(Double apparentPowerPhaseB) {
		this.apparentPowerPhaseB = apparentPowerPhaseB;
		return this;
	}

	@JsonProperty("apparentPowerPhaseC")
	public Double getApparentPowerPhaseC() {
		return apparentPowerPhaseC;
	}

	@JsonProperty("apparentPowerPhaseC")
	public void setApparentPowerPhaseC(Double apparentPowerPhaseC) {
		this.apparentPowerPhaseC = apparentPowerPhaseC;
	}

	public Set withApparentPowerPhaseC(Double apparentPowerPhaseC) {
		this.apparentPowerPhaseC = apparentPowerPhaseC;
		return this;
	}

	@JsonProperty("apparentPower")
	public Integer getApparentPower() {
		return apparentPower;
	}

	@JsonProperty("apparentPower")
	public void setApparentPower(Integer apparentPower) {
		this.apparentPower = apparentPower;
	}

	public Set withApparentPower(Integer apparentPower) {
		this.apparentPower = apparentPower;
		return this;
	}

	@JsonProperty("gridFrequency")
	public Double getGridFrequency() {
		return gridFrequency;
	}

	@JsonProperty("gridFrequency")
	public void setGridFrequency(Double gridFrequency) {
		this.gridFrequency = gridFrequency;
	}

	public Set withGridFrequency(Double gridFrequency) {
		this.gridFrequency = gridFrequency;
		return this;
	}

	@JsonProperty("displacementPowerFactor")
	public Double getDisplacementPowerFactor() {
		return displacementPowerFactor;
	}

	@JsonProperty("displacementPowerFactor")
	public void setDisplacementPowerFactor(Double displacementPowerFactor) {
		this.displacementPowerFactor = displacementPowerFactor;
	}

	public Set withDisplacementPowerFactor(Double displacementPowerFactor) {
		this.displacementPowerFactor = displacementPowerFactor;
		return this;
	}

	@JsonProperty("dcPowerInput")
	public List<DcPowerInput> getDcPowerInput() {
		return dcPowerInput;
	}

	@JsonProperty("dcPowerInput")
	public void setDcPowerInput(List<DcPowerInput> dcPowerInput) {
		this.dcPowerInput = dcPowerInput;
	}

	public Set withDcPowerInput(List<DcPowerInput> dcPowerInput) {
		this.dcPowerInput = dcPowerInput;
		return this;
	}

	@JsonProperty("dcVoltageInput")
	public List<DcVoltageInput> getDcVoltageInput() {
		return dcVoltageInput;
	}

	@JsonProperty("dcVoltageInput")
	public void setDcVoltageInput(List<DcVoltageInput> dcVoltageInput) {
		this.dcVoltageInput = dcVoltageInput;
	}

	public Set withDcVoltageInput(List<DcVoltageInput> dcVoltageInput) {
		this.dcVoltageInput = dcVoltageInput;
		return this;
	}

	@JsonProperty("dcCurrentInput")
	public List<DcCurrentInput> getDcCurrentInput() {
		return dcCurrentInput;
	}

	@JsonProperty("dcCurrentInput")
	public void setDcCurrentInput(List<DcCurrentInput> dcCurrentInput) {
		this.dcCurrentInput = dcCurrentInput;
	}

	public Set withDcCurrentInput(List<DcCurrentInput> dcCurrentInput) {
		this.dcCurrentInput = dcCurrentInput;
		return this;
	}

	@JsonProperty("isolationResistance")
	public Integer getIsolationResistance() {
		return isolationResistance;
	}

	@JsonProperty("isolationResistance")
	public void setIsolationResistance(Integer isolationResistance) {
		this.isolationResistance = isolationResistance;
	}

	public Set withIsolationResistance(Integer isolationResistance) {
		this.isolationResistance = isolationResistance;
		return this;
	}

	@JsonProperty("externalInsolation")
	public Double getExternalInsolation() {
		return externalInsolation;
	}

	@JsonProperty("externalInsolation")
	public void setExternalInsolation(Double externalInsolation) {
		this.externalInsolation = externalInsolation;
	}

	public Set withExternalInsolation(Double externalInsolation) {
		this.externalInsolation = externalInsolation;
		return this;
	}

	@JsonProperty("ambientTemperature")
	public Double getAmbientTemperature() {
		return ambientTemperature;
	}

	@JsonProperty("ambientTemperature")
	public void setAmbientTemperature(Double ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
	}

	public Set withAmbientTemperature(Double ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
		return this;
	}

	@JsonProperty("moduleTemperature")
	public Double getModuleTemperature() {
		return moduleTemperature;
	}

	@JsonProperty("moduleTemperature")
	public void setModuleTemperature(Double moduleTemperature) {
		this.moduleTemperature = moduleTemperature;
	}

	public Set withModuleTemperature(Double moduleTemperature) {
		this.moduleTemperature = moduleTemperature;
		return this;
	}

	@JsonProperty("windSpeed")
	public Double getWindSpeed() {
		return windSpeed;
	}

	@JsonProperty("windSpeed")
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Set withWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
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

	public Set withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

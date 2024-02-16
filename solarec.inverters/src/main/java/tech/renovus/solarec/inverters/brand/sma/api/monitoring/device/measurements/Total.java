
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
@JsonPropertyOrder({
    "time",
    "batteryCharging",
    "batteryDischarging",
    "batteryStateOfCharge",
    "batteryStateOfHealth",
    "batteryStateOfChargeArray",
    "batteryStateOfHealthArray",
    "batteryVoltage",
    "batteryCurrent",
    "batteryTemperature",
    "currentBatteryChargingSetVoltage"
})
@Generated("jsonschema2pojo")
public class Total {

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
    private List<BatteryStateOfChargeArray__1> batteryStateOfChargeArray;
    @JsonProperty("batteryStateOfHealthArray")
    private List<BatteryStateOfHealthArray__1> batteryStateOfHealthArray;
    @JsonProperty("batteryVoltage")
    private List<BatteryVoltage__1> batteryVoltage;
    @JsonProperty("batteryCurrent")
    private List<BatteryCurrent__1> batteryCurrent;
    @JsonProperty("batteryTemperature")
    private List<BatteryTemperature__1> batteryTemperature;
    @JsonProperty("currentBatteryChargingSetVoltage")
    private List<CurrentBatteryChargingSetVoltage__1> currentBatteryChargingSetVoltage;
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

    public Total withTime(String time) {
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

    public Total withBatteryCharging(Double batteryCharging) {
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

    public Total withBatteryDischarging(Double batteryDischarging) {
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

    public Total withBatteryStateOfCharge(Double batteryStateOfCharge) {
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

    public Total withBatteryStateOfHealth(Double batteryStateOfHealth) {
        this.batteryStateOfHealth = batteryStateOfHealth;
        return this;
    }

    @JsonProperty("batteryStateOfChargeArray")
    public List<BatteryStateOfChargeArray__1> getBatteryStateOfChargeArray() {
        return batteryStateOfChargeArray;
    }

    @JsonProperty("batteryStateOfChargeArray")
    public void setBatteryStateOfChargeArray(List<BatteryStateOfChargeArray__1> batteryStateOfChargeArray) {
        this.batteryStateOfChargeArray = batteryStateOfChargeArray;
    }

    public Total withBatteryStateOfChargeArray(List<BatteryStateOfChargeArray__1> batteryStateOfChargeArray) {
        this.batteryStateOfChargeArray = batteryStateOfChargeArray;
        return this;
    }

    @JsonProperty("batteryStateOfHealthArray")
    public List<BatteryStateOfHealthArray__1> getBatteryStateOfHealthArray() {
        return batteryStateOfHealthArray;
    }

    @JsonProperty("batteryStateOfHealthArray")
    public void setBatteryStateOfHealthArray(List<BatteryStateOfHealthArray__1> batteryStateOfHealthArray) {
        this.batteryStateOfHealthArray = batteryStateOfHealthArray;
    }

    public Total withBatteryStateOfHealthArray(List<BatteryStateOfHealthArray__1> batteryStateOfHealthArray) {
        this.batteryStateOfHealthArray = batteryStateOfHealthArray;
        return this;
    }

    @JsonProperty("batteryVoltage")
    public List<BatteryVoltage__1> getBatteryVoltage() {
        return batteryVoltage;
    }

    @JsonProperty("batteryVoltage")
    public void setBatteryVoltage(List<BatteryVoltage__1> batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Total withBatteryVoltage(List<BatteryVoltage__1> batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
        return this;
    }

    @JsonProperty("batteryCurrent")
    public List<BatteryCurrent__1> getBatteryCurrent() {
        return batteryCurrent;
    }

    @JsonProperty("batteryCurrent")
    public void setBatteryCurrent(List<BatteryCurrent__1> batteryCurrent) {
        this.batteryCurrent = batteryCurrent;
    }

    public Total withBatteryCurrent(List<BatteryCurrent__1> batteryCurrent) {
        this.batteryCurrent = batteryCurrent;
        return this;
    }

    @JsonProperty("batteryTemperature")
    public List<BatteryTemperature__1> getBatteryTemperature() {
        return batteryTemperature;
    }

    @JsonProperty("batteryTemperature")
    public void setBatteryTemperature(List<BatteryTemperature__1> batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }

    public Total withBatteryTemperature(List<BatteryTemperature__1> batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
        return this;
    }

    @JsonProperty("currentBatteryChargingSetVoltage")
    public List<CurrentBatteryChargingSetVoltage__1> getCurrentBatteryChargingSetVoltage() {
        return currentBatteryChargingSetVoltage;
    }

    @JsonProperty("currentBatteryChargingSetVoltage")
    public void setCurrentBatteryChargingSetVoltage(List<CurrentBatteryChargingSetVoltage__1> currentBatteryChargingSetVoltage) {
        this.currentBatteryChargingSetVoltage = currentBatteryChargingSetVoltage;
    }

    public Total withCurrentBatteryChargingSetVoltage(List<CurrentBatteryChargingSetVoltage__1> currentBatteryChargingSetVoltage) {
        this.currentBatteryChargingSetVoltage = currentBatteryChargingSetVoltage;
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

    public Total withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

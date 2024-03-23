
package tech.renovus.solarec.inverters.brand.sofar.api;

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
@JsonPropertyOrder({
    "generationPower",
    "usePower",
    "gridPower",
    "purchasePower",
    "wirePower",
    "chargePower",
    "dischargePower",
    "batteryPower",
    "batterySoc",
    "irradiateIntensity",
    "generationValue",
    "generationRatio",
    "gridRatio",
    "chargeRatio",
    "useValue",
    "useRatio",
    "buyRatio",
    "useDischargeRatio",
    "gridValue",
    "buyValue",
    "chargeValue",
    "dischargeValue",
    "fullPowerHours",
    "irradiate",
    "theoreticalGeneration",
    "pr",
    "cpr",
    "dateTime",
    "year",
    "month",
    "day"
})
@Generated("jsonschema2pojo")
public class StationDataItem {

    @JsonProperty("generationPower")
    private Object generationPower;
    @JsonProperty("usePower")
    private Object usePower;
    @JsonProperty("gridPower")
    private Object gridPower;
    @JsonProperty("purchasePower")
    private Object purchasePower;
    @JsonProperty("wirePower")
    private Object wirePower;
    @JsonProperty("chargePower")
    private Object chargePower;
    @JsonProperty("dischargePower")
    private Object dischargePower;
    @JsonProperty("batteryPower")
    private Object batteryPower;
    @JsonProperty("batterySoc")
    private Object batterySoc;
    @JsonProperty("irradiateIntensity")
    private Object irradiateIntensity;
    @JsonProperty("generationValue")
    private Double generationValue;
    @JsonProperty("generationRatio")
    private Double generationRatio;
    @JsonProperty("gridRatio")
    private Double gridRatio;
    @JsonProperty("chargeRatio")
    private Double chargeRatio;
    @JsonProperty("useValue")
    private Double useValue;
    @JsonProperty("useRatio")
    private Double useRatio;
    @JsonProperty("buyRatio")
    private Double buyRatio;
    @JsonProperty("useDischargeRatio")
    private Double useDischargeRatio;
    @JsonProperty("gridValue")
    private Double gridValue;
    @JsonProperty("buyValue")
    private Double buyValue;
    @JsonProperty("chargeValue")
    private Double chargeValue;
    @JsonProperty("dischargeValue")
    private Double dischargeValue;
    @JsonProperty("fullPowerHours")
    private Double fullPowerHours;
    @JsonProperty("irradiate")
    private Double irradiate;
    @JsonProperty("theoreticalGeneration")
    private Double theoreticalGeneration;
    @JsonProperty("pr")
    private Double pr;
    @JsonProperty("cpr")
    private Object cpr;
    @JsonProperty("dateTime")
    private String dateTime;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("month")
    private Integer month;
    @JsonProperty("day")
    private Integer day;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("generationPower")
    public Object getGenerationPower() {
        return generationPower;
    }

    @JsonProperty("generationPower")
    public void setGenerationPower(Object generationPower) {
        this.generationPower = generationPower;
    }

    public StationDataItem withGenerationPower(Object generationPower) {
        this.generationPower = generationPower;
        return this;
    }

    @JsonProperty("usePower")
    public Object getUsePower() {
        return usePower;
    }

    @JsonProperty("usePower")
    public void setUsePower(Object usePower) {
        this.usePower = usePower;
    }

    public StationDataItem withUsePower(Object usePower) {
        this.usePower = usePower;
        return this;
    }

    @JsonProperty("gridPower")
    public Object getGridPower() {
        return gridPower;
    }

    @JsonProperty("gridPower")
    public void setGridPower(Object gridPower) {
        this.gridPower = gridPower;
    }

    public StationDataItem withGridPower(Object gridPower) {
        this.gridPower = gridPower;
        return this;
    }

    @JsonProperty("purchasePower")
    public Object getPurchasePower() {
        return purchasePower;
    }

    @JsonProperty("purchasePower")
    public void setPurchasePower(Object purchasePower) {
        this.purchasePower = purchasePower;
    }

    public StationDataItem withPurchasePower(Object purchasePower) {
        this.purchasePower = purchasePower;
        return this;
    }

    @JsonProperty("wirePower")
    public Object getWirePower() {
        return wirePower;
    }

    @JsonProperty("wirePower")
    public void setWirePower(Object wirePower) {
        this.wirePower = wirePower;
    }

    public StationDataItem withWirePower(Object wirePower) {
        this.wirePower = wirePower;
        return this;
    }

    @JsonProperty("chargePower")
    public Object getChargePower() {
        return chargePower;
    }

    @JsonProperty("chargePower")
    public void setChargePower(Object chargePower) {
        this.chargePower = chargePower;
    }

    public StationDataItem withChargePower(Object chargePower) {
        this.chargePower = chargePower;
        return this;
    }

    @JsonProperty("dischargePower")
    public Object getDischargePower() {
        return dischargePower;
    }

    @JsonProperty("dischargePower")
    public void setDischargePower(Object dischargePower) {
        this.dischargePower = dischargePower;
    }

    public StationDataItem withDischargePower(Object dischargePower) {
        this.dischargePower = dischargePower;
        return this;
    }

    @JsonProperty("batteryPower")
    public Object getBatteryPower() {
        return batteryPower;
    }

    @JsonProperty("batteryPower")
    public void setBatteryPower(Object batteryPower) {
        this.batteryPower = batteryPower;
    }

    public StationDataItem withBatteryPower(Object batteryPower) {
        this.batteryPower = batteryPower;
        return this;
    }

    @JsonProperty("batterySoc")
    public Object getBatterySoc() {
        return batterySoc;
    }

    @JsonProperty("batterySoc")
    public void setBatterySoc(Object batterySoc) {
        this.batterySoc = batterySoc;
    }

    public StationDataItem withBatterySoc(Object batterySoc) {
        this.batterySoc = batterySoc;
        return this;
    }

    @JsonProperty("irradiateIntensity")
    public Object getIrradiateIntensity() {
        return irradiateIntensity;
    }

    @JsonProperty("irradiateIntensity")
    public void setIrradiateIntensity(Object irradiateIntensity) {
        this.irradiateIntensity = irradiateIntensity;
    }

    public StationDataItem withIrradiateIntensity(Object irradiateIntensity) {
        this.irradiateIntensity = irradiateIntensity;
        return this;
    }

    @JsonProperty("generationValue")
    public Double getGenerationValue() {
        return generationValue;
    }

    @JsonProperty("generationValue")
    public void setGenerationValue(Double generationValue) {
        this.generationValue = generationValue;
    }

    public StationDataItem withGenerationValue(Double generationValue) {
        this.generationValue = generationValue;
        return this;
    }

    @JsonProperty("generationRatio")
    public Double getGenerationRatio() {
        return generationRatio;
    }

    @JsonProperty("generationRatio")
    public void setGenerationRatio(Double generationRatio) {
        this.generationRatio = generationRatio;
    }

    public StationDataItem withGenerationRatio(Double generationRatio) {
        this.generationRatio = generationRatio;
        return this;
    }

    @JsonProperty("gridRatio")
    public Double getGridRatio() {
        return gridRatio;
    }

    @JsonProperty("gridRatio")
    public void setGridRatio(Double gridRatio) {
        this.gridRatio = gridRatio;
    }

    public StationDataItem withGridRatio(Double gridRatio) {
        this.gridRatio = gridRatio;
        return this;
    }

    @JsonProperty("chargeRatio")
    public Double getChargeRatio() {
        return chargeRatio;
    }

    @JsonProperty("chargeRatio")
    public void setChargeRatio(Double chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    public StationDataItem withChargeRatio(Double chargeRatio) {
        this.chargeRatio = chargeRatio;
        return this;
    }

    @JsonProperty("useValue")
    public Double getUseValue() {
        return useValue;
    }

    @JsonProperty("useValue")
    public void setUseValue(Double useValue) {
        this.useValue = useValue;
    }

    public StationDataItem withUseValue(Double useValue) {
        this.useValue = useValue;
        return this;
    }

    @JsonProperty("useRatio")
    public Double getUseRatio() {
        return useRatio;
    }

    @JsonProperty("useRatio")
    public void setUseRatio(Double useRatio) {
        this.useRatio = useRatio;
    }

    public StationDataItem withUseRatio(Double useRatio) {
        this.useRatio = useRatio;
        return this;
    }

    @JsonProperty("buyRatio")
    public Double getBuyRatio() {
        return buyRatio;
    }

    @JsonProperty("buyRatio")
    public void setBuyRatio(Double buyRatio) {
        this.buyRatio = buyRatio;
    }

    public StationDataItem withBuyRatio(Double buyRatio) {
        this.buyRatio = buyRatio;
        return this;
    }

    @JsonProperty("useDischargeRatio")
    public Double getUseDischargeRatio() {
        return useDischargeRatio;
    }

    @JsonProperty("useDischargeRatio")
    public void setUseDischargeRatio(Double useDischargeRatio) {
        this.useDischargeRatio = useDischargeRatio;
    }

    public StationDataItem withUseDischargeRatio(Double useDischargeRatio) {
        this.useDischargeRatio = useDischargeRatio;
        return this;
    }

    @JsonProperty("gridValue")
    public Double getGridValue() {
        return gridValue;
    }

    @JsonProperty("gridValue")
    public void setGridValue(Double gridValue) {
        this.gridValue = gridValue;
    }

    public StationDataItem withGridValue(Double gridValue) {
        this.gridValue = gridValue;
        return this;
    }

    @JsonProperty("buyValue")
    public Double getBuyValue() {
        return buyValue;
    }

    @JsonProperty("buyValue")
    public void setBuyValue(Double buyValue) {
        this.buyValue = buyValue;
    }

    public StationDataItem withBuyValue(Double buyValue) {
        this.buyValue = buyValue;
        return this;
    }

    @JsonProperty("chargeValue")
    public Double getChargeValue() {
        return chargeValue;
    }

    @JsonProperty("chargeValue")
    public void setChargeValue(Double chargeValue) {
        this.chargeValue = chargeValue;
    }

    public StationDataItem withChargeValue(Double chargeValue) {
        this.chargeValue = chargeValue;
        return this;
    }

    @JsonProperty("dischargeValue")
    public Double getDischargeValue() {
        return dischargeValue;
    }

    @JsonProperty("dischargeValue")
    public void setDischargeValue(Double dischargeValue) {
        this.dischargeValue = dischargeValue;
    }

    public StationDataItem withDischargeValue(Double dischargeValue) {
        this.dischargeValue = dischargeValue;
        return this;
    }

    @JsonProperty("fullPowerHours")
    public Double getFullPowerHours() {
        return fullPowerHours;
    }

    @JsonProperty("fullPowerHours")
    public void setFullPowerHours(Double fullPowerHours) {
        this.fullPowerHours = fullPowerHours;
    }

    public StationDataItem withFullPowerHours(Double fullPowerHours) {
        this.fullPowerHours = fullPowerHours;
        return this;
    }

    @JsonProperty("irradiate")
    public Double getIrradiate() {
        return irradiate;
    }

    @JsonProperty("irradiate")
    public void setIrradiate(Double irradiate) {
        this.irradiate = irradiate;
    }

    public StationDataItem withIrradiate(Double irradiate) {
        this.irradiate = irradiate;
        return this;
    }

    @JsonProperty("theoreticalGeneration")
    public Double getTheoreticalGeneration() {
        return theoreticalGeneration;
    }

    @JsonProperty("theoreticalGeneration")
    public void setTheoreticalGeneration(Double theoreticalGeneration) {
        this.theoreticalGeneration = theoreticalGeneration;
    }

    public StationDataItem withTheoreticalGeneration(Double theoreticalGeneration) {
        this.theoreticalGeneration = theoreticalGeneration;
        return this;
    }

    @JsonProperty("pr")
    public Double getPr() {
        return pr;
    }

    @JsonProperty("pr")
    public void setPr(Double pr) {
        this.pr = pr;
    }

    public StationDataItem withPr(Double pr) {
        this.pr = pr;
        return this;
    }

    @JsonProperty("cpr")
    public Object getCpr() {
        return cpr;
    }

    @JsonProperty("cpr")
    public void setCpr(Object cpr) {
        this.cpr = cpr;
    }

    public StationDataItem withCpr(Object cpr) {
        this.cpr = cpr;
        return this;
    }

    @JsonProperty("dateTime")
    public String getDateTime() {
        return dateTime;
    }

    @JsonProperty("dateTime")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public StationDataItem withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(Integer year) {
        this.year = year;
    }

    public StationDataItem withYear(Integer year) {
        this.year = year;
        return this;
    }

    @JsonProperty("month")
    public Integer getMonth() {
        return month;
    }

    @JsonProperty("month")
    public void setMonth(Integer month) {
        this.month = month;
    }

    public StationDataItem withMonth(Integer month) {
        this.month = month;
        return this;
    }

    @JsonProperty("day")
    public Integer getDay() {
        return day;
    }

    @JsonProperty("day")
    public void setDay(Integer day) {
        this.day = day;
    }

    public StationDataItem withDay(Integer day) {
        this.day = day;
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

    public StationDataItem withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

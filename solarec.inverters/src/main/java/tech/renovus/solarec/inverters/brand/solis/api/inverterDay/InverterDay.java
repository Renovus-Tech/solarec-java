package tech.renovus.solarec.inverters.brand.solis.api.inverterDay;

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
    "dataTimestamp",
    "timeStr",
    "acOutputType",
    "dcInputType",
    "state",
    "time",
    "pac",
    "pacStr",
    "pacPec",
    "eToday",
    "eTotal",
    "uPv1",
    "iPv1",
    "uPv2",
    "iPv2",
    "uPv3",
    "uPv4",
    "inverterTemperature",
    "powerFactor",
    "batteryCapacitySoc",
    "batteryHealthSoh",
    "socDischargeSet",
    "socChargingSet",
    "batteryVoltage",
    "bstteryCurrent",
    "batteryPower",
    "batteryTodayChargeEnergy",
    "batteryTotalChargeEnergy",
    "batteryTodayDischargeEnergy",
    "batteryTotalDischargeEnergy",
    "gridPurchasedTodayEnergy",
    "gridPurchasedTotalEnergy",
    "gridSellTodayEnergy",
    "gridSellTotalEnergy",
    "familyLoadPower",
    "bypassLoadPower",
    "pSum",
    "homeLoadTodayEnergy",
    "homeLoadTotalEnergy"
})
@Generated("jsonschema2pojo")
public class InverterDay {

    @JsonProperty("dataTimestamp")
    private String dataTimestamp;
    @JsonProperty("timeStr")
    private String timeStr;
    @JsonProperty("acOutputType")
    private Integer acOutputType;
    @JsonProperty("dcInputType")
    private Integer dcInputType;
    @JsonProperty("state")
    private Integer state;
    @JsonProperty("time")
    private String time;
    @JsonProperty("pac")
    private Double pac;
    @JsonProperty("pacStr")
    private String pacStr;
    @JsonProperty("pacPec")
    private String pacPec;
    @JsonProperty("eToday")
    private Double eToday;
    @JsonProperty("eTotal")
    private Double eTotal;
    @JsonProperty("uPv1")
    private Double uPv1;
    @JsonProperty("iPv1")
    private Double iPv1;
    @JsonProperty("uPv2")
    private Double uPv2;
    @JsonProperty("iPv2")
    private Double iPv2;
    @JsonProperty("uPv3")
    private Double uPv3;
    @JsonProperty("uPv4")
    private Double uPv4;
    @JsonProperty("inverterTemperature")
    private Double inverterTemperature;
    @JsonProperty("powerFactor")
    private Double powerFactor;
    @JsonProperty("batteryCapacitySoc")
    private Double batteryCapacitySoc;
    @JsonProperty("batteryHealthSoh")
    private Double batteryHealthSoh;
    @JsonProperty("socDischargeSet")
    private Double socDischargeSet;
    @JsonProperty("socChargingSet")
    private Double socChargingSet;
    @JsonProperty("batteryVoltage")
    private Double batteryVoltage;
    @JsonProperty("bstteryCurrent")
    private Double bstteryCurrent;
    @JsonProperty("batteryPower")
    private Double batteryPower;
    @JsonProperty("batteryTodayChargeEnergy")
    private Double batteryTodayChargeEnergy;
    @JsonProperty("batteryTotalChargeEnergy")
    private Double batteryTotalChargeEnergy;
    @JsonProperty("batteryTodayDischargeEnergy")
    private Double batteryTodayDischargeEnergy;
    @JsonProperty("batteryTotalDischargeEnergy")
    private Double batteryTotalDischargeEnergy;
    @JsonProperty("gridPurchasedTodayEnergy")
    private Double gridPurchasedTodayEnergy;
    @JsonProperty("gridPurchasedTotalEnergy")
    private Double gridPurchasedTotalEnergy;
    @JsonProperty("gridSellTodayEnergy")
    private Double gridSellTodayEnergy;
    @JsonProperty("gridSellTotalEnergy")
    private Double gridSellTotalEnergy;
    @JsonProperty("familyLoadPower")
    private Double familyLoadPower;
    @JsonProperty("bypassLoadPower")
    private Double bypassLoadPower;
    @JsonProperty("pSum")
    private Double pSum;
    @JsonProperty("homeLoadTodayEnergy")
    private Double homeLoadTodayEnergy;
    @JsonProperty("homeLoadTotalEnergy")
    private Double homeLoadTotalEnergy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("dataTimestamp")
    public String getDataTimestamp() {
        return dataTimestamp;
    }

    @JsonProperty("dataTimestamp")
    public void setDataTimestamp(String dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }

    public InverterDay withDataTimestamp(String dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
        return this;
    }

    @JsonProperty("timeStr")
    public String getTimeStr() {
        return timeStr;
    }

    @JsonProperty("timeStr")
    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public InverterDay withTimeStr(String timeStr) {
        this.timeStr = timeStr;
        return this;
    }

    @JsonProperty("acOutputType")
    public Integer getAcOutputType() {
        return acOutputType;
    }

    @JsonProperty("acOutputType")
    public void setAcOutputType(Integer acOutputType) {
        this.acOutputType = acOutputType;
    }

    public InverterDay withAcOutputType(Integer acOutputType) {
        this.acOutputType = acOutputType;
        return this;
    }

    @JsonProperty("dcInputType")
    public Integer getDcInputType() {
        return dcInputType;
    }

    @JsonProperty("dcInputType")
    public void setDcInputType(Integer dcInputType) {
        this.dcInputType = dcInputType;
    }

    public InverterDay withDcInputType(Integer dcInputType) {
        this.dcInputType = dcInputType;
        return this;
    }

    @JsonProperty("state")
    public Integer getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Integer state) {
        this.state = state;
    }

    public InverterDay withState(Integer state) {
        this.state = state;
        return this;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    public InverterDay withTime(String time) {
        this.time = time;
        return this;
    }

    @JsonProperty("pac")
    public Double getPac() {
        return pac;
    }

    @JsonProperty("pac")
    public void setPac(Double pac) {
        this.pac = pac;
    }

    public InverterDay withPac(Double pac) {
        this.pac = pac;
        return this;
    }

    @JsonProperty("pacStr")
    public String getPacStr() {
        return pacStr;
    }

    @JsonProperty("pacStr")
    public void setPacStr(String pacStr) {
        this.pacStr = pacStr;
    }

    public InverterDay withPacStr(String pacStr) {
        this.pacStr = pacStr;
        return this;
    }

    @JsonProperty("pacPec")
    public String getPacPec() {
        return pacPec;
    }

    @JsonProperty("pacPec")
    public void setPacPec(String pacPec) {
        this.pacPec = pacPec;
    }

    public InverterDay withPacPec(String pacPec) {
        this.pacPec = pacPec;
        return this;
    }

    @JsonProperty("eToday")
    public Double geteToday() {
        return eToday;
    }

    @JsonProperty("eToday")
    public void seteToday(Double eToday) {
        this.eToday = eToday;
    }

    public InverterDay witheToday(Double eToday) {
        this.eToday = eToday;
        return this;
    }

    @JsonProperty("eTotal")
    public Double geteTotal() {
        return eTotal;
    }

    @JsonProperty("eTotal")
    public void seteTotal(Double eTotal) {
        this.eTotal = eTotal;
    }

    public InverterDay witheTotal(Double eTotal) {
        this.eTotal = eTotal;
        return this;
    }

    @JsonProperty("uPv1")
    public Double getuPv1() {
        return uPv1;
    }

    @JsonProperty("uPv1")
    public void setuPv1(Double uPv1) {
        this.uPv1 = uPv1;
    }

    public InverterDay withuPv1(Double uPv1) {
        this.uPv1 = uPv1;
        return this;
    }

    @JsonProperty("iPv1")
    public Double getiPv1() {
        return iPv1;
    }

    @JsonProperty("iPv1")
    public void setiPv1(Double iPv1) {
        this.iPv1 = iPv1;
    }

    public InverterDay withiPv1(Double iPv1) {
        this.iPv1 = iPv1;
        return this;
    }

    @JsonProperty("uPv2")
    public Double getuPv2() {
        return uPv2;
    }

    @JsonProperty("uPv2")
    public void setuPv2(Double uPv2) {
        this.uPv2 = uPv2;
    }

    public InverterDay withuPv2(Double uPv2) {
        this.uPv2 = uPv2;
        return this;
    }

    @JsonProperty("iPv2")
    public Double getiPv2() {
        return iPv2;
    }

    @JsonProperty("iPv2")
    public void setiPv2(Double iPv2) {
        this.iPv2 = iPv2;
    }

    public InverterDay withiPv2(Double iPv2) {
        this.iPv2 = iPv2;
        return this;
    }

    @JsonProperty("uPv3")
    public Double getuPv3() {
        return uPv3;
    }

    @JsonProperty("uPv3")
    public void setuPv3(Double uPv3) {
        this.uPv3 = uPv3;
    }

    public InverterDay withuPv3(Double uPv3) {
        this.uPv3 = uPv3;
        return this;
    }

    @JsonProperty("uPv4")
    public Double getuPv4() {
        return uPv4;
    }

    @JsonProperty("uPv4")
    public void setuPv4(Double uPv4) {
        this.uPv4 = uPv4;
    }

    public InverterDay withuPv4(Double uPv4) {
        this.uPv4 = uPv4;
        return this;
    }

    @JsonProperty("inverterTemperature")
    public Double getInverterTemperature() {
        return inverterTemperature;
    }

    @JsonProperty("inverterTemperature")
    public void setInverterTemperature(Double inverterTemperature) {
        this.inverterTemperature = inverterTemperature;
    }

    public InverterDay withInverterTemperature(Double inverterTemperature) {
        this.inverterTemperature = inverterTemperature;
        return this;
    }

    @JsonProperty("powerFactor")
    public Double getPowerFactor() {
        return powerFactor;
    }

    @JsonProperty("powerFactor")
    public void setPowerFactor(Double powerFactor) {
        this.powerFactor = powerFactor;
    }

    public InverterDay withPowerFactor(Double powerFactor) {
        this.powerFactor = powerFactor;
        return this;
    }

    @JsonProperty("batteryCapacitySoc")
    public Double getBatteryCapacitySoc() {
        return batteryCapacitySoc;
    }

    @JsonProperty("batteryCapacitySoc")
    public void setBatteryCapacitySoc(Double batteryCapacitySoc) {
        this.batteryCapacitySoc = batteryCapacitySoc;
    }

    public InverterDay withBatteryCapacitySoc(Double batteryCapacitySoc) {
        this.batteryCapacitySoc = batteryCapacitySoc;
        return this;
    }

    @JsonProperty("batteryHealthSoh")
    public Double getBatteryHealthSoh() {
        return batteryHealthSoh;
    }

    @JsonProperty("batteryHealthSoh")
    public void setBatteryHealthSoh(Double batteryHealthSoh) {
        this.batteryHealthSoh = batteryHealthSoh;
    }

    public InverterDay withBatteryHealthSoh(Double batteryHealthSoh) {
        this.batteryHealthSoh = batteryHealthSoh;
        return this;
    }

    @JsonProperty("socDischargeSet")
    public Double getSocDischargeSet() {
        return socDischargeSet;
    }

    @JsonProperty("socDischargeSet")
    public void setSocDischargeSet(Double socDischargeSet) {
        this.socDischargeSet = socDischargeSet;
    }

    public InverterDay withSocDischargeSet(Double socDischargeSet) {
        this.socDischargeSet = socDischargeSet;
        return this;
    }

    @JsonProperty("socChargingSet")
    public Double getSocChargingSet() {
        return socChargingSet;
    }

    @JsonProperty("socChargingSet")
    public void setSocChargingSet(Double socChargingSet) {
        this.socChargingSet = socChargingSet;
    }

    public InverterDay withSocChargingSet(Double socChargingSet) {
        this.socChargingSet = socChargingSet;
        return this;
    }

    @JsonProperty("batteryVoltage")
    public Double getBatteryVoltage() {
        return batteryVoltage;
    }

    @JsonProperty("batteryVoltage")
    public void setBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public InverterDay withBatteryVoltage(Double batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
        return this;
    }

    @JsonProperty("bstteryCurrent")
    public Double getBstteryCurrent() {
        return bstteryCurrent;
    }

    @JsonProperty("bstteryCurrent")
    public void setBstteryCurrent(Double bstteryCurrent) {
        this.bstteryCurrent = bstteryCurrent;
    }

    public InverterDay withBstteryCurrent(Double bstteryCurrent) {
        this.bstteryCurrent = bstteryCurrent;
        return this;
    }

    @JsonProperty("batteryPower")
    public Double getBatteryPower() {
        return batteryPower;
    }

    @JsonProperty("batteryPower")
    public void setBatteryPower(Double batteryPower) {
        this.batteryPower = batteryPower;
    }

    public InverterDay withBatteryPower(Double batteryPower) {
        this.batteryPower = batteryPower;
        return this;
    }

    @JsonProperty("batteryTodayChargeEnergy")
    public Double getBatteryTodayChargeEnergy() {
        return batteryTodayChargeEnergy;
    }

    @JsonProperty("batteryTodayChargeEnergy")
    public void setBatteryTodayChargeEnergy(Double batteryTodayChargeEnergy) {
        this.batteryTodayChargeEnergy = batteryTodayChargeEnergy;
    }

    public InverterDay withBatteryTodayChargeEnergy(Double batteryTodayChargeEnergy) {
        this.batteryTodayChargeEnergy = batteryTodayChargeEnergy;
        return this;
    }

    @JsonProperty("batteryTotalChargeEnergy")
    public Double getBatteryTotalChargeEnergy() {
        return batteryTotalChargeEnergy;
    }

    @JsonProperty("batteryTotalChargeEnergy")
    public void setBatteryTotalChargeEnergy(Double batteryTotalChargeEnergy) {
        this.batteryTotalChargeEnergy = batteryTotalChargeEnergy;
    }

    public InverterDay withBatteryTotalChargeEnergy(Double batteryTotalChargeEnergy) {
        this.batteryTotalChargeEnergy = batteryTotalChargeEnergy;
        return this;
    }

    @JsonProperty("batteryTodayDischargeEnergy")
    public Double getBatteryTodayDischargeEnergy() {
        return batteryTodayDischargeEnergy;
    }

    @JsonProperty("batteryTodayDischargeEnergy")
    public void setBatteryTodayDischargeEnergy(Double batteryTodayDischargeEnergy) {
        this.batteryTodayDischargeEnergy = batteryTodayDischargeEnergy;
    }

    public InverterDay withBatteryTodayDischargeEnergy(Double batteryTodayDischargeEnergy) {
        this.batteryTodayDischargeEnergy = batteryTodayDischargeEnergy;
        return this;
    }

    @JsonProperty("batteryTotalDischargeEnergy")
    public Double getBatteryTotalDischargeEnergy() {
        return batteryTotalDischargeEnergy;
    }

    @JsonProperty("batteryTotalDischargeEnergy")
    public void setBatteryTotalDischargeEnergy(Double batteryTotalDischargeEnergy) {
        this.batteryTotalDischargeEnergy = batteryTotalDischargeEnergy;
    }

    public InverterDay withBatteryTotalDischargeEnergy(Double batteryTotalDischargeEnergy) {
        this.batteryTotalDischargeEnergy = batteryTotalDischargeEnergy;
        return this;
    }

    @JsonProperty("gridPurchasedTodayEnergy")
    public Double getGridPurchasedTodayEnergy() {
        return gridPurchasedTodayEnergy;
    }

    @JsonProperty("gridPurchasedTodayEnergy")
    public void setGridPurchasedTodayEnergy(Double gridPurchasedTodayEnergy) {
        this.gridPurchasedTodayEnergy = gridPurchasedTodayEnergy;
    }

    public InverterDay withGridPurchasedTodayEnergy(Double gridPurchasedTodayEnergy) {
        this.gridPurchasedTodayEnergy = gridPurchasedTodayEnergy;
        return this;
    }

    @JsonProperty("gridPurchasedTotalEnergy")
    public Double getGridPurchasedTotalEnergy() {
        return gridPurchasedTotalEnergy;
    }

    @JsonProperty("gridPurchasedTotalEnergy")
    public void setGridPurchasedTotalEnergy(Double gridPurchasedTotalEnergy) {
        this.gridPurchasedTotalEnergy = gridPurchasedTotalEnergy;
    }

    public InverterDay withGridPurchasedTotalEnergy(Double gridPurchasedTotalEnergy) {
        this.gridPurchasedTotalEnergy = gridPurchasedTotalEnergy;
        return this;
    }

    @JsonProperty("gridSellTodayEnergy")
    public Double getGridSellTodayEnergy() {
        return gridSellTodayEnergy;
    }

    @JsonProperty("gridSellTodayEnergy")
    public void setGridSellTodayEnergy(Double gridSellTodayEnergy) {
        this.gridSellTodayEnergy = gridSellTodayEnergy;
    }

    public InverterDay withGridSellTodayEnergy(Double gridSellTodayEnergy) {
        this.gridSellTodayEnergy = gridSellTodayEnergy;
        return this;
    }

    @JsonProperty("gridSellTotalEnergy")
    public Double getGridSellTotalEnergy() {
        return gridSellTotalEnergy;
    }

    @JsonProperty("gridSellTotalEnergy")
    public void setGridSellTotalEnergy(Double gridSellTotalEnergy) {
        this.gridSellTotalEnergy = gridSellTotalEnergy;
    }

    public InverterDay withGridSellTotalEnergy(Double gridSellTotalEnergy) {
        this.gridSellTotalEnergy = gridSellTotalEnergy;
        return this;
    }

    @JsonProperty("familyLoadPower")
    public Double getFamilyLoadPower() {
        return familyLoadPower;
    }

    @JsonProperty("familyLoadPower")
    public void setFamilyLoadPower(Double familyLoadPower) {
        this.familyLoadPower = familyLoadPower;
    }

    public InverterDay withFamilyLoadPower(Double familyLoadPower) {
        this.familyLoadPower = familyLoadPower;
        return this;
    }

    @JsonProperty("bypassLoadPower")
    public Double getBypassLoadPower() {
        return bypassLoadPower;
    }

    @JsonProperty("bypassLoadPower")
    public void setBypassLoadPower(Double bypassLoadPower) {
        this.bypassLoadPower = bypassLoadPower;
    }

    public InverterDay withBypassLoadPower(Double bypassLoadPower) {
        this.bypassLoadPower = bypassLoadPower;
        return this;
    }

    @JsonProperty("pSum")
    public Double getpSum() {
        return pSum;
    }

    @JsonProperty("pSum")
    public void setpSum(Double pSum) {
        this.pSum = pSum;
    }

    public InverterDay withpSum(Double pSum) {
        this.pSum = pSum;
        return this;
    }

    @JsonProperty("homeLoadTodayEnergy")
    public Double getHomeLoadTodayEnergy() {
        return homeLoadTodayEnergy;
    }

    @JsonProperty("homeLoadTodayEnergy")
    public void setHomeLoadTodayEnergy(Double homeLoadTodayEnergy) {
        this.homeLoadTodayEnergy = homeLoadTodayEnergy;
    }

    public InverterDay withHomeLoadTodayEnergy(Double homeLoadTodayEnergy) {
        this.homeLoadTodayEnergy = homeLoadTodayEnergy;
        return this;
    }

    @JsonProperty("homeLoadTotalEnergy")
    public Double getHomeLoadTotalEnergy() {
        return homeLoadTotalEnergy;
    }

    @JsonProperty("homeLoadTotalEnergy")
    public void setHomeLoadTotalEnergy(Double homeLoadTotalEnergy) {
        this.homeLoadTotalEnergy = homeLoadTotalEnergy;
    }

    public InverterDay withHomeLoadTotalEnergy(Double homeLoadTotalEnergy) {
        this.homeLoadTotalEnergy = homeLoadTotalEnergy;
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

    public InverterDay withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

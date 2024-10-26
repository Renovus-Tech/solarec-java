
package tech.renovus.solarec.inverters.brand.solis.api.inveterList;

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
    "id",
    "sn",
    "model",
    "collectorSn",
    "productModel",
    "nationalStandards",
    "inverterSoftwareVersion",
    "inverterSoftwareVersion2",
    "dcInputType",
    "acOutputType",
    "stationId",
    "tag",
    "rs485ComAddr",
    "simFlowState",
    "power",
    "powerStr",
    "pac",
    "pac1",
    "pacStr",
    "state",
    "stateExceptionFlag",
    "ivSupport",
    "inverterConfig",
    "fullHour",
    "totalFullHour",
    "maxDcBus",
    "maxDcBusTime",
    "maxUac",
    "maxUacTime",
    "maxUpv",
    "maxUpvTime",
    "timeZone",
    "timeZoneStr",
    "dataTimestamp",
    "dataTimestampStr",
    "fisTime",
    "inverterMeterModel",
    "updateShelfTime",
    "collectorId",
    "dispersionRate",
    "currentState",
    "pow1",
    "pow2",
    "pow3",
    "pow4",
    "pow5",
    "pow6",
    "pow7",
    "pow8",
    "pow9",
    "pow10",
    "pow11",
    "pow12",
    "pow13",
    "pow14",
    "pow15",
    "pow16",
    "pow17",
    "pow18",
    "pow19",
    "pow20",
    "pow21",
    "pow22",
    "pow23",
    "pow24",
    "pow25",
    "pow26",
    "pow27",
    "pow28",
    "pow29",
    "pow30",
    "pow31",
    "pow32",
    "gridPurchasedTodayEnergy",
    "gridPurchasedTodayEnergyStr",
    "gridSellTodayEnergy",
    "gridSellTodayEnergyStr",
    "psumCalPec",
    "batteryPower",
    "batteryPowerStr",
    "batteryPowerPec",
    "batteryCapacitySoc",
    "parallelStatus",
    "parallelAddr",
    "parallelPhase",
    "parallelBattery",
    "batteryTodayChargeEnergy",
    "batteryTodayChargeEnergyStr",
    "batteryTotalChargeEnergy",
    "batteryTotalChargeEnergyStr",
    "batteryTodayDischargeEnergy",
    "batteryTodayDischargeEnergyStr",
    "batteryTotalDischargeEnergy",
    "batteryTotalDischargeEnergyStr",
    "bypassLoadPower",
    "bypassLoadPowerStr",
    "backupTodayEnergy",
    "backupTodayEnergyStr",
    "backupTotalEnergy",
    "backupTotalEnergyStr",
    "nmiCode",
    "isS5",
    "batteryModel",
    "bypassAcOnoffSet",
    "parallelOnoff01",
    "parallelOnoff02",
    "etotal",
    "etoday",
    "psum",
    "psumCal",
    "etotal1",
    "offlineLongStr",
    "etoday1",
    "etotalStr",
    "etodayStr",
    "psumStr",
    "psumCalStr"
})
@Generated("jsonschema2pojo")
public class Record {

    @JsonProperty("id")
    private String id;
    @JsonProperty("sn")
    private String sn;
    @JsonProperty("model")
    private String model;
    @JsonProperty("collectorSn")
    private String collectorSn;
    @JsonProperty("productModel")
    private String productModel;
    @JsonProperty("nationalStandards")
    private String nationalStandards;
    @JsonProperty("inverterSoftwareVersion")
    private String inverterSoftwareVersion;
    @JsonProperty("inverterSoftwareVersion2")
    private String inverterSoftwareVersion2;
    @JsonProperty("dcInputType")
    private Integer dcInputType;
    @JsonProperty("acOutputType")
    private Integer acOutputType;
    @JsonProperty("stationId")
    private String stationId;
    @JsonProperty("tag")
    private String tag;
    @JsonProperty("rs485ComAddr")
    private String rs485ComAddr;
    @JsonProperty("simFlowState")
    private Integer simFlowState;
    @JsonProperty("power")
    private Double power;
    @JsonProperty("powerStr")
    private String powerStr;
    @JsonProperty("pac")
    private Double pac;
    @JsonProperty("pac1")
    private Integer pac1;
    @JsonProperty("pacStr")
    private String pacStr;
    @JsonProperty("state")
    private Integer state;
    @JsonProperty("stateExceptionFlag")
    private Integer stateExceptionFlag;
    @JsonProperty("ivSupport")
    private Integer ivSupport;
    @JsonProperty("inverterConfig")
    private String inverterConfig;
    @JsonProperty("fullHour")
    private Double fullHour;
    @JsonProperty("totalFullHour")
    private Double totalFullHour;
    @JsonProperty("maxDcBus")
    private Double maxDcBus;
    @JsonProperty("maxDcBusTime")
    private String maxDcBusTime;
    @JsonProperty("maxUac")
    private Double maxUac;
    @JsonProperty("maxUacTime")
    private String maxUacTime;
    @JsonProperty("maxUpv")
    private Double maxUpv;
    @JsonProperty("maxUpvTime")
    private String maxUpvTime;
    @JsonProperty("timeZone")
    private Double timeZone;
    @JsonProperty("timeZoneStr")
    private String timeZoneStr;
    @JsonProperty("dataTimestamp")
    private String dataTimestamp;
    @JsonProperty("dataTimestampStr")
    private String dataTimestampStr;
    @JsonProperty("fisTime")
    private String fisTime;
    @JsonProperty("inverterMeterModel")
    private Integer inverterMeterModel;
    @JsonProperty("updateShelfTime")
    private String updateShelfTime;
    @JsonProperty("collectorId")
    private String collectorId;
    @JsonProperty("dispersionRate")
    private Double dispersionRate;
    @JsonProperty("currentState")
    private String currentState;
    @JsonProperty("pow1")
    private Double pow1;
    @JsonProperty("pow2")
    private Double pow2;
    @JsonProperty("pow3")
    private Double pow3;
    @JsonProperty("pow4")
    private Double pow4;
    @JsonProperty("pow5")
    private Double pow5;
    @JsonProperty("pow6")
    private Double pow6;
    @JsonProperty("pow7")
    private Double pow7;
    @JsonProperty("pow8")
    private Double pow8;
    @JsonProperty("pow9")
    private Double pow9;
    @JsonProperty("pow10")
    private Double pow10;
    @JsonProperty("pow11")
    private Double pow11;
    @JsonProperty("pow12")
    private Double pow12;
    @JsonProperty("pow13")
    private Double pow13;
    @JsonProperty("pow14")
    private Double pow14;
    @JsonProperty("pow15")
    private Double pow15;
    @JsonProperty("pow16")
    private Double pow16;
    @JsonProperty("pow17")
    private Double pow17;
    @JsonProperty("pow18")
    private Double pow18;
    @JsonProperty("pow19")
    private Double pow19;
    @JsonProperty("pow20")
    private Double pow20;
    @JsonProperty("pow21")
    private Double pow21;
    @JsonProperty("pow22")
    private Double pow22;
    @JsonProperty("pow23")
    private Double pow23;
    @JsonProperty("pow24")
    private Double pow24;
    @JsonProperty("pow25")
    private Double pow25;
    @JsonProperty("pow26")
    private Double pow26;
    @JsonProperty("pow27")
    private Double pow27;
    @JsonProperty("pow28")
    private Double pow28;
    @JsonProperty("pow29")
    private Double pow29;
    @JsonProperty("pow30")
    private Double pow30;
    @JsonProperty("pow31")
    private Double pow31;
    @JsonProperty("pow32")
    private Double pow32;
    @JsonProperty("gridPurchasedTodayEnergy")
    private Double gridPurchasedTodayEnergy;
    @JsonProperty("gridPurchasedTodayEnergyStr")
    private String gridPurchasedTodayEnergyStr;
    @JsonProperty("gridSellTodayEnergy")
    private Double gridSellTodayEnergy;
    @JsonProperty("gridSellTodayEnergyStr")
    private String gridSellTodayEnergyStr;
    @JsonProperty("psumCalPec")
    private String psumCalPec;
    @JsonProperty("batteryPower")
    private Double batteryPower;
    @JsonProperty("batteryPowerStr")
    private String batteryPowerStr;
    @JsonProperty("batteryPowerPec")
    private String batteryPowerPec;
    @JsonProperty("batteryCapacitySoc")
    private Double batteryCapacitySoc;
    @JsonProperty("parallelStatus")
    private Integer parallelStatus;
    @JsonProperty("parallelAddr")
    private Integer parallelAddr;
    @JsonProperty("parallelPhase")
    private Integer parallelPhase;
    @JsonProperty("parallelBattery")
    private Integer parallelBattery;
    @JsonProperty("batteryTodayChargeEnergy")
    private Double batteryTodayChargeEnergy;
    @JsonProperty("batteryTodayChargeEnergyStr")
    private String batteryTodayChargeEnergyStr;
    @JsonProperty("batteryTotalChargeEnergy")
    private Double batteryTotalChargeEnergy;
    @JsonProperty("batteryTotalChargeEnergyStr")
    private String batteryTotalChargeEnergyStr;
    @JsonProperty("batteryTodayDischargeEnergy")
    private Double batteryTodayDischargeEnergy;
    @JsonProperty("batteryTodayDischargeEnergyStr")
    private String batteryTodayDischargeEnergyStr;
    @JsonProperty("batteryTotalDischargeEnergy")
    private Double batteryTotalDischargeEnergy;
    @JsonProperty("batteryTotalDischargeEnergyStr")
    private String batteryTotalDischargeEnergyStr;
    @JsonProperty("bypassLoadPower")
    private Double bypassLoadPower;
    @JsonProperty("bypassLoadPowerStr")
    private String bypassLoadPowerStr;
    @JsonProperty("backupTodayEnergy")
    private Double backupTodayEnergy;
    @JsonProperty("backupTodayEnergyStr")
    private String backupTodayEnergyStr;
    @JsonProperty("backupTotalEnergy")
    private Double backupTotalEnergy;
    @JsonProperty("backupTotalEnergyStr")
    private String backupTotalEnergyStr;
    @JsonProperty("nmiCode")
    private String nmiCode;
    @JsonProperty("isS5")
    private Integer isS5;
    @JsonProperty("batteryModel")
    private Integer batteryModel;
    @JsonProperty("bypassAcOnoffSet")
    private Double bypassAcOnoffSet;
    @JsonProperty("parallelOnoff01")
    private Double parallelOnoff01;
    @JsonProperty("parallelOnoff02")
    private Double parallelOnoff02;
    @JsonProperty("etotal")
    private Double etotal;
    @JsonProperty("etoday")
    private Double etoday;
    @JsonProperty("psum")
    private Double psum;
    @JsonProperty("psumCal")
    private Double psumCal;
    @JsonProperty("etotal1")
    private Double etotal1;
    @JsonProperty("offlineLongStr")
    private String offlineLongStr;
    @JsonProperty("etoday1")
    private Double etoday1;
    @JsonProperty("etotalStr")
    private String etotalStr;
    @JsonProperty("etodayStr")
    private String etodayStr;
    @JsonProperty("psumStr")
    private String psumStr;
    @JsonProperty("psumCalStr")
    private String psumCalStr;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Record withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("sn")
    public String getSn() {
        return sn;
    }

    @JsonProperty("sn")
    public void setSn(String sn) {
        this.sn = sn;
    }

    public Record withSn(String sn) {
        this.sn = sn;
        return this;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    public Record withModel(String model) {
        this.model = model;
        return this;
    }

    @JsonProperty("collectorSn")
    public String getCollectorSn() {
        return collectorSn;
    }

    @JsonProperty("collectorSn")
    public void setCollectorSn(String collectorSn) {
        this.collectorSn = collectorSn;
    }

    public Record withCollectorSn(String collectorSn) {
        this.collectorSn = collectorSn;
        return this;
    }

    @JsonProperty("productModel")
    public String getProductModel() {
        return productModel;
    }

    @JsonProperty("productModel")
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Record withProductModel(String productModel) {
        this.productModel = productModel;
        return this;
    }

    @JsonProperty("nationalStandards")
    public String getNationalStandards() {
        return nationalStandards;
    }

    @JsonProperty("nationalStandards")
    public void setNationalStandards(String nationalStandards) {
        this.nationalStandards = nationalStandards;
    }

    public Record withNationalStandards(String nationalStandards) {
        this.nationalStandards = nationalStandards;
        return this;
    }

    @JsonProperty("inverterSoftwareVersion")
    public String getInverterSoftwareVersion() {
        return inverterSoftwareVersion;
    }

    @JsonProperty("inverterSoftwareVersion")
    public void setInverterSoftwareVersion(String inverterSoftwareVersion) {
        this.inverterSoftwareVersion = inverterSoftwareVersion;
    }

    public Record withInverterSoftwareVersion(String inverterSoftwareVersion) {
        this.inverterSoftwareVersion = inverterSoftwareVersion;
        return this;
    }

    @JsonProperty("inverterSoftwareVersion2")
    public String getInverterSoftwareVersion2() {
        return inverterSoftwareVersion2;
    }

    @JsonProperty("inverterSoftwareVersion2")
    public void setInverterSoftwareVersion2(String inverterSoftwareVersion2) {
        this.inverterSoftwareVersion2 = inverterSoftwareVersion2;
    }

    public Record withInverterSoftwareVersion2(String inverterSoftwareVersion2) {
        this.inverterSoftwareVersion2 = inverterSoftwareVersion2;
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

    public Record withDcInputType(Integer dcInputType) {
        this.dcInputType = dcInputType;
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

    public Record withAcOutputType(Integer acOutputType) {
        this.acOutputType = acOutputType;
        return this;
    }

    @JsonProperty("stationId")
    public String getStationId() {
        return stationId;
    }

    @JsonProperty("stationId")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Record withStationId(String stationId) {
        this.stationId = stationId;
        return this;
    }

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    public Record withTag(String tag) {
        this.tag = tag;
        return this;
    }

    @JsonProperty("rs485ComAddr")
    public String getRs485ComAddr() {
        return rs485ComAddr;
    }

    @JsonProperty("rs485ComAddr")
    public void setRs485ComAddr(String rs485ComAddr) {
        this.rs485ComAddr = rs485ComAddr;
    }

    public Record withRs485ComAddr(String rs485ComAddr) {
        this.rs485ComAddr = rs485ComAddr;
        return this;
    }

    @JsonProperty("simFlowState")
    public Integer getSimFlowState() {
        return simFlowState;
    }

    @JsonProperty("simFlowState")
    public void setSimFlowState(Integer simFlowState) {
        this.simFlowState = simFlowState;
    }

    public Record withSimFlowState(Integer simFlowState) {
        this.simFlowState = simFlowState;
        return this;
    }

    @JsonProperty("power")
    public Double getPower() {
        return power;
    }

    @JsonProperty("power")
    public void setPower(Double power) {
        this.power = power;
    }

    public Record withPower(Double power) {
        this.power = power;
        return this;
    }

    @JsonProperty("powerStr")
    public String getPowerStr() {
        return powerStr;
    }

    @JsonProperty("powerStr")
    public void setPowerStr(String powerStr) {
        this.powerStr = powerStr;
    }

    public Record withPowerStr(String powerStr) {
        this.powerStr = powerStr;
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

    public Record withPac(Double pac) {
        this.pac = pac;
        return this;
    }

    @JsonProperty("pac1")
    public Integer getPac1() {
        return pac1;
    }

    @JsonProperty("pac1")
    public void setPac1(Integer pac1) {
        this.pac1 = pac1;
    }

    public Record withPac1(Integer pac1) {
        this.pac1 = pac1;
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

    public Record withPacStr(String pacStr) {
        this.pacStr = pacStr;
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

    public Record withState(Integer state) {
        this.state = state;
        return this;
    }

    @JsonProperty("stateExceptionFlag")
    public Integer getStateExceptionFlag() {
        return stateExceptionFlag;
    }

    @JsonProperty("stateExceptionFlag")
    public void setStateExceptionFlag(Integer stateExceptionFlag) {
        this.stateExceptionFlag = stateExceptionFlag;
    }

    public Record withStateExceptionFlag(Integer stateExceptionFlag) {
        this.stateExceptionFlag = stateExceptionFlag;
        return this;
    }

    @JsonProperty("ivSupport")
    public Integer getIvSupport() {
        return ivSupport;
    }

    @JsonProperty("ivSupport")
    public void setIvSupport(Integer ivSupport) {
        this.ivSupport = ivSupport;
    }

    public Record withIvSupport(Integer ivSupport) {
        this.ivSupport = ivSupport;
        return this;
    }

    @JsonProperty("inverterConfig")
    public String getInverterConfig() {
        return inverterConfig;
    }

    @JsonProperty("inverterConfig")
    public void setInverterConfig(String inverterConfig) {
        this.inverterConfig = inverterConfig;
    }

    public Record withInverterConfig(String inverterConfig) {
        this.inverterConfig = inverterConfig;
        return this;
    }

    @JsonProperty("fullHour")
    public Double getFullHour() {
        return fullHour;
    }

    @JsonProperty("fullHour")
    public void setFullHour(Double fullHour) {
        this.fullHour = fullHour;
    }

    public Record withFullHour(Double fullHour) {
        this.fullHour = fullHour;
        return this;
    }

    @JsonProperty("totalFullHour")
    public Double getTotalFullHour() {
        return totalFullHour;
    }

    @JsonProperty("totalFullHour")
    public void setTotalFullHour(Double totalFullHour) {
        this.totalFullHour = totalFullHour;
    }

    public Record withTotalFullHour(Double totalFullHour) {
        this.totalFullHour = totalFullHour;
        return this;
    }

    @JsonProperty("maxDcBus")
    public Double getMaxDcBus() {
        return maxDcBus;
    }

    @JsonProperty("maxDcBus")
    public void setMaxDcBus(Double maxDcBus) {
        this.maxDcBus = maxDcBus;
    }

    public Record withMaxDcBus(Double maxDcBus) {
        this.maxDcBus = maxDcBus;
        return this;
    }

    @JsonProperty("maxDcBusTime")
    public String getMaxDcBusTime() {
        return maxDcBusTime;
    }

    @JsonProperty("maxDcBusTime")
    public void setMaxDcBusTime(String maxDcBusTime) {
        this.maxDcBusTime = maxDcBusTime;
    }

    public Record withMaxDcBusTime(String maxDcBusTime) {
        this.maxDcBusTime = maxDcBusTime;
        return this;
    }

    @JsonProperty("maxUac")
    public Double getMaxUac() {
        return maxUac;
    }

    @JsonProperty("maxUac")
    public void setMaxUac(Double maxUac) {
        this.maxUac = maxUac;
    }

    public Record withMaxUac(Double maxUac) {
        this.maxUac = maxUac;
        return this;
    }

    @JsonProperty("maxUacTime")
    public String getMaxUacTime() {
        return maxUacTime;
    }

    @JsonProperty("maxUacTime")
    public void setMaxUacTime(String maxUacTime) {
        this.maxUacTime = maxUacTime;
    }

    public Record withMaxUacTime(String maxUacTime) {
        this.maxUacTime = maxUacTime;
        return this;
    }

    @JsonProperty("maxUpv")
    public Double getMaxUpv() {
        return maxUpv;
    }

    @JsonProperty("maxUpv")
    public void setMaxUpv(Double maxUpv) {
        this.maxUpv = maxUpv;
    }

    public Record withMaxUpv(Double maxUpv) {
        this.maxUpv = maxUpv;
        return this;
    }

    @JsonProperty("maxUpvTime")
    public String getMaxUpvTime() {
        return maxUpvTime;
    }

    @JsonProperty("maxUpvTime")
    public void setMaxUpvTime(String maxUpvTime) {
        this.maxUpvTime = maxUpvTime;
    }

    public Record withMaxUpvTime(String maxUpvTime) {
        this.maxUpvTime = maxUpvTime;
        return this;
    }

    @JsonProperty("timeZone")
    public Double getTimeZone() {
        return timeZone;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(Double timeZone) {
        this.timeZone = timeZone;
    }

    public Record withTimeZone(Double timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @JsonProperty("timeZoneStr")
    public String getTimeZoneStr() {
        return timeZoneStr;
    }

    @JsonProperty("timeZoneStr")
    public void setTimeZoneStr(String timeZoneStr) {
        this.timeZoneStr = timeZoneStr;
    }

    public Record withTimeZoneStr(String timeZoneStr) {
        this.timeZoneStr = timeZoneStr;
        return this;
    }

    @JsonProperty("dataTimestamp")
    public String getDataTimestamp() {
        return dataTimestamp;
    }

    @JsonProperty("dataTimestamp")
    public void setDataTimestamp(String dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }

    public Record withDataTimestamp(String dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
        return this;
    }

    @JsonProperty("dataTimestampStr")
    public String getDataTimestampStr() {
        return dataTimestampStr;
    }

    @JsonProperty("dataTimestampStr")
    public void setDataTimestampStr(String dataTimestampStr) {
        this.dataTimestampStr = dataTimestampStr;
    }

    public Record withDataTimestampStr(String dataTimestampStr) {
        this.dataTimestampStr = dataTimestampStr;
        return this;
    }

    @JsonProperty("fisTime")
    public String getFisTime() {
        return fisTime;
    }

    @JsonProperty("fisTime")
    public void setFisTime(String fisTime) {
        this.fisTime = fisTime;
    }

    public Record withFisTime(String fisTime) {
        this.fisTime = fisTime;
        return this;
    }

    @JsonProperty("inverterMeterModel")
    public Integer getInverterMeterModel() {
        return inverterMeterModel;
    }

    @JsonProperty("inverterMeterModel")
    public void setInverterMeterModel(Integer inverterMeterModel) {
        this.inverterMeterModel = inverterMeterModel;
    }

    public Record withInverterMeterModel(Integer inverterMeterModel) {
        this.inverterMeterModel = inverterMeterModel;
        return this;
    }

    @JsonProperty("updateShelfTime")
    public String getUpdateShelfTime() {
        return updateShelfTime;
    }

    @JsonProperty("updateShelfTime")
    public void setUpdateShelfTime(String updateShelfTime) {
        this.updateShelfTime = updateShelfTime;
    }

    public Record withUpdateShelfTime(String updateShelfTime) {
        this.updateShelfTime = updateShelfTime;
        return this;
    }

    @JsonProperty("collectorId")
    public String getCollectorId() {
        return collectorId;
    }

    @JsonProperty("collectorId")
    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public Record withCollectorId(String collectorId) {
        this.collectorId = collectorId;
        return this;
    }

    @JsonProperty("dispersionRate")
    public Double getDispersionRate() {
        return dispersionRate;
    }

    @JsonProperty("dispersionRate")
    public void setDispersionRate(Double dispersionRate) {
        this.dispersionRate = dispersionRate;
    }

    public Record withDispersionRate(Double dispersionRate) {
        this.dispersionRate = dispersionRate;
        return this;
    }

    @JsonProperty("currentState")
    public String getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public Record withCurrentState(String currentState) {
        this.currentState = currentState;
        return this;
    }

    @JsonProperty("pow1")
    public Double getPow1() {
        return pow1;
    }

    @JsonProperty("pow1")
    public void setPow1(Double pow1) {
        this.pow1 = pow1;
    }

    public Record withPow1(Double pow1) {
        this.pow1 = pow1;
        return this;
    }

    @JsonProperty("pow2")
    public Double getPow2() {
        return pow2;
    }

    @JsonProperty("pow2")
    public void setPow2(Double pow2) {
        this.pow2 = pow2;
    }

    public Record withPow2(Double pow2) {
        this.pow2 = pow2;
        return this;
    }

    @JsonProperty("pow3")
    public Double getPow3() {
        return pow3;
    }

    @JsonProperty("pow3")
    public void setPow3(Double pow3) {
        this.pow3 = pow3;
    }

    public Record withPow3(Double pow3) {
        this.pow3 = pow3;
        return this;
    }

    @JsonProperty("pow4")
    public Double getPow4() {
        return pow4;
    }

    @JsonProperty("pow4")
    public void setPow4(Double pow4) {
        this.pow4 = pow4;
    }

    public Record withPow4(Double pow4) {
        this.pow4 = pow4;
        return this;
    }

    @JsonProperty("pow5")
    public Double getPow5() {
        return pow5;
    }

    @JsonProperty("pow5")
    public void setPow5(Double pow5) {
        this.pow5 = pow5;
    }

    public Record withPow5(Double pow5) {
        this.pow5 = pow5;
        return this;
    }

    @JsonProperty("pow6")
    public Double getPow6() {
        return pow6;
    }

    @JsonProperty("pow6")
    public void setPow6(Double pow6) {
        this.pow6 = pow6;
    }

    public Record withPow6(Double pow6) {
        this.pow6 = pow6;
        return this;
    }

    @JsonProperty("pow7")
    public Double getPow7() {
        return pow7;
    }

    @JsonProperty("pow7")
    public void setPow7(Double pow7) {
        this.pow7 = pow7;
    }

    public Record withPow7(Double pow7) {
        this.pow7 = pow7;
        return this;
    }

    @JsonProperty("pow8")
    public Double getPow8() {
        return pow8;
    }

    @JsonProperty("pow8")
    public void setPow8(Double pow8) {
        this.pow8 = pow8;
    }

    public Record withPow8(Double pow8) {
        this.pow8 = pow8;
        return this;
    }

    @JsonProperty("pow9")
    public Double getPow9() {
        return pow9;
    }

    @JsonProperty("pow9")
    public void setPow9(Double pow9) {
        this.pow9 = pow9;
    }

    public Record withPow9(Double pow9) {
        this.pow9 = pow9;
        return this;
    }

    @JsonProperty("pow10")
    public Double getPow10() {
        return pow10;
    }

    @JsonProperty("pow10")
    public void setPow10(Double pow10) {
        this.pow10 = pow10;
    }

    public Record withPow10(Double pow10) {
        this.pow10 = pow10;
        return this;
    }

    @JsonProperty("pow11")
    public Double getPow11() {
        return pow11;
    }

    @JsonProperty("pow11")
    public void setPow11(Double pow11) {
        this.pow11 = pow11;
    }

    public Record withPow11(Double pow11) {
        this.pow11 = pow11;
        return this;
    }

    @JsonProperty("pow12")
    public Double getPow12() {
        return pow12;
    }

    @JsonProperty("pow12")
    public void setPow12(Double pow12) {
        this.pow12 = pow12;
    }

    public Record withPow12(Double pow12) {
        this.pow12 = pow12;
        return this;
    }

    @JsonProperty("pow13")
    public Double getPow13() {
        return pow13;
    }

    @JsonProperty("pow13")
    public void setPow13(Double pow13) {
        this.pow13 = pow13;
    }

    public Record withPow13(Double pow13) {
        this.pow13 = pow13;
        return this;
    }

    @JsonProperty("pow14")
    public Double getPow14() {
        return pow14;
    }

    @JsonProperty("pow14")
    public void setPow14(Double pow14) {
        this.pow14 = pow14;
    }

    public Record withPow14(Double pow14) {
        this.pow14 = pow14;
        return this;
    }

    @JsonProperty("pow15")
    public Double getPow15() {
        return pow15;
    }

    @JsonProperty("pow15")
    public void setPow15(Double pow15) {
        this.pow15 = pow15;
    }

    public Record withPow15(Double pow15) {
        this.pow15 = pow15;
        return this;
    }

    @JsonProperty("pow16")
    public Double getPow16() {
        return pow16;
    }

    @JsonProperty("pow16")
    public void setPow16(Double pow16) {
        this.pow16 = pow16;
    }

    public Record withPow16(Double pow16) {
        this.pow16 = pow16;
        return this;
    }

    @JsonProperty("pow17")
    public Double getPow17() {
        return pow17;
    }

    @JsonProperty("pow17")
    public void setPow17(Double pow17) {
        this.pow17 = pow17;
    }

    public Record withPow17(Double pow17) {
        this.pow17 = pow17;
        return this;
    }

    @JsonProperty("pow18")
    public Double getPow18() {
        return pow18;
    }

    @JsonProperty("pow18")
    public void setPow18(Double pow18) {
        this.pow18 = pow18;
    }

    public Record withPow18(Double pow18) {
        this.pow18 = pow18;
        return this;
    }

    @JsonProperty("pow19")
    public Double getPow19() {
        return pow19;
    }

    @JsonProperty("pow19")
    public void setPow19(Double pow19) {
        this.pow19 = pow19;
    }

    public Record withPow19(Double pow19) {
        this.pow19 = pow19;
        return this;
    }

    @JsonProperty("pow20")
    public Double getPow20() {
        return pow20;
    }

    @JsonProperty("pow20")
    public void setPow20(Double pow20) {
        this.pow20 = pow20;
    }

    public Record withPow20(Double pow20) {
        this.pow20 = pow20;
        return this;
    }

    @JsonProperty("pow21")
    public Double getPow21() {
        return pow21;
    }

    @JsonProperty("pow21")
    public void setPow21(Double pow21) {
        this.pow21 = pow21;
    }

    public Record withPow21(Double pow21) {
        this.pow21 = pow21;
        return this;
    }

    @JsonProperty("pow22")
    public Double getPow22() {
        return pow22;
    }

    @JsonProperty("pow22")
    public void setPow22(Double pow22) {
        this.pow22 = pow22;
    }

    public Record withPow22(Double pow22) {
        this.pow22 = pow22;
        return this;
    }

    @JsonProperty("pow23")
    public Double getPow23() {
        return pow23;
    }

    @JsonProperty("pow23")
    public void setPow23(Double pow23) {
        this.pow23 = pow23;
    }

    public Record withPow23(Double pow23) {
        this.pow23 = pow23;
        return this;
    }

    @JsonProperty("pow24")
    public Double getPow24() {
        return pow24;
    }

    @JsonProperty("pow24")
    public void setPow24(Double pow24) {
        this.pow24 = pow24;
    }

    public Record withPow24(Double pow24) {
        this.pow24 = pow24;
        return this;
    }

    @JsonProperty("pow25")
    public Double getPow25() {
        return pow25;
    }

    @JsonProperty("pow25")
    public void setPow25(Double pow25) {
        this.pow25 = pow25;
    }

    public Record withPow25(Double pow25) {
        this.pow25 = pow25;
        return this;
    }

    @JsonProperty("pow26")
    public Double getPow26() {
        return pow26;
    }

    @JsonProperty("pow26")
    public void setPow26(Double pow26) {
        this.pow26 = pow26;
    }

    public Record withPow26(Double pow26) {
        this.pow26 = pow26;
        return this;
    }

    @JsonProperty("pow27")
    public Double getPow27() {
        return pow27;
    }

    @JsonProperty("pow27")
    public void setPow27(Double pow27) {
        this.pow27 = pow27;
    }

    public Record withPow27(Double pow27) {
        this.pow27 = pow27;
        return this;
    }

    @JsonProperty("pow28")
    public Double getPow28() {
        return pow28;
    }

    @JsonProperty("pow28")
    public void setPow28(Double pow28) {
        this.pow28 = pow28;
    }

    public Record withPow28(Double pow28) {
        this.pow28 = pow28;
        return this;
    }

    @JsonProperty("pow29")
    public Double getPow29() {
        return pow29;
    }

    @JsonProperty("pow29")
    public void setPow29(Double pow29) {
        this.pow29 = pow29;
    }

    public Record withPow29(Double pow29) {
        this.pow29 = pow29;
        return this;
    }

    @JsonProperty("pow30")
    public Double getPow30() {
        return pow30;
    }

    @JsonProperty("pow30")
    public void setPow30(Double pow30) {
        this.pow30 = pow30;
    }

    public Record withPow30(Double pow30) {
        this.pow30 = pow30;
        return this;
    }

    @JsonProperty("pow31")
    public Double getPow31() {
        return pow31;
    }

    @JsonProperty("pow31")
    public void setPow31(Double pow31) {
        this.pow31 = pow31;
    }

    public Record withPow31(Double pow31) {
        this.pow31 = pow31;
        return this;
    }

    @JsonProperty("pow32")
    public Double getPow32() {
        return pow32;
    }

    @JsonProperty("pow32")
    public void setPow32(Double pow32) {
        this.pow32 = pow32;
    }

    public Record withPow32(Double pow32) {
        this.pow32 = pow32;
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

    public Record withGridPurchasedTodayEnergy(Double gridPurchasedTodayEnergy) {
        this.gridPurchasedTodayEnergy = gridPurchasedTodayEnergy;
        return this;
    }

    @JsonProperty("gridPurchasedTodayEnergyStr")
    public String getGridPurchasedTodayEnergyStr() {
        return gridPurchasedTodayEnergyStr;
    }

    @JsonProperty("gridPurchasedTodayEnergyStr")
    public void setGridPurchasedTodayEnergyStr(String gridPurchasedTodayEnergyStr) {
        this.gridPurchasedTodayEnergyStr = gridPurchasedTodayEnergyStr;
    }

    public Record withGridPurchasedTodayEnergyStr(String gridPurchasedTodayEnergyStr) {
        this.gridPurchasedTodayEnergyStr = gridPurchasedTodayEnergyStr;
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

    public Record withGridSellTodayEnergy(Double gridSellTodayEnergy) {
        this.gridSellTodayEnergy = gridSellTodayEnergy;
        return this;
    }

    @JsonProperty("gridSellTodayEnergyStr")
    public String getGridSellTodayEnergyStr() {
        return gridSellTodayEnergyStr;
    }

    @JsonProperty("gridSellTodayEnergyStr")
    public void setGridSellTodayEnergyStr(String gridSellTodayEnergyStr) {
        this.gridSellTodayEnergyStr = gridSellTodayEnergyStr;
    }

    public Record withGridSellTodayEnergyStr(String gridSellTodayEnergyStr) {
        this.gridSellTodayEnergyStr = gridSellTodayEnergyStr;
        return this;
    }

    @JsonProperty("psumCalPec")
    public String getPsumCalPec() {
        return psumCalPec;
    }

    @JsonProperty("psumCalPec")
    public void setPsumCalPec(String psumCalPec) {
        this.psumCalPec = psumCalPec;
    }

    public Record withPsumCalPec(String psumCalPec) {
        this.psumCalPec = psumCalPec;
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

    public Record withBatteryPower(Double batteryPower) {
        this.batteryPower = batteryPower;
        return this;
    }

    @JsonProperty("batteryPowerStr")
    public String getBatteryPowerStr() {
        return batteryPowerStr;
    }

    @JsonProperty("batteryPowerStr")
    public void setBatteryPowerStr(String batteryPowerStr) {
        this.batteryPowerStr = batteryPowerStr;
    }

    public Record withBatteryPowerStr(String batteryPowerStr) {
        this.batteryPowerStr = batteryPowerStr;
        return this;
    }

    @JsonProperty("batteryPowerPec")
    public String getBatteryPowerPec() {
        return batteryPowerPec;
    }

    @JsonProperty("batteryPowerPec")
    public void setBatteryPowerPec(String batteryPowerPec) {
        this.batteryPowerPec = batteryPowerPec;
    }

    public Record withBatteryPowerPec(String batteryPowerPec) {
        this.batteryPowerPec = batteryPowerPec;
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

    public Record withBatteryCapacitySoc(Double batteryCapacitySoc) {
        this.batteryCapacitySoc = batteryCapacitySoc;
        return this;
    }

    @JsonProperty("parallelStatus")
    public Integer getParallelStatus() {
        return parallelStatus;
    }

    @JsonProperty("parallelStatus")
    public void setParallelStatus(Integer parallelStatus) {
        this.parallelStatus = parallelStatus;
    }

    public Record withParallelStatus(Integer parallelStatus) {
        this.parallelStatus = parallelStatus;
        return this;
    }

    @JsonProperty("parallelAddr")
    public Integer getParallelAddr() {
        return parallelAddr;
    }

    @JsonProperty("parallelAddr")
    public void setParallelAddr(Integer parallelAddr) {
        this.parallelAddr = parallelAddr;
    }

    public Record withParallelAddr(Integer parallelAddr) {
        this.parallelAddr = parallelAddr;
        return this;
    }

    @JsonProperty("parallelPhase")
    public Integer getParallelPhase() {
        return parallelPhase;
    }

    @JsonProperty("parallelPhase")
    public void setParallelPhase(Integer parallelPhase) {
        this.parallelPhase = parallelPhase;
    }

    public Record withParallelPhase(Integer parallelPhase) {
        this.parallelPhase = parallelPhase;
        return this;
    }

    @JsonProperty("parallelBattery")
    public Integer getParallelBattery() {
        return parallelBattery;
    }

    @JsonProperty("parallelBattery")
    public void setParallelBattery(Integer parallelBattery) {
        this.parallelBattery = parallelBattery;
    }

    public Record withParallelBattery(Integer parallelBattery) {
        this.parallelBattery = parallelBattery;
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

    public Record withBatteryTodayChargeEnergy(Double batteryTodayChargeEnergy) {
        this.batteryTodayChargeEnergy = batteryTodayChargeEnergy;
        return this;
    }

    @JsonProperty("batteryTodayChargeEnergyStr")
    public String getBatteryTodayChargeEnergyStr() {
        return batteryTodayChargeEnergyStr;
    }

    @JsonProperty("batteryTodayChargeEnergyStr")
    public void setBatteryTodayChargeEnergyStr(String batteryTodayChargeEnergyStr) {
        this.batteryTodayChargeEnergyStr = batteryTodayChargeEnergyStr;
    }

    public Record withBatteryTodayChargeEnergyStr(String batteryTodayChargeEnergyStr) {
        this.batteryTodayChargeEnergyStr = batteryTodayChargeEnergyStr;
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

    public Record withBatteryTotalChargeEnergy(Double batteryTotalChargeEnergy) {
        this.batteryTotalChargeEnergy = batteryTotalChargeEnergy;
        return this;
    }

    @JsonProperty("batteryTotalChargeEnergyStr")
    public String getBatteryTotalChargeEnergyStr() {
        return batteryTotalChargeEnergyStr;
    }

    @JsonProperty("batteryTotalChargeEnergyStr")
    public void setBatteryTotalChargeEnergyStr(String batteryTotalChargeEnergyStr) {
        this.batteryTotalChargeEnergyStr = batteryTotalChargeEnergyStr;
    }

    public Record withBatteryTotalChargeEnergyStr(String batteryTotalChargeEnergyStr) {
        this.batteryTotalChargeEnergyStr = batteryTotalChargeEnergyStr;
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

    public Record withBatteryTodayDischargeEnergy(Double batteryTodayDischargeEnergy) {
        this.batteryTodayDischargeEnergy = batteryTodayDischargeEnergy;
        return this;
    }

    @JsonProperty("batteryTodayDischargeEnergyStr")
    public String getBatteryTodayDischargeEnergyStr() {
        return batteryTodayDischargeEnergyStr;
    }

    @JsonProperty("batteryTodayDischargeEnergyStr")
    public void setBatteryTodayDischargeEnergyStr(String batteryTodayDischargeEnergyStr) {
        this.batteryTodayDischargeEnergyStr = batteryTodayDischargeEnergyStr;
    }

    public Record withBatteryTodayDischargeEnergyStr(String batteryTodayDischargeEnergyStr) {
        this.batteryTodayDischargeEnergyStr = batteryTodayDischargeEnergyStr;
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

    public Record withBatteryTotalDischargeEnergy(Double batteryTotalDischargeEnergy) {
        this.batteryTotalDischargeEnergy = batteryTotalDischargeEnergy;
        return this;
    }

    @JsonProperty("batteryTotalDischargeEnergyStr")
    public String getBatteryTotalDischargeEnergyStr() {
        return batteryTotalDischargeEnergyStr;
    }

    @JsonProperty("batteryTotalDischargeEnergyStr")
    public void setBatteryTotalDischargeEnergyStr(String batteryTotalDischargeEnergyStr) {
        this.batteryTotalDischargeEnergyStr = batteryTotalDischargeEnergyStr;
    }

    public Record withBatteryTotalDischargeEnergyStr(String batteryTotalDischargeEnergyStr) {
        this.batteryTotalDischargeEnergyStr = batteryTotalDischargeEnergyStr;
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

    public Record withBypassLoadPower(Double bypassLoadPower) {
        this.bypassLoadPower = bypassLoadPower;
        return this;
    }

    @JsonProperty("bypassLoadPowerStr")
    public String getBypassLoadPowerStr() {
        return bypassLoadPowerStr;
    }

    @JsonProperty("bypassLoadPowerStr")
    public void setBypassLoadPowerStr(String bypassLoadPowerStr) {
        this.bypassLoadPowerStr = bypassLoadPowerStr;
    }

    public Record withBypassLoadPowerStr(String bypassLoadPowerStr) {
        this.bypassLoadPowerStr = bypassLoadPowerStr;
        return this;
    }

    @JsonProperty("backupTodayEnergy")
    public Double getBackupTodayEnergy() {
        return backupTodayEnergy;
    }

    @JsonProperty("backupTodayEnergy")
    public void setBackupTodayEnergy(Double backupTodayEnergy) {
        this.backupTodayEnergy = backupTodayEnergy;
    }

    public Record withBackupTodayEnergy(Double backupTodayEnergy) {
        this.backupTodayEnergy = backupTodayEnergy;
        return this;
    }

    @JsonProperty("backupTodayEnergyStr")
    public String getBackupTodayEnergyStr() {
        return backupTodayEnergyStr;
    }

    @JsonProperty("backupTodayEnergyStr")
    public void setBackupTodayEnergyStr(String backupTodayEnergyStr) {
        this.backupTodayEnergyStr = backupTodayEnergyStr;
    }

    public Record withBackupTodayEnergyStr(String backupTodayEnergyStr) {
        this.backupTodayEnergyStr = backupTodayEnergyStr;
        return this;
    }

    @JsonProperty("backupTotalEnergy")
    public Double getBackupTotalEnergy() {
        return backupTotalEnergy;
    }

    @JsonProperty("backupTotalEnergy")
    public void setBackupTotalEnergy(Double backupTotalEnergy) {
        this.backupTotalEnergy = backupTotalEnergy;
    }

    public Record withBackupTotalEnergy(Double backupTotalEnergy) {
        this.backupTotalEnergy = backupTotalEnergy;
        return this;
    }

    @JsonProperty("backupTotalEnergyStr")
    public String getBackupTotalEnergyStr() {
        return backupTotalEnergyStr;
    }

    @JsonProperty("backupTotalEnergyStr")
    public void setBackupTotalEnergyStr(String backupTotalEnergyStr) {
        this.backupTotalEnergyStr = backupTotalEnergyStr;
    }

    public Record withBackupTotalEnergyStr(String backupTotalEnergyStr) {
        this.backupTotalEnergyStr = backupTotalEnergyStr;
        return this;
    }

    @JsonProperty("nmiCode")
    public String getNmiCode() {
        return nmiCode;
    }

    @JsonProperty("nmiCode")
    public void setNmiCode(String nmiCode) {
        this.nmiCode = nmiCode;
    }

    public Record withNmiCode(String nmiCode) {
        this.nmiCode = nmiCode;
        return this;
    }

    @JsonProperty("isS5")
    public Integer getIsS5() {
        return isS5;
    }

    @JsonProperty("isS5")
    public void setIsS5(Integer isS5) {
        this.isS5 = isS5;
    }

    public Record withIsS5(Integer isS5) {
        this.isS5 = isS5;
        return this;
    }

    @JsonProperty("batteryModel")
    public Integer getBatteryModel() {
        return batteryModel;
    }

    @JsonProperty("batteryModel")
    public void setBatteryModel(Integer batteryModel) {
        this.batteryModel = batteryModel;
    }

    public Record withBatteryModel(Integer batteryModel) {
        this.batteryModel = batteryModel;
        return this;
    }

    @JsonProperty("bypassAcOnoffSet")
    public Double getBypassAcOnoffSet() {
        return bypassAcOnoffSet;
    }

    @JsonProperty("bypassAcOnoffSet")
    public void setBypassAcOnoffSet(Double bypassAcOnoffSet) {
        this.bypassAcOnoffSet = bypassAcOnoffSet;
    }

    public Record withBypassAcOnoffSet(Double bypassAcOnoffSet) {
        this.bypassAcOnoffSet = bypassAcOnoffSet;
        return this;
    }

    @JsonProperty("parallelOnoff01")
    public Double getParallelOnoff01() {
        return parallelOnoff01;
    }

    @JsonProperty("parallelOnoff01")
    public void setParallelOnoff01(Double parallelOnoff01) {
        this.parallelOnoff01 = parallelOnoff01;
    }

    public Record withParallelOnoff01(Double parallelOnoff01) {
        this.parallelOnoff01 = parallelOnoff01;
        return this;
    }

    @JsonProperty("parallelOnoff02")
    public Double getParallelOnoff02() {
        return parallelOnoff02;
    }

    @JsonProperty("parallelOnoff02")
    public void setParallelOnoff02(Double parallelOnoff02) {
        this.parallelOnoff02 = parallelOnoff02;
    }

    public Record withParallelOnoff02(Double parallelOnoff02) {
        this.parallelOnoff02 = parallelOnoff02;
        return this;
    }

    @JsonProperty("etotal")
    public Double getEtotal() {
        return etotal;
    }

    @JsonProperty("etotal")
    public void setEtotal(Double etotal) {
        this.etotal = etotal;
    }

    public Record withEtotal(Double etotal) {
        this.etotal = etotal;
        return this;
    }

    @JsonProperty("etoday")
    public Double getEtoday() {
        return etoday;
    }

    @JsonProperty("etoday")
    public void setEtoday(Double etoday) {
        this.etoday = etoday;
    }

    public Record withEtoday(Double etoday) {
        this.etoday = etoday;
        return this;
    }

    @JsonProperty("psum")
    public Double getPsum() {
        return psum;
    }

    @JsonProperty("psum")
    public void setPsum(Double psum) {
        this.psum = psum;
    }

    public Record withPsum(Double psum) {
        this.psum = psum;
        return this;
    }

    @JsonProperty("psumCal")
    public Double getPsumCal() {
        return psumCal;
    }

    @JsonProperty("psumCal")
    public void setPsumCal(Double psumCal) {
        this.psumCal = psumCal;
    }

    public Record withPsumCal(Double psumCal) {
        this.psumCal = psumCal;
        return this;
    }

    @JsonProperty("etotal1")
    public Double getEtotal1() {
        return etotal1;
    }

    @JsonProperty("etotal1")
    public void setEtotal1(Double etotal1) {
        this.etotal1 = etotal1;
    }

    public Record withEtotal1(Double etotal1) {
        this.etotal1 = etotal1;
        return this;
    }

    @JsonProperty("offlineLongStr")
    public String getOfflineLongStr() {
        return offlineLongStr;
    }

    @JsonProperty("offlineLongStr")
    public void setOfflineLongStr(String offlineLongStr) {
        this.offlineLongStr = offlineLongStr;
    }

    public Record withOfflineLongStr(String offlineLongStr) {
        this.offlineLongStr = offlineLongStr;
        return this;
    }

    @JsonProperty("etoday1")
    public Double getEtoday1() {
        return etoday1;
    }

    @JsonProperty("etoday1")
    public void setEtoday1(Double etoday1) {
        this.etoday1 = etoday1;
    }

    public Record withEtoday1(Double etoday1) {
        this.etoday1 = etoday1;
        return this;
    }

    @JsonProperty("etotalStr")
    public String getEtotalStr() {
        return etotalStr;
    }

    @JsonProperty("etotalStr")
    public void setEtotalStr(String etotalStr) {
        this.etotalStr = etotalStr;
    }

    public Record withEtotalStr(String etotalStr) {
        this.etotalStr = etotalStr;
        return this;
    }

    @JsonProperty("etodayStr")
    public String getEtodayStr() {
        return etodayStr;
    }

    @JsonProperty("etodayStr")
    public void setEtodayStr(String etodayStr) {
        this.etodayStr = etodayStr;
    }

    public Record withEtodayStr(String etodayStr) {
        this.etodayStr = etodayStr;
        return this;
    }

    @JsonProperty("psumStr")
    public String getPsumStr() {
        return psumStr;
    }

    @JsonProperty("psumStr")
    public void setPsumStr(String psumStr) {
        this.psumStr = psumStr;
    }

    public Record withPsumStr(String psumStr) {
        this.psumStr = psumStr;
        return this;
    }

    @JsonProperty("psumCalStr")
    public String getPsumCalStr() {
        return psumCalStr;
    }

    @JsonProperty("psumCalStr")
    public void setPsumCalStr(String psumCalStr) {
        this.psumCalStr = psumCalStr;
    }

    public Record withPsumCalStr(String psumCalStr) {
        this.psumCalStr = psumCalStr;
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

    public Record withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

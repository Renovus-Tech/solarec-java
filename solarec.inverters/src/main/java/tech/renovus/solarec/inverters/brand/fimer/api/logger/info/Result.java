
package tech.renovus.solarec.inverters.brand.fimer.api.logger.info;

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
    "loggerEntityID",
    "loggerName",
    "loggerDescription",
    "loggerState",
    "loggerSerialNumber",
    "loggerMACAddress",
    "loggerManufacturer",
    "loggerModel",
    "loggerFWVersion",
    "loggerFirstReportedDate",
    "plantEntityID"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("loggerEntityID")
    private Integer loggerEntityID;
    @JsonProperty("loggerName")
    private String loggerName;
    @JsonProperty("loggerDescription")
    private String loggerDescription;
    @JsonProperty("loggerState")
    private String loggerState;
    @JsonProperty("loggerSerialNumber")
    private String loggerSerialNumber;
    @JsonProperty("loggerMACAddress")
    private String loggerMACAddress;
    @JsonProperty("loggerManufacturer")
    private String loggerManufacturer;
    @JsonProperty("loggerModel")
    private String loggerModel;
    @JsonProperty("loggerFWVersion")
    private String loggerFWVersion;
    @JsonProperty("loggerFirstReportedDate")
    private String loggerFirstReportedDate;
    @JsonProperty("plantEntityID")
    private Integer plantEntityID;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("loggerEntityID")
    public Integer getLoggerEntityID() {
        return loggerEntityID;
    }

    @JsonProperty("loggerEntityID")
    public void setLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
    }

    public Result withLoggerEntityID(Integer loggerEntityID) {
        this.loggerEntityID = loggerEntityID;
        return this;
    }

    @JsonProperty("loggerName")
    public String getLoggerName() {
        return loggerName;
    }

    @JsonProperty("loggerName")
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public Result withLoggerName(String loggerName) {
        this.loggerName = loggerName;
        return this;
    }

    @JsonProperty("loggerDescription")
    public String getLoggerDescription() {
        return loggerDescription;
    }

    @JsonProperty("loggerDescription")
    public void setLoggerDescription(String loggerDescription) {
        this.loggerDescription = loggerDescription;
    }

    public Result withLoggerDescription(String loggerDescription) {
        this.loggerDescription = loggerDescription;
        return this;
    }

    @JsonProperty("loggerState")
    public String getLoggerState() {
        return loggerState;
    }

    @JsonProperty("loggerState")
    public void setLoggerState(String loggerState) {
        this.loggerState = loggerState;
    }

    public Result withLoggerState(String loggerState) {
        this.loggerState = loggerState;
        return this;
    }

    @JsonProperty("loggerSerialNumber")
    public String getLoggerSerialNumber() {
        return loggerSerialNumber;
    }

    @JsonProperty("loggerSerialNumber")
    public void setLoggerSerialNumber(String loggerSerialNumber) {
        this.loggerSerialNumber = loggerSerialNumber;
    }

    public Result withLoggerSerialNumber(String loggerSerialNumber) {
        this.loggerSerialNumber = loggerSerialNumber;
        return this;
    }

    @JsonProperty("loggerMACAddress")
    public String getLoggerMACAddress() {
        return loggerMACAddress;
    }

    @JsonProperty("loggerMACAddress")
    public void setLoggerMACAddress(String loggerMACAddress) {
        this.loggerMACAddress = loggerMACAddress;
    }

    public Result withLoggerMACAddress(String loggerMACAddress) {
        this.loggerMACAddress = loggerMACAddress;
        return this;
    }

    @JsonProperty("loggerManufacturer")
    public String getLoggerManufacturer() {
        return loggerManufacturer;
    }

    @JsonProperty("loggerManufacturer")
    public void setLoggerManufacturer(String loggerManufacturer) {
        this.loggerManufacturer = loggerManufacturer;
    }

    public Result withLoggerManufacturer(String loggerManufacturer) {
        this.loggerManufacturer = loggerManufacturer;
        return this;
    }

    @JsonProperty("loggerModel")
    public String getLoggerModel() {
        return loggerModel;
    }

    @JsonProperty("loggerModel")
    public void setLoggerModel(String loggerModel) {
        this.loggerModel = loggerModel;
    }

    public Result withLoggerModel(String loggerModel) {
        this.loggerModel = loggerModel;
        return this;
    }

    @JsonProperty("loggerFWVersion")
    public String getLoggerFWVersion() {
        return loggerFWVersion;
    }

    @JsonProperty("loggerFWVersion")
    public void setLoggerFWVersion(String loggerFWVersion) {
        this.loggerFWVersion = loggerFWVersion;
    }

    public Result withLoggerFWVersion(String loggerFWVersion) {
        this.loggerFWVersion = loggerFWVersion;
        return this;
    }

    @JsonProperty("loggerFirstReportedDate")
    public String getLoggerFirstReportedDate() {
        return loggerFirstReportedDate;
    }

    @JsonProperty("loggerFirstReportedDate")
    public void setLoggerFirstReportedDate(String loggerFirstReportedDate) {
        this.loggerFirstReportedDate = loggerFirstReportedDate;
    }

    public Result withLoggerFirstReportedDate(String loggerFirstReportedDate) {
        this.loggerFirstReportedDate = loggerFirstReportedDate;
        return this;
    }

    @JsonProperty("plantEntityID")
    public Integer getPlantEntityID() {
        return plantEntityID;
    }

    @JsonProperty("plantEntityID")
    public void setPlantEntityID(Integer plantEntityID) {
        this.plantEntityID = plantEntityID;
    }

    public Result withPlantEntityID(Integer plantEntityID) {
        this.plantEntityID = plantEntityID;
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

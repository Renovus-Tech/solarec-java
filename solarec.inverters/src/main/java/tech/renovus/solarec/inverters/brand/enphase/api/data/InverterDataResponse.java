
package tech.renovus.solarec.inverters.brand.enphase.api.data;

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
    "system_id",
    "serial_number",
    "granularity",
    "total_devices",
    "start_at",
    "end_at",
    "items",
    "intervals",
    "last_reported_time",
    "last_reported_soc"
})
@Generated("jsonschema2pojo")
public class InverterDataResponse {

    @JsonProperty("system_id")
    private Integer systemId;
    @JsonProperty("serial_number")
    private String serialNumber;
    @JsonProperty("granularity")
    private String granularity;
    @JsonProperty("total_devices")
    private Integer totalDevices;
    @JsonProperty("start_at")
    private Integer startAt;
    @JsonProperty("end_at")
    private Integer endAt;
    @JsonProperty("items")
    private String items;
    @JsonProperty("intervals")
    private List<Interval> intervals;
    @JsonProperty("last_reported_time")
    private Integer lastReportedTime;
    @JsonProperty("last_reported_soc")
    private String lastReportedSoc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("system_id")
    public Integer getSystemId() {
        return systemId;
    }

    @JsonProperty("system_id")
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public InverterDataResponse withSystemId(Integer systemId) {
        this.systemId = systemId;
        return this;
    }

    @JsonProperty("serial_number")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serial_number")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public InverterDataResponse withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @JsonProperty("granularity")
    public String getGranularity() {
        return granularity;
    }

    @JsonProperty("granularity")
    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public InverterDataResponse withGranularity(String granularity) {
        this.granularity = granularity;
        return this;
    }

    @JsonProperty("total_devices")
    public Integer getTotalDevices() {
        return totalDevices;
    }

    @JsonProperty("total_devices")
    public void setTotalDevices(Integer totalDevices) {
        this.totalDevices = totalDevices;
    }

    public InverterDataResponse withTotalDevices(Integer totalDevices) {
        this.totalDevices = totalDevices;
        return this;
    }

    @JsonProperty("start_at")
    public Integer getStartAt() {
        return startAt;
    }

    @JsonProperty("start_at")
    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public InverterDataResponse withStartAt(Integer startAt) {
        this.startAt = startAt;
        return this;
    }

    @JsonProperty("end_at")
    public Integer getEndAt() {
        return endAt;
    }

    @JsonProperty("end_at")
    public void setEndAt(Integer endAt) {
        this.endAt = endAt;
    }

    public InverterDataResponse withEndAt(Integer endAt) {
        this.endAt = endAt;
        return this;
    }

    @JsonProperty("items")
    public String getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(String items) {
        this.items = items;
    }

    public InverterDataResponse withItems(String items) {
        this.items = items;
        return this;
    }

    @JsonProperty("intervals")
    public List<Interval> getIntervals() {
        return intervals;
    }

    @JsonProperty("intervals")
    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public InverterDataResponse withIntervals(List<Interval> intervals) {
        this.intervals = intervals;
        return this;
    }

    @JsonProperty("last_reported_time")
    public Integer getLastReportedTime() {
        return lastReportedTime;
    }

    @JsonProperty("last_reported_time")
    public void setLastReportedTime(Integer lastReportedTime) {
        this.lastReportedTime = lastReportedTime;
    }

    public InverterDataResponse withLastReportedTime(Integer lastReportedTime) {
        this.lastReportedTime = lastReportedTime;
        return this;
    }

    @JsonProperty("last_reported_soc")
    public String getLastReportedSoc() {
        return lastReportedSoc;
    }

    @JsonProperty("last_reported_soc")
    public void setLastReportedSoc(String lastReportedSoc) {
        this.lastReportedSoc = lastReportedSoc;
    }

    public InverterDataResponse withLastReportedSoc(String lastReportedSoc) {
        this.lastReportedSoc = lastReportedSoc;
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

    public InverterDataResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

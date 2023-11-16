
package tech.renovus.solarec.weather.stormglass.weather;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cost",
    "dailyQuota",
    "end",
    "lat",
    "lng",
    "params",
    "requestCount",
    "start"
})
@Generated("jsonschema2pojo")
public class Meta {

    @JsonProperty("cost")
    private Integer cost;
    @JsonProperty("dailyQuota")
    private Integer dailyQuota;
    @JsonProperty("end")
    private String end;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;
    @JsonProperty("params")
    private List<String> params = null;
    @JsonProperty("requestCount")
    private Integer requestCount;
    @JsonProperty("start")
    private String start;

    @JsonProperty("cost")
    public Integer getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Meta withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    @JsonProperty("dailyQuota")
    public Integer getDailyQuota() {
        return dailyQuota;
    }

    @JsonProperty("dailyQuota")
    public void setDailyQuota(Integer dailyQuota) {
        this.dailyQuota = dailyQuota;
    }

    public Meta withDailyQuota(Integer dailyQuota) {
        this.dailyQuota = dailyQuota;
        return this;
    }

    @JsonProperty("end")
    public String getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(String end) {
        this.end = end;
    }

    public Meta withEnd(String end) {
        this.end = end;
        return this;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Meta withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    @JsonProperty("lng")
    public Double getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Meta withLng(Double lng) {
        this.lng = lng;
        return this;
    }

    @JsonProperty("params")
    public List<String> getParams() {
        return params;
    }

    @JsonProperty("params")
    public void setParams(List<String> params) {
        this.params = params;
    }

    public Meta withParams(List<String> params) {
        this.params = params;
        return this;
    }

    @JsonProperty("requestCount")
    public Integer getRequestCount() {
        return requestCount;
    }

    @JsonProperty("requestCount")
    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Meta withRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
        return this;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    public Meta withStart(String start) {
        this.start = start;
        return this;
    }

}

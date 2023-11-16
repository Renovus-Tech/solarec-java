
package tech.renovus.solarec.weather.meteoblue.weather;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "windspeed_80m_consensus",
    "windspeed_80m_p95exceedence",
    "windspeed_80m_p90exceedence",
    "windspeed_80m_p85exceedence",
    "windspeed_80m_p80exceedence",
    "windspeed_80m_p75exceedence",
    "windspeed_80m_p70exceedence",
    "windspeed_80m_p60exceedence",
    "windspeed_80m_p50exceedence",
    "windspeed_80m_p40exceedence",
    "windspeed_80m_p30exceedence",
    "windspeed_80m_p25exceedence",
    "windspeed_80m_p20exceedence",
    "windspeed_80m_p15exceedence",
    "windspeed_80m_p10exceedence",
    "windspeed_80m_p5exceedence",
    "windspeed_80m_max",
    "windspeed_80m_min"
})
@Generated("jsonschema2pojo")
public class Trend1h {

    @JsonProperty("time")
    private List<String> time = null;
    @JsonProperty("windspeed_80m_consensus")
    private List<Double> windspeed80mConsensus = null;
    @JsonProperty("windspeed_80m_p95exceedence")
    private List<Double> windspeed80mP95exceedence = null;
    @JsonProperty("windspeed_80m_p90exceedence")
    private List<Double> windspeed80mP90exceedence = null;
    @JsonProperty("windspeed_80m_p85exceedence")
    private List<Double> windspeed80mP85exceedence = null;
    @JsonProperty("windspeed_80m_p80exceedence")
    private List<Double> windspeed80mP80exceedence = null;
    @JsonProperty("windspeed_80m_p75exceedence")
    private List<Double> windspeed80mP75exceedence = null;
    @JsonProperty("windspeed_80m_p70exceedence")
    private List<Double> windspeed80mP70exceedence = null;
    @JsonProperty("windspeed_80m_p60exceedence")
    private List<Double> windspeed80mP60exceedence = null;
    @JsonProperty("windspeed_80m_p50exceedence")
    private List<Double> windspeed80mP50exceedence = null;
    @JsonProperty("windspeed_80m_p40exceedence")
    private List<Double> windspeed80mP40exceedence = null;
    @JsonProperty("windspeed_80m_p30exceedence")
    private List<Double> windspeed80mP30exceedence = null;
    @JsonProperty("windspeed_80m_p25exceedence")
    private List<Double> windspeed80mP25exceedence = null;
    @JsonProperty("windspeed_80m_p20exceedence")
    private List<Double> windspeed80mP20exceedence = null;
    @JsonProperty("windspeed_80m_p15exceedence")
    private List<Double> windspeed80mP15exceedence = null;
    @JsonProperty("windspeed_80m_p10exceedence")
    private List<Double> windspeed80mP10exceedence = null;
    @JsonProperty("windspeed_80m_p5exceedence")
    private List<Double> windspeed80mP5exceedence = null;
    @JsonProperty("windspeed_80m_max")
    private List<Double> windspeed80mMax = null;
    @JsonProperty("windspeed_80m_min")
    private List<Double> windspeed80mMin = null;

    @JsonProperty("time")
    public List<String> getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(List<String> time) {
        this.time = time;
    }

    public Trend1h withTime(List<String> time) {
        this.time = time;
        return this;
    }

    @JsonProperty("windspeed_80m_consensus")
    public List<Double> getWindspeed80mConsensus() {
        return windspeed80mConsensus;
    }

    @JsonProperty("windspeed_80m_consensus")
    public void setWindspeed80mConsensus(List<Double> windspeed80mConsensus) {
        this.windspeed80mConsensus = windspeed80mConsensus;
    }

    public Trend1h withWindspeed80mConsensus(List<Double> windspeed80mConsensus) {
        this.windspeed80mConsensus = windspeed80mConsensus;
        return this;
    }

    @JsonProperty("windspeed_80m_p95exceedence")
    public List<Double> getWindspeed80mP95exceedence() {
        return windspeed80mP95exceedence;
    }

    @JsonProperty("windspeed_80m_p95exceedence")
    public void setWindspeed80mP95exceedence(List<Double> windspeed80mP95exceedence) {
        this.windspeed80mP95exceedence = windspeed80mP95exceedence;
    }

    public Trend1h withWindspeed80mP95exceedence(List<Double> windspeed80mP95exceedence) {
        this.windspeed80mP95exceedence = windspeed80mP95exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p90exceedence")
    public List<Double> getWindspeed80mP90exceedence() {
        return windspeed80mP90exceedence;
    }

    @JsonProperty("windspeed_80m_p90exceedence")
    public void setWindspeed80mP90exceedence(List<Double> windspeed80mP90exceedence) {
        this.windspeed80mP90exceedence = windspeed80mP90exceedence;
    }

    public Trend1h withWindspeed80mP90exceedence(List<Double> windspeed80mP90exceedence) {
        this.windspeed80mP90exceedence = windspeed80mP90exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p85exceedence")
    public List<Double> getWindspeed80mP85exceedence() {
        return windspeed80mP85exceedence;
    }

    @JsonProperty("windspeed_80m_p85exceedence")
    public void setWindspeed80mP85exceedence(List<Double> windspeed80mP85exceedence) {
        this.windspeed80mP85exceedence = windspeed80mP85exceedence;
    }

    public Trend1h withWindspeed80mP85exceedence(List<Double> windspeed80mP85exceedence) {
        this.windspeed80mP85exceedence = windspeed80mP85exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p80exceedence")
    public List<Double> getWindspeed80mP80exceedence() {
        return windspeed80mP80exceedence;
    }

    @JsonProperty("windspeed_80m_p80exceedence")
    public void setWindspeed80mP80exceedence(List<Double> windspeed80mP80exceedence) {
        this.windspeed80mP80exceedence = windspeed80mP80exceedence;
    }

    public Trend1h withWindspeed80mP80exceedence(List<Double> windspeed80mP80exceedence) {
        this.windspeed80mP80exceedence = windspeed80mP80exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p75exceedence")
    public List<Double> getWindspeed80mP75exceedence() {
        return windspeed80mP75exceedence;
    }

    @JsonProperty("windspeed_80m_p75exceedence")
    public void setWindspeed80mP75exceedence(List<Double> windspeed80mP75exceedence) {
        this.windspeed80mP75exceedence = windspeed80mP75exceedence;
    }

    public Trend1h withWindspeed80mP75exceedence(List<Double> windspeed80mP75exceedence) {
        this.windspeed80mP75exceedence = windspeed80mP75exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p70exceedence")
    public List<Double> getWindspeed80mP70exceedence() {
        return windspeed80mP70exceedence;
    }

    @JsonProperty("windspeed_80m_p70exceedence")
    public void setWindspeed80mP70exceedence(List<Double> windspeed80mP70exceedence) {
        this.windspeed80mP70exceedence = windspeed80mP70exceedence;
    }

    public Trend1h withWindspeed80mP70exceedence(List<Double> windspeed80mP70exceedence) {
        this.windspeed80mP70exceedence = windspeed80mP70exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p60exceedence")
    public List<Double> getWindspeed80mP60exceedence() {
        return windspeed80mP60exceedence;
    }

    @JsonProperty("windspeed_80m_p60exceedence")
    public void setWindspeed80mP60exceedence(List<Double> windspeed80mP60exceedence) {
        this.windspeed80mP60exceedence = windspeed80mP60exceedence;
    }

    public Trend1h withWindspeed80mP60exceedence(List<Double> windspeed80mP60exceedence) {
        this.windspeed80mP60exceedence = windspeed80mP60exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p50exceedence")
    public List<Double> getWindspeed80mP50exceedence() {
        return windspeed80mP50exceedence;
    }

    @JsonProperty("windspeed_80m_p50exceedence")
    public void setWindspeed80mP50exceedence(List<Double> windspeed80mP50exceedence) {
        this.windspeed80mP50exceedence = windspeed80mP50exceedence;
    }

    public Trend1h withWindspeed80mP50exceedence(List<Double> windspeed80mP50exceedence) {
        this.windspeed80mP50exceedence = windspeed80mP50exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p40exceedence")
    public List<Double> getWindspeed80mP40exceedence() {
        return windspeed80mP40exceedence;
    }

    @JsonProperty("windspeed_80m_p40exceedence")
    public void setWindspeed80mP40exceedence(List<Double> windspeed80mP40exceedence) {
        this.windspeed80mP40exceedence = windspeed80mP40exceedence;
    }

    public Trend1h withWindspeed80mP40exceedence(List<Double> windspeed80mP40exceedence) {
        this.windspeed80mP40exceedence = windspeed80mP40exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p30exceedence")
    public List<Double> getWindspeed80mP30exceedence() {
        return windspeed80mP30exceedence;
    }

    @JsonProperty("windspeed_80m_p30exceedence")
    public void setWindspeed80mP30exceedence(List<Double> windspeed80mP30exceedence) {
        this.windspeed80mP30exceedence = windspeed80mP30exceedence;
    }

    public Trend1h withWindspeed80mP30exceedence(List<Double> windspeed80mP30exceedence) {
        this.windspeed80mP30exceedence = windspeed80mP30exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p25exceedence")
    public List<Double> getWindspeed80mP25exceedence() {
        return windspeed80mP25exceedence;
    }

    @JsonProperty("windspeed_80m_p25exceedence")
    public void setWindspeed80mP25exceedence(List<Double> windspeed80mP25exceedence) {
        this.windspeed80mP25exceedence = windspeed80mP25exceedence;
    }

    public Trend1h withWindspeed80mP25exceedence(List<Double> windspeed80mP25exceedence) {
        this.windspeed80mP25exceedence = windspeed80mP25exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p20exceedence")
    public List<Double> getWindspeed80mP20exceedence() {
        return windspeed80mP20exceedence;
    }

    @JsonProperty("windspeed_80m_p20exceedence")
    public void setWindspeed80mP20exceedence(List<Double> windspeed80mP20exceedence) {
        this.windspeed80mP20exceedence = windspeed80mP20exceedence;
    }

    public Trend1h withWindspeed80mP20exceedence(List<Double> windspeed80mP20exceedence) {
        this.windspeed80mP20exceedence = windspeed80mP20exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p15exceedence")
    public List<Double> getWindspeed80mP15exceedence() {
        return windspeed80mP15exceedence;
    }

    @JsonProperty("windspeed_80m_p15exceedence")
    public void setWindspeed80mP15exceedence(List<Double> windspeed80mP15exceedence) {
        this.windspeed80mP15exceedence = windspeed80mP15exceedence;
    }

    public Trend1h withWindspeed80mP15exceedence(List<Double> windspeed80mP15exceedence) {
        this.windspeed80mP15exceedence = windspeed80mP15exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p10exceedence")
    public List<Double> getWindspeed80mP10exceedence() {
        return windspeed80mP10exceedence;
    }

    @JsonProperty("windspeed_80m_p10exceedence")
    public void setWindspeed80mP10exceedence(List<Double> windspeed80mP10exceedence) {
        this.windspeed80mP10exceedence = windspeed80mP10exceedence;
    }

    public Trend1h withWindspeed80mP10exceedence(List<Double> windspeed80mP10exceedence) {
        this.windspeed80mP10exceedence = windspeed80mP10exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_p5exceedence")
    public List<Double> getWindspeed80mP5exceedence() {
        return windspeed80mP5exceedence;
    }

    @JsonProperty("windspeed_80m_p5exceedence")
    public void setWindspeed80mP5exceedence(List<Double> windspeed80mP5exceedence) {
        this.windspeed80mP5exceedence = windspeed80mP5exceedence;
    }

    public Trend1h withWindspeed80mP5exceedence(List<Double> windspeed80mP5exceedence) {
        this.windspeed80mP5exceedence = windspeed80mP5exceedence;
        return this;
    }

    @JsonProperty("windspeed_80m_max")
    public List<Double> getWindspeed80mMax() {
        return windspeed80mMax;
    }

    @JsonProperty("windspeed_80m_max")
    public void setWindspeed80mMax(List<Double> windspeed80mMax) {
        this.windspeed80mMax = windspeed80mMax;
    }

    public Trend1h withWindspeed80mMax(List<Double> windspeed80mMax) {
        this.windspeed80mMax = windspeed80mMax;
        return this;
    }

    @JsonProperty("windspeed_80m_min")
    public List<Double> getWindspeed80mMin() {
        return windspeed80mMin;
    }

    @JsonProperty("windspeed_80m_min")
    public void setWindspeed80mMin(List<Double> windspeed80mMin) {
        this.windspeed80mMin = windspeed80mMin;
    }

    public Trend1h withWindspeed80mMin(List<Double> windspeed80mMin) {
        this.windspeed80mMin = windspeed80mMin;
        return this;
    }

}

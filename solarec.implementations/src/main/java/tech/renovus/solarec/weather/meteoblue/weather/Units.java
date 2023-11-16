
package tech.renovus.solarec.weather.meteoblue.weather;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "precipitation_probability",
    "pressure",
    "relativehumidity",
    "co",
    "precipitation",
    "temperature",
    "windspeed",
    "winddirection"
})
@Generated("jsonschema2pojo")
public class Units {

    @JsonProperty("time")
    private String time;
    @JsonProperty("precipitation_probability")
    private String precipitationProbability;
    @JsonProperty("pressure")
    private String pressure;
    @JsonProperty("relativehumidity")
    private String relativehumidity;
    @JsonProperty("co")
    private String co;
    @JsonProperty("precipitation")
    private String precipitation;
    @JsonProperty("temperature")
    private String temperature;
    @JsonProperty("windspeed")
    private String windspeed;
    @JsonProperty("winddirection")
    private String winddirection;

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    public Units withTime(String time) {
        this.time = time;
        return this;
    }

    @JsonProperty("precipitation_probability")
    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public Units withPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
        return this;
    }

    @JsonProperty("pressure")
    public String getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public Units withPressure(String pressure) {
        this.pressure = pressure;
        return this;
    }

    @JsonProperty("relativehumidity")
    public String getRelativehumidity() {
        return relativehumidity;
    }

    @JsonProperty("relativehumidity")
    public void setRelativehumidity(String relativehumidity) {
        this.relativehumidity = relativehumidity;
    }

    public Units withRelativehumidity(String relativehumidity) {
        this.relativehumidity = relativehumidity;
        return this;
    }

    @JsonProperty("co")
    public String getCo() {
        return co;
    }

    @JsonProperty("co")
    public void setCo(String co) {
        this.co = co;
    }

    public Units withCo(String co) {
        this.co = co;
        return this;
    }

    @JsonProperty("precipitation")
    public String getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public Units withPrecipitation(String precipitation) {
        this.precipitation = precipitation;
        return this;
    }

    @JsonProperty("temperature")
    public String getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Units withTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    @JsonProperty("windspeed")
    public String getWindspeed() {
        return windspeed;
    }

    @JsonProperty("windspeed")
    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public Units withWindspeed(String windspeed) {
        this.windspeed = windspeed;
        return this;
    }

    @JsonProperty("winddirection")
    public String getWinddirection() {
        return winddirection;
    }

    @JsonProperty("winddirection")
    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public Units withWinddirection(String winddirection) {
        this.winddirection = winddirection;
        return this;
    }

}


package tech.renovus.solarec.weather.stormglass.weather;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "airTemperature100m",
    "humidity",
    "pressure",
    "time",
    "windDirection100m",
    "windSpeed100m"
})
@Generated("jsonschema2pojo")
public class Hour {

    @JsonProperty("airTemperature100m")
    private AirTemperature100m airTemperature100m;
    @JsonProperty("humidity")
    private Humidity humidity;
    @JsonProperty("pressure")
    private Pressure pressure;
    @JsonProperty("time")
    private String time;
    @JsonProperty("windDirection100m")
    private WindDirection100m windDirection100m;
    @JsonProperty("windSpeed100m")
    private WindSpeed100m windSpeed100m;

    @JsonProperty("airTemperature100m")
    public AirTemperature100m getAirTemperature100m() {
        return airTemperature100m;
    }

    @JsonProperty("airTemperature100m")
    public void setAirTemperature100m(AirTemperature100m airTemperature100m) {
        this.airTemperature100m = airTemperature100m;
    }

    public Hour withAirTemperature100m(AirTemperature100m airTemperature100m) {
        this.airTemperature100m = airTemperature100m;
        return this;
    }

    @JsonProperty("humidity")
    public Humidity getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Hour withHumidity(Humidity humidity) {
        this.humidity = humidity;
        return this;
    }

    @JsonProperty("pressure")
    public Pressure getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Hour withPressure(Pressure pressure) {
        this.pressure = pressure;
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

    public Hour withTime(String time) {
        this.time = time;
        return this;
    }

    @JsonProperty("windDirection100m")
    public WindDirection100m getWindDirection100m() {
        return windDirection100m;
    }

    @JsonProperty("windDirection100m")
    public void setWindDirection100m(WindDirection100m windDirection100m) {
        this.windDirection100m = windDirection100m;
    }

    public Hour withWindDirection100m(WindDirection100m windDirection100m) {
        this.windDirection100m = windDirection100m;
        return this;
    }

    @JsonProperty("windSpeed100m")
    public WindSpeed100m getWindSpeed100m() {
        return windSpeed100m;
    }

    @JsonProperty("windSpeed100m")
    public void setWindSpeed100m(WindSpeed100m windSpeed100m) {
        this.windSpeed100m = windSpeed100m;
    }

    public Hour withWindSpeed100m(WindSpeed100m windSpeed100m) {
        this.windSpeed100m = windSpeed100m;
        return this;
    }

}

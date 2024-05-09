package tech.renovus.solarec.weather.openmeteo.api;

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
"latitude",
"longitude",
"generationtime_ms",
"utc_offset_seconds",
"timezone",
"timezone_abbreviation",
"elevation",
"hourly_units",
"hourly"
})
@Generated("jsonschema2pojo")
public class WeatherData {

@JsonProperty("latitude")
private Double latitude;
@JsonProperty("longitude")
private Double longitude;
@JsonProperty("generationtime_ms")
private Double generationtimeMs;
@JsonProperty("utc_offset_seconds")
private Integer utcOffsetSeconds;
@JsonProperty("timezone")
private String timezone;
@JsonProperty("timezone_abbreviation")
private String timezoneAbbreviation;
@JsonProperty("elevation")
private Double elevation;
@JsonProperty("hourly_units")
private HourlyUnits hourlyUnits;
@JsonProperty("hourly")
private Hourly hourly;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("latitude")
public Double getLatitude() {
return latitude;
}

@JsonProperty("latitude")
public void setLatitude(Double latitude) {
this.latitude = latitude;
}

public WeatherData withLatitude(Double latitude) {
this.latitude = latitude;
return this;
}

@JsonProperty("longitude")
public Double getLongitude() {
return longitude;
}

@JsonProperty("longitude")
public void setLongitude(Double longitude) {
this.longitude = longitude;
}

public WeatherData withLongitude(Double longitude) {
this.longitude = longitude;
return this;
}

@JsonProperty("generationtime_ms")
public Double getGenerationtimeMs() {
return generationtimeMs;
}

@JsonProperty("generationtime_ms")
public void setGenerationtimeMs(Double generationtimeMs) {
this.generationtimeMs = generationtimeMs;
}

public WeatherData withGenerationtimeMs(Double generationtimeMs) {
this.generationtimeMs = generationtimeMs;
return this;
}

@JsonProperty("utc_offset_seconds")
public Integer getUtcOffsetSeconds() {
return utcOffsetSeconds;
}

@JsonProperty("utc_offset_seconds")
public void setUtcOffsetSeconds(Integer utcOffsetSeconds) {
this.utcOffsetSeconds = utcOffsetSeconds;
}

public WeatherData withUtcOffsetSeconds(Integer utcOffsetSeconds) {
this.utcOffsetSeconds = utcOffsetSeconds;
return this;
}

@JsonProperty("timezone")
public String getTimezone() {
return timezone;
}

@JsonProperty("timezone")
public void setTimezone(String timezone) {
this.timezone = timezone;
}

public WeatherData withTimezone(String timezone) {
this.timezone = timezone;
return this;
}

@JsonProperty("timezone_abbreviation")
public String getTimezoneAbbreviation() {
return timezoneAbbreviation;
}

@JsonProperty("timezone_abbreviation")
public void setTimezoneAbbreviation(String timezoneAbbreviation) {
this.timezoneAbbreviation = timezoneAbbreviation;
}

public WeatherData withTimezoneAbbreviation(String timezoneAbbreviation) {
this.timezoneAbbreviation = timezoneAbbreviation;
return this;
}

@JsonProperty("elevation")
public Double getElevation() {
return elevation;
}

@JsonProperty("elevation")
public void setElevation(Double elevation) {
this.elevation = elevation;
}

public WeatherData withElevation(Double elevation) {
this.elevation = elevation;
return this;
}

@JsonProperty("hourly_units")
public HourlyUnits getHourlyUnits() {
return hourlyUnits;
}

@JsonProperty("hourly_units")
public void setHourlyUnits(HourlyUnits hourlyUnits) {
this.hourlyUnits = hourlyUnits;
}

public WeatherData withHourlyUnits(HourlyUnits hourlyUnits) {
this.hourlyUnits = hourlyUnits;
return this;
}

@JsonProperty("hourly")
public Hourly getHourly() {
return hourly;
}

@JsonProperty("hourly")
public void setHourly(Hourly hourly) {
this.hourly = hourly;
}

public WeatherData withHourly(Hourly hourly) {
this.hourly = hourly;
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

public WeatherData withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
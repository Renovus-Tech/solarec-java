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
"time",
"temperature_2m",
"direct_radiation",
"cloud_cover",
"precipitation"
})
@Generated("jsonschema2pojo")
public class HourlyUnits {

@JsonProperty("time")
private String time;
@JsonProperty("temperature_2m")
private String temperature2m;
@JsonProperty("direct_radiation")
private String directRadiation;
@JsonProperty("cloud_cover")
private String cloudCover;
@JsonProperty("precipitation")
private String precipitation;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("time")
public String getTime() {
return time;
}

@JsonProperty("time")
public void setTime(String time) {
this.time = time;
}

public HourlyUnits withTime(String time) {
this.time = time;
return this;
}

@JsonProperty("temperature_2m")
public String getTemperature2m() {
return temperature2m;
}

@JsonProperty("temperature_2m")
public void setTemperature2m(String temperature2m) {
this.temperature2m = temperature2m;
}

public HourlyUnits withTemperature2m(String temperature2m) {
this.temperature2m = temperature2m;
return this;
}

@JsonProperty("direct_radiation")
public String getDirectRadiation() {
return directRadiation;
}

@JsonProperty("direct_radiation")
public void setDirectRadiation(String directRadiation) {
this.directRadiation = directRadiation;
}

public HourlyUnits withDirectRadiation(String directRadiation) {
this.directRadiation = directRadiation;
return this;
}

@JsonProperty("cloud_cover")
public String getCloudCover() {
return cloudCover;
}

@JsonProperty("cloud_cover")
public void setCloudCover(String cloudCover) {
this.cloudCover = cloudCover;
}

public HourlyUnits withCloudCover(String cloudCover) {
this.cloudCover = cloudCover;
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

public HourlyUnits withPrecipitation(String precipitation) {
this.precipitation = precipitation;
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

public HourlyUnits withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
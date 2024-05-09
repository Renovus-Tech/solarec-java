package tech.renovus.solarec.weather.openmeteo.api;

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
"time",
"temperature_2m",
"direct_radiation",
"cloud_cover",
"precipitation"
})
@Generated("jsonschema2pojo")
public class Hourly {

@JsonProperty("time")
private List<String> time;
@JsonProperty("temperature_2m")
private List<Double> temperature2m;
@JsonProperty("direct_radiation")
private List<Double> directRadiation;
@JsonProperty("cloud_cover")
private List<Integer> cloudCover;
@JsonProperty("precipitation")
private List<Double> precipitation;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("time")
public List<String> getTime() {
return time;
}

@JsonProperty("time")
public void setTime(List<String> time) {
this.time = time;
}

public Hourly withTime(List<String> time) {
this.time = time;
return this;
}

@JsonProperty("temperature_2m")
public List<Double> getTemperature2m() {
return temperature2m;
}

@JsonProperty("temperature_2m")
public void setTemperature2m(List<Double> temperature2m) {
this.temperature2m = temperature2m;
}

public Hourly withTemperature2m(List<Double> temperature2m) {
this.temperature2m = temperature2m;
return this;
}

@JsonProperty("direct_radiation")
public List<Double> getDirectRadiation() {
return directRadiation;
}

@JsonProperty("direct_radiation")
public void setDirectRadiation(List<Double> directRadiation) {
this.directRadiation = directRadiation;
}

public Hourly withDirectRadiation(List<Double> directRadiation) {
this.directRadiation = directRadiation;
return this;
}

@JsonProperty("cloud_cover")
public List<Integer> getCloudCover() {
return cloudCover;
}

@JsonProperty("cloud_cover")
public void setCloudCover(List<Integer> cloudCover) {
this.cloudCover = cloudCover;
}

public Hourly withCloudCover(List<Integer> cloudCover) {
this.cloudCover = cloudCover;
return this;
}

@JsonProperty("precipitation")
public List<Double> getPrecipitation() {
return precipitation;
}

@JsonProperty("precipitation")
public void setPrecipitation(List<Double> precipitation) {
this.precipitation = precipitation;
}

public Hourly withPrecipitation(List<Double> precipitation) {
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

public Hourly withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
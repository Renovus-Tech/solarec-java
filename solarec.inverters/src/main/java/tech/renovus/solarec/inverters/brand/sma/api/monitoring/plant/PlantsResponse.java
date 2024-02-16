package tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant;

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

import tech.renovus.solarec.inverters.brand.sma.api.ErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"plants"
})
@Generated("jsonschema2pojo")
public class PlantsResponse extends ErrorResponse {

@JsonProperty("plants")
private List<Plant> plants;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("plants")
public List<Plant> getPlants() {
return plants;
}

@JsonProperty("plants")
public void setPlants(List<Plant> plants) {
this.plants = plants;
}

public PlantsResponse withPlants(List<Plant> plants) {
this.plants = plants;
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

public PlantsResponse withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
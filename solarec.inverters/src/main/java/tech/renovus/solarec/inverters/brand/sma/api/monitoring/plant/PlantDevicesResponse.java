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
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"plant",
"devices"
})
@Generated("jsonschema2pojo")
public class PlantDevicesResponse extends ErrorResponse {

@JsonProperty("plant")
private Plant plant;
@JsonProperty("devices")
private List<Device> devices;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("plant")
public Plant getPlant() {
return plant;
}

@JsonProperty("plant")
public void setPlant(Plant plant) {
this.plant = plant;
}

public PlantDevicesResponse withPlant(Plant plant) {
this.plant = plant;
return this;
}

@JsonProperty("devices")
public List<Device> getDevices() {
return devices;
}

@JsonProperty("devices")
public void setDevices(List<Device> devices) {
this.devices = devices;
}

public PlantDevicesResponse withDevices(List<Device> devices) {
this.devices = devices;
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

public PlantDevicesResponse withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
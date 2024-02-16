package tech.renovus.solarec.inverters.brand.sma.api.monitoring.device;

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
"deviceId",
"name",
"timezone",
"type",
"product",
"productId",
"vendor",
"isActive",
"serial",
"generatorPower"
})
@Generated("jsonschema2pojo")
public class Device {

@JsonProperty("deviceId")
private String deviceId;
@JsonProperty("name")
private String name;
@JsonProperty("timezone")
private String timezone;
@JsonProperty("type")
private String type;
@JsonProperty("product")
private String product;
@JsonProperty("productId")
private Integer productId;
@JsonProperty("vendor")
private String vendor;
@JsonProperty("isActive")
private Boolean isActive;
@JsonProperty("serial")
private String serial;
@JsonProperty("generatorPower")
private Double generatorPower;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("deviceId")
public String getDeviceId() {
return deviceId;
}

@JsonProperty("deviceId")
public void setDeviceId(String deviceId) {
this.deviceId = deviceId;
}

public Device withDeviceId(String deviceId) {
this.deviceId = deviceId;
return this;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

public Device withName(String name) {
this.name = name;
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

public Device withTimezone(String timezone) {
this.timezone = timezone;
return this;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

public Device withType(String type) {
this.type = type;
return this;
}

@JsonProperty("product")
public String getProduct() {
return product;
}

@JsonProperty("product")
public void setProduct(String product) {
this.product = product;
}

public Device withProduct(String product) {
this.product = product;
return this;
}

@JsonProperty("productId")
public Integer getProductId() {
return productId;
}

@JsonProperty("productId")
public void setProductId(Integer productId) {
this.productId = productId;
}

public Device withProductId(Integer productId) {
this.productId = productId;
return this;
}

@JsonProperty("vendor")
public String getVendor() {
return vendor;
}

@JsonProperty("vendor")
public void setVendor(String vendor) {
this.vendor = vendor;
}

public Device withVendor(String vendor) {
this.vendor = vendor;
return this;
}

@JsonProperty("isActive")
public Boolean getIsActive() {
return isActive;
}

@JsonProperty("isActive")
public void setIsActive(Boolean isActive) {
this.isActive = isActive;
}

public Device withIsActive(Boolean isActive) {
this.isActive = isActive;
return this;
}

@JsonProperty("serial")
public String getSerial() {
return serial;
}

@JsonProperty("serial")
public void setSerial(String serial) {
this.serial = serial;
}

public Device withSerial(String serial) {
this.serial = serial;
return this;
}

@JsonProperty("generatorPower")
public Double getGeneratorPower() {
return generatorPower;
}

@JsonProperty("generatorPower")
public void setGeneratorPower(Double generatorPower) {
this.generatorPower = generatorPower;
}

public Device withGeneratorPower(Double generatorPower) {
this.generatorPower = generatorPower;
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

public Device withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
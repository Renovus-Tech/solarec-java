
package tech.renovus.solarec.inverters.brand.enphase.api.devices;

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
@JsonPropertyOrder({ "id", "last_report_at", "name", "serial_number", "part_number", "sku", "model", "status", "active",
		"state", "config_type", "product_name" })
@Generated("jsonschema2pojo")
public class Meter {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("last_report_at")
	private Integer lastReportAt;
	@JsonProperty("name")
	private String name;
	@JsonProperty("serial_number")
	private String serialNumber;
	@JsonProperty("part_number")
	private String partNumber;
	@JsonProperty("sku")
	private Object sku;
	@JsonProperty("model")
	private String model;
	@JsonProperty("status")
	private String status;
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("state")
	private String state;
	@JsonProperty("config_type")
	private String configType;
	@JsonProperty("product_name")
	private String productName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	public Meter withId(Integer id) {
		this.id = id;
		return this;
	}

	@JsonProperty("last_report_at")
	public Integer getLastReportAt() {
		return lastReportAt;
	}

	@JsonProperty("last_report_at")
	public void setLastReportAt(Integer lastReportAt) {
		this.lastReportAt = lastReportAt;
	}

	public Meter withLastReportAt(Integer lastReportAt) {
		this.lastReportAt = lastReportAt;
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

	public Meter withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}

	@JsonProperty("serial_number")
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Meter withSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
		return this;
	}

	@JsonProperty("part_number")
	public String getPartNumber() {
		return partNumber;
	}

	@JsonProperty("part_number")
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public Meter withPartNumber(String partNumber) {
		this.partNumber = partNumber;
		return this;
	}

	@JsonProperty("sku")
	public Object getSku() {
		return sku;
	}

	@JsonProperty("sku")
	public void setSku(Object sku) {
		this.sku = sku;
	}

	public Meter withSku(Object sku) {
		this.sku = sku;
		return this;
	}

	@JsonProperty("model")
	public String getModel() {
		return model;
	}

	@JsonProperty("model")
	public void setModel(String model) {
		this.model = model;
	}

	public Meter withModel(String model) {
		this.model = model;
		return this;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	public Meter withStatus(String status) {
		this.status = status;
		return this;
	}

	@JsonProperty("active")
	public Boolean getActive() {
		return active;
	}

	@JsonProperty("active")
	public void setActive(Boolean active) {
		this.active = active;
	}

	public Meter withActive(Boolean active) {
		this.active = active;
		return this;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	public Meter withState(String state) {
		this.state = state;
		return this;
	}

	@JsonProperty("config_type")
	public String getConfigType() {
		return configType;
	}

	@JsonProperty("config_type")
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public Meter withConfigType(String configType) {
		this.configType = configType;
		return this;
	}

	@JsonProperty("product_name")
	public String getProductName() {
		return productName;
	}

	@JsonProperty("product_name")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Meter withProductName(String productName) {
		this.productName = productName;
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

	public Meter withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

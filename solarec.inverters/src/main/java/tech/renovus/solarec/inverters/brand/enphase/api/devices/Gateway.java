
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
@JsonPropertyOrder({ "id", "last_report_at", "name", "serial_number", "part_number", "emu_sw_version", "sku", "model",
		"status", "active", "cellular_modem", "product_name" })
@Generated("jsonschema2pojo")
public class Gateway {

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
	@JsonProperty("emu_sw_version")
	private String emuSwVersion;
	@JsonProperty("sku")
	private String sku;
	@JsonProperty("model")
	private String model;
	@JsonProperty("status")
	private String status;
	@JsonProperty("active")
	private Boolean active;
	@JsonProperty("cellular_modem")
	private CellularModem cellularModem;
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

	public Gateway withId(Integer id) {
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

	public Gateway withLastReportAt(Integer lastReportAt) {
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

	public Gateway withName(String name) {
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

	public Gateway withSerialNumber(String serialNumber) {
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

	public Gateway withPartNumber(String partNumber) {
		this.partNumber = partNumber;
		return this;
	}

	@JsonProperty("emu_sw_version")
	public String getEmuSwVersion() {
		return emuSwVersion;
	}

	@JsonProperty("emu_sw_version")
	public void setEmuSwVersion(String emuSwVersion) {
		this.emuSwVersion = emuSwVersion;
	}

	public Gateway withEmuSwVersion(String emuSwVersion) {
		this.emuSwVersion = emuSwVersion;
		return this;
	}

	@JsonProperty("sku")
	public String getSku() {
		return sku;
	}

	@JsonProperty("sku")
	public void setSku(String sku) {
		this.sku = sku;
	}

	public Gateway withSku(String sku) {
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

	public Gateway withModel(String model) {
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

	public Gateway withStatus(String status) {
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

	public Gateway withActive(Boolean active) {
		this.active = active;
		return this;
	}

	@JsonProperty("cellular_modem")
	public CellularModem getCellularModem() {
		return cellularModem;
	}

	@JsonProperty("cellular_modem")
	public void setCellularModem(CellularModem cellularModem) {
		this.cellularModem = cellularModem;
	}

	public Gateway withCellularModem(CellularModem cellularModem) {
		this.cellularModem = cellularModem;
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

	public Gateway withProductName(String productName) {
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

	public Gateway withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

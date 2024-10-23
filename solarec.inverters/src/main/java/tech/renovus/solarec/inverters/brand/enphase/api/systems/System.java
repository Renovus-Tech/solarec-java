
package tech.renovus.solarec.inverters.brand.enphase.api.systems;

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
@JsonPropertyOrder({ "system_id", "name", "public_name", "timezone", "address", "connection_type", "energy_lifetime",
		"energy_today", "system_size", "status", "last_report_at", "last_energy_at", "operational_at",
		"attachment_type", "interconnect_date", "reference", "other_references" })
@Generated("jsonschema2pojo")
public class System {

	@JsonProperty("system_id")
	private Integer systemId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("public_name")
	private String publicName;
	@JsonProperty("timezone")
	private String timezone;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("connection_type")
	private String connectionType;
	@JsonProperty("energy_lifetime")
	private Integer energyLifetime;
	@JsonProperty("energy_today")
	private Integer energyToday;
	@JsonProperty("system_size")
	private Integer systemSize;
	@JsonProperty("status")
	private String status;
	@JsonProperty("last_report_at")
	private Integer lastReportAt;
	@JsonProperty("last_energy_at")
	private Integer lastEnergyAt;
	@JsonProperty("operational_at")
	private Integer operationalAt;
	@JsonProperty("attachment_type")
	private Object attachmentType;
	@JsonProperty("interconnect_date")
	private Object interconnectDate;
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("other_references")
	private List<String> otherReferences;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("system_id")
	public Integer getSystemId() {
		return systemId;
	}

	@JsonProperty("system_id")
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public System withSystemId(Integer systemId) {
		this.systemId = systemId;
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

	public System withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("public_name")
	public String getPublicName() {
		return publicName;
	}

	@JsonProperty("public_name")
	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public System withPublicName(String publicName) {
		this.publicName = publicName;
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

	public System withTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	public System withAddress(Address address) {
		this.address = address;
		return this;
	}

	@JsonProperty("connection_type")
	public String getConnectionType() {
		return connectionType;
	}

	@JsonProperty("connection_type")
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public System withConnectionType(String connectionType) {
		this.connectionType = connectionType;
		return this;
	}

	@JsonProperty("energy_lifetime")
	public Integer getEnergyLifetime() {
		return energyLifetime;
	}

	@JsonProperty("energy_lifetime")
	public void setEnergyLifetime(Integer energyLifetime) {
		this.energyLifetime = energyLifetime;
	}

	public System withEnergyLifetime(Integer energyLifetime) {
		this.energyLifetime = energyLifetime;
		return this;
	}

	@JsonProperty("energy_today")
	public Integer getEnergyToday() {
		return energyToday;
	}

	@JsonProperty("energy_today")
	public void setEnergyToday(Integer energyToday) {
		this.energyToday = energyToday;
	}

	public System withEnergyToday(Integer energyToday) {
		this.energyToday = energyToday;
		return this;
	}

	@JsonProperty("system_size")
	public Integer getSystemSize() {
		return systemSize;
	}

	@JsonProperty("system_size")
	public void setSystemSize(Integer systemSize) {
		this.systemSize = systemSize;
	}

	public System withSystemSize(Integer systemSize) {
		this.systemSize = systemSize;
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

	public System withStatus(String status) {
		this.status = status;
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

	public System withLastReportAt(Integer lastReportAt) {
		this.lastReportAt = lastReportAt;
		return this;
	}

	@JsonProperty("last_energy_at")
	public Integer getLastEnergyAt() {
		return lastEnergyAt;
	}

	@JsonProperty("last_energy_at")
	public void setLastEnergyAt(Integer lastEnergyAt) {
		this.lastEnergyAt = lastEnergyAt;
	}

	public System withLastEnergyAt(Integer lastEnergyAt) {
		this.lastEnergyAt = lastEnergyAt;
		return this;
	}

	@JsonProperty("operational_at")
	public Integer getOperationalAt() {
		return operationalAt;
	}

	@JsonProperty("operational_at")
	public void setOperationalAt(Integer operationalAt) {
		this.operationalAt = operationalAt;
	}

	public System withOperationalAt(Integer operationalAt) {
		this.operationalAt = operationalAt;
		return this;
	}

	@JsonProperty("attachment_type")
	public Object getAttachmentType() {
		return attachmentType;
	}

	@JsonProperty("attachment_type")
	public void setAttachmentType(Object attachmentType) {
		this.attachmentType = attachmentType;
	}

	public System withAttachmentType(Object attachmentType) {
		this.attachmentType = attachmentType;
		return this;
	}

	@JsonProperty("interconnect_date")
	public Object getInterconnectDate() {
		return interconnectDate;
	}

	@JsonProperty("interconnect_date")
	public void setInterconnectDate(Object interconnectDate) {
		this.interconnectDate = interconnectDate;
	}

	public System withInterconnectDate(Object interconnectDate) {
		this.interconnectDate = interconnectDate;
		return this;
	}

	@JsonProperty("reference")
	public String getReference() {
		return reference;
	}

	@JsonProperty("reference")
	public void setReference(String reference) {
		this.reference = reference;
	}

	public System withReference(String reference) {
		this.reference = reference;
		return this;
	}

	@JsonProperty("other_references")
	public List<String> getOtherReferences() {
		return otherReferences;
	}

	@JsonProperty("other_references")
	public void setOtherReferences(List<String> otherReferences) {
		this.otherReferences = otherReferences;
	}

	public System withOtherReferences(List<String> otherReferences) {
		this.otherReferences = otherReferences;
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

	public System withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

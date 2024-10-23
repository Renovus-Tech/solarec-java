
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
@JsonPropertyOrder({ "imei", "part_num", "sku", "plan_start_date", "plan_end_date" })
@Generated("jsonschema2pojo")
public class CellularModem {

	@JsonProperty("imei")
	private String imei;
	@JsonProperty("part_num")
	private String partNum;
	@JsonProperty("sku")
	private String sku;
	@JsonProperty("plan_start_date")
	private Integer planStartDate;
	@JsonProperty("plan_end_date")
	private Integer planEndDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("imei")
	public String getImei() {
		return imei;
	}

	@JsonProperty("imei")
	public void setImei(String imei) {
		this.imei = imei;
	}

	public CellularModem withImei(String imei) {
		this.imei = imei;
		return this;
	}

	@JsonProperty("part_num")
	public String getPartNum() {
		return partNum;
	}

	@JsonProperty("part_num")
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}

	public CellularModem withPartNum(String partNum) {
		this.partNum = partNum;
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

	public CellularModem withSku(String sku) {
		this.sku = sku;
		return this;
	}

	@JsonProperty("plan_start_date")
	public Integer getPlanStartDate() {
		return planStartDate;
	}

	@JsonProperty("plan_start_date")
	public void setPlanStartDate(Integer planStartDate) {
		this.planStartDate = planStartDate;
	}

	public CellularModem withPlanStartDate(Integer planStartDate) {
		this.planStartDate = planStartDate;
		return this;
	}

	@JsonProperty("plan_end_date")
	public Integer getPlanEndDate() {
		return planEndDate;
	}

	@JsonProperty("plan_end_date")
	public void setPlanEndDate(Integer planEndDate) {
		this.planEndDate = planEndDate;
	}

	public CellularModem withPlanEndDate(Integer planEndDate) {
		this.planEndDate = planEndDate;
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

	public CellularModem withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

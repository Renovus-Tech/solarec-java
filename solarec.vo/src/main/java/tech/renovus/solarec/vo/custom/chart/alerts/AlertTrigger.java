package tech.renovus.solarec.vo.custom.chart.alerts;

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
@JsonPropertyOrder({ "type", "gen_code", "description", "value", "previous_value", "threshold", "date",
		"diff_percentage" })
@Generated("jsonschema2pojo")
public class AlertTrigger {

	@JsonProperty("type")
	private String type;
	@JsonProperty("gen_code")
	private String genCode;
	@JsonProperty("description")
	private String description;
	@JsonProperty("value")
	private Double value;
	@JsonProperty("previous_value")
	private Double previousValue;
	@JsonProperty("threshold")
	private Integer threshold;
	@JsonProperty("date")
	private String date;
	@JsonProperty("diff_percentage")
	private Double diffPercentage;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	public AlertTrigger withType(String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("gen_code")
	public String getGenCode() {
		return genCode;
	}

	@JsonProperty("gen_code")
	public void setGenCode(String genCode) {
		this.genCode = genCode;
	}

	public AlertTrigger withGenCode(String genCode) {
		this.genCode = genCode;
		return this;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	public AlertTrigger withDescription(String description) {
		this.description = description;
		return this;
	}

	@JsonProperty("value")
	public Double getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(Double value) {
		this.value = value;
	}

	public AlertTrigger withValue(Double value) {
		this.value = value;
		return this;
	}

	@JsonProperty("previous_value")
	public Double getPreviousValue() {
		return previousValue;
	}

	@JsonProperty("previous_value")
	public void setPreviousValue(Double previousValue) {
		this.previousValue = previousValue;
	}

	public AlertTrigger withPreviousValue(Double previousValue) {
		this.previousValue = previousValue;
		return this;
	}

	@JsonProperty("threshold")
	public Integer getThreshold() {
		return threshold;
	}

	@JsonProperty("threshold")
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public AlertTrigger withThreshold(Integer threshold) {
		this.threshold = threshold;
		return this;
	}

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	public AlertTrigger withDate(String date) {
		this.date = date;
		return this;
	}

	@JsonProperty("diff_percentage")
	public Double getDiffPercentage() {
		return diffPercentage;
	}

	@JsonProperty("diff_percentage")
	public void setDiffPercentage(Double diffPercentage) {
		this.diffPercentage = diffPercentage;
	}

	public AlertTrigger withDiffPercentage(Double diffPercentage) {
		this.diffPercentage = diffPercentage;
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

	public AlertTrigger withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	
	//--- Code added from here ------------------
	public static final String TYPE_CUSTOM = "custom";
}
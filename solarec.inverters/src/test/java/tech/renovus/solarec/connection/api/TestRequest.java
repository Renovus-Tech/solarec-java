package tech.renovus.solarec.connection.api;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "text", "number", "decimal", "boolean", "date" })
@Generated("jsonschema2pojo")
public class TestRequest {

	@JsonProperty("text")
	private String text;
	@JsonProperty("number")
	private Integer number;
	@JsonProperty("decimal")
	private Double decimal;
	@JsonProperty("boolean")
	private Boolean _boolean;
	@JsonProperty("date")
	private String date;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("number")
	public Integer getNumber() {
		return number;
	}

	@JsonProperty("decimal")
	public Double getDecimal() {
		return decimal;
	}

	@JsonProperty("boolean")
	public Boolean getBoolean() {
		return _boolean;
	}

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public TestRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	// --- Added code ----------------------------
	public static TestRequest random() {
		TestRequest result = new TestRequest();

		result.text = UUID.randomUUID().toString();
		result.number = Integer.valueOf(Double.valueOf(Math.random() * 10000).intValue());
		result.decimal = Double.valueOf(Math.random() * 10000);
		result.date = "2024-01-02";

		return result;
	}
}
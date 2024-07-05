
package tech.renovus.solarec.certificate.surentis.api;

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
@JsonPropertyOrder({ "test_mode" })
@Generated("jsonschema2pojo")
public class Mode {

	@JsonProperty("test_mode")
	private Boolean testMode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("test_mode")
	public Boolean getTestMode() {
		return testMode;
	}

	@JsonProperty("test_mode")
	public void setTestMode(Boolean testMode) {
		this.testMode = testMode;
	}

	public Mode withTestMode(Boolean testMode) {
		this.testMode = testMode;
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

	public Mode withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

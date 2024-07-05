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
@JsonPropertyOrder({ "redemption_code", "exists" })
@Generated("jsonschema2pojo")
public class Redemption {

	@JsonProperty("redemption_code")
	private String redemptionCode;
	@JsonProperty("exists")
	private Boolean exists;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("redemption_code")
	public String getRedemptionCode() {
		return redemptionCode;
	}

	@JsonProperty("redemption_code")
	public void setRedemptionCode(String redemptionCode) {
		this.redemptionCode = redemptionCode;
	}

	public Redemption withRedemptionCode(String redemptionCode) {
		this.redemptionCode = redemptionCode;
		return this;
	}

	@JsonProperty("exists")
	public Boolean getExists() {
		return exists;
	}

	@JsonProperty("exists")
	public void setExists(Boolean exists) {
		this.exists = exists;
	}

	public Redemption withExists(Boolean exists) {
		this.exists = exists;
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

	public Redemption withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
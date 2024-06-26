package tech.renovus.solarec.certificate.greenhub.api;

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
@JsonPropertyOrder({ "location_id", "data_pro_id", "power", "data_start_date", "data_end_date", "id", "tokens",
		"available_tokens", "transaction_hash" })
@Generated("jsonschema2pojo")
public class Record {

	@JsonProperty("location_id")
	private Integer locationId;
	@JsonProperty("data_pro_id")
	private Integer dataProId;
	@JsonProperty("power")
	private Double power;
	@JsonProperty("data_start_date")
	private String dataStartDate;
	@JsonProperty("data_end_date")
	private String dataEndDate;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("tokens")
	private String tokens;
	@JsonProperty("available_tokens")
	private String availableTokens;
	@JsonProperty("transaction_hash")
	private String transactionHash;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("location_id")
	public Integer getLocationId() {
		return locationId;
	}

	@JsonProperty("location_id")
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Record withLocationId(Integer locationId) {
		this.locationId = locationId;
		return this;
	}

	@JsonProperty("data_pro_id")
	public Integer getDataProId() {
		return dataProId;
	}

	@JsonProperty("data_pro_id")
	public void setDataProId(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public Record withDataProId(Integer dataProId) {
		this.dataProId = dataProId;
		return this;
	}

	@JsonProperty("power")
	public Double getPower() {
		return power;
	}

	@JsonProperty("power")
	public void setPower(Double power) {
		this.power = power;
	}

	public Record withPower(Double power) {
		this.power = power;
		return this;
	}

	@JsonProperty("data_start_date")
	public String getDataStartDate() {
		return dataStartDate;
	}

	@JsonProperty("data_start_date")
	public void setDataStartDate(String dataStartDate) {
		this.dataStartDate = dataStartDate;
	}

	public Record withDataStartDate(String dataStartDate) {
		this.dataStartDate = dataStartDate;
		return this;
	}

	@JsonProperty("data_end_date")
	public String getDataEndDate() {
		return dataEndDate;
	}

	@JsonProperty("data_end_date")
	public void setDataEndDate(String dataEndDate) {
		this.dataEndDate = dataEndDate;
	}

	public Record withDataEndDate(String dataEndDate) {
		this.dataEndDate = dataEndDate;
		return this;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	public Record withId(Integer id) {
		this.id = id;
		return this;
	}

	@JsonProperty("tokens")
	public String getTokens() {
		return tokens;
	}

	@JsonProperty("tokens")
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public Record withTokens(String tokens) {
		this.tokens = tokens;
		return this;
	}

	@JsonProperty("available_tokens")
	public String getAvailableTokens() {
		return availableTokens;
	}

	@JsonProperty("available_tokens")
	public void setAvailableTokens(String availableTokens) {
		this.availableTokens = availableTokens;
	}

	public Record withAvailableTokens(String availableTokens) {
		this.availableTokens = availableTokens;
		return this;
	}

	@JsonProperty("transaction_hash")
	public String getTransactionHash() {
		return transactionHash;
	}

	@JsonProperty("transaction_hash")
	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}

	public Record withTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
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

	public Record withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
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
@JsonPropertyOrder({ "rec_id", "nft_id", "transaction_hash" })
@Generated("jsonschema2pojo")
public class Rec {

	@JsonProperty("rec_id")
	private Integer recId;
	@JsonProperty("nft_id")
	private Integer nftId;
	@JsonProperty("transaction_hash")
	private String transactionHash;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("rec_id")
	public Integer getRecId() {
		return recId;
	}

	@JsonProperty("rec_id")
	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public Rec withRecId(Integer recId) {
		this.recId = recId;
		return this;
	}

	@JsonProperty("nft_id")
	public Integer getNftId() {
		return nftId;
	}

	@JsonProperty("nft_id")
	public void setNftId(Integer nftId) {
		this.nftId = nftId;
	}

	public Rec withNftId(Integer nftId) {
		this.nftId = nftId;
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

	public Rec withTransactionHash(String transactionHash) {
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

	public Rec withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
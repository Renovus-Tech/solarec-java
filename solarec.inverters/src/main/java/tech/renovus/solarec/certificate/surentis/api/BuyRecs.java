package tech.renovus.solarec.certificate.surentis.api;

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
@JsonPropertyOrder({ "buyer_id", "from_date", "to_date", "location_ids", "number_of_recs", "buyer_blockchain_network",
		"buyer_blockchain_address", "redemption_code", "recs" })
@Generated("jsonschema2pojo")
public class BuyRecs {

	@JsonProperty("buyer_id")
	private Integer buyerId;
	@JsonProperty("from_date")
	private String fromDate;
	@JsonProperty("to_date")
	private String toDate;
	@JsonProperty("location_ids")
	private List<Object> locationIds;
	@JsonProperty("number_of_recs")
	private Integer numberOfRecs;
	@JsonProperty("buyer_blockchain_network")
	private String buyerBlockchainNetwork;
	@JsonProperty("buyer_blockchain_address")
	private String buyerBlockchainAddress;
	@JsonProperty("redemption_code")
	private String redemptionCode;
	@JsonProperty("recs")
	private List<Rec> recs;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("buyer_id")
	public Integer getBuyerId() {
		return buyerId;
	}

	@JsonProperty("buyer_id")
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public BuyRecs withBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
		return this;
	}

	@JsonProperty("from_date")
	public String getFromDate() {
		return fromDate;
	}

	@JsonProperty("from_date")
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public BuyRecs withFromDate(String fromDate) {
		this.fromDate = fromDate;
		return this;
	}

	@JsonProperty("to_date")
	public String getToDate() {
		return toDate;
	}

	@JsonProperty("to_date")
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public BuyRecs withToDate(String toDate) {
		this.toDate = toDate;
		return this;
	}

	@JsonProperty("location_ids")
	public List<Object> getLocationIds() {
		return locationIds;
	}

	@JsonProperty("location_ids")
	public void setLocationIds(List<Object> locationIds) {
		this.locationIds = locationIds;
	}

	public BuyRecs withLocationIds(List<Object> locationIds) {
		this.locationIds = locationIds;
		return this;
	}

	@JsonProperty("number_of_recs")
	public Integer getNumberOfRecs() {
		return numberOfRecs;
	}

	@JsonProperty("number_of_recs")
	public void setNumberOfRecs(Integer numberOfRecs) {
		this.numberOfRecs = numberOfRecs;
	}

	public BuyRecs withNumberOfRecs(Integer numberOfRecs) {
		this.numberOfRecs = numberOfRecs;
		return this;
	}

	@JsonProperty("buyer_blockchain_network")
	public String getBuyerBlockchainNetwork() {
		return buyerBlockchainNetwork;
	}

	@JsonProperty("buyer_blockchain_network")
	public void setBuyerBlockchainNetwork(String buyerBlockchainNetwork) {
		this.buyerBlockchainNetwork = buyerBlockchainNetwork;
	}

	public BuyRecs withBuyerBlockchainNetwork(String buyerBlockchainNetwork) {
		this.buyerBlockchainNetwork = buyerBlockchainNetwork;
		return this;
	}

	@JsonProperty("buyer_blockchain_address")
	public String getBuyerBlockchainAddress() {
		return buyerBlockchainAddress;
	}

	@JsonProperty("buyer_blockchain_address")
	public void setBuyerBlockchainAddress(String buyerBlockchainAddress) {
		this.buyerBlockchainAddress = buyerBlockchainAddress;
	}

	public BuyRecs withBuyerBlockchainAddress(String buyerBlockchainAddress) {
		this.buyerBlockchainAddress = buyerBlockchainAddress;
		return this;
	}

	@JsonProperty("redemption_code")
	public String getRedemptionCode() {
		return redemptionCode;
	}

	@JsonProperty("redemption_code")
	public void setRedemptionCode(String redemptionCode) {
		this.redemptionCode = redemptionCode;
	}

	public BuyRecs withRedemptionCode(String redemptionCode) {
		this.redemptionCode = redemptionCode;
		return this;
	}

	@JsonProperty("recs")
	public List<Rec> getRecs() {
		return recs;
	}

	@JsonProperty("recs")
	public void setRecs(List<Rec> recs) {
		this.recs = recs;
	}

	public BuyRecs withRecs(List<Rec> recs) {
		this.recs = recs;
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

	public BuyRecs withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
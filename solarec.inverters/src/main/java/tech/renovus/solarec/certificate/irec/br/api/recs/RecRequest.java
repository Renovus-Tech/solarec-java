package tech.renovus.solarec.certificate.irec.br.api.recs;

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
@JsonPropertyOrder({ "rec_id", "business_id", "date", "volume", "participant_id", "national_stats",
		"public_consumption" })
@Generated("jsonschema2pojo")
public class RecRequest {

	@JsonProperty("rec_id")
	private String recId;
	@JsonProperty("business_id")
	private String businessId;
	@JsonProperty("date")
	private String date;
	@JsonProperty("volume")
	private Double volume;
	@JsonProperty("participant_id")
	private Integer participantId;
	@JsonProperty("national_stats")
	private Integer nationalStats;
	@JsonProperty("public_consumption")
	private Integer publicConsumption;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("rec_id")
	public String getRecId() {
		return recId;
	}

	@JsonProperty("rec_id")
	public void setRecId(String recId) {
		this.recId = recId;
	}

	public RecRequest withRecId(String recId) {
		this.recId = recId;
		return this;
	}

	@JsonProperty("business_id")
	public String getBusinessId() {
		return businessId;
	}

	@JsonProperty("business_id")
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public RecRequest withBusinessId(String businessId) {
		this.businessId = businessId;
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

	public RecRequest withDate(String date) {
		this.date = date;
		return this;
	}

	@JsonProperty("volume")
	public Double getVolume() {
		return volume;
	}

	@JsonProperty("volume")
	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public RecRequest withVolume(Double volume) {
		this.volume = volume;
		return this;
	}

	@JsonProperty("participant_id")
	public Integer getParticipantId() {
		return participantId;
	}

	@JsonProperty("participant_id")
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public RecRequest withParticipantId(Integer participantId) {
		this.participantId = participantId;
		return this;
	}

	@JsonProperty("national_stats")
	public Integer getNationalStats() {
		return nationalStats;
	}

	@JsonProperty("national_stats")
	public void setNationalStats(Integer nationalStats) {
		this.nationalStats = nationalStats;
	}

	public RecRequest withNationalStats(Integer nationalStats) {
		this.nationalStats = nationalStats;
		return this;
	}

	@JsonProperty("public_consumption")
	public Integer getPublicConsumption() {
		return publicConsumption;
	}

	@JsonProperty("public_consumption")
	public void setPublicConsumption(Integer publicConsumption) {
		this.publicConsumption = publicConsumption;
	}

	public RecRequest withPublicConsumption(Integer publicConsumption) {
		this.publicConsumption = publicConsumption;
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

	public RecRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
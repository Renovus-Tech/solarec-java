package tech.renovus.solarec.certificate.irec.br.api.participant;

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
@JsonPropertyOrder({ "name", "irec" })
@Generated("jsonschema2pojo")
public class ParticipantRequest {

	@JsonProperty("name")
	private String name;
	@JsonProperty("irec")
	private String irec;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public ParticipantRequest withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("irec")
	public String getIrec() {
		return irec;
	}

	@JsonProperty("irec")
	public void setIrec(String irec) {
		this.irec = irec;
	}

	public ParticipantRequest withIrec(String irec) {
		this.irec = irec;
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

	public ParticipantRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
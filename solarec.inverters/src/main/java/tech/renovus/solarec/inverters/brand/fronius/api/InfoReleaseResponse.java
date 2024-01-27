package tech.renovus.solarec.inverters.brand.fronius.api;

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
@JsonPropertyOrder({ "releaseVersion", "releaseDate" })
@Generated("jsonschema2pojo")
public class InfoReleaseResponse extends ErrorResponse {

	@JsonProperty("releaseVersion")
	private String releaseVersion;
	@JsonProperty("releaseDate")
	private String releaseDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("releaseVersion")
	public String getReleaseVersion() {
		return releaseVersion;
	}

	@JsonProperty("releaseVersion")
	public void setReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

	public InfoReleaseResponse withReleaseVersion(String releaseVersion) {
		this.releaseVersion = releaseVersion;
		return this;
	}

	@JsonProperty("releaseDate")
	public String getReleaseDate() {
		return releaseDate;
	}

	@JsonProperty("releaseDate")
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public InfoReleaseResponse withReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
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

	public InfoReleaseResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
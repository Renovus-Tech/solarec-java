package tech.renovus.solarec.certificate.irec.br.api.error;

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
@JsonPropertyOrder({ "message", "name", "status", "frames" })
@Generated("jsonschema2pojo")
public class Error {

	@JsonProperty("message")
	private String message;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("frames")
	private List<Frame> frames;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	public Error withMessage(String message) {
		this.message = message;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Error withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Error withStatus(Integer status) {
		this.status = status;
		return this;
	}

	@JsonProperty("frames")
	public List<Frame> getFrames() {
		return frames;
	}

	@JsonProperty("frames")
	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public Error withFrames(List<Frame> frames) {
		this.frames = frames;
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

	public Error withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
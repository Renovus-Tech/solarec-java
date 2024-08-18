package tech.renovus.solarec.certificate.irec.br.api.error;

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
@JsonPropertyOrder({ "start", "pre", "line", "post" })
@Generated("jsonschema2pojo")
public class Context {

	@JsonProperty("start")
	private Integer start;
	@JsonProperty("pre")
	private String pre;
	@JsonProperty("line")
	private String line;
	@JsonProperty("post")
	private String post;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("start")
	public Integer getStart() {
		return start;
	}

	@JsonProperty("start")
	public void setStart(Integer start) {
		this.start = start;
	}

	public Context withStart(Integer start) {
		this.start = start;
		return this;
	}

	@JsonProperty("pre")
	public String getPre() {
		return pre;
	}

	@JsonProperty("pre")
	public void setPre(String pre) {
		this.pre = pre;
	}

	public Context withPre(String pre) {
		this.pre = pre;
		return this;
	}

	@JsonProperty("line")
	public String getLine() {
		return line;
	}

	@JsonProperty("line")
	public void setLine(String line) {
		this.line = line;
	}

	public Context withLine(String line) {
		this.line = line;
		return this;
	}

	@JsonProperty("post")
	public String getPost() {
		return post;
	}

	@JsonProperty("post")
	public void setPost(String post) {
		this.post = post;
	}

	public Context withPost(String post) {
		this.post = post;
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

	public Context withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
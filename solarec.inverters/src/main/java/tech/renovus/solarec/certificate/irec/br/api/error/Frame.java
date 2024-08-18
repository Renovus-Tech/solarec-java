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
@JsonPropertyOrder({ "file", "filePath", "method", "line", "column", "context", "isModule", "isNative", "isApp" })
@Generated("jsonschema2pojo")
public class Frame {

	@JsonProperty("file")
	private String file;
	@JsonProperty("filePath")
	private String filePath;
	@JsonProperty("method")
	private String method;
	@JsonProperty("line")
	private Integer line;
	@JsonProperty("column")
	private Integer column;
	@JsonProperty("context")
	private Context context;
	@JsonProperty("isModule")
	private Boolean isModule;
	@JsonProperty("isNative")
	private Boolean isNative;
	@JsonProperty("isApp")
	private Boolean isApp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("file")
	public String getFile() {
		return file;
	}

	@JsonProperty("file")
	public void setFile(String file) {
		this.file = file;
	}

	public Frame withFile(String file) {
		this.file = file;
		return this;
	}

	@JsonProperty("filePath")
	public String getFilePath() {
		return filePath;
	}

	@JsonProperty("filePath")
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Frame withFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	@JsonProperty("method")
	public String getMethod() {
		return method;
	}

	@JsonProperty("method")
	public void setMethod(String method) {
		this.method = method;
	}

	public Frame withMethod(String method) {
		this.method = method;
		return this;
	}

	@JsonProperty("line")
	public Integer getLine() {
		return line;
	}

	@JsonProperty("line")
	public void setLine(Integer line) {
		this.line = line;
	}

	public Frame withLine(Integer line) {
		this.line = line;
		return this;
	}

	@JsonProperty("column")
	public Integer getColumn() {
		return column;
	}

	@JsonProperty("column")
	public void setColumn(Integer column) {
		this.column = column;
	}

	public Frame withColumn(Integer column) {
		this.column = column;
		return this;
	}

	@JsonProperty("context")
	public Context getContext() {
		return context;
	}

	@JsonProperty("context")
	public void setContext(Context context) {
		this.context = context;
	}

	public Frame withContext(Context context) {
		this.context = context;
		return this;
	}

	@JsonProperty("isModule")
	public Boolean getIsModule() {
		return isModule;
	}

	@JsonProperty("isModule")
	public void setIsModule(Boolean isModule) {
		this.isModule = isModule;
	}

	public Frame withIsModule(Boolean isModule) {
		this.isModule = isModule;
		return this;
	}

	@JsonProperty("isNative")
	public Boolean getIsNative() {
		return isNative;
	}

	@JsonProperty("isNative")
	public void setIsNative(Boolean isNative) {
		this.isNative = isNative;
	}

	public Frame withIsNative(Boolean isNative) {
		this.isNative = isNative;
		return this;
	}

	@JsonProperty("isApp")
	public Boolean getIsApp() {
		return isApp;
	}

	@JsonProperty("isApp")
	public void setIsApp(Boolean isApp) {
		this.isApp = isApp;
	}

	public Frame withIsApp(Boolean isApp) {
		this.isApp = isApp;
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

	public Frame withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
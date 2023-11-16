package tech.renovus.solarec.vo.rest.history;

import java.util.ArrayList;
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
@JsonPropertyOrder({ "resultCode", "resultMessage", "errors" })
@Generated("jsonschema2pojo")
public class HistoryDataResult {

	@JsonProperty("resultCode")
	private Integer resultCode;
	@JsonProperty("resultMessage")
	private String resultMessage;
	@JsonProperty("errors")
	private List<String> errors;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("resultCode")
	public Integer getResultCode() {
		return resultCode;
	}

	@JsonProperty("resultCode")
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public HistoryDataResult withResultCode(Integer resultCode) {
		this.resultCode = resultCode;
		return this;
	}

	@JsonProperty("resultMessage")
	public String getResultMessage() {
		return resultMessage;
	}

	@JsonProperty("resultMessage")
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public HistoryDataResult withResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
		return this;
	}

	@JsonProperty("errors")
	public List<String> getErrors() {
		return errors;
	}

	@JsonProperty("errors")
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public HistoryDataResult withErrors(List<String> errors) {
		this.errors = errors;
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

	public HistoryDataResult withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	public static final int OK	= 10;
	private static final int OK_WITH_ERRORS = 11;
	private static final int ERROR_DATA_REQUIRED = -101;
	private static final int ERROR_CLIENT_REQUIRED = -102;
	private static final int ERROR_CLIENT = -103;
	private static final int ERROR_INTERNAL = -105;
	
	private static final String MSG_OK	= "Ok";
	private static final String MSG_OK_WITH_ERRORS	= "Ok, but errors found";
	private static final String MSG_ERROR_DATA_REQUIRED	= "No data was found to validate";
	private static final String MSG_ERROR_CLIENT_REQUIRED	= "Client data is required";
	private static final String MSG_ERROR_CLIENT = "Client not found: ";
	private static final String MSG_ERROR_INTERNAL = "Internal error: ";
	
	public void setInvalidSource(SourceType src, String type) {
		if (this.errors == null) this.errors = new ArrayList<>(1);
		this.resultCode = OK_WITH_ERRORS;
		this.resultMessage = MSG_OK_WITH_ERRORS;
		this.errors.add("Invalid " + type + ": " + src.toString());
	}

	public void setDataRequired() {
		this.resultCode = ERROR_DATA_REQUIRED;
		this.resultMessage = MSG_ERROR_DATA_REQUIRED;
	}

	public void setClientRequired() {
		this.resultCode = ERROR_CLIENT_REQUIRED;
		this.resultMessage = MSG_ERROR_CLIENT_REQUIRED;
	}

	public void setOk() {
		this.resultCode = OK;
		this.resultMessage = MSG_OK;
	}

	public void setError(Exception e) {
		this.resultCode = ERROR_INTERNAL;
		this.resultMessage = MSG_ERROR_INTERNAL + e.getLocalizedMessage();
	}
	
	public HistoryDataResult invalidClient(Client client) {
		this.resultCode = ERROR_CLIENT;
		this.resultMessage = MSG_ERROR_CLIENT + client.toString();
		return this;
	}
}
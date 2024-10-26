package tech.renovus.solarec.inverters.brand.solis.api.inverterDay;

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
@JsonPropertyOrder({ "id", "sn", "money", "time", "timeZone" })
@Generated("jsonschema2pojo")
public class InverterDayRequest {

	@JsonProperty("id")
	private String id;
	@JsonProperty("sn")
	private String sn;
	@JsonProperty("money")
	private String money;
	@JsonProperty("time")
	private String time;
	@JsonProperty("timeZone")
	private Integer timeZone;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public InverterDayRequest withId(String id) {
		this.id = id;
		return this;
	}

	@JsonProperty("sn")
	public String getSn() {
		return sn;
	}

	@JsonProperty("sn")
	public void setSn(String sn) {
		this.sn = sn;
	}

	public InverterDayRequest withSn(String sn) {
		this.sn = sn;
		return this;
	}

	@JsonProperty("money")
	public String getMoney() {
		return money;
	}

	@JsonProperty("money")
	public void setMoney(String money) {
		this.money = money;
	}

	public InverterDayRequest withMoney(String money) {
		this.money = money;
		return this;
	}

	@JsonProperty("time")
	public String getTime() {
		return time;
	}

	@JsonProperty("time")
	public void setTime(String time) {
		this.time = time;
	}

	public InverterDayRequest withTime(String time) {
		this.time = time;
		return this;
	}

	@JsonProperty("timeZone")
	public Integer getTimeZone() {
		return timeZone;
	}

	@JsonProperty("timeZone")
	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public InverterDayRequest withTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
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

	public InverterDayRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
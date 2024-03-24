
package tech.renovus.solarec.inverters.brand.aiswei.api;

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
@JsonPropertyOrder({ "sid", "ludt", "status", "E-Today", "E-Month", "E-Total", "TotalYield", "CO2Avoided", "Power" })
@Generated("jsonschema2pojo")
public class PlantOverviewResponse {

	@JsonProperty("sid")
	private Integer sid;
	@JsonProperty("ludt")
	private String ludt;
	@JsonProperty("status")
	private String status;
	@JsonProperty("E-Today")
	private EToday eToday;
	@JsonProperty("E-Month")
	private EMonth eMonth;
	@JsonProperty("E-Total")
	private ETotal eTotal;
	@JsonProperty("TotalYield")
	private TotalYield totalYield;
	@JsonProperty("CO2Avoided")
	private CO2Avoided cO2Avoided;
	@JsonProperty("Power")
	private Power power;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("sid")
	public Integer getSid() {
		return sid;
	}

	@JsonProperty("sid")
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public PlantOverviewResponse withSid(Integer sid) {
		this.sid = sid;
		return this;
	}

	@JsonProperty("ludt")
	public String getLudt() {
		return ludt;
	}

	@JsonProperty("ludt")
	public void setLudt(String ludt) {
		this.ludt = ludt;
	}

	public PlantOverviewResponse withLudt(String ludt) {
		this.ludt = ludt;
		return this;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	public PlantOverviewResponse withStatus(String status) {
		this.status = status;
		return this;
	}

	@JsonProperty("E-Today")
	public EToday getEToday() {
		return eToday;
	}

	@JsonProperty("E-Today")
	public void setEToday(EToday eToday) {
		this.eToday = eToday;
	}

	public PlantOverviewResponse withEToday(EToday eToday) {
		this.eToday = eToday;
		return this;
	}

	@JsonProperty("E-Month")
	public EMonth getEMonth() {
		return eMonth;
	}

	@JsonProperty("E-Month")
	public void setEMonth(EMonth eMonth) {
		this.eMonth = eMonth;
	}

	public PlantOverviewResponse withEMonth(EMonth eMonth) {
		this.eMonth = eMonth;
		return this;
	}

	@JsonProperty("E-Total")
	public ETotal getETotal() {
		return eTotal;
	}

	@JsonProperty("E-Total")
	public void setETotal(ETotal eTotal) {
		this.eTotal = eTotal;
	}

	public PlantOverviewResponse withETotal(ETotal eTotal) {
		this.eTotal = eTotal;
		return this;
	}

	@JsonProperty("TotalYield")
	public TotalYield getTotalYield() {
		return totalYield;
	}

	@JsonProperty("TotalYield")
	public void setTotalYield(TotalYield totalYield) {
		this.totalYield = totalYield;
	}

	public PlantOverviewResponse withTotalYield(TotalYield totalYield) {
		this.totalYield = totalYield;
		return this;
	}

	@JsonProperty("CO2Avoided")
	public CO2Avoided getCO2Avoided() {
		return cO2Avoided;
	}

	@JsonProperty("CO2Avoided")
	public void setCO2Avoided(CO2Avoided cO2Avoided) {
		this.cO2Avoided = cO2Avoided;
	}

	public PlantOverviewResponse withCO2Avoided(CO2Avoided cO2Avoided) {
		this.cO2Avoided = cO2Avoided;
		return this;
	}

	@JsonProperty("Power")
	public Power getPower() {
		return power;
	}

	@JsonProperty("Power")
	public void setPower(Power power) {
		this.power = power;
	}

	public PlantOverviewResponse withPower(Power power) {
		this.power = power;
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

	public PlantOverviewResponse withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}

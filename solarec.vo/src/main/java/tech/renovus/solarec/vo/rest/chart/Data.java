package tech.renovus.solarec.vo.rest.chart;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import tech.renovus.solarec.vo.rest.CustomDateSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

	//--- Public constants ----------------------
	public static final String INDICATOR_RED	= "red";
	public static final String INDICATOR_GREEN	= "green";
	
	//--- Private properties --------------------
	@JsonSerialize(using = CustomDateSerializer.class ) private Date date;
	private String indicator;
	private Double production;
	private Double windSpeed;
	private Double irradiance;
	private Double statusCode;
	private String status;
	private Boolean isOk;
	
	//--- Builder methods -----------------------
	public Data withDate(Date date)					{ this.setDate(date); return this; }
	public Data withIndicator(String indicator)		{ this.setIndicator(indicator); return this; }
	public Data withProduction(Double production)	{ this.setProduction(production); return this; }
	public Data withWindSpeed(Double windSpeed)		{ this.setWindSpeed(windSpeed); return this; }
	public Data withStatusCode(Double statusCode)	{ this.setStatusCode(statusCode); return this; }
	public Data withStatus(String status)			{ this.setStatus(status); return this; }
	public Data withIsOk(Boolean isOk)				{ this.setIsOk(isOk); return this; }
	public Data withIrradiance(Double irradiance)	{ this.setIrradiance(irradiance); return this; }
	
	//--- Getters and Setters -------------------
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public Double getProduction() {
		return production;
	}
	public void setProduction(Double production) {
		this.production = production;
	}
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Double statusCode) {
		this.statusCode = statusCode;
	}
	@JsonSerialize(using = CustomDateSerializer.class )
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getIsOk() {
		return isOk;
	}
	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}
	public Double getIrradiance() {
		return irradiance;
	}
	public void setIrradiance(Double irradiance) {
		this.irradiance = irradiance;
	}
}

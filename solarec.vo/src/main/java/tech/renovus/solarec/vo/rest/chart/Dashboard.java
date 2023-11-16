package tech.renovus.solarec.vo.rest.chart;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import tech.renovus.solarec.vo.rest.CustomDateSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dashboard {

	//--- Private properties --------------------
	@JsonSerialize(using = CustomDateSerializer.class ) private Date dateData;
	@JsonSerialize(using = CustomDateSerializer.class ) private Date dateGenerated;
	private Data dataNow;
	private Data dataToday;
	private Integer amountGenerators;
	private Integer amountGeneratorsOk;
	
	private List<Location> locations;

	//--- Builders ------------------------------
	public Dashboard withDateData(Date dateDate)					{ this.setDateData(dateDate); return this; }
	public Dashboard withDateGenerated(Date dateGenerated)			{ this.setDateGenerated(dateGenerated); return this; }
	public Dashboard withDataNow(Data dataNow)						{ this.setDataNow(dataNow); return this; }
	public Dashboard withDataToday(Data dataToday)					{ this.setDataToday(dataToday); return this; }
	public Dashboard withAmountGenerators(int amountGenerators)		{ this.setAmountGenerators(Integer.valueOf(amountGenerators)); return this; }
	public Dashboard withAmountGeneratorsOk(int amountGeneratorsOk)	{ this.setAmountGeneratorsOk(Integer.valueOf(amountGeneratorsOk)); return this; }

	public Dashboard withLocations(List<Location> locations)		{ this.setLocations(locations); return this; }
	
	//--- Getters and Setters -------------------
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	@JsonSerialize(using = CustomDateSerializer.class )
	public Date getDateData() {
		return dateData;
	}

	public void setDateData(Date dateData) {
		this.dateData = dateData;
	}
	@JsonSerialize(using = CustomDateSerializer.class )
	public Date getDateGenerated() {
		return dateGenerated;
	}

	public void setDateGenerated(Date dateGenerated) {
		this.dateGenerated = dateGenerated;
	}
	public Integer getAmountGenerators() {
		return amountGenerators;
	}
	public void setAmountGenerators(Integer amountGenerators) {
		this.amountGenerators = amountGenerators;
	}
	public Integer getAmountGeneratorsOk() {
		return amountGeneratorsOk;
	}
	public void setAmountGeneratorsOk(Integer amountGeneratorsOk) {
		this.amountGeneratorsOk = amountGeneratorsOk;
	}
	public Data getDataNow() {
		return dataNow;
	}
	public void setDataNow(Data dataNow) {
		this.dataNow = dataNow;
	}
	public Data getDataToday() {
		return dataToday;
	}
	public void setDataToday(Data dataToday) {
		this.dataToday = dataToday;
	}
}

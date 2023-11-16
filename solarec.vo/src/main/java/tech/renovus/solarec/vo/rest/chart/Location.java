package tech.renovus.solarec.vo.rest.chart;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

	//--- Private properties --------------------
	private Integer id;
	private String name;
	
	private Data dataNow;
	private Data dataToday;
	
	private Integer amountGenerators;
	private Integer amountGeneratorsOk;

	private List<Generator> generators;
	private List<Station> stations;
	
	//--- Builders ------------------------------
	public Location withId(Integer id)									{ this.setId(id); return this; }
	public Location withName(String name)								{ this.setName(name); return this; }
	public Location withDataNow(Data dataNow)							{ this.setDataNow(dataNow); return this; }
	public Location withDataToday(Data dataToday)						{ this.setDataToday(dataToday); return this; }

	public Location withAmountGenerators(int amountGenerators)			{ this.setAmountGenerators(Integer.valueOf(amountGenerators)); return this; }
	public Location withAmountGeneratorsOk(int amountGeneratorsOk)		{ this.setAmountGeneratorsOk(Integer.valueOf(amountGeneratorsOk)); return this; }

	public Location withGenerators(List<Generator> generators)			{ this.setGenerators(generators); return this; }
	public Location withStations(List<Station> stations)				{ this.setStations(stations); return this; }
	
	//--- Getters and Setters -------------------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Data getDataNow() {
		return dataNow;
	}
	public void setDataNow(Data data) {
		this.dataNow = data;
	}
	public List<Generator> getGenerators() {
		return generators;
	}
	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
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
	public Data getDataToday() {
		return dataToday;
	}
	public void setDataToday(Data dataToday) {
		this.dataToday = dataToday;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
}

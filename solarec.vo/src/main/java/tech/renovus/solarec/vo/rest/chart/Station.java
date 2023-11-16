package tech.renovus.solarec.vo.rest.chart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Station {

	//--- Private properties --------------------
	private Integer id;
	private String code;
	private Data dataNow;
	private Data dataToday;
	
	//--- Builders ------------------------------
	public Station withId(Integer id)				{ this.setId(id); return this; }
	public Station withCode(String code)			{ this.setCode(code); return this; }
	public Station withDataNow(Data dataNow)		{ this.setDataNow(dataNow); return this; } 
	public Station withDataToday(Data dataToday)	{ this.setDataToday(dataToday); return this; }
	
	//--- Getters and Setters -------------------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

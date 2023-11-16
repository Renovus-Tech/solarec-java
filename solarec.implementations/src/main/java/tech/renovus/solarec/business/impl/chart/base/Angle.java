package tech.renovus.solarec.business.impl.chart.base;

public class Angle {

	//--- Private properties --------------------
	private Double from;
	private Double to;
	
	//--- Constructors --------------------------
	public Angle() {}
	public Angle(Double from, Double to) {
		this.from = from;
		this.to = to;
	}
	
	//--- Getters and Setters -------------------
	public Double getTo() {
		return to;
	}
	public void setTo(Double to) {
		this.to = to;
	}
	public Double getFrom() {
		return from;
	}
	public void setFrom(Double from) {
		this.from = from;
	}
}

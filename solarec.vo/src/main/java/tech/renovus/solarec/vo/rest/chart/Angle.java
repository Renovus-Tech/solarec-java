package tech.renovus.solarec.vo.rest.chart;

public class Angle {

	//--- Public constants ----------------------
	public static final Angle SECTOR_0		= new Angle(0.0, 45.0);
	public static final Angle SECTOR_45		= new Angle(45.0, 90.0);
	public static final Angle SECTOR_90		= new Angle(90.0, 135.0);
	public static final Angle SECTOR_135	= new Angle(135.0, 180.0);
	public static final Angle SECTOR_180	= new Angle(180.0, 225.0);
	public static final Angle SECTOR_225	= new Angle(225.0, 270.0);
	public static final Angle SECTOR_270	= new Angle(270.0, 315.0);
	public static final Angle SECTOR_315	= new Angle(315.0, 360.0);
	
	//--- Private constants ---------------------
	private Double from;
	private Double to;
	
	//--- Constructors --------------------------
	public Angle() {}
	
	public Angle(Double from, Double to) {
		this.from = from;
		this.to = to;
	}
	
	public Angle(double from, double to) {
		this.from = Double.valueOf(from);
		this.to = Double.valueOf(to);
	}
	
	//--- Overridden methods --------------------
	@Override public String toString() { return this.from.intValue() + " - " + this.to.intValue(); }
	
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

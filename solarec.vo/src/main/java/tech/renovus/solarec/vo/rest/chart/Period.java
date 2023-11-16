package tech.renovus.solarec.vo.rest.chart;

public class Period {

	//--- Private properties --------------------
	private Integer startMonth;
	private Integer endMonth;
	private Integer startYear;
	private Integer endYear;
	
	//--- Constructors --------------------------
	public Period() {}
	
	public Period (int startYear, int startMonth, int endYear, int endMonth) {
		this.startYear	= Integer.valueOf(startYear);
		this.startMonth	= Integer.valueOf(startMonth);
		this.endYear	= Integer.valueOf(endYear);
		this.endMonth	= Integer.valueOf(endMonth);
	}
	
	//--- Overridden methods --------------------
	@Override public String toString() {
		return new StringBuilder()
		.append(this.startYear)
		.append("-")
		.append(this.startMonth)
		.append(" --> ")
		.append(this.endYear)
		.append("-")
		.append(this.endMonth)
		.toString();
	}
	
	//--- Public methods ------------------------
	public Period createCopy() {
		Period result = new Period();
		
		result.startMonth	= this.startMonth;
		result.startYear	= this.startYear;
		result.endMonth		= this.endMonth;
		result.endYear		= this.endYear;
		
		return result;
	}
	
	//--- Getters and Setters -------------------
	public Integer getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}
	public Integer getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}
	public Integer getStartYear() {
		return startYear;
	}
	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}
	public Integer getEndYear() {
		return endYear;
	}
	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}
}

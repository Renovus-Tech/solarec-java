package tech.renovus.solarec.vo.rest.entity;

public class Frequency {

	public static class Period {
		private boolean yesterday;
		private boolean currentMonth;
		private boolean currentYear;
		private boolean range;
		
		//--- Getters and Setters ---------------
		public boolean isYesterday() {
			return yesterday;
		}
		public void setYesterday(boolean yesterday) {
			this.yesterday = yesterday;
		}
		public boolean isCurrentMonth() {
			return currentMonth;
		}
		public void setCurrentMonth(boolean currentMonth) {
			this.currentMonth = currentMonth;
		}
		public boolean isCurrentYear() {
			return currentYear;
		}
		public void setCurrentYear(boolean currentYear) {
			this.currentYear = currentYear;
		}
		public boolean isRange() {
			return range;
		}
		public void setRange(boolean range) {
			this.range = range;
		}
	}
	
	public static class GroupBy {
		private boolean day;
		private boolean week;
		private boolean month;
		private boolean year;
		
		//--- Getters and Setters ---------------
		public boolean isDay() {
			return day;
		}
		public void setDay(boolean day) {
			this.day = day;
		}
		public boolean isWeek() {
			return week;
		}
		public void setWeek(boolean week) {
			this.week = week;
		}
		public boolean isMonth() {
			return month;
		}
		public void setMonth(boolean month) {
			this.month = month;
		}
		public boolean isYear() {
			return year;
		}
		public void setYear(boolean year) {
			this.year = year;
		}
	}
	
	//--- Private properties --------------------
	private Integer id;
	private String name;
	private Period periods;
	private GroupBy groupby;
	
	//--- Getters and setters -------------------
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
	public Period getPeriods() {
		return periods;
	}
	public void setPeriods(Period periods) {
		this.periods = periods;
	}
	public GroupBy getGroupby() {
		return groupby;
	}
	public void setGroupby(GroupBy groupby) {
		this.groupby = groupby;
	}
}

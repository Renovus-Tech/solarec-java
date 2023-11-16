package tech.renovus.solarec.vo.rest.weather;

import java.util.Date;
import java.util.List;

public class WeatherForecast {
	
	//--- Inner classes -------------------------
	public static class Data {
		
		//--- Private properties ----------------
		private Date date;
		private Double avgTempC;
		private Double windDir80mDEG;
		private Double windSpeed80mKPH;
		
		//--- Getters and Setters ---------------
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Double getAvgTempC() {
			return avgTempC;
		}
		public void setAvgTempC(Double avgTempC) {
			this.avgTempC = avgTempC;
		}
		public Double getWindDir80mDEG() {
			return windDir80mDEG;
		}
		public void setWindDir80mDEG(Double windDir80mDEG) {
			this.windDir80mDEG = windDir80mDEG;
		}
		public Double getWindSpeed80mKPH() {
			return windSpeed80mKPH;
		}
		public void setWindSpeed80mKPH(Double windSpeed80mKPH) {
			this.windSpeed80mKPH = windSpeed80mKPH;
		}
	}

	//--- Private properties --------------------
	private String type = "lightning";
	private Date request;
	private List<Data> data;
	
	//--- Getters and Setters -------------------
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getRequest() {
		return request;
	}
	public void setRequest(Date request) {
		this.request = request;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
}

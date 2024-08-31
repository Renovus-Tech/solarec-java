package tech.renovus.solarec.weather.meteoblue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeteoblueConfiguration {
	
	//--- API configuration ---------------------
	@Value("${solarec.meteoblue.key}")					private String key;
	@Value("${solarec.meteoblue.max_day_past:7}")		private Integer maxDaysPast;

	//--- Getters methods -----------------------
	public String getKey() {
		return key;
	}

	public void setKey(String meteoblueKey) {
		this.key = meteoblueKey;
	}

	public Integer getMaxDaysPast() {
		return maxDaysPast;
	}

	public void setMaxDaysPast(Integer maxDaysPast) {
		this.maxDaysPast = maxDaysPast;
	}
	
}
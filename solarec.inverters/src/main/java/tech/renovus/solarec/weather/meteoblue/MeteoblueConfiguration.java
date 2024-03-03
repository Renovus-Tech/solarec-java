package tech.renovus.solarec.weather.meteoblue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeteoblueConfiguration {
	
	//--- API configuration ---------------------
	@Value("${salrec.meteoblue.key}")					private String meteoblueKey;

	//--- Getters methods -----------------------
	public String getMeteoblueKey() {
		return meteoblueKey;
	}
	
}
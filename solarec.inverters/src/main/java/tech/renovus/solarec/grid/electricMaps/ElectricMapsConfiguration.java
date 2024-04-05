package tech.renovus.solarec.grid.electricMaps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElectricMapsConfiguration {
	
	//--- API configuration ---------------------
	@Value("${salrec.electricmaps.key}")					private String key;

	//--- Getters methods -----------------------
	public String getKey() {
		return key;
	}
	
}
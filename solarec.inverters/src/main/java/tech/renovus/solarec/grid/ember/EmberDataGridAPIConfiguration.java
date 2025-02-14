package tech.renovus.solarec.grid.ember;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmberDataGridAPIConfiguration {
	
	//--- API configuration ---------------------
	@Value("${solarec.ember.api.key:DefaultKey}")				private String key;

	//--- Getters methods -----------------------
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
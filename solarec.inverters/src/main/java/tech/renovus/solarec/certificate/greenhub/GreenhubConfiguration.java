package tech.renovus.solarec.certificate.greenhub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreenhubConfiguration {
	
	//--- API configuration ---------------------
	@Value("${greenhub.url}")						private String url;
	@Value("${greenhub.test_mode}")					private boolean testMode;

	//--- Getters methods -----------------------
	public boolean getTestMode() {
		return testMode;
	}
	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
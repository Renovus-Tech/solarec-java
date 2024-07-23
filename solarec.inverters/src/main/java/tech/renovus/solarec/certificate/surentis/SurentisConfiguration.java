package tech.renovus.solarec.certificate.surentis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurentisConfiguration {
	
	//--- API configuration ---------------------
	@Value("${surentis.url}")					private String url;
	@Value("${surentis.system_id}")				private Integer systemId;
	@Value("${surentis.test_mode}")				private boolean testMode;

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
	public Integer getSystemId() {
		return systemId;
	}
	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}
	
}
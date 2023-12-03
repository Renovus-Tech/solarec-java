package tech.renovus.solarec.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenovusSolarConfiguration {

	//--- Site configuration --------------------
	@Value("${tech.renovus.solarec.solar.site.url}")				private String siteUrl;
	
	//--- Path configurations -------------------
	@Value("${falconer.path.log}")									private String pathLog;
	
	//--- Chart configuration -------------------
	@Value("${tech.renovus.solarec.solar.overview.url}")			private String chartOverviewUrl;
	@Value("${tech.renovus.solarec.solar.performanceIndex.url}")	private String chartPerformanceIndexUrl;
	@Value("${tech.renovus.solarec.solar.climate.url}")				private String chartClimateUrl;
	@Value("${tech.renovus.solarec.solar.powerCurve.url}")			private String chartPowerCurveUrl;

	//--- Mail configuration --------------------
	@Value("${tech.renovus.solarec.mail.send_from}")				private String mailSendFrom;
	@Value("${tech.renovus.solarec.mail.protocol}")					private String mailCheckerProtocol;
	@Value("${tech.renovus.solarec.mail.host}")						private String mailCheckerHost;
	@Value("${tech.renovus.solarec.mail.username}")					private String mailCheckerUsername;
	@Value("${tech.renovus.solarec.mail.password}")					private String mailCheckerPassword;
	
	@Value("${tech.renovus.solarec.mail.on_error.send_to}")			private String onErrorSendEmailTo;
	
	//--- Getters and Setters -------------------
	public String getChartOverviewUrl() {
		return chartOverviewUrl;
	}
	public String getChartPerformanceIndexUrl() {
		return chartPerformanceIndexUrl;
	}
	public String getChartClimateUrl() {
		return chartClimateUrl;
	}
	public String getChartPowerCurveUrl() {
		return chartPowerCurveUrl;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public String getPathLog() {
		return pathLog;
	}
	public String getMailSendFrom() {
		return mailSendFrom;
	}
	public String getMailCheckerProtocol() {
		return mailCheckerProtocol;
	}
	public String getMailCheckerHost() {
		return mailCheckerHost;
	}
	public String getMailCheckerUsername() {
		return mailCheckerUsername;
	}
	public String getMailCheckerPassword() {
		return mailCheckerPassword;
	}
	public String getOnErrorSendEmailTo() {
		return onErrorSendEmailTo;
	}
}
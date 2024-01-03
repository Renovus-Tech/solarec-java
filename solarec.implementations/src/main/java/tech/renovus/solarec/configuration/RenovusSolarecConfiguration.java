package tech.renovus.solarec.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class RenovusSolarecConfiguration {
	
	//--- Site configuration --------------------
	@Getter @Value("${tech.renovus.solarec.site.url}")						private String siteUrl;
	
	
	//--- Mail configuration --------------------
	@Getter @Value("${tech.renovus.solarec.mail.send_from:Solarec}")		private String mailSendFrom;
	@Getter @Value("${tech.renovus.solarec.mail.on_error.send_to}")			private String onErrorSendEmailTo;
	
	//--- Path configurations -------------------
	@Getter @Value("${tech.renovus.solarec.path.log}")						private String pathLog;
	@Getter @Value("${tech.renovus.solarec.site.url}")						private String chartOverviewUrl;

	//--- Chart configuration -------------------
	@Getter @Value("${tech.renovus.solarec.python.url.method:get}")			private String chartMethod;
	@Getter @Value("${tech.renovus.solarec.python.performanceIndex.url}")	private String chartPerformanceIndexUrl;
	@Getter @Value("${tech.renovus.solarec.python.climate.url}")			private String chartClimateUrl;
	@Getter @Value("${tech.renovus.solarec.python.powerCurve.url}")			private String chartPowerCurveUrl;
	
	@Getter @Value("${tech.renovus.solarec.python.alertCalculations.url}")	private String alertCalculations;
	
	//--- DRecsService configurations -----------
	@Getter @Value("USER")													private String drecsServiceUsername;
	@Getter @Value("PASS")													private String drecsServicePassword;
	
	
	
	
}
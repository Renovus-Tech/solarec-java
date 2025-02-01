package tech.renovus.solarec.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenovusSolarecConfiguration {
	
	//--- Site configuration --------------------
	@Value("${tech.renovus.solarec.site.url}")						private String siteUrl;
	@Value("${tech.renovus.solarec.site.locale:EN}")				private String siteLocale;
	
	//--- Mail configuration --------------------
	@Value("${spring.mail.username}")								private String mailSendFromUser;
	@Value("${tech.renovus.solarec.mail.send_from:Solarec}")		private String mailSendFrom;
	@Value("${tech.renovus.solarec.mail.on_error.send_to}")			private String onErrorSendEmailTo;
	
	//--- Path configurations -------------------
	@Value("${tech.renovus.solarec.path.log}")						private String pathLog;

	//--- Chart configuration -------------------
	@Value("${tech.renovus.solarec.python.url.method:get}")			private String chartMethod;
	@Value("${tech.renovus.solarec.python.overview.url}")			private String chartOverviewUrl;
	@Value("${tech.renovus.solarec.python.performanceIndex.url}")	private String chartPerformanceIndexUrl;
	@Value("${tech.renovus.solarec.python.climate.url}")			private String chartClimateUrl;
	@Value("${tech.renovus.solarec.python.powerCurve.url}")			private String chartPowerCurveUrl;
	@Value("${tech.renovus.solarec.python.solarSales.url}")			private String chartSolarSalesUrl;
	@Value("${tech.renovus.solarec.python.solarCertificate.url}")	private String chartSolarCertificateUrl;
	@Value("${tech.renovus.solarec.python.emissons.url}")			private String chartEmissionsUrl;
	
	@Value("${tech.renovus.solarec.python.alertCalculations.url}")	private String alertCalculations;
	@Value("${tech.renovus.solarec.python.anomalyDetection.url}")	private String anomalyDetection;
	
	//--- Getters -------------------------------
	public String getSiteUrl() {
		return siteUrl;
	}
	public String getMailSendFrom() {
		return mailSendFrom;
	}
	public String getOnErrorSendEmailTo() {
		return onErrorSendEmailTo;
	}
	public String getPathLog() {
		return pathLog;
	}
	public String getChartMethod() {
		return chartMethod;
	}
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
	public String getAlertCalculations() {
		return alertCalculations;
	}
	public String getChartSolarSalesUrl() {
		return chartSolarSalesUrl;
	}
	public String getChartSolarCertificateUrl() {
		return chartSolarCertificateUrl;
	}
	public String getChartEmissionsUrl() {
		return chartEmissionsUrl;
	}
	public String getMailSendFromUser() {
		return mailSendFromUser;
	}
	public String getSiteLocale() {
		return siteLocale;
	}
	public void setSiteLocale(String siteLocale) {
		this.siteLocale = siteLocale;
	}
	public String getAnomalyDetection() {
		return anomalyDetection;
	}
}
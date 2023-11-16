package tech.renovus.solarec.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenovusConfiguration {

	//--- Resoureces ----------------------------
	protected @Autowired RenovusSolarConfiguration solar;
	
	//--- Path configuration --------------------
	@Value("${falconer.path.documents}")				private String pathDocuments;
	
	//--- Site configuration --------------------
	@Value("${falconer.site.url}")						private String siteUrl;
	
	//--- Chart configuration -------------------
	@Value("${falconer.chart.method}")					private String chartMethod;
	
	//--- Mail configuration --------------------
	@Value("${falconer.mail.send_from}")				private String mailSendFrom;
	@Value("${falconer.mail.checker.protocol}")			private String mailCheckerProtocol;
	@Value("${falconer.mail.checker.host}")				private String mailCheckerHost;
	@Value("${falconer.mail.checker.username}")			private String mailCheckerUsername;
	@Value("${falconer.mail.checker.password}")			private String mailCheckerPassword;
	@Value("${falconer.mail.checker.folder}")			private String mailCheckerFolder;
	
	@Value("${falconer.mail.on_error.send_to}")			private String onErrorSendEmailTo;
	
	//--- Statistic configuration ---------------
	@Value("${falconer.stat.curtailment.url}")			private String statisticCurtailmentUrl;
	@Value("${falconer.stat.expected_energy.url}")		private String statisticExpectedEnergytUrl;
	
	//--- PDF Extractor configuration -----------
	@Value("${falconer.extractor.pdf.enabled}")			private boolean extractorPdfEnabled;
	@Value("${falconer.extractor.pdf.url}")				private String extractorPdfUrl;
	
	//--- HTML configuration --------------------
	@Value("${falconer.html.index.file}")				private String htmlIndexFile;
	
	//--- Path configurations -------------------
	@Value("${falconer.path.log}")						private String pathLog;

	//--- Weather configurations ----------------
//	@Value("${falconer.weather.client}")				private String weatherClient;
//	@Value("${falconer.weather.secret}")				private String weatherSecret;
	@Value("${falconer.weather.stormglass.key}")		private String weatherStormGlassKey;
	
	//--- Apmplitude ----------------------------
	@Value("${falconer.amplitude.key}")					private String amplitudeKey;
	
	//--- Apmplitude ----------------------------
	@Value("${falconer.meteoblue.key}")					private String meteoblueKey;
	
	//--- Getters and Setters -------------------
	public RenovusSolarConfiguration getSolar() {
		return solar;
	}

	public String getPathDocuments() {
		return pathDocuments;
	}
	public String getExtractorPdfUrl() {
		return extractorPdfUrl;
	}
	public boolean isExtractorPdfEnabled() {
		return extractorPdfEnabled;
	}
	public String getHtmlIndexFile() {
		return htmlIndexFile;
	}
	public String getPathLog() {
		return pathLog;
	}
	public void setPathLog(String pathLog) {
		this.pathLog = pathLog;
	}
	public String getChartMethod() {
		return chartMethod;
	}
	public String getStatisticCurtailmentUrl() {
		return statisticCurtailmentUrl;
	}
	public String getMailSendFrom() {
		return mailSendFrom;
	}
	public String getStatisticExpectedEnergytUrl() {
		return statisticExpectedEnergytUrl;
	}
	public String getAmplitudeKey() {
		return amplitudeKey;
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
	public String getMailCheckerFolder() {
		return mailCheckerFolder;
	}
	public String getOnErrorSendEmailTo() {
		return onErrorSendEmailTo;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public String getWeatherStormGlassKey() {
		return weatherStormGlassKey;
	}
	public String getMeteoblueKey() {
		return meteoblueKey;
	}
}
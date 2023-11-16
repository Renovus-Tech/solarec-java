package tech.renovus.solarec.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RenovusSolarConfiguration {

	//--- Chart configuration -------------------
	@Value("${falconer.chart.solar.overview.url}")			private String chartOverviewUrl;
	@Value("${falconer.chart.solar.performanceIndex.url}")	private String chartPerformanceIndexUrl;
	@Value("${falconer.chart.solar.climate.url}")			private String chartClimateUrl;
	@Value("${falconer.chart.solar.powerCurve.url}")		private String chartPowerCurveUrl;

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
}
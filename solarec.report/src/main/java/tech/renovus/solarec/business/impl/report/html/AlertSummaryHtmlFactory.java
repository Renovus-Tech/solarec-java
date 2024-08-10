package tech.renovus.solarec.business.impl.report.html;

import java.io.IOException;

import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.report.html.basic.BasicHtmlFactory;
import tech.renovus.solarec.business.impl.report.html.basic.config.ChartOptions;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.custom.chart.climate.Climate;
import tech.renovus.solarec.vo.custom.chart.climate.ClimateData;
import tech.renovus.solarec.vo.custom.chart.climate.ClimateGenData;
import tech.renovus.solarec.vo.custom.chart.overview.Datum;
import tech.renovus.solarec.vo.custom.chart.overview.Overview;
import tech.renovus.solarec.vo.custom.chart.performanceIndex.DataPerformance;
import tech.renovus.solarec.vo.custom.chart.performanceIndex.GenDatum;
import tech.renovus.solarec.vo.custom.chart.performanceIndex.PerformanceIndex;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

/**
 * 
 * 
- Alerts
  - Time-based availabliity (%) `GET /overview { data.timeBasedAvailability }`
  - Perfromance ratio (%) `GET /overview { data.performanceRatio }`
  - Charts (group by day)
    - Trends `GET /solar/climate { data.data[].genData[].id | productionMwh }`
    - Performance `GET /performanceIndex`
      - Performance ratio `{ data.data[].genData[].id | performanceRatio }`
      - Production and Irradiation `{ data.data[].genData[].id | productionMwh + irradiationKwhM2  }`

 */
@Service
public class AlertSummaryHtmlFactory extends BasicHtmlFactory<ChartFilter> {

	//--- Private properties --------------------
	
	///--- Resources ----------------------------
	
	//--- Private methods ------------------------
	private String createHtml(PerformanceIndex performance) {
		StringBuilder html = new StringBuilder();
		
		DefaultCategoryDataset ratioDataSet			= new DefaultCategoryDataset();
		DefaultCategoryDataset productionDataSet	= new DefaultCategoryDataset();
		DefaultCategoryDataset irradianceDataSet	= new DefaultCategoryDataset();
		
		for (DataPerformance data : performance.getData()) {
			String fromDate = data.getFrom();
			if (fromDate.indexOf(' ') != -1) fromDate = fromDate.substring(0, fromDate.indexOf(' '));
			for (GenDatum genData : data.getGenData()) {
				ratioDataSet.addValue(genData.getPerformanceRatio(), genData.getCode(), fromDate);
				productionDataSet.addValue(genData.getProductionMwh(), genData.getCode(), fromDate);
			}
			
			irradianceDataSet.addValue(data.getTotalIrradiationKwhM2(), "Irradiance", fromDate);
		}
		
        try {
	        html
	        	.append("<h2>Performance Ratio</h2>")
	        	.append("<div class='image'><img src='")
		        .append(this.generateLineChart(
		        		ratioDataSet, 
		        		new ChartOptions()
		        			.withTitle("") 
		        			.withCategoryAxisLabel("") 
		        			.withValueAxisLabel("%")
		        	))
		       .append("'></div>")
		       .append("<h2>Production and Irradiation</h2>")
		       .append("<div class='image'><img src='")
		        .append(this.generateLineChart(
		        		productionDataSet,
		        		irradianceDataSet,
		        		new ChartOptions()
		        			.withTitle("") 
		        			.withCategoryAxisLabel("") 
		        			.withValueAxisLabel("MWh"),
	        			new ChartOptions()
		        			.withTitle("") 
		        			.withCategoryAxisLabel("") 
		        			.withValueAxisLabel("Kwh/m2")
		        	))
		       .append("'></div>")
		      ;
        } catch (IOException e) {
			html.append("<div class='error'><p>Error found: " + e.getLocalizedMessage() + "</p><small>" + StringUtil.toString(e, true) + "</small></div>");
		}
        
		return html.toString();
	}
	
	private String createHtml(Climate climate) {
		StringBuilder html = new StringBuilder();
		
		DefaultCategoryDataset productionDataSet	= new DefaultCategoryDataset();
		DefaultCategoryDataset irradiationDataSet	= new DefaultCategoryDataset();
		
		for (ClimateData data : climate.getData()) {
			String fromDate = data.getFrom();
			if (fromDate.indexOf(' ') != -1) fromDate = fromDate.substring(0, fromDate.indexOf(' '));
			
			for (ClimateGenData genData : data.getGenData()) {
				productionDataSet.addValue(genData.getProductionMwh(), genData.getCode(), fromDate);
			}
			
			irradiationDataSet.addValue(data.getTotalIrradiationKwhM2(), "Irradiation", fromDate);
			
		}
		
        try {
	        html
	        	.append("<h2>Trends</h2>")
	        	.append("<div class='image'><img src='")
		        .append(this.generateLineChart(
		        		productionDataSet, 
		        		irradiationDataSet, 
		        		new ChartOptions()
			        		.withTitle("") 
		        			.withCategoryAxisLabel("") 
		        			.withValueAxisLabel("MWh"),
		        		new ChartOptions()
			        		.withTitle("") 
		        			.withCategoryAxisLabel("") 
		        			.withValueAxisLabel("Kwh/m2")
		        	))
		       .append("'></div>");
        } catch (IOException e) {
			html.append("<div class='error'><p>Error found: " + e.getLocalizedMessage() + "</p><small>" + StringUtil.toString(e, true) + "</small></div>");
		}
		
		return html.toString();
	}
	
	private String createHtml(Overview overview) {
		
		StringBuilder html = new StringBuilder();
		
		double timeBasedAvailability	= 0;
		double performanceRatio			= 0;
		int amount						= 0;
		
		if (overview != null && CollectionUtil.notEmpty(overview.getData())) {
			for (Datum data : overview.getData()) {
				if (data != null) {
					timeBasedAvailability	+= data.getTimeBasedAvailability();
					performanceRatio		+= data.getPerformanceRatio();
					
					amount ++;
				}
			}
		}
		
		timeBasedAvailability /= amount;
		performanceRatio /= amount;
		
		html
			.append("Time-based availabliity: <strong>")
			.append(timeBasedAvailability)
			.append(" %")	
			.append("</strong>")
			.append("<br><br>")
		
			.append("Performance ratio: <strong>")
			.append(performanceRatio)
			.append(" %")	
			.append("</strong>")
			.append("<br><br>");
		
		return html.toString();
	}
	
	//--- Overridden methods ---------------------
	@Override public String getTitle() { return "Alerts"; }
	@Override public String createStyle() { return StringUtil.EMPTY_STRING; }
	
	@Override public String createHtml(UserData userData, ChartFilter filter) {
		StringBuilder html = new StringBuilder();
		
		html.append("<div class='section alerts'><h1>" + this.getTitle() + "</h1>");
		html.append("<div class='content'>");
		
		filter.setGenerators(null);
		filter.setStations(null);
		filter.setGroupBy(ChartFilter.GROUP_BY_WEEK);
		
		filter = this.chartService.validate(filter, userData);
		
		try {
			String result	= (String) this.chartService.runOverview(filter, userData);
			Overview call	= JsonUtil.toObject(result, Overview.class);
			
			String error	= call.getError();
			if (error != null) {
				html.append(error);
			} else {
				html.append(this.createHtml(call));
			}
			
		} catch (JsonProcessingException | CoreException e) {
			html.append(this.generateHtmlError(e));
		}
		
		try {
			filter.setGroupBy(ChartFilter.GROUP_BY_DAY);
			
			String result	= (String) this.chartService.runClimate(filter, userData);
			Climate call	= JsonUtil.toObject(result, Climate.class);
			
			String error	= call.getError();
			if (error != null) {
				html.append(error);
			} else {
				html.append(this.createHtml(call));
			}
			
		} catch (JsonProcessingException | CoreException e) {
			html.append(this.generateHtmlError(e));
		}
		
		try {
			filter.setGroupBy(ChartFilter.GROUP_BY_DAY);
			
			String result			= (String) this.chartService.runPerformanceIndex(filter, userData);
			PerformanceIndex call	= JsonUtil.toObject(result, PerformanceIndex.class);
			
			Error error	= call.getError();
			if (error != null) {
				html.append(error.getMessage());
			} else {
				html.append(this.createHtml(call));
			}
			
		} catch (JsonProcessingException | CoreException e) {
			html.append(this.generateHtmlError(e));
		}
			
		html
			.append(this.generatePeriodHtml(filter))
			.append("</div></div>");
		
		return html.toString();
	}

	
}

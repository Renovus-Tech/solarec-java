package tech.renovus.solarec.business.impl.report.html;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.report.html.basic.BasicHtmlFactory;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.custom.chart.overview.Datum;
import tech.renovus.solarec.vo.custom.chart.overview.Overview;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

/**
 * 
 *
Production and climate
Production GET /overview { data.production }
Irradiation GET /overview { data.irradiationKwhM2 }
Average ambient temperature GET /overview { data.agvgAmbientTemp }
 */
@Service
public class ProductionAndClimateHtmlFactory extends BasicHtmlFactory<ChartFilter> {

	//--- Private properties --------------------
	
	///--- Resources ----------------------------
	
	//--- Private methods ------------------------
	private Object createHtml(Overview overview, UserData userData) {
		
		StringBuilder html = new StringBuilder();
		
		double production		= 0;
		double irradiation		= 0;
		double avgAmbientTemp	= 0;
		
		if (overview != null && CollectionUtil.notEmpty(overview.getData())) {
			for (Datum data : overview.getData()) {
				if (data != null) {
					production		+= data.getProductionMwh();
					irradiation		+= data.getIrradiationKwhM2();
					avgAmbientTemp	+= data.getAvgAmbientTemp();
				}
			}
		}
		
		html
			.append(this.translationService.forLabel(userData.getLocale(), "report.result.prod_and_weather.production"))
			.append(": <strong>")
			.append(production)
			.append(" MWh")	
			.append("</strong>")
			.append("<br><br>")

			.append(this.translationService.forLabel(userData.getLocale(), "report.result.prod_and_weather.irradiation"))
			.append(": <strong>")
			.append(irradiation)
			.append(" Kwh/m2")	
			.append("</strong>")
			.append("<br><br>")
		
			.append(this.translationService.forLabel(userData.getLocale(), "report.result.prod_and_weather.avt_env_temp"))
			.append(": <strong>")
			.append(avgAmbientTemp)
			.append(" °C")
			.append("</strong>")
			.append("<br><br>");
			
		
		return html.toString();
	}
	
	//--- Overridden methods ---------------------
	@Override public String getTitle() { return "report.result.prod_and_weather.title"; }
	@Override public String createStyle() { return StringUtil.EMPTY_STRING; }
	
	@Override public String createHtml(UserData userData, ChartFilter filter) {
		StringBuilder html = new StringBuilder();
		
		html
			.append("<div class='section alerts'><h1>")
			.append(this.translationService.forLabel(userData.getLocale(), this.getTitle()))
			.append("</h1><div class='content'>");
		
		filter.setGenerators(null);
		filter.setStations(null);
		filter.setGroupBy(ChartFilter.GROUP_BY_WEEK);
		
		filter = this.chartService.validate(filter, userData);
		
		
		try {
			String result = (String) this.chartService.runOverview(filter, userData);
			Overview callResult = JsonUtil.toObject(result, Overview.class);
			
			String error				= callResult.getError();
			if (error != null) {
				html.append(error);
			} else {
				html.append(this.createHtml(callResult, userData));
			}
			
		} catch (JsonProcessingException | CoreException e) {
			html.append(this.generateHtmlError(e));
		} finally {
			html.append(this.generatePeriodHtml(filter, userData));
		}
			
		html.append("</div></div>");
		
		return html.toString();
	}

	
}

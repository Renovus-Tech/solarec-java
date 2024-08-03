package tech.renovus.solarec.business.impl.report.html;

import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.report.html.basic.BasicHtmlFactory;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@Service
public class ProductionAndClimateHtmlFactory extends BasicHtmlFactory<ChartFilter> {

	//--- Private properties --------------------
	
	///--- Resources ----------------------------
	
	//--- Private methods ------------------------
//	private Object createHtml(Alerts alerts) {
//		
//		StringBuilder html = new StringBuilder();
//		
//		html.append("Wind turbines with performance < 90% (current month): <strong>");	
//		if (alerts.getAlert1Error() != null) {
//			html.append("Error: "+alerts.getAlert1Error());
//		} else {
//			html.append(StringUtil.SPACE_STRING);
//			if (alerts.getAlerts1() != null) {
//				html.append(String.join(", ", alerts.getAlerts1()));
//			} else {
//				html.append(" - ");
//			}
//		}
//		html.append("</strong>");
//		html.append("<br><br>");
//		
//		html.append("Wind turbines with negative change exceeding -6% in performance (yesterday): <strong>");
//		if (alerts.getAlert2Error() != null) {
//			html.append("Error: "+alerts.getAlert2Error());
//		} else {
//			html.append(StringUtil.SPACE_STRING);
//			if (alerts.getAlerts2() != null) {
//				html.append(String.join(", ", alerts.getAlerts2()));
//			} else {
//				html.append(" - ");
//			}
//		}
//		html.append("</strong>");
//		html.append("<br><br>");
//		
//		html.append("Wind turbines with total stopped time > 10.0 hours (yesterday): <strong>");
//		if (alerts.getAlert3Error() != null) {
//			html.append("Error: "+alerts.getAlert3Error());
//		} else {
//			html.append(StringUtil.SPACE_STRING);
//			if (alerts.getAlerts3() != null) {
//				html.append(String.join(", ", alerts.getAlerts3()));
//			} else {
//				html.append(" - ");
//			}
//		}
//		html.append("</strong>");
//		html.append("<br><br>");
//		
//		html.append("< 90% data available (yesterday): <strong>");
//		if (alerts.getAlert4Error() != null) {
//			html.append("Error: "+alerts.getAlert4Error());
//		} else {
//			html.append(StringUtil.SPACE_STRING);
//			if (alerts.getAlerts4() != null) {
//				html.append(String.join(", ", alerts.getAlerts4()));
//			} else {
//				html.append(" - ");
//			}
//		}
//		html.append("</strong>");
//		html.append("<br><br>");
//		return html.toString();
//	}
	
	//--- Overridden methods ---------------------
	@Override public String getTitle() { return "Production and climate"; }
	@Override public String createStyle() { return StringUtil.EMPTY_STRING; }
	
	@Override public String createHtml(UserData userData, ChartFilter filter) {
		StringBuilder html = new StringBuilder();
		
		html.append("<div class='section alerts'><h1>" + this.getTitle() + "</h1>");
		html.append("<div class='content'>");
		
		filter.setTo(filter.getFrom());
		filter.setGenerators(null);
		filter.setStations(null);
		filter.setGroupBy("day");
		
		filter = this.chartService.validate(filter, userData);
		
//		try {
//			Alerts callResult		= (Alerts) this.chartService.alerts(filter, userData);
//			String error				= callResult.getAlert1Error();
//			if (error != null) {
//				html.append(error);
//			} else {
//				html.append(this.createHtml(callResult));
//			}
//			
//		} catch (CoreException e) {
//			html.append(this.generateHtmlError(e));
//		}
			
		html.append("</div></div>");
		
		return html.toString();
	}

	
}

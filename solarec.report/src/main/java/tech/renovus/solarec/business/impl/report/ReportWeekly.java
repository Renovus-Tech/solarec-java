package tech.renovus.solarec.business.impl.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.report.html.AlertSummaryHtmlFactory;
import tech.renovus.solarec.business.impl.report.html.ProductionAndClimateHtmlFactory;
import tech.renovus.solarec.business.impl.report.html.basic.IReportHtml;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.report.ReportRequest;
import tech.renovus.solarec.vo.report.ReportResponse;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@Service
public class ReportWeekly extends BaseReport {

	//--- Resources -----------------------------
	@Autowired private ProductionAndClimateHtmlFactory productionAndClimate;
	@Autowired private AlertSummaryHtmlFactory alertSummary;

	//--- Overridden methods --------------------
	@Override public ReportResponse generate(ReportRequest request) {
		UserData userData		= this.getUserData(request.getCliId(), Integer.valueOf(0));
		if (userData.getClientVo() == null) {
			return null;
		}
		
		LocationVo locVo = this.locationDao.findVo(request.getCliId(), request.getLocId());
		
		Collection<IReportHtml<ChartFilter>> factories = new ArrayList<>();
		factories.add(this.productionAndClimate);
		factories.add(this.alertSummary);
		
		userData.setLocationVo(locVo);
		
		System.out.println("Doing weekly report for: " + userData.getCliName() + " - " + userData.getLocName());
		
		ChartFilter filter 			= new ChartFilter();
		Date firstDate			= request.getTheDate();
		
		filter.setType(ChartFilter.TYPE_JSON);
		filter.setFrom(firstDate);
		filter.setTo(DateUtil.addUnit(firstDate, Calendar.DAY_OF_MONTH, 6));
		filter.setPeriod(null);
		filter.setClient(userData.getCliId());
		filter.setLocation(userData.getLocId());
		
		filter = this.service.validate(filter, userData);
		
		return this.generateHtmlFor("Weekly", locVo, filter, factories, userData);
	}
}

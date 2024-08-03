package tech.renovus.solarec.business.impl.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.report.html.AlertSummaryHtmlFactory;
import tech.renovus.solarec.business.impl.report.html.ProductionAndClimateHtmlFactory;
import tech.renovus.solarec.business.impl.report.html.basic.IReportHtml;
import tech.renovus.solarec.util.CollectionUtil;
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
	@Override public List<ReportResponse> generate(ReportRequest request) {
		UserData userData		= this.getUserData(request.getCliId(), Integer.valueOf(0));
		if (userData.getClientVo() == null) {
			return null;
		}
		
		Collection<LocationVo> locations = null;
		if (CollectionUtil.isEmpty(request.getLocIds())) {
			locations = this.locationDao.findAll(request.getCliId());
		} else {
			locations = new ArrayList<>(CollectionUtil.size(request.getLocIds()));
			for (Integer locId : request.getLocIds()) {
				locations.add(this.locationDao.findVo(request.getCliId(), locId));
			}
		}
		
		List<ReportResponse> result = new ArrayList<>(CollectionUtil.size(locations));
		
		Collection<IReportHtml<ChartFilter>> factories = new ArrayList<>();
		factories.add(this.productionAndClimate);
		factories.add(this.alertSummary);
		
		for (LocationVo locVo : locations) {
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
			
			ReportResponse response = this.generateHtmlFor("Weekly", locVo, filter, factories, userData);
			result.add(response);

		}
		
		return result;
	}
}

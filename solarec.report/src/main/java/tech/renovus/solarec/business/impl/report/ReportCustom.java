package tech.renovus.solarec.business.impl.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.report.html.basic.IReportHtml;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.report.ReportRequest;
import tech.renovus.solarec.vo.report.ReportResponse;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@Service
public class ReportCustom extends BaseReport {

	//--- Resources -----------------------------
	@Autowired private List<IReportHtml<ChartFilter>> htmlReports;

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
		
			Collection<IReportHtml<ChartFilter>> reports = new ArrayList<>();
			if (CollectionUtil.notEmpty(request.getSections())) {
				for (String section : request.getSections()) {
					for (IReportHtml<ChartFilter> htmlReport : this.htmlReports) {
						if (ClassUtil.equals(htmlReport.getClass().getSimpleName(), section)) {
							reports.add(htmlReport);
						}
					}
				}
			}
			
			for (LocationVo locVo : locations) {
				userData.setLocationVo(locVo);
				
				ChartFilter filter 			= new ChartFilter();
				
				filter.setType(ChartFilter.TYPE_JSON);
				filter.setFrom(request.getTheDate());
				filter.setTo(request.getTheDateTo());
				filter.setPeriod(null);
				filter.setClient(userData.getCliId());
				filter.setLocation(userData.getLocId());
				
				filter = this.service.validate(filter, userData);
				
				ReportResponse response = this.generateHtmlFor("Custom", locVo, filter, reports, userData);
				result.add(response);
			}
		
		return result;
	}
}

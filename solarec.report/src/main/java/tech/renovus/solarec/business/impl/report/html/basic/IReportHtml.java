package tech.renovus.solarec.business.impl.report.html.basic;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.ReportException;
import tech.renovus.solarec.vo.rest.chart.IFilter;

public interface IReportHtml <T extends IFilter> {

	public abstract String getTitle();
	public abstract String createStyle();
	public abstract String createHtml(UserData userData, T filter) throws ReportException;
}

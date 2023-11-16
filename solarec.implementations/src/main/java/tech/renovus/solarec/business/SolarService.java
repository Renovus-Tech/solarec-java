package tech.renovus.solarec.business;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.custom.chart.revenue.Revenue;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;
import tech.renovus.solarec.vo.rest.chart.IFilter;

public interface SolarService {

	//--- Public methods ------------------------
	default <T extends IFilter> T validate(T filter, UserData userData) {
		if (filter instanceof ChartFilter) return (T) this.validate((ChartFilter) filter, userData);
		return filter;
	}
	
	ChartFilter validate(ChartFilter filter, UserData userData);
	
	//--- Dashboards ----------------------------
//	Object dashboard(boolean forNow, UserData userData) throws CoreException;
	
	//--- Chart methods -------------------------
	Object runOverview(ChartFilter filter, UserData userData) throws CoreException;
	Object runClimate(ChartFilter filter, UserData userData) throws CoreException;
	Object runPerformanceIndex(ChartFilter filter, UserData loggedUserData) throws CoreException;

	Revenue revenue(ChartFilter filter, UserData loggedUserData);
	Revenue revenueSales(ChartFilter filter, UserData loggedUserData);
	
}

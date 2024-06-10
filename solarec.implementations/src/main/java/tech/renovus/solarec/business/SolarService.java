package tech.renovus.solarec.business;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;
import tech.renovus.solarec.vo.rest.chart.IFilter;

public interface SolarService {

	//--- Public methods ------------------------
	default <T extends IFilter> T validate(T filter, UserData userData) {
		if (filter instanceof ChartFilter) return (T) this.validate((ChartFilter) filter, userData);
		return filter;
	}
	
	ChartFilter validate(ChartFilter filter, UserData userData);
	
	//--- Chart methods -------------------------
	Object retrieveOverviewAlerts(ChartFilter filter, UserData userData) throws CoreException;
	Object retrieveOverviewCo2(ChartFilter filter, UserData userData) throws CoreException;
	
	Object runOverview(ChartFilter filter, UserData userData) throws CoreException;
	Object runClimate(ChartFilter filter, UserData userData) throws CoreException;
	Object runPerformanceIndex(ChartFilter filter, UserData loggedUserData) throws CoreException;

	Object revenue(ChartFilter filter, UserData loggedUserData) throws CoreException;
	Object revenueSales(ChartFilter filter, UserData loggedUserData) throws CoreException;

	
}

package tech.renovus.solarec.api.rest.controller.chart;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.business.SolarService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@RestController
public class SolarController extends BasicController {

	//--- Resources -----------------------------
	@Resource SolarService service;

	//--- Get mapping methods -------------------
//	@GetMapping(path = { EndPointFactory.REST_API_SOLAR_DASHBOARD }, produces = { MediaType.APPLICATION_JSON_VALUE } )
//	public Object getDashboard(HttpSession session) throws CoreException { return this.dashobard(session); }
//	
//	@GetMapping(path = { EndPointFactory.REST_API_SOLAR_DASHBOARD_NOW }, produces = { MediaType.APPLICATION_JSON_VALUE } )
//	public Object getDashboardNow(HttpSession session) throws CoreException { return this.dashobardNow(session); }
	
	@GetMapping(path = { EndPointFactory.REST_API_SOLAR_OVERVIEW }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object getOverview(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException { return this.overview(filter, session); }

	@GetMapping(path = { EndPointFactory.REST_API_SOLAR_PERFORMANCE_INDEX }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object getPerformanceIndex(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException { return this.performanceIndex(filter, session); }

	@GetMapping(path = { EndPointFactory.REST_CHART_REVENUE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object getRevenue(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException { return this.revenue(filter, session); }
	
	@GetMapping(path = { EndPointFactory.REST_CHART_REVENUE_SALES }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object getRevenueSales(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException { return this.revenueSales(filter, session); }
	
	
	//--- Post mapping methods ------------------
//	@PostMapping(path = { EndPointFactory.REST_API_SOLAR_DASHBOARD }, produces = { MediaType.APPLICATION_JSON_VALUE } )
//	public Object dashobard(HttpSession session) throws CoreException {
//		return this.service.dashboard(false, this.getLoggedUserData(session));
//	}
//	
//	@PostMapping(path = { EndPointFactory.REST_API_SOLAR_DASHBOARD_NOW }, produces = { MediaType.APPLICATION_JSON_VALUE } )
//	public Object dashobardNow(HttpSession session) throws CoreException {
//		return this.service.dashboard(true, this.getLoggedUserData(session));
//	}
	
	@PostMapping(path = { EndPointFactory.REST_API_SOLAR_OVERVIEW }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object overview(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException {
		filter = this.service.validate(filter, this.getLoggedUserData(session));
		return this.service.runOverview(filter, this.getLoggedUserData(session));
	}
	
	@PostMapping(path = { EndPointFactory.REST_API_SOLAR_PERFORMANCE_INDEX }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object performanceIndex(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException {
		filter = this.service.validate(filter, this.getLoggedUserData(session));
		return this.service.runPerformanceIndex(filter, this.getLoggedUserData(session));
	}
	
	@PostMapping(path = { EndPointFactory.REST_API_SOLAR_CLIMATE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object climate(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException {
		filter = this.service.validate(filter, this.getLoggedUserData(session));
		return this.service.runClimate(filter, this.getLoggedUserData(session));
	}
	
	@PostMapping(path = { EndPointFactory.REST_CHART_REVENUE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object revenue(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException {
		filter = this.service.validate(filter, this.getLoggedUserData(session));
		return this.service.revenue(filter, this.getLoggedUserData(session));
	}
	
	@PostMapping(path = { EndPointFactory.REST_CHART_REVENUE_SALES }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object revenueSales(@RequestBody(required = false) ChartFilter filter, HttpSession session) throws CoreException {
		filter = this.service.validate(filter, this.getLoggedUserData(session));
		return this.service.revenueSales(filter, this.getLoggedUserData(session));
	}
}

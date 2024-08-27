package tech.renovus.solarec.api.rest.controller.report;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.business.ReportService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.report.ReportConfiguration;
import tech.renovus.solarec.vo.report.ReportGeneration;

@RestController
public class ReportController extends BasicController {

	//--- Resources -----------------------------
	@Resource ReportService reportService;

	//--- Get mapping methods -------------------
	@GetMapping(path = { EndPointFactory.REST_API_REPORT_CONFIGURE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object getConfiguration(HttpSession session) throws CoreException {
		return this.reportService.getConfiguration(this.getLoggedUserData(session));
	} 
	
	@GetMapping(path = { EndPointFactory.REST_API_REPORT_GENERATE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object getGenerate(HttpSession session) throws CoreException {
		return this.reportService.getGenerateOptions(this.getLoggedUserData(session));
	} 
	
	//--- Post mapping methods ------------------
	@PostMapping(path = { EndPointFactory.REST_API_REPORT_CONFIGURE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object setConfiguration(@RequestBody ReportConfiguration configuration, HttpSession session) throws CoreException {
		return this.reportService.setConfiguration(configuration, this.getLoggedUserData(session));
	}
	
	@PostMapping(path = { EndPointFactory.REST_API_REPORT_GENERATE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
	public Object doGenerate(@RequestBody ReportGeneration generation, HttpSession session) throws CoreException {
		return this.reportService.doGenerate(generation, this.getLoggedUserData(session));
	}
	
}
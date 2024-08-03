package tech.renovus.solarec.business;

import java.util.List;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.report.ReportConfiguration;
import tech.renovus.solarec.vo.report.ReportGeneration;
import tech.renovus.solarec.vo.report.ReportRequest;
import tech.renovus.solarec.vo.report.ReportResponse;

public interface ReportService {

	List<ReportResponse> generateReport(ReportRequest request) throws CoreException;

	ReportConfiguration getConfiguration(UserData loggedUserData);
	ReportConfiguration setConfiguration(ReportConfiguration configuration, UserData loggedUserData);

	ReportGeneration getGenerateOptions(UserData loggedUserData);
	ReportGeneration doGenerate(ReportGeneration generation, UserData loggedUserData);
}

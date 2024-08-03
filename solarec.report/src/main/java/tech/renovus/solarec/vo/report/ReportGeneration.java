package tech.renovus.solarec.vo.report;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportGeneration {

	//--- Private properties --------------------
	private ReportDefinition report;
	private List<ReportDefinition> reports;
	
	private Boolean generated;
	private String willSendTo;

	//--- Getters and Setters -------------------
	public ReportDefinition getReport() {
		return report;
	}

	public void setReport(ReportDefinition report) {
		this.report = report;
	}

	public List<ReportDefinition> getReports() {
		return reports;
	}

	public void setReports(List<ReportDefinition> reports) {
		this.reports = reports;
	}

	public Boolean getGenerated() {
		return generated;
	}

	public void setGenerated(Boolean generated) {
		this.generated = generated;
	}

	public String getWillSendTo() {
		return willSendTo;
	}

	public void setWillSendTo(String willSendTo) {
		this.willSendTo = willSendTo;
	}
}

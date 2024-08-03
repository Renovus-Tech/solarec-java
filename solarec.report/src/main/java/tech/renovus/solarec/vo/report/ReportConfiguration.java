package tech.renovus.solarec.vo.report;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import tech.renovus.solarec.vo.rest.entity.Location;
import tech.renovus.solarec.vo.rest.entity.LocationReport;
import tech.renovus.solarec.vo.rest.entity.Report;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportConfiguration {

	//--- Private properties --------------------
	private List<Report> reports;
	private List<Location> locations;
	private Collection<LocationReport> settings;
	
	private Boolean loaded;
	private Boolean saved;
	
	//--- Getters and Setters -------------------
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public Boolean getSaved() {
		return saved;
	}
	public void setSaved(Boolean saved) {
		this.saved = saved;
	}
	public Collection<LocationReport> getSettings() {
		return settings;
	}
	public void setSettings(Collection<LocationReport> settings) {
		this.settings = settings;
	}
	public Boolean getLoaded() {
		return loaded;
	}
	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}
}

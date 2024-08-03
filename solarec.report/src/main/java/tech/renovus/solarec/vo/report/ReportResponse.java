package tech.renovus.solarec.vo.report;

public class ReportResponse {

	//--- Private properties --------------------
	private Integer cliId;
	private Integer locId;
	private String name;
	private String path;
	private boolean generated;
	private String error;
	
	//--- Build methods -------------------------
	public ReportResponse withCliId(Integer cliId) { this.cliId = cliId; return this; }
	public ReportResponse withLocId(Integer locId) { this.locId = locId;; return this; }
	public ReportResponse withName(String name) { this.name = name; return this; }
	public ReportResponse withPath(String path) { this.path = path; return this; }
	public ReportResponse withGenerated(boolean generated) { this.generated = generated; return this; }
	public ReportResponse withError(String error) { this.error = error; return this; }
	
	//--- Getters and Setters -------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String location) {
		this.path = location;
	}
	public Integer getCliId() {
		return cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public boolean isGenerated() {
		return generated;
	}
	public void setGenerated(boolean generated) {
		this.generated = generated;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}

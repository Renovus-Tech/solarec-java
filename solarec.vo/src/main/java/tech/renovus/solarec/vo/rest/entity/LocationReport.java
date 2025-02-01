package tech.renovus.solarec.vo.rest.entity;

public class LocationReport implements Comparable<LocationReport> {

	//--- Private properties --------------------
	private Integer locId;
	private Integer repId;
	
	private boolean selected;
	
	//--- Constructors --------------------------
	public LocationReport() {}
	
	public LocationReport(Integer locId, Integer repId) {
		this.locId = locId;
		this.repId = repId;
	}
	
	public LocationReport(Integer locId, Integer repId, boolean selected) {
		this.locId		= locId;
		this.repId		= repId;
		this.selected	= selected;
	}
	
	//--- Overridden methods --------------------
	@Override public int compareTo(LocationReport obj) {
		int result = 0;
		if (result == 0) {
			result = this.locId.compareTo(obj.locId);
		}
		if (result == 0) {
			result = this.repId.compareTo(obj.repId);
		}
		
		return result;
	}

	//--- Getters and Setteers ------------------
	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getRepId() {
		return repId;
	}

	public void setRepId(Integer repId) {
		this.repId = repId;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

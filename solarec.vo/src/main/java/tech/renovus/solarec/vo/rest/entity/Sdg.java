package tech.renovus.solarec.vo.rest.entity;

public class Sdg {

	//--- Private properties --------------------
	private String code;
	private String name;
	private String description;
	private Boolean selected;
	
	//--- Getters and Setters -------------------
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}

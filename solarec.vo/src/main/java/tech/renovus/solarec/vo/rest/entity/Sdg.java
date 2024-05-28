package tech.renovus.solarec.vo.rest.entity;

import tech.renovus.solarec.vo.rest.entity.base.BaseEntity;

public class Sdg extends BaseEntity {

	//--- Private properties --------------------
	private Boolean selected;
	
	//--- Getters and Setters -------------------
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}

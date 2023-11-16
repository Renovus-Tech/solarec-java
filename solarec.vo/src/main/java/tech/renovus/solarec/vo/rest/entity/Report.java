package tech.renovus.solarec.vo.rest.entity;

public class Report {

	//--- Private properties --------------------
	private Integer id;
	private String title;
	private Integer order;
	
	//--- Getters and Setters -------------------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}

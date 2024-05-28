package tech.renovus.solarec.vo.rest.entity.base;

public class BaseEntity {

	//--- Private constants ---------------------
	private String code;
	private String name;
	private String description;
	
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

}

package tech.renovus.solarec.vo.rest.entity;

public class Country {

	//--- Private properties --------------------
	private String name;
	private String iso2;
	private String iso3;
	private String phone;
	
	//--- Getters and Setters -------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIso2() {
		return iso2;
	}
	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}
	public String getIso3() {
		return iso3;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}

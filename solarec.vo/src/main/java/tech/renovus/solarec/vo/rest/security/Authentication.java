package tech.renovus.solarec.vo.rest.security;

public class Authentication {

	//--- Private properties --------------------
	private String email;
	private String password;
	private String client;
	private String location;
	
	//--- Getters and Setters -------------------
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}

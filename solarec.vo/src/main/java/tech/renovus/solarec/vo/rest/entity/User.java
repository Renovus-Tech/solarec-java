package tech.renovus.solarec.vo.rest.entity;

import java.util.Collection;

public class User {

	//--- Private properties --------------------
	private Integer id;
	private String name;
	private String email;
	private boolean authenticated;
	private String error;
	private Integer errorCode;

	private Client client;
	private Location location;
	private Collection<Functionality> functionalities;
	private Collection<Setting> settings;
	
	//--- Getters and Setters -------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Collection<Functionality> getFunctionalities() {
		return functionalities;
	}
	public void setFunctionalities(Collection<Functionality> functionalities) {
		this.functionalities = functionalities;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Collection<Setting> getSettings() {
		return settings;
	}
	public void setSettings(Collection<Setting> settings) {
		this.settings = settings;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}

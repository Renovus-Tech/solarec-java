package tech.renovus.solarec.inverters.common;

public class InverterCofigurationVo {

	//--- Private propreties --------------------
	private String user;
	private String password;
	private String key;

	//--- Constructors --------------------------
	public InverterCofigurationVo(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public InverterCofigurationVo(String user, String password, String key) {
		this.user = user;
		this.password = password;
		this.key = key;
	}
	
	//--- Getters and Setters -------------------
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	public String getKey() {
		return key;
	}
}

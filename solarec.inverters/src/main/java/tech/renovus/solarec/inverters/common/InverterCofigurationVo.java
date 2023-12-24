package tech.renovus.solarec.inverters.common;

public class InverterCofigurationVo {

	//--- Private propreties --------------------
	private String user;
	private String password;

	//--- Constructors --------------------------
	public InverterCofigurationVo(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	//--- Getters and Setters -------------------
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
}

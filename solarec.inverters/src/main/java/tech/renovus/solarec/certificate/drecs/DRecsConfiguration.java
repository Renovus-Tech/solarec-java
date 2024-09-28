package tech.renovus.solarec.certificate.drecs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DRecsConfiguration {
	
	//--- API configuration ---------------------
	@Value("${drecs.user}")			private String user;
	@Value("${drecs.password}")		private String password;
	

	//--- Getters methods -----------------------
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
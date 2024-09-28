package tech.renovus.solarec.certificate.irec.br;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IrecBrConfiguration {
	
	//--- API configuration ---------------------
	@Value("${irecbr.url}")				private String url;
	@Value("${irecbr.user}")			private String user;
	@Value("${irecbr.password}")		private String password;
	
	@Value("${irecbr.id.source}")		private String idSource;
	@Value("${irecbr.id.technology}")	private String idTechnology;
	

	//--- Getters methods -----------------------
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getIdSource() {
		return idSource;
	}
	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}
	public String getIdTechnology() {
		return idTechnology;
	}
	public void setIdTechnology(String idTechnology) {
		this.idTechnology = idTechnology;
	}
	
}
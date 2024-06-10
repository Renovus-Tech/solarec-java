package tech.renovus.solarec.api.rest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import tech.renovus.solarec.UserData;

public class BasicController {
	
	//--- Private constants ---------------------
	private static final String USER_DATA	= "userData";
	
	//--- Protected methods ---------------------
	protected UserData getUserData(HttpSession session) {
		UserData userData = (UserData) session.getAttribute(USER_DATA);
		
		if (userData == null) userData = this.createUserData(session);
		
		return userData;
	}

	protected UserData getLoggedUserData(HttpSession session) {
		UserData userData = (UserData) session.getAttribute(USER_DATA);
		
		if (userData == null || ! userData.isLogged()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error, user not logged");
		
		return userData;
	}
	
	private UserData createUserData(HttpSession session) {
		UserData userData = new UserData();
		session.setAttribute(USER_DATA, userData);
		
		return userData;
	}
}

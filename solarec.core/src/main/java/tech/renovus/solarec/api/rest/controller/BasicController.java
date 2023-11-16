package tech.renovus.solarec.api.rest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import tech.renovus.solarec.UserData;

public class BasicController {
	
//	@Autowired private Environment environment;
	
	//--- Private constants ---------------------
	private static final String USER_DATA	= "userData";
	
//	private static UserData FOR_DEV	= null;
	
	//--- Protected methods ---------------------
	protected UserData getUserData(HttpSession session) {
		UserData userData = (UserData) session.getAttribute(USER_DATA);
		
		if (userData == null) userData = this.createUserData(session);
		
		return userData;
	}

	protected UserData getLoggedUserData(HttpSession session) {
		UserData userData = (UserData) session.getAttribute(USER_DATA);
		
		if (userData == null || ! userData.isLogged()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error, user not logged");
//		if (userData == null || ! userData.isLogged()) userData = this.createUserData(session);
		
		return userData;
	}
	
	private UserData createUserData(HttpSession session) {
//		if (Arrays.stream(this.environment.getActiveProfiles()).anyMatch("dev"::equals) && FOR_DEV != null) return FOR_DEV;
		
		UserData userData = new UserData();
		session.setAttribute(USER_DATA, userData);
		
//		userData.setUserVo(new UsersVo(Integer.valueOf(1)));
//		userData.setClientVo(new ClientVo(Integer.valueOf(3)));
//		userData.setLocationVo(new LocationVo(Integer.valueOf(3), Integer.valueOf(4)));
		
//		FOR_DEV = userData;
		
		return userData;
	}
}

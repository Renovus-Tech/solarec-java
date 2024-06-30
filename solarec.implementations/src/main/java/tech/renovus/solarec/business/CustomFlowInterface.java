package tech.renovus.solarec.business;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.vo.rest.security.Authentication;

public interface CustomFlowInterface {

	boolean afterAuthentication(Authentication authentication, UserData userData);
	void beforeSendingToHomepage(UserData userData);
	void beforeLogOut(UserData userData);


}

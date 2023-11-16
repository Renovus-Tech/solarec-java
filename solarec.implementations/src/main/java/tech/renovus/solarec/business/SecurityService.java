package tech.renovus.solarec.business;

import java.util.Collection;
import java.util.Locale;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.User;
import tech.renovus.solarec.vo.rest.security.Authentication;
import tech.renovus.solarec.vo.rest.security.PasswordReset;

public interface SecurityService {

	//--- Public constants ----------------------
	public static final int AUTHENTICATION_NOT_ALLOWED						= -3;
	public static final int AUTHENTICATION_ERROR_BAD_EMAIL_PASSWORD_CLIENT	= -2;
	public static final int AUTHENTICATION_NOT_LOGGED						= -1;
	
	public static final int AUTHENTICATION_LOGGED							= 1;
	
	//--- Public methods ------------------------
	void authenticate(Authentication authentication, UserData userData);
	void doLogout(UserData userData);
	Collection<ClientVo> listClients(UserData userData);
	void setClient(Integer clidId, UserData userData);
	void setLocation(Integer locId, UserData userData);
	Collection<LocationVo> getLocations(UserData userData);
	
	void startPasswordReset(String email, Locale locale);
	PasswordReset doPassworReset(PasswordReset passwordReset, UserData userData);
	void setUserData(User user, UserData userData);
}

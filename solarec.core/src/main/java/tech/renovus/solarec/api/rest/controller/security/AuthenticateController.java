package tech.renovus.solarec.api.rest.controller.security;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.api.rest.controller.BasicController;
import tech.renovus.solarec.api.rest.controller.EndPointFactory;
import tech.renovus.solarec.api.rest.controller.RestFactory;
import tech.renovus.solarec.business.SecurityService;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.rest.entity.Client;
import tech.renovus.solarec.vo.rest.entity.Location;
import tech.renovus.solarec.vo.rest.entity.User;
import tech.renovus.solarec.vo.rest.security.Authentication;
import tech.renovus.solarec.vo.rest.security.PasswordReset;

@CrossOrigin
@RestController
public class AuthenticateController extends BasicController {

	//--- Resources -----------------------------
	@Resource SecurityService service;
	
	//--- Mapping methods -----------------------
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE)
	public List<Client> listClients(HttpSession session) throws CoreException {
		UserData userData = this.getLoggedUserData(session);
		return RestFactory.getInstance().convertClients(this.service.listClients(userData), userData);
	}
	
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE + "/current")
	public User current(HttpSession session) throws CoreException {
		UserData userData = this.getLoggedUserData(session);
		return RestFactory.getInstance().convert(userData);
	}
	
	@PostMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE + "/current")
	public User setToCurrent(@RequestBody User user, HttpSession session) throws CoreException {
		UserData userData = this.getLoggedUserData(session);
		this.service.setUserData(user, userData);
		return RestFactory.getInstance().convert(userData);
	}
	
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE + "/{id}")
	public User setClient(@PathVariable Integer id, HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		this.service.setClient(id, userData);
		return RestFactory.getInstance().convert(userData);
	}
	
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE_LOCATION)
	public List<Location> getLocations(HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		return RestFactory.getInstance().convertLocations(this.service.getLocations(userData));
	}
	
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE_LOCATION + "/{id}")
	public User setLocation(@PathVariable Integer id, HttpSession session) {
		UserData userData = this.getLoggedUserData(session);
		this.service.setLocation(id, userData);
		return RestFactory.getInstance().convert(userData);
	}
	
	@PostMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE)
	public User authenticate(@RequestBody Authentication authentication, HttpSession session) throws CoreException {
		UserData userData = this.getUserData(session);
		this.service.authenticate(authentication, userData);
		return RestFactory.getInstance().convert(userData);
	}
	
	@DeleteMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE)
	public User logout(HttpSession session) throws CoreException {
		UserData userData = this.getUserData(session);
		this.service.doLogout(userData);
		return RestFactory.getInstance().convert(userData);
	}
	
	//--- Password reset ------------------------
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE_RESET)
	public PasswordReset startPasswordReset(HttpSession session) {
		return new PasswordReset(false);
	}
	
	@GetMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE_RESET + "/{email}")
	public PasswordReset startPasswordReset(@PathVariable String email, HttpSession session) {
		this.service.startPasswordReset(email, Locale.ENGLISH);
		return new PasswordReset(true);
	}
	
	@PostMapping(EndPointFactory.REST_SECURITY_AUTHENTICATE_RESET)
	public PasswordReset doPassworReset(@RequestBody PasswordReset passwordReset, HttpSession session) throws CoreException {
		UserData userData = this.getUserData(session);
		return this.service.doPassworReset(passwordReset, userData);
	}

}

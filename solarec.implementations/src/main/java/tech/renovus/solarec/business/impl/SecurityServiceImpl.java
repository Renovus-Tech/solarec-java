package tech.renovus.solarec.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.SecurityService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliUserDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.FunctionalityDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsrSettingDao;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.CliUserVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;
import tech.renovus.solarec.vo.rest.entity.Setting;
import tech.renovus.solarec.vo.rest.entity.User;
import tech.renovus.solarec.vo.rest.security.Authentication;
import tech.renovus.solarec.vo.rest.security.PasswordReset;

/**
 * Reference i18n: https://lokalise.com/blog/spring-boot-internationalization/
 * Reference i18n: https://howtodoinjava.com/spring-boot2/rest/i18n-internationalization/
 */

@Service
public class SecurityServiceImpl implements SecurityService {

	//--- Resources -----------------------------
	@Autowired EmailService emailService;
	@Autowired RenovusSolarecConfiguration configuration;
	@Autowired MessageSource messageSource;

	@Resource DataDefinitionDao dataDefinitionDao;
	@Resource UsersDao usersDao;
	@Resource UsrSettingDao usrSettingDao;
	@Resource ClientDao clientsDao;
	@Resource CliUserDao cliUserDao;
	@Resource FunctionalityDao functionalitiesDao;
	@Resource LocationDao locationDao;
	
	//--- Private methods -----------------------
	private void logout(UserData userData) {
		userData.setUserVo(null);
		userData.setClientVo(null);
		userData.setFunctionalities(null);
		userData.setLogged(false);
		userData.setAuthenticationError(SecurityService.AUTHENTICATION_NOT_LOGGED);
	}
	
	private void login(UserData userData, UsersVo usrVo, ClientVo cliVo, Collection<FunctionalityVo> functionalities) {
		userData.setUserVo(usrVo);
		userData.setClientVo(cliVo);
		userData.setFunctionalities(functionalities);
		userData.setLogged(true);
	}

	private boolean accessEnable(UsersVo usrVo, ClientVo cliVo) {
		if  (usrVo == null || cliVo == null) return false;
		return this.cliUserDao.findVo(cliVo.getCliId(), usrVo.getUsrId()) != null;
	}

	private boolean authenticate(UsersVo usrVo, String password) {
		if (usrVo == null || StringUtil.isEmpty(password)) return false;
		return ClassUtil.equals(usrVo.getUsrPassword(), password);
	}
	
	private ClientVo getDefaultClient(String cliName, Integer usrId) {
		if (StringUtil.notEmpty(cliName)) return this.clientsDao.findBy(cliName);
		
		Collection<ClientVo> clients = usrId == null ? null : this.clientsDao.findAllForUser(usrId, true);
		return CollectionUtil.notEmpty(clients) ? clients.iterator().next() : null;
	}
	
	private void setDefaultLocation(UserData userData) {
		userData.setLocationVo(null);
		Collection<LocationVo> locations = this.locationDao.findAllForUser(userData.getCliId(), userData.getUsrId(), true);
		if (CollectionUtil.size(locations) >= 1) userData.setLocationVo(locations.iterator().next());
	}

	//--- Overridden methods --------------------
	@Override public void authenticate(Authentication authentication, UserData userData) {
		this.logout(userData);
		
		UsersVo usrVo			= this.usersDao.findBy(authentication.getEmail());
		ClientVo cliVo			= this.getDefaultClient(authentication.getClient(), usrVo != null ? usrVo.getUsrId() : null);
		
		boolean authenticated	= this.authenticate(usrVo, authentication.getPassword());
		boolean accessEnable	= this.accessEnable(usrVo, cliVo);
		
		if (! (authenticated && accessEnable)) {
			userData.setAuthenticationError(SecurityService.AUTHENTICATION_ERROR_BAD_EMAIL_PASSWORD_CLIENT);
			return;
		}
		
		Date currentDate = new Date();
		this.usersDao.setLoginDate(usrVo.getUsrId(), currentDate);
		this.cliUserDao.setAccessDate(cliVo.getCliId(), usrVo.getUsrId(), currentDate);
		
		Collection<FunctionalityVo> functionalities = this.functionalitiesDao.findFor(usrVo.getUsrId(), cliVo.getCliId());
		usrVo.setSettings(this.usrSettingDao.findAllFor(usrVo.getUsrId()));
		
		this.login(userData, usrVo, cliVo, functionalities);
		this.setDefaultLocation(userData);
	}

	@Override public Collection<ClientVo> listClients(UserData userData) {
		return userData.isLogged() ? this.clientsDao.findAllForUser(userData.getUsrId(), false) : null;
	}
	
	@Override public void setClient(Integer cliId, UserData userData) {
		CliUserVo cliUsrVo		= this.cliUserDao.findVo(cliId, userData.getUsrId());
		
		if (cliUsrVo == null) {
			this.logout(userData);
			userData.setAuthenticationError(SecurityService.AUTHENTICATION_NOT_ALLOWED);
			return;
		}
		
		ClientVo cliVo			= this.clientsDao.findVo(cliId);
		if (cliVo.getDataDefId() != null) cliVo.setDataDefinitionVo(this.dataDefinitionDao.findVo(cliVo.getDataDefId()));
		userData.setClientVo(cliVo);
		this.cliUserDao.setAccessDate(cliUsrVo.getCliId(), cliUsrVo.getUsrId(), new Date());
		this.setDefaultLocation(userData);
	}
	
	@Override public void setLocation(Integer locId, UserData userData) {
		LocationVo locVo	= this.locationDao.findForUser(userData.getUsrId(), userData.getCliId(), locId);
		
		if (locVo != null) userData.setLocationVo(locVo);
	}

	@Override public void doLogout(UserData userData) { this.logout(userData); }

	@Override public Collection<LocationVo> getLocations(UserData userData) {
		return this.locationDao.findAllForUser(userData.getCliId(), userData.getUsrId(), false);
	}

	//--- Password reset methods ----------------
	@Override public void startPasswordReset(String email, Locale locale) {
		UsersVo usrVo = this.usersDao.findBy(email);
		if (usrVo == null) return;
		
		usrVo.setUsrPwdResetRequested(new Date());
		usrVo.setUsrPwdResetUuid(UUID.randomUUID().toString());
		
		usrVo.setSyncType(UsersVo.SYNC_UPDATE);
		
		this.usersDao.synchronize(usrVo);
		
		StringBuilder url = new StringBuilder(this.configuration.getSiteUrl());
		if (! this.configuration.getSiteUrl().endsWith("/")) url.append("/");
		url.append("#/resetPassword/?reset_token=");
		url.append(usrVo.getUsrPwdResetUuid());
		
		Map<String, String> params = new HashMap<>(2);
		params.put("${user}", usrVo.getUsrName());
		params.put("${url}", url.toString());
		
		String subject = this.messageSource.getMessage("email.reset-password.subject", null, locale); //"RENOVUS - Password reset request";
		String content = StringUtil.replaceAll(this.messageSource.getMessage("email.reset-password.content.html", null, locale), params);
		
		this.emailService.sendSimpleHtmlMessage(usrVo.getUsrEmail(), subject, content);
	}

	@Override public PasswordReset doPassworReset(PasswordReset passwordReset, UserData userData) {
		PasswordReset result = null;
		
		if (passwordReset == null)																									result = new PasswordReset(PasswordReset.ERROR_INVALID_REQUEST, "Invalid information received.");
		else if (StringUtil.isEmpty(passwordReset.getId()) && ! userData.isLogged())												result = new PasswordReset(PasswordReset.ERROR_INVALID_ID, "Invalid password request.");
		else if (StringUtil.isEmpty(passwordReset.getNewPassword()))																result = new PasswordReset(PasswordReset.ERROR_INVALID_PASSWORD, "Invalid new password.");
		else if (! ClassUtil.equals(passwordReset.getNewPassword(), passwordReset.getNewPasswordConfirm()))							result = new PasswordReset(PasswordReset.ERROR_INVALID_PASSWORD, "Invalid new password.");
		else if (passwordReset.getNewPassword().length() > 100)																		result = new PasswordReset(PasswordReset.ERROR_INVALID_PASSWORD, "Invalid new password.");
		
		else {
			boolean fromResetEmail	= StringUtil.notEmpty(passwordReset.getId());
			UsersVo usrVo			= fromResetEmail ? this.usersDao.findByResetUuid(passwordReset.getId()) : this.usersDao.findBy(userData.getUsrEmail());
			
			if (usrVo == null) {
				result = new PasswordReset(PasswordReset.ERROR_INVALID_ID, "Invalid password request.");
				
			} else {
				if (fromResetEmail && Math.abs(DateUtil.dateDiffIn(DateUtil.DATE_DIFF_IN_HOURS, usrVo.getUsrPwdResetRequested(), new Date())) >= 2)	{
					result = new PasswordReset(PasswordReset.ERROR_INVALID_ID, "Invalid password request.");
					
				} else {
					usrVo.setUsrPassword(passwordReset.getNewPassword());
					usrVo.setUsrPwdResetRequested(null);
					usrVo.setUsrPwdResetUuid(null);
					
					usrVo.setSyncType(UsersVo.SYNC_UPDATE);
					
					this.usersDao.synchronize(usrVo);
					
					result = new PasswordReset(Boolean.TRUE, Boolean.TRUE);
					
					Map<String, String> params = new HashMap<>(2);
					params.put("${user}", usrVo.getUsrName());
					
					String subject = this.messageSource.getMessage("email.password-changed.subject", null, userData.getLocale()); //"RENOVUS - Password reset request";
					String content = StringUtil.replaceAll(this.messageSource.getMessage("email.password-changed.content.html", null, userData.getLocale()), params);
					
					this.emailService.sendSimpleHtmlMessage(usrVo.getUsrEmail(), subject, content);
				}
			}
		}
		
		return result;
	}

	@Override
	public void setUserData(User user, UserData userData) {
		Collection<UsrSettingVo> settings = new ArrayList<>();
		
		if (user != null && CollectionUtil.notEmpty(user.getSettings())) {
			for (Setting setting : user.getSettings()) {
				if (! UsrSettingVo.validName(setting.getName())) continue;
				UsrSettingVo usrSetVo = new UsrSettingVo(userData.getUsrId(), setting.getName(), setting.getValue());
				usrSetVo.setSyncType(UsrSettingVo.SYNC_INSERT);
				settings.add(usrSetVo);
			}
			
			this.usrSettingDao.deleteAllFor(userData.getUsrId());
			this.usrSettingDao.synchronize(settings);
		}
		
		userData.getUserVo().setSettings(this.usrSettingDao.findAllFor(userData.getUsrId()));
	}
}

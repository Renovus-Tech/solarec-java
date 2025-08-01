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
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ClientService;
import tech.renovus.solarec.business.CustomFlowInterface;
import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.SecurityService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliUserDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.FrequencyDao;
import tech.renovus.solarec.db.data.dao.interfaces.FunctionalityDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocSdgDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.SettingsDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsrSettingDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.CliUserVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.SettingsVo;
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
	@Autowired ClientService clientService;
	@Autowired EmailService emailService;
	@Autowired RenovusSolarecConfiguration configuration;
	@Autowired TranslationService translationService;
	@Autowired CustomFlowInterface customFlow;
	
	@Resource DataDefinitionDao dataDefinitionDao;
	@Resource UsersDao usersDao;
	@Resource UsrSettingDao usrSettingDao;
	@Resource ClientDao clientsDao;
	@Resource CliUserDao cliUserDao;
	@Resource FunctionalityDao functionalitiesDao;
	@Resource LocationDao locationDao;
	@Resource CliSettingDao cliSettingDao;
	@Resource SettingsDao settingsDao;
	@Resource LocSdgDao locSdgDao;
	@Resource LocTypeDao locTypeDao;
	@Resource CountryDao countryDao;
	@Resource FrequencyDao frequencyDao;
	
	//--- Private methods -----------------------
	private void logout(UserData userData) {
		this.customFlow.beforeLogOut(userData);
		
		userData.setUserVo(null);
		userData.setClientVo(null);
		userData.setFunctionalities(null);
		userData.setLogged(false);
		userData.setAuthenticationError(SecurityService.AUTHENTICATION_NOT_LOGGED);
	}
	
	private void setClientLocationSettings(UserData userData, UsersVo usrVo, ClientVo cliVo, Date currentDate) {
		userData.setUserVo(usrVo);
		userData.setClientVo(cliVo);
		userData.setFunctionalities(this.functionalitiesDao.findFor(usrVo.getUsrId(), cliVo.getCliId()));
		userData.setLogged(true);
		
		this.cliUserDao.setAccessDate(cliVo.getCliId(), usrVo.getUsrId(), currentDate);
		
		usrVo.setSettings(this.usrSettingDao.findAllFor(usrVo.getUsrId()));
		cliVo.setSettings(this.cliSettingDao.findAllFor(cliVo.getCliId()));
		cliVo.setCountryVo(this.countryDao.findVo(cliVo.getCtrId()));
	
		UsrSettingVo settingVo = usrVo.getSetting(SettingsVo.PREFER_LANGUAGE);
		if (settingVo != null) {
			userData.setLocale(this.translationService.getLocale(settingVo.getValue()));
		} else {
			userData.setLocale(this.translationService.getLocale(userData.getLanguage()));
		}
		
		this.cliUserDao.setAccessDate(userData.getCliId(), userData.getUsrId(), new Date());
		this.setDefaultLocation(userData);
	}

	private boolean accessEnable(UsersVo usrVo, ClientVo cliVo) {
		if  (usrVo == null || cliVo == null || ! FlagUtil.getFlagValue(cliVo, ClientVo.FLAG_ENABLED)) {
			return false;
		}
		return this.cliUserDao.findVo(cliVo.getCliId(), usrVo.getUsrId()) != null;
	}

	private boolean authenticate(UsersVo usrVo, String password) {
		if (usrVo == null || StringUtil.isEmpty(password) || ! FlagUtil.getFlagValue(usrVo, UsersVo.FLAG_ENABLED)) {
			return false;
		}
		return ClassUtil.equals(usrVo.getUsrPassword(), password);
	}
	
	private ClientVo getDefaultClient(String cliName, Integer usrId) {
		if (StringUtil.notEmpty(cliName)) {
			return this.clientsDao.findBy(cliName);
		}
		
		Collection<ClientVo> clients = usrId == null ? null : this.clientsDao.findAllForUser(usrId, true);
		return CollectionUtil.notEmpty(clients) ? clients.iterator().next() : null;
	}
	
	private void setDefaultLocation(UserData userData) {
		userData.setLocationVo(null);
		Collection<LocationVo> locations = this.locationDao.findAllForUser(userData.getCliId(), userData.getUsrId(), true);
		if (CollectionUtil.size(locations) >= 1) {
			userData.setLocationVo(locations.iterator().next());
		}
		
		if (userData.getLocationVo() != null) {
			userData.getLocationVo().setSdgs(this.locSdgDao.getAllForLocation(userData.getLocationVo().getCliId(), userData.getLocationVo().getLocId()));
			userData.getLocationVo().setLocTypeVo(this.locTypeDao.findVo(userData.getLocationVo().getLocTypeId()));
			userData.getLocationVo().setFrequencyVo(this.frequencyDao.findVo(userData.getLocationVo().getFrqId()));
			userData.getLocationVo().setCountryVo(this.countryDao.findVo(userData.getLocationVo().getCtrId()));
		}
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
		userData.setAuthenticationError(SecurityService.AUTHENTICATION_LOGGED);
		userData.setLanguage(authentication.getLanguage());
		
		if (! this.customFlow.afterAuthentication(authentication, userData)) {
			return;
		}
		
		Date currentDate = new Date();
		this.usersDao.setLoginDate(usrVo.getUsrId(), currentDate);
		
		this.setClientLocationSettings(userData, usrVo, cliVo, currentDate);
		
		this.customFlow.beforeSendingToHomepage(userData);
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
		if (cliVo.getDataDefId() != null) {
			cliVo.setDataDefinitionVo(this.dataDefinitionDao.findVo(cliVo.getDataDefId()));
		}
		
		this.setClientLocationSettings(userData, userData.getUserVo(), cliVo, new Date());
	}
	
	@Override public void setLocation(Integer locId, UserData userData) {
		LocationVo locVo	= this.locationDao.findForUser(userData.getUsrId(), userData.getCliId(), locId);
		
		if (locVo != null && FlagUtil.getFlagValue(locVo, LocationVo.FLAG_ENABLED)) {
			locVo.setSdgs(this.locSdgDao.getAllForLocation(locVo.getCliId(), locVo.getLocId()));
			locVo.setFrequencyVo(this.frequencyDao.findVo(locVo.getFrqId()));
			userData.setLocationVo(locVo);
		}
	}

	@Override public void doLogout(UserData userData) { this.logout(userData); }

	@Override public Collection<LocationVo> getLocations(UserData userData) {
		return this.locationDao.findAllForUser(userData.getCliId(), userData.getUsrId(), false);
	}

	//--- Password reset methods ----------------
	@Override public void startPasswordReset(String email, Locale locale) {
		UsersVo usrVo = this.usersDao.findBy(email);
		if (usrVo == null) {
			return;
		}
		
		usrVo.setUsrPwdResetRequested(new Date());
		usrVo.setUsrPwdResetUuid(UUID.randomUUID().toString());
		
		usrVo.setSyncType(UsersVo.SYNC_UPDATE);
		
		this.usersDao.synchronize(usrVo);
		
		StringBuilder url = new StringBuilder(this.configuration.getSiteUrl());
		if (! this.configuration.getSiteUrl().endsWith("/")) {
			url.append("/");
		}
		url.append("#/resetPassword/?reset_token=");
		url.append(usrVo.getUsrPwdResetUuid());
		
		String subject = this.translationService.forLabel(locale, "email.reset-password.subject");
		
        Map<String, Object> variables = new HashMap<>();
        variables.put("user", usrVo.getUsrName());
        variables.put("email", usrVo.getUsrEmail());
        variables.put("link", url.toString());
        
        String emailContent			= this.translationService.forTemplate(locale, "email_password_reset", variables);
		
        try {
        	this.emailService.sendSimpleHtmlMessage(usrVo.getUsrEmail(), subject, emailContent);
        } catch (CoreException e) {
			LoggerService.rootLogger().error("Error found: " + e.getLocalizedMessage() + "\r\n" + StringUtil.toString(e));
		}
	}

	@Override public PasswordReset doPassworReset(PasswordReset passwordReset, UserData userData) {
		PasswordReset result = null;
		
		if (passwordReset == null) {
			result = new PasswordReset(PasswordReset.ERROR_INVALID_REQUEST, "Invalid information received.");
		} else if (StringUtil.isEmpty(passwordReset.getId()) && ! userData.isLogged()) {
			result = new PasswordReset(PasswordReset.ERROR_INVALID_ID, "Invalid password request.");
		} else if (StringUtil.isEmpty(passwordReset.getNewPassword())) {
			result = new PasswordReset(PasswordReset.ERROR_INVALID_PASSWORD, "Invalid new password.");
		} else if (! ClassUtil.equals(passwordReset.getNewPassword(), passwordReset.getNewPasswordConfirm())) {
			result = new PasswordReset(PasswordReset.ERROR_INVALID_PASSWORD, "Invalid new password.");
		} else if (passwordReset.getNewPassword().length() > 100) {
			result = new PasswordReset(PasswordReset.ERROR_INVALID_PASSWORD, "Invalid new password.");
		} else {
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
					
			        Map<String, Object> variables = new HashMap<>();
			        variables.put("user", usrVo.getUsrName());
			        variables.put("email", usrVo.getUsrEmail());
			        
			        String subject = this.translationService.forLabel(userData.getLocale(), "email.password-changed.subject");
			        String emailContent			= this.translationService.forTemplate(userData.getLocale(), "email_password_reset_done", variables);

			        try {
			        	this.emailService.sendSimpleHtmlMessage(usrVo.getUsrEmail(), subject, emailContent);
			        } catch (CoreException e) {
						LoggerService.rootLogger().error("Error found: " + e.getLocalizedMessage() + "\r\n" + StringUtil.toString(e));
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public void saveUserData(User user, UserData userData) {
		Collection<UsrSettingVo> settings = new ArrayList<>();
		
		if (user != null && CollectionUtil.notEmpty(user.getSettings())) {
			Collection<String> settingsNames = this.settingsDao.getAllNamesForUser();
			for (Setting setting : user.getSettings()) {
				if (! settingsNames.contains(setting.getName())) {
					continue;
				}
				
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

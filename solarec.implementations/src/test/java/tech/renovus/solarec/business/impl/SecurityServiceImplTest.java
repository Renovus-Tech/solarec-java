package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.CliUserVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.rest.security.Authentication;
import tech.renovus.solarec.vo.rest.security.PasswordReset;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceImplTest {

	@Mock private ClientService clientService;
	@Mock private EmailService emailService;
	@Mock private RenovusSolarecConfiguration configuration;
	@Mock private TranslationService translationService;
	@Mock private CustomFlowInterface customFlow;
	
	@Mock private DataDefinitionDao dataDefinitionDao;
	@Mock private UsersDao usersDao;
	@Mock private UsrSettingDao usrSettingDao;
	@Mock private ClientDao clientsDao;
	@Mock private CliUserDao cliUserDao;
	@Mock private FunctionalityDao functionalitiesDao;
	@Mock private LocationDao locationDao;
	@Mock private CliSettingDao cliSettingDao;
	@Mock private SettingsDao settingsDao;
	@Mock private LocSdgDao locSdgDao;
	@Mock private LocTypeDao locTypeDao;
	@Mock private CountryDao countryDao;
	@Mock private FrequencyDao frequencyDao;
	
	@InjectMocks SecurityServiceImpl service = new SecurityServiceImpl();
	
	@Test
	public void authenticate() {
		String theEmail = "asdads@adsads.com";
		String theClient = "client";
		String aValue = "asdad";
		Integer usrId = Integer.valueOf(2);
		Integer cliId = Integer.valueOf(3);
		
		UsersVo usrVo = new UsersVo(usrId);
		usrVo.setUsrEmail(theEmail);
		usrVo.setUsrPassword(aValue);
		FlagUtil.setFlagValue(usrVo, UsersVo.FLAG_ENABLED, true);
		
		ClientVo cliVo = new ClientVo(cliId);
		cliVo.setCliName(theClient);
		FlagUtil.setFlagValue(cliVo, ClientVo.FLAG_ENABLED, true);
		
		when(this.usersDao.findBy(theEmail)).thenReturn(usrVo);
		when(this.clientsDao.findBy(theClient)).thenReturn(cliVo);
		when(this.cliUserDao.findVo(cliId, usrId)).thenReturn(new CliUserVo(cliId, usrId));
		when(this.customFlow.afterAuthentication(any(), any())).thenReturn(true);
		
		Authentication authentication = new Authentication();
		authentication.setEmail(theEmail);
		authentication.setClient(theClient);
		authentication.setPassword(aValue);
		
		UserData userData = new UserData();
		
		this.service.authenticate(authentication, userData);
		
		assertEquals(SecurityService.AUTHENTICATION_LOGGED, userData.getAuthenticationError());
	}
	
	@Test
	public void simpleTests() {
		UserData userData = new UserData();
		
		when(this.clientsDao.findAllForUser(userData.getUsrId(), false)).thenReturn(Arrays.asList(new ClientVo()));
		
		Collection<ClientVo> result = this.service.listClients(userData);
		assertNull(result);
		
		userData.setLogged(true);
		result = this.service.listClients(userData);
		assertNotNull(result);
		
//		verify(this.clientsDao).findAllForUser(any(), false);
	}
	
	@Test
	public void setClientLocation() {
		Integer cliId = Integer.valueOf(1);
		Integer usrId = Integer.valueOf(2);
		Integer locId = Integer.valueOf(3);
		Integer locTypeId = Integer.valueOf(4);
		
		ClientVo cliVo = new ClientVo(cliId);
		LocationVo locVo = new LocationVo(cliId, locId);
		locVo.setLocTypeId(locTypeId);
		FlagUtil.setFlagValue(locVo, LocationVo.FLAG_ENABLED, true);
		
		UserData userData = new UserData();
		userData.setUserVo(new UsersVo(usrId));
		
		when(this.cliUserDao.findVo(cliId, usrId)).thenReturn(new CliUserVo(cliId, usrId));
		when(this.clientsDao.findVo(cliId)).thenReturn(cliVo);
		when(this.locationDao.findAllForUser(cliId, usrId, true)).thenReturn(Arrays.asList(locVo));
		when(this.locationDao.findForUser(usrId, cliId, locId)).thenReturn(locVo);
		when(this.frequencyDao.findVo(any())).thenReturn(null);
		
		this.service.setClient(cliId, userData);
		
		verify(this.cliUserDao).findVo(cliId, usrId);
		verify(this.clientsDao).findVo(cliId);
//		verify(this.cliUserDao).setAccessDate(cliId, usrId, any(Date.class));
		verify(this.locSdgDao).getAllForLocation(cliId, locId);
		verify(this.locTypeDao).findVo(locTypeId);
		
		assertEquals(cliVo, userData.getClientVo());
		assertEquals(locVo, userData.getLocationVo());
		
		this.service.setLocation(locId, userData);
		assertEquals(locVo, userData.getLocationVo());
		
		assertNotNull(this.service.getLocations(userData));
	}
	
	@Test
	public void logoutTest() {
		UserData userData = new UserData();
		userData.setUserVo(new UsersVo());
		userData.setClientVo(new ClientVo());
		userData.setFunctionalities(new ArrayList<>());
		userData.setLogged(true);
		userData.setAuthenticationError(-1);
		
		this.service.doLogout(userData);
		
		assertNull(userData.getUserVo());
		assertNull(userData.getClientVo());
		assertNull(userData.getFunctionalities());
		assertFalse(userData.isLogged());
		assertEquals(SecurityService.AUTHENTICATION_NOT_LOGGED, userData.getAuthenticationError());
		
		verify(this.customFlow).beforeLogOut(userData);
	}
	
	@Test
	public void passwordTest() {
		String theEmail = "sadads@asdad.com";
		Locale locale = Locale.ENGLISH;
		Integer usrId = Integer.valueOf(1);
		String theSiteUrl = "http://test.site";
		String theLabel = "the label";
		
		UsersVo usrVo = new UsersVo(usrId);
		usrVo.setUsrEmail(theEmail);
		
		when(this.usersDao.findBy(theEmail)).thenReturn(usrVo);
		when(this.configuration.getSiteUrl()).thenReturn(theSiteUrl);
		when(this.translationService.forLabel(any(), any())).thenReturn(theLabel);
		
		this.service.startPasswordReset(theEmail, locale);
		
		assertNotNull(usrVo.getUsrPwdResetUuid());
		
//		verify(this.usersDao).update(usrVo);

		UserData userData = new UserData();
		PasswordReset passwordReset = new PasswordReset();
		
		assertEquals(PasswordReset.ERROR_INVALID_REQUEST, this.service.doPassworReset(null, userData).getErrorCode());
		assertEquals(PasswordReset.ERROR_INVALID_ID, this.service.doPassworReset(passwordReset, userData).getErrorCode());
		passwordReset.setId("asdadad");
		assertEquals(PasswordReset.ERROR_INVALID_PASSWORD, this.service.doPassworReset(passwordReset, userData).getErrorCode());
		passwordReset.setNewPassword("1");
		assertEquals(PasswordReset.ERROR_INVALID_PASSWORD, this.service.doPassworReset(passwordReset, userData).getErrorCode());
		passwordReset.setNewPasswordConfirm("1");
		assertEquals(PasswordReset.ERROR_INVALID_ID, this.service.doPassworReset(passwordReset, userData).getErrorCode());
		
		when(this.usersDao.findByResetUuid("asdadad")).thenReturn(usrVo);
		assertNull(this.service.doPassworReset(passwordReset, userData).getErrorCode());
	}
}

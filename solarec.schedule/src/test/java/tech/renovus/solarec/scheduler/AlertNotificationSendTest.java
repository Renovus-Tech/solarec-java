package tech.renovus.solarec.scheduler;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.thymeleaf.TemplateEngine;

import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.ParserService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenUsrAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocUsrAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.UsersVo;

@RunWith(MockitoJUnitRunner.class)
public class AlertNotificationSendTest {

	@Mock EmailService emailService;
	@Mock ParserService parserService;
	@Mock TranslationService tranaslationService;
	@Mock TemplateEngine templateEngine;
	
	@Mock CliLocAlertDao cliLocAlertDao;
	@Mock CliGenAlertDao cliGenAlertDao;
	@Mock CliLocUsrAlertDao cliLocUsrAlertDao;
	@Mock CliGenUsrAlertDao cliGenUsrAlertDao;
	@Mock UsersDao userDao;
	
	@InjectMocks AlertNotificationSend service = new AlertNotificationSend();
	
	private CliGenAlertVo cliGenAlertVo;
	private CliLocAlertVo cliLocAlertVo;
	
	@Before public void setup() {
		this.cliGenAlertVo = new CliGenAlertVo();
		this.cliGenAlertVo.setCliId(Integer.valueOf(1));
		this.cliGenAlertVo.setLocId(Integer.valueOf(2));
		
		this.cliLocAlertVo = new CliLocAlertVo();
		this.cliLocAlertVo.setCliId(Integer.valueOf(1));
		this.cliLocAlertVo.setLocId(Integer.valueOf(2));
		
		UsersVo usrVo = new UsersVo();
		usrVo.setUsrEmail("testing@renovus.tech");
		
		Collection<CliGenAlertVo> genAlerts = new ArrayList<>();
		genAlerts.add(this.cliGenAlertVo);

		Collection<CliLocAlertVo> locAlerts = new ArrayList<>();
		locAlerts.add(this.cliLocAlertVo);
		
		Collection<UsersVo> users = new ArrayList<>();
		users.add(usrVo);
		
		when(this.cliGenAlertDao.retrieveForEmailAlert()).thenReturn(genAlerts);
		when(this.cliLocAlertDao.retrieveForEmailAlert()).thenReturn(locAlerts);
		when(this.userDao.findAllForAlertEmailNotification(Integer.valueOf(1), Integer.valueOf(2), UsersVo.FLAG_RECEIVE_ALERT_GENERATOR_NBY_EMAIL)).thenReturn(users);
	}
	
	@Test 
	public void testTranslation() {
		this.service.send();
		
		verify(this.cliLocAlertDao).updateFlags(this.cliLocAlertVo);
		verify(this.cliGenAlertDao).updateFlags(this.cliGenAlertVo);

		verify(this.cliLocAlertDao).retrieveForEmailAlert();
		verify(this.cliGenAlertDao).retrieveForEmailAlert();
		
		assertTrue(FlagUtil.getFlagValue(this.cliLocAlertVo, CliLocAlertVo.FLAG_SEND_BY_EMAIL));
		assertTrue(FlagUtil.getFlagValue(this.cliGenAlertVo, CliGenAlertVo.FLAG_SEND_BY_EMAIL));
	}
}

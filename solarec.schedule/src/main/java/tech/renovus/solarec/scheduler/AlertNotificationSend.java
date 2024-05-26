package tech.renovus.solarec.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.ParserService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenUsrAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocUsrAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliGenUsrAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocUsrAlertVo;
import tech.renovus.solarec.vo.db.data.SettingsVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;

@Component
public class AlertNotificationSend {

	//--- Resources -----------------------------
	@Autowired EmailService emailService;
	@Autowired ParserService parserService;
	@Autowired TranslationService tranaslationService;
	@Autowired TemplateEngine templateEngine;
	
	@Resource CliLocAlertDao cliLocAlertDao;
	@Resource CliGenAlertDao cliGenAlertDao;
	@Resource CliLocUsrAlertDao cliLocUsrAlertDao;
	@Resource CliGenUsrAlertDao cliGenUsrAlertDao;
	@Resource UsersDao userDao;
	
	//--- Private methods -----------------------
	private void sendCliLocationAlerts() {
		LoggerService.emailLogger().info("Start alert notification check for locations");
		Collection<CliLocAlertVo> toSend = this.cliLocAlertDao.retrieveForEmailAlert();
		LoggerService.emailLogger().info("Amount of alerts to send: {}", CollectionUtil.size(toSend));
		
		if (CollectionUtil.notEmpty(toSend)) {
			Map<Integer, Collection<UsersVo>> sendNotificationsTo = new HashMap<>();
			
			for (CliLocAlertVo alertVo : toSend) {
				Collection<UsersVo> notify					= sendNotificationsTo.computeIfAbsent(alertVo.getLocId(), locId -> this.userDao.findAllForAlertEmailNotification(alertVo.getCliId(), alertVo.getLocId(), UsersVo.FLAG_RECEIVE_ALERT_GENERATOR_NBY_EMAIL));
				Collection<CliLocUsrAlertVo> sendResults	= new ArrayList<>();
						
				for (UsersVo usrVo : notify) {
					UsrSettingVo settingVo	= usrVo.getSetting(SettingsVo.PREFER_LANGUAGE);
					Locale locale			= this.tranaslationService.getLocale(settingVo == null ? null : settingVo.getValue());
			        String emailSubject		= this.tranaslationService.forLabel(locale, "email.location.alert.subject");
			        
			        Context context			= new Context(locale);
			        context.setVariable("alertContent", this.parserService.parseAlert(alertVo, locale));
			        
			        String emailContent			= this.templateEngine.process("email_alert.html", context);
					
			        CliLocUsrAlertVo sendResult = new CliLocUsrAlertVo(alertVo.getCliId(), alertVo.getLocId(), usrVo.getUsrId(), alertVo.getCliLocAlertId());
					sendResult.setSyncType(BaseDbVo.SYNC_INSERT);
					sendResult.setCliLocUsrAlertSendDate(new Date());
					
					try {
						LoggerService.emailLogger().info("Sending email for alert {} to '{}'", alertVo.getCliLocAlertId(), usrVo.getUsrEmail());
						this.emailService.sendSimpleHtmlMessage(usrVo.getUsrEmail(), emailSubject, emailContent);
						sendResult.setCliLocUsrAlertSendResult("send ok");
					} catch (Exception e) {
						sendResult.setCliLocUsrAlertSendResult("error: " + e.getLocalizedMessage());
					} finally {
						sendResults.add(sendResult);
					}
				}
				
				FlagUtil.setFlagValue(alertVo, CliLocAlertVo.FLAG_SEND_BY_EMAIL, true);
				
				this.cliLocAlertDao.updateFlags(alertVo);
				this.cliLocUsrAlertDao.synchronize(sendResults);
			}
		}
	}
	
	private void sendCliGeneratorAlerts() {
		LoggerService.emailLogger().info("Start alert notification check for generators");
		Collection<CliGenAlertVo> toSend = this.cliGenAlertDao.retrieveForEmailAlert();
		LoggerService.emailLogger().info("Amount of alerts to send: {}", CollectionUtil.size(toSend));
		
		if (CollectionUtil.notEmpty(toSend)) {
			Map<Integer, Collection<UsersVo>> sendNotificationsTo = new HashMap<>();
			
			for (CliGenAlertVo alertVo : toSend) {
				Collection<UsersVo> notify					= sendNotificationsTo.computeIfAbsent(alertVo.getLocId(), locId -> this.userDao.findAllForAlertEmailNotification(alertVo.getCliId(), alertVo.getLocId(), UsersVo.FLAG_RECEIVE_ALERT_GENERATOR_NBY_EMAIL));
				Collection<CliGenUsrAlertVo> sendResults	= new ArrayList<>();
						
				for (UsersVo usrVo : notify) {
					UsrSettingVo settingVo	= usrVo.getSetting(SettingsVo.PREFER_LANGUAGE);
					Locale locale			= this.tranaslationService.getLocale(settingVo == null ? null : settingVo.getValue());
			        String emailSubject		= this.tranaslationService.forLabel(locale, "email.generator.alert.subject");
			        
			        Context context			= new Context(locale);
			        context.setVariable("alertContent", this.parserService.parseAlert(alertVo, locale));
			        
			        String emailContent			= this.templateEngine.process("email_alert.html", context);
					
			        CliGenUsrAlertVo sendResult = new CliGenUsrAlertVo(alertVo.getCliId(), alertVo.getGenId(), usrVo.getUsrId(), alertVo.getCliGenAlertId());
					sendResult.setSyncType(BaseDbVo.SYNC_INSERT);
					sendResult.setCliGenUsrAlertSendDate(new Date());
					
					try {
						LoggerService.emailLogger().info("Sending email for alert {} to '{}'", alertVo.getCliGenAlertId(), usrVo.getUsrEmail());
						this.emailService.sendSimpleHtmlMessage(usrVo.getUsrEmail(), emailSubject, emailContent);
						sendResult.setCliGenUsrAlertSendResult("send ok");
					} catch (Exception e) {
						sendResult.setCliGenUsrAlertSendResult("error: " + e.getLocalizedMessage());
					} finally {
						sendResults.add(sendResult);
					}
				}
				
				FlagUtil.setFlagValue(alertVo, CliGenAlertVo.FLAG_SEND_BY_EMAIL, true);
				
				this.cliGenAlertDao.updateFlags(alertVo);
				this.cliGenUsrAlertDao.synchronize(sendResults);
			}
		}
	}
	
	//--- Schedule methods ----------------------
	//@Scheduled(cron="0 0 06 * * *") // all days at 6am
	public void send() {
 		LoggerService.emailLogger().info("Start alert notification check");
		this.sendCliLocationAlerts();
		this.sendCliGeneratorAlerts();
		LoggerService.emailLogger().info("Ended alert notification check");
	}
}

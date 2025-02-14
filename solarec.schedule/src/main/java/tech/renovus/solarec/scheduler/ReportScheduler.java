package tech.renovus.solarec.scheduler;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.renovus.solarec.business.ReportService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.LocUsrRepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.RepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.LocUsrRepTypeVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.RepTypeVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.report.ReportRequest;
import tech.renovus.solarec.vo.report.ReportResponse;

/**
 * @source: https://stackoverflow.com/questions/60070289/how-to-send-email-from-spring-boot-application-automatically-each-end-of-day
 * Seconds can have values 0-59 or the special characters , - * / .
 * Minutes can have values 0-59 or the special characters , - * / .
 * Hours can have values 0-59 or the special characters , - * / .
 * Day of month can have values 1-31 or the special characters , - * ? / L W C .
 * Month can have values 1-12, JAN-DEC or the special characters , - * / .
 * Day of week can have values 1-7, SUN-SAT or the special characters , - * ? / L C # .
 * Year can be empty, have values 1970-2099 or the special characters , - * / .
 */
@Component
public class ReportScheduler {
	
	//--- Private properties --------------------
	@Autowired RenovusSolarecConfiguration config;
	@Resource ReportService reporte;
	
	@Autowired LocationDao locationDao;
	@Autowired UsersDao userDao;
	@Autowired RepTypeDao repTypeDao;
	@Autowired LocUsrRepTypeDao locUsrRepTypeDao;
	
	//--- Private methods -----------------------
	private void execute(String type, Date date) {
		this.execute(this.repTypeDao.findByName(type), date);
	}
		
	private void execute(RepTypeVo repTypeVo, Date date) {
		if (repTypeVo == null) {
			return;
		}
		if (! FlagUtil.getFlagValue(repTypeVo, RepTypeVo.FLAG_ENABLED)) {
			return;
		}
		
		try {
			LoggerService.reportLogger().info("Start of report {} for date {}", repTypeVo.getRepTypeName(), DateUtil.formatDateTime(date, DateUtil.FMT_MILITAR));
			Collection<LocationVo> locations = this.locationDao.getAllForReport();
			
			LoggerService.reportLogger().info("Locations found: {}", CollectionUtil.size(locations));
			if (CollectionUtil.notEmpty(locations)) {
				for (LocationVo locVo : locations) {
					Collection<UsersVo> users = this.userDao.getAllForLocationReport(locVo.getCliId(), locVo.getLocId(), repTypeVo.getRepTypeId());
					if (CollectionUtil.isEmpty(users)) {
						LoggerService.reportLogger().debug("Skipping no users to send to for location: {}" + locVo.getLocName());
						continue;
					}
					
					ReportRequest request = new ReportRequest();
					request.setCliId(locVo.getCliId());
					request.setLocId(locVo.getLocId());
					request.setLocName(locVo.getLocName());
					request.setTypeId(repTypeVo.getRepTypeId());
					request.setType(repTypeVo.getRepTypeName());
					request.setDate(DateUtil.formatDateTime(date, DateUtil.FMT_PARAMETER_DATE));
		
					request.setSendByEmail(true);
		
					for (UsersVo usrVo : users) {
						if (StringUtil.isEmpty(usrVo.getUsrEmail())) {
							continue;
						}
						if (FlagUtil.getFlagValue(usrVo, UsersVo.FLAG_RECEIVE_REPORT_BY_EMAIL_BCC)) {
							request.addEmailBCC(usrVo.getUsrEmail());
						} else {
							LocUsrRepTypeVo setting = this.locUsrRepTypeDao.findVo(locVo.getCliId(), locVo.getLocId(), usrVo.getUsrId(), repTypeVo.getRepTypeId());
							
							if (setting != null && FlagUtil.getFlagValue(setting, LocUsrRepTypeVo.FLAG_SELECTED)) {
								request.addEmail(usrVo.getUsrEmail());
							}
						}
					}

					if (CollectionUtil.isEmpty(request.getEmails()) && CollectionUtil.isEmpty(request.getEmailsBCC())) {
						LoggerService.reportLogger().debug("Skipping no emails to send to for location: {}" + locVo.getLocName());
						continue;
					}
					
					LoggerService.reportLogger().debug("Report request: {}", JsonUtil.toString(request));
					
					try {
						LoggerService.reportLogger().debug("Calling report...");
						ReportResponse response = this.reporte.generateReport(request);
						LoggerService.reportLogger().debug("Report result: {}", JsonUtil.toString(response));
					} catch (Exception e) {
						LoggerService.reportLogger().error("Error found: {}\r\n{}", e.getLocalizedMessage(), StringUtil.toString(e));
					}
				}
			}
		} catch (Exception e) {
			LoggerService.reportLogger().error("Error found: {}\r\n{}", e.getLocalizedMessage(), StringUtil.toString(e));
		} finally {
			LoggerService.reportLogger().info("End of report");
		}
	}
	
	//--- Schedule methods ----------------------
	@Scheduled(cron="${solarec.scheduler.report.weekly:0 30 8 * * MON}") //All monday at 08:30
	public void weekly() {
		Date date = new Date();
		date = DateUtil.clearTime(date);
		date = DateUtil.addUnit(date, Calendar.DAY_OF_MONTH, -7);
		
		this.execute(ReportRequest.TYPE_WEEKLY, date);
	}
	
}

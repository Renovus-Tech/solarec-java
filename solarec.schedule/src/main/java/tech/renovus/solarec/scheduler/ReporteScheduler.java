package tech.renovus.solarec.scheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.renovus.solarec.business.ReportService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.LocUsrRepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.RepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
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
@Profile("prod")
public class ReporteScheduler {
	
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
		
		Date dateStart = new Date();
		StringBuilder log = new StringBuilder();
		
		try {
			
			this.log(log, "Start of report " + repTypeVo.getRepTypeName() + " for date " + DateUtil.formatDateTime(date, DateUtil.FMT_MILITAR));
			Collection<LocationVo> locations = this.locationDao.getAllForReport();
			
			this.log(log, "Locations found: " + CollectionUtil.size(locations));
			if (CollectionUtil.notEmpty(locations)) {
				for (LocationVo locVo : locations) {
					ReportRequest request = new ReportRequest();
					request.setCliId(locVo.getCliId());
					request.addLocId(locVo.getLocId());
					request.setTypeId(repTypeVo.getRepTypeId());
					request.setType(repTypeVo.getRepTypeName());
					request.setDate(DateUtil.formatDateTime(date, DateUtil.FMT_PARAMETER_DATE));
		
					request.setSendByEmail(true);
		
					Collection<UsersVo> users = this.userDao.getAllForLocationReport(locVo.getCliId(), locVo.getLocId(), repTypeVo.getRepTypeId());
					if (CollectionUtil.isEmpty(users)) {
						this.log(log, "Skipping no users to send to.");
						continue;
					}
					
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

					this.log(log, "Report request: " + JsonUtil.toString(request));
					
					if (CollectionUtil.isEmpty(request.getEmails()) && CollectionUtil.isEmpty(request.getEmailsBCC())) {
						this.log(log, "Skipping no emails to send to");
						continue;
					}
					
					try {
						this.log(log, "Calling report...");
						Collection<ReportResponse> responses = this.reporte.generateReport(request);
						
						this.log(log, "Report result");
						if (CollectionUtil.notEmpty(responses)) {
							for (ReportResponse response : responses) {
								this.log(log, JsonUtil.toString(response));
							}
						}
					} catch (Exception e) {
						this.log(log, e);
					}
				}
			}
		} catch (Exception e) {
			this.log(log, e);
		} finally {
			this.log(log, "End of report");
			
			this.createLogFile(log, dateStart);
		}
	}
	
	private void createLogFile(StringBuilder log, Date date) {
		File logFile	= new File(this.config.getPathLog(), DateUtil.formatDateTime(date, DateUtil.FMT_DATE) + File.separator + "report_generation" + DateUtil.formatDateTime(date, DateUtil.FMT_TIME_MILI) + ".log");
		logFile.getParentFile().mkdirs();
		try (
			FileWriter o = new FileWriter(logFile, false);
		) {
			o.write(log.toString());
			o.flush();
		} catch (IOException e) {
			System.out.println("Error saving repot generation log file: " + e.getLocalizedMessage());
			System.out.println(StringUtil.toString(e));
			System.out.println("Content of file: ");
			System.out.println(log.toString());
			System.out.println();
		}
	}
	
	private void log(StringBuilder log, Exception e) {
		log
			.append(DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR))
			.append(StringUtil.BAR_SPARATOR)
			.append("ERROR")
			.append(StringUtil.BAR_SPARATOR)
			.append("Error found: ")
			.append(e.getLocalizedMessage())
			.append(StringUtil.NEW_LINE)
			.append(StringUtil.toString(e))
			.append(StringUtil.NEW_LINE);
	}

	private void log(StringBuilder log, String msg) {
		log
			.append(DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR))
			.append(StringUtil.BAR_SPARATOR)
			.append("INFO")
			.append(StringUtil.BAR_SPARATOR)
			.append(msg)
			.append(StringUtil.NEW_LINE);
	}
	
	//--- Schedule methods ----------------------
	@Scheduled(cron="0 30 8 * * MON") //All monday at 08:30
	public void weekly() {
		Date date = new Date();
		date = DateUtil.clearTime(date);
		date = DateUtil.addUnit(date, Calendar.DAY_OF_MONTH, -7);
		
		this.execute(ReportRequest.TYPE_WEEKLY, date);
	}
	
}

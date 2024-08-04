package tech.renovus.solarec.business.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.RestFactory;
import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.ReportService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.business.impl.email.EmailFile;
import tech.renovus.solarec.business.impl.report.ReportCustom;
import tech.renovus.solarec.business.impl.report.ReportWeekly;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.LocUsrRepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.RepTypeDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.LocUsrRepTypeVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.RepTypeVo;
import tech.renovus.solarec.vo.report.ReportConfiguration;
import tech.renovus.solarec.vo.report.ReportDefinition;
import tech.renovus.solarec.vo.report.ReportGeneration;
import tech.renovus.solarec.vo.report.ReportRequest;
import tech.renovus.solarec.vo.report.ReportResponse;
import tech.renovus.solarec.vo.rest.entity.LocationReport;

@Service
public class ReportServiceImpl implements ReportService {

	//--- Properties ----------------------------
	@Autowired ApplicationEventPublisher eventPublisher;
	@Autowired TranslationService translationService;
	
	@Autowired RenovusSolarecConfiguration configuration;
	@Autowired RestFactory restFactory;
	@Autowired ReportWeekly	reportWeekly;
	@Autowired ReportCustom	reportCustom;
	
	@Autowired RepTypeDao repTypeDao;
	@Autowired LocationDao locDao;
	@Autowired LocUsrRepTypeDao locUsrRepTypeDao;
	
	@Autowired EmailService emailService;
	
	//--- Private methods -----------------------
	private void loadConfiguration(ReportConfiguration configuration, UserData userData) {
		Collection<RepTypeVo> reportTypes			= this.repTypeDao.getlAllActive()
														.stream()
														.filter(x -> FlagUtil.getFlagValue(x, RepTypeVo.FLAG_ENABLED_FOR_SETTING))
														.collect(Collectors.toList());
		
		Collection<LocationVo> locations			= this.locDao.findAllForUser(userData.getCliId(), userData.getUsrId(), false)
														.stream()
														.filter(x -> FlagUtil.getFlagValue(x, LocationVo.FLAG_REPORT_ENABLED))
														.collect(Collectors.toList());
		
		Collection<LocUsrRepTypeVo> locUsrRepTypes	= this.locUsrRepTypeDao.getAllForUser(userData.getUsrId());
		
		configuration.setReports(this.restFactory.convertReportTypes(reportTypes));
		configuration.setLocations(this.restFactory.convertLocations(locations));
		
		configuration.setSettings(new TreeSet<>());
		
		if (CollectionUtil.notEmpty(locUsrRepTypes)) {
			for (LocUsrRepTypeVo settingVo : locUsrRepTypes) {
				configuration.getSettings().add(new LocationReport(settingVo.getLocId(), settingVo.getRepTypeId(), FlagUtil.getFlagValue(settingVo, LocUsrRepTypeVo.FLAG_SELECTED)));
			}
		}
		
		for (LocationVo locVo : locations) {
			for (RepTypeVo repTypeVo : reportTypes) {
				configuration.getSettings().add(new LocationReport(locVo.getLocId(), repTypeVo.getRepTypeId()));
			}
		}
		
		configuration.setLoaded(Boolean.TRUE);
	}
	
	//--- Overridden ----------------------------
	@Override public ReportResponse generateReport(ReportRequest request) throws CoreException {
		ReportResponse report = null;
		
		if (ClassUtil.equals(request.getType(), ReportRequest.TYPE_WEEKLY)) {
			report = this.reportWeekly.generate(request);
		} else if (ClassUtil.equals(request.getType(), ReportRequest.TYPE_CUSTOM)) {
			report = this.reportCustom.generate(request);
		}
		
		if (report != null && request.isSendByEmail()) {
			Locale locale			= Locale.ENGLISH;
			
			String subject = this.translationService.forLabel(locale, request.getSubjectLabel(), request.getDate());
			
			Map<String, Object> variables = new HashMap<>();
	        variables.put("reportContent", this.translationService.forLabel(
	        		locale, 
	        		request.getContentLabel(),
	        		request.getLocName()
	        	));
	        
	        String emailContent			= this.translationService.forTemplate(locale, "email_report", variables);
	        
			Collection<EmailFile> files = new ArrayList<>(1);
			if (report.isGenerated()) {
				files.add(new EmailFile(report.getName(), report.getPath()));
			}
			
			try {
				this.emailService.sendMessageWithAttachment(
					request.getEmails(), 
					request.getEmailsCC(), 
					request.getEmailsBCC(), 
					subject, 
					emailContent, 
					files,
					true
				);
			} catch (CoreException e) {
				throw new CoreException(e);
			}
		}
		
		return report;
	}

	@Override public ReportConfiguration getConfiguration(UserData userData) {
		ReportConfiguration configuration = new ReportConfiguration();
		configuration.setSaved(Boolean.FALSE);
		
		this.loadConfiguration(configuration, userData);
		
		return configuration;
	}

	@Override public ReportConfiguration setConfiguration(ReportConfiguration configuration, UserData userData) {
		Collection<Integer> reportTypes		= this.repTypeDao.getlAllActive()
												.stream()
												.filter(x -> FlagUtil.getFlagValue(x, RepTypeVo.FLAG_ENABLED_FOR_SETTING))
												.map(RepTypeVo::getRepTypeId)
												.collect(Collectors.toList());
		Collection<Integer> locations		= this.locDao.findAllForUser(userData.getCliId(), userData.getUsrId(), false)
												.stream()
												.filter(x -> FlagUtil.getFlagValue(x, LocationVo.FLAG_REPORT_ENABLED))
												.map(LocationVo::getLocId)
												.collect(Collectors.toList());
		
		Collection<LocUsrRepTypeVo> settings = new TreeSet<>();
		
		if (CollectionUtil.notEmpty(configuration.getSettings())) {
			for (LocationReport setting : configuration.getSettings()) {
				if ( ! reportTypes.contains(setting.getRepId())) {
					continue;
				}
				if ( ! locations.contains(setting.getLocId())) {
					continue;
				}
				LocUsrRepTypeVo toSet =  new LocUsrRepTypeVo(userData.getCliId(), setting.getLocId(), userData.getUsrId(), setting.getRepId()).withFlag(LocUsrRepTypeVo.FLAG_SELECTED, setting.isSelected());
				toSet.setSyncType(LocUsrRepTypeVo.SYNC_INSERT);
				settings.add(toSet);
			}
		}
		
		for (Integer locId : locations) {
			for (Integer repTypeId : reportTypes) {
				LocUsrRepTypeVo setting = new LocUsrRepTypeVo(
						userData.getCliId(), 
						locId, 
						userData.getUsrId(), 
						repTypeId
					).withFlag(LocUsrRepTypeVo.FLAG_SELECTED, false);
				
				setting.setSyncType(LocUsrRepTypeVo.SYNC_INSERT);
				
				settings.add(setting);
			}
		}
		
		this.locUsrRepTypeDao.synchronize(settings);

		configuration.setSaved(Boolean.TRUE);
		
		this.loadConfiguration(configuration, userData);
		
		return configuration;
	}

	@Override public ReportGeneration getGenerateOptions(UserData userData) {
		if (! userData.isLogged()) {
			return null;
		}
		
		Collection<RepTypeVo> allReports = this.repTypeDao.getlAllActive();
		
		ReportGeneration result = new ReportGeneration();
		result.setReports(new ArrayList<>(CollectionUtil.size(allReports)));
		
		allReports.stream().forEach(vo -> result.getReports().add(new ReportDefinition(vo)));
		
		return result;
	}

	@Override public ReportGeneration doGenerate(ReportGeneration generation, UserData userData) {
		ReportGeneration result = new ReportGeneration();
		result.setGenerated(Boolean.FALSE);
		
		RepTypeVo vo = ( ! userData.isLogged()) || generation == null || generation.getReport() == null ? null : this.repTypeDao.findVo(generation.getReport().getId());
		
		if (vo != null) {
			ReportRequest request = new ReportRequest();
			request.setCliId(userData.getCliId());
			request.setLocId(userData.getLocId());
			request.setLocName(userData.getLocName());
			request.setSendByEmail(true);
			request.setEmails(new ArrayList<>(1));
			request.getEmails().add(userData.getUsrEmail());
			request.setType(vo.getRepTypeName());
			request.setTypeId(vo.getRepTypeId());
			
			boolean allOk = true;
			
			if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_DATE)) {
				if (CollectionUtil.size(generation.getReport().getValues()) < 1) {
					allOk = false;
				} else if (StringUtil.isEmpty(generation.getReport().getValues().get(0))) {
					allOk = false;
				} else {
					request.setDate(generation.getReport().getValues().get(0));
				}

			} else if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_WEEK)) {
				if (CollectionUtil.size(generation.getReport().getValues()) < 1) {
					allOk = false;
				} else if (StringUtil.isEmpty(generation.getReport().getValues().get(0))) {
					allOk = false;
				} else {
					request.setDateTo(generation.getReport().getValues().get(0));
					request.setDate(DateUtil.addUnit(request.getTheDateTo(), Calendar.DAY_OF_MONTH, -6));
				}
				
			} else if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_MONTH)) {
				if (CollectionUtil.size(generation.getReport().getValues()) < 1) {
					allOk = false;
				} else if (StringUtil.isEmpty(generation.getReport().getValues().get(0))) {
					allOk = false;
				} else {
					Calendar calendar = GregorianCalendar.getInstance();
					calendar.setTime(DateUtil.stringToDate(generation.getReport().getValues().get(0), DateUtil.FMT_PARAMETER_DATE));
					calendar.set(Calendar.DAY_OF_MONTH, 1);

					request.setDate(DateUtil.formatDateTime(calendar.getTime(), DateUtil.FMT_PARAMETER_DATE));
					
					calendar.add(Calendar.MONTH, 1);
					calendar.add(Calendar.DAY_OF_MONTH, -1);
					
					request.setDateTo(DateUtil.formatDateTime(calendar.getTime(), DateUtil.FMT_PARAMETER_DATE));
				}

			} else if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_RANGE)) {
				if (CollectionUtil.size(generation.getReport().getValues()) < 2) {
					allOk = false;
				} else if (StringUtil.isEmpty(generation.getReport().getValues().get(0))) {
					allOk = false;
				} else if (StringUtil.isEmpty(generation.getReport().getValues().get(1))) {
					allOk = false;
				} else {
					request.setDate(generation.getReport().getValues().get(0));
					request.setDateTo(generation.getReport().getValues().get(1));
				}
			}
			
			if (allOk) {
				this.eventPublisher.publishEvent(request);
				
				result.setGenerated(Boolean.TRUE);
				result.setReport(new ReportDefinition(vo));
				result.getReport().setValues(generation.getReport().getValues());
				result.setWillSendTo(userData.getUsrEmail());
			}
		}
		
		return result;
	}
	
	@Async @EventListener public void handleEvent(ReportRequest request) {
		try {
			this.generateReport(request);
		} catch (CoreException e) {
			try {
				this.emailService.sendMessageWithAttachment(
						Arrays.asList(this.configuration.getOnErrorSendEmailTo()), 
						null, 
						null,
						this.translationService.forLabel(Locale.ENGLISH, "email.report-error.subject"),
						this.translationService.forLabel(Locale.ENGLISH, "email.report-error.content.html", e.getLocalizedMessage(), StringUtil.toString(e,  true)), 
						null, 
						true
					);
			} catch (CoreException ee) { /* do nothing */ }
		}
    }
}


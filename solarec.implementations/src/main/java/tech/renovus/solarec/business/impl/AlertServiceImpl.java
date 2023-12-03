package tech.renovus.solarec.business.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.business.AlertService;
import tech.renovus.solarec.business.impl.alert.AlertProcessingFactory;
import tech.renovus.solarec.business.impl.alert.base.AbstractAlertProcessing;
import tech.renovus.solarec.configuration.RenovusSolarConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.AlertProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.util.interfaces.IAlert;
import tech.renovus.solarec.vo.db.data.AlertDefinitionVo;
import tech.renovus.solarec.vo.db.data.AlertProcessingVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;

@Service
public class AlertServiceImpl implements AlertService {

	//--- Resources -----------------------------
	@Autowired RenovusSolarConfiguration config;
	
	@Resource ClientDao clientDao;
	@Resource AlertProcessingDao alertProcessingDao;
	@Resource LocationDao locationDao;
	@Resource GeneratorDao generatorDao;
	@Resource StationDao stationDao;

	@Resource LocAlertDao locAlertDao;
	@Resource GenAlertDao genAlertDao;
	@Resource StaAlertDao staAlertDao;
	
	//--- Private methods -----------------------
	private void saveToFile(File file, String content) throws CoreException {
		try (
			FileWriter o = new FileWriter(file, false);
		) {
			o.write(content);
			o.flush();
		} catch (IOException e) {
			throw new CoreException(e);
		}
	}
	
	private void saveAlert(AlertProcessingVo alertProVo, ClientVo cliVo) {
		this.clientDao.update(cliVo);
		
		if (CollectionUtil.notEmpty(cliVo.getLocations())) for (LocationVo locVo : cliVo.getLocations()) {
			locVo.setCliId(cliVo.getCliId());
			
			this.saveData(alertProVo, locVo);
		}
	}
	
	private void setProcessingData(AlertProcessingVo alertProVo, Collection<? extends IAlert> alerts) {
		if (CollectionUtil.notEmpty(alerts)) for (IAlert vo : alerts) {
			vo.setAlertProId(alertProVo.getAlertProId());
			vo.setAlertDateAdded(alertProVo.getAlertProDateStart());
		}
	}
	
	private void saveData(AlertProcessingVo alertProVo, LocationVo locVo) {
		if (locVo.getLocId() == null) this.locationDao.insert(locVo);
		
		locVo.setChildrensId();
		
		long start = System.currentTimeMillis();
		System.out.println("Saving alerts " + locVo.getLocName() + "...");
		
		this.setProcessingData(alertProVo, locVo.getAlerts());
		this.locAlertDao.insert(locVo.getAlerts());
		
		if (CollectionUtil.notEmpty(locVo.getGenerators())) for (GeneratorVo genVo : locVo.getGenerators()) {
			genVo.setCliId(locVo.getCliId());
			genVo.setLocId(locVo.getLocId());
			
			this.saveData(alertProVo, genVo);
		}
		
		if (CollectionUtil.notEmpty(locVo.getStations())) for (StationVo staVo : locVo.getStations()) {
			staVo.setCliId(locVo.getCliId());
			staVo.setLocId(locVo.getLocId());
			
			this.saveData(alertProVo, staVo);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Alerts saved in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL));
	}
	
	private void saveData(AlertProcessingVo alertProVo, GeneratorVo genVo) {
		if (genVo.getGenId() == null) this.generatorDao.insert(genVo);
		
		genVo.setChildrensId();
		
		this.setProcessingData(alertProVo, genVo.getAlerts());
		this.genAlertDao.insert(genVo.getAlerts());
		
	}
	
	private void saveData(AlertProcessingVo alertProVo, StationVo staVo) {
		if (staVo.getStaId() == null) this.stationDao.insert(staVo);
		
		staVo.setChildrensId();
		
		this.setProcessingData(alertProVo, staVo.getAlerts());
		this.staAlertDao.insert(staVo.getAlerts());
	}
	
	//--- Implemented methods -------------------
	@Override public AlertProcessingVo doProcessing(DataProcessingVo dataProVo, AlertDefinitionVo alertDefVo) {
		AlertProcessingVo alertProVo	= new AlertProcessingVo();
		alertProVo.setCliId(dataProVo.getCliId());
		alertProVo.setLocId(dataProVo.getLocId());
		alertProVo.setAlertDefId(alertDefVo.getAlertDefId());
		alertProVo.setAlertProDateStart(new Date());
		
		ClientVo cliVoWithAlert		= null;
		StringBuilder processingLog	= new StringBuilder();
		
		try {
			AbstractAlertProcessing alert = AlertProcessingFactory.getInstance().get(alertDefVo.getAlertDefExecutable());
			processingLog.append("Execution of class: " + alert.getClass().getCanonicalName() + StringUtil.NEW_LINE);
			alert.prepareFor(dataProVo);
			long start = System.currentTimeMillis();
			try {
				alert.process();
			} finally {
				long end = System.currentTimeMillis();
				processingLog.append(alert.getLog());
				System.out.println("Processing of alert ended in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL));
			}
			cliVoWithAlert = alert.generateAlertToSave();
			
			alertProVo.setAlertProResult(AlertProcessingVo.RESULT_PROCESSING_OK);
			
		} catch (CoreException | ProcessingException e) {
			alertProVo.setAlertProResult(AlertProcessingVo.RESULT_PROCESSING_ERROR);
			processingLog.append(StringUtil.NEW_LINE + StringUtil.NEW_LINE);
			processingLog.append("Error: " + e.getLocalizedMessage() + StringUtil.NEW_LINE);
			processingLog.append(StringUtil.toString(e));
			
		} finally {
			alertProVo.setAlertProDateEnd(new Date());
		}
		
		this.alertProcessingDao.insert(alertProVo);
		
		File logFile	= new File(this.config.getPathLog(), "alert_processing_" + alertProVo.getAlertProId() + ".log");
		
		alertProVo.setAlertProFileLog(logFile.getName());
		
		try {
			this.saveToFile(logFile, processingLog.toString());
		} catch (CoreException e) {
			System.out.println("Save log error" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		try {
			if (cliVoWithAlert != null) this.saveAlert(alertProVo, cliVoWithAlert);
		} finally {
			alertProVo.setAlertProDateEnd(new Date());
			this.alertProcessingDao.update(alertProVo);
		}
		
		return alertProVo;
	}


}

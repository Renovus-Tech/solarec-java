package tech.renovus.solarec.scheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefTriggerDao;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.rest.entity.DataDefinitionTrigger;

public abstract class AbstractCheckScheduler {

	//--- Protected properties ------------------
	@Autowired protected RenovusSolarecConfiguration config;
	@Autowired protected CliDataDefTriggerDao triggerDao;
	
	//--- Protected methods ---------------------
	protected void log(StringBuilder log, CliDataDefTriggerVo triggerVo) {
		StringBuilder info = new StringBuilder();
		
		info
			.append("\tTrigger: ")
			.append(triggerVo.getTriId())
			.append(StringUtil.BAR_SPARATOR)
			.append(triggerVo.getTriName())
			.append(StringUtil.BAR_SPARATOR)
			.append(triggerVo.getTriValue());
		
		this.log(log, info.toString());
	}

	protected void createLogFile(StringBuilder log, Date date, String prefix) {
		File logFile	= new File(this.config.getPathLog(), DateUtil.formatDateTime(date, DateUtil.FMT_DATE) + File.separator + prefix + DateUtil.formatDateTime(date, DateUtil.FMT_TIME_MILI) + ".log");
		logFile.getParentFile().mkdirs();
		try (
			FileWriter o = new FileWriter(logFile, false);
		) {
			o.write(log.toString());
			o.flush();
		} catch (IOException e) {
			System.out.println("Error saving email log file: " + e.getLocalizedMessage());
			System.out.println(StringUtil.toString(e));
			System.out.println("Content of file: ");
			System.out.println(log.toString());
			System.out.println();
		}
	}
	
	protected void log(StringBuilder log, Exception e) {
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

	protected void log(StringBuilder log, String msg) {
		log
			.append(DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR))
			.append(StringUtil.BAR_SPARATOR)
			.append("INFO")
			.append(StringUtil.BAR_SPARATOR)
			.append(msg)
			.append(StringUtil.NEW_LINE);
	}

	protected void log(StringBuilder log, DataProcessingVo procVo) {
		StringBuilder info = new StringBuilder();
		info
			.append("\tProcessing: ")
			.append(procVo.getDataProId())
			.append(StringUtil.BAR_SPARATOR)
			.append(procVo.getDataProFileName())
			.append(StringUtil.BAR_SPARATOR)
			.append(procVo.getDataProFileLog())
			.append(StringUtil.BAR_SPARATOR)
			.append(procVo.getDataProResult());
		
		this.log(log, info.toString());
	}
	
	protected DataDefinitionTrigger getTriggerRequest(CliDataDefTriggerVo vo) {
		DataDefinitionTrigger request = new DataDefinitionTrigger();
		
		request.setId(vo.getTriId());
		request.setDataDefinitionId(vo.getDataDefId());
		request.setClientId(vo.getCliId());
		request.setLocationId(vo.getLocId());
		request.setGeneratorId(vo.getGenId());
		request.setStationId(vo.getStaId());
		
		return request;
	}
}

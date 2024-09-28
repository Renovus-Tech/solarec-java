package tech.renovus.solarec.business.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.AlertService;
import tech.renovus.solarec.business.CalculationService;
import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.ProcessingService;
import tech.renovus.solarec.business.impl.processing.DataProcessingFactory;
import tech.renovus.solarec.business.impl.processing.base.AbstractDataProcessing;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefTriggerDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefAlertDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefStatDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProAlertProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProStatProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.util.interfaces.IData;
import tech.renovus.solarec.vo.db.data.AlertProcessingVo;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefAlertDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProAlertProcessingVo;
import tech.renovus.solarec.vo.db.data.DataProStatProcessingVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.rest.background.Processing;
import tech.renovus.solarec.vo.rest.entity.DataDefinitionTrigger;

@Service
public class ProcessingServiceImpl implements ProcessingService {

	//--- Resources -----------------------------
	@Autowired RenovusSolarecConfiguration config;
	@Autowired CalculationService calculationService;
	@Autowired AlertService alertService;
	@Autowired EmailService emailService;
	
	@Autowired AutowireCapableBeanFactory autowireCapableBeanFactory;
	@Resource DataDefinitionDao dataDefinitionDao;
	
	@Resource ClientDao clientDao;
	@Resource LocationDao locationDao;
	@Resource GeneratorDao generatorDao;
	@Resource StationDao stationDao;
		
	@Resource LocDataDao locDataDao;
	@Resource GenDataDao genDataDao;
	@Resource StaDataDao staDataDao;
	
	@Resource DataProcessingDao dataProcessingDao;
	@Resource DataDefStatDefinitionDao dataDefStatDefDao;
	@Resource DataDefAlertDefinitionDao dataDefAlertDefDao;
	@Resource DataProStatProcessingDao dataProStatProcessingDao;
	@Resource DataProAlertProcessingDao dataProAlertProcessingDao;
	@Resource CliDataDefTriggerDao cliDataDefTriggerDao;
	
	//--- Private methods -----------------------
	private DataDefinitionVo getDataDefinitionVo(Processing request, ClientVo cliVo) {
		Integer locId = request.getLocationId();
		Integer genId = request.getGeneratorId();
		Integer staId = request.getStationId();
		
		Integer dataDefId = this.loadElements(cliVo, locId, genId, staId);
		
		DataDefinitionVo dataDefVo	= this.dataDefinitionDao.findVo(dataDefId);
		return dataDefVo;
	}

	private Integer loadElements(ClientVo cliVo, Integer locId, Integer genId, Integer staId) {
		Integer dataDefId;
		if (locId != null) {
			LocationVo locVo	= this.locationDao.findVo(cliVo.getCliId(), locId);
			dataDefId			= locVo.getDataDefId();
			
			cliVo.add(locVo);
			
			if (genId != null) {
				GeneratorVo genVo	= this.generatorDao.findVo(cliVo.getCliId(), genId);
				dataDefId			= genVo.getDataDefId();
				
				locVo.add(genVo);
				
			} else if (staId != null) {
				StationVo staVo		= this.stationDao.findVo(cliVo.getCliId(), staId);
				dataDefId			= staVo.getDataDefId();
				
				locVo.add(staVo);
			} else {
				locVo.addGenerators(this.generatorDao.findAll(locVo.getCliId(), locVo.getLocId()));
				locVo.addStations(this.stationDao.findAll(locVo.getCliId(), locVo.getLocId()));
			}
		} else {
			cliVo.setLocations(this.locationDao.findAll(cliVo.getCliId()));
			
			if (CollectionUtil.notEmpty(cliVo.getLocations())) {
				for (LocationVo locVo : cliVo.getLocations()) {
					locVo.addGenerators(this.generatorDao.findAll(locVo.getCliId(), locVo.getLocId()));
					locVo.addStations(this.stationDao.findAll(locVo.getCliId(), locVo.getLocId()));
				}
			}
			
			dataDefId = cliVo.getDataDefId();
		}
		return dataDefId;
	}
	
	private void getDataDefinitionVo(DataDefinitionTrigger request, CliDataDefTriggerVo triggerVo) {
		request.setDataDefinition(null);
		request.setClient(null);
		request.setLocation(null);
		request.setGenerator(null);
		request.setStation(null);
		
		Integer locId = request.getLocationId();
		Integer genId = request.getGeneratorId();
		Integer staId = request.getStationId();
		
		this.loadElements(triggerVo.getClientVo(), locId, genId, staId);
		
		request.setDataDefinitionId(triggerVo.getDataDefId());
		request.setClientId(triggerVo.getCliId());
		request.setLocationId(triggerVo.getLocId());
		request.setGeneratorId(triggerVo.getGenId());
		request.setStationId(triggerVo.getStaId());
	}
	
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
	
	private void saveData(DataProcessingVo dataProVo, ClientVo cliVo) {
		this.clientDao.update(cliVo);
		
		if (CollectionUtil.notEmpty(cliVo.getLocations())) {
			for (LocationVo locVo : cliVo.getLocations()) {
				locVo.setCliId(cliVo.getCliId());
				
				this.saveData(dataProVo, locVo);
			}
		}
	}

	private void saveData(DataProcessingVo dataProVo, LocationVo locVo) {
		if (locVo.getLocId() == null) this.locationDao.insert(locVo);
		
		locVo.setChildrensId();
		
		long start = System.currentTimeMillis();
		System.out.println("Saving data " + locVo.getLocName() + "...");
		
		this.setProcessingData(dataProVo, locVo.getDatas());
		this.locDataDao.insert(locVo.getDatas());
		
		if (CollectionUtil.notEmpty(locVo.getGenerators())) for (GeneratorVo genVo : locVo.getGenerators()) {
			genVo.setCliId(locVo.getCliId());
			genVo.setLocId(locVo.getLocId());
			
			this.saveData(dataProVo, genVo);
		}
		
		if (CollectionUtil.notEmpty(locVo.getStations())) for (StationVo staVo : locVo.getStations()) {
			staVo.setCliId(locVo.getCliId());
			staVo.setLocId(locVo.getLocId());
			
			this.saveData(dataProVo, staVo);
		}
		
		this.locationDao.updateDataDateMaxMin();
		this.generatorDao.updateDataDateMaxMin();
		this.stationDao.updateDataDateMaxMin();
		
		long end = System.currentTimeMillis();
		System.out.println("Data saved in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL));
	}

	private void saveData(DataProcessingVo dataProVo, GeneratorVo genVo) {
		if (genVo.getGenId() == null) this.generatorDao.insert(genVo);
		
		genVo.setChildrensId();
		
		this.setProcessingData(dataProVo, genVo.getDatas());
		this.genDataDao.insert(genVo.getDatas());
		
	}
	
	private void saveData(DataProcessingVo dataProVo, StationVo staVo) {
		if (staVo.getStaId() == null) this.stationDao.insert(staVo);
		
		staVo.setChildrensId();
		
		this.setProcessingData(dataProVo, staVo.getDatas());
		this.staDataDao.insert(staVo.getDatas());
	}

	private void setProcessingData(DataProcessingVo dataProVo, Collection<? extends IData> datas) {
		if (CollectionUtil.notEmpty(datas)) for (IData vo : datas) {
			vo.setDataProId(dataProVo.getDataProId());
			vo.setDataDateAdded(dataProVo.getDataProDateStart());
		}
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	private void doStatistics(DataDefinitionVo dataDefVo, DataProcessingVo dataProVo) {
		Collection<DataDefStatDefinitionVo> calculations = this.dataDefStatDefDao.getAllForDataDefinition(dataDefVo.getDataDefId());
		if (CollectionUtil.notEmpty(calculations)) {
			for (DataDefStatDefinitionVo calculation : calculations) {
				System.out.println("Doing statistic processing: " + calculation.getStatDefVo().getStatDefExecutable());

				long start = System.currentTimeMillis();
				
				StatProcessingVo statProcessingVo = this.calculationService.calculate(dataProVo, calculation.getStatDefVo());
				this.dataProStatProcessingDao.insert(new DataProStatProcessingVo(dataProVo.getDataProId(), statProcessingVo.getStatProId()));
				
				long end = System.currentTimeMillis();
				System.out.println("Statistic processing ended in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL));
			}
		}
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	private void doAlerts(DataDefinitionVo dataDefVo, DataProcessingVo dataProVo) {
		Collection<DataDefAlertDefinitionVo> alerts = this.dataDefAlertDefDao.getAllForDataDefinition(dataDefVo.getDataDefId());
		if (CollectionUtil.notEmpty(alerts)) {
			for (DataDefAlertDefinitionVo alert : alerts) {
				System.out.println("Doing alert processing: " + alert.getAlertDefVo().getAlertDefExecutable());
				
				long start = System.currentTimeMillis();
				AlertProcessingVo alertProcessingVo = this.alertService.doProcessing(dataProVo, alert.getAlertDefVo());
				this.dataProAlertProcessingDao.insert(new DataProAlertProcessingVo(dataProVo.getDataProId(), alertProcessingVo.getAlertProId()));
				
				long end = System.currentTimeMillis();
				System.out.println("Alert processing ended in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL));
			}
		}
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	private Collection<File> doValidation(DataDefinitionVo dataDefVo, Processing request, ClientVo cliVo) throws CoreException {
		String filePath			= request.getFilePath();
		MultipartFile filePart	= request.getFilePart();
		
		return this.doValidation(filePath, filePart, dataDefVo, cliVo);
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	private Collection<File> doValidation(DataDefinitionVo dataDefVo, DataDefinitionTrigger request, ClientVo cliVo) throws CoreException {
		String filePath			= request.getFilePath();
		MultipartFile filePart	= request.getFilePart();
		
		return this.doValidation(filePath, filePart, dataDefVo, cliVo);
	}

	private Collection<File> doValidation(String filePath, MultipartFile filePart, DataDefinitionVo dataDefVo, ClientVo cliVo) throws CoreException {
		InputStream stream		= null;
		File fileToProcess		= StringUtil.isEmpty(filePath) ? null : new File(filePath);
		boolean mustCloseStream	= fileToProcess != null && fileToProcess.exists() && fileToProcess.canRead();
		
		try {
			if (mustCloseStream) {
				System.out.println("Doing validation of file: " + fileToProcess.getName());
				stream = new FileInputStream(fileToProcess);
			} else if (filePart != null) {
				System.out.println("Doing validation of stream");
				stream = filePart.getInputStream();
			}
			
			if (stream == null) throw new ProcessingException("Can't find stream to process.");
			
			long start = System.currentTimeMillis();
			AbstractDataProcessing processing	= DataProcessingFactory.getInstance().get(dataDefVo);
			this.autowireCapableBeanFactory.autowireBean(processing);
			Collection<File> toProcess			= processing.validateFile(cliVo, stream);
			long end = System.currentTimeMillis();
			
			System.out.println("Validation ended in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL) + " - with " + CollectionUtil.size(toProcess) + " files.");
			
			if (toProcess == null) {
				toProcess = new ArrayList<>(1);
				toProcess.add(mustCloseStream ? fileToProcess : null);
			}
			
			return toProcess;
		} catch (IOException | ProcessingException e) {
			throw new CoreException(e);
		} finally {
			if (mustCloseStream && stream != null) try { stream.close(); } catch (IOException e) { /* do nothing */ } 
		}
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	public DataProcessingVo doProcessing(DataDefinitionVo dataDefVo, Processing request, File fileToProcess, ClientVo cliVo) throws CoreException {
		Integer locId				= request.getLocationId();
		MultipartFile filePart		= request.getFilePart();
		DataProcessingVo dataProVo	= this.doProcessing(dataDefVo, locId, filePart, fileToProcess, cliVo);
		
		request.setResult(dataProVo.getDataProResult());
		
		return dataProVo;
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW)
	private DataProcessingVo doProcessing(DataDefinitionVo dataDefVo, DataDefinitionTrigger request, File fileToProcess, ClientVo cliVo) throws CoreException {
		Integer locId				= request.getLocationId();
		MultipartFile filePart		= request.getFilePart();
		DataProcessingVo dataProVo	= this.doProcessing(dataDefVo, locId, filePart, fileToProcess, cliVo);
		
		request.setResult(dataProVo.getDataProResult());
		
		return dataProVo;
	}

	private DataProcessingVo doProcessing(DataDefinitionVo dataDefVo, Integer locId, MultipartFile filePart, File fileToProcess, ClientVo cliVo) throws CoreException {
		DataProcessingVo dataProVo	= new DataProcessingVo();
		dataProVo.setCliId(cliVo.getCliId());
		dataProVo.setLocId(locId);
		dataProVo.setDataDefId(dataDefVo.getDataDefId());
		dataProVo.setDataProDateStart(new Date());
		
		ClientVo cliVoWithData		= null;
		StringBuilder processingLog	= new StringBuilder();
		InputStream stream			= null;
		boolean mustCloseStream		= fileToProcess != null && fileToProcess.exists() && fileToProcess.canRead();
		
		try {
			if (mustCloseStream) {
				System.out.println("Doing processing of file: " + fileToProcess.getName());
				stream = new FileInputStream(fileToProcess);
				dataProVo.setDataProFileName(fileToProcess.getName());
				
			} else if (filePart != null) {
				System.out.println("Doing processing of stream: " + filePart.getOriginalFilename());
				stream = filePart.getInputStream();
				dataProVo.setDataProFileName(filePart.getOriginalFilename());
			}
			
			if (stream == null) throw new ProcessingException("Can't find stream to process.");
			
			AbstractDataProcessing processing = DataProcessingFactory.getInstance().get(dataDefVo);
			this.autowireCapableBeanFactory.autowireBean(processing);
			processingLog.append("Execution of class: " + processing.getClass().getCanonicalName() + StringUtil.NEW_LINE);
			processing.prepareFor(cliVo, stream);
			long start = System.currentTimeMillis();
			try {
				processing.process();
			} finally {
				long end = System.currentTimeMillis();
				processingLog.append(processing.getLog());
				System.out.println("Processing of data ended in: " + DateUtil.formatDateTime(new Date(end - start), DateUtil.FMT_TIME_FULL));
			}
			cliVoWithData = processing.generateDataToSave();
			
			boolean continueWithStats = processing.continueWithSatistics();
			dataProVo.setDataProResult(continueWithStats ? DataProcessingVo.RESULT_PROCESSING_OK : DataProcessingVo.RESULT_PROCESSING_OK_PARTIAL);
			processingLog.append("Execution result code: " + dataProVo.getDataProResult() + StringUtil.NEW_LINE);
			if (! continueWithStats) processingLog.append("Continue with statistics: " + continueWithStats + StringUtil.NEW_LINE);
			
		} catch (IOException | ProcessingException e) {
			dataProVo.setDataProResult(DataProcessingVo.RESULT_PROCESSING_ERROR);
			processingLog.append(StringUtil.NEW_LINE + StringUtil.NEW_LINE);
			processingLog.append("Error: " + e.getLocalizedMessage() + StringUtil.NEW_LINE);
			processingLog.append(StringUtil.toString(e));
			
		} finally {
			dataProVo.setDataProDateEnd(new Date());
			
			if (mustCloseStream && stream != null) try { stream.close(); } catch (IOException e) { /* do nothing */ } 
		}
		
		this.dataProcessingDao.insert(dataProVo);
		File logFile	= new File(this.config.getPathLog(), DateUtil.formatDateTime(new Date(), DateUtil.FMT_DATE) + File.separator + "data_processing_" + dataProVo.getDataProId() + ".log");
		
		try {
			FileUtil.createPath(logFile.getParent());
			
			dataProVo.setDataProFileLog(logFile.getName());
			
			this.saveToFile(logFile, processingLog.toString());
		} catch (IOException | CoreException e) {
			System.out.println("Save log error" + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			if (DataProcessingVo.RESULT_PROCESSING_ERROR.equals(dataProVo.getDataProResult())) {
				try {
					this.emailService.sendMessageWithAttachment(
							this.config.getOnErrorSendEmailTo(), 
							"Processing error", 
							"Error found durring processing. See attached log for more information", 
							"procceing_error.log", 
							logFile.getAbsolutePath(), 
							false
							);
				} catch (CoreException e) {
					System.out.println("Send email with error, error: " + e.getLocalizedMessage());
					e.printStackTrace();	
				}
			}
		}
		
		try {
			if (cliVoWithData != null) this.saveData(dataProVo, cliVoWithData);
		} finally {
			dataProVo.setDataProDateEnd(new Date());
			this.dataProcessingDao.update(dataProVo);
		}
		return dataProVo;
	}
	
	private void processStatsAndAlerts(DataDefinitionVo dataDefVo, DataProcessingVo aResult) {
		if (DataProcessingVo.RESULT_PROCESSING_OK.equals(aResult.getDataProResult())) {
		  this.doStatistics(dataDefVo, aResult);
		  this.doAlerts(dataDefVo, aResult);
		}
	}
	
	//--- Implemented methods -------------------
	@Override public Collection<DataProcessingVo> process(Processing request, UserData userData) throws CoreException {
		ClientVo cliVo						= this.clientDao.findVo(userData.getCliId());
		DataDefinitionVo dataDefVo			= this.getDataDefinitionVo(request, cliVo);
		Collection<File> toProcess			= this.doValidation(dataDefVo, request, cliVo);
		Collection<DataProcessingVo> result = new ArrayList<>(CollectionUtil.size(toProcess));
		
		for (File aFile : toProcess) {
			cliVo				= this.clientDao.findVo(userData.getCliId());
			dataDefVo			= this.getDataDefinitionVo(request, cliVo);

			System.out.println("Processing file: " + (aFile == null ? "<stream>" : aFile.getAbsolutePath()));
			DataProcessingVo aResult		= this.doProcessing(dataDefVo, request, aFile, cliVo);
			this.processStatsAndAlerts(dataDefVo, aResult);
			result.add(aResult);
			if (aFile != null) aFile.delete();
		}

		return result;
	}

	@Override public Collection<DataProcessingVo> process(DataDefinitionTrigger request, UserData userData) throws CoreException {
		CliDataDefTriggerVo triggerVo		= this.cliDataDefTriggerDao.findFullVo(request.getId());
											  this.getDataDefinitionVo(request, triggerVo);
		ClientVo cliVo						= triggerVo.getClientVo();
		DataDefinitionVo dataDefVo			= triggerVo.getDataDefinitionVo();
		Collection<File> toProcess			= this.doValidation(dataDefVo, request, cliVo);
		Collection<DataProcessingVo> result = new ArrayList<>(CollectionUtil.size(toProcess));
		
		for (File aFile : toProcess) {
			System.out.println("Processing file: " + (aFile == null ? "<stream>" : aFile.getAbsolutePath()));
			DataProcessingVo aResult		= this.doProcessing(dataDefVo, request, aFile, cliVo);
			this.processStatsAndAlerts(dataDefVo, aResult);
			result.add(aResult);
		}

		return result;
	}

	@Override public Collection<DataProcessingVo> findAllClient(Integer cliId, UserData userData) {
		return CollectionUtil.subCollection(this.dataProcessingDao.finaAll(cliId), 0, 10);
	}
}

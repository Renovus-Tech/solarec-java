package tech.renovus.solarec.scheduler;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.business.AlertService;
import tech.renovus.solarec.business.CalculationService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefAlertDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefStatDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProAlertProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProStatProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.scheduler.data.DataProcessing;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.AlertProcessingVo;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefAlertDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProAlertProcessingVo;
import tech.renovus.solarec.vo.db.data.DataProStatProcessingVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;
import tech.renovus.solarec.vo.db.data.StationVo;

@Component
public class InvertersCheckScheduler {

	//--- Resources -----------------------------
	@Autowired AutowireCapableBeanFactory autowireCapableBeanFactory;
	@Autowired RenovusSolarecConfiguration config;
	
	@Resource CalculationService calculationService;
	@Resource AlertService alertService;
	
	@Resource ClientDao clientDao;
	@Resource GenDataDao genDataDao;
	@Resource StaDataDao staDataDao;
	@Resource LocationDao locationDao;
	@Resource GeneratorDao generatorDao;
	@Resource StationDao stationDao;
	@Resource DataProcessingDao dataProcessinDao;
	@Resource DataDefinitionDao dataDefinitionDao;
	@Resource DataDefStatDefinitionDao dataDefStatDefDao;
	@Resource DataDefAlertDefinitionDao dataDefAlertDefDao;
	@Resource DataProAlertProcessingDao dataProAlertProcessingDao;
	@Resource CliDataDefParameterDao cliDataDefParameterDao;
	@Resource LocDataDefParameterDao locDataDefParameterDao;
	@Resource GenDataDefParameterDao genDataDefParameterDao;
	@Resource DataProStatProcessingDao dataProStatProcessingDao;
	@Resource CliMetadataDao cliMetadataDao;
	@Resource LocMetadataDao locMetadataDao;
	@Resource GenMetadataDao genMetadataDao;
	@Resource CliLocAlertDao cliLocAlertDao;
	@Resource CliGenAlertDao cliGenAlertDao;
	
    //--- Private methods -----------------------
	private InverterService getService(DataDefinitionVo dataDefinitionVo) throws CoreException {
		try {
			Class<?> aClass = Class.forName(dataDefinitionVo.getDataDefExecutable());
			InverterService service = (InverterService) aClass.getConstructor().newInstance();
			this.autowireCapableBeanFactory.autowireBean(service);
			return service;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new CoreException(e);
		}
	}
	
	private String generateLogText(ClientVo clientVo, LocationVo locationVo, GeneratorVo generatorVo) {
		StringBuilder text = new StringBuilder();
		
		if (clientVo != null) {
			text.append(clientVo.getCliName()).append(" (").append(clientVo.getCliId()).append(") ");
		}
		if (locationVo != null) {
			text.append(locationVo.getLocName()).append(" (").append(locationVo.getLocId()).append(") ");
		}
		if (generatorVo != null) {
			text.append(generatorVo.getGenName()).append(" (").append(generatorVo.getGenId()).append(") ");
		}
		
		return text.toString();
	}
	
	private void doCalculation(DataProcessingVo dataProVo) throws CoreException {
		try {
			DataProcessing request = new DataProcessing(dataProVo);
			LoggerService.inverterLogger().info("Calculation for: " + JsonUtil.toString(dataProVo));
			LoggerService.inverterLogger().info("Request (" + this.config.getAlertCalculations() + "): " + JsonUtil.toString(request));
			
			Map<String, Object> params = new HashMap<>();
			params.put("param_json", JsonUtil.toString(request));
			
			String jsonResponse			= JsonUtil.get(this.config.getAlertCalculations(), params);
			LoggerService.inverterLogger().info("Response: " + jsonResponse);
			
		} catch (JsonProcessingException e) {
			LoggerService.inverterLogger().error("Error: " + e.getLocalizedMessage() + StringUtil.NEW_LINE + StringUtil.toString(e));
			throw new CoreException(e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void process(DataProcessingVo dataProVo, DataDefinitionVo dataDefVo, GeneratorVo genVo, Date currentDate) {
		ClientVo cliVo				= this.clientDao.findVo(genVo.getCliId());
		LocationVo locVo			= this.locationDao.findVo(genVo.getCliId(), genVo.getLocId());
		StationVo staVo 			= this.stationDao.findDefault(locVo.getCliId(), locVo.getLocId());
		
		cliVo.setDataDefParameters(this.cliDataDefParameterDao.getAllFor(cliVo.getCliId()));
		cliVo.setMetadata(this.cliMetadataDao.getAllFor(cliVo.getCliId()));

		locVo.setDataDefParameters(this.locDataDefParameterDao.getAllFor(locVo.getCliId(), locVo.getLocId()));
		locVo.setMetadata(this.locMetadataDao.getAllFor(locVo.getCliId(), locVo.getLocId()));

		genVo.setDataDefParameters(this.genDataDefParameterDao.getAllFor(genVo.getCliId(), genVo.getGenId()));
		genVo.setMetadata(this.genMetadataDao.getAllFor(genVo.getCliId(), genVo.getGenId()));
		
		cliVo.add(locVo);
		locVo.add(genVo);
		locVo.add(staVo);
		
		LoggerService.inverterLogger().info( "Start data retrieve for: " + this.generateLogText(cliVo, locVo, genVo));
		
		try {
			InverterService service = this.getService(dataDefVo);
			
			LoggerService.inverterLogger().info( "Service to use: " + service.getClass().getName());
			
			service.prepareFor(cliVo);
			boolean canRetrieve = service.canRetrieve();
			
			if (canRetrieve) {
				InverterData newData = service.retrieveData();
				boolean continueWithStats = service.continueWithStats();
				dataProVo.setDataProResult(continueWithStats ? DataProcessingVo.RESULT_PROCESSING_OK : DataProcessingVo.RESULT_PROCESSING_OK_PARTIAL);
				
				if (newData != null) {
					if (CollectionUtil.notEmpty(newData.getGeneratorData())) {
						for (GenDataVo dataVo : newData.getGeneratorData()) {
							dataVo.setCliId(genVo.getCliId());
							dataVo.setGenId(genVo.getGenId());
							dataVo.setDataDateAdded(currentDate);
							dataVo.setDataProId(dataProVo.getDataProId());
							dataVo.setSyncType(GeneratorVo.SYNC_INSERT_UPDATE);
						}
					}
					
					if (CollectionUtil.notEmpty(newData.getStationData())) {
						for (StaDataVo dataVo : newData.getStationData()) {
							dataVo.setCliId(staVo.getCliId());
							dataVo.setStaId(staVo.getStaId());
							dataVo.setDataDateAdded(currentDate);
							dataVo.setDataProId(dataProVo.getDataProId());
							dataVo.setSyncType(GeneratorVo.SYNC_INSERT_UPDATE);
						}
					}
					
					if (CollectionUtil.notEmpty(newData.getGeneratorAlerts())) {
						for (CliGenAlertVo alertVo : newData.getGeneratorAlerts()) {
							alertVo.setCliId(genVo.getCliId());
							alertVo.setGenId(genVo.getGenId());
							alertVo.setSyncType(GeneratorVo.SYNC_INSERT_UPDATE);
						}
					}
					
					if (CollectionUtil.notEmpty(newData.getLocationAlerts())) {
						for (CliLocAlertVo alertVo : newData.getLocationAlerts()) {
							alertVo.setCliId(genVo.getCliId());
							alertVo.setLocId(genVo.getGenId());
							alertVo.setSyncType(GeneratorVo.SYNC_INSERT_UPDATE);
						}
					}
				}
				
				genVo.setChildrensId();
				staVo.setChildrensId();
				locVo.setChildrensId();
				cliVo.setChildrensId();
				
				genVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				staVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				locVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				cliVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				
				if (CollectionUtil.notEmpty(genVo.getDataDefParameters())) {
					genVo.getDataDefParameters().forEach(x -> x.setSyncType(StringUtil.isEmpty(x.getGenDataDefParValue()) ? GenDataDefParameterVo.SYNC_INIT : x.getSyncType()));
				}
				if (CollectionUtil.notEmpty(locVo.getDataDefParameters())) {
					locVo.getDataDefParameters().forEach(x -> x.setSyncType(StringUtil.isEmpty(x.getLocDataDefParValue()) ? GenDataDefParameterVo.SYNC_INIT : x.getSyncType()));
				}
				if (CollectionUtil.notEmpty(cliVo.getDataDefParameters())) {
					cliVo.getDataDefParameters().forEach(x -> x.setSyncType(StringUtil.isEmpty(x.getCliDataDefParValue()) ? GenDataDefParameterVo.SYNC_INIT : x.getSyncType()));
				}
				
				LoggerService.inverterLogger().info( "Doing synchronization of data to databaes... ");
				LoggerService.inverterLogger().info( "\tGenerator data: " + CollectionUtil.size(newData == null ? null : newData.getGeneratorData()));
				LoggerService.inverterLogger().info( "\tStation data: " + CollectionUtil.size(newData == null ? null : newData.getStationData()));
				LoggerService.inverterLogger().info( "\tLocation alerts: " + CollectionUtil.size(newData == null ? null : newData.getLocationAlerts()));
				LoggerService.inverterLogger().info( "\tGenerator alerts: " + CollectionUtil.size(newData == null ? null : newData.getGeneratorAlerts()));
				
				this.genDataDefParameterDao.synchronize(genVo.getDataDefParameters());
				this.locDataDefParameterDao.synchronize(locVo.getDataDefParameters());
				this.cliDataDefParameterDao.synchronize(cliVo.getDataDefParameters());
				this.genMetadataDao.synchronize(genVo.getMetadata());
				this.locMetadataDao.synchronize(locVo.getMetadata());
				this.cliMetadataDao.synchronize(cliVo.getMetadata());
				
				if (newData != null) {
					this.genDataDao.synchronize(newData.getGeneratorData());
					this.staDataDao.synchronize(newData.getStationData());
					this.cliLocAlertDao.synchronize(newData.getLocationAlerts());
					this.cliGenAlertDao.synchronize(newData.getGeneratorAlerts());
				}
				
				if (continueWithStats) {
					this.doCalculation(dataProVo);
				}
				
			} else {
				dataProVo.setDataProResult(DataProcessingVo.RESULT_PROCESSING_ERROR);
				LoggerService.inverterLogger().info( "Data not retrieve, reason: " + service.getReasonWhyCantRetrieve());
			}
		} catch (CoreException | InveterServiceException e) {
			dataProVo.setDataProResult(DataProcessingVo.RESULT_PROCESSING_ERROR);
			LoggerService.inverterLogger().error(StringUtil.toString(e));
		} finally {
			dataProVo.setDataProDateEnd(new Date());
			LoggerService.inverterLogger().info( "End data retrieve for: " + this.generateLogText(cliVo, locVo, genVo));
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private DataProcessingVo generateDataProcessing(GeneratorVo genVo, Date currentDate) {
		DataProcessingVo dataProVo	= new DataProcessingVo();
		dataProVo.setCliId(genVo.getCliId());
		dataProVo.setLocId(genVo.getLocId());
		dataProVo.setGenId(genVo.getGenId());
		dataProVo.setDataDefId(genVo.getDataDefId());
		dataProVo.setDataProDateStart(currentDate);
		dataProVo.setDataProResult(DataProcessingVo.RESULT_PROCESSING_PROCESSING);
		
		this.dataProcessinDao.insert(dataProVo);
		
		return dataProVo;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void synchronize(DataProcessingVo dataProVo) {
		this.dataProcessinDao.update(dataProVo);
	}
	
	private void processStatsAndAlerts(DataDefinitionVo dataDefVo, DataProcessingVo aResult) {
		if (DataProcessingVo.RESULT_PROCESSING_OK.equals(aResult.getDataProResult())) {
		  this.doStatistics(dataDefVo, aResult);
		  this.doAlerts(dataDefVo, aResult);
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

	//--- Public methods ------------------------
	@Scheduled(cron="0 0 02 * * *") // daily at 2am
	public void checkInverters() {
		Collection<GeneratorVo> candidate = this.generatorDao.getAllGeneratorsWithDataDefinitionInverter();
		Collection<DataDefinitionVo> dataDefinitions = this.dataDefinitionDao.getAllInvertersDefinitions();
		
		Map<Integer, DataDefinitionVo> definitions = dataDefinitions.stream().collect(Collectors.toMap(DataDefinitionVo::getDataDefId, Function.identity()));
		Date currentDate = new Date();
		
		LoggerService.inverterLogger().info("Starting check of inverters data");
		
		try {
			for (GeneratorVo genVo : candidate) {
				if (! FlagUtil.getFlagValue(genVo, GeneratorVo.FLAG_ENABLED)) {
					LoggerService.inverterLogger().info("Skkiping (not enabled): " + this.generateLogText(null, null, genVo));
					continue;
				}
				if (! definitions.containsKey(genVo.getDataDefId())) {
					LoggerService.inverterLogger().info("Skkiping (invalida data definition): " + this.generateLogText(null, null, genVo));
					continue;
				}
				
				DataDefinitionVo dataDefVo	= definitions.get(genVo.getDataDefId());
				DataProcessingVo dataProVo	= this.generateDataProcessing(genVo, currentDate);
				try {
					this.process(dataProVo, dataDefVo, genVo, currentDate);
				} catch (Exception e) {
					dataProVo.setDataProResult(DataProcessingVo.RESULT_PROCESSING_ERROR);
					throw e;
				} finally {
					this.synchronize(dataProVo);
					this.locationDao.updateDataDateMaxMin();
					this.generatorDao.updateDataDateMaxMin();
					this.stationDao.updateDataDateMaxMin();
				}
				
				this.processStatsAndAlerts(dataDefVo, dataProVo);
			}
		} catch (Exception e) {
			LoggerService.inverterLogger().error("Error during data retrieval (all stoped): " + e.getLocalizedMessage(), e);
		} finally {
			LoggerService.inverterLogger().info("End check of inverters data");
		}
	}
}

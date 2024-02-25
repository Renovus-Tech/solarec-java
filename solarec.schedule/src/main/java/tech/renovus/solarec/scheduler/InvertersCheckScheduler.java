package tech.renovus.solarec.scheduler;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tech.renovus.solarec.business.AlertService;
import tech.renovus.solarec.business.CalculationService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefAlertDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefStatDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProAlertProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProStatProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.AlertProcessingVo;
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
import tech.renovus.solarec.vo.db.data.StatProcessingVo;

@Component
public class InvertersCheckScheduler {

	//--- Resources -----------------------------
	@Autowired RenovusSolarecConfiguration config;
	
	@Resource CalculationService calculationService;
	@Resource AlertService alertService;
	
	@Resource ClientDao clientDao;
	@Resource GenDataDao genDataDao;
	@Resource LocationDao locationDao;
	@Resource GeneratorDao generatorDao;
	@Resource DataProcessingDao dataProcessinDao;
	@Resource DataDefinitionDao dataDefinitionDao;
	@Resource DataDefStatDefinitionDao dataDefStatDefDao;
	@Resource DataDefAlertDefinitionDao dataDefAlertDefDao;
	@Resource DataProAlertProcessingDao dataProAlertProcessingDao;
	@Resource CliDataDefParameterDao cliDataDefParameterDao;
	@Resource LocDataDefParameterDao locDataDefParameterDao;
	@Resource GenDataDefParameterDao genDataDefParameterDao;
	@Resource DataProStatProcessingDao dataProStatProcessingDao;
	
    //--- Private methods -----------------------
	private InverterService getService(DataDefinitionVo dataDefinitionVo) throws CoreException {
		try {
			Class<?> aClass = Class.forName(dataDefinitionVo.getDataDefExecutable());
			return (InverterService) aClass.getConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new CoreException(e);
		}
	}
	
	private String generateLogText(ClientVo clientVo, LocationVo locationVo, GeneratorVo generatorVo) {
		StringBuilder text = new StringBuilder();
		
		if (clientVo != null) text.append("(").append(clientVo.getCliId()).append(") ");
		if (locationVo != null) text.append("(").append(locationVo.getCliId()).append(") ");
		if (generatorVo != null) text.append("(").append(generatorVo.getCliId()).append(") ");
		
		return text.toString();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void process(DataProcessingVo dataProVo, DataDefinitionVo dataDefVo, GeneratorVo genVo, Date currentDate) {
		ClientVo cliVo				= this.clientDao.findVo(genVo.getCliId());
		LocationVo locVo			= this.locationDao.findVo(genVo.getCliId(), genVo.getLocId());
		
		cliVo.setDataDefParameters(this.cliDataDefParameterDao.getAllFor(cliVo.getCliId()));
		locVo.setDataDefParameters(this.locDataDefParameterDao.getAllFor(locVo.getCliId(), locVo.getLocId()));
		genVo.setDataDefParameters(this.genDataDefParameterDao.getAllFor(genVo.getCliId(), genVo.getGenId()));
		
		cliVo.add(locVo);
		locVo.add(genVo);
		
		LoggerService.inverterLogger().info( "Start data retrieve for: " + this.generateLogText(cliVo, locVo, genVo));
		
		try {
			InverterService service = this.getService(dataDefVo);
			
			service.prepareFor(cliVo);
			boolean canRetrieve = service.canRetrieve();
			
			if (canRetrieve) {
				Collection<GenDataVo> newData = service.retrieveData();
				
				dataProVo.setDataProResult(service.continueWithStats() ? DataProcessingVo.RESULT_PROCESSING_OK : DataProcessingVo.RESULT_PROCESSING_OK_PARTIAL);
				
				if (CollectionUtil.notEmpty(newData)) {
					for (GenDataVo dataVo : newData) {
						dataVo.setCliId(genVo.getCliId());
						dataVo.setGenId(genVo.getGenId());
						dataVo.setDataDateAdded(currentDate);
						dataVo.setDataProId(dataProVo.getDataProId());
						dataVo.setSyncType(GeneratorVo.SYNC_INSERT);
					}
				}
				
				genVo.setChildrensId();
				locVo.setChildrensId();
				cliVo.setChildrensId();
				
				genVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				locVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				cliVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
				
				if (CollectionUtil.notEmpty(genVo.getDataDefParameters())) genVo.getDataDefParameters().forEach(x -> x.setSyncType(StringUtil.isEmpty(x.getGenDataDefParValue()) ? GenDataDefParameterVo.SYNC_INIT : x.getSyncType()));
				if (CollectionUtil.notEmpty(locVo.getDataDefParameters())) locVo.getDataDefParameters().forEach(x -> x.setSyncType(StringUtil.isEmpty(x.getLocDataDefParValue()) ? GenDataDefParameterVo.SYNC_INIT : x.getSyncType()));
				if (CollectionUtil.notEmpty(cliVo.getDataDefParameters())) cliVo.getDataDefParameters().forEach(x -> x.setSyncType(StringUtil.isEmpty(x.getCliDataDefParValue()) ? GenDataDefParameterVo.SYNC_INIT : x.getSyncType()));
				
				this.genDataDao.synchronize(newData);
				this.genDataDefParameterDao.synchronize(genVo.getDataDefParameters());
				this.locDataDefParameterDao.synchronize(locVo.getDataDefParameters());
				this.cliDataDefParameterDao.synchronize(cliVo.getDataDefParameters());
			} else {
				dataProVo.setDataProResult(DataProcessingVo.RESULT_PROCESSING_ERROR);
				LoggerService.inverterLogger().info( "Dat not retrieve, reason: " + service.getReasonWhyCantRetrieve());
			}
		} catch (CoreException e) {
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
//					this.stationDao.updateDataDateMaxMin();
				}
				
				this.processStatsAndAlerts(dataDefVo, dataProVo);
			}
		} catch (Exception e) {
			LoggerService.inverterLogger().error("Error during data retrieval (all stpted): " + e.getLocalizedMessage(), e);
		} finally {
			LoggerService.inverterLogger().info("End check of inverters data");
		}
	}
}

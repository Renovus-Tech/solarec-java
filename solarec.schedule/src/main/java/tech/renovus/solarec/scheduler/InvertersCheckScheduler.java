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

import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

@Component
public class InvertersCheckScheduler {

	//--- Resources -----------------------------
	@Autowired RenovusSolarecConfiguration config;
	@Resource GeneratorDao generatorDao;
	@Resource DataDefinitionDao dataDefinitionDao;
	@Resource ClientDao clientDao;
	@Resource LocationDao locationDao;
	@Resource CliDataDefParameterDao cliDataDefParameterDao;
	@Resource LocDataDefParameterDao locDataDefParameterDao;
	@Resource GenDataDefParameterDao genDataDefParameterDao;
	@Resource GenDataDao genDataDao;
	
    //--- Private methods -----------------------
	private InverterService getService(DataDefinitionVo dataDefinitionVo) throws CoreException {
		try {
			Class<?> aClass = Class.forName(dataDefinitionVo.getDataDefExecutable());
			return (InverterService) aClass.getConstructor().newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new CoreException(e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void process(GeneratorVo generatorVo, Map<Integer, DataDefinitionVo> definitions, Date currentDate) {
		DataDefinitionVo dataDefinitionVo = definitions.get(generatorVo.getDataDefId());
		ClientVo clientVo = this.clientDao.findVo(generatorVo.getCliId());
		LocationVo locationVo = this.locationDao.findVo(generatorVo.getCliId(), generatorVo.getLocId());
		
		clientVo.setDataDefParameters(this.cliDataDefParameterDao.getAllFor(clientVo.getCliId()));
		locationVo.setDataDefParameters(this.locDataDefParameterDao.getAlLFor(locationVo.getCliId(), locationVo.getLocId()));
		generatorVo.setDataDefParameters(this.genDataDefParameterDao.getAllFor(generatorVo.getCliId(), generatorVo.getGenId()));
		
		clientVo.add(locationVo);
		locationVo.add(generatorVo);
		
		try {
			InverterService service = this.getService(dataDefinitionVo);
			Collection<GenDataVo> newData = service.retrieveData(clientVo);
			
			if (CollectionUtil.notEmpty(newData)) {
				for (GenDataVo dataVo : newData) {
					dataVo.setCliId(generatorVo.getCliId());
					dataVo.setGenId(generatorVo.getGenId());
					dataVo.setDataDateAdded(currentDate);
					dataVo.setSyncType(GeneratorVo.SYNC_INSERT);
				}
			}
			
			generatorVo.setChildrensId();
			locationVo.setChildrensId();
			clientVo.setChildrensId();
			
			generatorVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
			locationVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
			clientVo.synchronizeForce(GeneratorVo.SYNC_INSERT_UPDATE);
			
			this.genDataDao.synchronize(newData);
			this.genDataDefParameterDao.synchronize(generatorVo.getDataDefParameters());
			this.locDataDefParameterDao.synchronize(locationVo.getDataDefParameters());
			this.cliDataDefParameterDao.synchronize(clientVo.getDataDefParameters());
		} catch (CoreException e) {
			LoggerService.inverterLogger().error(StringUtil.toString(e));
		}
	}
	
	//--- Public methods ------------------------
	@Scheduled(cron="0 0 02 * * *") // daily at 2am
	public void checkRest() {
		Collection<GeneratorVo> candidate = this.generatorDao.getAllGeneratorsWithDataDefinitionInverter();
		Collection<DataDefinitionVo> dataDefinitions = this.dataDefinitionDao.getAllInvertersDefinitions();
		
		Map<Integer, DataDefinitionVo> definitions = dataDefinitions.stream().collect(Collectors.toMap(DataDefinitionVo::getDataDefId, Function.identity()));
		Date currentDate = new Date();
		
		for (GeneratorVo generatorVo : candidate) {
			if (! FlagUtil.getFlagValue(generatorVo, GeneratorVo.FLAG_ENABLED)) continue;
			if (! definitions.containsKey(generatorVo.getDataDefId())) continue;
			
			this.process(generatorVo, definitions, currentDate);
		}
	}
}

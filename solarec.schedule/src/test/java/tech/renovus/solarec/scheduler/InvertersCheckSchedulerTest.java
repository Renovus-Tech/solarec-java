package tech.renovus.solarec.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import tech.renovus.solarec.db.data.dao.interfaces.CliDataDefParameterDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliMetadataDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
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
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;

@RunWith(MockitoJUnitRunner.class)
public class InvertersCheckSchedulerTest {

	@Mock private AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	@Mock private ClientDao clientDao;
	@Mock private LocationDao locationDao;
	@Mock private GeneratorDao generatorDao;
	@Mock private StationDao stationDao;
	@Mock private DataDefinitionDao dataDefinitionDao;
	@Mock private DataProcessingDao dataProcessinDao;
	@Mock private CliDataDefParameterDao cliDataDefParameterDao;
	@Mock private LocDataDefParameterDao locDataDefParameterDao;
	@Mock private GenDataDefParameterDao genDataDefParameterDao;
	@Mock private CliMetadataDao cliMetadataDao;
	@Mock private GenDataDao genDataDao;
	@Mock private StaDataDao staDataDao;
	@Mock private LocMetadataDao locMetadataDao;
	@Mock private GenMetadataDao genMetadataDao;
	@Mock private CliLocAlertDao cliLocAlertDao;
	@Mock private CliGenAlertDao cliGenAlertDao;
//	@Mock private DataDefAlertDefinitionDao dataDefAlertDefDao;
//	@Mock private DataProAlertProcessingDao dataProAlertProcessingDao;
//	@Mock private DataDefStatDefinitionDao dataDefStatDefDao;
//	@Mock private DataProStatProcessingDao dataProStatProcessingDao;
	
	@InjectMocks private InvertersCheckScheduler service;
	
	@Test public void test() throws Exception {
		ClientVo cliVo				= createClientVo();
		LocationVo locVo            = createLocationVo();
		GeneratorVo genVo			= createGeneratorVo();
		DataDefinitionVo dataDefVo	= generateDateDefinitionVo();
		StationVo staVo				= createStationVo();
		
		Collection<GeneratorVo> candidate				= Arrays.asList(genVo);
		Collection<DataDefinitionVo> dataDefinitions	= Arrays.asList(dataDefVo);
		
		when(this.clientDao			.findVo(any())).thenReturn(cliVo);
		when(this.locationDao		.findVo(any(), any())).thenReturn(locVo);
		when(this.stationDao		.findDefault(any(), any())).thenReturn(staVo);
		when(this.generatorDao		.getAllGeneratorsWithDataDefinitionInverter()).thenReturn(candidate);
		when(this.dataDefinitionDao	.getAllInvertersDefinitions()).thenReturn(dataDefinitions);
		
		when(this.cliDataDefParameterDao	.getAllFor(any())).thenReturn(new ArrayList<>());
		when(this.cliMetadataDao			.getAllFor(any())).thenReturn(new ArrayList<>());
		when(this.locDataDefParameterDao	.getAllFor(any(), any())).thenReturn(new ArrayList<>());
		when(this.locMetadataDao			.getAllFor(any(), any())).thenReturn(new ArrayList<>());
		when(this.genDataDefParameterDao	.getAllFor(any(), any())).thenReturn(new ArrayList<>());
		when(this.genMetadataDao			.getAllFor(any(), any())).thenReturn(new ArrayList<>());

		doNothing().when(this.genDataDefParameterDao).synchronize(anyCollection());
		doNothing().when(this.locDataDefParameterDao).synchronize(anyCollection());
//		doNothing().when(this.cliDataDefParameterDao).synchronize(anyCollection());
		doNothing().when(this.genMetadataDao).synchronize(anyCollection());
		doNothing().when(this.locMetadataDao).synchronize(anyCollection());
		doNothing().when(this.cliMetadataDao).synchronize(anyCollection());
		doNothing().when(this.cliDataDefParameterDao).synchronize(anyCollection());
		doNothing().when(this.genDataDao).synchronize(anyCollection());
		doNothing().when(this.staDataDao).synchronize(anyCollection());
		doNothing().when(this.cliLocAlertDao).synchronize(anyCollection());
		doNothing().when(this.cliGenAlertDao).synchronize(anyCollection());
		
		this.service.checkInverters();

		assertTrue(InverterServiceTest.calledPrepareFor);
		assertTrue(InverterServiceTest.calledCanRetrieve);
		assertTrue(InverterServiceTest.calledContinueWithStats);
		assertTrue(InverterServiceTest.calledRetrieveData);
		assertFalse(InverterServiceTest.calledGetReasonWhyCantRetrieve);
		
		verify(this.genDataDefParameterDao, times(1)).synchronize(anyCollection());
		verify(this.locDataDefParameterDao, times(1)).synchronize(anyCollection());
		verify(this.cliDataDefParameterDao, times(1)).synchronize(anyCollection());
		verify(this.genMetadataDao, times(1)).synchronize(anyCollection());
		verify(this.locMetadataDao, times(1)).synchronize(anyCollection());
		verify(this.cliMetadataDao, times(1)).synchronize(anyCollection());
		verify(this.cliDataDefParameterDao, times(1)).synchronize(anyCollection());

		verify(this.genDataDao, times(1)).synchronize(anyCollection());
		verify(this.staDataDao, times(1)).synchronize(anyCollection());
		verify(this.cliLocAlertDao, times(1)).synchronize(anyCollection());
		verify(this.cliGenAlertDao, times(1)).synchronize(anyCollection());
	}

	private ClientVo createClientVo() {
        ClientVo cliVo = new ClientVo();
        cliVo.setCliId(1);
        
        return cliVo;
	}
	
	private StationVo createStationVo() {
		StationVo staVo = new StationVo();
		staVo.setCliId(1);
		staVo.setLocId(2);
		staVo.setStaId(3);
		
		return staVo;
	}

	private DataDefinitionVo generateDateDefinitionVo() {
		DataDefinitionVo dataDefVo = new DataDefinitionVo();
		dataDefVo.setDataDefId(1);
		dataDefVo.setDataDefExecutable("tech.renovus.solarec.scheduler.InverterServiceTest");
		return dataDefVo;
	}

	private LocationVo createLocationVo() {
		LocationVo locVo = new LocationVo();
		locVo.setCliId(1);
		locVo.setLocId(2);
		locVo.setDataDefId(1);
		return locVo;
	}
	
	private GeneratorVo createGeneratorVo() {
		GeneratorVo genVo = new GeneratorVo();
		FlagUtil.setFlagValue(genVo, GeneratorVo.FLAG_ENABLED, true);
		genVo.setCliId(1);
		genVo.setLocId(2);
		genVo.setGenId(3);
		genVo.setDataDefId(1);
		return genVo;
	}
}

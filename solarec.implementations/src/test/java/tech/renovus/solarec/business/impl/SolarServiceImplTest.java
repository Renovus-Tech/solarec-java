package tech.renovus.solarec.business.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ParserService;
import tech.renovus.solarec.business.impl.chart.ChartFactory;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StatDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@RunWith(MockitoJUnitRunner.class)
public class SolarServiceImplTest {

	@Mock private ParserService parserService;
	@Mock private GeneratorDao generatorDao;
	@Mock private CliLocAlertDao cliLocAlertDao;
	@Mock private CliGenAlertDao cliGenAlertDao;
	@Mock private StatDefinitionDao staDefDao;
	@Mock private CliSettingDao cliSettingDao;
	@Mock private ClientDao cliDao;
	@Mock private LocationDao locDao;
	@Mock private GeneratorDao genDao;
	@Mock private GenDataDao genDataDao;
	@Mock private StationDao staDao;
	@Mock private StaDataDao staDataDao;
	@Mock private ChartFactory factory;
	@Mock private RenovusSolarecConfiguration config;
	
	@InjectMocks SolarServiceImpl service = new SolarServiceImpl();
	
	@Test 
	public void test() {
		ClientVo cliVo = new ClientVo(Integer.valueOf(1));
		LocationVo locVo = new LocationVo(cliVo.getCliId(), Integer.valueOf(2));
		ChartFilter filter = new ChartFilter();
		UserData userData = new UserData();
		userData.setClientVo(cliVo);
		userData.setLocationVo(locVo);
		CoreException exception = null;
		
		Collection<CliLocAlertVo> cliLocAlerts = Arrays.asList(
				new CliLocAlertVo(),
				new CliLocAlertVo()
			);
		Collection<CliGenAlertVo> cliGenAlerts = Arrays.asList(
				new CliGenAlertVo(),
				new CliGenAlertVo()
				);
		
		when(this.cliLocAlertDao.retrieveFor(any(), any(), any(), any())).thenReturn(cliLocAlerts);
		when(this.cliGenAlertDao.retrieveFor(any(), any(), any(), any())).thenReturn(cliGenAlerts);
		
		try {
			assertNotNull(this.service.runOverview(filter, userData));
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		exception = null;
		try {
			assertNotNull(this.service.runClimate(filter, userData));
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		exception = null;
		try {
			assertNotNull(this.service.runPerformanceIndex(filter, userData));
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		exception = null;
		try {
			assertNotNull(this.service.retrieveOverviewCo2(filter, userData));
		this.service.revenue(filter, userData);
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		exception = null;
		try {
			assertNotNull(this.service.revenueSales(filter, userData));
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		exception = null;
		try {
			assertNotNull(this.service.retrieveOverviewAlerts(filter, userData));
		} catch (CoreException e) {
			exception = e;
		}
		
		assertNull(exception);
		
		verify(this.cliLocAlertDao).retrieveFor(any(), any(), any(), any());
		verify(this.cliGenAlertDao).retrieveFor(any(), any(), any(), any());
	}
}

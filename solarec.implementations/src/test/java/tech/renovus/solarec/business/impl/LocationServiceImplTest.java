package tech.renovus.solarec.business.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocEstimationDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {
	
	@Mock private LocationDao dao;
	@Mock private GeneratorDao generatorsDao;
	@Mock private StationDao stationsDao;
	@Mock private DataDefinitionDao dataDefinitionDao;
	@Mock private LocEstimationDao locEstimationDao;
	@Mock private CountryDao countryDao;
	
	@InjectMocks LocationServiceImpl service = new LocationServiceImpl();
	
	@Test 
	public void test() {
		
	}
}

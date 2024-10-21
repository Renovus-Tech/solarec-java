package tech.renovus.solarec.business.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.db.data.dao.interfaces.DocGeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenNeighbourDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenPowerDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenStatisticDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorServiceImplTest {

	@Mock private GeneratorDao dao;
	@Mock private DocGeneratorDao docGeneratorDao;
	@Mock private GenStatisticDao genStatisticDao;
	@Mock private GenNeighbourDao genNeighbourDao;
	@Mock private GenDataDao genDataDao;
	@Mock private GenPowerDao genPowerDao;
	@Mock private LocationDao locationDao;
	
	@InjectMocks GeneratorServiceImpl service = new GeneratorServiceImpl();
	
	@Test 
	public void test() {
		
	}
}

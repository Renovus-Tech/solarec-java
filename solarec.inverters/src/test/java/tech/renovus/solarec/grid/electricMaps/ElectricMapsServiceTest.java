package tech.renovus.solarec.grid.electricMaps;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.grid.DataGridService.DataGridServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

@RunWith(MockitoJUnitRunner.class)
public class ElectricMapsServiceTest {
	
	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	
	@InjectMocks private ElectricMapsService service = new ElectricMapsService();
	
	private static String key;

	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		ElectricMapsServiceTest.key		= System.getProperty("electricMap_key");
		
		boolean allDataRequired = StringUtil.notEmpty(ElectricMapsServiceTest.key);
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: electricMap_key", allDataRequired);
	}
	
	//--- Tests ---------------------------------
	@Test public void testService() {
		CountryVo ctrVo = new CountryVo();
		ctrVo.setCtrCode2("UY");
		
		ElectricMapsConfiguration config = new ElectricMapsConfiguration();
		config.setKey(ElectricMapsServiceTest.key);
		
		assertEquals(ElectricMapsServiceTest.key, config.getKey());
		
		DataGridServiceException exception = null;
		try {
			Collection<CtrDataVo> data = this.service.retrieveGridData(ctrVo);
			
			assertNotNull(data);
			assertTrue(CollectionUtil.notEmpty(data));
		} catch (DataGridServiceException e) {
			exception = e;
		}
		
		assertNull(exception);
	}
}

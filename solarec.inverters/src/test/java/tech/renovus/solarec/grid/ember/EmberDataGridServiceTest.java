package tech.renovus.solarec.grid.ember;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.grid.DataGridService.DataGridServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

public class EmberDataGridServiceTest {

	//--- Tests ---------------------------------
	@Test public void testService() {
		CountryVo ctrVo = new CountryVo();
		ctrVo.setCtrCode3("URY");
		
		DataGridService service = new EmberDataGridService();
		
		DataGridServiceException exception = null;
		try {
			Collection<CtrDataVo> data = service.retrieveGridData(ctrVo);
			
			assertNotNull(data);
			assertTrue(CollectionUtil.notEmpty(data));
		} catch (DataGridServiceException e) {
			exception = e;
		}
		
		assertNull(exception);
	}
}

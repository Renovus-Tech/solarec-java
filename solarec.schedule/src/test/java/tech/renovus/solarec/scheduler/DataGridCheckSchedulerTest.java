package tech.renovus.solarec.scheduler;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.CtrDataDao;
import tech.renovus.solarec.grid.DataGridService;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.CtrDataVo;

@RunWith(MockitoJUnitRunner.class)
public class DataGridCheckSchedulerTest {

	@Mock private DataGridService dataGridService;
	@Mock private CountryDao countryDao;
	@Mock private CtrDataDao ctrDataDao;
	
	@InjectMocks private DataGridCheckScheduler srevice;
	
	@Test public void testScheduler() throws Exception {
		CountryVo ctrVo = new CountryVo();
		CtrDataVo aData = new CtrDataVo();
		Collection<CountryVo> countries = Arrays.asList(ctrVo);
		Collection<CtrDataVo> datas = Arrays.asList(aData);
		
		//Set mock behavior
		when(dataGridService.retrieveGridData(ctrVo)).thenReturn(datas);
		when(countryDao.getCountriesInUse()).thenReturn(countries);
		doNothing().when(countryDao).synchronize(ctrVo);
		doNothing().when(ctrDataDao).synchronize(datas);
		
		this.srevice.checkDataGrid();
		
		verify(dataGridService, times(1)).retrieveGridData(ctrVo);
		verify(countryDao, times(1)).getCountriesInUse();
		verify(countryDao, times(1)).synchronize(ctrVo);
		verify(ctrDataDao, times(1)).synchronize(datas);
	}
}

package tech.renovus.solarec.scheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.business.ReportService;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.RepTypeDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.RepTypeVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.report.ReportRequest;

@RunWith(MockitoJUnitRunner.class)
public class ReportSchedulerTest {

	@Mock private RepTypeDao repTypeDao;
	@Mock private LocationDao locationDao;
	@Mock private UsersDao usersDao;
	@Mock private ReportService reportService;
	
	@InjectMocks private ReportScheduler service;
	
	@Test public void test() throws Exception {
		RepTypeVo repTypeVo = new RepTypeVo();
		repTypeVo.setRepTypeId(1);
		repTypeVo.setRepTypeName("test");
		
		FlagUtil.setFlagValue(repTypeVo, RepTypeVo.FLAG_ENABLED, true);
		Collection<LocationVo> locationsForReport = new ArrayList<>();
		Collection<UsersVo> usersForLocationReport = new ArrayList<>();
			
		LocationVo locVo = new LocationVo();
		locVo.setCliId(1);
		locVo.setLocId(1);
		locationsForReport.add(locVo);
		
		UsersVo usrVo = new UsersVo();
		usrVo.setUsrEmail("fake@email.address.com");
		FlagUtil.setFlagValue(usrVo, UsersVo.FLAG_RECEIVE_REPORT_BY_EMAIL_BCC, true);
		
		usersForLocationReport.add(usrVo);
		
		when(this.repTypeDao.findByName(ReportRequest.TYPE_WEEKLY)).thenReturn(repTypeVo);
		when(this.locationDao.getAllForReport()).thenReturn(locationsForReport);
		when(this.usersDao.getAllForLocationReport(any(), any(), any())).thenReturn(usersForLocationReport);
		
		this.service.weekly();
		
		verify(this.reportService, times(1)).generateReport(any());
	}
}

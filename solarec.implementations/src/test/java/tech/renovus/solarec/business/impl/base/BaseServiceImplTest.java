package tech.renovus.solarec.business.impl.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.chart.ChartFactory;
import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.db.data.dao.interfaces.StatDefinitionDao;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@RunWith(MockitoJUnitRunner.class)
public class BaseServiceImplTest {

	public static class TestAbstractChart extends AbstractChart {

		public boolean executed = false;
		
		@Override
		public Object execute() {
			this.executed = true;
			return null;
		}
		
	}
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	@Mock private StatDefinitionDao staDefDao;
	@Mock private ChartFactory chartFactory;
	
	@InjectMocks BaseServiceImpl service = new BaseServiceImpl();
	
	@Test 
	public void test() throws Exception {
		Date now = this.formatter.parse("20241019");
		BaseServiceImpl service = new BaseServiceImpl();
		
		LocationVo locVo = new LocationVo();
		locVo.setLocId(Integer.valueOf(2));
		locVo.setLocDemoDate(now);

		UserData userData = new UserData();
		userData.setLocationVo(locVo);
		
		ChartFilter updated = service.validate(null, userData);
		assertTrue(updated.getFrom().before(now));
		assertEquals(ChartFilter.PERIOD_12_WEEKS, updated.getPeriod());
		assertEquals(ChartFilter.GROUP_BY_WEEK, updated.getGroupBy());
		assertEquals(locVo.getLocId(), updated.getLocation());
		
		this.validatePeriod(service, userData, ChartFilter.PERIOD_YESTERDAY,				this.formatter.parse("20241018"), this.formatter.parse("20241018")); 
		this.validatePeriod(service, userData, ChartFilter.PERIOD_BEFORE_YESTERDAY,			this.formatter.parse("20241018"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_30_DAYS,					this.formatter.parse("20240917"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_4_WEEKS,					this.formatter.parse("20240920"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_12_WEEKS,					this.formatter.parse("20240726"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_6_MONTH,					this.formatter.parse("20240419"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_12_MONTH,					this.formatter.parse("20231019"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_FILTER_WEEK,				this.formatter.parse("20241018"), this.formatter.parse("20241024"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_FILTER_WEEK_4,			this.formatter.parse("20240927"), this.formatter.parse("20241024"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_FILTER_MONTH,				this.formatter.parse("20241001"), this.formatter.parse("20241031"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_FILTER_MONTH_6,			this.formatter.parse("20240501"), this.formatter.parse("20241031"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_FILTER_YEAR,				this.formatter.parse("20240101"), this.formatter.parse("20241031"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_CURRENT_YEAR,				this.formatter.parse("20240101"), this.formatter.parse("20241018"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_CURRENT_YEAR_DELTA + 1,	this.formatter.parse("20230101"), this.formatter.parse("20231231"));
		this.validatePeriod(service, userData, ChartFilter.PERIOD_CURRENT_MONTH,			this.formatter.parse("20241001"), this.formatter.parse("20241018"));
//		this.validatePeriod(service, userData, ChartFilter.PERIOD_CURRENT_WEEK,				this.formatter.parse("20241014"), this.formatter.parse("20241018"));
	}
	
	public void validatePeriod(BaseServiceImpl service, UserData userData, String period, Date dateFromExpected, Date dateToExpected) {
		ChartFilter filter = new ChartFilter();
		filter.setPeriod(period);
		
		ChartFilter updated = service.validate(filter, userData);
		
		assertEquals(dateFromExpected, updated.getFrom());
		assertEquals(dateToExpected, updated.getTo());
	}
	
	@Test public void testExecute() throws Exception {
		Date now = this.formatter.parse("20241019");
		String statDefName = "testDefName";
		TestAbstractChart testChart = new TestAbstractChart();
		StatDefinitionVo staDefVo = new StatDefinitionVo();
		staDefVo.setStatDefExecutable(TestAbstractChart.class.getCanonicalName());

		when(this.staDefDao.findVo(statDefName)).thenReturn(staDefVo);
		when(this.chartFactory.get(staDefVo)).thenReturn(testChart);
		
		LocationVo locVo = new LocationVo();
		locVo.setLocId(Integer.valueOf(2));
		locVo.setLocDemoDate(now);

		UserData userData = new UserData();
		userData.setLocationVo(locVo);
		
		this.service.execute(statDefName, service.validate(null, userData), userData);
		
		assertTrue(testChart.executed);
	}
}

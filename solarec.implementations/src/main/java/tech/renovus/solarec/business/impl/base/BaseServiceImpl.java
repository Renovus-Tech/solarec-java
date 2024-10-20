package tech.renovus.solarec.business.impl.base;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.chart.ChartFactory;
import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliSettingDao;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StatDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public class BaseServiceImpl {

	//--- Resources -----------------------------
	protected @Resource StatDefinitionDao staDefDao;
	protected @Resource CliSettingDao cliSettingDao;
	protected @Resource ClientDao cliDao;
	protected @Resource LocationDao locDao;
	protected @Resource GeneratorDao genDao;
	protected @Resource GenDataDao genDataDao;
	protected @Resource StationDao staDao;
	protected @Resource StaDataDao staDataDao;
	
	protected @Autowired ChartFactory factory;
	protected @Autowired RenovusSolarecConfiguration config;

	//--- Util methods --------------------------
	public ChartFilter validate(ChartFilter filter, UserData userData) {
		if (filter == null) {
			filter = new ChartFilter();
		}
		
		Date dateToUser = this.calculateDateToUser(userData);
		
		Date currentDate = DateUtil.addUnit(dateToUser, Calendar.DAY_OF_MONTH, -1);
		currentDate = DateUtil.clearTime(currentDate);

		filter.setClient(userData.getCliId());
		if (filter.getLocation() != null && this.locDao.findForUser(userData.getCliId(), filter.getClient(), filter.getLocation()) == null) {
			filter.setLocation(null);
		} 
		
		if (filter.getTo() == null) {
			filter.setTo(currentDate);
		}
		if (StringUtil.isEmpty(filter.getPeriod()) && filter.getFrom() == null) {
			filter.setPeriod(ChartFilter.PERIOD_12_WEEKS);
		}
		if (StringUtil.isEmpty(filter.getGroupBy())) {
			filter.setGroupBy(ChartFilter.GROUP_BY_WEEK);
		}
		
		if (StringUtil.notEmpty(filter.getPeriod())) {
			filter.setTo(currentDate);
			Calendar aCalendar = null;
			
			int yearDelta = 0;
			if (filter.getPeriod().startsWith(ChartFilter.PERIOD_CURRENT_YEAR_DELTA)) {
				String period = filter.getPeriod();
				filter.setPeriod(ChartFilter.PERIOD_CURRENT_YEAR);
				
				try { yearDelta = Integer.parseInt(period.substring(ChartFilter.PERIOD_CURRENT_YEAR_DELTA.length())); } catch (NumberFormatException e) { /* do nothing */ } 
			}
				
			
			switch (filter.getPeriod()) {
				case ChartFilter.PERIOD_YESTERDAY:		filter.setFrom(filter.getTo()); break;
				case ChartFilter.PERIOD_30_DAYS:		filter.setFrom(DateUtil.addUnit(currentDate, Calendar.DAY_OF_MONTH, -31));	break;
				case ChartFilter.PERIOD_4_WEEKS:		filter.setFrom(DateUtil.addUnit(currentDate, Calendar.WEEK_OF_YEAR, -4));	break;
				case ChartFilter.PERIOD_12_WEEKS:		filter.setFrom(DateUtil.addUnit(currentDate, Calendar.WEEK_OF_YEAR, -12));	break;
				case ChartFilter.PERIOD_6_MONTH:		filter.setFrom(DateUtil.addUnit(DateUtil.addUnit(currentDate, Calendar.MONTH, -6), Calendar.DAY_OF_MONTH, 1));			break;
				case ChartFilter.PERIOD_12_MONTH:		filter.setFrom(DateUtil.addUnit(DateUtil.addUnit(currentDate, Calendar.YEAR, -1), Calendar.DAY_OF_MONTH, 1));			break;
				
				case ChartFilter.PERIOD_CURRENT_YEAR:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					aCalendar.set(Calendar.MONTH, Calendar.JANUARY);
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					aCalendar.add(Calendar.YEAR, - yearDelta);
					filter.setFrom(aCalendar.getTime());
					
					if (yearDelta > 0) {
						aCalendar.add(Calendar.YEAR, 1);
						aCalendar.add(Calendar.DAY_OF_MONTH, -1);
						filter.setTo(aCalendar.getTime());
						
						filter.setPeriod(ChartFilter.PERIOD_CURRENT_YEAR_DELTA + yearDelta);
					}
					
					break;
				
				case ChartFilter.PERIOD_CURRENT_MONTH:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					filter.setFrom(aCalendar.getTime());

					break;
				
				case ChartFilter.PERIOD_FILTER_WEEK:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					
					if (filter.getFrom() != null) {
						aCalendar.setTime(filter.getFrom());
					} else if (filter.getTo() != null) {
						aCalendar.setTime(filter.getTo());
					}
					
					filter.setFrom(aCalendar.getTime());
					
					aCalendar.add(Calendar.WEEK_OF_YEAR, 1);
					aCalendar.add(Calendar.DAY_OF_MONTH, -1);
					filter.setTo(aCalendar.getTime());
					
					break;
					
				case ChartFilter.PERIOD_FILTER_WEEK_4:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					
					if (filter.getFrom() != null) {
						aCalendar.setTime(filter.getFrom());
					} else if (filter.getTo() != null) {
						aCalendar.setTime(filter.getTo());
					}
					
					aCalendar.add(Calendar.WEEK_OF_YEAR, -3);
					filter.setFrom(aCalendar.getTime());
					
					aCalendar.add(Calendar.WEEK_OF_YEAR, 4);
					aCalendar.add(Calendar.DAY_OF_MONTH, -1);
					filter.setTo(aCalendar.getTime());
					
					break;
					
				case ChartFilter.PERIOD_FILTER_MONTH:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					
					if (filter.getFrom() != null) {
						aCalendar.setTime(filter.getFrom());
					} else if (filter.getTo() != null) {
						aCalendar.setTime(filter.getTo());
					}
					
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					filter.setFrom(aCalendar.getTime());
					
					aCalendar.add(Calendar.MONTH, 1);
					aCalendar.add(Calendar.DAY_OF_MONTH, -1);
					filter.setTo(aCalendar.getTime());

					if (filter.isForReport() && filter.getTo().after(currentDate)) {
						filter.setTo(currentDate);
					}
					
					break;

				case ChartFilter.PERIOD_FILTER_MONTH_6:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					
					if (filter.getFrom() != null) {
						aCalendar.setTime(filter.getFrom());
					} else if (filter.getTo() != null) {
						aCalendar.setTime(filter.getTo());
					}
					
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					aCalendar.add(Calendar.MONTH, 1);
					aCalendar.add(Calendar.DAY_OF_MONTH, -1);
					filter.setTo(aCalendar.getTime());
					
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					aCalendar.add(Calendar.MONTH, -5); //el to incluye el 6° mes
					filter.setFrom(aCalendar.getTime());
					
					if (filter.isForReport() && filter.getTo().after(currentDate)) {
						filter.setTo(currentDate);
					}
					
					break;
				
				case ChartFilter.PERIOD_FILTER_YEAR:
					aCalendar = Calendar.getInstance();
					aCalendar.setTime(currentDate);
					
					if (filter.getFrom() != null) {
						aCalendar.setTime(filter.getFrom());
					} else if (filter.getTo() != null) {
						aCalendar.setTime(filter.getTo());
					}
					
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					aCalendar.add(Calendar.MONTH, 1);
					aCalendar.add(Calendar.DAY_OF_MONTH, -1);
					filter.setTo(aCalendar.getTime());
					
					aCalendar.set(Calendar.DAY_OF_MONTH, 1);
					aCalendar.set(Calendar.MONTH, Calendar.JANUARY);
					filter.setFrom(aCalendar.getTime());
					
					if (filter.isForReport() && filter.getTo().after(currentDate)) {
						filter.setTo(currentDate);
					}
					
					break;

				case ChartFilter.PERIOD_CURRENT_WEEK:
					aCalendar = Calendar.getInstance();
					aCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					aCalendar.set(Calendar.HOUR,0);
					aCalendar.set(Calendar.MINUTE,0);
					aCalendar.set(Calendar.SECOND,0);
					aCalendar.set(Calendar.MILLISECOND,0);
					aCalendar.set(Calendar.AM_PM,Calendar.AM);

					filter.setFrom(aCalendar.getTime());
					break;
				
				default: filter.setFrom(currentDate);
			}
		} else if (filter.getTo() != null) {
			Date maxToDate = DateUtil.clearTime(new Date());
			Date toDate = DateUtil.clearTime(filter.getTo());
			if (toDate.equals(maxToDate) || toDate.after(maxToDate)) {
				filter.setTo(maxToDate);
			}
		}
		
		if (filter.getLocation() == null) {
			filter.setLocation(userData.getLocId());
		}
		
		return filter;
	}

	public Date calculateDateToUser(UserData userData) {
		Date dateToUse = null;

		if (userData.getLocationVo() != null) {
			dateToUse = userData.getLocationVo().getLocDemoDate();
		}
		if (dateToUse == null && userData.getClientVo() != null) {
			dateToUse = userData.getClientVo().getCliDemoDate();
		}
		if (dateToUse == null) {
			dateToUse = new Date();
		}
		return dateToUse;
	}

	public Object execute(String statDefName, ChartFilter filter, UserData userData) throws CoreException {
		StatDefinitionVo vo	= this.staDefDao.findVo(statDefName);
		if (vo == null) {
			return this.factory.generateChartResultErrorAsString(null, "can't find the requested chart information.", filter);
		}
		if (vo.getStatDefExecutable() == null) {
			return this.factory.generateChartResultErrorAsString(vo.getStatDefName(), "Chart has an invalid executable", filter);
		}
		
		AbstractChart chart	= this.factory.get(vo);
		chart.prepareForExecution(vo, filter, userData, this.config);
		
		Object result = chart.execute();
		
		return result;
	}
}

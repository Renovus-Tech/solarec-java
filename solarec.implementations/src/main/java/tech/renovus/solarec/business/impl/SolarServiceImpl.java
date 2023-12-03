package tech.renovus.solarec.business.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.UserData;
//import tech.renovus.solarec.business.AlarmService;
import tech.renovus.solarec.business.SolarService;
import tech.renovus.solarec.business.impl.base.BaseServiceImpl;
import tech.renovus.solarec.configuration.RenovusSolarConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.EmberCountryOverviewDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.comparator.GeneratorGenCodeAsNumberComparator;
import tech.renovus.solarec.vo.custom.chart.performanceIndex.Datum;
import tech.renovus.solarec.vo.custom.chart.performanceIndex.PerformanceIndex;
import tech.renovus.solarec.vo.custom.chart.revenue.Month;
import tech.renovus.solarec.vo.custom.chart.revenue.Revenue;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.EmberCountryOverviewVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@Service
public class SolarServiceImpl extends BaseServiceImpl implements SolarService {

	//--- Resources -----------------------------
	@Autowired RenovusSolarConfiguration config;
	@Autowired EmberCountryOverviewDao emberCountryDao;
	@Autowired GeneratorDao generatorDao;
	@Autowired CliLocAlertDao cliLocAlertDao;
	
	//--- Private methods -----------------------
	
	//--- Implemented methods -------------------
	@Override public Object runOverview(ChartFilter filter, UserData userData) throws CoreException				{ return this.execute(StatDefinitionVo.ID_SOLAR_OVERVIEW, filter, userData); }
	@Override public Object runClimate(ChartFilter filter, UserData userData) throws CoreException				{ return this.execute(StatDefinitionVo.ID_SOLAR_CLIMATE, filter, userData); }
	@Override public Object runPerformanceIndex(ChartFilter filter, UserData userData) throws CoreException		{ return this.execute(StatDefinitionVo.ID_SOLAR_PERFORMANCE_INDEX, filter, userData); }

	@Override public Object runOverviewAlerts(ChartFilter filter, UserData userData) throws CoreException		{
		if (filter == null) filter = new ChartFilter();
		filter = this.validate(filter, userData);
		
		Collection<CliLocAlertVo> alerts = this.cliLocAlertDao.retrieveFor(userData.getCliId(), userData.getLocId(), filter.getFrom(), filter.getTo());

		Collection<CliLocAlertVo> toMarkAsSeen = alerts.stream().filter(x -> ! FlagUtil.getFlagValue(x, CliLocAlertVo.FLAG_SEEN)).collect(Collectors.toList());
		if (CollectionUtil.notEmpty(toMarkAsSeen)) {
			toMarkAsSeen.forEach(x -> {
				x.setSyncType(CliLocAlertVo.SYNC_UPDATE);
				FlagUtil.setFlagValue(x, CliLocAlertVo.FLAG_SEEN, true);
			});
			
			this.cliLocAlertDao.synchronize(toMarkAsSeen);
		}
		
		return alerts;
	}
	
	@Override public Revenue revenue(ChartFilter filter, UserData userData) {
		if (filter == null) filter = new ChartFilter();
		
		Collection<GeneratorVo> generators = new TreeSet<>(GeneratorGenCodeAsNumberComparator.getInstance());
		CollectionUtil.addAll(generators, this.generatorDao.findAll(userData.getCliId(), filter.getLocation()));
		filter.setGenerators(generators.stream().map(GeneratorVo::getGenId).collect(Collectors.toList()));
		
//		Calendar cal = GregorianCalendar.getInstance();
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		cal.add(Calendar.DAY_OF_MONTH, -1);
//		filter.setTo(cal.getTime());
//		
//		cal.add(Calendar.YEAR, -1);
//		cal.add(Calendar.DAY_OF_MONTH, 1);
//		filter.setFrom(cal.getTime());
		
		filter.setStations(null);
		filter.setGroupBy(ChartFilter.GROUP_BY_MONTH);
		filter.setForReport(true);
		
		if (StringUtil.isEmpty(filter.getPeriod())) filter.setPeriod(ChartFilter.PERIOD_CURRENT_YEAR);

		filter = this.validate(filter, userData);
		
		try {
			String callPerformance				= (String) this.runPerformanceIndex(filter, userData);
			PerformanceIndex chartPerformance	= JsonUtil.toObject(callPerformance, PerformanceIndex.class);
			Revenue result = new Revenue();
			
			CliSettingVo cliSettingVo = this.cliSettingDao.findVoOrDefault(userData.getCliId(), CliSettingVo.D_RECS_SOLD_PORCENTAGE, CliSettingVo.DEFAULT_VALUE_D_RECS_SOLD_PORCENTAGE);
			
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(filter.getFrom());
			Integer year = Integer.valueOf(cal.get(Calendar.YEAR));
			
			EmberCountryOverviewVo vo = this.emberCountryDao.findFirstFrom("Uruguay", year);
			if (vo == null) vo = this.emberCountryDao.findLastFrom("Uruguay", year);
			double emissionsIntensityGco2PerMwh = (vo.getEmissionsIntensityGco2PerKwh() == null ? 0 : vo.getEmissionsIntensityGco2PerKwh().doubleValue()) * 1000;
			double soldPorcentaje = cliSettingVo.doubleValue() / (double) 100;

			SimpleDateFormat jsonDate = new SimpleDateFormat(DateUtil.FMT_JSON_CHART);
			SimpleDateFormat chartDate = new SimpleDateFormat("MMMM yyyy");
			
			for (Datum data : chartPerformance.getData()) {
				String dateFrom = data.getFrom();
				try { dateFrom = chartDate.format(jsonDate.parse(dateFrom)); } catch (Exception e) { /* do nothing */ }
				Double dRecGenerated = data.getTotalACProductionMwh();
				Double dRecSold = Double.valueOf(dRecGenerated.doubleValue() * soldPorcentaje);
				Double coAvoided = Double.valueOf(dRecGenerated.doubleValue() * emissionsIntensityGco2PerMwh);
				
				result.add(new Month()
					.withLabel(dateFrom)
					.withdRecGenerated(dRecGenerated)
					.withdRecSold(dRecSold)
					.withCoAvoided(coAvoided)
				);
			}
			
			return result;
			
		} catch (CoreException | JsonProcessingException e) {
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	@Override public Revenue revenueSales(ChartFilter filter, UserData userData) {
		Revenue result = this.revenue(filter, userData);

		CliSettingVo cliSettingVo = this.cliSettingDao.findVoOrDefault(userData.getCliId(), CliSettingVo.D_RECS_PRICE, CliSettingVo.DEFAULT_VALUE_D_RECS_PRICE);
		double price = cliSettingVo.doubleValue();
		
		if (CollectionUtil.notEmpty(result.getMonths())) {
			for (Month month : result.getMonths()) {
				if (month.getdRecGenerated() == null) continue;
				month.setdRecPrice(Double.valueOf(month.getdRecGenerated().doubleValue() * price));
				month.setdRecIncome(Double.valueOf(month.getdRecSold().doubleValue() * price));
			}
		}
		
		return result;
	}

}

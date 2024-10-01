package tech.renovus.solarec.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ParserService;
import tech.renovus.solarec.business.SolarService;
import tech.renovus.solarec.business.impl.base.BaseServiceImpl;
import tech.renovus.solarec.db.data.dao.interfaces.CliGenAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.CliLocAlertDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;
import tech.renovus.solarec.vo.rest.entity.Alert;

@Service
public class SolarServiceImpl extends BaseServiceImpl implements SolarService {

	//--- Resources -----------------------------
	@Autowired ParserService parserService;
	
	@Autowired GeneratorDao generatorDao;
	@Autowired CliLocAlertDao cliLocAlertDao;
	@Autowired CliGenAlertDao cliGenAlertDao;
	
	//--- Private methods -----------------------
	private Collection<Alert> retrieveLocationAlerts(ChartFilter filter, UserData userData) {
		Collection<CliLocAlertVo> alerts = this.cliLocAlertDao.retrieveFor(userData.getCliId(), userData.getLocId(), filter.getFrom(), filter.getTo());
		Collection<Alert> result = new ArrayList<>(CollectionUtil.size(alerts));
		
		if (CollectionUtil.notEmpty(alerts)) {
			alerts.forEach(cliLocAlertVo -> {
				
				Alert alert = new Alert()
						.withDate(cliLocAlertVo.getCliLocAlertTrigger())
						.withFirstView(! FlagUtil.getFlagValue(cliLocAlertVo, CliLocAlertVo.FLAG_SEEN))
						.withMessage(this.parserService.parseAlert(cliLocAlertVo, userData))
						.withType(cliLocAlertVo.getCliLocAlertType())
					;
				
				result.add(alert);
			});
			
			Collection<CliLocAlertVo> toMarkAsSeen = alerts.stream().filter(x -> ! FlagUtil.getFlagValue(x, CliLocAlertVo.FLAG_SEEN)).collect(Collectors.toList());
			if (CollectionUtil.notEmpty(toMarkAsSeen)) {
				toMarkAsSeen.forEach(x -> {
					x.setSyncType(CliLocAlertVo.SYNC_UPDATE);
					FlagUtil.setFlagValue(x, CliLocAlertVo.FLAG_SEEN, true);
				});
				
				this.cliLocAlertDao.synchronize(toMarkAsSeen);
			}
		}
		
		
		return result;
	}
	
	private Collection<Alert> retrieveGeneratorsAlerts(ChartFilter filter, UserData userData) {
		Collection<CliGenAlertVo> alerts = this.cliGenAlertDao.retrieveFor(userData.getCliId(), userData.getLocId(), filter.getFrom(), filter.getTo());
		Collection<Alert> result = new ArrayList<>(CollectionUtil.size(alerts));

		if (CollectionUtil.notEmpty(alerts)) {
			alerts.forEach(x -> {
				
				Alert alert = new Alert()
						.withDate(x.getCliGenAlertTrigger())
						.withFirstView(! FlagUtil.getFlagValue(x, CliGenAlertVo.FLAG_SEEN))
						.withMessage(this.parserService.parseAlert(x, userData))
						.withType(x.getCliGenAlertType())
					;
				
				result.add(alert);
			});
			
			Collection<CliGenAlertVo> toMarkAsSeen = alerts.stream().filter(x -> ! FlagUtil.getFlagValue(x, CliGenAlertVo.FLAG_SEEN)).collect(Collectors.toList());
			if (CollectionUtil.notEmpty(toMarkAsSeen)) {
				toMarkAsSeen.forEach(x -> {
					x.setSyncType(CliGenAlertVo.SYNC_UPDATE);
					FlagUtil.setFlagValue(x, CliGenAlertVo.FLAG_SEEN, true);
				});
				
				this.cliGenAlertDao.synchronize(toMarkAsSeen);
			}
		}
		
		return result;
	}
	
	//--- Implemented methods -------------------
	@Override public Object runOverview(ChartFilter filter, UserData userData) throws CoreException				{ return this.execute(StatDefinitionVo.ID_SOLAR_OVERVIEW, filter, userData); }
	@Override public Object runClimate(ChartFilter filter, UserData userData) throws CoreException				{ return this.execute(StatDefinitionVo.ID_SOLAR_CLIMATE, filter, userData); }
	@Override public Object runPerformanceIndex(ChartFilter filter, UserData userData) throws CoreException		{ return this.execute(StatDefinitionVo.ID_SOLAR_PERFORMANCE_INDEX, filter, userData); }
	@Override public Object retrieveOverviewCo2(ChartFilter filter, UserData userData) throws CoreException 	{ return this.execute(StatDefinitionVo.ID_SOLAR_OVERVIEW_CO2, filter, userData); }
	@Override public Object revenue(ChartFilter filter, UserData userData) throws CoreException 				{ return this.execute(StatDefinitionVo.ID_SOLAR_REVENUE, filter, userData); }
	@Override public Object revenueSales(ChartFilter filter, UserData userData) throws CoreException			{ return this.execute(StatDefinitionVo.ID_SOLAR_SALES, filter, userData); }

	@Override public Object retrieveOverviewAlerts(ChartFilter filter, UserData userData) throws CoreException		{
		if (filter == null) {
			filter = new ChartFilter(ChartFilter.PERIOD_YESTERDAY);
		} else {
			filter.setPeriod(ChartFilter.PERIOD_YESTERDAY);
		}
		filter = this.validate(filter, userData);
		
		Collection<Alert> alerts = new ArrayList<>();
		CollectionUtil.addAll(alerts, this.retrieveLocationAlerts(filter.createCopy(), userData));
		CollectionUtil.addAll(alerts, this.retrieveGeneratorsAlerts(filter.createCopy(), userData));
		
		return alerts;
	}
}

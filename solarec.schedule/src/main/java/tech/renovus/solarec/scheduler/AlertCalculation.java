package tech.renovus.solarec.scheduler;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.business.impl.chart.base.Chart;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.custom.chart.alerts.AlertResponse;
import tech.renovus.solarec.vo.db.data.LocationVo;

@Component
public class AlertCalculation {

	//--- Resources -----------------------------
	@Autowired RenovusSolarecConfiguration config;
	@Resource LocationDao locationDao;
	
	//--- Private properties --------------------
	private Logger logger = LoggerService.schedulerLogger();
	
	//--- Private methods -----------------------
	private void calculateFor(LocationVo locVo) throws CoreException {
		Chart data = new Chart();
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(locVo.getLocDataDateMax());
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		cal.set(Calendar.AM_PM,Calendar.AM);
		
		data.setClient(locVo.getCliId());
		data.setLocation(locVo.getLocId());
		data.setTo(cal.getTime());
		
		
		cal.add(Calendar.DAY_OF_YEAR, -1);
		data.setFrom(cal.getTime());

		try {
			String jsonRequest = JsonUtil.toString(data);
			this.logger.info("Calculation for: " + jsonRequest);
			
			String jsonResponse			= JsonUtil.post(this.config.getAlertCalculations(), jsonRequest);
			this.logger.info("Response: " + jsonResponse);
			
//			AlertResponse alertReponse	= JsonUtil.toObject(jsonResponse, AlertResponse.class);
		} catch (JsonProcessingException e) {
			this.logger.error("Error: {}", e.getLocalizedMessage(), e);
			throw new CoreException(e);
		}
	}
	
	//--- Schedulers ----------------------------
//	@Scheduled(cron="0 0 03 * * *") // all days at 1am
	public void calculate() {
		Collection<LocationVo> locations = this.locationDao.getAllForAlertCalculation();
		
		if (CollectionUtil.notEmpty(locations)) {
			for (LocationVo locVo : locations) {
				try {
					this.calculateFor(locVo);
				} catch (CoreException e) {
					/** on error continue with others **/
				}
			}
		}
	}
}

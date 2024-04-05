package tech.renovus.solarec.business.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.ParserService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.custom.chart.alerts.AlertTrigger;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

@Service
public class ParserServiceImpl implements ParserService {

	//--- Private constants ---------------------
	
	//--- Resources -----------------------------
	@Autowired private TranslationService translation;

	//--- Overridden methods --------------------
	@Override public String parseAlert(CliGenAlertVo aVo, UserData userData) {
		Locale locale = this.translation.getLocale(userData);
		AlertTrigger vo = null;
		try {
			vo = JsonUtil.toObject(aVo.getCliGenAlertData(), AlertTrigger.class);
			
			return this.translation.forAlert(
					locale, 
					"generator." + vo.getType(),
					aVo.getCliName(), // Client name
					aVo.getLocName(), // Location name
					aVo.getLocCode(), // Location code
					aVo.getGenName(), // Generator name
					aVo.getGenCode(), // Generator code
					vo.getDate(), // Date
					vo.getValue(), // Value
					vo.getPreviousValue(), // Previous value
					vo.getThreshold(), // Threshold
					vo.getDiffPercentage() // Difference in percentage
				); 
		} catch (JsonProcessingException e) {
			return this.translation.forLabel(locale, TranslationService.ERROR_PARSING, new Object[] {e.getLocalizedMessage()});
		} catch (NoSuchMessageException e) {
			LoggerService.rootLogger().error("Error found during translation cli_gen_alert: " + aVo.getCliGenAlertId(), e);
			return vo.getDescription();
		}
	}
	
	@Override public String parseAlert(CliLocAlertVo aVo, UserData userData) {
		Locale locale = this.translation.getLocale(userData);
		try {
			AlertTrigger vo = JsonUtil.toObject(aVo.getCliLocAlertData(), AlertTrigger.class);
			
			return this.translation.forAlert(
					locale, 
					"location." + vo.getType(),
					aVo.getCliName(), // Client name
					aVo.getLocName(), // Location name
					aVo.getLocCode(), // Location code
					vo.getDate(), // Date
					vo.getValue(), // Value
					vo.getPreviousValue(), // Previous value
					vo.getThreshold(), // Threshold
					vo.getDiffPercentage() // Difference in percentage
				); 
		} catch (JsonProcessingException e) {
			return this.translation.forLabel(locale, TranslationService.ERROR_PARSING, new Object[] {e.getLocalizedMessage()});
		}
	}
}

package tech.renovus.solarec.business;

import java.util.Locale;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

public interface ParserService {

	String parseAlert(CliLocAlertVo vo, UserData userData);
	String parseAlert(CliGenAlertVo vo, UserData userData);
	String parseAlert(CliGenAlertVo vo, Locale locale);
	String parseAlert(CliLocAlertVo vo, Locale locale);
}

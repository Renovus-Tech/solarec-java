package tech.renovus.solarec.business.impl;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.TranslationService;

@Service
public class TranslationServiceImpl implements TranslationService {

	//--- Private constants ---------------------
	private static final String PREFIX_SETTING			= "setting.";
	private static final String PREFIX_SETTING_CATEGORY	= "setting.category.";
	
	//--- Private properties --------------------
	@Resource private MessageSource messageSource;
	
	//--- Implemented methods -------------------
	@Override public Locale getLocale(UserData userData) {
		return userData == null || userData.getLocale() == null ? Locale.ENGLISH : userData.getLocale();
	}
	
	@Override public String forLabel(Locale locale, String labe, Object[] params)		{ return this.messageSource.getMessage(labe, params, locale); }
	@Override public String forSetting(Locale locale, String setting)					{ return this.forLabel(locale, PREFIX_SETTING + setting, null); }
	@Override public String forSettingCategory(Locale locale, String settingCategory)	{ return this.forLabel(locale, PREFIX_SETTING_CATEGORY + settingCategory, null); }

	@Override
	public String forAlert(Locale locale, String alert, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

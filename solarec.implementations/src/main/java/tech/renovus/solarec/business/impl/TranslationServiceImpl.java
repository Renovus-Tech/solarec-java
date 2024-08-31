package tech.renovus.solarec.business.impl;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;

@Service
public class TranslationServiceImpl implements TranslationService {

	//--- Private constants ---------------------
	private static final String PREFIX_ALERT			= "alert.";
	private static final String PREFIX_SETTING			= "setting.";
	private static final String PREFIX_SETTING_CATEGORY	= "setting.category.";
	private static final String PREFIX_EMAIL_TEMPLATE	= "email_template.";
	
	//--- Private properties --------------------
	@Autowired private TemplateEngine templateEngine;
	@Resource private MessageSource messageSource;
	
	private Locale defaultLocale;
	
	//--- Constructors --------------------------
	public TranslationServiceImpl() {
		this.defaultLocale = Locale.ENGLISH;
	}
	
	public TranslationServiceImpl(@Autowired RenovusSolarecConfiguration configuration) {
		this.defaultLocale = new Locale(configuration.getSiteLocale());
	}
	
	//--- Implemented methods -------------------
	@Override public Locale getLocale(String language) {
		return StringUtil.isEmpty(language) ? Locale.ENGLISH : new Locale(language);
	}
	
	@Override public Locale getLocale(UserData userData) {
		return userData == null || userData.getLocale() == null ? Locale.ENGLISH : userData.getLocale();
	}
	
	@Override
	public String forLabel(Locale locale, String label, Object... params) {
		try {
			return this.messageSource.getMessage(label, params, locale);
		} catch (NoSuchMessageException e) {
			return this.messageSource.getMessage(label, params, this.defaultLocale);
		}
	}

	@Override
	public String forSetting(Locale locale, String setting) {
		return this.forLabel(locale, PREFIX_SETTING + setting);
	}

	@Override
	public String forSettingCategory(Locale locale, String settingCategory) {
		return this.forLabel(locale, PREFIX_SETTING_CATEGORY + settingCategory);
	}

	@Override
	public String forAlert(Locale locale, String alertType, Object... params) {
		return this.forLabel(locale, PREFIX_ALERT + alertType, params);
	}

	@Override public String forTemplate(Locale locale, String templateName, Map<String, Object> variables) {
		String template = null;
		try {
			template = this.messageSource.getMessage(PREFIX_EMAIL_TEMPLATE + templateName, null, locale);
		} catch (NoSuchMessageException e) {
			locale = this.defaultLocale;
			template = this.messageSource.getMessage(PREFIX_EMAIL_TEMPLATE + templateName, null, locale);
		}
		Context context = new Context(locale);
		if (CollectionUtil.notEmpty(variables)) {
			variables.entrySet().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));
		}
		return this.templateEngine.process(template, context);
	}
}

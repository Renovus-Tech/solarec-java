package tech.renovus.solarec.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.TranslationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TranslationServiceTest {

	@Mock private MessageSource messageSource;
	
	@InjectMocks TranslationServiceImpl service = new TranslationServiceImpl();
	
	@Test 
	public void testTranslation() {
		String[] params = new String[] {"param"};
		
		when(messageSource.getMessage("test.label", params, Locale.ENGLISH)).thenReturn("Test label no language EN param");
		when(messageSource.getMessage("setting.set", new String[0], Locale.ENGLISH)).thenReturn("Setting");
		when(messageSource.getMessage("setting.category.cat", new String[0], Locale.ENGLISH)).thenReturn("Category");
		when(messageSource.getMessage("alert.type", params, Locale.ENGLISH)).thenReturn("Type param");
		
		assertEquals(Locale.ENGLISH, service.getLocale((String) null));
		assertEquals(Locale.ENGLISH, service.getLocale((UserData) null));
		
		Locale en = service.getLocale("en");
		Locale es = service.getLocale("es");
		
		assertNotNull(en);
		assertNotNull(es);
		
		assertEquals("Test label no language EN param", service.forLabel(en, "test.label", "param"));
		assertEquals("Setting", service.forSetting(en, "set"));
		assertEquals("Category", service.forSettingCategory(en, "cat"));
		assertEquals("Type param", service.forAlert(en, "type", "param"));
	}
}

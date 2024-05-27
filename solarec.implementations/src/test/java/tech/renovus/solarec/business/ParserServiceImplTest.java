package tech.renovus.solarec.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.ParserServiceImpl;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.CliGenAlertVo;

@RunWith(MockitoJUnitRunner.class)
public class ParserServiceImplTest {

	@Mock private TranslationService translationService;
	
	@InjectMocks ParserServiceImpl service = new ParserServiceImpl();
	
	private CliGenAlertVo createGenAlertVo() {
		CliGenAlertVo vo = new CliGenAlertVo();
		
		vo.setCliGenAlertData("{\"type\": \"alertPerformanceRatioLow\", \"gen_code\": \"1\", \"description:\": \"Performance ratio on 2022-09-09 for generator 1 was 56.13%, which is 33.24% lower than the previous day (84.08%)\", \"value\": 56.13432315371699, \"previous_value\": 84.0792077271813, \"threshold\": 6, \"date\": \"2022-09-09\", \"diff_percentage\": 33.23637951506319}");
		
		return vo;
	}
	
	@Test 
	public void testParser() {
		Locale en = Locale.ENGLISH;
		
		when(translationService.getLocale((UserData) null)).thenReturn(en);
		when(translationService.forAlert(any(), anyString(), any())).thenAnswer((Answer<String>) invocation -> StringUtil.join("-", invocation.getArguments()) );
		
		
		CliGenAlertVo genAlert = this.createGenAlertVo();
//		CliLocAlertVo locAlert = this.createLocAlertVo();
		
		assertEquals("en-generator.alertPerformanceRatioLow-null-null-null-null-null-2022-09-09-56.13432315371699-84.0792077271813-6-33.23637951506319", service.parseAlert(genAlert, en));
		assertEquals("en-generator.alertPerformanceRatioLow-null-null-null-null-null-2022-09-09-56.13432315371699-84.0792077271813-6-33.23637951506319", service.parseAlert(genAlert, (UserData) null));
//		service.parseAlert(locAlert, en);
		
	}
}

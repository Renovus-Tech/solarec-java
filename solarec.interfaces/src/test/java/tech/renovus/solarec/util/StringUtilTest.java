package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tech.renovus.solarec.util.StringUtil;

public class StringUtilTest {

	@Test  public void replaceTesting() {
		String sampleText = "In stet et ipsum et sit feugiat duis consetetur at amet sed facilisis justo. Voluptua dolor takimata nibh nulla dolores sit.";
		String sampleHtml = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Text Conversion Test</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>Text Conversion Test</h1>\r\n"
				+ "    <p>This is a simple text conversion test.</p>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
		Map<String,String> params	= new HashMap<>();
		params.put("ipsum", "TEST");
		params.put("sed", "AND");
		
		
		String expectedText1 = "In stet et TEST et sit feugiat duis consetetur at amet sed facilisis justo. Voluptua dolor takimata nibh nulla dolores sit.";
		String expectedText2 = "In stet et TEST et sit feugiat duis consetetur at amet AND facilisis justo. Voluptua dolor takimata nibh nulla dolores sit.";
		String expectedText3 = "In st3t 3t ipsum 3t sit f3ugiat duis cons3t3tur at am3t s3d facilisis justo. Voluptua dolor takimata nibh nulla dolor3s sit.";
		
		String expectedHtml1 = "<!DOCTYPE&nbsp;html>\r\n"
				+ "<html&nbsp;lang=&quot;en&quot;>\r\n"
				+ "<head>\r\n"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;<meta&nbsp;charset=&quot;UTF-8&quot;>\r\n"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;<meta&nbsp;name=&quot;viewport&quot;&nbsp;content=&quot;width=device-width,&nbsp;initial-scale=1.0&quot;>\r\n"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;<title>Text&nbsp;Conversion&nbsp;Test</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;<h1>Text&nbsp;Conversion&nbsp;Test</h1>\r\n"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;<p>This&nbsp;is&nbsp;a&nbsp;simple&nbsp;text&nbsp;conversion&nbsp;test.</p>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
		
		String expectedHtml2 = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Text Conversion Test</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>Text Conversion Test</h1>\r\n"
				+ "    <p>This is a simple text conversion test.</p>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
		
		assertEquals(expectedText1, StringUtil.replace(sampleText, "ipsum", "TEST"));
		assertEquals(expectedText2, StringUtil.replaceAll(sampleText, params));
		assertEquals(expectedText3, StringUtil.replaceAll(sampleText, "e", "3"));
		assertEquals(expectedHtml2, StringUtil.replaceHtmlAcents(sampleHtml));
		assertEquals(expectedHtml1, StringUtil.replaceHtmlAcents(sampleHtml, false));
		assertEquals(expectedHtml2, StringUtil.replaceHtmlAcents(sampleHtml, true));
	}
	
	@Test public void emptyTest() {
		assertTrue(StringUtil.isEmpty((String) null));
		assertTrue(StringUtil.isEmpty(""));
		assertTrue(StringUtil.isEmpty(null, null));
		assertTrue(StringUtil.isEmpty(null, null));
		assertTrue(StringUtil.isEmpty("", null));
		assertTrue(StringUtil.isEmpty(null, ""));
		assertTrue(StringUtil.isEmptyTrim(null));
		assertTrue(StringUtil.isEmptyTrim(""));
		assertTrue(StringUtil.isEmptyTrim(" "));
		
		
		assertFalse(StringUtil.isEmpty("a dsa dads"));
		assertTrue(StringUtil.isEmpty("asdads", null));
		assertTrue(StringUtil.isEmpty(null, "asdasd"));
		assertFalse(StringUtil.isEmpty("asdada", "asdasd"));
		assertFalse(StringUtil.isEmptyTrim(" asdada "));
	}
	
	@Test public void convertTest() {
		String sampleXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<data>\r\n"
				+ "    <person>\r\n"
				+ "        <name>John Doe</name>\r\n"
				+ "        <age>30</age>\r\n"
				+ "        <gender>Male</gender>\r\n"
				+ "    </person>\r\n"
				+ "    <person>\r\n"
				+ "        <name>Jane Smith</name>\r\n"
				+ "        <age>25</age>\r\n"
				+ "        <gender>Female</gender>\r\n"
				+ "    </person>\r\n"
				+ "</data>\r\n";
		
		String expectedXml = "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;\r\n"
				+ "&lt;data&gt;\r\n"
				+ "    &lt;person&gt;\r\n"
				+ "        &lt;name&gt;John Doe&lt;/name&gt;\r\n"
				+ "        &lt;age&gt;30&lt;/age&gt;\r\n"
				+ "        &lt;gender&gt;Male&lt;/gender&gt;\r\n"
				+ "    &lt;/person&gt;\r\n"
				+ "    &lt;person&gt;\r\n"
				+ "        &lt;name&gt;Jane Smith&lt;/name&gt;\r\n"
				+ "        &lt;age&gt;25&lt;/age&gt;\r\n"
				+ "        &lt;gender&gt;Female&lt;/gender&gt;\r\n"
				+ "    &lt;/person&gt;\r\n"
				+ "&lt;/data&gt;\r\n";
		
		String expectedXml2 = "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;\r\n"
				+ "&lt;data&gt;\r\n"
				+ "    &lt;person&gt;\r\n"
				+ "        &lt;name&gt;John Doe&lt;/name&gt;\r\n"
				+ "        &lt;age&gt;30&lt;/age&gt;\r\n"
				+ "        &lt;gender&gt;Male&lt;/gender&gt;\r\n"
				+ "    &lt;/person&gt;\r\n"
				+ "    &lt;person&gt;\r\n"
				+ "        &lt;name&gt;Jane Smith&lt;/name&gt;\r\n"
				+ "        &lt;age&gt;25&lt;/age&gt;\r\n"
				+ "        &lt;gender&gt;Female&lt;/gender&gt;\r\n"
				+ "    &lt;/person&gt;\r\n"
				+ "&lt;/data&gt;\r\n";
		
		assertEquals(expectedXml, StringUtil.toXml(sampleXml));
		assertEquals(expectedXml2, StringUtil.fromXml(sampleXml));
	}
}

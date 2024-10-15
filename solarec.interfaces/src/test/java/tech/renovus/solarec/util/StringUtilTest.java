package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class StringUtilTest {

	@Test  public void replaceTesting() {
		String sampleText = "In stet et ipsum et sit feugiat duis consetetur at amet sed facilisis justo. Voluptua dolor takimata nibh nulla dolores sit.";
		Map<String,String> params	= new HashMap<>();
		params.put("ipsum", "TEST");
		params.put("sed", "AND");
		
		
		String expectedText1 = "In stet et TEST et sit feugiat duis consetetur at amet sed facilisis justo. Voluptua dolor takimata nibh nulla dolores sit.";
		String expectedText2 = "In stet et TEST et sit feugiat duis consetetur at amet AND facilisis justo. Voluptua dolor takimata nibh nulla dolores sit.";
		String expectedText3 = "In st3t 3t ipsum 3t sit f3ugiat duis cons3t3tur at am3t s3d facilisis justo. Voluptua dolor takimata nibh nulla dolor3s sit.";
		
		assertEquals(expectedText1, StringUtil.replace(sampleText, "ipsum", "TEST"));
		assertEquals(expectedText2, StringUtil.replaceAll(sampleText, params));
		assertEquals(expectedText3, StringUtil.replaceAll(sampleText, "e", "3"));
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
		
		assertTrue(StringUtil.notEmpty("sdad", "asdad"));
		assertFalse(StringUtil.notEmpty("", "asdad"));
		assertFalse(StringUtil.notEmpty("sdad", null));
	}
	
	@Test public void toStringTest() {
		assertEquals(Integer.toString(1), StringUtil.toString(1));
		assertEquals(Integer.valueOf(221).toString(), StringUtil.toString(Integer.valueOf(221)));
		assertEquals("if empty", StringUtil.toString(null, "if empty"));
		assertEquals("a value", StringUtil.toString("a value", "if empty"));
		assertEquals("", StringUtil.noNull(null));
		assertEquals("asd", StringUtil.noNull("asd"));
	}
	
	@Test public void numberTest() {
		assertEquals(Integer.valueOf(2), StringUtil.toInteger("2"));
		assertEquals(Integer.valueOf(2), StringUtil.toInteger(null, Integer.valueOf(2)));
		assertEquals(Integer.valueOf(3), StringUtil.toInteger("3", Integer.valueOf(2)));
		assertNull(StringUtil.toInteger(null));
		
		assertEquals(-1, StringUtil.firstNumberPosition("asdadasd"));
		assertEquals(-1, StringUtil.firstNumberPosition(""));
		assertEquals(-1, StringUtil.firstNumberPosition(null));
		assertEquals(0, StringUtil.firstNumberPosition("1asdadadad"));
		assertEquals(0, StringUtil.firstNumberPosition("1asdadadad2"));
		assertEquals("asdadadad1".length()-1, StringUtil.firstNumberPosition("asdadadad1"));
		assertEquals(0, StringUtil.lastNumberPosition("1asdadadad"));
		assertEquals("1asdadadad2".length()-1, StringUtil.lastNumberPosition("1asdadadad2"));
	}
	
	@Test public void xmlTest() {
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
	
	@Test public void htmlTest() {
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
		
		String texts = new StringBuilder()
							.append("¥")
							.append("¦")
							.append("§")
							.append("¨")
							.append("©")
							.append("ª")
							.append("«")
							.append("¬")
							.append("­")
							.append("®")
							.append("¯")
							.toString();
		
		String htmlCodes = new StringBuilder()
							.append("&#165;")
							.append("&#166;")
							.append("&#167;")
							.append("&#168;")
							.append("&#169;")
							.append("&#170;")
							.append("&#171;")
							.append("&#172;")
							.append("&#173;")
							.append("&#174;")
							.append("&#175;")
							.toString();
		
		
		String htmlValues = new StringBuilder()
								.append("&Ccedil;")
								.append("&atilde;")
								.append("&ccedil;")
								.append("&ntilde;")
								.append("&Yacute;")
								.append("&otilde;")
								.append("&yacute;")
								.append("&Oslash;")
								.toString();
		
		assertEquals("ÇãçñÝõýØ", StringUtil.addHtmlAcents(htmlValues, false));
		assertEquals(texts, StringUtil.addHtmlAcents(htmlCodes, true));
		assertEquals(texts, StringUtil.addHtmlAcents(htmlCodes));
		assertEquals(expectedHtml2, StringUtil.replaceHtmlAcents(sampleHtml));
		assertEquals(expectedHtml1, StringUtil.replaceHtmlAcents(sampleHtml, false));
		assertEquals(expectedHtml2, StringUtil.replaceHtmlAcents(sampleHtml, true));
	}
	
	@Test public void parseTest() {
		String msg1 = "a <TOK1> c <TOK2> e";
		String msg2 = "a <TOK2> c <TOK1> e";
		List<String> values = Arrays.asList("b", "d");
		
		assertEquals(msg1, StringUtil.parseMessage(msg1, null));
		assertEquals("a b c d e", StringUtil.parseMessage(msg1, values));
		assertEquals("a d c b e", StringUtil.parseMessage(msg2, values));

		String[] src = new String[] {"a", "b"};
		String[] dest = new String[] {"a", "b"};
		String[] cloned = StringUtil.clone(src);
		
		assertEquals(dest.length, cloned.length);
		assertNotEquals(dest, cloned);
		
		for (int i = 0; i < dest.length; i++) {
			assertEquals(dest[i], cloned[i]);
		}
		
		assertEquals("a,b,c", StringUtil.join(",", Arrays.asList("a","b","c")));
		assertEquals("a,0,c", StringUtil.join(",", Arrays.asList((Object) "a", (Object) Integer.valueOf(0),(Object) "c")));
		assertEquals("a,null,c", StringUtil.join(",", Arrays.asList("a",null,"c")));
		assertEquals("", StringUtil.join(",", new ArrayList<String>()));
		assertEquals("a,b,c", StringUtil.joinNotNull(",", "a","b","c"));
		assertEquals("a,c", StringUtil.joinNotNull(",", "a",null,"c"));
	}
}


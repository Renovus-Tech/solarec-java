package tech.renovus.solarec.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import tech.renovus.solarec.connection.api.GetResponse;
import tech.renovus.solarec.connection.api.OnlyProperties;
import tech.renovus.solarec.connection.api.PostResponse;
import tech.renovus.solarec.connection.api.TestRequest;

public class JsonCallerTest {
	
	//--- Private constants ---------------------
	private static final String ENDPOIT_STATUS	= "http://postman-echo.com/status/";
	private static final String ENDPOIT_POST	= "http://postman-echo.com/post";
	private static final String ENDPOIT_GET		= "http://postman-echo.com/get";
	
	//--- Private properties --------------------
	private JsonCaller caller = new JsonCaller();
	
	//--- Private methods -----------------------
	private void assertParameters(Map<String, String> params, OnlyProperties response) {
		for (Map.Entry<String, String > entry : params.entrySet()) {
			assertTrue(response.getAdditionalProperties().containsKey(entry.getKey()));
			try {
				assertEquals(response.getAdditionalProperties().get(entry.getKey()), URLEncoder.encode(entry.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				assertNotNull(e);
			}
		}
	}
	
	private void assertHeaders(Map<String, String> headers, GetResponse response) {
		for (Map.Entry<String, String > entry : headers.entrySet()) {
			assertTrue(response.getHeaders().getAdditionalProperties().containsKey(entry.getKey()));
			assertEquals(response.getHeaders().getAdditionalProperties().get(entry.getKey()), entry.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void assertTestRequest(TestRequest request, PostResponse response) {
		assertEquals(request.getText(), ((Map<String, Object>) response.getAdditionalProperties().get("data")).get("text"));
		assertEquals(request.getNumber(), ((Map<String, Object>) response.getAdditionalProperties().get("data")).get("number"));
		assertEquals(request.getDecimal(), ((Map<String, Object>) response.getAdditionalProperties().get("data")).get("decimal"));
		assertEquals(request.getBoolean(), ((Map<String, Object>) response.getAdditionalProperties().get("data")).get("boolean"));
		assertEquals(request.getDate(), ((Map<String, Object>) response.getAdditionalProperties().get("data")).get("date"));
	}

	///--- Test post methods --------------------
	@Test public void statusCode() {
		Integer code = Integer.valueOf("501");
		PostResponse responseGet = this.caller.get(ENDPOIT_STATUS + code.toString(), null, PostResponse.class);
		assertNotNull(responseGet.getAdditionalProperties());
		assertNotNull(responseGet.getAdditionalProperties().get("status"));
		assertEquals(code, responseGet.getAdditionalProperties().get("status"));

		PostResponse responsePost = this.caller.post(ENDPOIT_STATUS + code, null, PostResponse.class);
		assertNull(responsePost);
	}
	
	@Test public void postTest1() {
		TestRequest request = TestRequest.random();
		
		PostResponse response = this.caller.post(ENDPOIT_POST, request, PostResponse.class);
		assertEquals(ENDPOIT_POST, response.getUrl());
		assertTestRequest(request, response);
	}
	
	@Test public void postTest2() {
		TestRequest request = TestRequest.random();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("header1", "value1");
		headers.put("header2", "value2");
		
		PostResponse response = this.caller.post(ENDPOIT_POST, headers, request, PostResponse.class);
		assertEquals(ENDPOIT_POST, response.getUrl());
	}
	
	@Test public void postTest3() {
		TestRequest request = TestRequest.random();
		
		String authCode = UUID.randomUUID().toString();
		
		PostResponse response = this.caller.bearerPost(ENDPOIT_POST, request, authCode, PostResponse.class);
		assertEquals(ENDPOIT_POST, response.getUrl());
		
		assertTrue(response.getHeaders().getAdditionalProperties().containsKey("authorization"));
		assertEquals(response.getHeaders().getAdditionalProperties().get("authorization"), "Bearer " + authCode);
	}
	
	@Test public void postTest4() {
		Map<String, String> params = new HashMap<>();
		params.put("string", "Hello");
		params.put("number", "1");
		params.put("date", "2024-01-01");
		
		PostResponse response = this.caller.post(ENDPOIT_POST, params, PostResponse.class);
		
		assertEquals(ENDPOIT_POST, response.getUrl());
		assertEquals("application/json; charset=utf-8", response.getHeaderContentType());
		this.assertParameters(params, response.getForm());
	}
	
	//--- Test get methods ----------------------
	@Test public void getTest1() {
		GetResponse response = this.caller.get(ENDPOIT_GET, GetResponse.class);
		assertEquals(ENDPOIT_GET, response.getUrl());
	}
	
	@Test public void getTest2() {
		Map<String, String> params = new HashMap<>();
		
		params.put("string", "Hello");
		params.put("number", "1");
		params.put("date", "2024-01-01");
		
		GetResponse response = this.caller.get(ENDPOIT_GET, params, GetResponse.class);
		
		assertTrue(response.getUrl().startsWith(ENDPOIT_GET));
		this.assertParameters(params, response.getArgs());
	}

	@Test public void getTest3() {
		Map<String, String> params = new HashMap<>();
		params.put("string", "Hello");
		params.put("number", "1");
		params.put("date", "2024-01-01");
		
		String authCode = UUID.randomUUID().toString();
		
		GetResponse response = this.caller.bearerGet(ENDPOIT_GET, params, authCode, GetResponse.class);
		
		assertTrue(response.getUrl().startsWith(ENDPOIT_GET));
		this.assertParameters(params, response.getArgs());

		assertTrue(response.getHeaders().getAdditionalProperties().containsKey("authorization"));
		assertEquals(response.getHeaders().getAdditionalProperties().get("authorization"), "Bearer " + authCode);
	}
	
	@Test public void getTest4() {
		Map<String, String> headers = new HashMap<>();
		headers.put("header1", "value1");
		headers.put("header2", "value2");
		
		Map<String, String> params = new HashMap<>();
		params.put("string", "Hello");
		params.put("number", "1");
		params.put("date", "2024-01-01");

		GetResponse response = this.caller.get(ENDPOIT_GET, headers, params, GetResponse.class);
		
		assertTrue(response.getUrl().startsWith(ENDPOIT_GET));
		this.assertParameters(params, response.getArgs());
		this.assertHeaders(headers, response);
	}

}

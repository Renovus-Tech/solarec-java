package tech.renvous.solarec.util;

import java.util.Map;

import javax.net.ssl.SSLException;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

public class JsonUtil {

	public static final String post(String url, Object data) throws JsonProcessingException {
		return WebClient
				.builder()
				.exchangeStrategies(
					ExchangeStrategies
						.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(30 * 1024 * 1024))
	                    .build()
	                )
				.build()
	            .post()
				.uri(url)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(toString(data))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	public static final String get(String url, Map<String, Object> values) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		
		if (CollectionUtil.notEmpty(values))
			for (Map.Entry<String, Object> entry : values.entrySet())
				builder.queryParam(entry.getKey(), entry.getValue());
		
		return WebClient
				.builder()
				.exchangeStrategies(
					ExchangeStrategies
						.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(30 * 1024 * 1024))
	                    .build()
	                )
				.build()
				.get()
				.uri(builder.build().toUri())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	public static final String get(String url, Map<String, Object> values, String authorization) throws SSLException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		
		if (CollectionUtil.notEmpty(values))
			for (Map.Entry<String, Object> entry : values.entrySet())
				builder.queryParam(entry.getKey(), entry.getValue());
		
		SslContext sslContext = SslContextBuilder
									.forClient()
									.trustManager(InsecureTrustManagerFactory.INSTANCE)
									.build();

		HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
		
		return WebClient
				.builder()
				.exchangeStrategies(
					ExchangeStrategies
						.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(30 * 1024 * 1024))
	                    .build()
	                )
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build()
				.get()
				.uri(builder.build().toUri())
				.header("Authorization", authorization)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	public static final String multipart(String url, BodyInserters.MultipartInserter multipart) {
		return WebClient.create().post()
				.uri(url)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON)
				.body(multipart)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	public static final JsonNode toNode(String json) throws JsonProcessingException {
		return new ObjectMapper().readTree(json);
	}
	
	public static final JsonNode toNode(Object object) throws JsonProcessingException {
		return new ObjectMapper().valueToTree(object);
	}
	
	public static final <T> T toObject(String json, Class<T> aClass) throws JsonProcessingException {
		return new ObjectMapper()
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.readValue(json, aClass);
	}
	
	public static final <T> T toObject(JsonNode json, Class<T> aClass) throws JsonProcessingException {
		return new ObjectMapper()
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.treeToValue(json, aClass);
	}
	
	public static final String toString(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
	
	public static final String toStringPretty(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}

}

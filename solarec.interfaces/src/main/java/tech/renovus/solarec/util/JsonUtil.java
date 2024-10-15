package tech.renovus.solarec.util;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		if (CollectionUtil.notEmpty(values)) {
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
		}
		
		try {
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
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), String.class);
			} catch (JsonProcessingException e) {
				return webClientError.getResponseBodyAsString();
			}
		}
	}
	
	public static final JsonNode toNode(String json) throws JsonProcessingException {
		return new ObjectMapper().readTree(json);
	}
	
	public static final <T> T toObject(String json, Class<T> aClass) throws JsonProcessingException {
		return new ObjectMapper()
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.readValue(json, aClass);
	}
	
	public static final String toString(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
	
	public static final String toStringPretty(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}
}

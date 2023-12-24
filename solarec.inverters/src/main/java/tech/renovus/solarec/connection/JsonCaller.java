package tech.renovus.solarec.connection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class JsonCaller {

	public static <T extends Object> T post(String url, Object payload, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		return webClient.post()
	            .uri(url)
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(BodyInserters.fromValue(payload))
	            .retrieve()
	            .bodyToMono(responseClass)
	            .block();
	}

	public static <T extends Object> T get(String url, Class<T> responseClass) {
		return get(url, new HashMap<>(0), responseClass);
	}
	
	public static <T extends Object> T get(String url, Map<String,String> params, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		return webClient.get()
	            .uri(url, params)
	            .retrieve()
	            .bodyToMono(responseClass)
	            .block();
	}
	
	public static <T extends Object> T bearerPost(String url, Object payload, String authCode, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		return webClient.post()
	            .uri(url)
	            .contentType(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Bearer " + authCode)
	            .body(BodyInserters.fromValue(payload))
	            .retrieve()
	            .bodyToMono(responseClass)
	            .block();
	}
}

package tech.renovus.solarec.connection;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class JsonCaller {

	public static <T extends Object> T post(String url, Object payload, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		return webClient.post()
	            .uri("http://example.com/api/endpoint")
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(BodyInserters.fromValue(payload))
	            .retrieve()
	            .bodyToMono(responseClass)
	            .block();
	}
	
	public static <T extends Object> T bearerPost(String url, Object payload, String authCode, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		return webClient.post()
	            .uri("http://example.com/api/endpoint")
	            .contentType(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Bearer " + authCode)
	            .body(BodyInserters.fromValue(payload))
	            .retrieve()
	            .bodyToMono(responseClass)
	            .block();
	}
}

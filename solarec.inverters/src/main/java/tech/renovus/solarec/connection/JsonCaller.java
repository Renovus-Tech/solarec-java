package tech.renovus.solarec.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.JsonUtil;

public class JsonCaller {

	//--- Private methods -----------------------
	private static String generateCompleteURL(String baseEndpoint, Map<String, String> queryParams) {
        try {
            // Create a URI with the base endpoint
            URI uri = new URI(baseEndpoint);

            // Add query parameters to the URI
            String queryString = buildQueryString(queryParams);
            URI completeURI = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), queryString, null);

            // Convert URI to String
            return completeURI.toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Error building URI", e);
        }
    }
	
	private static String buildQueryString(Map<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return null;
        }

        StringBuilder queryStringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (queryStringBuilder.length() > 0) {
                queryStringBuilder.append("&");
            }
            queryStringBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }
        return queryStringBuilder.toString();
    }
	
	//--- Post methods --------------------------
	public static <T extends Object> T post(String url, Object payload, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		try {
			return webClient.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_JSON)
		            .body(BodyInserters.fromValue(payload))
		            .retrieve()
		            .bodyToMono(responseClass)
		            .block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}
	
	public static <T extends Object> T post(String url, Map<String, String> headers, Object payload, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		try {
			return webClient.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_JSON)
		            .body(BodyInserters.fromValue(payload))
		            .retrieve()
		            .bodyToMono(responseClass)
		            .block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}

	public static <T extends Object> T bearerPost(String url, Object payload, String authCode, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		try {
			return webClient.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_JSON)
		            .header("Authorization", "Bearer " + authCode)
		            .body(BodyInserters.fromValue(payload))
		            .retrieve()
		            .bodyToMono(responseClass)
		            .block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}
	
	public static <T extends Object> T post(String url, Map<String,String> queryParams, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		try {
			return webClient.post()
		            .uri(generateCompleteURL(url, queryParams))
		            .retrieve()
		            .bodyToMono(responseClass)
		            .block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}
	
	//--- Get methods ---------------------------
	public static <T extends Object> T get(String url, Class<T> responseClass) {
		return get(url, new HashMap<>(0), responseClass);
	}
	
	public static <T extends Object> T get(String url, Map<String,String> queryParams, Class<T> responseClass) {
		WebClient webClient = WebClient.create();

		try {
			return webClient.get()
		            .uri(generateCompleteURL(url, queryParams))
		            .retrieve()
		            .bodyToMono(responseClass)
		            .block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}
	
	public static <T extends Object> T bearerGet(String url, Map<String,String> queryParams, String authCode, Class<T> responseClass) {
		WebClient webClient = WebClient.create();
		
		try {
			return webClient.get()
					.uri(generateCompleteURL(url, queryParams))
					.header("Authorization", "Bearer " + authCode)
					.retrieve()
					.bodyToMono(responseClass)
				.block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}
	
	public static <T extends Object> T get(String url, Map<String, String> headers, final Map<String,String> queryParams, Class<T> responseClass) {
		WebClient webClient = WebClient
				.builder()
				.build();
		
		try {
			return webClient.get()
		            .uri(generateCompleteURL(url, queryParams))
		            .headers(consumer -> {
		            	if (headers != null && ! headers.isEmpty()) {
		            		headers.entrySet().forEach(entry -> consumer.add(entry.getKey(), entry.getValue()));
		            	}
		            })
		            .retrieve()
		            .bodyToMono(responseClass)
		            .block();
		} catch (WebClientResponseException webClientError) {
			try {
				return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
			} catch (JsonProcessingException e) {
				LoggerService.inverterLogger().error("Error calling URL: " + url, e);
				return null;
			}
		}
	}
}

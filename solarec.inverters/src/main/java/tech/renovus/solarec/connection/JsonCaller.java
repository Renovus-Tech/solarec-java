package tech.renovus.solarec.connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;

import reactor.netty.http.client.HttpClient;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.JsonUtil;

public class JsonCaller {

	//--- Private methods -----------------------
	private static WebClient buildWebClient() {
		WebClient webClient = WebClient.builder()
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024)).build())
				.clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))
				.build();
		return webClient;
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
	
	private static <T extends Object> T processError(String url, WebClientResponseException webClientError, Class<T> responseClass) {
		try {
			return JsonUtil.toObject(webClientError.getResponseBodyAsString(), responseClass);
		} catch (JsonProcessingException e) {
			LoggerService.inverterLogger().error("Error calling URL: " + url, e);
			return null;
		}
	}
	
	private static <T extends Object> void addHeaders(ResponseEntity<T> response) {
		if (response == null) {
			return;
		}
		
		T entity = response.getBody();
		if (entity instanceof IWithHeaders) {
			IWithHeaders withHeaders = (IWithHeaders) entity;
			List<String> headers = withHeaders.getHeadersToSet();
			if (CollectionUtil.notEmpty(headers)) {
				for (String header : headers) {
					withHeaders.setHeader(header, response.getHeaders().getFirst(header));
				}
			}
			
		}
	}
	
	private static <T extends Object> T processResponse(ResponseSpec responseSpec, Class<T> responseClass) throws WebClientResponseException {
		ResponseEntity<T> response = responseSpec.toEntity(responseClass).block();
		addHeaders(response);
		
		return response == null ? null : response.getBody();
	}
	
	//--- Generators methods --------------------
	public static String generateCompleteURL(String baseEndpoint, Map<String, String> queryParams) {
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
	
	//--- Post methods --------------------------
	public static <T extends Object> T post(String url, Object payload, Class<T> responseClass) {
		try {
			ResponseSpec responseSpec = buildWebClient()
					.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_JSON)
		            .body(BodyInserters.fromValue(payload))
		            .retrieve();
					
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}
	
	public static <T extends Object> T post(String url, Map<String, String> headers, Object payload, Class<T> responseClass) {
		try {
			ResponseSpec responseSpec = buildWebClient()
					.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_JSON)
		            .headers(consumer -> {
		            	if (headers != null && ! headers.isEmpty()) {
		            		headers.entrySet().forEach(entry -> consumer.add(entry.getKey(), entry.getValue()));
		            	}
		            })
		            .body(BodyInserters.fromValue(payload))
		            .retrieve();
					
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}

	public static <T extends Object> T bearerPost(String url, Object payload, String authCode, Class<T> responseClass) {
		try {
			ResponseSpec responseSpec = buildWebClient()
					.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_JSON)
		            .header("Authorization", "Bearer " + authCode)
		            .body(BodyInserters.fromValue(payload))
		            .retrieve();
					
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}
	
	public static <T extends Object> T post(String url, Map<String,String> queryParams, Class<T> responseClass) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		if (queryParams != null) {
			queryParams.forEach(formData::add);
		}

		try {
			ResponseSpec responseSpec = buildWebClient()
					.post()
		            .uri(url)
		            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		            .bodyValue(formData)
		            .retrieve();
					
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}
	
	//--- Get methods ---------------------------
	public static <T extends Object> T get(String url, Class<T> responseClass) {
		return get(url, new HashMap<>(0), responseClass);
	}
	
	public static <T extends Object> T get(String url, Map<String,String> queryParams, Class<T> responseClass) {
		try {
			ResponseSpec responseSpec = buildWebClient()
					.get()
		            .uri(generateCompleteURL(url, queryParams))
		            .retrieve();
					
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}
	
	public static <T extends Object> T bearerGet(String url, Map<String,String> queryParams, String authCode, Class<T> responseClass) {
		url = generateCompleteURL(url, queryParams);
		try {
			ResponseSpec responseSpec = buildWebClient()
					.get()
					.uri(url)
					.header("Authorization", "Bearer " + authCode)
					.retrieve();
					
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}
	
	public static <T extends Object> T get(String url, Map<String, String> headers, final Map<String,String> queryParams, Class<T> responseClass) {
		url = generateCompleteURL(url, queryParams);
		try {
			ResponseSpec responseSpec = buildWebClient()
					.get()
		            .uri(url)
		            .headers(consumer -> {
		            	if (headers != null && ! headers.isEmpty()) {
		            		headers.entrySet().forEach(entry -> consumer.add(entry.getKey(), entry.getValue()));
		            	}
		            })
		            .retrieve();
			
			return processResponse(responseSpec, responseClass);
		} catch (WebClientResponseException webClientError) {
			return processError(url, webClientError, responseClass);
		}
	}
}

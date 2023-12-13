package tech.renovus.solarec.certificate.drecs;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import tech.renovus.solarec.certificate.drecs.api.auth.AuthRequest;
import tech.renovus.solarec.certificate.drecs.api.auth.AuthResponse;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;

/**
 * API information: https://api.drecs.org/swagger
 */
public class DRecsService {
	
	protected @Autowired RenovusSolarecConfiguration config;

	/**
	 * Doc: https://api.drecs.org/swagger/#/auth/AuthController_login
	 */
	public void authenticate() {
		// URL
		String authUrl = "https://api.drecs.org/api/auth/login";

		// Credenciales de autenticación
		String username = this.config.getDrecsServiceUsername();
		String password = this.config.getDrecsServicePassword();

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			// Creo el post
			HttpPost httpPost = new HttpPost(authUrl);

			// Añado headers
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			// Creo objeto que representa el JSON y lo paso a String
			AuthRequest jsonRequest = new AuthRequest().withUsername(username).withPassword(password);
			String jsonRequestString = new ObjectMapper().writeValueAsString(jsonRequest);
			
			// Seteo el JSON como entidad saliente
			StringEntity stringEntity = new StringEntity(jsonRequestString);
			httpPost.setEntity(stringEntity);

			System.out.println("Executing request " + httpPost.getRequestLine()); // TODO Mejorar a log si es necesario

			// Ejecutar la solicitud y obtener la respuesta
			HttpResponse response = httpClient.execute(httpPost);	
			String jsonResponseString = EntityUtils.toString(response.getEntity());
			
			AuthResponse jsonResponse = new ObjectMapper()
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.readValue(jsonResponseString, AuthResponse.class);

			// Obtener respuesta
			if (jsonResponse.getStatusCode() == HttpStatus.SC_OK) {
				// Procesar la respuesta
				//String jsonResponse = EntityUtils.toString(responseEntity);
				System.out.println("Respuesta exitosa: " + jsonResponse);
			} else {
				// Manejar errores de autenticación
				System.out.println("Error en la autenticación. Código de estado: " 
						+ response.getStatusLine().getStatusCode());
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	/**
	 * Doc:
	 * https://api.drecs.org/swagger/#/organization/OrganizationController_register
	 */
	public void registerOrganization() {
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/device/DeviceController_create
	 */
	public void registerDevice() {
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_newstoreRead
	 */
	public void uploadHistoryData() {
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_storeReads
	 */
	public void uploadMeterData() {
	}

}

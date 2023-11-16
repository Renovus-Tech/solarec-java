package tech.renovus.solarec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tech.renovus.solarec.api.rest.controller.EndPointFactory;

@SpringBootApplication
@EnableScheduling
public class RenovusSolarecApplication {

	//--- Main methods --------------------------
	public static void main(String[] args) {
		SpringApplication.run(RenovusSolarecApplication.class, args);
	}

	//--- Configuration methods -----------------
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping(EndPointFactory.REST_API + "/**").allowedOrigins("*").allowedMethods("OPTION", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
			}
		};
	}
}

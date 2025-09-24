package com.pessoal.bora.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {
	@Bean
	public OpenAPI openAPIDocumentation() {
		
		return new OpenAPI()
				.info(
					new Info()
						.title("Bora API")
						.description("API do sistema Bora, de facilitação de mobilidade urbana")
						.version("V1.0")
						.contact(new Contact()
								.name("Sandro M. dos Santos")
								.email("sandromarista@gmail.com")
								)
						);
				
	}

}

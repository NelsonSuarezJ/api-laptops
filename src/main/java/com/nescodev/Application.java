package com.nescodev;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public OpenAPI customAPI (){
		return new OpenAPI()
				.info(new Info().title("Laptops API").description("API de Laptops con CRUD").version("1.0.0")
						.contact(new Contact().name("Nelson Suarez").email("nelson.sj@hotmail.com"))
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}

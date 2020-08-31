package com.tryout.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import databaseFiller.DatabaseFiller;



@JsonIgnoreProperties(ignoreUnknown = true) //to ignore unknown fields during json parsing
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		DatabaseFiller dbFiller = new DatabaseFiller();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/authenticate").allowedOrigins("http://localhost:3000");
				registry.addMapping("/subscriptions").allowedOrigins("http://localhost:3000");
			}
		};
	}
	
}

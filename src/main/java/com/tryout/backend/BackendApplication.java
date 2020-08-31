package com.tryout.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ristoranteEntity.Ristorante;
import ristoranteEntity.Subscription;


@JsonIgnoreProperties(ignoreUnknown = true) //to ignore unknown fields during json parsing
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		dataBaseFiller();
		jacksonReader();
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
	
	public static void dataBaseFiller() {
		String url = "jdbc:postgresql://localhost/ristorante";
		String user = "postgres";
		String password = "admin";
		
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void jsonReader() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(""));
			JSONObject jsonObject = (JSONObject) obj;
			String name = (String) jsonObject.get(""); //this is the key
			System.out.println("Name is: " + name);
			
			//loop array
			JSONArray coursesArray = (JSONArray) jsonObject.get("");
			Iterator<String> iterator = coursesArray.iterator();
			
			while(iterator.hasNext()) {
				
			}
			
		}
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(ParseException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	public static void jacksonReader() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //to ignore unknown fields in json file
		
		try {
			// JSON file to Java object
	         Ristorante ristoranteModerno = mapper.readValue(new File("src/main/resources/db - Copy.json"), Ristorante.class);
	         System.out.println("ristorantedb file found!");
	         System.out.println(ristoranteModerno.getDishes());
	         System.out.println("The name of the second dish is: " + ristoranteModerno.getDishes().get(2).getName());
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}

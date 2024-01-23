package com.capstoneproject.educonnect;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.capstoneproject.educonnect.Service.FileService;

@SpringBootApplication
public class EduConnectApplication implements CommandLineRunner {
	

	@Resource
	FileService fileService;
	
	@Value("${cors.allowed-origins}")
	private String allowed;

	public static void main(String[] args) {
		SpringApplication.run(EduConnectApplication.class, args);
		
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(allowed)
				.allowedMethods("*");
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {

		fileService.init();
	}

}

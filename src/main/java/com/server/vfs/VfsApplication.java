package com.server.vfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;



@Configuration
@ComponentScan
@EnableAutoConfiguration
public class VfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VfsApplication.class, args);
	}

	@Bean
	MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("5120KB");
		factory.setMaxRequestSize("5120KB");
		return factory.createMultipartConfig();
	}

}

package com.tinyurl_system_design.key_generator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class KeyGeneratorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeyGeneratorServiceApplication.class, args);
	}

}

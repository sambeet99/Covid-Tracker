package com.sam.coronavirusreporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


//tdgdt
@SpringBootApplication
@EnableScheduling
public class CoronavirusreporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusreporterApplication.class, args);
	}

}
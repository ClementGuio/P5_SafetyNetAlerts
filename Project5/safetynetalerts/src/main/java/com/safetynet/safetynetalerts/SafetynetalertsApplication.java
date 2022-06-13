package com.safetynet.safetynetalerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.safetynetalerts.controller.AlertCommandLineController;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;

@SpringBootApplication
public class SafetynetalertsApplication {

	@Autowired
	DataJSONDeserializer dataAlertReader;
	
	public static void main(String[] args) {
		SpringApplication.run(SafetynetalertsApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		System.out.println("Run!!!");
		dataAlertReader.readAndStore();
	}*/

}

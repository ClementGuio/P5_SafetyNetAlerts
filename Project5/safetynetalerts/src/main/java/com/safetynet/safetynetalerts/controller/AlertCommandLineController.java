package com.safetynet.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;

@Component
public class AlertCommandLineController implements CommandLineRunner{

	@Autowired
	DataJSONDeserializer dataAlertReader;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("run!!!");
		dataAlertReader.readAndStore();
	}

}

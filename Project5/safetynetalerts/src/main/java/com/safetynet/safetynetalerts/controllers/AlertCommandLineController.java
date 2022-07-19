package com.safetynet.safetynetalerts.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.safetynetalerts.exceptions.MissingEntitiesException;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;

@Component
public class AlertCommandLineController implements CommandLineRunner{
	Logger logger = LoggerFactory.getLogger(AlertCommandLineController.class);
	
	@Autowired
	DataJSONDeserializer dataDeserializer;
	@Autowired
	EntitiesContainer entities;
	@Autowired
	LinkedEntitiesContainer linkedEntities;
	
	@Override
	public void run(String... args) throws IOException, StreamReadException, DatabindException {
		logger.info("Run :"+LocalDateTime.now());
		if (args.length>0 && args[0].equals("test")) {
			dataDeserializer.readAndStore(new File("src/test/java/com/safetynet/safetynetalerts/resources/dataTest.json"));
		}else {
			dataDeserializer.readAndStore(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"));
			}
		
		try {
			linkedEntities.linkEntities();
		}catch(MissingEntitiesException e) {
			logger.error(e.getMessage());
		}
		logger.info("Data successfully loaded");
		logger.trace("LOADED:\n"+linkedEntities);
	}
}

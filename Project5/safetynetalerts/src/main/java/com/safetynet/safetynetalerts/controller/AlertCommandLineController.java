package com.safetynet.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;
import com.safetynet.safetynetalerts.service.EntitiesService;

//TODO : SWAGGER (tests + doc)
//TODO : javadoc
//TODO : SOLID


@Component
public class AlertCommandLineController implements CommandLineRunner{
	Logger logger = LoggerFactory.getLogger(AlertCommandLineController.class);
	
	@Autowired
	DataJSONDeserializer dataDeserializer;
	@Autowired
	EntitiesContainer entities;
	@Autowired
	EntitiesService entitiesService;
	
	private String address1 = "1509 Culver St";
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Run!!");
		dataDeserializer.readAndStore();
		//entities.showEntities();
		entities.linkEntities();
		/*List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		for (PersonMedicalrecordFirestation linkedEntity : linkedEntities) {
			System.out.println(linkedEntity);
		}*/
	}

}

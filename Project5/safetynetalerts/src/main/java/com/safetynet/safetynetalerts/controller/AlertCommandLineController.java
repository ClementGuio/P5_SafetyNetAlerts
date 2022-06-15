package com.safetynet.safetynetalerts.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;
import com.safetynet.safetynetalerts.service.EntitiesService;
import com.safetynet.safetynetalerts.service.PersonService;

@Component
public class AlertCommandLineController implements CommandLineRunner{
	Logger logger = LoggerFactory.getLogger(AlertCommandLineController.class);
	
	@Autowired
	DataJSONDeserializer dataDeserializer;
	
	@Autowired
	EntitiesService entities;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Run!!");
		dataDeserializer.readAndStore();
		//entities.showEntities();
		//System.out.println(entities.getPersons());
		//System.out.println(entities.getPerson("Ron", "Peters"));
		//Collection<Person> ronPeters = personService.getPerson("Ron", "Peters");
		//System.out.println(entities.getStationUnder(2));
	}

}

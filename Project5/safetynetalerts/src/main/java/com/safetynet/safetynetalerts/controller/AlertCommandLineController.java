package com.safetynet.safetynetalerts.controller;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;
import com.safetynet.safetynetalerts.service.EntitiesService;

//TODO : doc Enunciate
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
	LinkedEntitiesContainer linkedEntities;
	
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Run!!");
		if (args.length>0 && args[0].equals("test")) {
			dataDeserializer.readAndStore(new File("src/test/java/com/safetynet/safetynetalerts/resources/dataTest.json"));
		}else {
			//dataDeserializer.readAndStore(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"));
			dataDeserializer.readAndStore(new File("src/test/java/com/safetynet/safetynetalerts/resources/dataTest.json"));
		}
		linkedEntities.linkEntities();
		System.out.println(linkedEntities);
		/*Person p1 = new Person("firstname","lastname","1 St","Community","99999","123-123-123","community@email.com");
		Firestation s1 = new Firestation("1 St",1);
		Medicalrecord r1 = new Medicalrecord("firstname","lastname",LocalDate.now(),new ArrayList<String>(),new ArrayList<String>());

		List<PersonMedicalrecordFirestation> entry = new ArrayList<PersonMedicalrecordFirestation>();
		entry.add(new PersonMedicalrecordFirestation(p1,r1,s1));
		entities.setLinkedEntities(entry);
		System.out.println(entities.linkedToString());
	*/
	}

}

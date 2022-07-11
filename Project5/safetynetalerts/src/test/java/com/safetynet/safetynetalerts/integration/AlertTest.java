package com.safetynet.safetynetalerts.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.controller.AlertCommandLineController;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;
import com.safetynet.safetynetalerts.service.AlertBuilder;
//TODO : AlertTest, CRUDTest, UnitsTest
//TODO : créer objets Alert pour les tests (NIK!!) ou try hard swagger


public class AlertTest {
	
	@Autowired
	AlertBuilder alertBuilder;
	
	@Autowired
	DataJSONDeserializer deser;
	
	@Autowired
	EntitiesContainer entities;
	
	@Autowired
	LinkedEntitiesContainer linkedEntities;
	
	//List<PersonMedicalrecordFirestation> linkedEntities = null;
	
	/*@BeforeAll
	public static void setUp() {
		List<PersonMedicalrecordFirestation> entry = new ArrayList<PersonMedicalrecordFirestation>();
		Person p1 = new Person("firstname","lastname","1 St","Community","99999","123-123-123","community@email.com");
		Firestation s1 = new Firestation("1 St",1);
		Medicalrecord r1 = new Medicalrecord("firstname","lastname",LocalDate.now(),new ArrayList<String>(),new ArrayList<String>());
		entry.add(new PersonMedicalrecordFirestation(p1,r1,s1));
		
		entities.setLinkedEntities(entry);
	}*/
	
	/*@BeforeAll
	public void setUp() throws Exception {
		deser.readAndStore(new File("src/test/java/com/safetynet/safetynetalerts/resources/dataTest.json"));
		entities.linkEntities();
		linkedEntities = entities.getLinkedEntities();
		entities.linkedToString();
	}*/
	
	// TODO: Use JsonNode.elements() to retrieve all values of an Object or a list
	/*@Test
	public void testFirestationAlert() throws ClientProtocolException, IOException{
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode resource = mapper.readTree(new URL("http://localhost:8080/firestation?stationNumber=1"));
	    assertEquals();
	}*/
	
	//Person : String firstName, String lastName, String address, String city, String zip, String phone,String email, Map<String, Object> additionalProperties
	//Firestation : String address, Integer station, Map<String, Object> additionalProperties
	//Medicalrecord : String firstName, String lastName, LocalDate birthdate, List<String> medications, List<String> allergies
	
	@Test
	public void testCommunityEmail() {
		//entities = new EntitiesContainer();
		//List<PersonMedicalrecordFirestation> entry = new ArrayList<PersonMedicalrecordFirestation>();
		Person p1 = new Person("firstname","lastname","1 St","Community","99999","123-123-123","community@email.com");
		Firestation s1 = new Firestation("1 St",1);
		Medicalrecord r1 = new Medicalrecord("firstname","lastname",LocalDate.now(),new ArrayList<String>(),new ArrayList<String>());
		//entry.add(new PersonMedicalrecordFirestation(p1,r1,s1));
		System.out.println(entities);
		entities.getPersons().add(p1);
		entities.getFirestations().add(s1);
		entities.getMedicalrecords().add(r1);
		//linkedEntities = new LinkedEntitiesContainer();
		linkedEntities.linkEntities();
		alertBuilder= new AlertBuilder();
		JsonNode alert = alertBuilder.buildCommunityEmailAlert("Community");
		List<JsonNode> fields = alert.findValues("email");
		assertTrue(fields.contains("community@email.com"));
	}

}

package com.safetynet.safetynetalerts.integration;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.EntitiesService;


@SpringBootTest(args ="test")
//@RunWith(SpringRunner.class)
public class EntitiesServiceTest {
	
	//TODO : tester le reste du entities service (seulement test unitaire) sinon supprimer
	
	@Autowired
	EntitiesContainer container;
	@Autowired
	EntitiesService service;
	/*
	@BeforeEach
	public void setUp() {
		service = new EntitiesService();
		container = new EntitiesContainer();
		container.setFirestations(new ArrayList<Firestation>());
		container.setMedicalrecords(new ArrayList<Medicalrecord>());
		container.setPersons(new ArrayList<Person>());
	}
	*/
	@Test
	public void testAddFirestation() {
		Firestation station = new Firestation();
		station.setAddress("1 address");
		station.setStation(1);
		
		service.addFirestation(station);
		
		assertTrue(container.getFirestations().contains(station));
	}
}

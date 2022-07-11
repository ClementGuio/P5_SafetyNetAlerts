package com.safetynet.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.EntitiesService;

@RequestMapping("/v1")
@RestController
public class CRUDRestController {
	
	Logger logger = LoggerFactory.getLogger(CRUDRestController.class);

	@Autowired
	EntitiesService entities;
	
	//Person
	@PostMapping(value="/person")
	public void addPerson(@RequestBody Person person) {
		logger.info("Post : /person");
		entities.addPerson(person);
		entities.showPersons();
	}
	@PutMapping(value="/person")
	public void updatePerson(@RequestBody Person person ) {
		logger.info("Put : /person");
		entities.updatePerson(person);
		entities.showPersons();
	}
	@DeleteMapping(value="/person", params= {"firstName","lastName"})
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("Delete : /person?firstName="+firstName+"lastName="+lastName);
		entities.removePerson(firstName, lastName);
		entities.showPersons();
	}
	
	//Firestation
	@PostMapping(value="/firestation")
	public void addFirestation(@RequestBody Firestation station) {
		logger.info("Post : /firestation");
		entities.addFirestation(station);
		logger.info("added");
		entities.showFirestations();
	}
	@PutMapping(value="/firestation")
	public void updatePerson(@RequestBody Firestation station) {
		logger.info("Put : /firestation");
		entities.updateFirestation(station);
		logger.info("Updated");
		entities.showFirestations();
	}
	@DeleteMapping(value="/firestation")
	public void deletePerson(@RequestBody Firestation station) {
		logger.info("Delete : /firestation");
		entities.removeFirestation(station);
		logger.info("Deleted");
		entities.showFirestations();
	}
	
	//Medicalrecord
	@PostMapping(value="/medicalrecord")
	public void addMedicalrecord(@RequestBody Medicalrecord record) {
		logger.info("Post : /medicalerecord");
		entities.addMedicalrecord(record);
		entities.showMedicalrecords();
	}
	@PutMapping(value="/medicalrecord")
	public void updateMedicalrecord(@RequestBody Medicalrecord record ) {
		logger.info("Put : /medicalrecord");
		entities.updateMedicalrecord(record);
		entities.showMedicalrecords();
	}
	@DeleteMapping(value="/medicalrecord", params= {"firstName", "lastName"})
	public void deleteMedicalrecord(@RequestParam String firstName,@RequestParam String lastName) {
		logger.info("Delete : /firestation");
		entities.removeMedicalrecord(firstName, lastName);
		entities.showMedicalrecords();
	}

	
}

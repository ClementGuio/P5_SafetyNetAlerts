package com.safetynet.safetynetalerts.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.exceptions.IllegalRequestException;
import com.safetynet.safetynetalerts.exceptions.MissingEntitiesException;
import com.safetynet.safetynetalerts.interfaces.ICRUDService;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;

//TODO : log dans un fichier
@RequestMapping("/v1")
@RestController
public class CRUDRestController {

	Logger logger = LoggerFactory.getLogger(CRUDRestController.class);

	@Autowired
	ICRUDService entities;

	@Autowired
	EntitiesContainer container;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@GetMapping(value="/person", params= {"firstName","lastName"})
	public JsonNode getPerson(@RequestParam String firstName, @RequestParam String lastName) throws IllegalRequestException{
		logger.info("GET /v1/person?firstName="+firstName+"&lastName="+lastName);
		Person person = container.getPerson(firstName,lastName);
		if (person == null) {
			logger.error("Missing Person -> firstName:"+firstName+", lastName:"+lastName);
			throw new IllegalRequestException("This Person does not exist.");
		}else {
			return mapper.valueToTree(person);
		}
	}
	
	@GetMapping(value="/medicalrecord", params= {"firstName","lastName"})
	public JsonNode getMedicalrecord(@RequestParam String firstName, @RequestParam String lastName) throws IllegalRequestException {
		logger.info("GET /v1/medicalrecord?firstName="+firstName+"&lastName="+lastName);
		Medicalrecord record = container.getMedicalrecordOf(firstName, lastName);
		if (record == null) {
			logger.error("Missing Medicalrecord -> firstName:"+firstName+", lastName:"+lastName);
			throw new IllegalRequestException("This Medicalrecord does not exist.");
		}else {
			return mapper.valueToTree(record);
		}
	}
	
	@GetMapping(value="/firestation", params="address")
	public JsonNode getFirestation(@RequestParam String address) throws IllegalRequestException{
		logger.info("GET /v1/firestation?address="+address);
		Firestation station = container.getFirestationOf(address);
		if (station == null) {
			logger.error("Missing Firestation -> address:"+address);
			throw new IllegalRequestException("This Firestation does not exist.");
		}else {
			return mapper.valueToTree(station);
		}
	}
	
	//Person
	@PostMapping(value="/person")
	public void addPerson(@RequestBody Person person) throws MissingEntitiesException{
		logger.info("POST /v1/person : "+person);
		entities.addPerson(person);
	}
	@PutMapping(value="/person")
	public void updatePerson(@RequestBody Person person ) throws IllegalRequestException{
		logger.info("PUT : /v1/person : "+person);
		entities.updatePerson(person);
	}
	@DeleteMapping(value="/person", params= {"firstName","lastName"})
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) throws IllegalRequestException{
		logger.info("DELETE /person?firstName="+firstName+"lastName="+lastName);
		entities.removePerson(firstName, lastName);
	}
	
	//Firestation
	@PostMapping(value="/firestation")
	public void addFirestation(@RequestBody Firestation station) {
		logger.info("POST /firestation : "+station);
		entities.addFirestation(station);
	}
	@PutMapping(value="/firestation")
	public void updatePerson(@RequestBody Firestation station) throws IllegalRequestException {
		logger.info("PUT /firestation : "+station);
		entities.updateFirestation(station);
	}
	@DeleteMapping(value="/firestation", params= "address")
	public void deleteFirestation(@RequestParam String address) throws IllegalRequestException {
		logger.info("DELETE /firestation?address="+address);
		entities.removeFirestation(address);
	}
	
	//Medicalrecord
	@PostMapping(value="/medicalrecord")
	public void addMedicalrecord(@RequestBody Medicalrecord record) {
		logger.info("POST /medicalerecord : "+record);
		entities.addMedicalrecord(record);
	}
	@PutMapping(value="/medicalrecord")
	public void updateMedicalrecord(@RequestBody Medicalrecord record ) throws IllegalRequestException{
		logger.info("PUT /medicalrecord : "+record);
		entities.updateMedicalrecord(record); 
	}
	@DeleteMapping(value="/medicalrecord", params= {"firstName", "lastName"})
	public void deleteMedicalrecord(@RequestParam String firstName,@RequestParam String lastName) throws IllegalRequestException{
		logger.info("DELETE /firestation?firstName="+firstName+"&lastName="+lastName);
		entities.removeMedicalrecord(firstName, lastName);
	}
}

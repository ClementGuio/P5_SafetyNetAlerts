package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;
import com.safetynet.safetynetalerts.repository.DataJSONSerializer;
import com.safetynet.safetynetalerts.service.AlertBuilder;
import com.safetynet.safetynetalerts.service.EntitiesService;

@RestController
public class AlertRestController {

	Logger logger = LoggerFactory.getLogger(AlertRestController.class);
	
	@Autowired
	EntitiesService entities;
	
	@Autowired
	AlertBuilder alertBuilder;
	
	@Autowired
	EntitiesContainer container;
	
	DataJSONSerializer serializer = new DataJSONSerializer();;
	
	//TEST
	/*@GetMapping("/resumepersons")
	public String getResumePersons() throws Exception{
		logger.info("/resumepersons");
		return serializer.generateAlert(container.getLinkedEntities());
	}*/
	
	@GetMapping("/linkedpersons") 
	public JsonNode getLinkedPersons() throws Exception{
		logger.info("/linkedpersons");
		//return serializer.generateAlert(container.getLinkedEntities());
		return null;
	}
	 
	
	
	@GetMapping("/person")
	public List<Person> getPersons(){
		logger.info("/person");
		return entities.getPersons();
	}
	@GetMapping(value = "/person", params = {"firstName","lastName"})
	public Person getPerson(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("/person?firstName="+firstName+"&lastName="+lastName);
		return entities.getPerson(firstName, lastName);
	}
	
	@GetMapping(value = "/linkedentities")
	public List<PersonMedicalrecordFirestation> getLinkedEntities(){
		logger.info("/linkedentities");
		return container.getLinkedEntities();
	}
	//public
	
	//OK
	@GetMapping(value = "/firestation", params = {"stationNumber"})
	public JsonNode getPersonsCoveredBy(@RequestParam int stationNumber){
		logger.info("/firestation?stationNumber?"+stationNumber);
		return alertBuilder.buildFirestationAlert(stationNumber);
	}
	//OK
	@GetMapping(value = "/childAlert", params = {"address"})
	public JsonNode getChildAlert(@RequestParam String address){
		logger.info("getChildAlert("+address+")");
		return alertBuilder.buildChildAlert(address);
	}
	//OK
	@GetMapping(value = "/phoneAlert", params = {"firestation"})
	public JsonNode getPhoneAlert(@RequestParam int firestation) throws Exception{
		logger.info("/phoneAlert?firestation="+firestation);
		return alertBuilder.buildPhoneAlert(firestation);
	}
	
	@GetMapping(value = "/fire", params = {"address"})
	public JsonNode getFireAlert(String address){
		logger.info("getFireAlert("+address+")");
		return alertBuilder.buildFireAlert(address);
	}
	
	@GetMapping(value = "flood/stations", params = {"stations"})
	public JsonNode getFloodAlert(@RequestParam Integer... stations){
		logger.info("getFloodAlert("+stations+")");
		return alertBuilder.buildFloodAlert(stations);
	}
	
	@GetMapping(value = "/personInfo", params = {"firstName","lastName"})
	public List<String> getPersonInfo(String firstName, String lastName){
		return null;
	}
	//OK
	@GetMapping(value = "/communityEmail", params = {"city"})
	public JsonNode getInhabitantsEmail(@RequestParam String city){
		return alertBuilder.buildCommunityEmailAlert(city);
	}
	
}

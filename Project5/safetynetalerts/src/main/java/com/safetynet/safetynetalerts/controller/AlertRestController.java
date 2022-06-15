package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.EntitiesService;

@RestController
public class AlertRestController {

	Logger logger = LoggerFactory.getLogger(AlertRestController.class);
	
	@Autowired
	EntitiesService entities;
	
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
	
	//public
	//TODO : types de retour invalides -> besoin de classe supplémentaires
	@GetMapping(value = "/firestation", params = {"stationNumber"})
	public List<Person> getPersonsCoveredBy(@RequestParam int stationNumber){
		return null;
	}
	
	@GetMapping(value = "/childAlert", params = {"address"})
	public List<Person> getChild(@RequestParam String address){
		return null;
	}
	
	@GetMapping(value = "/phoneAlert", params = {"firestation"})
	public List<String> getPhoneNumberCoveredBy(@RequestParam int firestation){
		return null;
	}
	
	@GetMapping(value = "fire?address={address}", params = {"address"})
	public List<String> getInhabitantsAndFirestationAt(String address){
		return null;
	}
	
	@GetMapping(value = "flood/stations", params = {"numberStationList"})
	public List<String> getHomesCoveredBy(List<Integer> numberStationList){
		return null;
	}
	
	@GetMapping(value = "personInfo", params = {"firstName","lastName"})
	public List<String> getPersonInfo(String firstName, String lastName){
		return null;
	}
	
	@GetMapping(value = "communityEmail", params = {"city"})
	public List<String> getInhabitantsEmail(String city){
		return null;
	}
	
}

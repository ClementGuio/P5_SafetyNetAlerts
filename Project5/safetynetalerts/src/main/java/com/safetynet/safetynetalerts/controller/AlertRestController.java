package com.safetynet.safetynetalerts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.service.AlertBuilder;
import com.safetynet.safetynetalerts.service.EntitiesService;

@RequestMapping("/v1")
@RestController
public class AlertRestController {

	Logger logger = LoggerFactory.getLogger(AlertRestController.class);
	
	@Autowired
	EntitiesService entities;
	
	@Autowired
	AlertBuilder alertBuilder;
	
	@Autowired
	EntitiesContainer container;
	
	@GetMapping(value = "/firestation", params = {"stationNumber"})
	public JsonNode getFirestationAlert(@RequestParam int stationNumber){
		logger.trace("GET /v1/firestation?stationNumber="+stationNumber);
		return alertBuilder.buildFirestationAlert(stationNumber);
	}
	
	@GetMapping(value = "/childAlert", params = {"address"})
	public JsonNode getChildAlert(@RequestParam String address){
		logger.trace("GET /v1/childAlert?address="+address);
		return alertBuilder.buildChildAlert(address);
	}

	@GetMapping(value = "/phoneAlert", params = {"firestation"})
	public JsonNode getPhoneAlert(@RequestParam int firestation) throws Exception{
		logger.trace("GET /v1/phoneAlert?firestation="+firestation);
		return alertBuilder.buildPhoneAlert(firestation);
	}
	
	@GetMapping(value = "/fire", params = {"address"})
	public JsonNode getFireAlert(String address){
		logger.trace("GET /v1/fire?address="+address);
		return alertBuilder.buildFireAlert(address);
	}
	
	@GetMapping(value = "/flood/stations", params = {"stations"})
	public JsonNode getFloodAlert(@RequestParam Integer... stations){
		logger.trace("GET /v1/flood/stations?stations="+stations);
		return alertBuilder.buildFloodAlert(stations);
	}
	
	@GetMapping(value = "/personInfo", params = {"firstName","lastName"})
	public JsonNode getPersonInfo(String firstName, String lastName){
		logger.trace("GET /v1/personInfo?firstName="+firstName+"&lastName="+lastName);
		return alertBuilder.buildPersonInfo(firstName, lastName);
	}

	@GetMapping(value = "/communityEmail", params = {"city"})
	public JsonNode getCommunityEmail(@RequestParam String city){
		logger.trace("GET /v1/communityEmail?city="+city);
		return alertBuilder.buildCommunityEmailAlert(city);
	}
}

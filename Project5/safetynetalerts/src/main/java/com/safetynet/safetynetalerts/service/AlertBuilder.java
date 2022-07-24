package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;

@Service
public class AlertBuilder {
	
	@Autowired
	LinkedEntitiesContainer entities;
	
	@Autowired
	ObjectMapper mapper;
	
	public JsonNode buildPersonInfo(String firstName, String lastName) {
    	ObjectNode node = mapper.createObjectNode();
    	List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
    	Set<Map<String,Object>> response = new HashSet<Map<String,Object>>();
    	for (PersonMedicalrecordFirestation person : linkedEntities) {
    		if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
    			System.out.println("----------------"+firstName+" "+lastName);
    			Map<String, Object> field = new LinkedHashMap<String,Object>();
    			field.put("firstName", firstName);
    			field.put("lastName", lastName);
    			field.put("address", person.getAddress());
    			field.put("age", person.getAge());
    			field.put("email", person.getEmail());
    			field.put("medications", person.getMedications());
    			field.put("allergies", person.getAllergies());
    			response.add(field);
    		}
    	}
    	node.set("personInfo", mapper.convertValue(response, JsonNode.class));
    	return node;
	}
	
	public JsonNode buildPhoneAlert(int stationNumber){
    	ObjectNode node = mapper.createObjectNode();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		Set<String> phones = new HashSet<String>();
		for (PersonMedicalrecordFirestation person : linkedEntities) {
    		if (person.getStation() == stationNumber) {
    			phones.add(person.getPhone());
    		}
    	}
		node.set("phones", mapper.convertValue(phones, JsonNode.class));
    	return node;
	}
	
	public JsonNode buildCommunityEmailAlert(String city) {
    	ObjectNode node = mapper.createObjectNode();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		Set<String> emails = new HashSet<String>();
		for (PersonMedicalrecordFirestation person : linkedEntities) {
    		if (person.getCity().equals(city)) {
    			emails.add(person.getEmail());
    		}
    	}
		node.set("emails", mapper.convertValue(emails, JsonNode.class));
    	return node;
	}
	
	public JsonNode buildChildAlert(String address){
    	ObjectNode node = mapper.createObjectNode();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		List<PersonMedicalrecordFirestation> household = ListUtils.select(linkedEntities, p -> p.getAddress().equals(address));
		List<PersonMedicalrecordFirestation> children = ListUtils.select(household, p -> p.isChild()==true);
		List<Map<String,String>> childrenMaps = new ArrayList<Map<String,String>>();
		for (PersonMedicalrecordFirestation child : children) {
			Map<String,String> childrenMap = new LinkedHashMap<String,String>();
			childrenMap.put("firstName", child.getFirstName());
			childrenMap.put("lastName", child.getLastName());
			childrenMap.put("age", child.getAge().toString());
			childrenMaps.add(childrenMap);
		}
		node.set("Children", mapper.convertValue(childrenMaps, JsonNode.class));
		List<PersonMedicalrecordFirestation> adults = ListUtils.select(household, p -> p.isChild()==false);
		List<Map<String,String>> adultMaps = new ArrayList<Map<String,String>>();
		for (PersonMedicalrecordFirestation adult : adults) {
			Map<String,String> adultMap = new LinkedHashMap<String,String>();
			adultMap.put("firstName", adult.getFirstName());
			adultMap.put("lastName", adult.getLastName());
			adultMap.put("phone", adult.getPhone());
			adultMap.put("email", adult.getEmail());
			adultMaps.add(adultMap);
		}
		node.set("household members", mapper.convertValue(adultMaps, JsonNode.class));
		return node;
	}
	
	public JsonNode buildFirestationAlert(int stationNumber) {
    	ObjectNode node = mapper.createObjectNode();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		List<Map<String,String>> response = new ArrayList<Map<String,String>>();
		Integer nbChild=0, nbAdult=0;
		for (PersonMedicalrecordFirestation person : linkedEntities) {
			if (person.getStation()==stationNumber) {
				Map<String,String> field = new HashMap<String,String>();
				field.put("firstName", person.getFirstName());
				field.put("lastName", person.getLastName());
				field.put("address", person.getAddress());
				field.put("phone", person.getPhone());
				response.add(field);
				if (person.isChild()) {
					nbChild++;
				}else {
					nbAdult++;
				}
			}
		}
		node.set("person", mapper.convertValue(response, JsonNode.class));
		node.set("n° children", mapper.convertValue(nbChild, JsonNode.class));
		node.set("n° adults", mapper.convertValue(nbAdult, JsonNode.class));
		
		return node;
	}
	
	public JsonNode buildFireAlert(String address) {
    	ObjectNode node = mapper.createObjectNode();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		List<Map<String,Object>> response = new ArrayList<Map<String,Object>>();
		String station = null; 
		for (PersonMedicalrecordFirestation person : linkedEntities) {
			if (person.getAddress().equals(address)) {
				Map<String,Object> field = new LinkedHashMap<String,Object>();
				field.put("firstName", person.getFirstName());
				field.put("LastName", person.getLastName());
				field.put("phone", person.getPhone());
				field.put("age", person.getAge().toString());
				field.put("medications", person.getMedications());
				field.put("allergies", person.getAllergies());
				response.add(field);
			}
			if (station == null) {
				station = person.getStation().toString();
			}
		}
		node.set("person", mapper.convertValue(response, JsonNode.class));
		node.set("station", mapper.convertValue(station, JsonNode.class));
		return node;
	}
	
	public JsonNode buildFloodAlert(Integer[] stations) {
    	ObjectNode node = mapper.createObjectNode();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		Map<String,List<Map<String,Object>>> fieldAddress = new LinkedHashMap<String,List<Map<String,Object>>>();
		for (Integer station : stations) {
			for (PersonMedicalrecordFirestation person : linkedEntities) {
				if (station == person.getStation()) {
					if (!fieldAddress.containsKey(person.getAddress())) {
						fieldAddress.put(person.getAddress(), new ArrayList<Map<String,Object>>());
					}
					Map<String,Object> fieldInfo = new LinkedHashMap<String,Object>();
					fieldInfo.put("firstName", person.getFirstName());
					fieldInfo.put("LastName", person.getLastName());
					fieldInfo.put("phone", person.getPhone());
					fieldInfo.put("age", person.getAge().toString());
					fieldInfo.put("medications", person.getMedications());
					fieldInfo.put("allergies", person.getAllergies());
					fieldAddress.get(person.getAddress()).add(fieldInfo);
				}
			}
		}
		node.set("address", mapper.convertValue(fieldAddress, JsonNode.class));
		return node;
	}
}

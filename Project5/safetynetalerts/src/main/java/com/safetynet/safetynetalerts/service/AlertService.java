package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class AlertService {

	private static final Logger logger = LoggerFactory.getLogger(AlertService.class);
	
	@Autowired
	EntitiesService entities;
	
	
	//--------------------PhoneAlert---------------------
	
	
	//PhoneAlert,
	public List<Firestation> getAddressCoveredBy(int stationNumber){
		logger.info("getAddressCoveredBy("+stationNumber+")");
		return ListUtils.select(entities.getFirestations(), f -> ((Firestation)f).getStation().equals(stationNumber));
	}
		
	//PhoneAlert,ChildAlert
	public List<Person> getResidents(String address){
		logger.info("getInhabitants("+address+")");
		return ListUtils.select(entities.getPersons(),p -> ((Person)p).getAddress().equals(address));
	}
	
	public List<Person> getResidentsCoveredBy(List<Firestation> stations){
		List<Person> persons = new ArrayList<Person>();
		for (Firestation station : stations) {
			persons.addAll(getResidents(station.getAddress()));
		}
		return persons;
	}
	
	public List<Person> getResidents(List<String> addresses){
		List<Person> residents = new ArrayList<Person>();
		for (String address : addresses) {
			residents.addAll(ListUtils.select(entities.getPersons(),p -> ((Person)p).getAddress().equals(address)));
		}
		return residents;
	}
	
		
	//----------------EmailCityAlert--------------------
	
	public List<Person> getCommunity(String city){
		logger.info("getCommunity("+city+")");
		return ListUtils.select(entities.getPersons(),p -> ((Person)p).getCity().equals(city));
	}
	
	//---------------ChildAlert--------------------
	public List<Person> minorPersonsFrom(List<Person> persons){
		List<Person> children = new ArrayList<Person>();
		for (Person person: persons) {
			if (getAgeOf(person)<18) {
				children.add(person);
			}
		}
		return children;
	}
	
	public int getAgeOf(Person person){
		Medicalrecord medicRecords = entities.medicalRecordsOf(person.getFirstName(),person.getLastName()).get(0);
		return medicRecords.age();
	}
	/*
	public List<Children> getChildrenFrom(List<Person> minorPersons){
		List<Children> children = new ArrayList<Children>();
		for (Person child: minorPersons) {
			children.add(new Children(child.getFirstName(),child.getLastName(),getAgeOf(child)));
		}
		return children;
	}
	
	public List<Adults> getAdultsFromSub(List<Person> persons,List<Person> minorPersons){
		List<Person> majorPersons = ListUtils.subtract(minorPersons, persons);
		List<Adults> adults = new ArrayList<Adults>();
		for (Person adult : majorPersons) {
			adults.add(new Adults(adult.getFirstName(),adult.getLastName(),adult.getPhone()));
		}
		return adults;
	}
	*/
	
}

package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.AlertCommandLineController;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class EntitiesService {

	private static final Logger logger = LoggerFactory.getLogger(EntitiesService.class);
	
	@Autowired
	EntitiesContainer entities;
	
	public void showEntities() {
		logger.info("showEntities()");
		System.out.println(entities.toString());
	}
	
	public List<Person> getPersons() {
		logger.info("getPersons()");
		return entities.getPersons();
	}
	
	//Essai
	public Collection<Firestation> getStationUnder(int number){
		logger.info("getStationUnder("+number+")");
		ArrayList<Firestation> res = new ArrayList<Firestation>(entities.getFirestations());
		return CollectionUtils.filter(res, f -> ((Firestation)f).getStation() < number);
	}
	
	public Person getPerson(String firstName, String lastName) {
		logger.info("getPerson("+firstName+","+lastName+")");
		
		for (Person person : getPersons()) {
			//System.out.println(person);
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
		//ArrayList<Person> res = new ArrayList<Person>(getPersons());
		//return CollectionUtils.filter(res, p -> (((Person)p).getFirstName()==firstName)&&(((Person)p).getLastName()==lastName));
		/*return CollectionUtils.filter(getPersons(), new Predicate<Person>() {
			@Override
			public boolean evaluate(Person p) {
				return p.getFirstName()==firstName && p.getLastName()==lastName;
			}
		});*/
	}
}

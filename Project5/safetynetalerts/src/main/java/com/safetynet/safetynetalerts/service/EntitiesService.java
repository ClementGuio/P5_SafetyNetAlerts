package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cglib.core.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.AlertCommandLineController;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;

@Service
public class EntitiesService {

	private static final Logger logger = LoggerFactory.getLogger(EntitiesService.class);
	
	@Autowired
	EntitiesContainer entities;
	
	//------------------------------------
	
	
	//-----------------------------------
	
	public void showEntities() {
		logger.info("showEntities()");
		System.out.println(entities.toString());
	}
	
	public List<Person> getPersons() {
		logger.info("getPersons()");
		return entities.getPersons();
	}
	
	public List<Firestation> getFirestations(){
		logger.info("getFirestations()");
		return entities.getFirestations();
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
	}
	
	//------------------MedicalRecord----------------
	public List<Medicalrecord> medicalRecordsOf(String firstName, String lastName) {
		Predicate<Medicalrecord> firstNamePredicate = m -> ((Medicalrecord)m).getFirstName().equals(firstName);
		Predicate<Medicalrecord> lastNamePredicate = m -> ((Medicalrecord)m).getLastName().equals(lastName);
		return ListUtils.select(entities.getMedicalrecords(),PredicateUtils.andPredicate(firstNamePredicate, lastNamePredicate));
	}
	public List<Medicalrecord> medicalRecordsOf(Person person) {
		Predicate<Medicalrecord> firstNamePredicate = m -> ((Medicalrecord)m).getFirstName().equals(person.getFirstName());
		Predicate<Medicalrecord> lastNamePredicate = m -> ((Medicalrecord)m).getLastName().equals(person.getLastName());
		return ListUtils.select(entities.getMedicalrecords(),PredicateUtils.andPredicate(firstNamePredicate, lastNamePredicate));
	}
	
	//Essai
	public Collection<Firestation> getStationUnder(int number){
		logger.info("getStationUnder("+number+")");
		ArrayList<Firestation> res = new ArrayList<Firestation>(entities.getFirestations());
		return CollectionUtils.filter(res, f -> ((Firestation)f).getStation() < number);
	}
	
}

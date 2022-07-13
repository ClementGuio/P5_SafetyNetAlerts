package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.safetynet.safetynetalerts.controller.AlertCommandLineController;
import com.safetynet.safetynetalerts.exception.IllegalRequestException;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;
//TODO: créer EntityLinker
//TODO : factoriser getPerson(fistName,lastName)
//TODO : utiliser beanUtils.copyproperties pour set(get())
@Service
public class EntitiesService {

	private static final Logger logger = LoggerFactory.getLogger(EntitiesService.class);
	
	@Autowired
	EntitiesContainer entities;
	
	
	public void showEntities() {
		logger.info("showEntities()");
		System.out.println(entities.toString());
	}
	
	public void showPersons() {
		System.out.println(entities.getPersons());
	}
	
	public void showMedicalrecords() {
		System.out.println(entities.getMedicalrecords());
	}
	
	public void showFirestations() {
		System.out.println(entities.getFirestations());
	}
	
//CRUD	
	public void removeMedicalrecord(String firstName, String lastName) throws IllegalRequestException {
		Medicalrecord recordToRemove = null;
		Iterator<Medicalrecord> iter = entities.getMedicalrecords().iterator();
		boolean isFound = false;
		
		while (iter.hasNext() && !isFound) {
			Medicalrecord record = iter.next();
			if (record.getFirstName().equals(firstName) && record.getLastName().equals(lastName)) {
				recordToRemove = record;
				isFound = true;
			}
		}
		
		if (!isFound) {
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			entities.getMedicalrecords().remove(recordToRemove);
		}
	}

	public void updateMedicalrecord(Medicalrecord record) throws IllegalRequestException{
		String firstName = record.getFirstName();
		String lastName = record.getLastName();
		
		Iterator<Medicalrecord> iter = entities.getMedicalrecords().iterator();
		boolean isFound = false;
		while (iter.hasNext() && !isFound) {
			Medicalrecord mr = iter.next();
			if (mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				mr.setBirthdate(record.getBirthdate());
				mr.setMedications(record.getMedications());
				mr.setAllergies(record.getAllergies());
				isFound = true;
			}
		}
		if (!isFound) {
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}
	}

	public void addMedicalrecord(Medicalrecord record) {
		entities.getMedicalrecords().add(record);
	}

	public void removeFirestation(String address, int stationNumber) throws IllegalRequestException{
		logger.info("removeFirestation : "+address+", "+stationNumber);
		Firestation stationToRemove = null;
		Iterator<Firestation> iter = entities.getFirestations().iterator();
		boolean isFound = false;
		
		while (iter.hasNext() && !isFound) {
			Firestation station = iter.next();
			if (station.getAddress().equals(address) && station.getStation()==stationNumber) {
				stationToRemove = station;
				isFound = true;
			}
		}
		
		if (!isFound) {
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			entities.getFirestations().remove(stationToRemove);
		}
	}

	public void updateFirestation(Firestation station) throws IllegalRequestException{
		logger.info("updateFirestation : "+station);
		String address = station.getAddress();
		
		Iterator<Firestation> iter = entities.getFirestations().iterator();
		boolean isFound = false;
		while (iter.hasNext() && !isFound) {
			Firestation fs = iter.next();
			if (fs.getAddress().equals(address)) {
				fs.setStation(station.getStation());
				isFound = true;
			}
		}
		if (!isFound) {
			throw new IllegalRequestException("Submitted Firestation doesn't match with any existing Firestation.");
		}
	}

	public void addFirestation(Firestation station) {
		logger.info("addFirestation : "+station);
		entities.getFirestations().add(station);
		
	}

	public void removePerson(String firstName, String lastName) throws IllegalRequestException {
		Person personToRemove = null;
		Iterator<Person> iter = entities.getPersons().iterator();
		boolean isFound = false;
		
		while (iter.hasNext() && !isFound) {
			Person person = iter.next();
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				personToRemove = person;
				isFound = true;
			}
		}
		
		if (!isFound) {
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			entities.getPersons().remove(personToRemove);
		}
	}

	public void updatePerson(Person person) throws IllegalRequestException{
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
		
		Iterator<Person> iter = entities.getPersons().iterator();
		boolean isFound = false;
		while (iter.hasNext() && !isFound) {
			Person currentPerson = iter.next();
			if (currentPerson.getFirstName().equals(firstName) && currentPerson.getLastName().equals(lastName)) {
				currentPerson.setAddress(person.getAddress());
				currentPerson.setCity(person.getCity());
				currentPerson.setEmail(person.getEmail());
				currentPerson.setPhone(person.getPhone());
				currentPerson.setZip(person.getZip());
				isFound = true;
			}
		}
		if (!isFound) {
			throw new IllegalRequestException("Submitted Person doesn't match with any existing Person.");
		}
		
		/*
		for (Person p : entities.getPersons()) {
			if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
				p.setAddress(person.getAddress());
				p.setCity(person.getCity());
				p.setEmail(person.getEmail());
				p.setPhone(person.getPhone());
				p.setZip(person.getZip());
				return;
			}
		}
		*/
	}
//...................................................................
	public void addPerson(Person person) {
		entities.getPersons().add(person);
	}

	public String getAddressOf(String firstName, String lastName) {
		for (Person person : entities.getPersons()) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return person.getAddress();
			}
		}
		return null;
	}
	
}

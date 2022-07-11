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
//TODO: séparer CRUDService & EntitiesService
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
	public void removeMedicalrecord(String firstName, String lastName) {
		Medicalrecord recordToRemove = null;
		for (Medicalrecord record : entities.getMedicalrecords()) {
			if (record.getFirstName().equals(firstName) && record.getLastName().equals(lastName)) {
				recordToRemove = record;
				break;
			}
		}
		entities.getMedicalrecords().remove(recordToRemove);
	}

	public void updateMedicalrecord(Medicalrecord record) {
		String firstName = record.getFirstName();
		String lastName = record.getLastName();
		for (Medicalrecord mr : entities.getMedicalrecords()) {
			if (mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)) {
				mr.setBirthdate(record.getBirthdate());
				mr.setMedications(record.getMedications());
				mr.setAllergies(record.getAllergies());
				return;
			}
		}
	}

	public void addMedicalrecord(Medicalrecord record) {
		entities.getMedicalrecords().add(record);
	}

	public void removeFirestation(Firestation station) {
		logger.info("removeFirestation : "+station.toString());
		entities.getFirestations().remove(station);
	}

	public void updateFirestation(Firestation station) {
		logger.info("updateFirestation : "+station.toString());
		String address = station.getAddress();
		for (Firestation fs : entities.getFirestations()) {
			if (fs.getAddress().equals(address)) {
				fs.setStation(station.getStation());
				return;
			}
		}
	}

	public void addFirestation(Firestation station) {
		logger.info("addFirestation : "+station.toString());
		entities.getFirestations().add(station);
		
	}

	public void removePerson(String firstName, String lastName) {
		Person personToRemove = null;
		for (Person person : entities.getPersons()) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)){
				System.out.println("Found :"+firstName+", "+lastName);
				personToRemove = person;
				break;
			}
		}
		entities.getPersons().remove(personToRemove);
	}

	public void updatePerson(Person person) {
		String firstName = person.getFirstName();
		String lastName = person.getLastName();
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
	}

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

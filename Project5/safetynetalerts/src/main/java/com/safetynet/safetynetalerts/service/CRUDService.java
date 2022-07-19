package com.safetynet.safetynetalerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.exceptions.IllegalRequestException;
import com.safetynet.safetynetalerts.exceptions.MissingEntitiesException;
import com.safetynet.safetynetalerts.interfaces.ICrudService;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;

@Service
public class CRUDService implements ICrudService {

	private static final Logger logger = LoggerFactory.getLogger(CRUDService.class);
	
	@Autowired
	EntitiesContainer container;
	
	@Autowired
	LinkedEntitiesContainer linkedEntities;
	
	public void removeMedicalrecord(String firstName, String lastName) throws IllegalRequestException {
		Medicalrecord recordToRemove = container.getMedicalrecordOf(firstName,lastName);
		if (recordToRemove == null) {
			logger.error("Missing Medicalrecord -> firstName:"+firstName+", lastName:"+lastName);
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			container.getMedicalrecords().remove(recordToRemove);
		}
	}

	public void updateMedicalrecord(Medicalrecord record) throws IllegalRequestException{
		Medicalrecord recordToUpdate = container.getMedicalrecordOf(record.getFirstName(), record.getLastName());
		if (recordToUpdate == null) {
			logger.error("Missing Medicalrecord -> "+record);
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			recordToUpdate.setBirthdate(record.getBirthdate());
			recordToUpdate.setMedications(record.getMedications());
			recordToUpdate.setAllergies(record.getAllergies());
		}
	}

	public void addMedicalrecord(Medicalrecord record) {
		container.getMedicalrecords().add(record);
	}

	public void removeFirestation(String address) throws IllegalRequestException{
		Firestation stationToRemove = container.getFirestationOf(address);
		if (stationToRemove == null) {
			logger.error("Missing Firestation -> address:"+address);
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			container.getFirestations().remove(stationToRemove);
		}
	}

	public void updateFirestation(Firestation station) throws IllegalRequestException{
		Firestation stationToUpdate = container.getFirestationOf(station.getAddress());
		if (stationToUpdate == null) {
			logger.error("Missing Firestation -> "+station);
			throw new IllegalRequestException("Submitted Firestation doesn't match with any existing Firestation.");
		}else {
			stationToUpdate.setStation(station.getStation());
		}
	}

	public void addFirestation(Firestation station) {
		container.getFirestations().add(station);
	}

	public void removePerson(String firstName, String lastName) throws IllegalRequestException {
		Person personToRemove = container.getPerson(firstName, lastName);
		if (personToRemove == null) {
			logger.error("Missing Person -> firstName:"+firstName+", lastName:"+lastName);
			throw new IllegalRequestException("Submitted Medicalrecord doesn't match with any existing Medicalrecord.");
		}else {
			container.getPersons().remove(personToRemove);
		}
	}

	public void updatePerson(Person person) throws IllegalRequestException{
		Person personToUpdate = container.getPerson(person.getFirstName(), person.getLastName());
		if (personToUpdate == null) {
			logger.error("Missing Person -> "+person);
			throw new IllegalRequestException("Submitted Person doesn't match with any existing Person.");
		}else {
			personToUpdate.setAddress(person.getAddress());
			personToUpdate.setCity(person.getCity());
			personToUpdate.setEmail(person.getEmail());
			personToUpdate.setPhone(person.getPhone());
			personToUpdate.setZip(person.getZip());
		}
	}

	public void addPerson(Person person) throws MissingEntitiesException{
		Medicalrecord record = container.getMedicalrecordOf(person);
		Firestation station = container.getFirestationOf(person);
		if (record==null || station==null) {
			logger.error("Missing entities -> Medicalrecord:"+(record==null)+", Firestation:"+(station==null));
			throw new MissingEntitiesException("There is missing entity. To add a Person, there must be a Medicalrecord and a Firestation related to it. Please add them before adding Person.");
		}else {
			container.getPersons().add(person);
			linkedEntities.getLinkedEntities().add(new PersonMedicalrecordFirestation(person,container.getMedicalrecordOf(person),container.getFirestationOf(person)));
		}
	}
}

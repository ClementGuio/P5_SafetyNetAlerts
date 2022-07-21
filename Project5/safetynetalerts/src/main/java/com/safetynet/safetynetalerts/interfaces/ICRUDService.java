package com.safetynet.safetynetalerts.interfaces;

import com.safetynet.safetynetalerts.exceptions.IllegalRequestException;
import com.safetynet.safetynetalerts.exceptions.MissingEntitiesException;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;

public interface ICRUDService {

	void removeMedicalrecord(String firstName, String lastName) throws IllegalRequestException;

	void updateMedicalrecord(Medicalrecord record) throws IllegalRequestException;

	void addMedicalrecord(Medicalrecord record);

	void removeFirestation(String address) throws IllegalRequestException;

	void updateFirestation(Firestation station) throws IllegalRequestException;

	void addFirestation(Firestation station);

	void removePerson(String firstName, String lastName) throws IllegalRequestException;

	void updatePerson(Person person) throws IllegalRequestException;

	void addPerson(Person person) throws MissingEntitiesException;

}
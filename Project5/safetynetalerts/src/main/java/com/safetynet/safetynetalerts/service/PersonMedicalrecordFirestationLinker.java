package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;

public class PersonMedicalrecordFirestationLinker {

	@Autowired
	EntitiesContainer entities;
	
	
	public List<PersonMedicalrecordFirestation> linkEntities() {
		List<PersonMedicalrecordFirestation> linkedEntities = new ArrayList<PersonMedicalrecordFirestation>();
		for (Person person : entities.getPersons()) {
			System.out.println(person);
			System.out.println(entities.getMedicalrecordOf(person));
			System.out.println(entities.getFirestationOf(person));
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			linkedEntities.add(new PersonMedicalrecordFirestation(person,entities.getMedicalrecordOf(person),entities.getFirestationOf(person)));
		}
		return linkedEntities;
	}
}

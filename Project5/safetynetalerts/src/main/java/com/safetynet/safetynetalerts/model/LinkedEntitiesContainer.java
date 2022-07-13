package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinkedEntitiesContainer {
	
	@Autowired
	EntitiesContainer entities;
	
	private List<PersonMedicalrecordFirestation> linkedEntities;
	
	@Override
	public String toString() {
		String res="";
		for (PersonMedicalrecordFirestation person: linkedEntities) {
			res+=person.toString()+"\n";
		}
		return res;
	}
	
	public void setLinkedEntities(List<PersonMedicalrecordFirestation> linkedEntities) {
		this.linkedEntities = linkedEntities;
	}
	
	public List<PersonMedicalrecordFirestation> getLinkedEntities(){
		return linkedEntities;
	}
	//TODO : créer Exception perso pr la cohérence des données
	//TODO : add clearLinkedEntities() ?
	public void linkEntities() {
		List<PersonMedicalrecordFirestation> linkedEntities = new ArrayList<PersonMedicalrecordFirestation>(); 
		for (Person person : entities.getPersons()) {
			linkedEntities.add(new PersonMedicalrecordFirestation(person,entities.getMedicalrecordOf(person),entities.getFirestationOf(person)));
		}
		this.linkedEntities = linkedEntities;
	}
}

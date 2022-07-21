package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.exceptions.MissingEntitiesException;
import com.safetynet.safetynetalerts.interfaces.EntityLinker;

@Component
public class LinkedEntitiesContainer implements EntityLinker{
	
	private static final Logger logger = LoggerFactory.getLogger(LinkedEntitiesContainer.class);
	
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
 
	public void linkEntities() throws MissingEntitiesException{
		logger.info("linkEntities() starts!");
		List<PersonMedicalrecordFirestation> linkedEntities = new ArrayList<PersonMedicalrecordFirestation>(); 
		for (Person person : entities.getPersons()) {
			Medicalrecord record = entities.getMedicalrecordOf(person);
			Firestation station = entities.getFirestationOf(person);
			if (record == null || station ==null) {
				logger.error("FAIL Person -> "+person+"\n"
						+ "Missing entities -> Medicalrecord:"+(record==null)+", Firestation:"+(station==null));
				throw new MissingEntitiesException("There is missing entities in the data. Every Person must have a Firestation and a Medicalrecord related to it.");
			}
			
			linkedEntities.add(new PersonMedicalrecordFirestation(person,record,station));
		}
		this.linkedEntities = linkedEntities;
	}
}

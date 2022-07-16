package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"persons","firestations","medicalrecords"})
@Generated("jsonschema2pojo")
public class EntitiesContainer{
	//TODO : comprendre pourquoi static est nécessaire
	@JsonProperty("persons")
	private static List<Person> persons = new ArrayList<Person>();
	@JsonProperty("firestations")
	private static List<Firestation> firestations = new ArrayList<Firestation>();
	@JsonProperty("medicalrecords")
	private static List<Medicalrecord> medicalrecords = new ArrayList<Medicalrecord>();
	
	@Override
	public String toString() {
		String str = "persons :\n";
		for (Person person : persons) {
			str += person.toString();
		}
		str += "firestations :\n";
		for (Firestation firestation : firestations) {
			str += firestation.toString();
		}
		str += "medicalrecords :\n";
		for (Medicalrecord medicalrecord : medicalrecords) {
			str += medicalrecord.toString();
		}
		return str;
	}
	
	public Person getPerson(String firstName, String lastName) {
		return IterableUtils.find(persons, p -> ((Person)p).getFirstName().equals(firstName) && p.getLastName().equals(lastName));
	}
	
	public Firestation getFirestationOf(Person person) {
		return IterableUtils.find(firestations, f -> f.getAddress().equals(person.getAddress()));
	}
	
	public Firestation getFirestationOf(String address) {
		return IterableUtils.find(firestations, f -> f.getAddress().equals(address));
	}
	
	public Medicalrecord getMedicalrecordOf(Person person) {
		return IterableUtils.find(medicalrecords, r -> ((Medicalrecord)r).getFirstName().equals(person.getFirstName()) 
				&& r.getLastName().equals(person.getLastName()));
	}
	
	public Medicalrecord getMedicalrecordOf(String firstName, String lastName) {
		return IterableUtils.find(medicalrecords, r -> ((Medicalrecord)r).getFirstName().equals(firstName) 
				&& r.getLastName().equals(lastName));
	}
	
	@JsonProperty("persons")
	public List<Person> getPersons() {
		return persons;
	}

	@JsonProperty("persons")
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@JsonProperty("firestations")
	public List<Firestation> getFirestations() {
		return firestations;
	}

	@JsonProperty("firestations")
	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}

	@JsonProperty("medicalrecords")
	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}

	@JsonProperty("medicalrecords")
	public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
}

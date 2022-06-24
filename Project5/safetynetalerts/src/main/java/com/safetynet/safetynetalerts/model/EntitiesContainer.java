package com.safetynet.safetynetalerts.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.PostConstruct;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//TODO : rename bind/bound -> link/linked
//TODO : séparer linkedEntities de entitiesContainer


@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"persons","firestations","medicalrecords"})
@Generated("jsonschema2pojo")
public class EntitiesContainer implements EntityLinker{
	
	private List<PersonMedicalrecordFirestation> linkedEntities;
	
	@JsonProperty("persons")
	private static List<Person> persons = null;
	@JsonProperty("firestations")
	private static List<Firestation> firestations = null;
	@JsonProperty("medicalrecords")
	private static List<Medicalrecord> medicalrecords = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
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
	
	public void linkEntities() {
		List<PersonMedicalrecordFirestation> linkedEntities = new ArrayList<PersonMedicalrecordFirestation>(); 
		for (Person person : persons) {
			System.out.println(person);
			linkedEntities.add(new PersonMedicalrecordFirestation(person,getMedicalrecordOf(person),getFirestationOf(person)));
		}
		this.linkedEntities = linkedEntities;
	}
	
	public List<PersonMedicalrecordFirestation> getLinkedEntities(){
		return linkedEntities;
	}
	
	public Firestation getFirestationOf(Person person) {
		return IterableUtils.find(firestations, f -> f.getAddress().equals(person.getAddress()));
	}
	
	public Medicalrecord getMedicalrecordOf(Person person) {
		Predicate<Medicalrecord> firstNamePredicate = m -> ((Medicalrecord)m).getFirstName().equals(person.getFirstName());
		Predicate<Medicalrecord> lastNamePredicate = m -> ((Medicalrecord)m).getLastName().equals(person.getLastName());
		return IterableUtils.find(medicalrecords, PredicateUtils.andPredicate(firstNamePredicate, lastNamePredicate));
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

package com.safetynet.safetynetalerts.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class PersonService {

	@Autowired 
	EntitiesService entities;
	/*
	public Iterable<Person> getPerson(String firstName, String lastName){
		List<Person> persons = data.getPersons().clone();
		return CollectionUtils.filter(data.getPersons(), p -> ((((Person) p).getFirstName() == firstName) && (((Person) p).getLastName() == lastName)));
	}*/
	/*
	public List<Person> getChild(String address){
		return null;
	}
	
	public List<Person> getEMailFromInhabitants(String city){
		return null;
	}
	
	public List<String> getPhoneNumberAroundFirestation(int station ){
		return null;
	}
	
	public List<Person> getPersons(){
		return entities.getPersons();
	}
	
	public Collection<Person> getPerson(String firstName, String lastName) {
		return entities.getPerson(firstName,lastName);
	}*/
}

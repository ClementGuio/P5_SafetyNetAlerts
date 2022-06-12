package com.safetynet.safetynetalerts.repository;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.DataAlert;
import com.safetynet.safetynetalerts.model.Person;

//TODO : classes Deserializer, Serializer????

@Component
public class DataAlertReaderJSON {
	private ObjectMapper objectMapper; 
	
	public DataAlertReaderJSON() {
		this.objectMapper = new ObjectMapper();	
	}
	
	public void readAndStore() throws Exception {
		System.out.println("ReadAndStore");
		/*String json = "{ \"persons\" :"
				+ "[ { \"firstName\" : \"John\", \"lastName\" : \"Boyd\" },"
				+ "{\"firstName\" : \"Jack\", \"lastName\" : \"DuCul\"}"
				+ "]}";
		*/
		//NON
		/*Persons persons = objectMapper.readValue(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"),
					Persons.class);*/
		//Essai: Liste de Person
		//List<Person> persons = objectMapper.readValue(json, new TypeReference<List<Person>>(){});

		//Essai: Persons
		//Persons persons = objectMapper.readValue(json, Persons.class);
 
		//Ok pour Persons only
		//FileInputStream data = new FileInputStream("/home/eletelekeli/Documents/OpenClassrooms/Projets_DAJava/P5/dataPersons.json");
		//Persons persons = objectMapper.readValue(data,Persons.class);
		
		//TODO : traitement générique pour récupérer tous les objets
		DataAlert data = objectMapper.readValue(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"), DataAlert.class);
		
		System.out.println(data);
		/*
		Person person = objectMapper.readValue(json,Person.class);
		System.out.println("Person : "+person);
		*/
	}

}

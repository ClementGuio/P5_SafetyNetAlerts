package com.safetynet.safetynetalerts.repository;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.DataContainer;
import com.safetynet.safetynetalerts.model.Person;

//TODO : classes Deserializer, Serializer????
//TODO : change types (String->LocalDate, ...)
//TODO : try to write Json
//TODO : SOLID in model
//TODO : find a good name for DataAlert and dataAlertReaderJSON classes
//TODO : begin to create RESTController and try some basic requests
//TODO : priority order for the TODO list

@Component
public class DataJSONDeserializer {
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	
	public void readAndStore() throws Exception {
		DataContainer data = objectMapper.readValue(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"), 
				DataContainer.class);
		System.out.println(data);
	}

}

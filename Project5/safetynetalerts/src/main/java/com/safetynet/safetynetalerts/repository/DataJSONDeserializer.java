package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Person;

@Component
public class DataJSONDeserializer {
	//TODO: clean 
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Autowired
	private EntitiesContainer data;
	
	public void readAndStore(URL src) throws Exception {
		data = objectMapper.readValue(src, EntitiesContainer.class);
		//System.out.println(data);
	}
	
	public void readAndStore(File src) throws Exception {
		data = objectMapper.readValue(src, EntitiesContainer.class);
		//System.out.println(data);
	}

}

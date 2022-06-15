package com.safetynet.safetynetalerts.repository;

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
	
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Autowired
	private EntitiesContainer data;
	
	public void readAndStore() throws Exception {
		data = objectMapper.readValue(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"), 
				EntitiesContainer.class);
		//System.out.println(data);
	}

}

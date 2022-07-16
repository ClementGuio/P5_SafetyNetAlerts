package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.EntitiesContainer;

@Component
public class DataJSONDeserializer {
	@Autowired
	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Autowired
	private EntitiesContainer data;
	
	public void readAndStore(URL src) throws IOException, StreamReadException, DatabindException {
		data = objectMapper.readValue(src, EntitiesContainer.class);
	}

	public void readAndStore(File src) throws IOException, StreamReadException, DatabindException {
		data = objectMapper.readValue(src, EntitiesContainer.class);
	}

}

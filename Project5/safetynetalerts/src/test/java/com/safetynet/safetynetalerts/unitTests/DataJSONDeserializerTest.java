package com.safetynet.safetynetalerts.unitTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.repository.DataJSONDeserializer;

public class DataJSONDeserializerTest {

	@Autowired
	DataJSONDeserializer deser;
	
	@Autowired
	EntitiesContainer entities;
	
	@Test
	public void testReadAndStore() throws Exception{
		deser.readAndStore(new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json"));
		assertNotNull(entities.getPersons());
	}
}

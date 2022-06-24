package com.safetynet.safetynetalerts.repository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;


@Component
public class DataJSONSerializer {
	
	@Autowired
	EntitiesContainer entities;
	    
    public JsonNode phoneAlert(int station) throws Exception {
    	System.out.println("phoneAlert");
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectNode node = mapper.createObjectNode();
    	entities.linkEntities();
		List<PersonMedicalrecordFirestation> linkedEntities = entities.getLinkedEntities();
		for (PersonMedicalrecordFirestation linkedEntity : linkedEntities) {
			System.out.println(linkedEntity);
		}
		/*for (PersonMedicalrecordFirestation person : linkedEntities) {
    		if (person.getStation() == station) {
    			node.set("phone", mapper.convertValue(person.getPhone(), JsonNode.class));
    		}
    	}*/
    	return node;
    }
    
    
    

}

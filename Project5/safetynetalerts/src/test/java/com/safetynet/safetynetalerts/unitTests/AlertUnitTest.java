package com.safetynet.safetynetalerts.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;
import com.safetynet.safetynetalerts.service.AlertBuilder;

@RunWith(JUnitPlatform.class)
public class AlertUnitTest {

	@Mock
	LinkedEntitiesContainer linkedEntities;
	@InjectMocks
	AlertBuilder alertBuilder;
	
	@BeforeEach
	public void setUp() {
		alertBuilder = new AlertBuilder();
	}
	
	@Test
	public void testCommunityEmail() {
		//LinkedEntitiesContainer mock = org.mockito.Mockito.mock(LinkedEntitiesContainer.class);
		List<PersonMedicalrecordFirestation> entry = new ArrayList<PersonMedicalrecordFirestation>();
		Person p1 = new Person("firstname","lastname","1 St","Community","99999","123-123-123","community@email.com");
		Firestation s1 = new Firestation("1 St",1);
		Medicalrecord r1 = new Medicalrecord("firstname","lastname",LocalDate.now(),new ArrayList<String>(),new ArrayList<String>());
		entry.add(new PersonMedicalrecordFirestation(p1,r1,s1));
		System.out.println(entry);
		when(linkedEntities.getLinkedEntities()).thenReturn(entry);
		
		JsonNode response = alertBuilder.buildCommunityEmailAlert("Community");
		List<JsonNode> fields = response.findValues("email");
		
		assertTrue(fields.contains("community@email.com"));
	}
	
	@Test
	public void testFuckYou() {
		assertEquals(1,1);
	}
}

package com.safetynet.safetynetalerts.integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest(args = "test")
@AutoConfigureMockMvc
public class CrudRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	EntitiesContainer container;
	
	
	//POST
	@Test
	public void testPostPerson() throws Exception{
		Person person = new Person();
		person.setFirstName("CrudRestControllerTest");
		person.setLastName("PostPerson");
		person.setAddress("1 address");
		person.setCity("Town");
		person.setEmail("address@email.com");
		person.setPhone("111-111-111");
		person.setZip("12345");
		
		mvc.perform( MockMvcRequestBuilders 
				.post("/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{ \"firstName\":\"CrudRestControllerTest\", \"lastName\":\"PostPerson\", \"address\":\"1 address\", \"city\":\"Town\", "
			    		+ "\"zip\":\"12345\", \"phone\":\"111-111-111\", \"email\":\"address@email.com\" }")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertTrue(container.getPersons().contains(person));
	}
	@Test
	public void testPostMedicalrecord() throws Exception{
		Medicalrecord record = new Medicalrecord();
		record.setFirstname("CrudRestControllerTest");
		record.setLastName("PostMedicalrecord");
		record.setBirthdate(LocalDate.of(1990,1,1));
		record.setAllergies(new ArrayList<String>());
		record.setMedications(new ArrayList<String>());
		
		mvc.perform( MockMvcRequestBuilders 
				.post("/v1/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{ \"firstName\":\"CrudRestControllerTest\", \"lastName\":\"PostMedicalrecord\", \"birthdate\":\"01/01/1990\", \"medications\":[], \"allergies\":[] }")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertTrue(container.getMedicalrecords().contains(record));
	}
	
	@Test
	public void testPostFirestation() throws Exception{
		Firestation station = new Firestation();
		station.setAddress("testPostFirestation");
		station.setStation(1);
		
		mvc.perform( MockMvcRequestBuilders 
				.post("/v1/firestation")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{\"address\":\"testPostFirestation\",\"station\":\"1\"}")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertTrue(container.getFirestations().contains(station));			    
	}
	
	//PUT
	
	@Test
	public void testPutPerson() throws Exception{
		Person person = new Person();
		person.setFirstName("CrudRestControllerTest");
		person.setLastName("Put");
		person.setAddress("testPut");
		person.setCity("new city");
		person.setEmail("address@email.com");
		person.setPhone("111-111-111");
		person.setZip("12345");
		
		mvc.perform( MockMvcRequestBuilders 
				.put("/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{ \"firstName\":\"CrudRestControllerTest\", \"lastName\":\"Put\", \"address\":\"testPut\", \"city\":\"new city\", "
			    		+ "\"zip\":\"12345\", \"phone\":\"111-111-111\", \"email\":\"address@email.com\" }")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertTrue(container.getPersons().contains(person));
	}
	
	@Test
	public void testPutMedicalrecord() throws Exception{
		Medicalrecord record = new Medicalrecord();
		record.setFirstname("CrudRestControllerTest");
		record.setLastName("Put");
		record.setBirthdate(LocalDate.of(1990, 1, 1));
		record.setAllergies(new ArrayList<String>());
		record.setMedications(new ArrayList<String>());
		
		mvc.perform( MockMvcRequestBuilders 
				.put("/v1/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{ \"firstName\":\"CrudRestControllerTest\", \"lastName\":\"Put\", \"birthdate\":\"01/01/1990\", "
			    		+ "\"medications\":[],\"allergies\":[] }")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertTrue(container.getMedicalrecords().contains(record));
	}
	
	@Test
	public void testPutFirestation() throws Exception{
		Firestation station = new Firestation();
		station.setAddress("testPut");
		station.setStation(99);
		
		mvc.perform( MockMvcRequestBuilders 
				.put("/v1/firestation")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{\"address\":\"testPut\",\"station\":\"99\"}")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertTrue(container.getFirestations().contains(station));
	}
	
	@Test
	public void testPutPersonWithUnknownPerson() throws Exception{
		Person person = new Person();
		person.setFirstName("CrudRestControllerTest");
		person.setLastName("PutPersonWithUnknownPerson");
		person.setAddress("address");
		person.setCity("Town");
		person.setEmail("address@email.com");
		person.setPhone("111-111-111");
		person.setZip("12345");
		
		mvc.perform( MockMvcRequestBuilders 
				.put("/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{ \"firstName\":\"CrudRestController\", \"lastName\":\"PutPersonWithUnknownPerson\", \"address\":\"address\", \"city\":\"Town\", "
			    		+ "\"zip\":\"12345\", \"phone\":\"111-111-111\", \"email\":\"address@email.com\" }")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isConflict());

		assertFalse(container.getPersons().contains(person));
	}
	
	@Test
	public void testPutMedicalrecordWithUnknownMedicalrecord() throws Exception{
		Medicalrecord record = new Medicalrecord();
		record.setFirstname("CrudRestControllerTest");
		record.setLastName("PutMedicalrecordWithUnknownMedicalrecord");
		record.setBirthdate(LocalDate.of(1990, 1, 1));
		record.setAllergies(new ArrayList<String>());
		record.setMedications(new ArrayList<String>());
		
		mvc.perform( MockMvcRequestBuilders 
				.put("/v1/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{ \"firstName\":\"CrudRestControllerTest\", \"lastName\":\"PutMedicalrecordWithUnknownMedicalrecord\", \"birthdate\":\"01/01/1990\", "
			    		+ "\"medications\":[],\"allergies\":[] }")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isConflict());
		
		assertFalse(container.getMedicalrecords().contains(record));
	}
	
	@Test
	public void testPutFirestationWithUnknownFirestation() throws Exception{
		Firestation station = new Firestation();
		station.setAddress("unknown");
		station.setStation(13);
		
		mvc.perform( MockMvcRequestBuilders 
				.put("/v1/firestation")
				.contentType(MediaType.APPLICATION_JSON)
			    .content("{\"address\":\"unknown\",\"station\":\"13\"}")
			    .accept(MediaType.APPLICATION_JSON))
			    .andExpect(MockMvcResultMatchers.status().isConflict());
		
		assertFalse(container.getFirestations().contains(station));
	}
	
	@Test
	public void testDeletePerson() throws Exception{
		Person person = new Person();
		person.setFirstName("CrudRestControllerTest");
		person.setLastName("Delete");
		person.setAddress("testPut");
		person.setCity("Ville");
		person.setEmail("address@email.com");
		person.setPhone("111-111-111");
		person.setZip("12345");
		
		mvc.perform( MockMvcRequestBuilders 
				.delete("/v1/person?firstName=CrudRestControllerTest&lastName=Delete"))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertFalse(container.getPersons().contains(person));
	}
	
	@Test
	public void testDeleteFirestation() throws Exception{
		Firestation station = new Firestation();
		station.setAddress("testDelete");
		station.setStation(11);
		
		mvc.perform( MockMvcRequestBuilders 
				.delete("/v1/firestation?address=testDelete&station=11"))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertFalse(container.getFirestations().contains(station));
	}
	
	@Test
	public void testDeleteMedicalrecord() throws Exception{
		Medicalrecord record = new Medicalrecord();
		record.setFirstname("CrudRestControllerTest");
		record.setLastName("Delete");
		record.setBirthdate(LocalDate.of(1990, 1, 1));
		record.setAllergies(new ArrayList<String>());
		record.setMedications(new ArrayList<String>());
		
		mvc.perform( MockMvcRequestBuilders 
				.delete("/v1/medicalrecord?firstName=CrudRestControllerTest&lastName=Delete"))
			    .andExpect(MockMvcResultMatchers.status().isOk());
		
		assertFalse(container.getMedicalrecords().contains(record));
	}
	
	@Test
	public void testDeletePersonWithUnknownPerson() throws Exception{
		
		mvc.perform( MockMvcRequestBuilders 
				.delete("/v1/person?firstName=CrudRestControllerTest&lastName=Unknown"))
			    .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void testDeleteFirestationWithUnknownFirestation() throws Exception{
		
		mvc.perform( MockMvcRequestBuilders 
				.delete("/v1/firestation?address=Unknown&station=11"))
			    .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void testDeleteMedicalrecordWithUnknownMedicalrecord() throws Exception{
		
		mvc.perform( MockMvcRequestBuilders 
				.delete("/v1/medicalrecord?firstName=CrudRestControllerTest&lastName=Unknwon"))
			    .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
}

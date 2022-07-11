package com.safetynet.safetynetalerts.unitTests;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.safetynet.safetynetalerts.model.LinkedEntitiesContainer;
import com.safetynet.safetynetalerts.service.AlertBuilder;

//@RunWith(SpringRunner.class)
//@WebMvcTest(AlertBuilder.class)
@AutoConfigureMockMvc
@SpringBootTest(args = "test")
public class AlertMockMVCTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testPersonInfo() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/personInfo?firstName={fistName}&lastName={lastName}","Person","Info")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"personInfo\":[{\"firstName\":\"Person\",\"lastName\":\"Info\","
						+ "\"address\":\"4 St\",\"age\":23,\"email\":\"personInfo@email.com\",\"medications\":[],\"allergies\":[]}]}"));
	}
	
	@Test
	public void testPhoneAlert() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/phoneAlert?firestation={station}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{'phones':['123-457','123-456']}"));
	}
	
	@Test
	public void testCommunityEmail() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/communityEmail?city={city}","Community")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"emails\":[\"community2@email.com\",\"community1@email.com\"]}"));
	}
	
	@Test
	public void testChildAlert() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/childAlert?address={address}","2 St")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"Children\":"
						+ "[{\"firstName\":\"Alert\",\"lastName\":\"Child1\",\"age\":\"12\"},"
						+ "{\"firstName\":\"Alert\",\"lastName\":\"Child2\",\"age\":\"11\"}],"
						+ "\"household members\":"
						+ "[{\"firstName\":\"Alert\",\"lastName\":\"Child3\",\"phone\":\"345-123\",\"email\":\"childAlert3@email.com\"},"
						+ "{\"firstName\":\"Alert\",\"lastName\":\"Child4\",\"phone\":\"345-123\",\"email\":\"childAlert4@email.com\"}]}"));
	}
	
	@Test
	public void testFirestationAlert() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/firestation?stationNumber={stationNumber}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"person\":"
						+ "[{\"firstName\":\"Alert\",\"lastName\":\"Firestation1\",\"address\":\"1 St\",\"phone\":\"123-456\"},"
						+ "{\"firstName\":\"Alert\",\"lastName\":\"Firestation2\",\"address\":\"1 St\",\"phone\":\"123-457\"}],"
						+ "\"n° children\":1,"
						+ "\"n° adults\":1}"));
	}
	
	@Test
	public void testFireAlert() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/fire?address={address}","3 St")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"person\":"
						+ "[{\"firstName\":\"Alert\",\"LastName\":\"Fire1\",\"phone\":\"444-123\",\"age\":\"21\",\"medications\":[\"med1:1g\",\"med2:2mg\"],\"allergies\":[\"thing1\"]},"
						+ "{\"firstName\":\"Alert\",\"LastName\":\"Fire2\",\"phone\":\"444-123\",\"age\":\"22\",\"medications\":[\"med3:1g\"],\"allergies\":[\"thing2\"]},"
						+ "{\"firstName\":\"Alert\",\"LastName\":\"Fire3\",\"phone\":\"444-123\",\"age\":\"23\",\"medications\":[],\"allergies\":[]}],\"station\":\"1\"}"));
	}
	
	@Test
	public void testFloodAlert() throws Exception{
		mvc.perform( MockMvcRequestBuilders 
				.get("/v1/flood/stations?stations={stations}",new int[]{1,3})
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{\"address\":"
						+ "{\"1 St\":"
						+ "[{\"firstName\":\"Alert\",\"LastName\":\"Firestation1\",\"phone\":\"123-456\",\"age\":\"17\",\"medications\":[],\"allergies\":[]},"
						+ "{\"firstName\":\"Alert\",\"LastName\":\"Firestation2\",\"phone\":\"123-457\",\"age\":\"31\",\"medications\":[],\"allergies\":[]}],"
						+ "\"3 St\":"
						+ "[{\"firstName\":\"Alert\",\"LastName\":\"Fire1\",\"phone\":\"444-123\",\"age\":\"21\",\"medications\":[\"med1:1g\",\"med2:2mg\"],\"allergies\":[\"thing1\"]},"
						+ "{\"firstName\":\"Alert\",\"LastName\":\"Fire2\",\"phone\":\"444-123\",\"age\":\"22\",\"medications\":[\"med3:1g\"],\"allergies\":[\"thing2\"]},"
						+ "{\"firstName\":\"Alert\",\"LastName\":\"Fire3\",\"phone\":\"444-123\",\"age\":\"23\",\"medications\":[],\"allergies\":[]}]}}"));
	}
	
	
	

}

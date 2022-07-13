package com.safetynet.safetynetalerts.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.safetynet.safetynetalerts.model.EntitiesContainer;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.AlertBuilder2;
import com.safetynet.safetynetalerts.service.EntitiesService;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AlertBuilderTest {

	@Mock
	EntitiesService entitiesMock;
	
	@Mock
	EntitiesContainer containerMock;
	
	@Mock
	Person personMock;
	
	@InjectMocks
	AlertBuilder alertBuilder;
	
	@BeforeEach
	public void setUp() {
		alertBuilder = new AlertBuilder();
	}
	
	@Test
	public void fuckTest() {
		Person p1 = new Person("firstname","lastname","1 St","Community","99999","123-123-123","community@email.com");
		List<Person> persons = Arrays.asList(p1);
		when(containerMock.getPersons()).thenReturn(persons);
		entitiesMock = Mockito.mock(EntitiesService.class, Mockito.RETURNS_DEEP_STUBS);
		when(personMock.getFirstName()).thenReturn("firstname");
		when(personMock.getLastName()).thenReturn("lastname");
		when(entitiesMock.getAddressOf("firstname", "lastname")).thenReturn("1 St");
		
		String res = alertBuilder.getAddressOf("firstname", "firstname");
		
		assertEquals("1 St",res);
	}
}

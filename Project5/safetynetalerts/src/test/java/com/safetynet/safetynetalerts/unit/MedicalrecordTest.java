package com.safetynet.safetynetalerts.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.model.Medicalrecord;


public class MedicalrecordTest {

	Medicalrecord record;
	
	@BeforeEach
	public void setUp() {
		record = new Medicalrecord();
	}
	/*
	@Test
	public void testIsChild() {
		record.setBirthdate(LocalDate.now().minusYears(17));
		assertTrue(record.isChild());
	}
	
	@Test
	public void testIsAdult() {
		record.setBirthdate(LocalDate.now().minusYears(18));
		assertFalse(record.isChild());
	}
	*/
	@Test
	public void testEquals() {
		Medicalrecord other = new Medicalrecord();
		record.setFirstname("firstname");other.setFirstname("firstname");
		record.setLastName("equals");other.setLastName("equals");
		List<String> allergies1 = new ArrayList<String>();
		List<String> allergies2 = new ArrayList<String>();
		allergies1.add("thing1");allergies1.add("thing2");
		allergies2.add("thing1");allergies2.add("thing2");
		record.setAllergies(allergies1);other.setAllergies(allergies2);
		List<String> medications1 = new ArrayList<String>();
		List<String> medications2 = new ArrayList<String>();
		medications1.add("med1 : 1g");medications2.add("med1 : 1g");
		record.setMedications(medications1);other.setMedications(medications2);
		record.setBirthdate(LocalDate.now());other.setBirthdate(LocalDate.now());
		assertEquals(record,other);
	}
}

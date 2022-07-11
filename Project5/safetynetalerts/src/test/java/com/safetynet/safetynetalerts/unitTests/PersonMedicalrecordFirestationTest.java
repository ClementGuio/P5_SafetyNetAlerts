package com.safetynet.safetynetalerts.unitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Medicalrecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonMedicalrecordFirestation;

public class PersonMedicalrecordFirestationTest {

	PersonMedicalrecordFirestation person;
	
	@Test
	public void testIsChild() {
		Person p1 = new Person();
		Medicalrecord r1 = new Medicalrecord();
		r1.setBirthdate(LocalDate.now().minusYears(17));
		Firestation s1 = new Firestation();
		person = new PersonMedicalrecordFirestation(p1,r1,s1);
		assertTrue(person.isChild());
	}
	
	@Test
	public void testIsAdult() {
		Person p1 = new Person();
		Medicalrecord r1 = new Medicalrecord();
		r1.setBirthdate(LocalDate.now().minusYears(18));
		Firestation s1 = new Firestation();
		person = new PersonMedicalrecordFirestation(p1,r1,s1);
		assertFalse(person.isChild());
	}
}

package com.safetynet.safetynetalerts.unitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.model.Medicalrecord;


public class MedicalrecordTest {

	Medicalrecord medicalrecord = new Medicalrecord();;
	/*
	@Test
	public void testIsChild() {
		medicalrecord.setBirthdate(LocalDate.now().minusYears(17));
		System.out.println("Child = "+medicalrecord);
		assertTrue(medicalrecord.isChild());
	}
	
	@Test
	public void testIsAdult() {
		medicalrecord.setBirthdate(LocalDate.now().minusYears(18));
		System.out.println("Adult = "+medicalrecord);
		assertFalse(medicalrecord.isChild());
	}
	*/
	
}

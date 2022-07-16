package com.safetynet.safetynetalerts.model;

import java.time.LocalDate;
import java.util.List;

public class PersonMedicalrecordFirestation{

	
	private Person person;
	
	private Medicalrecord record;
	
	private Firestation firestation;
	
	public PersonMedicalrecordFirestation(Person person, Medicalrecord record, Firestation firestation){
		this.person = person;
		this.record = record;
		this.firestation = firestation;
	}

	@Override
	public String toString() {
		return "{\"firstName\":\"" +getFirstName()+ 
				"\",\"lastName\":\""+getLastName()+ 
				"\",\"address\":\"" +getAddress()+ 
				"\",\"city\":\"" +getCity()+ 
				"\",\"zip\":\"" +getZip()+ 
				"\",\"phone\":\"" +getPhone()+ 
				"\",\"email\":\"" +getEmail()+ 
				"\",\"birthdate\":\"" +getBirthdate()+ 
				"\",\"medications\":\"" +getMedications()+ 
				"\",\"allergies\":\"" +getAllergies()+ 
				"\",\"station\":\""+getStation()+"\"}";
	}
	
	public boolean isChild() {
		return getAge()<18;
	}
	
	public Integer getAge() {
		return getBirthdate().until(LocalDate.now()).getYears();
	}
	
	public String getFirstName() {
		return person.getFirstName();
	}

	public String getLastName() {
		return person.getLastName();
	}

	public String getAddress() {
		return person.getAddress();
	}

	public String getCity() {
		return person.getCity();
	}

	public String getZip() {
		return person.getZip();
	}

	public String getPhone() {
		return person.getPhone();
	}

	public String getEmail() {
		return person.getEmail();
	}

	public LocalDate getBirthdate() {
		return record.getBirthdate();
	}

	public List<String> getMedications() {
		return record.getMedications();
	}

	public List<String> getAllergies() {
		return record.getAllergies();
	}

	public Integer getStation() {
		return firestation.getStation();
	}	
}

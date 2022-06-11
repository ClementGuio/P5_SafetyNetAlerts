package com.safetynet.safetynetalerts.model;

public class Medicalrecord {
	private String firstname, lastname, birthdate;
	private String[] medications, allergies;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String[] getMedications() {
		return medications;
	}
	public void setMedications(String[] medications) {
		this.medications = medications;
	}
	public String[] getAllergies() {
		return allergies;
	}
	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}

}

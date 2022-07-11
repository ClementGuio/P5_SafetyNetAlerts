package com.safetynet.safetynetalerts.model;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class PersonMedicalrecordFirestation{

	@JsonProperty("firstName")
	private String firstName; 
	@JsonProperty("lastName")
	private String lastName; 
	@JsonProperty("address")
	private String address;
	@JsonProperty("city")
	private String city; 
	@JsonProperty("zip")
	private String zip; 
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("email")
	private String email;
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="MM/dd/yyyy")
	@JsonProperty("birthdate")
	private LocalDate birthdate;
	@JsonProperty("age")
	private Integer age;
	@JsonProperty("medications")
	private List<String> medications = null; 
	@JsonProperty("allergies")
	private List<String> allergies = null;
	@JsonProperty("station")
	private Integer station;
	
	
	public PersonMedicalrecordFirestation(Person person, Medicalrecord record, Firestation firestation) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.city = person.getCity();
		this.zip = person.getZip();
		this.phone = person.getPhone();
		this.email = person.getEmail();
		this.birthdate = record.getBirthdate();
		this.age = this.birthdate.until(LocalDate.now()).getYears();
		this.medications = record.getMedications();
		this.allergies = record.getAllergies();
		this.station = firestation.getStation();
	}
	
	/*
	public PersonMedicalrecordFirestation(PersonMedicalrecordFirestationBuilder build) {
		this.firstName = build.firstName;
		this.lastName = build.lastName;
		this.address = build.address;
		this.city = build.city;
		this.zip = build.zip;
		this.phone = build.phone;
		this.email = build.email;
		this.birthdate = build.birthdate;
		this.age = build.age;
		this.medications = build.medications;
		this.allergies = build.allergies;
		this.station = build.station;
	}
	
	public static class PersonMedicalrecordFirestationBuilder{
		@Autowired
		private EntitiesContainer entities;
		
		private String firstName; 
		private String lastName; 
		private String address;
		private String city; 
		private String zip; 
		private String phone;
		private String email;
		private LocalDate birthdate;
		private Integer age;
		private List<String> medications = null; 
		private List<String> allergies = null;
		private Integer station;
		
		
		public PersonMedicalrecordFirestationBuilder(Person person) {
			this.firstName = person.getFirstName();
			this.lastName = person.getLastName();
			this.address = person.getAddress();
			this.city = person.getCity();
			this.zip = person.getZip();
			this.phone = person.getPhone();
			this.email = person.getEmail();
			Medicalrecord record = entities.getMedicalrecordOf(person);
			this.birthdate = record.getBirthdate();
			this.age = this.birthdate.until(LocalDate.now()).getYears();
			this.medications = record.getMedications();
			this.allergies = record.getAllergies();
			this.station = entities.getFirestationOf(person).getStation();
		}
		

		public PersonMedicalrecordFirestation build() {
			return new PersonMedicalrecordFirestation(this);
		}
		
		
	}*/
	@JsonIgnore
	public boolean isChild() {
		return age<18;
	}
//getters, setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "PersonMedicalrecordFirestation [firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", city=" + city + ", zip=" + zip + ", phone=" + phone
				+ ", email=" + email + ", birthdate=" + birthdate + ", age=" + age + ", medications=" + medications
				+ ", allergies=" + allergies + ", station=" + station + "]";
	}
	
}

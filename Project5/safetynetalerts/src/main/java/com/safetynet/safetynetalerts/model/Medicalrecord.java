package com.safetynet.safetynetalerts.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"firstName","lastName","birthdate","medications",})
@Generated("jsonschema2pojo")
public class Medicalrecord {
	
	@JsonProperty("firstName")
	private String firstName; 
	@JsonProperty("lastName")
	private String lastName; 
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern="MM/dd/yyyy")
	@JsonProperty("birthdate")
	private LocalDate birthdate;
	@JsonProperty("medications")
	private List<String> medications = null; 
	@JsonProperty("allergies")
	private List<String> allergies = null;
	@JsonIgnore
	private Map<String,Object> additionalProperties = new HashMap<String,Object>();
	
	
	@Override
	public String toString() {
		return firstName+" "+lastName+"\nbirthdate : "+birthdate+"\nmedications : "+medications+"\nallergies : "+allergies+"\n";
	}
	
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}
	
	@JsonProperty("firstName")
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}
	
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonProperty("birthdate")
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
	@JsonProperty("birthdate")
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@JsonProperty("medications")
	public List<String> getMedications() {
		return medications;
	}
	
	@JsonProperty("medications")
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	
	@JsonProperty("allergies")
	public List<String> getAllergies() {
		return allergies;
	}
	
	@JsonProperty("allergies")
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	@JsonAnyGetter
	public Map<String,Object> getAdditionalProperties(){
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperties(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

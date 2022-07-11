package com.safetynet.safetynetalerts.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"firstName","lastName","address","city","zip","phone","email"})
@Generated("jsonschema2pojo")
public class Person{

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
	
	@JsonIgnore
	private Map<String,Object> additionalProperties = new HashMap<String,Object>();
/*	
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.additionalProperties = additionalProperties;
	}
	*/
	
	@Override
	public String toString() {
		return firstName+" "+lastName+"\nAddress : "+address+"\nCity : "+city+" "+zip+"\nPhone : "+phone+"\nEmail : "+email+"\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj==null) return false;
		if (getClass() != obj.getClass()) return false;
		Person other = (Person) obj;
		if (!firstName.equals(other.firstName)) return false;
		if (!lastName.equals(other.lastName)) return false;
		if (!address.equals(other.address)) return false;
		if (city.equals(other.city)) return false;
		if (!zip.equals(other.zip)) return false;
		if (!phone.equals(other.phone)) return false;
		if (!email.equals(other.email)) return false;
		return true;
	}
	
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("zip")
	public String getZip() {
		return zip;
	}

	@JsonProperty("zip")
	public void setZip(String zip) {
		this.zip = zip;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
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

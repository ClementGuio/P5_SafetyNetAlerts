package com.safetynet.safetynetalerts.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"address","station"})
@Generated("jsonschema2pojo")
public class Firestation{
	
	@JsonProperty("address")
	private String address; 
	@JsonProperty("station")
	private Integer station;
	@JsonIgnore
	private Map<String,Object> additionalProperties = new HashMap<String,Object>();
	
	/*
	public Firestation(String address, Integer station) {
		this.address = address;
		this.station = station;
		this.additionalProperties = additionalProperties;
	}
*/
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj==null) return false;
		if (getClass() != obj.getClass()) return false;
		Firestation other = (Firestation) obj;
		if (!address.equals(other.address)) return false;
		if (station != other.station) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "address : "+address+"\nstation : "+station+"\n";
	}
	
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("station")
	public Integer getStation() {
		return station;
	}

	@JsonProperty("station")
	public void setStation(Integer station) {
		this.station = station;
	}
	
	@JsonAnyGetter
	public Map<String,Object> getAdditionalProperties(){
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
}

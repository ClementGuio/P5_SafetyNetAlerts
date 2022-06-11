package com.safetynet.safetynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class Firestations {
	private List<Firestation> firestations;

	public List<Firestation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
}

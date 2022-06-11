package com.safetynet.safetynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Medicalrecords {
	private List<Medicalrecord> medicalrecords;

	public List<Medicalrecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

}

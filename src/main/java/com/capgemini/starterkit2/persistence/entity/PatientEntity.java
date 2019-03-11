package com.capgemini.starterkit2.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "Patient.findByLastName", query = "SELECT p FROM PatientEntity p WHERE p.lastName = :lastName") })
@Entity
@Table(name = "PATIENT")
@PrimaryKeyJoinColumn(name = "patient_id", referencedColumnName = "id")
public class PatientEntity extends PersonEntity {

	@Column(nullable = false)
	private String patientNumber;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}

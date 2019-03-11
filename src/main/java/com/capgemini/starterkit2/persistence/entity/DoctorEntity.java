package com.capgemini.starterkit2.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM DoctorEntity d") })
@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private AddressEntity address;

	@Column(nullable = false)
	private String doctorNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

}

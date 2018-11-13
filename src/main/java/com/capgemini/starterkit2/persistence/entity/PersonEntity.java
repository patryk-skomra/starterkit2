package com.capgemini.starterkit2.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM PersonEntity p WHERE p.lastName = :lastName")
})
@Entity
@Table(name = "PERSON")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "id")
public class PersonEntity extends CustomerEntity {

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private LocalDateTime dateOfBirth;

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

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}

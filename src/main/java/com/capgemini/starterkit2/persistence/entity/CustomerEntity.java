package com.capgemini.starterkit2.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM CustomerEntity c"),
		@NamedQuery(name = "Customer.findByPrimaryKey", query = "SELECT c FROM CustomerEntity c WHERE c.id = :id"),
})
@Entity
@Table(name = "CUSTOMER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CustomerEntity extends AbstractEntity {

	private String email;

	@Column(nullable = false)
	private String telephoneNumber;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private AddressEntity address;

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

}

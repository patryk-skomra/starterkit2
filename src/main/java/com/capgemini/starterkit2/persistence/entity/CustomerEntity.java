package com.capgemini.starterkit2.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

@NamedQueries({
		@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM CustomerEntity c"),
		@NamedQuery(name = "Customer.findByPrimaryKey", query = "SELECT c FROM CustomerEntity c WHERE c.id = :id"),
		@NamedQuery(name = "Customer.findByLastName", query = "SELECT c FROM CustomerEntity c WHERE c.lastName = :lastName")
})
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private String email;

	@Column(nullable = false)
	private String telephoneNrumber;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private AddressEntity address;

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

	public String getTelephoneNrumber() {
		return telephoneNrumber;
	}

	public void setTelephoneNrumber(String telephoneNrumber) {
		this.telephoneNrumber = telephoneNrumber;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
		this.updateDate = createDate;
	}

	@PreUpdate
	public void updateDate() {
		this.updateDate = LocalDateTime.now();
	}
}

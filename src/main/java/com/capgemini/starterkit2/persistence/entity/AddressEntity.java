package com.capgemini.starterkit2.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity extends AbstractEntity {

	private String city;

	private String addressLine1;

	private String addressLine2;

	private String postalCode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}

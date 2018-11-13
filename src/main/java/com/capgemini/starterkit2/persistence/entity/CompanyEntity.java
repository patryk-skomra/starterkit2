package com.capgemini.starterkit2.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
@PrimaryKeyJoinColumn(name = "company_id", referencedColumnName = "id")
public class CompanyEntity extends CustomerEntity {

	private String name;

	private String nip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

}

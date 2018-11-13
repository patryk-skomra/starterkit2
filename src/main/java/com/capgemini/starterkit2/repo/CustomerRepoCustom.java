package com.capgemini.starterkit2.repo;

import java.util.List;

import com.capgemini.starterkit2.persistence.entity.CustomerEntity;

public interface CustomerRepoCustom {

	List<CustomerEntity> findByTelephoneNrumber(String telephoneNrumber);
}

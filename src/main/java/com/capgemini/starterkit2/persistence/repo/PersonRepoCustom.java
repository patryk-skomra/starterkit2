package com.capgemini.starterkit2.persistence.repo;

import java.util.List;

import com.capgemini.starterkit2.persistence.entity.PersonEntity;

public interface PersonRepoCustom {

	List<PersonEntity> findByTelephoneNumber(String telephoneNumber);
}

package com.capgemini.starterkit2.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.PersonEntity;

public interface PersonRepo extends JpaRepository<PersonEntity, Long>, PersonRepoCustom {

}

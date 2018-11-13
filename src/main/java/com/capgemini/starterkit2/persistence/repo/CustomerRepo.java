package com.capgemini.starterkit2.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long>, CustomerRepoCustom {

}

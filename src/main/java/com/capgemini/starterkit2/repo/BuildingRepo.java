package com.capgemini.starterkit2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.BuildingEntity;

public interface BuildingRepo extends JpaRepository<BuildingEntity, Long> {

}

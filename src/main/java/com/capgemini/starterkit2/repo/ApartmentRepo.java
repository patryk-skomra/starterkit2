package com.capgemini.starterkit2.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.ApartmentEntity;
import com.capgemini.starterkit2.persistence.entity.Status;

public interface ApartmentRepo extends JpaRepository<ApartmentEntity, Long> {

	List<ApartmentEntity> findByStatus(Status status);

	List<ApartmentEntity> findByAreaBetween(Double from, Double to);
}

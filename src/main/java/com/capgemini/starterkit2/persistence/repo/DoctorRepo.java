package com.capgemini.starterkit2.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.DoctorEntity;

public interface DoctorRepo extends JpaRepository<DoctorEntity, Long> {

	DoctorEntity findByDoctorNumber(String doctorNumber);
}

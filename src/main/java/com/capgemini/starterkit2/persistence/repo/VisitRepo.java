package com.capgemini.starterkit2.persistence.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.DoctorEntity;
import com.capgemini.starterkit2.persistence.entity.VisitEntity;

public interface VisitRepo extends JpaRepository<VisitEntity, Long> {

	List<VisitEntity> findByTimeBetween(LocalDateTime fromTime, LocalDateTime toTime);

	List<VisitEntity> findByDoctor(DoctorEntity doctor);
}

package com.capgemini.starterkit2.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit2.persistence.entity.MedicalTreatmentEntity;
import com.capgemini.starterkit2.persistence.entity.TreatmentType;

public interface MedicalTreatmentRepo extends JpaRepository<MedicalTreatmentEntity, Long> {

	List<MedicalTreatmentEntity> findByType(TreatmentType type);

}

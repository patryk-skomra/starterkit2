package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.DoctorEntity;
import com.capgemini.starterkit2.persistence.entity.MedicalTreatmentEntity;
import com.capgemini.starterkit2.persistence.entity.PatientEntity;
import com.capgemini.starterkit2.persistence.entity.Specialization;
import com.capgemini.starterkit2.persistence.entity.TreatmentType;
import com.capgemini.starterkit2.persistence.entity.VisitEntity;
import com.capgemini.starterkit2.persistence.repo.DoctorRepo;
import com.capgemini.starterkit2.persistence.repo.PersonRepo;
import com.capgemini.starterkit2.persistence.repo.VisitRepo;
import com.google.common.collect.Sets;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VisitRepoTest {

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private VisitRepo visitRepo;

	@Before
	public void loadData() {
		PatientEntity patient1 = createPatient("Jan", "Kowalski", "1234567", "P-123", LocalDate.now().minusYears(20));
		personRepo.save(patient1);

		PatientEntity patient2 = createPatient("Marcin", "Kowalski", "9871236", "P-124",
				LocalDate.now().minusYears(15));
		personRepo.save(patient2);

		DoctorEntity doctor1 = createDoctor("Anna", "Nowak", "7654321", "D-321", Specialization.GP);
		personRepo.save(doctor1);

		DoctorEntity doctor2 = createDoctor("Jakub", "Lis", "7654321", "D-322", Specialization.GP);
		personRepo.save(doctor2);

		VisitEntity visit1 = createVisit("Hypochondria", LocalDateTime.of(2019, 2, 7, 12, 30),
				Sets.newHashSet(createMedicalTreatment("Heart ultrasound", TreatmentType.USG)), patient1, doctor1);
		visitRepo.save(visit1);

		VisitEntity visit2 = createVisit("Broken forearm", LocalDateTime.of(2019, 2, 7, 12, 45),
				Sets.newHashSet(createMedicalTreatment("X-ray of the right forearm", TreatmentType.RTG)), patient2,
				doctor2);
		visitRepo.save(visit2);
	}

	@Test
	public void shouldFindByDoctor() {

		// given
		DoctorEntity doctor = doctorRepo.findByDoctorNumber("D-321");

		// when
		List<VisitEntity> visits = visitRepo.findByDoctor(doctor);

		// then
		assertThat(visits).hasSize(1);
		visits.forEach(v -> assertThat(v.getDoctor().getDoctorNumber()).isEqualTo("D-321"));
	}

	@Test
	public void shouldNotFindByDoctor() {

		// given
		DoctorEntity doctor = doctorRepo.findByDoctorNumber("P-123");

		// when
		List<VisitEntity> visits = visitRepo.findByDoctor(doctor);

		// then
		assertThat(visits).hasSize(0);
	}

	@Test
	public void shouldFindByTime() {

		// given

		// when
		List<VisitEntity> visits = visitRepo.findByTimeBetween(LocalDateTime.of(2019, 2, 7, 00, 00),
				LocalDateTime.of(2019, 2, 7, 23, 59));

		// then
		assertThat(visits).hasSize(2);
		visits.forEach(v -> assertThat(v.getTime()).isGreaterThanOrEqualTo(LocalDateTime.of(2019, 2, 7, 00, 00)));
		visits.forEach(v -> assertThat(v.getTime()).isLessThanOrEqualTo(LocalDateTime.of(2019, 2, 7, 23, 59)));
	}

	@Test
	public void shouldNotFindByTime() {

		// given

		// when
		List<VisitEntity> visits = visitRepo.findByTimeBetween(LocalDateTime.of(2018, 2, 7, 00, 00),
				LocalDateTime.of(2018, 2, 7, 23, 59));

		// then
		assertThat(visits).hasSize(0);
	}

	private PatientEntity createPatient(String firstName, String lastName, String telephoneNumber, String patientNumber,
			LocalDate dateOfBirth) {

		PatientEntity patient = new PatientEntity();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setEmail(firstName + lastName + "@.st.com");
		patient.setTelephoneNumber(telephoneNumber);
		patient.setDateOfBirth(dateOfBirth);
		patient.setPatientNumber(patientNumber);
		return patient;
	}

	private DoctorEntity createDoctor(String firstName, String lastName, String telephoneNumber, String doctorNumber,
			Specialization specialization) {

		DoctorEntity doctor = new DoctorEntity();
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setEmail(firstName + lastName + "@.st.com");
		doctor.setTelephoneNumber(telephoneNumber);
		doctor.setSpecialization(specialization);
		doctor.setDoctorNumber(doctorNumber);
		return doctor;
	}

	private VisitEntity createVisit(String description, LocalDateTime time,
			Set<MedicalTreatmentEntity> medicalTreatments, PatientEntity patient, DoctorEntity doctor) {

		VisitEntity visit = new VisitEntity();
		visit.setDescription(description);
		visit.setTime(time);
		visit.setMedicalTreatments(medicalTreatments);
		visit.setDoctor(doctor);
		visit.setPatient(patient);
		return visit;
	}

	private MedicalTreatmentEntity createMedicalTreatment(String description, TreatmentType type) {
		MedicalTreatmentEntity treatment = new MedicalTreatmentEntity();
		treatment.setDescription(description);
		treatment.setType(type);
		return treatment;
	}
}

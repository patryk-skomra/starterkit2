package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.DoctorEntity;
import com.capgemini.starterkit2.persistence.entity.PatientEntity;
import com.capgemini.starterkit2.persistence.entity.PersonEntity;
import com.capgemini.starterkit2.persistence.entity.Specialization;
import com.capgemini.starterkit2.persistence.repo.PersonRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepoTest {

	@Autowired
	private PersonRepo repo;

	@Before
	public void loadData() {
		PersonEntity person1 = createPatient("Jan", "Kowalski", "1234567", "P-123", LocalDate.now().minusYears(20));
		repo.save(person1);

		PersonEntity person2 = createPatient("Marcin", "Kowalski", "9871236", "P-124", LocalDate.now().minusYears(15));
		repo.save(person2);

		PersonEntity person3 = createPatient("Maria", "Nowak", "7654321", "P-125", LocalDate.now().minusYears(35));
		repo.save(person3);

		PersonEntity person4 = createDoctor("Cap", "123-321", "7654321", "D-321", Specialization.GP);
		repo.save(person4);
	}

	@Test
	public void shouldFindByTelephoneNumber() {

		// when
		List<PersonEntity> result = repo.findByTelephoneNumber("7654321");

		// then
		assertThat(result).hasSize(2);
		assertTrue(result.stream().allMatch(c -> c.getTelephoneNumber().equals("7654321")));
	}

	@Test
	public void shouldNotFindByTelephoneNumber() {

		// when
		List<PersonEntity> result = repo.findByTelephoneNumber("7654321321");

		// then
		assertThat(result).hasSize(0);
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

}

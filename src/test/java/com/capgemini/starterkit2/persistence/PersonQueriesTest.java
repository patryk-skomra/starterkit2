package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.DoctorEntity;
import com.capgemini.starterkit2.persistence.entity.PatientEntity;
import com.capgemini.starterkit2.persistence.entity.PersonEntity;
import com.capgemini.starterkit2.persistence.entity.Specialization;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonQueriesTest {

	@PersistenceContext
	private EntityManager manager;

	@Before
	public void loadData() {
		PersonEntity person1 = createPatient("Jan", "Kowalski", "1234567", "P-123", LocalDate.now().minusYears(20));
		manager.persist(person1);

		PersonEntity person2 = createPatient("Marcin", "Kowalski", "9871236", "P-124", LocalDate.now().minusYears(15));
		manager.persist(person2);

		PersonEntity person3 = createPatient("Maria", "Nowak", "7654321", "P-125", LocalDate.now().minusYears(35));
		manager.persist(person3);

		PersonEntity person4 = createDoctor("Cap", "123-321", "7654321", "D-321", Specialization.GP);
		manager.persist(person4);
	}

	@Test
	public void shouldFindAllPersons() {
		// when
		List<PersonEntity> result = manager.createNamedQuery("Person.findAll", PersonEntity.class).getResultList();

		// then
		assertThat(result).hasSize(4);
		assertThat(result.stream().filter(PatientEntity.class::isInstance).count()).isEqualTo(3);
		assertThat(result.stream().filter(DoctorEntity.class::isInstance).count()).isEqualTo(1);
	}

	@Test
	public void shouldFindPersonById() {
		// given
		PersonEntity person = createPatient("Anna", "Kwiatkowska", "9753246", "P-123", LocalDate.now().minusYears(20));
		manager.persist(person);

		// when
		PersonEntity result = manager.find(PersonEntity.class, person.getId());

		// then
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(person.getId());
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

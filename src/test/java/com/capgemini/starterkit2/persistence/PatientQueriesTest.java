package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.PatientEntity;
import com.capgemini.starterkit2.persistence.entity.PersonEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientQueriesTest {

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
	}

	@Test
	public void shouldFindPatientByLastName() {
		// when
		List<PatientEntity> result = manager.createNamedQuery("Patient.findByLastName", PatientEntity.class)
				.setParameter("lastName", "Kowalski").getResultList();

		// then
		assertThat(result).hasSize(2);
		assertThat(result.stream().map(c -> c.getLastName()).distinct().collect(Collectors.toList())).hasSize(1);
		assertThat(result.stream().map(c -> c.getLastName()).findAny().get()).isEqualTo("Kowalski");
	}

	@Test
	public void shouldNotFindPatientByLastName() {
		// when
		List<PatientEntity> result = manager.createNamedQuery("Patient.findByLastName", PatientEntity.class)
				.setParameter("lastName", "Kwiatkowski").getResultList();

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
}

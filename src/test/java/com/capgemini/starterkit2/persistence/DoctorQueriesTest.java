package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.capgemini.starterkit2.persistence.entity.Specialization;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorQueriesTest {

	@PersistenceContext
	private EntityManager manager;

	@Before
	public void loadData() {

		DoctorEntity doctor1 = createDoctor("Jan", "Kowalski", "7654321", "D-321", Specialization.GP);
		manager.persist(doctor1);

		DoctorEntity doctor2 = createDoctor("Anna", "Nowak", "1234567", "D-322", Specialization.DERMATOLOGIST);
		manager.persist(doctor2);

		DoctorEntity doctor3 = createDoctor("Marek", "Kwiatkowski", "3214567", "D-323", Specialization.OCULIST);
		manager.persist(doctor3);

		DoctorEntity doctor4 = createDoctor("Joanna", "Lis", "4567123", "D-324", Specialization.SURGEON);
		manager.persist(doctor4);
	}

	@Test
	public void shouldFindAllDoctors() {
		// when
		List<DoctorEntity> result = manager.createNamedQuery("Doctor.findAll", DoctorEntity.class).getResultList();

		// then
		assertThat(result).hasSize(4);
	}

	@Test
	public void shouldFindDoctorById() {
		// given
		DoctorEntity doctor = createDoctor("Marcin", "Kot", "9876123", "D-325", Specialization.GP);
		manager.persist(doctor);

		// when
		DoctorEntity result = manager.find(DoctorEntity.class, doctor.getId());

		// then
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(doctor.getId());
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

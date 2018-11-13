package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
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

import com.capgemini.starterkit2.persistence.entity.CustomerEntity;
import com.capgemini.starterkit2.persistence.entity.PersonEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonQueriesTest {

	@PersistenceContext
	private EntityManager manager;

	@Before
	public void loadData() {
		CustomerEntity customer1 = createPerson("Jan", "Kowalski", "1234567");
		manager.persist(customer1);

		CustomerEntity customer2 = createPerson("Marcin", "Kowalski", "9871236");
		manager.persist(customer2);

		CustomerEntity customer3 = createPerson("Maria", "Nowak", "7654321");
		manager.persist(customer3);
	}

	@Test
	public void shouldFindCustomerByLastName() {
		// when
		List<PersonEntity> result = manager.createNamedQuery("Person.findByLastName", PersonEntity.class)
				.setParameter("lastName", "Kowalski")
				.getResultList();

		// then
		assertThat(result).hasSize(2);
		assertThat(result.stream().map(c -> c.getLastName()).distinct().collect(Collectors.toList())).hasSize(1);
		assertThat(result.stream().map(c -> c.getLastName()).findAny().get()).isEqualTo("Kowalski");
	}

	@Test
	public void shouldNotFindCustomerByLastName() {
		// when
		List<PersonEntity> result = manager.createNamedQuery("Person.findByLastName", PersonEntity.class)
				.setParameter("lastName", "Kwiatkowski")
				.getResultList();

		// then
		assertThat(result).hasSize(0);
	}

	private CustomerEntity createPerson(String firstName, String lastName, String telephoneNrumber) {
		PersonEntity person = new PersonEntity();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(firstName + lastName + "@.st.com");
		person.setTelephoneNrumber(telephoneNrumber);
		person.setDateOfBirth(LocalDateTime.now().minusYears(20));
		return person;
	}
}

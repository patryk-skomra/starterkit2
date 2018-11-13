package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.CompanyEntity;
import com.capgemini.starterkit2.persistence.entity.CustomerEntity;
import com.capgemini.starterkit2.persistence.entity.PersonEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerQueriesTest {

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

		CustomerEntity customer4 = createCompany("Cap", "123-321", "7654321");
		manager.persist(customer4);
	}

	@Test
	public void shouldFindAllCustomer() {
		// when
		List<CustomerEntity> result = manager.createNamedQuery("Customer.findAll", CustomerEntity.class)
				.getResultList();

		// then
		assertThat(result).hasSize(4);
		assertThat(result.stream().filter(PersonEntity.class::isInstance).count()).isEqualTo(3);
		assertThat(result.stream().filter(CompanyEntity.class::isInstance).count()).isEqualTo(1);
	}

	@Test
	public void shouldFindCustomerById() {
		// given
		CustomerEntity customer = createPerson("Anna", "Kwiatkowska", "9753246");
		manager.persist(customer);

		// when
		CustomerEntity result = manager.find(CustomerEntity.class, customer.getId());

		// then
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(customer.getId());
	}

	private CustomerEntity createPerson(String firstName, String lastName, String telephoneNrumber) {
		PersonEntity person = new PersonEntity();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(firstName + lastName + "@.st.com");
		person.setTelephoneNumber(telephoneNrumber);
		person.setDateOfBirth(LocalDateTime.now().minusYears(20));
		return person;
	}

	private CompanyEntity createCompany(String name, String nip, String telephoneNrumber) {
		CompanyEntity company = new CompanyEntity();
		company.setNip(nip);
		company.setName(name);
		company.setEmail(name + "@.st.com");
		company.setTelephoneNumber(telephoneNrumber);
		return company;
	}
}

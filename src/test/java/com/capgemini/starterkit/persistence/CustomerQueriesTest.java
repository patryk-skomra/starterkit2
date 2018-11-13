package com.capgemini.starterkit.persistence;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.capgemini.starterkit.persistence.entity.CustomerEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerQueriesTest {

	@PersistenceContext
	private EntityManager manager;

	@Before
	public void loadData() {
		CustomerEntity customer1 = createCustomer("Jan", "Kowalski", "1234567");
		manager.persist(customer1);

		CustomerEntity customer2 = createCustomer("Marcin", "Kowalski", "9871236");
		manager.persist(customer2);

		CustomerEntity customer3 = createCustomer("Maria", "Nowak", "7654321");
		manager.persist(customer3);
	}

	@Test
	public void shouldFindCustomerByLastName() {
		// when
		List<CustomerEntity> result = manager.createNamedQuery("Customer.findByLastName", CustomerEntity.class)
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
		List<CustomerEntity> result = manager.createNamedQuery("Customer.findByLastName", CustomerEntity.class)
				.setParameter("lastName", "Kwiatkowski")
				.getResultList();

		// then
		assertThat(result).hasSize(0);
	}

	@Test
	public void shouldFindAllCustomer() {
		// when
		List<CustomerEntity> result = manager.createNamedQuery("Customer.findAll", CustomerEntity.class)
				.getResultList();

		// then
		assertThat(result).hasSize(3);
		assertThat(result.get(0).getLastName()).isEqualTo("Kowalski");
	}

	@Test
	public void shouldFindCustomerById() {
		// given
		CustomerEntity customer = createCustomer("Anna", "Kwiatkowska", "9753246");
		manager.persist(customer);

		// when
		CustomerEntity result = manager.find(CustomerEntity.class, customer.getId());

		// then
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(customer.getId());
	}

	private CustomerEntity createCustomer(String firstName, String lastName, String telephoneNrumber) {
		CustomerEntity customer = new CustomerEntity();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setTelephoneNrumber(telephoneNrumber);
		return customer;
	}
}

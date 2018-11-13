package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.CompanyEntity;
import com.capgemini.starterkit2.persistence.entity.CustomerEntity;
import com.capgemini.starterkit2.persistence.entity.PersonEntity;
import com.capgemini.starterkit2.repo.CustomerRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerRepoTest {

	@Autowired
	private CustomerRepo repo;

	@Before
	public void loadData() {
		CustomerEntity customer1 = createPerson("Jan", "Kowalski", "1234567");
		repo.save(customer1);

		CustomerEntity customer2 = createPerson("Marcin", "Kowalski", "9871236");
		repo.save(customer2);

		CustomerEntity customer3 = createPerson("Maria", "Nowak", "7654321");
		repo.save(customer3);

		CustomerEntity customer4 = createCompany("Cap", "123-321", "7654321");
		repo.save(customer4);
	}

	@Test
	public void shouldFindByTelephoneNumber() {

		// when
		List<CustomerEntity> result = repo.findByTelephoneNrumber("7654321");

		// then
		assertThat(result).hasSize(2);
		assertTrue(result.stream().allMatch(c -> c.getTelephoneNumber().equals("7654321")));
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

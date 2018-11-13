package com.capgemini.starterkit2.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.starterkit2.persistence.entity.ApartmentEntity;
import com.capgemini.starterkit2.persistence.entity.Status;
import com.capgemini.starterkit2.repo.ApartmentRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApartmentRepoTest {

	@Autowired
	private ApartmentRepo repo;

	@Before
	public void loadData() {
		ApartmentEntity apartment1 = createApartment(100.1, 1, 1, 4, Status.FREE, BigDecimal.valueOf(600000));
		repo.save(apartment1);

		ApartmentEntity apartment2 = createApartment(75.5, 2, 2, 3, Status.BOOKED, BigDecimal.valueOf(450000));
		repo.save(apartment2);

		ApartmentEntity apartment3 = createApartment(85.0, 3, 1, 3, Status.FREE, BigDecimal.valueOf(500000));
		repo.save(apartment3);
	}

	@Test
	public void shouldFindByStatus() {
		// when
		List<ApartmentEntity> result = repo.findByStatus(Status.FREE);

		// then
		assertThat(result).hasSize(2);
		assertThat(result.stream().map(a -> a.getStatus()).distinct().count()).isEqualTo(1);
		assertThat(result.stream().map(a -> a.getStatus()).distinct().findAny().get()).isEqualTo(Status.FREE);
	}

	@Test
	public void shouldNotFindByStatus() {
		// when
		List<ApartmentEntity> result = repo.findByStatus(Status.SOLD);

		// then
		assertThat(result).hasSize(0);
	}

	@Test
	public void shouldFindByArea() {
		// when
		List<ApartmentEntity> result = repo.findByAreaBetween(50d, 90d);

		// then
		assertThat(result).hasSize(2);
		assertTrue(result.stream().allMatch(a -> a.getArea().doubleValue() < 90d));
		assertTrue(result.stream().allMatch(a -> a.getArea().doubleValue() > 50d));
	}

	@Test
	public void shouldNotFindByArea() {
		// when
		List<ApartmentEntity> result = repo.findByAreaBetween(110d, 200d);

		// then
		assertThat(result).hasSize(0);
	}

	private ApartmentEntity createApartment(Double area, Integer floor, Integer numberOfBalconies,
			Integer numberOfRooms, Status status, BigDecimal price) {
		ApartmentEntity apartment = new ApartmentEntity();
		apartment.setArea(area);
		apartment.setFloor(floor);
		apartment.setNumberOfBalconies(numberOfBalconies);
		apartment.setNumberOfRooms(numberOfRooms);
		apartment.setStatus(status);
		apartment.setPrice(price);
		return apartment;
	}

}

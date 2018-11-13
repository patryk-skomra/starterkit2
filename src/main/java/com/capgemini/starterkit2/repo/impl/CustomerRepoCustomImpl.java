package com.capgemini.starterkit2.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.starterkit2.persistence.entity.CustomerEntity;
import com.capgemini.starterkit2.persistence.entity.QCustomerEntity;
import com.capgemini.starterkit2.repo.CustomerRepoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CustomerRepoCustomImpl implements CustomerRepoCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<CustomerEntity> findByTelephoneNrumber(String telephoneNrumber) {

		QCustomerEntity customer = QCustomerEntity.customerEntity;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory.selectFrom(customer)
				.where(customer.telephoneNumber.eq(telephoneNrumber))
				.fetch();
	}

}

package com.capgemini.starterkit2.persistence.repo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.starterkit2.persistence.entity.PersonEntity;
import com.capgemini.starterkit2.persistence.entity.QPersonEntity;
import com.capgemini.starterkit2.persistence.repo.PersonRepoCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PersonRepoCustomImpl implements PersonRepoCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<PersonEntity> findByTelephoneNumber(String telephoneNumber) {

		QPersonEntity person = QPersonEntity.personEntity;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		return queryFactory.selectFrom(person)
				.where(person.telephoneNumber.eq(telephoneNumber))
				.fetch();
	}

}

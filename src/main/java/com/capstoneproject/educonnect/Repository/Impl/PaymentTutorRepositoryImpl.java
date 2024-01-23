package com.capstoneproject.educonnect.Repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.PayForTutorDTO;
import com.capstoneproject.educonnect.Entity.QPayment;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Repository.PaymentTutorRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PaymentTutorRepositoryImpl implements PaymentTutorRepository {

	private JPAQueryFactory jpaQueryFactory;
	
	private final QTutor tutor = QTutor.tutor;
	private final QPayment payment = QPayment.payment;

	@Autowired
	public PaymentTutorRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	@Transactional
	@Override
	public void paymenttutor(int tutorid, float amount) {
		jpaQueryFactory.update(tutor)
		.where(tutor.tutorId.eq(tutorid))
		.set(tutor.salary, tutor.salary.subtract(amount))
		.execute();
	}

	@Override
	public PayForTutorDTO showbank(int tutorid) {
		PayForTutorDTO result = jpaQueryFactory.select(Projections.constructor(PayForTutorDTO.class, payment.tutorid,
				payment.money, payment.banknumber, payment.bank, payment.datepay))
				.from(payment)
				.where(payment.tutorid.eq(tutorid))
				.orderBy(payment.paymentid.desc())
				.limit(1)
				.fetchFirst();
		return result;
	}

	@Override
	public List<PayForTutorDTO> historypayment(int tutorid) {
		List<PayForTutorDTO> result = jpaQueryFactory.select(Projections.constructor(PayForTutorDTO.class, payment.tutorid,
				payment.money, payment.banknumber, payment.bank, payment.datepay))
				.from(payment)
				.where(payment.tutorid.eq(tutorid))
				.orderBy(payment.paymentid.desc())
				.fetch();
		return result;
	}

}

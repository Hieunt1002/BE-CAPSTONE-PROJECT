package com.capstoneproject.educonnect.Repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Repository.UserRepositoryT;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserRepositoryIpmI implements UserRepositoryT {

	private JPAQueryFactory jpaQueryFactory;
	
	private final QUser user = QUser.user;
	
	@Autowired
	public UserRepositoryIpmI(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	@Transactional
	@Override
	public void ChangePassword(String email, String pass) {	
		jpaQueryFactory.update(user)
		.where(user.email.eq(email))
		.set(user.password, pass)
		.execute();
		
	}

	@Override
	public String checkpass(int id, String pass) {
		String result = jpaQueryFactory.select(user.password)
				.distinct()
				.from(user)
				.where(user.userid.eq(id).and(user.password.eq(pass)))
				.fetchOne();
		return result;
	}

}

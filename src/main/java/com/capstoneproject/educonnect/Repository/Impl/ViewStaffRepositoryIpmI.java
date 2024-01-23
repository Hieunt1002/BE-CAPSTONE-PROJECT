package com.capstoneproject.educonnect.Repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.AddLinkMeetDTO;
import com.capstoneproject.educonnect.DTO.ViewStaffDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QStaff;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Repository.ViewStaffRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ViewStaffRepositoryIpmI implements ViewStaffRepository {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	private final QStaff Staff = QStaff.staff;
	private final QUser User = QUser.user;
	private final QBookingEntity bookingEntity = QBookingEntity.bookingEntity;
	
	@Autowired
    public ViewStaffRepositoryIpmI(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
	
	@Override
	public ViewStaffDTO ListviewStaff(int staffId) {
		ViewStaffDTO result = jpaQueryFactory
				.select(Projections.constructor(ViewStaffDTO.class, Staff.staffid, User.fullname, Staff.img,
						Staff.birthdate, Staff.city, Staff.wards, Staff.salary, Staff.experience, User.phone, User.email, User.gender))
				.distinct()
				.from(Staff)
				.innerJoin(User).on(User.userid.eq(Staff.staffid))
				.where(Staff.staffid.eq(staffId))
				.fetchOne();
		return result;
	}
	
	@Transactional
	@Override
	public void UpdateStaff(int staffid, String fullname,String file,
			String birthdate, String city, String wards) {
		jpaQueryFactory.update(Staff)
		.where(Staff.staffid.eq(staffid))
		.set(Staff.img, file)
		.set(Staff.birthdate, birthdate)
		.set(Staff.city, city)
		.set(Staff.wards, wards)
		.execute();
		jpaQueryFactory.update(User)
		.where(User.userid.eq(staffid))
		.set(User.fullname, fullname)
		.execute();
	}

	@Transactional
	@Override
	public void addlinkmeet(AddLinkMeetDTO addLinkMeetDTO) {
		jpaQueryFactory.update(bookingEntity)
		.where(bookingEntity.bookId.eq(addLinkMeetDTO.getBookid()))
		.set(bookingEntity.linkmeet, addLinkMeetDTO.getLinkmeet())
		.execute();	
	}

}

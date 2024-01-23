package com.capstoneproject.educonnect.Repository.Impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.GetTutorRequestDTO;
import com.capstoneproject.educonnect.DTO.List4TutorByCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorByCourseDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QFeedback;
import com.capstoneproject.educonnect.Entity.QTeach;
import com.capstoneproject.educonnect.Entity.QTeachTime;
import com.capstoneproject.educonnect.Entity.QTimeline;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Repository.TutorByCourseRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
@Repository
public class TutorByCourseRepositoryImpI implements TutorByCourseRepository{
	private JPAQueryFactory jpaQueryFactory;

	private final QCourse course = QCourse.course;
	private final QTutor tutor = QTutor.tutor;
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QClassCourse classCourse = QClassCourse.classCourse;
	private final QUser user = QUser.user;
	private final QTeach teach = QTeach.teach;
	private final QFeedback feedback = QFeedback.feedback;
	private final QBookingEntity Booking = QBookingEntity.bookingEntity;
	private final QTimeline Timeline = QTimeline.timeline1;
	private final QTeachTime TeachTime = QTeachTime.teachTime;
	public TutorByCourseRepositoryImpI(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

	@Override
	public List<TutorByCourseDTO> ListAllTutorByCourse(int courseid, int page) {
		List<TutorByCourseDTO> result = jpaQueryFactory
				.select(Projections.constructor(TutorByCourseDTO.class,classCourse.classCourseId, course.courseName,classEntity.className, tutor.tutorId, tutor.img, user.fullname, feedback.ranks.avg(), Booking.bookId.count()))		
	            .distinct()
	            .from(tutor)
	            .leftJoin(Booking).on(Booking.tutorid.eq(tutor.tutorId))
	            .innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
	            .leftJoin(feedback).on(feedback.feedbackid.eq(Booking.bookId))
	            .innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
	            .innerJoin(course).on(course.courseId.eq(classCourse.courseid))
	            .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
	            .innerJoin(user).on(user.userid.eq(tutor.tutorId))           
	            .where(teach.classcourseid.eq(courseid).and(user.status.eq(1)))
	            .groupBy(classCourse.classCourseId, course.courseName,classEntity.className, tutor.tutorId, tutor.img, user.fullname)
	            .limit(12).offset(page)
	            .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}	
	}

	@Override
	public List<List4TutorByCourseDTO> List4TutorByCourse(int CourseId) {
		List<List4TutorByCourseDTO> result = jpaQueryFactory
		.select(Projections.constructor(List4TutorByCourseDTO.class,classCourse.classCourseId, course.courseName, classEntity.className,tutor.tutorId, tutor.img, user.fullname, feedback.ranks.avg()))
        .distinct()
        .from(tutor)      
        .leftJoin(Booking).on(Booking.tutorid.eq(tutor.tutorId))
        .innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
        .leftJoin(feedback).on(feedback.feedbackid.eq(Booking.bookId))
        .innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
        .innerJoin(course).on(course.courseId.eq(classCourse.courseid))   
        .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
        .innerJoin(user).on(user.userid.eq(tutor.tutorId))    
        .orderBy(feedback.ranks.avg().desc()).limit(4)
        .where(classCourse.classCourseId.eq(CourseId))
        .groupBy(classCourse.classCourseId, course.courseName, classEntity.className,tutor.tutorId, tutor.img, user.fullname)
        .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}
	}

	@Override
	public List<TutorByCourseDTO> ListSeachTutor(int classcourse, String name) {
		List<TutorByCourseDTO> result = jpaQueryFactory
				.select(Projections.constructor(TutorByCourseDTO.class,classCourse.classCourseId, course.courseName,classEntity.className, tutor.tutorId, tutor.img, user.fullname, feedback.ranks.avg(), Booking.bookId.count()))		
	            .distinct()
	            .from(tutor)
	            .leftJoin(Booking).on(Booking.tutorid.eq(tutor.tutorId))
	            .innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
	            .leftJoin(feedback).on(feedback.feedbackid.eq(Booking.bookId))
	            .innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
	            .innerJoin(course).on(course.courseId.eq(classCourse.courseid))
	            .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
	            .innerJoin(user).on(user.userid.eq(tutor.tutorId)) 
	            .where(user.fullname.like("%" + name + "%").and(teach.classcourseid.eq(classcourse).and(user.status.eq(1))))
	            .groupBy(classCourse.classCourseId, course.courseName,classEntity.className, tutor.tutorId, tutor.img, user.fullname)
	            .limit(12)
	            .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}
	}

	@Override
	public int countpage(int courseid) {
		int result = (int) jpaQueryFactory
	            .select(tutor.tutorId.countDistinct())
	            .from(tutor)
	            .leftJoin(Booking).on(Booking.tutorid.eq(tutor.tutorId))
	            .innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
	            .leftJoin(feedback).on(feedback.feedbackid.eq(Booking.bookId))
	            .innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
	            .innerJoin(course).on(course.courseId.eq(classCourse.courseid))
	            .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
	            .innerJoin(user).on(user.userid.eq(tutor.tutorId))
	            .where(teach.classcourseid.eq(courseid).and(user.status.eq(1)))
	            .groupBy(classCourse.classCourseId, course.courseName, classEntity.className, tutor.tutorId, tutor.img, user.fullname)
	            .fetchCount();
		int endpage = 0;
		endpage = result / 12;
		if(result % 12 != 0) {
			endpage ++;
		}
		return endpage;
	}
	
}

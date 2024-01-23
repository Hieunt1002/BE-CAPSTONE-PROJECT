package com.capstoneproject.educonnect.Repository.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.CourseByClassDTO;
import com.capstoneproject.educonnect.DTO.CourseByTutorDTO;
import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.RegisteredCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorExerciseDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QDiscount;
import com.capstoneproject.educonnect.Entity.QDiscountCourse;
import com.capstoneproject.educonnect.Entity.QLevel;
import com.capstoneproject.educonnect.Entity.QStudent;
import com.capstoneproject.educonnect.Entity.QTeach;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Repository.CourseRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
@Repository
public class CourseRepositoryImpI implements CourseRepository{

	private JPAQueryFactory jpaQueryFactory;
	
	private final QCourse course = QCourse.course;
	private final QClassCourse classCourse = QClassCourse.classCourse;
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QLevel level1 = QLevel.level1;
	private final QTeach Teach = QTeach.teach;
	private final QTutor TuTor = QTutor.tutor;
	private final QBookingEntity Booking = QBookingEntity.bookingEntity;
	private final QStudent student = QStudent.student;
	private final QUser user = QUser.user;
	private final QDiscount discount = QDiscount.discount1;
	private final QDiscountCourse discountCourse = QDiscountCourse.discountCourse;
	
	LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateString = today.format(formatter);
	
	@Autowired
    public CourseRepositoryImpI(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
	
	@Override
	public List<CourseByClassDTO> ListAllCourseByClass(int classcourseid) {
		List<CourseByClassDTO> result = jpaQueryFactory
				.select(Projections.constructor(CourseByClassDTO.class, classCourse.classCourseId, course.courseName, classCourse.classid, level1.level, classEntity.className, classCourse.img, discount.discount, Booking.bookId.count()))
	            .distinct()
	            .from(classCourse)
	            .innerJoin(course).on(course.courseId.eq(classCourse.courseid))
	            .leftJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
	            .leftJoin(discount).on(discount.discountid.eq(discountCourse.discountid).and(discount.startDate.loe(dateString)).and(discount.endDate.goe(dateString)))
	            .leftJoin(Booking).on(classCourse.classCourseId.eq(Booking.classcourseid))
	            .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
	            .innerJoin(level1).on(level1.levelId.eq(classEntity.levelid))
	            .where(classCourse.classid.eq(classcourseid))
	            .groupBy(classCourse.classCourseId, course.courseName, classCourse.classid, level1.level, classEntity.className)
	            .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}
	}

	@Override
	public List<CourseByTutorDTO> List4CourseByTutor(int tutorid) {
		List<CourseByTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(CourseByTutorDTO.class, classCourse.classCourseId, course.courseName, classCourse.classid, level1.level, classEntity.className, classCourse.img, discount.discount, Booking.bookId.count()))
				.distinct()
				.from(TuTor) 
				.innerJoin(Teach).on(Teach.tutorid.eq(TuTor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(Teach.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.leftJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
	            .leftJoin(discount).on(discount.discountid.eq(discountCourse.discountid))
				.leftJoin(Booking).on(Booking.classcourseid.eq(classCourse.classCourseId))
				.innerJoin(level1).on(level1.levelId.eq(classEntity.levelid))
				.where(TuTor.tutorId.eq(tutorid))
				.groupBy(classCourse.classCourseId, course.courseName, classCourse.classid, level1.level, classEntity.className, classCourse.img)	
				.orderBy(Booking.bookId.count().desc())
				.limit(4)
	            .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else { 
			return result;
		}
	}

	@Override
	public List<RegisteredCourseDTO> ListAllRegisteredCourse(int studentId) {
		List<RegisteredCourseDTO> result = jpaQueryFactory
				.select(Projections.constructor(RegisteredCourseDTO.class,Booking.studentid,Booking.bookId, classCourse.classCourseId, course.courseName,classEntity.className, Booking.dateregister, Booking.startDate, Booking.endDate, Booking.status))
				.distinct()
				.from(classCourse)
				 	.innerJoin(Booking).on(Booking.classcourseid.eq(classCourse.classCourseId))
	                .innerJoin(course).on(course.courseId.eq(classCourse.courseid))  
	                .innerJoin(classCourse).on(classCourse.courseid.eq(course.courseId))
	                .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
	                .where(Booking.studentid.eq(studentId).and(Booking.datePay.isNotNull()))	                
	                .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}	
	}

	@Override
	public List<CourseByClassDTO> ListCourseForStudent(int classcourseid, int studentid) {
		List<Integer> classCourseIds = jpaQueryFactory
		        .select(Booking.classcourseid)
		        .from(Booking)
		        .innerJoin(student).on(Booking.studentid.eq(student.studentid))
		        .where(student.studentid.eq(studentid)
		                .and(Booking.endDate.goe(dateString))
		                .and(Booking.status.in(1, 2)))
		        .fetch();
		
		List<CourseByClassDTO> result = jpaQueryFactory
		        .select(Projections.constructor(CourseByClassDTO.class, classCourse.classCourseId,
		                course.courseName, classCourse.classid, level1.level, classEntity.className,
		                classCourse.img, discount.discount, Booking.bookId.count()))
		        .distinct()
		        .from(classCourse)
		        .innerJoin(course).on(course.courseId.eq(classCourse.courseid))
		        .leftJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
		        .leftJoin(discount).on(discount.discountid.eq(discountCourse.discountid).and(discount.startDate.loe(dateString)).and(discount.endDate.goe(dateString)))
		        .leftJoin(Booking).on(classCourse.classCourseId.eq(Booking.classcourseid))
		        .innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
		        .innerJoin(level1).on(level1.levelId.eq(classEntity.levelid))
		        .where(classCourse.classid.eq(classcourseid)
		                .and(classCourse.classCourseId.notIn(classCourseIds)))
		        .groupBy(classCourse.classCourseId,
		                course.courseName, classCourse.classid, level1.level, classEntity.className,
		                classCourse.img)
		        .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}
	}

	@Override
	public TutorExerciseDTO tutorexercise(int bookid) {
		TutorExerciseDTO result = jpaQueryFactory.select(Projections.constructor(TutorExerciseDTO.class,TuTor.tutorId, user.fullname, course.courseName, classEntity.className, Booking.startDate, Booking.endDate))
				.distinct()
				.from(TuTor)
				.innerJoin(Booking).on(Booking.tutorid.eq(TuTor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(Booking.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(user).on(user.userid.eq(TuTor.tutorId))
				.where(Booking.bookId.eq(bookid))
				.fetchFirst();
		return result;
	}

	@Override
	public List<CourseDTO> listcourse() {
		List<CourseDTO> result = jpaQueryFactory.select(Projections.constructor(CourseDTO.class, course.courseId,
				course.courseName))
				.from(course)
				.fetch();
		return result;
	}

	@Override
	public List<CourseDTO> listcoursebydiscountid(int discountid) {
		List<CourseDTO> result = jpaQueryFactory.select(Projections.constructor(CourseDTO.class, 
				course.courseId, course.courseName))
				.from(course)
				.innerJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
				.where(discountCourse.discountid.eq(discountid))
				.fetch();
		return result;
	}
}

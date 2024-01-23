package com.capstoneproject.educonnect.Repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.ViewInfoClassroomDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassroom;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QExercise;
import com.capstoneproject.educonnect.Repository.ClassroomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ClassroomRepositoryIpmI implements ClassroomRepository {

	
	private JPAQueryFactory jpaQueryFactory;
	
	private final QCourse Course = QCourse.course;
	private final QClassroom Classroom = QClassroom.classroom;
	private final QExercise Exercise = QExercise.exercise;
	private final QBookingEntity Booking = QBookingEntity.bookingEntity;
	private final QClassCourse Classcourse = QClassCourse.classCourse;
	
	@Autowired
	public ClassroomRepositoryIpmI(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	@Override
	public ViewInfoClassroomDTO ViewInfoClassroom(int classroomId) {
		ViewInfoClassroomDTO result = jpaQueryFactory
				.select(Projections.constructor(ViewInfoClassroomDTO.class, Course.courseId, Course.courseName, Classroom.classroomId, Classroom.exerciseid, Classroom.nameClassroom, Classroom.link))
				.distinct()
				.from(Classroom)
				.innerJoin(Exercise).on(Exercise.exerciseId.eq(Classroom.exerciseid))
				.innerJoin(Booking).on(Booking.bookId.eq(Exercise.bookid))
				.innerJoin(Classcourse).on(Classcourse.classCourseId.eq(Booking.classcourseid))
				.innerJoin(Course).on(Course.courseId.eq(Classcourse.courseid))
				.where(Classroom.classroomId.eq(classroomId))
				.fetchOne();
		return result;
	}
}

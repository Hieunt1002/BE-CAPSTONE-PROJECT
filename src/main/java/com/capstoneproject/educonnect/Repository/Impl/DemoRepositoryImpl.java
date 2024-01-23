package com.capstoneproject.educonnect.Repository.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DetailDemoDTO;
import com.capstoneproject.educonnect.DTO.ListDemoDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.DTO.DemoDTO;
import com.capstoneproject.educonnect.Entity.BookingEntity;
import com.capstoneproject.educonnect.Entity.Demo;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QDemo;
import com.capstoneproject.educonnect.Entity.QExercise;
import com.capstoneproject.educonnect.Repository.DemoRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class DemoRepositoryImpl implements DemoRepository {

	private JPAQueryFactory jpaQueryFactory;
	
	
	private final QClassCourse classCourse = QClassCourse.classCourse;
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QCourse course = QCourse.course;
	private final QDemo demo = QDemo.demo1;
	private final QBookingEntity booking = QBookingEntity.bookingEntity;
	private final QExercise exercise = QExercise.exercise;
	
	
	@Override
	public List<ListDemoDTO> listdemo(int classid, int page) {
		List<ListDemoDTO> result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
				classCourse.classCourseId, course.courseName, classEntity.className,
				demo.demoId, demo.img, demo.demo, demo.files))
				.distinct()
				.from(demo)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(demo.classcourseid.eq(classid))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	
	@Override
	public List<ListDemoDTO> listAllDemos(int classcourseid) {
		List<ListDemoDTO> result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
				classCourse.classCourseId, course.courseName, classEntity.className,
				demo.demoId, demo.img, demo.demo, demo.files))
				.distinct()
				.from(demo)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(booking).on(booking.classcourseid.eq(classCourse.classCourseId))
				.innerJoin(exercise).on(exercise.bookid.eq(booking.bookId))
				.where(exercise.exerciseId.eq(classcourseid))
				.fetch();
		return result;
	}
	
	@Override
	public List<ListDemoDTO> listAllDemo() {
		List<ListDemoDTO> result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
				classCourse.classCourseId, course.courseName, classEntity.className,
				demo.demoId, demo.img, demo.demo, demo.files))
				.distinct()
				.from(demo)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.fetch();
		return result;
	}

	@Override
	public long totalpage(int classcourseid) {
		long result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
				classCourse.classCourseId, course.courseName, classEntity.className,
				demo.demoId, demo.img, demo.demo, demo.files))
				.distinct()
				.from(demo)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(demo.classcourseid.eq(classcourseid))
				.fetchCount();
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}


	@Override
	public List<ListDemoDTO> listDemoWithId(int classid) {
		List<ListDemoDTO> result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
				classCourse.classCourseId, course.courseName, classEntity.className,
				demo.demoId, demo.img, demo.demo, demo.files))
				.distinct()
				.from(demo)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(classEntity.classId.eq(classid))
				.fetch();
		return result;
	}

	
	@Override
	public List<ListDemoDTO> listDemoWithString(int courseid) {
		 List<ListDemoDTO> result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
					classCourse.classCourseId, course.courseName, classEntity.className,
					demo.demoId, demo.img, demo.demo, demo.files))
					.distinct()
					.from(demo)
					.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
					.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
					.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
					.where(course.courseId.eq(courseid))
					.fetch();
			return result;
	}


	@Override
	public List<ListDemoDTO> listDemoWithAll(int classid, int courseName) {
		 List<ListDemoDTO> result = jpaQueryFactory.select(Projections.constructor(ListDemoDTO.class, 
					classCourse.classCourseId, course.courseName, classEntity.className,
					demo.demoId, demo.img, demo.demo, demo.files))
					.distinct()
					.from(demo)
					.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
					.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
					.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
					.where(course.courseId.eq(courseName).and(classEntity.classId.eq(classid)))
					.fetch();
			return result;
	}

	@Override
	public List<LopDTO> classentity() {
		List<LopDTO> result = jpaQueryFactory.select(Projections.constructor(LopDTO.class, classEntity.classId, classEntity.className, classEntity.levelid))
				.distinct()
				.from(classEntity)
				.innerJoin(classCourse).on(classEntity.classId.eq(classEntity.classId))
				.innerJoin(demo).on(demo.classcourseid.eq(classCourse.classCourseId))
				.fetch();
		return result;
	}


	@Override
	public List<CourseDTO> listcourse() {
		List<CourseDTO> result = jpaQueryFactory.select(Projections.constructor(CourseDTO.class, course.courseId, course.courseName))
				.distinct()
				.from(course)
				.innerJoin(classCourse).on(classCourse.courseid.eq(course.courseId))
				.innerJoin(demo).on(demo.classcourseid.eq(classCourse.classCourseId))
				.fetch();
		return result;
	}


	@Override
	public DetailDemoDTO detaildemo(int demoid) {
		DetailDemoDTO result = jpaQueryFactory.select(Projections.constructor(DetailDemoDTO.class, 
				demo.demoId, classEntity.classId,
				course.courseId, demo.demo, demo.files, demo.img))
				.distinct()
				.from(demo)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(demo.demoId.eq(demoid))
				.fetchFirst();
		return result;
	}

}

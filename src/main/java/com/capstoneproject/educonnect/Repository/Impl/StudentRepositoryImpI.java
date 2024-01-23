package com.capstoneproject.educonnect.Repository.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.DetailFeedbackDTO;
import com.capstoneproject.educonnect.DTO.FeedBackDetailDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackBookingOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.DTO.StudentDTO;
import com.capstoneproject.educonnect.DTO.StudentProfileDTO;
import com.capstoneproject.educonnect.DTO.UFeedbackDTO;
import com.capstoneproject.educonnect.Entity.BookingEntity;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QFeedback;
import com.capstoneproject.educonnect.Entity.QLevel;
import com.capstoneproject.educonnect.Entity.QStudent;
import com.capstoneproject.educonnect.Entity.QTeachTime;
import com.capstoneproject.educonnect.Entity.QTimeline;
import com.capstoneproject.educonnect.Entity.QTrylearning;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Repository.StudentRepositoryT;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class StudentRepositoryImpI implements StudentRepositoryT {

	private JPAQueryFactory jpaQueryFactory;
	
	private final QStudent Student = QStudent.student;
	private final QUser User = QUser.user;
	private final QTimeline Timeline = QTimeline.timeline1;
	private final QClassEntity Class = QClassEntity.classEntity;
	private final QTeachTime TeachTime = QTeachTime.teachTime;
	private final QBookingEntity bookingEntity = QBookingEntity.bookingEntity;
	private final QTutor tutor = QTutor.tutor;
	private final QFeedback feedback = QFeedback.feedback;
	private final QClassCourse classCourse =QClassCourse.classCourse;
	private final QCourse course = QCourse.course;
	private final QUser studentUser = new QUser("studentUser");
	private final QUser tutorUser = new QUser("tutorUser");
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QTrylearning trylearning = QTrylearning.trylearning;
	private final QLevel level = QLevel.level1;
	
	LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String futureDate = today.plusDays(5).format(formatter);
    String dateString = today.format(formatter);
	String dateStrings = today.format(formatter);
	
	@Autowired
    public StudentRepositoryImpI(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

	@Override
	public StudentProfileDTO listInfomationStudent(int studentid) {
		StudentProfileDTO result = jpaQueryFactory
				.select(Projections.constructor(StudentProfileDTO.class, User.fullname, Student.studentid, Student.img, User.gender, Student.birthdate, 
						 User.phone, User.email,Student.city, Student.wards, Class.classId, Class.className))
	            .distinct()
	            .from(Student)
	            .innerJoin(Class).on(Class.classId.eq(Student.classId))
	            .innerJoin(User).on(User.userid.eq(Student.studentid))
	            .where(User.userid.eq(studentid))
	            .fetchOne();		
			return result;
	
	}
	@Transactional
	@Override
	public void UpdateStudent(String fullname, int studentid,
			String file, int gender, String birthdate, String phone, String city,
			String wards, int classentity) {
		jpaQueryFactory.update(Student)
		.where(Student.studentid.eq(studentid))		
		.set(Student.img, file)		
		.set(Student.birthdate, birthdate)
		.set(Student.city, city)
		.set(Student.wards, wards)
		.set(Student.classId, classentity)
		.execute();
		jpaQueryFactory.update(User)
		.where(User.userid.eq(studentid))
		.set(User.fullname, fullname)
		.set(User.gender, gender)
		.set(User.phone, phone)
		.execute();
	}

	@Override
	public List<ListFeedbackDTO> listFeedback(int studentid) {
		List<ListFeedbackDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListFeedbackDTO.class,
						Student.studentid,studentUser.fullname,bookingEntity.startDate ,bookingEntity.endDate, bookingEntity.bookId, 
						tutor.tutorId, tutorUser.fullname,course.courseName ,classEntity.className , bookingEntity.bookId))
				.distinct()
				.from(bookingEntity)
				.leftJoin(feedback).on(bookingEntity.bookId.eq(feedback.feedbackid))
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(Student).on(Student.studentid.eq(bookingEntity.studentid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(studentUser).on(studentUser.userid.eq(Student.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(tutor.tutorId))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.where(bookingEntity.endDate.loe(futureDate).and(bookingEntity.endDate.goe(dateString)).and(Student.studentid.eq(studentid))
						.and(bookingEntity.bookId.notIn(
								jpaQueryFactory.select(feedback.feedbackid)
								.distinct()
								.from(feedback)
								.where(feedback.feedbackid.eq(bookingEntity.bookId))
								)))
				.fetch();
		return result;	
	}

	@Override
	public BookingEntity detailBookingDTO(int bookid) {
		BookingEntity result  = jpaQueryFactory.select(bookingEntity)
				.distinct()
				.from(bookingEntity)
				.where(bookingEntity.bookId.eq(bookid))
				.fetchFirst();
		return result;
	}

	@Override
	public DetailFeedbackDTO detailFeedbackDTO(int feedbackid, int studentid) {
		DetailFeedbackDTO result = jpaQueryFactory
				.select(Projections.constructor(DetailFeedbackDTO.class,
						Student.studentid,studentUser.fullname, bookingEntity.endDate, bookingEntity.bookId, 
						tutor.tutorId, tutorUser.fullname,course.courseName, 
						feedback.feedbackid, feedback.notes, feedback.ranks))
				.distinct()
				.from(bookingEntity)
				.leftJoin(feedback).on(bookingEntity.bookId.eq(feedback.feedbackid))
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(Student).on(Student.studentid.eq(bookingEntity.studentid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(studentUser).on(studentUser.userid.eq(Student.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(tutor.tutorId))
				.where(feedback.feedbackid.eq(feedbackid).and(Student.studentid.eq(studentid)))
				.fetchFirst();
		return result;	
	}
	
	@Transactional
	@Override
	public void updateFeedback(UFeedbackDTO uFeedbackDTO) {
		jpaQueryFactory.update(feedback).where(feedback.feedbackid.eq(uFeedbackDTO.getFeedbackid()))
		.set(feedback.notes, uFeedbackDTO.getNotes())
		.set(feedback.ranks, uFeedbackDTO.getRanks())
		.execute();		
	}

	@Override
	public List<LopDTO> listclass() {
		List<LopDTO> result = jpaQueryFactory.select(Projections.constructor(LopDTO.class, classEntity.classId, classEntity.className, level.levelId))
				.distinct()
				.from(classEntity)
				.innerJoin(level).on(level.levelId.eq(classEntity.levelid))
				.fetch();
		return result;
	}

	@Override
	public List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(int tutorid) {
		 List<ListFeedbackBookingOfTutorDTO> result = jpaQueryFactory
					.select(Projections.constructor(ListFeedbackBookingOfTutorDTO.class,
							tutor.tutorId, bookingEntity.studentid, User.fullname, 
							bookingEntity.classcourseid,Student.img, course.courseName, classEntity.className,bookingEntity.dateregister, 
							feedback.feedbackid, feedback.notes, feedback.ranks))
					.distinct()
					.from(feedback)
					.leftJoin(bookingEntity).on(bookingEntity.bookId.eq(feedback.feedbackid))
					.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
					.innerJoin(Student).on(Student.studentid.eq(bookingEntity.studentid))
					.innerJoin(User).on(User.userid.eq(Student.studentid))
					.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
					.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
					.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
		            .where(tutor.tutorId.eq(tutorid))
		            .fetch();
		return result;
	}
	@Override
	public boolean checkstudent(int studentid) {
		String result = jpaQueryFactory
	            .select(Student.city)
	            .from(Student)
	            .where(Student.studentid.eq(studentid))
	            .fetchFirst();

	    if (result == null || result.isEmpty()) {
	        return false;
	    }

	    return true;
	}

	@Override
	public List<ListFeedbackDTO> listFeedbacktutor(int studentid) {
		List<ListFeedbackDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListFeedbackDTO.class,
						Student.studentid,studentUser.fullname,bookingEntity.startDate ,bookingEntity.endDate, bookingEntity.bookId, 
						tutor.tutorId, tutorUser.fullname,course.courseName ,classEntity.className , bookingEntity.bookId))
				.distinct()
				.from(bookingEntity)
				.leftJoin(feedback).on(bookingEntity.bookId.eq(feedback.feedbackid))
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(Student).on(Student.studentid.eq(bookingEntity.studentid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(studentUser).on(studentUser.userid.eq(Student.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(tutor.tutorId))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.where(bookingEntity.endDate.goe(dateStrings).and(Student.studentid.eq(studentid))
						.and(bookingEntity.bookId.in(
								jpaQueryFactory.select(feedback.feedbackid)
								.distinct()
								.from(feedback)
								.where(feedback.feedbackid.eq(bookingEntity.bookId))
								)))
				.fetch();
		return result;
	}

	@Override
	public FeedBackDetailDTO detailfeedback(int feedbackid) {
		FeedBackDetailDTO result = jpaQueryFactory.select(Projections.constructor(FeedBackDetailDTO.class, feedback.feedbackid, feedback.notes,
				feedback.ranks))
				.from(feedback)
				.where(feedback.feedbackid.eq(feedbackid))
				.fetchFirst();
		return result;
	}

	@Override
	public StudentDTO getStudentByBookId(int bookid) {
		StudentDTO result = jpaQueryFactory.select(Projections.constructor(StudentDTO.class,Class.className, User.fullname ))
				.from(Student)
				.innerJoin(User).on(User.userid.eq(Student.studentid))
				.innerJoin(Class).on(Class.classId.eq(Student.classId))
				.innerJoin(classCourse).on(classCourse.classid.eq(Class.classId))
				.innerJoin(bookingEntity).on(bookingEntity.classcourseid.eq(classCourse.classCourseId))
				.where(bookingEntity.bookId.eq(bookid))
				.fetchFirst();

		return result;
	}
}

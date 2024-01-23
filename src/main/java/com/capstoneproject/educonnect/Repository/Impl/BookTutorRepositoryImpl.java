package com.capstoneproject.educonnect.Repository.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.capstoneproject.educonnect.DTO.BookLesson;
import com.capstoneproject.educonnect.DTO.InforDTO;
import com.capstoneproject.educonnect.DTO.ListTimelineOfTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QDiscount;
import com.capstoneproject.educonnect.Entity.QDiscountCourse;
import com.capstoneproject.educonnect.Entity.QStudent;
import com.capstoneproject.educonnect.Entity.QTeachTime;
import com.capstoneproject.educonnect.Entity.QTimeBook;
import com.capstoneproject.educonnect.Entity.QTimeline;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Entity.Qlesson;
import com.capstoneproject.educonnect.Repository.BookTutorRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BookTutorRepositoryImpl implements BookTutorRepository{

	private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	public BookTutorRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateString = today.format(formatter);
    LocalDate todate = LocalDate.now();
    LocalDate futureDate = today.plusDays(7);
    String dateStrings = futureDate.format(formatter);
	
	private QBookingEntity bookingEntity = QBookingEntity.bookingEntity;
	private QUser user = QUser.user;
	private QStudent student = QStudent.student;
	private QTutor tutor = QTutor.tutor;
	private QClassCourse classCourse = QClassCourse.classCourse;
	private QCourse course = QCourse.course;
	private QDiscountCourse discountCourse = QDiscountCourse.discountCourse;
	private QDiscount discount = QDiscount.discount1;
	private QClassEntity classEntity = QClassEntity.classEntity;
	private QTeachTime teachTime = QTeachTime.teachTime;
	private QTimeline timeline = QTimeline.timeline1;
	private Qlesson lesson = Qlesson.lesson;
	private QTimeBook timeBook = QTimeBook.timeBook;
	
	@Transactional
	@Override
	public void payDone(int studentid, int status) {
		jpaQueryFactory.update(bookingEntity)
		.where(bookingEntity.studentid.eq(studentid).and(bookingEntity.status.eq(0)))
		.set(bookingEntity.datePay, dateString)
		.set(bookingEntity.status, status)
		.execute();
	}
	
	@Transactional
	@Override
	public void payDonebank(int studentid, int status) {
		jpaQueryFactory.update(bookingEntity)
		.where(bookingEntity.studentid.eq(studentid).and(bookingEntity.status.eq(0)))
		.set(bookingEntity.status, status)
		.execute();
	}

	@Override
	public String mailStudent(int studentid) {
		String result = jpaQueryFactory.select(user.email)
				.distinct()
				.from(bookingEntity)
				.innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.where(bookingEntity.datePay.isNull().and(bookingEntity.studentid.eq(studentid)))
				.fetchFirst();
		return result;
	}

	@Override
	public String mailTutorByStudent(int studentid) {
		String result = jpaQueryFactory.select(user.email)
				.distinct()
				.from(bookingEntity)
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(bookingEntity.datePay.isNull().and(bookingEntity.studentid.eq(studentid)))
				.fetchFirst();
		return result;
	}

	@Override
	public float pricecourse(int studentid) {
		float result = jpaQueryFactory.select(tutor.price)
				.distinct()
				.from(bookingEntity)
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.leftJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
				.leftJoin(discount).on(discount.discountid.eq(discountCourse.discountid))
				.where(bookingEntity.datePay.isNull().and(bookingEntity.studentid.eq(studentid)))
				.fetchOne();
		Float discounts = 
				jpaQueryFactory.select(discount.discount)
				.distinct()
				.from(bookingEntity)
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.leftJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
				.leftJoin(discount).on(discount.discountid.eq(discountCourse.discountid))
				.where(bookingEntity.datePay.isNull()
						.and(bookingEntity.studentid.eq(studentid))
						.and(discount.startDate.loe(dateString))
						.and(discount.endDate.goe(dateString)))
				.fetchFirst();
		float discountsValue = (discounts != null) ? discounts.floatValue() : 0;
		return result * ((100 - discountsValue) / 100);
	}

	@Override
	public InforDTO inforstudent(int studentid) {
		InforDTO result = jpaQueryFactory.select(Projections.constructor(InforDTO.class, user.fullname, course.courseName, classEntity.className))
				.distinct()
				.from(bookingEntity)
				.innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(bookingEntity.datePay.isNull().and(bookingEntity.studentid.eq(studentid)))
				.fetchFirst();
		return result;
	}

	@Override
	public InforDTO infortutor(int studentid) {
		InforDTO result = jpaQueryFactory.select(Projections.constructor(InforDTO.class, user.fullname, course.courseName, classEntity.className))
				.distinct()
				.from(bookingEntity)
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(bookingEntity.datePay.isNull().and(bookingEntity.studentid.eq(studentid)))
				.fetchFirst();
		return result;
	}

	@Override
	public List<ListTimelineOfTutorDTO> listTimelineOfTutor(int tutorid) {
		List<ListTimelineOfTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListTimelineOfTutorDTO.class, timeline.timeId, timeline.timeline, teachTime.tutorId))
				.distinct()
				.from(timeline)
				.innerJoin(teachTime).on(teachTime.timeId.eq(timeline.timeId))
				.innerJoin(tutor).on(tutor.tutorId.eq(teachTime.tutorId))
				.where(tutor.tutorId.eq(tutorid))
				.fetch();
		return result;
	}

	@Override
	public List<BookLesson> listtimeandlesson(int tutorid, int studentid) {
		 List<BookLesson> result = jpaQueryFactory
		            .select(Projections.constructor(BookLesson.class,timeline.timeId, lesson.lessonId, timeline.timeline, lesson.lessonline))
		            .distinct()
		            .from(tutor)
		            .innerJoin(teachTime).on(teachTime.tutorId.eq(tutor.tutorId))
		            .innerJoin(lesson).on(lesson.lessonId.eq(teachTime.lessonid))
		            .innerJoin(timeline).on(timeline.timeId.eq(teachTime.timeId))
		            .innerJoin(bookingEntity).on(bookingEntity.tutorid.eq(tutor.tutorId))
		            .where(tutor.tutorId.eq(tutorid)
		            		.and(JPAExpressions
		    		                .selectOne()
		    		                .from(student)
		    		                .leftJoin(bookingEntity).on(bookingEntity.studentid.eq(student.studentid))
		    		                .innerJoin(timeBook).on(timeBook.bookid.eq(bookingEntity.bookId))
		    		                .where(timeBook.timeid.eq(timeline.timeId)
		    		                    .and(timeBook.lessonid.eq(lesson.lessonId).and(bookingEntity.endDate.goe(dateString))
		    		                    		.and(bookingEntity.startDate.loe(dateStrings))
		    		                    		.and(student.studentid.eq(studentid))
		    		                    		.and(bookingEntity.status.in(1, 2)))
		    		                )
		    		                .notExists()
		    		            )
		            .and(JPAExpressions
		                .selectOne()
		                .from(tutor)
		                .leftJoin(bookingEntity).on(bookingEntity.tutorid.eq(tutor.tutorId))
		                .innerJoin(timeBook).on(timeBook.bookid.eq(bookingEntity.bookId))
		                .where(timeBook.timeid.eq(timeline.timeId)
		                    .and(timeBook.lessonid.eq(lesson.lessonId).and(bookingEntity.endDate.goe(dateString))
		                    		.and(bookingEntity.startDate.loe(dateStrings))
		                    		.and(tutor.tutorId.eq(tutorid))
		                    		.and(bookingEntity.status.in(1, 2)))
		                )
		                .notExists()
		            ))
		            .fetch();
		    return result;
	}
	
	@Transactional
	@Override
	public void cancelbook(int studentid) {
		jpaQueryFactory.delete(bookingEntity)
		.where(bookingEntity.studentid.eq(studentid).and(bookingEntity.datePay.isNull()))
		.execute();
	}

	@Override
	public int bookid(int studentid) {
		Integer result = jpaQueryFactory.select(bookingEntity.bookId)
				.distinct()
				.from(bookingEntity)
				.where(bookingEntity.studentid.eq(studentid).and(bookingEntity.datePay.isNull()))
				.fetchFirst();
		return result == null ? 0 : result;
	}

	@Transactional
    @Override
    public void checkcalender(int bookid) {
		LocalDate date = date(bookid);
		LocalDate futureDate = date.plusDays(7);
		String dateString = futureDate.format(formatter);
		
        jpaQueryFactory
            .update(bookingEntity)
            .set(bookingEntity.endDate, dateString)
            .where(bookingEntity.bookId.eq(bookid))
            .execute();
    }

	public LocalDate date(int bookid) {
		String result = jpaQueryFactory.select(bookingEntity.endDate)
				.from(bookingEntity)
				.where(bookingEntity.bookId.eq(bookid))
				.fetchFirst();
		
		if (result != null) {
            return LocalDate.parse(result, formatter);
        } else {
            return LocalDate.now();
        }
	}

	@Override
	public String emailStudent(int bookid) {
		String result = jpaQueryFactory.select(user.email)
				.distinct()
				.from(user)
				.innerJoin(student).on(student.studentid.eq(user.userid))
				.innerJoin(bookingEntity).on(bookingEntity.studentid.eq(student.studentid))
				.where(bookingEntity.bookId.eq(bookid))
				.fetchFirst();
		return result;
	}

	@Override
	public String emailTutor(int bookid) {
		String result = jpaQueryFactory.select(user.email)
				.distinct()
				.from(user)
				.innerJoin(tutor).on(tutor.tutorId.eq(user.userid))
				.innerJoin(bookingEntity).on(bookingEntity.tutorid.eq(tutor.tutorId))
				.where(bookingEntity.bookId.eq(bookid))
				.fetchFirst();
		return result;
	}

	@Transactional
	@Override
	public void deletecalender(int timeid, int lessonid, int tutorid) {
		jpaQueryFactory.delete(teachTime)
		.where(teachTime.timeId.eq(timeid).and(teachTime.lessonid.eq(lessonid)).and(teachTime.tutorId.eq(tutorid)))
		.execute();
	}

	@Override
	public List<StaffViewProfileTimelineTutorDTO> learntimestudentandtutor(int studentid) {
		List<StaffViewProfileTimelineTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileTimelineTutorDTO.class, timeline.timeId,
						timeline.timeline, lesson.lessonline))
				.distinct()
				.from(bookingEntity)
				.innerJoin(timeBook).on(timeBook.bookid.eq(bookingEntity.bookId))
				.innerJoin(timeline).on(timeline.timeId.eq(timeBook.timeid))
				.innerJoin(lesson).on(lesson.lessonId.eq(timeBook.lessonid))
				.where(bookingEntity.studentid.eq(studentid))
				.orderBy(lesson.lessonId.asc())
				.fetch();
		return result;
	}

	@Override
	public List<StaffViewProfileTimelineTutorDTO> learntimestudent(int studentid) {
		List<StaffViewProfileTimelineTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileTimelineTutorDTO.class, timeline.timeId,
						timeline.timeline, lesson.lessonline))
				.distinct()
				.from(bookingEntity)
				.innerJoin(timeBook).on(timeBook.bookid.eq(bookingEntity.bookId))
				.innerJoin(timeline).on(timeline.timeId.eq(timeBook.timeid))
				.innerJoin(lesson).on(lesson.lessonId.eq(timeBook.lessonid))
				.where(bookingEntity.studentid.eq(studentid))
				.orderBy(lesson.lessonId.desc())
				.limit(3)
				.fetch();
		return result;
	}

	@Override
	public boolean checkbook(int studentid) {
		String check = jpaQueryFactory.select(bookingEntity.datePay)
				.from(bookingEntity)
				.where(bookingEntity.studentid.eq(studentid).and(bookingEntity.datePay.isNull()))
				.fetchFirst();
		if(check != null) {
			return true;
		}else {
			return false;
		}
	}

	@Transactional
	@Override
	public void deletetimebook(int bookid) {
		Integer check = jpaQueryFactory.select(bookingEntity.bookId)
				.from(timeBook)
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(timeBook.bookid))
				.where(bookingEntity.studentid.eq(bookid).and(bookingEntity.datePay.isNull()))
				.fetchFirst();
		if(check != null) {
			jpaQueryFactory.delete(timeBook)
			.where(timeBook.bookid.eq(check))
			.execute();
		}
	}

	@Transactional
	@Override
	public void acceptcardpaymet(int bookid) {
		jpaQueryFactory.update(bookingEntity)
		.where(bookingEntity.studentid.eq(bookid).and(bookingEntity.datePay.isNull()))
		.set(bookingEntity.datePay, dateString)
		.execute();
	}

	
	@Transactional
	@Override
	public void cancelcardpay(int bookid) {
		jpaQueryFactory.delete(timeBook)
		.where(timeBook.bookid.eq(bookid))
		.execute();
		jpaQueryFactory.delete(bookingEntity)
		.where(bookingEntity.bookId.eq(bookid))
		.execute();
	}

	@Override
	public int checktime(int bookid) {
		Integer result = jpaQueryFactory.select(timeBook.bookid)
				.from(timeBook)
				.where(timeBook.bookid.eq(bookid))
				.fetchFirst();
		return result == null ? 0 : result;
	}

	@Transactional
	@Override
	public void deletetimebookPay(int bookid) {
		jpaQueryFactory.delete(timeBook)
			.where(timeBook.bookid.eq(bookid))
			.execute();
	}
}

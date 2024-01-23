package com.capstoneproject.educonnect.Repository.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.AcceptFileDTO;
import com.capstoneproject.educonnect.DTO.CountTutorAndStudentByClassDTO;
import com.capstoneproject.educonnect.DTO.ListStaffByAdminDTO;
import com.capstoneproject.educonnect.DTO.ListStudentOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListTutorOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListWaitForConfirmTutorDTO;
import com.capstoneproject.educonnect.DTO.PayForTuttorDTO;
import com.capstoneproject.educonnect.DTO.RadarChartDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileClasscourseTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentTrylearnDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTutorDTO;
import com.capstoneproject.educonnect.DTO.USalaryStaffDTO;
import com.capstoneproject.educonnect.DTO.USalaryTutorDTO;
import com.capstoneproject.educonnect.DTO.ViewStudentTrylearnDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QFiles;
import com.capstoneproject.educonnect.Entity.QPayment;
import com.capstoneproject.educonnect.Entity.QStaff;
import com.capstoneproject.educonnect.Entity.QStudent;
import com.capstoneproject.educonnect.Entity.QTeach;
import com.capstoneproject.educonnect.Entity.QTeachTime;
import com.capstoneproject.educonnect.Entity.QTimeBook;
import com.capstoneproject.educonnect.Entity.QTimeline;
import com.capstoneproject.educonnect.Entity.QTrylearning;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Entity.Qlesson;
import com.capstoneproject.educonnect.Entity.User;
import com.capstoneproject.educonnect.Repository.StaffRepo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class StaffImpl implements StaffRepo {
	private JPAQueryFactory jpaQueryFactory;
	
	private final QStaff staff = QStaff.staff;
	private final QStudent student = QStudent.student;
	private final QTutor tutor = QTutor.tutor;
	private final QUser user = QUser.user;
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QClassCourse classCourse = QClassCourse.classCourse;
	private final QCourse course = QCourse.course;
	private final QTrylearning trylearning = QTrylearning.trylearning;
	private final QTeach teach = QTeach.teach;
	private final QTeachTime teachTime = QTeachTime.teachTime;
	private final QTimeline timeline = QTimeline.timeline1;
	private final QBookingEntity bookingEntity = QBookingEntity.bookingEntity;
	private final QTimeBook timeBook = QTimeBook.timeBook;
	private final QUser studentUser = new QUser("studentUser");
	private final QUser tutorUser = new QUser("tutorUser");
	private final Qlesson lesson = Qlesson.lesson;
	private final QPayment payment = QPayment.payment;
	private final QFiles files = QFiles.files1;
	
	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String dateString = today.format(formatter);
	
	@Autowired
	public StaffImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	@Override
	public List<ViewStudentTrylearnDTO> viewStudentTrylearn(int page) {
		List<ViewStudentTrylearnDTO> result = jpaQueryFactory
				.select(Projections.constructor(ViewStudentTrylearnDTO.class, tutor.tutorId,
						student.studentid, studentUser.fullname, tutorUser.fullname, student.img,  
						classCourse.classCourseId,course.courseName, classEntity.className,
						trylearning.trylearningid, trylearning.dateregister, trylearning.status, studentUser.phone, studentUser.email))
				.distinct()
				.from(trylearning)
				.innerJoin(tutor).on(tutor.tutorId.eq(trylearning.tutorid))
				.innerJoin(student).on(student.studentid.eq(trylearning.studentid))
				.innerJoin(studentUser).on(studentUser.userid.eq(student.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(trylearning.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	@Override
	public StaffViewProfileStudentTrylearnDTO staffViewProfileStudentTrylearnDTO(int trylearningid) {
		StaffViewProfileStudentTrylearnDTO result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileStudentTrylearnDTO.class, trylearning.studentid,
						studentUser.fullname,student.img,student.birthdate,student.city, student.wards, 
						studentUser.email,studentUser.phone, 
						classEntity.className,course.courseName, trylearning.tutorid, tutorUser.fullname, staff.staffid ))
				.distinct()
				.from(trylearning)
				.innerJoin(tutor).on(tutor.tutorId.eq(trylearning.tutorid))
				.innerJoin(student).on(student.studentid.eq(trylearning.studentid))
				.innerJoin(staff).on(staff.staffid.eq(tutor.staffid))
				.innerJoin(studentUser).on(studentUser.userid.eq(trylearning.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(trylearning.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(trylearning.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(trylearning.trylearningid.eq(trylearningid))
				.fetchFirst();
		return result;
	}
	
	@Override
	public List<ListTutorOfStaffDTO> listTutorOfStaff(int staffid, int page) {
		List<ListTutorOfStaffDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListTutorOfStaffDTO.class, tutor.tutorId,
						user.fullname,tutor.city, tutor.wards, user.phone, user.createdate, user.status, tutor.price))
				.distinct()
				.from(tutor)
				.innerJoin(staff).on(staff.staffid.eq(tutor.staffid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(staff.staffid.eq(staffid).and(user.status.ne(3)))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	@Override
	public StaffViewProfileTutorDTO staffViewProfileTutor(int tutorid) {
		StaffViewProfileTutorDTO result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileTutorDTO.class, tutor.tutorId,
						user.fullname,tutor.img,tutor.birthdate,tutor.city, tutor.wards, 
						user.phone, user.email, tutor.price))
				.distinct()
				.from(tutor)
				.innerJoin(staff).on(staff.staffid.eq(tutor.staffid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(tutor.tutorId.eq(tutorid))
				.fetchFirst();
		return result;
	}
	@Override
	public List<StaffViewProfileClasscourseTutorDTO> staffViewProfileClasscourseTutor(int tutorid) {
		List<StaffViewProfileClasscourseTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileClasscourseTutorDTO.class,
						classCourse.classCourseId, course.courseName, classEntity.className))
				.distinct()
				.from(tutor)
				.innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid)) 
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(tutor.tutorId.eq(tutorid))
				.fetch();
		return result;	}
	
	@Override
	public List<StaffViewProfileTimelineTutorDTO> staffViewProfileTimelineTutor(int tutorid) {
		List<StaffViewProfileTimelineTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileTimelineTutorDTO.class, teachTime.teachtimeid,
						timeline.timeline, lesson.lessonline))
				.distinct()
				.from(teachTime)
				.innerJoin(timeline).on(timeline.timeId.eq(teachTime.timeId))
				.innerJoin(lesson).on(lesson.lessonId.eq(teachTime.lessonid))
				.innerJoin(tutor).on(tutor.tutorId.eq(teachTime.tutorId))
				.where(tutor.tutorId.eq(tutorid))
				.orderBy(lesson.lessonId.asc())
				.fetch();
		return result;
	}

	@Override
	public List<ListStudentOfStaffDTO> listStudentOfStaff(int page) {
		List<ListStudentOfStaffDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListStudentOfStaffDTO.class, student.studentid,
						user.fullname,student.city, student.wards, user.phone, user.createdate, user.status))
				.distinct()
				.from(student)
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.leftJoin(bookingEntity).on(bookingEntity.studentid.eq(student.studentid))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	@Override
	public StaffViewProfileStudentDTO staffViewProfileStudent(int studentid) {
		StaffViewProfileStudentDTO result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileStudentDTO.class, student.studentid,
						user.fullname, student.img, student.birthdate, student.city, student.wards, user.email,
						user.phone, classEntity.className))
				.distinct()
				.from(student)
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.innerJoin(classEntity).on(classEntity.classId.eq(student.classId))
				.where(student.studentid.eq(studentid))
				.fetchFirst();
		return result;
	}

	@Override
	public List<StaffViewProfileTimelineStudentDTO> staffViewProfileTimelineStudent(int studentid) {
		List<StaffViewProfileTimelineStudentDTO> result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileTimelineStudentDTO.class, 
						timeBook.timebookid,timeline.timeline, classCourse.classCourseId, course.courseName, lesson.lessonline))
				.distinct()
				.from(bookingEntity)
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid ))
				.innerJoin(timeBook).on(timeBook.bookid.eq(bookingEntity.bookId))
				.innerJoin(lesson).on(lesson.lessonId.eq(timeBook.lessonid))
				.innerJoin(timeline).on(timeline.timeId.eq(timeBook.timeid))
				.innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.where(student.studentid.eq(studentid))
				.fetch();
		return result;
	}
	
	@Transactional
	@Override
	public void blockTutor(int tutorid) {
		jpaQueryFactory.update(user)
		.set(user.status, 2)
		.where(user.userid.eq(tutorid))
		.execute();
	}
	
	@Transactional
	@Override
	public void blockStudent(int studentid) {
		jpaQueryFactory.update(user)
		.set(user.status, 2)
		.where(user.userid.eq(studentid))
		.execute();
	}

	@Transactional
	@Override
	public void updateSalary(USalaryTutorDTO uSalaryTutorDTO) {
		jpaQueryFactory.update(tutor).where(tutor.tutorId.eq(uSalaryTutorDTO.getTutorid()))
			.set(tutor.price, uSalaryTutorDTO.getPrice())
			.execute();	
	}

	@Override
	public User detaiUser(String email) {
		User result = jpaQueryFactory.select(user)
				.distinct()
				.from(user)
				.where(user.email.eq(email))
				.fetchFirst();
		return result;
	}

	@Override
	public List<ListStaffByAdminDTO> listStaff(int page) {
		List<ListStaffByAdminDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListStaffByAdminDTO.class, staff.staffid,
						user.fullname,staff.city, staff.wards, user.phone, user.createdate, user.status, staff.salary))
				.distinct()
				.from(staff)
				.innerJoin(user).on(user.userid.eq(staff.staffid))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	@Transactional
	@Override
	public void updateSalaryStaff(USalaryStaffDTO uSalaryStaffDTO) {
		jpaQueryFactory.update(staff).where(staff.staffid.eq(uSalaryStaffDTO.getStaffid()))
		.set(staff.salary, uSalaryStaffDTO.getSalary())
		.execute();	
		
	}
	
	@Transactional
	@Override
	public void deleteStaff(int staffid) {
		 jpaQueryFactory.delete(staff)
         .where(staff.staffid.eq(staffid))
         .execute();
		 jpaQueryFactory.delete(user)
         .where(user.userid.eq(staffid))
         .execute();
	}

	@Override
	public long countWaitForConfirmTutor() {
		long result = jpaQueryFactory.select(user.userid.count())
				.distinct()
				.from(user)
				.where(user.status.in(2,3).and(user.role.eq(2)))
				.fetchFirst();
		return result;
	}

	@Override
	public List<ListWaitForConfirmTutorDTO> listWaitForConfirmTutor(int page) {
		List<ListWaitForConfirmTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListWaitForConfirmTutorDTO.class, 
						user.fullname, user.email,user.phone, user.gender, user.status,
						tutor.tutorId, tutor.cv, tutor.experience, tutor.salary, tutor.price))
				.distinct()
				.from(tutor)
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(user.status.in(2,3).and(user.role.eq(2)))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	
	@Override
	@Transactional
	public void confirmTutor(int tutorid, int staffid, float price) {
	    jpaQueryFactory
	        .update(tutor)
	        .set(tutor.staffid, staffid)
	        .set(tutor.price, price)
	        .where(tutor.tutorId.eq(tutorid))
	        .execute();
	}
	@Override
	public long totalpage() {
		long result = jpaQueryFactory
				.select(Projections.constructor(ListStaffByAdminDTO.class, staff.staffid,
						user.fullname,staff.city, staff.wards, user.phone, user.createdate, user.status, staff.salary))
				.distinct()
				.from(staff)
				.innerJoin(user).on(user.userid.eq(staff.staffid))
				.fetchCount();
		long endpage = 0;
		endpage = result / 10;
		if (result % 10 != 0) {
			endpage++;
		}
		return endpage;
	}

  @Override
  public long countTutorregistersforlessons() {
    // TODO Auto-generated method stub
    return 0;
  }
	@Override
	public List<PayForTuttorDTO> payfortutor(int staffid, int page) {
		List<PayForTuttorDTO> result = jpaQueryFactory.select(Projections.constructor(PayForTuttorDTO.class, tutor.tutorId,
				user.fullname, user.email, user.phone, payment.money, payment.banknumber, payment.bank, payment.datepay))
				.from(tutor)
				.innerJoin(payment).on(payment.tutorid.eq(tutor.tutorId))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(tutor.staffid.eq(staffid).and(payment.datepay.isNull()))
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	@Override
	public long pagepay(int staffid) {
		long result = jpaQueryFactory.select(Projections.constructor(PayForTuttorDTO.class, tutor.tutorId,
				user.fullname, user.email, user.phone, payment.money, payment.banknumber, payment.bank, payment.datepay))
				.from(tutor)
				.innerJoin(payment).on(payment.tutorid.eq(tutor.tutorId))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(tutor.staffid.eq(staffid).and(payment.datepay.isNull()))
				.fetchCount();
		return result;
	}

	@Transactional
	@Override
	public void accept(int tutorid) {
		jpaQueryFactory.update(payment).where(payment.tutorid.eq(tutorid))
		.set(payment.datepay, dateString)
		.execute();
	}

	@Override
	public long totalpagetutor(int staffid) {
		long result = jpaQueryFactory
				.select(Projections.constructor(ListTutorOfStaffDTO.class, tutor.tutorId,
						user.fullname,tutor.city, tutor.wards, user.phone, user.createdate, user.status, tutor.price))
				.distinct()
				.from(tutor)
				.innerJoin(staff).on(staff.staffid.eq(tutor.staffid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(staff.staffid.eq(staffid).and(user.status.eq(1)))
				.fetchCount();
		return result;
	}

	@Override
	public long pagestudent() {
		long result = jpaQueryFactory
				.select(Projections.constructor(ListStudentOfStaffDTO.class, student.studentid,
						user.fullname,student.city, student.wards, user.phone, user.createdate, user.status))
				.distinct()
				.from(student)
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.leftJoin(bookingEntity).on(bookingEntity.studentid.eq(student.studentid))
				.fetchCount();
		return result;
	}

	@Override
	public int checkflage(int tutorid) {
		int check = 0;
	    long result = jpaQueryFactory
	            .select(tutor.tutorId)
	            .from(tutor)
	            .innerJoin(user).on(user.userid.eq(tutor.tutorId))
	            .where(tutor.tutorId.eq(tutorid).and(user.status.eq(1)))
	            .fetchCount();
	    if (result > 0) {
	        check = 1;
	    }
	    
	    return check;
	}

	@Override
	public long checkaddcourse(int tutorid, int classcourseid) {
		int check = 0;
		long result = jpaQueryFactory.select(teach.teachid)
				.from(teach)
				.where(teach.tutorid.eq(tutorid).and(teach.classcourseid.eq(classcourseid)))
				.fetchCount();
		if(result > 0) {
			return check = 1;
		}
		return check;
	}

	@Transactional
	@Override
	public void accpettrylean(int studentid, int status, String datelearn) {
		jpaQueryFactory.update(trylearning)
		.where(trylearning.studentid.eq(studentid))
		.set(trylearning.status, status)
		.set(trylearning.datelearn, datelearn)
		.execute();
	}

	@Override
	public long counttrylearn() {
		long result = jpaQueryFactory
				.select(Projections.constructor(ViewStudentTrylearnDTO.class, tutor.tutorId,
						student.studentid, studentUser.fullname, tutorUser.fullname, student.img,  
						classCourse.classCourseId,course.courseName, classEntity.className,
						trylearning.trylearningid, trylearning.dateregister, trylearning.status, studentUser.phone, studentUser.email))
				.distinct()
				.from(trylearning)
				.innerJoin(tutor).on(tutor.tutorId.eq(trylearning.tutorid))
				.innerJoin(student).on(student.studentid.eq(trylearning.studentid))
				.innerJoin(studentUser).on(studentUser.userid.eq(student.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(trylearning.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(trylearning.status.eq(2))
				.fetchCount();
		return result;
	}

	@Override
	public long counttrylearnpage() {
		long result = jpaQueryFactory
				.select(Projections.constructor(ViewStudentTrylearnDTO.class, tutor.tutorId,
						student.studentid, studentUser.fullname, tutorUser.fullname, student.img,  
						classCourse.classCourseId,course.courseName, classEntity.className,
						trylearning.trylearningid, trylearning.dateregister, trylearning.status, studentUser.phone, studentUser.email))
				.distinct()
				.from(trylearning)
				.innerJoin(tutor).on(tutor.tutorId.eq(trylearning.tutorid))
				.innerJoin(student).on(student.studentid.eq(trylearning.studentid))
				.innerJoin(studentUser).on(studentUser.userid.eq(student.studentid))
				.innerJoin(tutorUser).on(tutorUser.userid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(trylearning.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.fetchCount();
		return result;
	}

	@Transactional
	@Override
	public void acceptfile(AcceptFileDTO acceptFileDTO) {
		jpaQueryFactory.update(files)
		.where(files.fileId.eq(acceptFileDTO.getFileid()))
		.set(files.status, acceptFileDTO.getStatus())
		.execute();
	}
	
	@Transactional
	@Override
	public void accepttutor(int tutorid, int status, int staffid, float price, int experience) {
		jpaQueryFactory.update(user)
		.where(user.userid.eq(tutorid))
		.set(user.status, status)
		.execute();
		jpaQueryFactory.update(tutor)
		.where(tutor.tutorId.eq(tutorid))
		.set(tutor.staffid, staffid)
		.set(tutor.price, price)
		.set(tutor.experience, experience)
		.execute();
	}

	@Transactional
	@Override
	public void deletetutorregiter(int tutorid) {
		jpaQueryFactory.delete(tutor)
		.where(tutor.tutorId.eq(tutorid))
		.execute();
		jpaQueryFactory.delete(user)
		.where(user.userid.eq(tutorid))
		.execute();
	}

	@Override
	public long pageWaitForConfirmTutor() {
		long result = jpaQueryFactory
				.select(Projections.constructor(ListWaitForConfirmTutorDTO.class, 
						user.fullname, user.email,user.phone, user.gender, user.status,
						tutor.tutorId, tutor.cv, tutor.experience, tutor.salary, tutor.price))
				.distinct()
				.from(tutor)
				.innerJoin(user).on(user.userid. eq(tutor.tutorId))
				.where(user.status.in(2,3).and(user.role.eq(2)))
				.fetchCount();
		return result;
	}

	@Override
	public List<StaffViewProfileTimelineTutorDTO> learntimestudentandtutor(int bookid) {
		List<StaffViewProfileTimelineTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(StaffViewProfileTimelineTutorDTO.class, timeline.timeId,
						timeline.timeline, lesson.lessonline))
				.distinct()
				.from(bookingEntity)
				.innerJoin(timeBook).on(timeBook.bookid.eq(bookingEntity.bookId))
				.innerJoin(timeline).on(timeline.timeId.eq(timeBook.timeid))
				.innerJoin(lesson).on(lesson.lessonId.eq(timeBook.lessonid))
				.where(bookingEntity.bookId.eq(bookid))
				.orderBy(lesson.lessonId.asc())
				.fetch();
		return result;
	}

	@Override
	public long checkblockstudent(int studentid) {
		long result = jpaQueryFactory
	            .select(student.studentid)
	            .from(student)
	            .innerJoin(user).on(user.userid.eq(student.studentid))
	            .where(student.studentid.eq(studentid).and(user.status.eq(1)))
	            .fetchCount();
		return result;
	}

	@Override
	public List<CountTutorAndStudentByClassDTO> countstudentandtutor() {
		List<CountTutorAndStudentByClassDTO> result = jpaQueryFactory.select(Projections.constructor(CountTutorAndStudentByClassDTO.class, 
				classEntity.className, student.studentid.countDistinct(), teach.tutorid.countDistinct()))
				.from(classEntity)
				.leftJoin(student).on(student.classId.eq(classEntity.classId))
				.innerJoin(classCourse).on(classCourse.classid.eq(classEntity.classId))
				.leftJoin(teach).on(teach.classcourseid.eq(classCourse.classCourseId))
				.groupBy(classEntity.className)
				.orderBy(classEntity.classId.asc())
				.fetch();
		return result;
	}

	@Override
	public long counttutor() {
		long result = jpaQueryFactory.select(tutor.tutorId.countDistinct())
				.from(tutor)
				.fetchFirst();
		return result;
	}

	@Override
	public long countstudent() {
		long result = jpaQueryFactory.select(student.studentid.countDistinct())
				.from(student)
				.fetchFirst();
		return result;
	}

	@Override
	public List<RadarChartDTO> radarChart() {
		List<RadarChartDTO> result = jpaQueryFactory.select(Projections.constructor(RadarChartDTO.class, course.courseName, bookingEntity.studentid.countDistinct()))
				.from(course)
				.innerJoin(classCourse).on(classCourse.courseid.eq(course.courseId))
				.leftJoin(bookingEntity).on(bookingEntity.classcourseid.eq(classCourse.classCourseId))
				.groupBy(course.courseName)
				.orderBy(bookingEntity.studentid.countDistinct().desc())
				.limit(6)
				.fetch();
		return result;
	}

	@Override
	public long countstaff() {
		long result = jpaQueryFactory.select(staff.staffid.countDistinct())
				.from(staff)
				.fetchFirst();
		return result;
	}

	@Override
	public List<RadarChartDTO> radarChartAdmin() {
		List<RadarChartDTO> result = jpaQueryFactory.select(Projections.constructor(RadarChartDTO.class, user.fullname, bookingEntity.tutorid.countDistinct()))
				.from(tutor)
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.leftJoin(bookingEntity).on(bookingEntity.tutorid.eq(tutor.tutorId))
				.groupBy(user.fullname)
				.orderBy(bookingEntity.tutorid.countDistinct().desc())
				.limit(6)
				.fetch();
		return result;
	}
}

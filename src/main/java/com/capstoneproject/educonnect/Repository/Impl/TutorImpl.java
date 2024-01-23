package com.capstoneproject.educonnect.Repository.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.BookTutorDTO;
import com.capstoneproject.educonnect.DTO.CourseAllByTutor;
import com.capstoneproject.educonnect.DTO.CourseOfTutorDTO;
import com.capstoneproject.educonnect.DTO.DemoDetailOfClasscourseDTO;
import com.capstoneproject.educonnect.DTO.DemoFollowClasscourseDTO;
import com.capstoneproject.educonnect.DTO.DescListAllTutorDTO;
import com.capstoneproject.educonnect.DTO.DetailSubmitOfHomeworkDTO;
import com.capstoneproject.educonnect.DTO.ListClassroomByTutorDTO;
import com.capstoneproject.educonnect.DTO.ListExerciseByTutorDTO;
import com.capstoneproject.educonnect.DTO.ListFeedbackBookingOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListStudentFinishedOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ListStudentOfTutorDTO;
import com.capstoneproject.educonnect.DTO.ShowDetailHomeworkDTO;
import com.capstoneproject.educonnect.DTO.ShowDetailVideoDTO;
import com.capstoneproject.educonnect.DTO.StudentViewDetailTutorDTO;
import com.capstoneproject.educonnect.DTO.Top3TutorDTO;
import com.capstoneproject.educonnect.DTO.TutorForStudentDTO;
import com.capstoneproject.educonnect.DTO.TutorScorePointsForSubmitDTO;
import com.capstoneproject.educonnect.DTO.TutorViewProfileStudentDTO;
import com.capstoneproject.educonnect.DTO.TutorViewProfileStudentFinishedDTO;
import com.capstoneproject.educonnect.DTO.TutorViewProfileTimelineStudentDTO;
import com.capstoneproject.educonnect.DTO.VTutorExerciseDTO;
import com.capstoneproject.educonnect.DTO.ViewScreenTutorDTO;
import com.capstoneproject.educonnect.DTO.ViewTutorDTO;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QClassroom;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QDemo;
import com.capstoneproject.educonnect.Entity.QDiscount;
import com.capstoneproject.educonnect.Entity.QDiscountCourse;
import com.capstoneproject.educonnect.Entity.QExercise;
import com.capstoneproject.educonnect.Entity.QFeedback;
import com.capstoneproject.educonnect.Entity.QHomework;
import com.capstoneproject.educonnect.Entity.QLevel;
import com.capstoneproject.educonnect.Entity.QScoreClassroom;
import com.capstoneproject.educonnect.Entity.QStudent;
import com.capstoneproject.educonnect.Entity.QSubmit;
import com.capstoneproject.educonnect.Entity.QTeach;
import com.capstoneproject.educonnect.Entity.QTeachTime;
import com.capstoneproject.educonnect.Entity.QTimeBook;
import com.capstoneproject.educonnect.Entity.QTimeline;
import com.capstoneproject.educonnect.Entity.QTrylearning;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Entity.QVideo;
import com.capstoneproject.educonnect.Entity.Qlesson;
import com.capstoneproject.educonnect.Repository.TutorRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TutorImpl implements TutorRepository {

	private JPAQueryFactory jpaQueryFactory;

	private final QTutor tutor = QTutor.tutor;
	private final QBookingEntity booking = QBookingEntity.bookingEntity;
	private final QUser user = QUser.user;
	private final QFeedback feedback = QFeedback.feedback;
	private final QClassCourse classCourse = QClassCourse.classCourse;
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QStudent student = QStudent.student;
	private final QLevel level = QLevel.level1;
	private final QCourse course = QCourse.course;
	private final QTeach teach = QTeach.teach;
	private final QDemo demo = QDemo.demo1;
	private final QHomework homework = QHomework.homework;
	private final QExercise exercise = QExercise.exercise;
	private final QVideo video = QVideo.video1;
	private final QTimeBook timeBook = QTimeBook.timeBook;
	private final QTimeline timeline = QTimeline.timeline1;
	private final QSubmit submit = QSubmit.submit;
	private final QClassroom classroom = QClassroom.classroom;
	private final QScoreClassroom scoreClassroom = QScoreClassroom.scoreClassroom;
	private final QTeachTime Teachtime = QTeachTime.teachTime;
	private final Qlesson lesson = Qlesson.lesson;
	private final QTrylearning trylearning = QTrylearning.trylearning;
	private final QDiscount discount = QDiscount.discount1;
	private final QDiscountCourse discountCourse = QDiscountCourse.discountCourse;

	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String dateString = today.format(formatter);

	@Autowired
	public TutorImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public List<Top3TutorDTO> findTop3Tutors() {
		List<Top3TutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(Top3TutorDTO.class, tutor.tutorId, user.fullname, feedback.ranks.avg(),
						tutor.img))
				.distinct().from(tutor).innerJoin(user).on(user.userid.eq(tutor.tutorId)).innerJoin(booking)
				.on(booking.tutorid.eq(tutor.tutorId)).innerJoin(feedback).on(feedback.feedbackid.eq(booking.bookId))
				.where(user.status.eq(1)).groupBy(tutor.tutorId, user.fullname, tutor.img)
				.orderBy(feedback.ranks.avg().desc()).limit(3).fetch();
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public ViewTutorDTO findViewTutor(int tutorId) {
		ViewTutorDTO result = jpaQueryFactory
				.select(Projections.constructor(ViewTutorDTO.class, tutor.tutorId, user.fullname, user.phone, tutor.img,
						user.gender, tutor.birthdate, tutor.city, tutor.wards, tutor.experience, tutor.salary))
				.distinct()
				.from(tutor)
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(tutor.tutorId.eq(tutorId)).fetchOne();
		return result;
	}

	@Override
	public List<CourseAllByTutor> AllCourseByTutor(int tutorid) {
		List<CourseAllByTutor> result = jpaQueryFactory
				.select(Projections.constructor(CourseAllByTutor.class, classCourse.classCourseId, course.courseName,
						classCourse.img, classEntity.className, level.level, student.studentid.count()))
				.distinct().from(teach).innerJoin(tutor).on(tutor.tutorId.eq(teach.tutorid)).innerJoin(classCourse)
				.on(classCourse.classCourseId.eq(teach.classcourseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).innerJoin(level).on(level.levelId.eq(classEntity.levelid))
				.leftJoin(booking).on(booking.classcourseid.eq(classCourse.classCourseId)).leftJoin(student)
				.on(student.studentid.eq(booking.studentid)).where(tutor.tutorId.eq(tutorid))
				.groupBy(classCourse.classCourseId, course.courseName, classCourse.img, classEntity.className,
						level.level)
				.fetch();
		return result;
	}

	@Override
	public ShowDetailHomeworkDTO showDetailHomework(int homeworkid) {
		ShowDetailHomeworkDTO result = jpaQueryFactory
				.select(Projections.constructor(ShowDetailHomeworkDTO.class, exercise.exerciseId, exercise.title,
						classCourse.classCourseId, course.courseName, tutor.tutorId, user.fullname, homework.homeworkId,
						demo.demoId, homework.files, homework.title, homework.startDate, homework.endDate, demo.demo,
						demo.files))
				.distinct().from(homework).innerJoin(exercise).on(exercise.exerciseId.eq(homework.exerciseid))
				.innerJoin(booking).on(booking.bookId.eq(exercise.bookid)).innerJoin(classCourse)
				.on(classCourse.classCourseId.eq(booking.classcourseid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId)).innerJoin(demo).on(demo.demoId.eq(homework.demoid))
				.where(homework.homeworkId.eq(homeworkid)).fetchFirst();
		return result;
	}

	// show detail submit c� id submit t�n c?a student �?, m�n h?c, score
	@Override
	public DetailSubmitOfHomeworkDTO detailSubmitOfHomeworkDTO(int homeworkid) {
		DetailSubmitOfHomeworkDTO result = jpaQueryFactory
				.select(Projections.constructor(DetailSubmitOfHomeworkDTO.class, user.fullname, tutor.tutorId,
						submit.submitid, submit.submitdate, submit.files, submit.score))
				.distinct().from(submit).innerJoin(homework).on(homework.homeworkId.eq(submit.submitid))
				.innerJoin(exercise).on(exercise.exerciseId.eq(homework.exerciseid)).innerJoin(booking)
				.on(booking.bookId.eq(exercise.bookid)).innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(student).on(student.studentid.eq(booking.studentid)).innerJoin(user)
				.on(user.userid.eq(student.studentid)).where(homework.homeworkId.eq(homeworkid)).fetchFirst();
		return result;
	}

	// ghi �i?m v� s?a �i?m c?a submit
	@Transactional
	@Override
	public void TutorScorePointsForSubmit(TutorScorePointsForSubmitDTO tutorScorePointsForSubmitDTO) {
		jpaQueryFactory.update(submit).where(submit.submitid.eq(tutorScorePointsForSubmitDTO.getSubmitid()))
				.set(submit.score, tutorScorePointsForSubmitDTO.getScore()).execute();
	}

	@Override
	public ShowDetailVideoDTO showDetailVideo(int videoid) {
		ShowDetailVideoDTO result = jpaQueryFactory
				.select(Projections.constructor(ShowDetailVideoDTO.class, exercise.exerciseId, exercise.title,
						classCourse.classCourseId, course.courseName, tutor.tutorId, user.fullname, video.videoId,
						video.nameVideo, video.video))
				.distinct().from(video).innerJoin(exercise).on(exercise.exerciseId.eq(video.exerciseid))
				.innerJoin(booking).on(booking.bookId.eq(exercise.exerciseId)).innerJoin(classCourse)
				.on(classCourse.classCourseId.eq(booking.classcourseid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId)).where(video.videoId.eq(videoid)).fetchFirst();
		return result;
	}

	@Override
	public List<DemoFollowClasscourseDTO> demoFollowClasscourse(int classcourseid) {
		List<DemoFollowClasscourseDTO> result = jpaQueryFactory
				.select(Projections.constructor(DemoFollowClasscourseDTO.class, demo.demoId, demo.demo, demo.files,
						tutor.tutorId, classCourse.classCourseId, course.courseName, classEntity.className))
				.distinct().from(demo).innerJoin(classCourse).on(classCourse.classCourseId.eq(demo.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid)).innerJoin(teach)
				.on(teach.classcourseid.eq(classCourse.classCourseId)).innerJoin(tutor)
				.on(tutor.tutorId.eq(teach.tutorid)).where(classCourse.classCourseId.eq(classcourseid)).fetch();
		return result;
	}

	@Override
	public List<ListStudentFinishedOfTutorDTO> listStudentFinishedOfTutorDTOs(int tutorid, int status, int page, int courseid) {
		List<Integer> check = new ArrayList<>();
		if(status == 0) {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid))
					.fetch();
			check = id;
		}else if(status == 1) {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid).and(booking.startDate.gt(dateString)))
					.fetch();
			check = id;
		}else if(status == 2) {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid).and(booking.startDate.loe(dateString)).and(booking.endDate.goe(dateString)))
					.fetch();
			check = id;
		}else {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid).and(booking.endDate.loe(dateString)))
					.fetch();
			check = id;
		}
		
		List<ListStudentFinishedOfTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListStudentFinishedOfTutorDTO.class, tutor.tutorId, booking.bookId, student.studentid,
						user.fullname, student.img, classCourse.classCourseId, course.courseName, classEntity.className,
						booking.startDate, booking.endDate))
				.distinct()
				.from(booking)
				.innerJoin(student).on(student.studentid.eq(booking.studentid))
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(booking.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.where(tutor.tutorId.eq(tutorid).and(booking.bookId.in(check)).and(classCourse.classCourseId.eq(courseid)))
				.orderBy(booking.bookId.desc())
				.limit(10).offset(page)
				.fetch();
		return result;
	}

	@Override
	public TutorViewProfileStudentFinishedDTO tutorViewProfileStudentFinishedDTO(int studentid) {
		TutorViewProfileStudentFinishedDTO result = jpaQueryFactory
				.select(Projections.constructor(TutorViewProfileStudentFinishedDTO.class, student.studentid,
						user.fullname, student.img, student.birthdate, student.city, student.wards, user.email,
						user.phone, classEntity.className))
				.distinct().from(student).innerJoin(user).on(user.userid.eq(student.studentid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(student.classId)).where(student.studentid.eq(studentid)).fetchFirst();
		return result;
	}

	@Override
	public List<ListStudentOfTutorDTO> listStudentOfTutor(int tutorid) {
		List<ListStudentOfTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListStudentOfTutorDTO.class, tutor.tutorId, student.studentid,
						user.fullname, student.img, classCourse.classCourseId, course.courseName, classEntity.className,
						booking.startDate, booking.endDate))
				.distinct().from(booking).innerJoin(student).on(student.studentid.eq(booking.studentid)).innerJoin(user)
				.on(user.userid.eq(student.studentid)).innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(booking.classcourseid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid))
				.where(tutor.tutorId.eq(tutorid).and(booking.endDate.goe(dateString))).fetch();
		return result;
	}

	@Override
	public TutorViewProfileStudentDTO tutorViewProfileStudent(int studentid) {
		TutorViewProfileStudentDTO result = jpaQueryFactory
				.select(Projections.constructor(TutorViewProfileStudentDTO.class, student.studentid, user.fullname,
						student.img, student.birthdate, student.city, student.wards, user.email, user.phone,
						classEntity.className))
				.distinct().from(student).innerJoin(user).on(user.userid.eq(student.studentid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(student.classId)).where(student.studentid.eq(studentid)).fetchFirst();
		return result;
	}

	@Override
	public List<TutorViewProfileTimelineStudentDTO> tutorViewProfileTimelineStudent(int bookid) {
		List<TutorViewProfileTimelineStudentDTO> result = jpaQueryFactory
				.select(Projections.constructor(TutorViewProfileTimelineStudentDTO.class, student.studentid,
						booking.bookId, timeBook.timebookid, timeBook.timeid, lesson.lessonline, timeline.timeline,student.img,user.fullname, 
						student.birthdate,user.email,user.phone))
				.distinct().from(booking)
				.innerJoin(student).on(student.studentid.eq(booking.studentid))
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.innerJoin(timeBook).on(timeBook.bookid.eq(booking.bookId))
				.innerJoin(lesson).on(lesson.lessonId.eq(timeBook.lessonid))
				.innerJoin(timeline).on(timeline.timeId.eq(timeBook.timeid))
				.innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(booking.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.where(booking.bookId.eq(bookid)).fetch();
		return result;
	}

	@Override
	public DemoDetailOfClasscourseDTO demoDetailOfClasscourse(int demoid) {
		DemoDetailOfClasscourseDTO result = jpaQueryFactory.select(Projections
				.constructor(DemoDetailOfClasscourseDTO.class, demo.demoId, demo.demo, demo.files, demo.classcourseid))
				.distinct().from(demo).where(demo.demoId.eq(demoid)).fetchFirst();
		return result;
	}

	@Override
	public BookTutorDTO booktutor(int tutorid, int classcourseid) {
		Float discounts = 
				jpaQueryFactory.select(discount.discount)
				.distinct()
				.from(classCourse)
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.leftJoin(discountCourse).on(discountCourse.courseid.eq(course.courseId))
				.leftJoin(discount).on(discount.discountid.eq(discountCourse.discountid))
				.where(discount.startDate.loe(dateString)
						.and(discount.endDate.goe(dateString))
						.and(classCourse.classCourseId.eq(classcourseid)))
				.fetchFirst();
		Float dis = discounts == null ? 0 : discounts;
		Float total = ((100 - dis)/100);
		BookTutorDTO result = jpaQueryFactory
				.select(Projections.constructor(BookTutorDTO.class, tutor.tutorId, user.fullname, tutor.img,
						course.courseName, classEntity.className, tutor.price.multiply(total), classCourse.classCourseId,
						feedback.ranks.avg(), booking.bookId.count(), trylearning.status))
				.distinct()
				.from(tutor)
				.innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
				.leftJoin(booking).on(booking.tutorid.eq(tutor.tutorId))
				.leftJoin(feedback).on(feedback.feedbackid.eq(booking.bookId))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.leftJoin(trylearning).on(trylearning.tutorid.eq(tutor.tutorId).and(trylearning.classcourseid.eq(classCourse.classCourseId)))
				.where(tutor.tutorId.eq(tutorid)
						.and(classCourse.classCourseId.eq(classcourseid)))
				.groupBy(tutor.tutorId, user.fullname, tutor.img,
						course.courseName, classEntity.className, tutor.price.multiply(total), classCourse.classCourseId, trylearning.status)
				.fetchOne();
		return result;
	}

	@Transactional
	@Override
	public void UpdateTutor(int tutorid, String fullname, String phone, String file, int gender, String birthdate,
			String city, String wards) {
		jpaQueryFactory.update(tutor).where(tutor.tutorId.eq(tutorid)).set(tutor.img, file)
				.set(tutor.birthdate, birthdate).set(tutor.city, city).set(tutor.wards, wards).execute();
		jpaQueryFactory.update(user).where(user.userid.eq(tutorid)).set(user.gender, gender)
				.set(user.fullname, fullname).set(user.phone, phone).execute();
	}

	@Override
	public List<ListClassroomByTutorDTO> listClassroomByTutor(int exerciseid) {
		List<ListClassroomByTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListClassroomByTutorDTO.class, tutor.tutorId, user.fullname,
						course.courseName, classEntity.className, exercise.exerciseId, exercise.title,
						classroom.classroomId, classroom.nameClassroom, classroom.link, scoreClassroom.scoreid,
						scoreClassroom.score))
				.distinct().from(classroom).innerJoin(exercise).on(exercise.exerciseId.eq(classroom.exerciseid))
				.innerJoin(booking).on(booking.bookId.eq(exercise.bookid)).innerJoin(tutor)
				.on(tutor.tutorId.eq(booking.tutorid)).innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(student).on(student.studentid.eq(booking.studentid)).innerJoin(classCourse)
				.on(classCourse.classCourseId.eq(booking.classcourseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).leftJoin(scoreClassroom)
				.on(scoreClassroom.scoreid.eq(classroom.classroomId)).where(exercise.exerciseId.eq(exerciseid)).fetch();
		return result;
	}

	@Override
	public List<TutorForStudentDTO> TutorForStudent(int tutorid) {
		List<TutorForStudentDTO> result = jpaQueryFactory
				.select(Projections.constructor(TutorForStudentDTO.class, tutor.tutorId, tutor.img, user.fullname,
						tutor.experience, Teachtime.timeId, tutor.price, booking.bookId.count()))
				.distinct()
				.from(tutor)
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(Teachtime).on(Teachtime.tutorId.eq(tutor.tutorId))
				.innerJoin(booking).on(booking.tutorid.eq(tutor.tutorId))
				.where(tutor.tutorId.eq(tutorid))
				.groupBy(tutor.tutorId, tutor.img, user.fullname, tutor.experience, Teachtime.timeId, tutor.price)
				.fetch();
		return result;
	}

	@Override
	public List<ListExerciseByTutorDTO> listExerciseByTutorDTOs(int tutorid) {
		List<ListExerciseByTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListExerciseByTutorDTO.class, tutor.tutorId, user.fullname,
						classCourse.classCourseId, course.courseName, classEntity.className, exercise.exerciseId,
						exercise.title))
				.distinct().from(exercise).innerJoin(booking).on(booking.bookId.eq(exercise.bookid)).innerJoin(tutor)
				.on(tutor.tutorId.eq(booking.tutorid)).innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(booking.classcourseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).where(tutor.tutorId.eq(tutorid)).fetch();
		return result;
	}

	@Override
	public List<DescListAllTutorDTO> DescListAllTutor(int courseid, int page) {
		List<DescListAllTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(DescListAllTutorDTO.class, classCourse.classCourseId, course.courseName,
						classEntity.className, tutor.tutorId, tutor.img, user.fullname, feedback.ranks.avg(),
						booking.bookId.count()))
				.distinct()
				.from(tutor)
				.leftJoin(booking).on(booking.tutorid.eq(tutor.tutorId))
				.innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
				.leftJoin(feedback).on(feedback.feedbackid.eq(booking.bookId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(teach.classcourseid.eq(courseid).and(user.status.eq(1)))
				.groupBy(classCourse.classCourseId, course.courseName, classEntity.className, tutor.tutorId, tutor.img,user.fullname)
				.having(feedback.ranks.avg().goe(3))
				.orderBy(feedback.ranks.avg().desc())
				.limit(4).offset(page)
				.fetch();
		return result;
	}

	@Override
	public List<ViewScreenTutorDTO> ViewScreenTutor(int tutorid) {
		List<ViewScreenTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ViewScreenTutorDTO.class, course.courseId, course.courseName,
						tutor.tutorId, user.fullname, classEntity.className, video.nameVideo, video.video,
						feedback.notes, feedback.ranks, booking.bookId.count()))
				.distinct().from(tutor).innerJoin(user).on(user.userid.eq(tutor.tutorId)).innerJoin(booking)
				.on(booking.tutorid.eq(tutor.tutorId)).innerJoin(classCourse)
				.on(classCourse.classCourseId.eq(booking.classcourseid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid)).innerJoin(feedback)
				.on(feedback.feedbackid.eq(booking.bookId)).innerJoin(exercise).on(exercise.bookid.eq(booking.bookId))
				.innerJoin(video).on(video.exerciseid.eq(exercise.exerciseId)).where(tutor.tutorId.eq(tutorid))
				.groupBy(course.courseId, course.courseName, tutor.tutorId, user.fullname, classEntity.className,
						video.nameVideo, video.video, feedback.notes, feedback.ranks)
				.fetch();
		return result;
	}

	@Override
	public List<CourseOfTutorDTO> courOfTutor(int tutorid) {
		List<CourseOfTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(CourseOfTutorDTO.class, 
						classCourse.classCourseId, course.courseName,classEntity.className,
						tutor.tutorId, course.courseId))
				.distinct()
				.from(course)
				.innerJoin(classCourse).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(teach).on(classCourse.classCourseId.eq(teach.classcourseid))
				.innerJoin(tutor).on(tutor.tutorId.eq(teach.tutorid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.where(tutor.tutorId.eq(tutorid))
				.fetch();
		return result;
	}

	public long countpage(int courseid) {
		long result = jpaQueryFactory
				.select(Projections.constructor(DescListAllTutorDTO.class, classCourse.classCourseId, course.courseName,
						classEntity.className, tutor.tutorId, tutor.img, user.fullname, feedback.ranks.avg(),
						booking.bookId.count()))
				.distinct().from(tutor).leftJoin(booking).on(booking.tutorid.eq(tutor.tutorId)).innerJoin(teach)
				.on(teach.tutorid.eq(tutor.tutorId)).leftJoin(feedback).on(feedback.feedbackid.eq(booking.bookId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid)).innerJoin(course)
				.on(course.courseId.eq(classCourse.courseid)).innerJoin(classEntity)
				.on(classEntity.classId.eq(classCourse.classid)).innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.where(teach.classcourseid.eq(courseid).and(user.status.eq(1)))
				.groupBy(classCourse.classCourseId, course.courseName, classEntity.className, tutor.tutorId, tutor.img,
						user.fullname)
				.having(feedback.ranks.avg().goe(3)).orderBy(feedback.ranks.avg().desc()).fetchCount();
		long endpage = 0;
		endpage = result / 5;
		if (result % 5 != 0) {
			endpage++;
		}
		return endpage;
	}

	@Override
	@Transactional
	public void updatesalary(String email) {
		float result = jpaQueryFactory.select(tutor.price).distinct().from(tutor).innerJoin(user)
				.on(user.userid.eq(tutor.tutorId)).where(user.email.eq(email)).fetchFirst();
		float total = (result - (result * 0.3f));

		jpaQueryFactory.update(tutor).where(tutor.tutorId.eq(user.userid).and(user.email.eq(email)))
				.set(tutor.salary, tutor.salary.add(total)).execute();
	}

	@Override
	public StudentViewDetailTutorDTO studentViewDetailTutorDTO(int tutorid) {
		StudentViewDetailTutorDTO result = jpaQueryFactory
				.select(Projections.constructor(StudentViewDetailTutorDTO.class, tutor.tutorId, user.fullname,
						tutor.img, booking.studentid.count(), tutor.experience, teach.classcourseid.countDistinct(),
						feedback.ranks.avg()))
				.distinct().from(tutor).innerJoin(user).on(user.userid.eq(tutor.tutorId)).leftJoin(booking)
				.on(booking.tutorid.eq(tutor.tutorId)).leftJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
				.leftJoin(feedback).on(feedback.feedbackid.eq(booking.bookId)).where(tutor.tutorId.eq(tutorid))
				.groupBy(tutor.tutorId, user.fullname, tutor.img, tutor.experience).fetchOne();
		return result;
	}

	@Override
	public boolean checktutor(int tutorid) {
		String result = jpaQueryFactory.select(tutor.city)
				.from(tutor)
				.where(tutor.tutorId.eq(tutorid))
				.fetchFirst();
		if(result == null || result.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public long countstudent(int tutorid, int status, int courseid) {
		List<Integer> check = new ArrayList<>();
		if(status == 0) {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid))
					.fetch();
			check = id;
		}else if(status == 1) {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid).and(booking.startDate.gt(dateString)))
					.fetch();
			check = id;
		}else if(status == 2) {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid).and(booking.startDate.loe(dateString)).and(booking.endDate.goe(dateString)))
					.fetch();
			check = id;
		}else {
			List<Integer> id = jpaQueryFactory.select(booking.bookId)
					.from(booking)
					.where(booking.tutorid.eq(tutorid).and(booking.endDate.loe(dateString)))
					.fetch();
			check = id;
		}
		
		long result = jpaQueryFactory
				.select(booking.bookId)
				.distinct()
				.from(booking)
				.innerJoin(student).on(student.studentid.eq(booking.studentid))
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(booking.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.where(tutor.tutorId.eq(tutorid).and(booking.bookId.in(check)).and(classCourse.classCourseId.eq(courseid)))
				.fetchCount();
		return result;
	}

	@Override
	public List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(int tutorid, int classcourse) {
		List<ListFeedbackBookingOfTutorDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListFeedbackBookingOfTutorDTO.class,
						tutor.tutorId, booking.studentid, user.fullname, 
						booking.classcourseid,student.img, course.courseName, classEntity.className,booking.dateregister, 
						feedback.feedbackid, feedback.notes, feedback.ranks))
				.distinct()
				.from(feedback)
				.leftJoin(booking).on(booking.bookId.eq(feedback.feedbackid))
				.innerJoin(tutor).on(tutor.tutorId.eq(booking.tutorid))
				.innerJoin(student).on(student.studentid.eq(booking.studentid))
				.innerJoin(user).on(user.userid.eq(student.studentid))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(booking.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
	            .where(tutor.tutorId.eq(tutorid).and(classCourse.classCourseId.eq(classcourse)))
	            .fetch();
	return result;
	}

	@Override
	public VTutorExerciseDTO viewtutor(int classcourseid, int tutorid) {
		VTutorExerciseDTO result = jpaQueryFactory.select(Projections.constructor(VTutorExerciseDTO.class, course.courseName, classEntity.className, user.fullname))
				.from(tutor)
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(teach).on(teach.tutorid.eq(tutor.tutorId))
				.innerJoin(classCourse).on(classCourse.classCourseId.eq(teach.classcourseid))
				.innerJoin(course).on(course.courseId.eq(classCourse.courseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classCourse.classid))
				.where(classCourse.classCourseId.eq(classcourseid).and(tutor.tutorId.eq(tutorid)))
				.fetchFirst();
		return result;
	}
}

package com.capstoneproject.educonnect.Repository.Impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.ClassRoomEDTO;
import com.capstoneproject.educonnect.DTO.DetaiHomeworkOfStudentDTO;
import com.capstoneproject.educonnect.DTO.ExserciseDTO;
import com.capstoneproject.educonnect.DTO.FilesDTO;
import com.capstoneproject.educonnect.DTO.HomeWorkDTO;
import com.capstoneproject.educonnect.DTO.HomeworkEDTO;
import com.capstoneproject.educonnect.DTO.HomeworkResDTO;
import com.capstoneproject.educonnect.DTO.ListScoreClassroomFollowExerciseByStudentDTO;
import com.capstoneproject.educonnect.DTO.ScoreExerciseDTO;
import com.capstoneproject.educonnect.DTO.UClassRoomDTO;
import com.capstoneproject.educonnect.DTO.UExerciseDTO;
import com.capstoneproject.educonnect.DTO.UVideoDTO;
import com.capstoneproject.educonnect.DTO.VideoDTO;
import com.capstoneproject.educonnect.Entity.Classroom;
import com.capstoneproject.educonnect.Entity.Files;
import com.capstoneproject.educonnect.Entity.Homework;
import com.capstoneproject.educonnect.Entity.QBookingEntity;
import com.capstoneproject.educonnect.Entity.QClassCourse;
import com.capstoneproject.educonnect.Entity.QClassEntity;
import com.capstoneproject.educonnect.Entity.QClassroom;
import com.capstoneproject.educonnect.Entity.QCourse;
import com.capstoneproject.educonnect.Entity.QDemo;
import com.capstoneproject.educonnect.Entity.QExercise;
import com.capstoneproject.educonnect.Entity.QFiles;
import com.capstoneproject.educonnect.Entity.QHomework;
import com.capstoneproject.educonnect.Entity.QScoreClassroom;
import com.capstoneproject.educonnect.Entity.QStudent;
import com.capstoneproject.educonnect.Entity.QSubmit;
import com.capstoneproject.educonnect.Entity.QTutor;
import com.capstoneproject.educonnect.Entity.QUser;
import com.capstoneproject.educonnect.Entity.QVideo;
import com.capstoneproject.educonnect.Entity.ScoreClassroom;
import com.capstoneproject.educonnect.Entity.Submit;
import com.capstoneproject.educonnect.Entity.Video;
import com.capstoneproject.educonnect.Repository.ExersiceRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ExersiceRepositoryImpl implements ExersiceRepository {

	private JPAQueryFactory jpaQueryFactory;
	
	private final QExercise exercise = QExercise.exercise;
	private final QBookingEntity bookingEntity = QBookingEntity.bookingEntity;
	private final QStudent student = QStudent.student;
	private final QFiles file = QFiles.files1;
	private final QVideo video = QVideo.video1;
	private final QHomework homework = QHomework.homework;
	private final QSubmit submit = QSubmit.submit;
	private final QClassCourse classcourse = QClassCourse.classCourse;
	private final QCourse course = QCourse.course;
	private final QClassroom classroom = QClassroom.classroom;
	private final QScoreClassroom scoreClassroom = QScoreClassroom.scoreClassroom;
	private final QClassEntity classEntity = QClassEntity.classEntity;
	private final QTutor tutor = QTutor.tutor;
	private final QUser user = QUser.user;
	private final QDemo demo = QDemo.demo1;
    
	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String dateString = today.format(formatter);
	
	@Autowired
    public ExersiceRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
	
	@Override
	public List<ExserciseDTO> ListAllExserciseByStudent(int bookId) {
		List<ExserciseDTO> result = jpaQueryFactory
	            .select(Projections.constructor(ExserciseDTO.class, exercise.exerciseId, exercise.bookid, exercise.title))
	            .distinct()
	            .from(exercise)
	            .innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(bookingEntity.bookId.eq(bookId))
	            .fetch();
		if(result.isEmpty()) {
			return Collections.emptyList();
		}else {
			return result;
		}
	}

	@Override
	public List<FilesDTO> listFileFollowExercise(int bookId) {
		List<FilesDTO> result = jpaQueryFactory.select(Projections.constructor(FilesDTO.class, file.fileId, file.exerciseid, file.nameFile, file.files, file.status))
				.distinct()
				.from(file)
				.innerJoin(exercise).on(exercise.exerciseId.eq(file.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(bookingEntity.bookId.eq(bookId).and(file.status.in(1,2)))
				.fetch();
		return result;
	}
	@Override
	public ExserciseDTO getExerciseById(int exId) {
		ExserciseDTO result = jpaQueryFactory
	            .select(Projections.constructor(ExserciseDTO.class, exercise.exerciseId, exercise.bookid, exercise.title))
	            .from(exercise)
	            .where(exercise.exerciseId.eq(exId))
	            .fetchOne();
		return result;
	}

	@Override
	public List<VideoDTO> listVideoFollowExercise(int bookId) {
		List<VideoDTO> result = jpaQueryFactory.select(Projections.constructor(VideoDTO.class, video.videoId, video.exerciseid, video.nameVideo, video.video, video.status))
				.distinct()
				.from(video)
				.innerJoin(exercise).on(exercise.exerciseId.eq(video.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(bookingEntity.bookId.eq(bookId))
				.fetch();
		return result;
	}

	@Override
	public List<ScoreExerciseDTO> listScoreFollowExercise(int bookid) {
		List<ScoreExerciseDTO> result = jpaQueryFactory
	            .select(Projections.constructor(ScoreExerciseDTO.class, exercise.exerciseId, student.studentid, 
	            		homework.title, course.courseName,homework.startDate, homework.endDate, submit.submitdate, 
	            		submit.score))
	            .distinct()
	            .from(submit)
	            .innerJoin(homework).on(homework.homeworkId.eq(submit.submitid))
	            .innerJoin(exercise).on(exercise.exerciseId.eq(homework.exerciseid))
	            .innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .innerJoin(classcourse).on(classcourse.classCourseId.eq(bookingEntity.classcourseid))
	            .innerJoin(course).on(course.courseId.eq(classcourse.courseid))
	            .where(bookingEntity.bookId.eq(bookid))
	            .fetch();
	    return result;
  }
	@Override
	public List<HomeWorkDTO> listHomeworkExercise(int bookId) {
		List<HomeWorkDTO> result = jpaQueryFactory.select(Projections.constructor(HomeWorkDTO.class, homework.homeworkId, homework.exerciseid, homework.title,homework.files))
				.distinct()
				.from(homework)
				.innerJoin(exercise).on(exercise.exerciseId.eq(homework.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(bookingEntity.bookId.eq(bookId))
				.fetch();
		return result;
	}
	
	@Override
	public List<HomeworkEDTO> homeworkViewByTutor(int bookid) {
		List<HomeworkEDTO> result = jpaQueryFactory.select(Projections.constructor(HomeworkEDTO.class, homework.homeworkId, homework.exerciseid, homework.title,submit.files,submit.submitdate,submit.score,student.studentid,submit.status,user.fullname))
				.distinct()
				.from(homework)
				.innerJoin(exercise).on(exercise.exerciseId.eq(homework.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .innerJoin(user).on(user.userid.eq(student.studentid))
	            .innerJoin(submit).on(submit.submitid.eq(homework.homeworkId))
	            .where(bookingEntity.bookId.eq(bookid))
				.fetch();
		return result;
	}
	
	
	@Override
	public List<HomeworkResDTO> listHomeworkByExercise(int exerciseid) {
		List<HomeworkResDTO> result = jpaQueryFactory.select(Projections.constructor(HomeworkResDTO.class, homework.homeworkId, homework.exerciseid, homework.title, homework.files, homework.startDate, homework.endDate, demo.demo, demo.files))
				.from(homework)
				.innerJoin(demo).on(demo.demoId.eq(homework.demoid))
	            .where(homework.exerciseid.eq(exerciseid))
				.fetch();
		return result;
	}

	@Override
	public List<ClassRoomEDTO> listClassRoomExercise(int bookId) {
		List<ClassRoomEDTO> result = jpaQueryFactory.select(Projections.constructor(ClassRoomEDTO.class, classroom.classroomId, classroom.exerciseid, 
				classroom.nameClassroom, classroom.link, classroom.submitdate))
				.distinct()
				.from(classroom)
				.innerJoin(exercise).on(exercise.exerciseId.eq(classroom.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(bookingEntity.bookId.eq(bookId))
				.fetch();
		return result;
	}

	@Transactional
	@Override
	public void UpdateHomeWord(int homeworkid,
			int demoid, String file, String title, String startDate,
			String endDate) {
		jpaQueryFactory.update(homework).where(homework.homeworkId.eq(homeworkid))
		.set(homework.demoid, demoid)
		.set(homework.files, file)
		.set(homework.title, title)
		.set(homework.startDate, startDate)
		.set(homework.endDate, endDate)
		.execute();
	}

	@Transactional
	@Override
	public void UpdateVideo(UVideoDTO uVideoDTO) {
		jpaQueryFactory.update(video).where(video.videoId.eq(uVideoDTO.getVideoId()))
		.set(video.nameVideo, uVideoDTO.getNameVideo())
		.set(video.video, uVideoDTO.getVideo())
		.set(video.status, 2)
		.execute();
	}

	@Transactional
	@Override
	public void UpdateFiles(int fileid, String nameFile, String files) {
		jpaQueryFactory.update(file).where(file.fileId.eq(fileid))
		.set(file.nameFile, nameFile)
		.set(file.files, files)
		.set(file.status, 3)
		.execute();
	}

	@Transactional
	@Override
	public void UpdateClassRoom(UClassRoomDTO uClassRoomDTO) {
		jpaQueryFactory.update(classroom).where(classroom.classroomId.eq(uClassRoomDTO.getClassroomId()))
		.set(classroom.nameClassroom, uClassRoomDTO.getNameClassroom())
		.set(classroom.link, uClassRoomDTO.getLink())
		.execute();
	}

	@Override
	public Homework detailhomework(int id) {
		Homework result = jpaQueryFactory.select(homework)
				.distinct()
				.from(homework)
				.where(homework.homeworkId.eq(id))
				.fetchFirst();
		return result;
	}
	
	@Override
	public Classroom detailClassroom(int classroomid) {
		Classroom result = jpaQueryFactory.select(classroom)
				.distinct()
				.from(classroom)
				.where(classroom.classroomId.eq(classroomid))
				.fetchFirst();
		return result;
	}
	
	@Transactional
	@Override
	public void watchedFiles(int fileid) {
		jpaQueryFactory.update(file)
		.set(file.status, 1)
		.where(file.fileId.eq(fileid))
		.execute();
	}
	
	@Transactional
	@Override
	public void watchedVideo(int videoid) {
		jpaQueryFactory.update(video)
		.set(video.status, 1)
		.where(video.videoId.eq(videoid))
		.execute();
	}
	
	@Transactional
	@Override
	public void UpdateExercise(UExerciseDTO uExerciseDTO) {
		jpaQueryFactory.update(exercise).where(exercise.exerciseId.eq(uExerciseDTO.getExerciseId()))
		.set(exercise.title, uExerciseDTO.getTitle())
		.execute();	
	}
	
	@Transactional
	@Override
	public void deleteExercise(int exerciseid) {
		Homework hw = jpaQueryFactory.select(homework)
				.from(homework)
				.where(homework.exerciseid.eq(exerciseid))
				.fetchOne();
		Files fl = jpaQueryFactory.select(file)
				.from(file)
				.where(file.exerciseid.eq(exerciseid))
				.fetchOne();
		Classroom cl = jpaQueryFactory.select(classroom)
				.from(classroom)
				.where(classroom.exerciseid.eq(exerciseid))
				.fetchOne();
		Video vd = jpaQueryFactory.select(video)
				.from(video)
				.where(video.exerciseid.eq(exerciseid))
				.fetchOne();
		if(hw != null) {
			Submit result = jpaQueryFactory.select(submit)
					.from(submit)
					.innerJoin(homework).on(homework.homeworkId.eq(submit.submitid))
					.where(homework.exerciseid.eq(exerciseid))
					.fetchOne();
			if(result != null) {
				jpaQueryFactory.delete(submit).where(submit.submitid.eq(homework.homeworkId).and(homework.exerciseid.eq(exerciseid))).execute();
				jpaQueryFactory.delete(homework).where(homework.exerciseid.eq(exerciseid)).execute();
			}else {
				jpaQueryFactory.delete(homework).where(homework.exerciseid.eq(exerciseid)).execute();
			}
		}else if(fl != null) {
			jpaQueryFactory.delete(file).where(file.exerciseid.eq(exerciseid)).execute();
		}else if(cl != null) {
			ScoreClassroom result = jpaQueryFactory.select(scoreClassroom)
					.from(scoreClassroom)
					.innerJoin(classroom).on(classroom.classroomId.eq(scoreClassroom.scoreid))
					.where(classroom.exerciseid.eq(exerciseid))
					.fetchFirst();
			if(result != null) {
				jpaQueryFactory.delete(scoreClassroom).where(scoreClassroom.scoreid.eq(classroom.classroomId).and(classroom.exerciseid.eq(exerciseid))).execute();
				jpaQueryFactory.delete(classroom).where(classroom.exerciseid.eq(exerciseid)).execute();
			}else {
				jpaQueryFactory.delete(classroom).where(classroom.exerciseid.eq(exerciseid)).execute();
			}
		}else if(vd != null) {
			jpaQueryFactory.delete(video).where(video.exerciseid.eq(exerciseid)).execute();
		}
		 jpaQueryFactory.delete(exercise)
         .where(exercise.exerciseId.eq(exerciseid))
         .execute();	
	}

	@Override
	public List<ListScoreClassroomFollowExerciseByStudentDTO> listScoreClassroomByStudentDTOs(int bookid) {
		List<ListScoreClassroomFollowExerciseByStudentDTO> result = jpaQueryFactory
				.select(Projections.constructor(ListScoreClassroomFollowExerciseByStudentDTO.class, 
						tutor.tutorId, student.studentid, user.fullname,
						course.courseName, classEntity.className, exercise.exerciseId, exercise.title,
						classroom.classroomId, classroom.nameClassroom, classroom.link,
						scoreClassroom.score, classroom.submitdate,scoreClassroom.status))
				.distinct()
				.from(classroom)
				.innerJoin(exercise).on(exercise.exerciseId.eq(classroom.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
				.innerJoin(classcourse).on(classcourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classcourse.classid))
				.innerJoin(course).on(course.courseId.eq(classcourse.courseid))
				.leftJoin(scoreClassroom).on(scoreClassroom.scoreid.eq(classroom.classroomId))
				.where(bookingEntity.bookId.eq(bookid))
				.fetch();
		return result;
	}

	@Override
	public DetaiHomeworkOfStudentDTO detailHomeworkOfStudent(int homeworkid) {
		DetaiHomeworkOfStudentDTO result = jpaQueryFactory
				.select(Projections.constructor(DetaiHomeworkOfStudentDTO.class, exercise.exerciseId, exercise.title,
						classcourse.classCourseId, course.courseName, classEntity.className, tutor.tutorId, user.fullname, homework.homeworkId,
						demo.demoId, homework.files, homework.title, homework.startDate,
						homework.endDate, demo.demo, demo.files,
						submit.submitid, submit.submitdate, submit.files, submit.score, bookingEntity.startDate, bookingEntity.endDate))
				.distinct()
				.from(homework)
				.innerJoin(exercise).on(exercise.exerciseId.eq(homework.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
				.innerJoin(classcourse).on(classcourse.classCourseId.eq(bookingEntity.classcourseid))
				.innerJoin(classEntity).on(classEntity.classId.eq(classcourse.classid))
				.innerJoin(course).on(course.courseId.eq(classcourse.courseid))
				.innerJoin(tutor).on(tutor.tutorId.eq(bookingEntity.tutorid))
				.innerJoin(user).on(user.userid.eq(tutor.tutorId))
				.leftJoin(demo).on(demo.demoId.eq(homework.demoid))
				.leftJoin(submit).on(submit.submitid.eq(homework.homeworkId))
				.where(homework.homeworkId.eq(homeworkid))
				.fetchFirst();
		return result;
	}

	@Override
	@Transactional
	public void updatesubmithomework(int homeworkid, String file) {
		jpaQueryFactory.update(submit).where(submit.submitid.eq(homeworkid))
		.set(submit.files, file)
		.set(submit.submitdate, dateString)
		.execute();
	}

	@Override
	public List<FilesDTO> listFileFollowExerciseid(int cid) {
		List<FilesDTO> result = jpaQueryFactory.select(Projections.constructor(FilesDTO.class, file.fileId, file.exerciseid, file.nameFile, file.files, file.status))
				.distinct()
				.from(file)
				.innerJoin(exercise).on(exercise.exerciseId.eq(file.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(exercise.exerciseId.eq(cid))
				.fetch();
		return result;
	}

	@Override
	public List<VideoDTO> listVideoFollowExerciseid(int cid) {
		List<VideoDTO> result = jpaQueryFactory.select(Projections.constructor(VideoDTO.class, video.videoId, video.exerciseid, video.nameVideo, video.video, video.status))
				.distinct()
				.from(video)
				.innerJoin(exercise).on(exercise.exerciseId.eq(video.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(exercise.exerciseId.eq(cid))
				.fetch();
		return result;
	}

	@Override
	public List<ClassRoomEDTO> listClassRoomExerciseid(int cid) {
		List<ClassRoomEDTO> result = jpaQueryFactory.select(Projections.constructor(ClassRoomEDTO.class, classroom.classroomId, classroom.exerciseid, 
				classroom.nameClassroom, classroom.link, classroom.submitdate))
				.distinct()
				.from(classroom)
				.innerJoin(exercise).on(exercise.exerciseId.eq(classroom.exerciseid))
				.innerJoin(bookingEntity).on(bookingEntity.bookId.eq(exercise.bookid))
	            .innerJoin(student).on(student.studentid.eq(bookingEntity.studentid))
	            .where(exercise.exerciseId.eq(cid))
				.fetch();
		return result;
	}

	@Transactional
	@Override
	public void deletehomework(int homeworkid) {
		Submit result = jpaQueryFactory.select(submit)
				.from(submit)
				.where(submit.submitid.eq(homeworkid))
				.fetchOne();
		if(result != null) {
			jpaQueryFactory.delete(submit).where(submit.submitid.eq(homeworkid)).execute();
			jpaQueryFactory.delete(homework).where(homework.homeworkId.eq(homeworkid)).execute();
		}else {
			jpaQueryFactory.delete(homework).where(homework.homeworkId.eq(homeworkid)).execute();
		}
	}

	@Transactional
	@Override
	public void deleteclassroom(int classroomid) {
		ScoreClassroom result = jpaQueryFactory.select(scoreClassroom)
				.from(scoreClassroom)
				.where(scoreClassroom.scoreid.eq(classroomid))
				.fetchFirst();
		if(result != null) {
			jpaQueryFactory.delete(scoreClassroom).where(scoreClassroom.scoreid.eq(classroomid)).execute();
			jpaQueryFactory.delete(classroom).where(classroom.classroomId.eq(classroomid)).execute();
		}else {
			jpaQueryFactory.delete(classroom).where(classroom.classroomId.eq(classroomid)).execute();
		}
	}

	@Override
	public String file(int fileid) {
		String result = jpaQueryFactory.select(file.files)
				.from(file)
				.where(file.fileId.eq(fileid))
				.fetchFirst();
		return result;
	}

	@Transactional
	@Override
	public void deletefile(int fileid) {
		jpaQueryFactory.delete(file).where(file.fileId.eq(fileid)).execute();
	}

	@Transactional
	@Override
	public void deletevideo(int videoid) {
		jpaQueryFactory.delete(video).where(video.videoId.eq(videoid)).execute();
	}
}

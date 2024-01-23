package com.capstoneproject.educonnect.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstoneproject.educonnect.DTO.AddClassRoomDTO;
import com.capstoneproject.educonnect.DTO.AddExerciseDTO;
import com.capstoneproject.educonnect.DTO.AddVideoDTO;
import com.capstoneproject.educonnect.DTO.ClassRoomEDTO;
import com.capstoneproject.educonnect.DTO.DetaiHomeworkOfStudentDTO;
import com.capstoneproject.educonnect.DTO.ExserciseDTO;
import com.capstoneproject.educonnect.DTO.FilesDTO;
import com.capstoneproject.educonnect.DTO.HomeWorkDTO;
import com.capstoneproject.educonnect.DTO.HomeworkEDTO;
import com.capstoneproject.educonnect.DTO.HomeworkResDTO;
import com.capstoneproject.educonnect.DTO.ListScoreClassroomFollowExerciseByStudentDTO;
import com.capstoneproject.educonnect.DTO.ScoreClassroomDTO;
import com.capstoneproject.educonnect.DTO.ScoreExerciseDTO;
import com.capstoneproject.educonnect.DTO.UClassRoomDTO;
import com.capstoneproject.educonnect.DTO.UExerciseDTO;
import com.capstoneproject.educonnect.DTO.UVideoDTO;
import com.capstoneproject.educonnect.DTO.VideoDTO;
import com.capstoneproject.educonnect.Entity.Classroom;
import com.capstoneproject.educonnect.Entity.Exercise;
import com.capstoneproject.educonnect.Entity.Files;
import com.capstoneproject.educonnect.Entity.Homework;
import com.capstoneproject.educonnect.Entity.ScoreClassroom;
import com.capstoneproject.educonnect.Entity.Submit;
import com.capstoneproject.educonnect.Entity.Video;
import com.capstoneproject.educonnect.Repository.AddExerciseRepository;
import com.capstoneproject.educonnect.Repository.AddFileRepository;
import com.capstoneproject.educonnect.Repository.ExersiceRepository;
import com.capstoneproject.educonnect.Repository.NewClassRoomRepository;
import com.capstoneproject.educonnect.Repository.NewHomeWorkRepository;
import com.capstoneproject.educonnect.Repository.NewVideoRepository;
import com.capstoneproject.educonnect.Repository.ScoreClassroomRepository;
import com.capstoneproject.educonnect.Repository.SubmitHomeWorkRepository;

@Transactional
@Service
public class ExserciseService {

	@Autowired
	private ExersiceRepository exersiceRepository;

	@Autowired
	private NewHomeWorkRepository newHomeWorkRepository;

	@Autowired
	private AddFileRepository addFileRepository;

	@Autowired
	private NewClassRoomRepository newClassRoomRepository;

	@Autowired
	private NewVideoRepository newVideoRepository;

	@Autowired
	private SubmitHomeWorkRepository submitHomeWorkRepository;

	@Autowired
	private AddExerciseRepository addExerciseRepository;

	@Autowired
	private ScoreClassroomRepository scoreClassroomRepository;

	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String dateString = today.format(formatter);

	public List<ExserciseDTO> findExersice(int cid) {
		return exersiceRepository.ListAllExserciseByStudent(cid);
	}

	public ExserciseDTO getExerciseById(int exId) {
		return exersiceRepository.getExerciseById(exId);
	}

	public String file(int fileid) {
		return exersiceRepository.file(fileid);
	}

	public void deletehomework(int homeworkid) {
		exersiceRepository.deletehomework(homeworkid);
	}

	public void deleteclassroom(int classroomid) {
		exersiceRepository.deleteclassroom(classroomid);
	}

	public void deletefile(int fileid) {
		exersiceRepository.deletefile(fileid);
	}

	public void deletevideo(int videoid) {
		exersiceRepository.deletevideo(videoid);
	}

	public List<ListScoreClassroomFollowExerciseByStudentDTO> listScoreClassroomFollowExerciseByStudentDTOs(
			int bookid) {
		List<ListScoreClassroomFollowExerciseByStudentDTO> result = exersiceRepository
				.listScoreClassroomByStudentDTOs(bookid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<FilesDTO> listFileFollowExercise(int cid) {
		List<FilesDTO> result = exersiceRepository.listFileFollowExercise(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<FilesDTO> listFileFollowExerciseid(int cid) {
		List<FilesDTO> result = exersiceRepository.listFileFollowExerciseid(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<VideoDTO> listVideoFollowExercise(int cid) {
		List<VideoDTO> result = exersiceRepository.listVideoFollowExercise(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<VideoDTO> listVideoFollowExerciseid(int cid) {
		List<VideoDTO> result = exersiceRepository.listVideoFollowExerciseid(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<ScoreExerciseDTO> listScoreFollowExercise(int bookid) {
		List<ScoreExerciseDTO> result = exersiceRepository.listScoreFollowExercise(bookid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<HomeWorkDTO> listHomeworkExersice(int cid) {
		List<HomeWorkDTO> result = exersiceRepository.listHomeworkExercise(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<HomeworkEDTO> homeworkViewByTutor(int bookid) {
		List<HomeworkEDTO> result = exersiceRepository.homeworkViewByTutor(bookid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<HomeworkResDTO> listHomeworkByExercise(int cid) {
		List<HomeworkResDTO> result = exersiceRepository.listHomeworkByExercise(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<ClassRoomEDTO> listClassRoomExersice(int cid) {
		List<ClassRoomEDTO> result = exersiceRepository.listClassRoomExercise(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public List<ClassRoomEDTO> listClassRoomExersiceid(int cid) {
		List<ClassRoomEDTO> result = exersiceRepository.listClassRoomExerciseid(cid);
		if (result.isEmpty()) {
			return Collections.emptyList();
		} else {
			return result;
		}
	}

	public void AddHomeWorkTutor(int exerciseid,
			int demoid, String file, String title, String startDate,
			String endDate) {
		Homework homework = new Homework();
		homework.setExerciseid(exerciseid);
		homework.setDemoid(demoid);
		homework.setFiles(file);
		homework.setTitle(title);
		homework.setStartDate(startDate);
		homework.setEndDate(endDate);
		newHomeWorkRepository.save(homework);
	}

	public void AddFileTutor(int exerciseid, String nameFile, String files) {
		Files file = new Files();
		file.setExerciseid(exerciseid);
		file.setNameFile(nameFile);
		file.setFiles(files);
		file.setStatus(3);
		addFileRepository.save(file);
	}

	public void AddClassRoomTutor(AddClassRoomDTO addClassRoomDTO) {
		Classroom classroom = new Classroom();
		classroom.setExerciseid(addClassRoomDTO.getExerciseid());
		classroom.setNameClassroom(addClassRoomDTO.getNameClassroom());
		classroom.setLink(addClassRoomDTO.getLink());
		classroom.setSubmitdate(dateString);
		newClassRoomRepository.save(classroom);
	}

	public void AddVideoTutor(AddVideoDTO addVideoDTO) {
		Video video = new Video();
		video.setExerciseid(addVideoDTO.getExerciseid());
		video.setNameVideo(addVideoDTO.getNameVideo());
		video.setVideo(addVideoDTO.getVideo());
		video.setStatus(2);
		newVideoRepository.save(video);
	}

	public void SubmitHomework(int homeworkid, String file) {
		Submit submit = new Submit();
		Homework homework = exersiceRepository.detailhomework(homeworkid);
		submit.setHomework(homework);
		submit.setSubmitid(homework.getHomeworkId());
		submit.setFiles(file);
		submit.setSubmitdate(dateString);
		submit.setStatus('N');
		submitHomeWorkRepository.save(submit);
	}

	// ghi điểm scoreclassroom
	public int ScoreClassroom(ScoreClassroomDTO scoreClassroomDTO) {
		ScoreClassroom scoreClassroom = new ScoreClassroom();
		Classroom classroom = exersiceRepository.detailClassroom(scoreClassroomDTO.getScoreid());
		// scoreClassroom.setClassroom(classroom);
		scoreClassroom.setScoreid(classroom.getClassroomId());
		scoreClassroom.setScore(scoreClassroomDTO.getScore());
		scoreClassroom.setStatus('Y');
		ScoreClassroom scoreClassroomReponse = scoreClassroomRepository.save(scoreClassroom);
		System.out.println("scoreClassroomReponse: " + scoreClassroomReponse.getScoreid());
		if (scoreClassroomReponse != null) {
			return 1;
		} else {
			return 0;
		}

	}

	public void UpdateExercise(UExerciseDTO uExerciseDTO) {
		exersiceRepository.UpdateExercise(uExerciseDTO);
	}

	public void UpdateHomeWord(int homeworkid,
			int demoid, String file, String title, String startDate,
			String endDate) {
		exersiceRepository.UpdateHomeWord(homeworkid, demoid, file, title, startDate, endDate);
		;
	}

	public void UpdateVideo(UVideoDTO uVideoDTO) {
		exersiceRepository.UpdateVideo(uVideoDTO);
	}

	public void UpdateFiles(int fileid, String nameFile, String files) {
		exersiceRepository.UpdateFiles(fileid, nameFile, files);
		;
	}

	public void UpdateClassRoom(UClassRoomDTO uClassRoomDTO) {
		exersiceRepository.UpdateClassRoom(uClassRoomDTO);
	}

	public void addExercise(AddExerciseDTO addExerciseDTO) {
		Exercise exercise = new Exercise();
		exercise.setBookid(addExerciseDTO.getBookid());
		exercise.setTitle(addExerciseDTO.getTitle());
		addExerciseRepository.save(exercise);
	}

	public void watchedFiles(int fileid) {
		exersiceRepository.watchedFiles(fileid);
	}

	public void watchedVideo(int videoid) {
		exersiceRepository.watchedVideo(videoid);
	}

	public void deleteExercise(int exerciseid) {
		exersiceRepository.deleteExercise(exerciseid);
	}

	public DetaiHomeworkOfStudentDTO detailHomeworkOfStudent(int homeworkid) {
		return exersiceRepository.detailHomeworkOfStudent(homeworkid);
	}

	public void updatesubmit(int homeworkid, String file) {
		exersiceRepository.updatesubmithomework(homeworkid, file);
	}

	public int updateScoresubmit(float score, int submitid) {
		return submitHomeWorkRepository.updateScoresubmit(score, submitid);
	}
}

package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

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
import com.capstoneproject.educonnect.Entity.Homework;

@Repository
public interface ExersiceRepository {
	List<ExserciseDTO> ListAllExserciseByStudent(int cid);
	
	ExserciseDTO getExerciseById(int exId);

	List<FilesDTO> listFileFollowExercise(int cid);
	
	List<FilesDTO> listFileFollowExerciseid(int cid);

	List<VideoDTO> listVideoFollowExercise(int cid);
	
	List<VideoDTO> listVideoFollowExerciseid(int cid);
  
	List<ScoreExerciseDTO> listScoreFollowExercise(int bookid);

	List<HomeWorkDTO> listHomeworkExercise(int cid);
	
  List<HomeworkEDTO> homeworkViewByTutor(int bookid);

	List<HomeworkResDTO> listHomeworkByExercise(int cid);

	List<ClassRoomEDTO> listClassRoomExercise(int cid);
	
	List<ClassRoomEDTO> listClassRoomExerciseid(int cid);
	
	List<ListScoreClassroomFollowExerciseByStudentDTO> listScoreClassroomByStudentDTOs(int bookid);

	DetaiHomeworkOfStudentDTO detailHomeworkOfStudent(int homeworkid);
	
	public void UpdateExercise(UExerciseDTO uExerciseDTO);
	
	public void UpdateHomeWord(int homeworkid,
			int demoid, String file, String title, String startDate,
			String endDate);
	
	public void UpdateVideo(UVideoDTO uVideoDTO);
	
	public void UpdateFiles(int fileid, String nameFile, String files);
	
	public void UpdateClassRoom(UClassRoomDTO uClassRoomDTO);
	
	public void watchedFiles(int fileid);
	
	public void watchedVideo(int videoid);
	
	public void deleteExercise(int exerciseid);
	
	Homework detailhomework(int id);
	
	Classroom detailClassroom(int classroomid);
	
	void updatesubmithomework(int homeworkid, String file);
	
	void deletehomework(int homeworkid);
	
	void deleteclassroom(int classroomid);
	
	void deletefile(int fileid);
	
	void deletevideo(int videoid);
	
	String file (int fileid);
}

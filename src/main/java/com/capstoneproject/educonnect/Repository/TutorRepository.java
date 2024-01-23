package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

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


@Repository
public interface TutorRepository {
  
	List<Top3TutorDTO> findTop3Tutors();
	
	ViewTutorDTO findViewTutor(int tutorId);
	
	public void UpdateTutor(int tutorid, String fullname, String phone,
			String file, int gender, String birthdate, String city, String wards);
	
	List<ViewScreenTutorDTO> ViewScreenTutor(int tutorid);

	List<CourseAllByTutor> AllCourseByTutor(int tutorid);
	
	List<TutorForStudentDTO> TutorForStudent(int tutorid);
	
	ShowDetailHomeworkDTO showDetailHomework(int homeworkid);
  
	DetailSubmitOfHomeworkDTO detailSubmitOfHomeworkDTO(int homeworkid);
  
	public void TutorScorePointsForSubmit(TutorScorePointsForSubmitDTO tutorScorePointsForSubmitDTO);
	
	ShowDetailVideoDTO showDetailVideo(int videoid);
	
	List<DemoFollowClasscourseDTO> demoFollowClasscourse(int classcourseid);
  
	DemoDetailOfClasscourseDTO demoDetailOfClasscourse(int demoid);
	
	List<ListStudentFinishedOfTutorDTO> listStudentFinishedOfTutorDTOs(int tutorid, int status, int page , int courseid);
	
	long countstudent(int tutorid, int status, int courseid);
	
	TutorViewProfileStudentFinishedDTO tutorViewProfileStudentFinishedDTO(int studentid);
	
	List<ListStudentOfTutorDTO> listStudentOfTutor(int tutorid);
	
	TutorViewProfileStudentDTO tutorViewProfileStudent(int studentid);

	
	List<TutorViewProfileTimelineStudentDTO> tutorViewProfileTimelineStudent(int bookid);

	BookTutorDTO booktutor(int tutorid, int classcourseid);
  
	
	List<DescListAllTutorDTO> DescListAllTutor(int courseid, int page);
	
	long countpage (int courseid);

	List<ListExerciseByTutorDTO> listExerciseByTutorDTOs(int tutorid);
  
	List<ListClassroomByTutorDTO> listClassroomByTutor(int exerciseid);
	
	List<CourseOfTutorDTO> courOfTutor(int tutorid);
	
	void updatesalary(String email);
	
	StudentViewDetailTutorDTO studentViewDetailTutorDTO(int tutorid);
	
	boolean checktutor(int tutorid);
	
	List<ListFeedbackBookingOfTutorDTO> listFeedbackBookingOfTutor(int tutorid, int classcourse);

	VTutorExerciseDTO viewtutor(int classcourseid, int tutorid);
}

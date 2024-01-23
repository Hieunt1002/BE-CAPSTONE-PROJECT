package com.capstoneproject.educonnect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.CourseByClassDTO;
import com.capstoneproject.educonnect.DTO.CourseByTutorDTO;
import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.RegisteredCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorExerciseDTO;
import com.capstoneproject.educonnect.Repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	
	public List<CourseByClassDTO> findCourseByClass (int classcourseid){
		return courseRepository.ListAllCourseByClass(classcourseid);
	}
	public List<CourseByTutorDTO> findCourseByTutor (int CourseId){
		return courseRepository.List4CourseByTutor(CourseId);
	}
	public List<RegisteredCourseDTO> findAllRegisteredCourse (int StudentId){
		return courseRepository.ListAllRegisteredCourse(StudentId);
	}
	
	public List<CourseByClassDTO> ListCourseForStudent (int classcourseid, int studentid){
		return courseRepository.ListCourseForStudent(classcourseid, studentid);
	}
	
	public TutorExerciseDTO tutorexercise(int bookid) {
		return courseRepository.tutorexercise(bookid);
	}
	
	public List<CourseDTO> listcourse(){
		return courseRepository.listcourse();
	}
}
  
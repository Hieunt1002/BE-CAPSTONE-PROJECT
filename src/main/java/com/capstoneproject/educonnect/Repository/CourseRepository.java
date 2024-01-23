package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.CourseByClassDTO;
import com.capstoneproject.educonnect.DTO.CourseByTutorDTO;
import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.RegisteredCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorExerciseDTO;
@Repository
public interface CourseRepository {

	List<CourseByClassDTO> ListAllCourseByClass(int classcourseid);

	List<CourseByTutorDTO> List4CourseByTutor(int CourseId);

	List<RegisteredCourseDTO> ListAllRegisteredCourse(int StudentId);
	
	List<CourseByClassDTO> ListCourseForStudent(int classcourseid, int studentid);
	
	TutorExerciseDTO tutorexercise(int bookid);
	
	List<CourseDTO> listcourse();
	
	List<CourseDTO> listcoursebydiscountid(int discountid);
}

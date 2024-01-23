package com.capstoneproject.educonnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.DTO.CourseByClassDTO;
import com.capstoneproject.educonnect.DTO.CourseByTutorDTO;
import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.RegisteredCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorExerciseDTO;
import com.capstoneproject.educonnect.Service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/findCourseByClass")
	public List<CourseByClassDTO> findallCourseByClass (@RequestParam int classcourseid){
		return courseService.findCourseByClass(classcourseid);
	}
	
	@GetMapping("/findCourseByTutor")
	public List<CourseByTutorDTO> findCourseByTutor (@RequestParam int tutorid){
		return courseService.findCourseByTutor(tutorid);	
	}
	
	@GetMapping("/RegisteredCourse")
	public List<RegisteredCourseDTO> findAllRegisteredCourse (@RequestParam int StudentId){
		return courseService.findAllRegisteredCourse(StudentId);	
	}
	
	@GetMapping("/listcourseforstudent")
	public List<CourseByClassDTO> ListCourseForStudent (@RequestParam int classcourseid, @RequestParam int studentid){
		return courseService.ListCourseForStudent(classcourseid, studentid);	
	}
	
	@GetMapping("/tutorexercise")
	public TutorExerciseDTO tutorexercise(int bookid) {
		return courseService.tutorexercise(bookid);
	}
	
	@GetMapping("/listcourse")
	public List<CourseDTO> listcourse (){
		return courseService.listcourse();
	}
}

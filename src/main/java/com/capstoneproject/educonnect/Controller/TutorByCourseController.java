package com.capstoneproject.educonnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.DTO.List4TutorByCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorByCourseDTO;
import com.capstoneproject.educonnect.Service.TutorByCourseService;

@RestController
@RequestMapping("/tutorByCourse")
public class TutorByCourseController {
	@Autowired
	private TutorByCourseService tutorByCourseService;
	
	@GetMapping("/findTutorByCourse")
	public List<TutorByCourseDTO> findallTutorByCourse(@RequestParam int courseid, @RequestParam(required = false) Integer page) {
		int pageNumber = (page != null) ? page : 0;
		return tutorByCourseService.findTutorByCourse(courseid, pageNumber);
	}
	
	@GetMapping(value = "/pagetutor/{courseid}")
	public int getPage(@PathVariable int courseid) {
		int result = tutorByCourseService.countpage(courseid);
		if(result == 0) {
			return 1;
		}else {
			int page = result;
			return page;
		}
	}

	
	@GetMapping("/find4TutorByCourse")
	public List<List4TutorByCourseDTO> findTutorRelateCourse (@RequestParam int CourseId){
		return tutorByCourseService.findTutorRelateCourse(CourseId);	
	}
	
	@GetMapping("/search")
	public List<TutorByCourseDTO> ListSeachTutor (int classcoursid, String name){
		return tutorByCourseService.ListSeachTutor(classcoursid, name);	
	}
}

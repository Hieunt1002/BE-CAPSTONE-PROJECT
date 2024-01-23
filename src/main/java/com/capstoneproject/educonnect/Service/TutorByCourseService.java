package com.capstoneproject.educonnect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.GetTutorRequestDTO;
import com.capstoneproject.educonnect.DTO.List4TutorByCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorByCourseDTO;
import com.capstoneproject.educonnect.Repository.TutorByCourseRepository;


@Service
public class TutorByCourseService {
	@Autowired
	private TutorByCourseRepository tutorByCourseRepository;
	
	public List<TutorByCourseDTO> findTutorByCourse (int courseid, int page){
		int pages = (page - 1) * 12;
		return tutorByCourseRepository.ListAllTutorByCourse(courseid, pages);
	}
	
	public int countpage(int courseid) {
		int result = tutorByCourseRepository.countpage(courseid);
		return result;
	}
	
	public List<List4TutorByCourseDTO> findTutorRelateCourse(int tutorid){
		return tutorByCourseRepository.List4TutorByCourse(tutorid);
    }
	
	public List<TutorByCourseDTO> ListSeachTutor (int classcourse, String name){
		return tutorByCourseRepository.ListSeachTutor(classcourse, name);
	}
}

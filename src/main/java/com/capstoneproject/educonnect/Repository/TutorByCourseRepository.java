package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.GetTutorRequestDTO;
import com.capstoneproject.educonnect.DTO.List4TutorByCourseDTO;
import com.capstoneproject.educonnect.DTO.TutorByCourseDTO;
@Repository
public interface TutorByCourseRepository {
	List<TutorByCourseDTO> ListAllTutorByCourse (int courseid, int page);
	
	int countpage (int courseid);
	
	List<List4TutorByCourseDTO> List4TutorByCourse (int tutorid);
	
	List<TutorByCourseDTO> ListSeachTutor(int classcourse, String name);
	
}

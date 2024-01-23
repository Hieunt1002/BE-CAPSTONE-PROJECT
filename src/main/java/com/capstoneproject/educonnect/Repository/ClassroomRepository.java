package com.capstoneproject.educonnect.Repository;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.ViewInfoClassroomDTO;

@Repository
public interface ClassroomRepository {

	ViewInfoClassroomDTO ViewInfoClassroom(int classroomId);
	
	
	
	
}

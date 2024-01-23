package com.capstoneproject.educonnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.AddLinkClassroomDTO;
import com.capstoneproject.educonnect.DTO.ViewInfoClassroomDTO;
import com.capstoneproject.educonnect.Entity.Classroom;
import com.capstoneproject.educonnect.Repository.AddLinkRepository;
import com.capstoneproject.educonnect.Repository.ClassroomRepository;

@Service
public class ClassroomService {
	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private AddLinkRepository addLinkRepository;
	
	public ViewInfoClassroomDTO ViewInfoClassroom(int classroomId) {
		return classroomRepository.ViewInfoClassroom(classroomId);
	}
	
	public void AddLinkClassroom(AddLinkClassroomDTO addLinkClassroomDTO) {
		Classroom classroom = new Classroom();
		classroom.setExerciseid(addLinkClassroomDTO.getExerciseId());
		classroom.setNameClassroom(addLinkClassroomDTO.getNameClassroom());
		classroom.setLink(addLinkClassroomDTO.getLink());
		addLinkRepository.save(classroom);	
	}
	
}

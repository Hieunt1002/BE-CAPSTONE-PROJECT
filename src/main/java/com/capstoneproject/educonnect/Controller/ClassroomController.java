package com.capstoneproject.educonnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.DTO.AddLinkClassroomDTO;
import com.capstoneproject.educonnect.DTO.ViewInfoClassroomDTO;
import com.capstoneproject.educonnect.Service.ClassroomService;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	@Autowired
	private ClassroomService classroomService;
	
	@GetMapping("/viewClassroom")
	public ViewInfoClassroomDTO ViewInfoClassroom(int classroomId) {
		return classroomService.ViewInfoClassroom(classroomId);
	}
	
	@PostMapping("/addlink")
	public void addLinks(@RequestBody AddLinkClassroomDTO addLinkClassroomDTO) {
		classroomService.AddLinkClassroom(addLinkClassroomDTO);
	}
	
}

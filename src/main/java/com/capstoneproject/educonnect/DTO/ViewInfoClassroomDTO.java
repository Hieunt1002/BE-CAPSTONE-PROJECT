package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewInfoClassroomDTO {
	
	@JsonProperty("courseId")
    private int courseId;
	
	@JsonProperty("courseName")
	private String courseName;

	@JsonProperty("classroomid")
    private int classroomId;
    
	@JsonProperty("exerciseid")
	private int exerciseid;
	
	@JsonProperty("nameClassroom")
	private String nameClassroom;
	
	@JsonProperty("link")
	private String link;
	
}

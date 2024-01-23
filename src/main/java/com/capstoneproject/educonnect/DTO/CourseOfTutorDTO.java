package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseOfTutorDTO {
	@JsonProperty("classcourseid")
	private int classCourseId;
	
	@JsonProperty("courseName")
	private String coursename;
	
	@JsonProperty("classname")
	private String classname;
	
	@JsonProperty("tutorid")
	private int tutorid;
	
	@JsonProperty("courseid")
	private int courseid;

}

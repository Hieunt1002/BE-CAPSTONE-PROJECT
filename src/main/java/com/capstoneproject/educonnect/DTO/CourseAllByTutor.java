package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseAllByTutor {
	@JsonProperty("classcourseid")
	private int classCourseId;
	
	@JsonProperty("courseName")
	private String coursename;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("class")
	private String className;
	
	@JsonProperty("level")
	private String level;
	
	@JsonProperty("count_student")
	private long count_student;
}

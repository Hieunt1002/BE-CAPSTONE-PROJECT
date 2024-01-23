package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseByTutorDTO {

	@JsonProperty("classCourseId")
	private int classCourseId;
	
	@JsonProperty("courseName")
	private String courseName;
	
	@JsonProperty("classId")
	private int classId;
	
	@JsonProperty("level")
	private String level;
	
	@JsonProperty("className")
	private String className;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("discount")
	private float discount;
	
	@JsonProperty("CountStudent")
	private long CountStudent;
	
}

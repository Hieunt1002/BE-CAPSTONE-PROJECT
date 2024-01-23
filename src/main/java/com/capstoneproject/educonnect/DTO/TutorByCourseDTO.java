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
public class TutorByCourseDTO {
	@JsonProperty("classcourseid")
	private int classcourseid;
	
	@JsonProperty("coursename")
	private String courseName;
	
	@JsonProperty("classentity")
	private String classentity;
	
	@JsonProperty("tutorid")
	private int tutorId; 

	@JsonProperty("img")
	private String img;
	
	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("ranks")
	private double ranks;
	
	@JsonProperty("CountStudent")
	private long CountStudent;
	
	
}

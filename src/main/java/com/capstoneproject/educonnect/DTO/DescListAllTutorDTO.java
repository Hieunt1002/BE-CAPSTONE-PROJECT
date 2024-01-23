package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescListAllTutorDTO {
	
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

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
public class ViewStudentTrylearnDTO {
	
	@JsonProperty("tutorid")
	private int tutorId;

	@JsonProperty("studentid")
	private int studentid;

	@JsonProperty("fullnamestudent")
	private String fullnamestudent;
	
	@JsonProperty("fullnametutor")
	private String fullnametutor;

	@JsonProperty("img")
	private String img;

	@JsonProperty("classcourseid")
	private int classCourseId;

	@JsonProperty("courseName")
	private String coursename;

	@JsonProperty("class")
	private String className;
	
	@JsonProperty("trylearningid")
	private int trylearningid;
	
	@JsonProperty("dateregister")
	private String dateregister;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("email")
	private String email;
}

package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListStudentFinishedOfTutorDTO {

	@JsonProperty("tutorid")
	private int tutorId;
	
	@JsonProperty("bookid")
	private int bookid;

	@JsonProperty("studentid")
	private int studentid;

	@JsonProperty("fullname")
	private String fullname;

	@JsonProperty("img")
    private String img;
	
	@JsonProperty("classcourseid")
	private int classCourseId;
	
	@JsonProperty("coursename")
	private String courseName;
	
	@JsonProperty("class")
	private String className;

	@JsonProperty("startdate")
    private String startDate;

	@JsonProperty("enddate")
    private String endDate;
}

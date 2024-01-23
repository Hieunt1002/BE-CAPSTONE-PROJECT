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
public class RegisteredCourseDTO {
	
	@JsonProperty("Studentid")
	private int StudentId;
		
	@JsonProperty("bookid")
	private int bookId;
	
	@JsonProperty("classCourseid")
	private int classCourseId;
	
	@JsonProperty("courseName")
	private String courseName;
	
	@JsonProperty("classname")
	private String classname;
	
	@JsonProperty("dateregister")
	private String dateregister;
	
	@JsonProperty("startdate")
	private String startDate;
	
	@JsonProperty("enddate")
	private String endDate;
	
	@JsonProperty("status")
	private int status;
}

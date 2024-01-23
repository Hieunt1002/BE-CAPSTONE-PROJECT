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
public class ListFeedbackDTO {
	@JsonProperty("studentId")
	private int studentId;

	@JsonProperty("studentname")
	private String studentName;
	
	@JsonProperty("startdate")
	private String startdate;

	@JsonProperty("enddate")
	private String endDate;

	@JsonProperty("bookid")
	private int bookId;

	@JsonProperty("tutorId")
	private int tutorId;

	@JsonProperty("tutorname")
	private String tutorName;
	
	@JsonProperty("courseName")
	private String courseName;
	
	@JsonProperty("classname")
	private String classname;
	
	@JsonProperty("feedbackid")
    private int feedbackid;
}

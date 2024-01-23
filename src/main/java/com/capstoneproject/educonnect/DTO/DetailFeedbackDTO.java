package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailFeedbackDTO {
	@JsonProperty("studentId")
	private int studentId;

	@JsonProperty("studentname")
	private String studentName;

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
	
	@JsonProperty("feedbackid")
    private int feedbackid;
	
	@JsonProperty("notes")
    private String notes;

	@JsonProperty("ranks")
    private float ranks;
}

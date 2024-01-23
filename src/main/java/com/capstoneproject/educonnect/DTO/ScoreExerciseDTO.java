package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreExerciseDTO {
	@JsonProperty("exerciseid")
    private int exerciseId;
	
	@JsonProperty("studentid")
	private int studentid;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("coursename")
	private String courseName;
	
	@JsonProperty("startDate")
    private String startDate;
	
	@JsonProperty("endDate")
    private String endDate;
	
	@JsonProperty("submitdate")
    private String submitdate;
	
	@JsonProperty("score")
	private float score;
	
}

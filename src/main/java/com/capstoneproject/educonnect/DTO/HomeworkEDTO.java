package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkEDTO {

	@JsonProperty("homeworkid")
	private int homeworkId;

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("title")
    private String title;
	
	@JsonProperty("files")
    private String files;
	
	@JsonProperty("submitdate")
	private String submitdate;
	
	@JsonProperty("score")
	private Float score;
	
	@JsonProperty("studentid")
	private int studentid;
	
  @JsonProperty("status")
    private char status;
	
	@JsonProperty("studentname")
	private String studentname;
}
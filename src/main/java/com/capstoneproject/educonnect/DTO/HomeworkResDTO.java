package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkResDTO {

	@JsonProperty("homeworkid")
	private int homeworkId;

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("files")
    private String files;
	
	@JsonProperty("startDate")
    private String startDate;
	
	@JsonProperty("endDate")
    private String endDate;
	
	@JsonProperty("demo")
    private String demoName;

	@JsonProperty("linkDemo")
    private String linkDemo;
}

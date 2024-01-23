package com.capstoneproject.educonnect.DTO;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkAddDTO {

	@JsonProperty("homeworkid")
	private int homeworkId;
	
	@JsonProperty("tutorid")
	private int tutorid;

	@JsonProperty("exerciseid")
    private int exerciseid;

	@JsonProperty("title")
	private String title;
	
//	@JsonProperty("file")
//    private MultipartFile file;
	
	@JsonProperty("startDate")
    private String startDate;
	
	@JsonProperty("endDate")
    private String endDate;
	
	@JsonProperty("demo")
    private String demoName;
	
	@JsonProperty("demoid")
    private int demoid;

	@JsonProperty("linkDemo")
    private String linkDemo;
}

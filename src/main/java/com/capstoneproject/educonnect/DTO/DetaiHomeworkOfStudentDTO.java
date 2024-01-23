package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetaiHomeworkOfStudentDTO {
	@JsonProperty("exerciseid")
    private int exerciseId;

    @JsonProperty("titleExercise")
    private String titleExercise;
    
    @JsonProperty("classcourseid")
	private int classCourseId;
    
    @JsonProperty("courseName")
	private String courseName;
    
    @JsonProperty("classname")
    private String classname;
    
    @JsonProperty("tutorid")
    private int tutorId;
    
    @JsonProperty("fullname")
    private String fullname;
    
	@JsonProperty("homeworkid")
	private int homeworkId;

	@JsonProperty("demoid")
    private int demoid;

	@JsonProperty("filesHomework")
    private String filesHomework;

	@JsonProperty("titleHomework")
    private String titleHomework;

	@JsonProperty("startdate")
    private String startDate;

	@JsonProperty("enddate")
    private String endDate;
	
	@JsonProperty("demo")
    private String demoName;
	
	@JsonProperty("filesDemo")
    private String filesDemo;

	@JsonProperty("submitid")
	private int submitid;

	@JsonProperty("submitdate")
	private String submitdate;

	@JsonProperty("fileSubmid")
	private String fileSubmid;

	@JsonProperty("score")
	private float score;
	
	@JsonProperty("startdateb")
	private String startdateb;
	
	@JsonProperty("enddateb")
	private String enddateb;
}

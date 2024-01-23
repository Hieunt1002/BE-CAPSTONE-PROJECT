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
public class ShowDetailHomeworkDTO {
 // NOTE thuộc tính
	@JsonProperty("exerciseid")
    private int exerciseId;

    @JsonProperty("titleExercise")
    private String titleExercise;
    
    @JsonProperty("classcourseid")
	private int classCourseId;
    
    @JsonProperty("courseName")
	private String courseName;
    
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
}

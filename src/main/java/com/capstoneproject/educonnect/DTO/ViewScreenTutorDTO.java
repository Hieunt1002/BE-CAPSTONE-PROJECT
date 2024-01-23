package com.capstoneproject.educonnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewScreenTutorDTO {

	@JsonProperty("courseId")
    private int courseId;
	
	@JsonProperty("courseName")
	private String courseName;
	
	@JsonProperty("tutorid")
	private int tutorId;
	
	@JsonProperty("fullName")
	private String fullName;
	
	@JsonProperty("class")
	private String className;
	
	@JsonProperty("namevideo")
	private String nameVideo;	
	
	@JsonProperty("video")
	private String video;
	
	@JsonProperty("notes")
	private String notes;
	
	@JsonProperty("ranks")
	private float ranks;
	
	@JsonProperty("countStudent")
	private long CountStudent;
}

